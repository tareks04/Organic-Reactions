package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.AppLanguage
import com.example.data.model.BasicTopic
import com.example.data.model.FunctionalGroupItem
import com.example.data.model.HybridizationType
import com.example.data.model.MainTab
import com.example.data.model.NamedReaction
import com.example.data.model.QuizQuestion
import com.example.data.model.ReactionCategory
import com.example.data.repository.ChemistryRepository
import com.example.data.repository.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class PracticeMode {
  QUIZ,
  FLASHCARDS,
}

data class ChemistryUiState(
  val language: AppLanguage = AppLanguage.EN,
  val selectedTab: MainTab = MainTab.BASICS,
  val selectedHybridization: HybridizationType = HybridizationType.SP3,
  val basicTopics: List<BasicTopic> = emptyList(),
  val functionalGroups: List<FunctionalGroupItem> = emptyList(),
  val allReactions: List<NamedReaction> = emptyList(),
  val filteredReactions: List<NamedReaction> = emptyList(),
  val searchQuery: String = "",
  val selectedCategory: ReactionCategory = ReactionCategory.ALL,
  val selectedPartFilter: String = "all",
  val showBookmarksOnly: Boolean = false,
  val bookmarkedIds: Set<Int> = emptySet(),
  val selectedReaction: NamedReaction? = null,
  val practiceMode: PracticeMode = PracticeMode.QUIZ,
  val quizQuestions: List<QuizQuestion> = emptyList(),
  val currentQuizIndex: Int = 0,
  val selectedQuizOption: Int? = null,
  val quizScore: Int = 0,
  val quizAttempted: Int = 0,
  val flashcardIndex: Int = 0,
  val isFlashcardFlipped: Boolean = false,
  val flashcardBookmarksOnly: Boolean = false,
)

class ChemistryViewModel(application: Application) : AndroidViewModel(application) {

  private val repository = ChemistryRepository()
  private val prefs = PreferencesManager(application.applicationContext)

  private val allReactions = repository.getAllReactions()
  private val quizQuestions = repository.getQuizQuestions()

  private val _uiState =
    MutableStateFlow(
      ChemistryUiState(
        basicTopics = repository.getBasicTopics(),
        functionalGroups = repository.getFunctionalGroups(),
        allReactions = allReactions,
        filteredReactions = allReactions,
        quizQuestions = quizQuestions,
      )
    )
  val uiState: StateFlow<ChemistryUiState> = _uiState.asStateFlow()

  init {
    viewModelScope.launch {
      prefs.languageFlow.collect { lang -> _uiState.update { it.copy(language = lang) } }
    }
    viewModelScope.launch {
      prefs.bookmarksFlow.collect { ids ->
        _uiState.update { state ->
          val updated = state.copy(bookmarkedIds = ids)
          updated.copy(filteredReactions = filterReactions(updated))
        }
      }
    }
  }

  fun toggleLanguage() {
    val next = if (_uiState.value.language == AppLanguage.EN) AppLanguage.BN else AppLanguage.EN
    _uiState.update { it.copy(language = next) }
    viewModelScope.launch { prefs.setLanguage(next) }
  }

  fun selectTab(tab: MainTab) {
    _uiState.update { it.copy(selectedTab = tab) }
  }

  fun selectHybridization(type: HybridizationType) {
    _uiState.update { it.copy(selectedHybridization = type) }
  }

  fun updateSearchQuery(query: String) {
    _uiState.update { state ->
      val next = state.copy(searchQuery = query)
      next.copy(filteredReactions = filterReactions(next))
    }
  }

  fun selectCategory(category: ReactionCategory) {
    _uiState.update { state ->
      val next = state.copy(selectedCategory = category)
      next.copy(filteredReactions = filterReactions(next))
    }
  }

  fun selectPartFilter(part: String) {
    _uiState.update { state ->
      val next = state.copy(selectedPartFilter = part)
      next.copy(filteredReactions = filterReactions(next))
    }
  }

  fun toggleBookmarksOnly() {
    _uiState.update { state ->
      val next = state.copy(showBookmarksOnly = !state.showBookmarksOnly)
      next.copy(filteredReactions = filterReactions(next))
    }
  }

  fun toggleBookmark(reactionId: Int) {
    viewModelScope.launch { prefs.toggleBookmark(reactionId) }
  }

  fun openReactionDetail(reaction: NamedReaction) {
    _uiState.update { it.copy(selectedReaction = reaction) }
  }

  fun openReactionById(reactionId: Int) {
    val match = allReactions.find { it.id == reactionId } ?: return
    _uiState.update { it.copy(selectedReaction = match) }
  }

  fun closeReactionDetail() {
    _uiState.update { it.copy(selectedReaction = null) }
  }

  fun selectPracticeMode(mode: PracticeMode) {
    _uiState.update { it.copy(practiceMode = mode) }
  }

  fun answerQuiz(optionIndex: Int) {
    val state = _uiState.value
    if (state.selectedQuizOption != null) return
    val currentQ = state.quizQuestions.getOrNull(state.currentQuizIndex) ?: return
    val isCorrect = optionIndex == currentQ.correctIndex
    _uiState.update {
      it.copy(
        selectedQuizOption = optionIndex,
        quizScore = if (isCorrect) it.quizScore + 1 else it.quizScore,
        quizAttempted = it.quizAttempted + 1,
      )
    }
  }

  fun nextQuizQuestion() {
    _uiState.update { state ->
      val nextIdx = (state.currentQuizIndex + 1) % state.quizQuestions.size
      state.copy(currentQuizIndex = nextIdx, selectedQuizOption = null)
    }
  }

  fun resetQuiz() {
    _uiState.update {
      it.copy(currentQuizIndex = 0, selectedQuizOption = null, quizScore = 0, quizAttempted = 0)
    }
  }

  fun flipFlashcard() {
    _uiState.update { it.copy(isFlashcardFlipped = !it.isFlashcardFlipped) }
  }

  fun nextFlashcard() {
    val deck = activeFlashcardDeck()
    if (deck.isEmpty()) return
    _uiState.update {
      it.copy(flashcardIndex = (it.flashcardIndex + 1) % deck.size, isFlashcardFlipped = false)
    }
  }

  fun prevFlashcard() {
    val deck = activeFlashcardDeck()
    if (deck.isEmpty()) return
    _uiState.update {
      val nextIdx = (it.flashcardIndex - 1 + deck.size) % deck.size
      it.copy(flashcardIndex = nextIdx, isFlashcardFlipped = false)
    }
  }

  fun randomFlashcard() {
    val deck = activeFlashcardDeck()
    if (deck.size <= 1) return
    val current = _uiState.value.flashcardIndex
    val candidates = deck.indices.filter { it != current }
    _uiState.update { it.copy(flashcardIndex = candidates.random(), isFlashcardFlipped = false) }
  }

  fun toggleFlashcardBookmarksOnly() {
    _uiState.update {
      it.copy(
        flashcardBookmarksOnly = !it.flashcardBookmarksOnly,
        flashcardIndex = 0,
        isFlashcardFlipped = false,
      )
    }
  }

  fun activeFlashcardDeck(): List<NamedReaction> {
    val state = _uiState.value
    return if (state.flashcardBookmarksOnly && state.bookmarkedIds.isNotEmpty()) {
      state.allReactions.filter { it.id in state.bookmarkedIds }
    } else {
      state.allReactions
    }
  }

  private fun filterReactions(state: ChemistryUiState): List<NamedReaction> {
    val q = state.searchQuery.trim().lowercase()
    return state.allReactions.filter { r ->
      val catOk =
        state.selectedCategory == ReactionCategory.ALL || r.category == state.selectedCategory
      val partOk = state.selectedPartFilter == "all" || state.selectedPartFilter in r.parts
      val bookmarkOk = !state.showBookmarksOnly || r.id in state.bookmarkedIds
      val searchOk =
        q.isEmpty() ||
          r.nameEn.lowercase().contains(q) ||
          r.nameBn.lowercase().contains(q) ||
          r.conceptEn.lowercase().contains(q) ||
          r.conceptBn.lowercase().contains(q) ||
          r.reagents.lowercase().contains(q) ||
          r.equation.lowercase().contains(q) ||
          r.tags.lowercase().contains(q) ||
          "#${r.id}" == q ||
          "${r.id}" == q
      catOk && partOk && bookmarkOk && searchOk
    }
  }
}
