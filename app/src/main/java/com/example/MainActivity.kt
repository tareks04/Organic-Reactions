package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material.icons.outlined.Science
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material.icons.rounded.Quiz
import androidx.compose.material.icons.rounded.Science
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.model.AppLanguage
import com.example.data.model.MainTab
import com.example.ui.components.ReactionDetailSheet
import com.example.ui.components.TopLanguageBar
import com.example.ui.screens.BasicsScreen
import com.example.ui.screens.PracticeScreen
import com.example.ui.screens.ReactionsScreen
import com.example.ui.theme.BengaliFontFamily
import com.example.ui.theme.DisplayFontFamily
import com.example.ui.theme.OrganicChemTheme
import com.example.ui.viewmodel.ChemistryViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      OrganicChemTheme {
        OrganicChemApp()
      }
    }
  }
}

@Composable
fun OrganicChemApp(viewModel: ChemistryViewModel = viewModel()) {
  val state by viewModel.uiState.collectAsStateWithLifecycle()

  // Navigate back from secondary tabs to Basics before exiting
  BackHandler(enabled = state.selectedTab != MainTab.BASICS && state.selectedReaction == null) {
    viewModel.selectTab(MainTab.BASICS)
  }

  val navItems =
    listOf(
      Triple(
        MainTab.BASICS,
        if (state.language == AppLanguage.EN) "Basics" else "মৌলিক",
        Icons.Rounded.MenuBook to Icons.Outlined.MenuBook,
      ),
      Triple(
        MainTab.REACTIONS,
        if (state.language == AppLanguage.EN) "56 Reactions" else "৫৬ বিক্রিয়া",
        Icons.Rounded.Science to Icons.Outlined.Science,
      ),
      Triple(
        MainTab.PRACTICE,
        if (state.language == AppLanguage.EN) "Quiz & Cards" else "অনুশীলন",
        Icons.Rounded.Quiz to Icons.Outlined.Quiz,
      ),
    )

  BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
    val useNavRail = maxWidth >= 720.dp

    Scaffold(
      modifier = Modifier.fillMaxSize(),
      containerColor = MaterialTheme.colorScheme.background,
      contentWindowInsets = WindowInsets(0, 0, 0, 0),
      topBar = {
        TopLanguageBar(
          language = state.language,
          onToggleLanguage = viewModel::toggleLanguage,
        )
      },
      bottomBar = {
        if (!useNavRail) {
          NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
            navItems.forEach { (tab, label, icons) ->
              val selected = state.selectedTab == tab
              NavigationBarItem(
                selected = selected,
                onClick = { viewModel.selectTab(tab) },
                modifier = Modifier.testTag("nav_${tab.name.lowercase()}"),
                icon = {
                  Icon(
                    imageVector = if (selected) icons.first else icons.second,
                    contentDescription = label,
                  )
                },
                label = {
                  Text(
                    text = label,
                    fontFamily =
                      if (state.language == AppLanguage.EN) DisplayFontFamily
                      else BengaliFontFamily,
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                    fontSize = 12.sp,
                  )
                },
              )
            }
          }
        }
      },
    ) { innerPadding ->
      Row(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
        if (useNavRail) {
          NavigationRail(containerColor = MaterialTheme.colorScheme.surface) {
            navItems.forEach { (tab, label, icons) ->
              val selected = state.selectedTab == tab
              NavigationRailItem(
                selected = selected,
                onClick = { viewModel.selectTab(tab) },
                modifier = Modifier.testTag("nav_rail_${tab.name.lowercase()}"),
                icon = {
                  Icon(
                    imageVector = if (selected) icons.first else icons.second,
                    contentDescription = label,
                  )
                },
                label = {
                  Text(
                    text = label,
                    fontFamily =
                      if (state.language == AppLanguage.EN) DisplayFontFamily
                      else BengaliFontFamily,
                    fontSize = 11.sp,
                  )
                },
              )
            }
          }
        }

        Column(modifier = Modifier.weight(1f).fillMaxSize()) {
          when (state.selectedTab) {
            MainTab.BASICS ->
              BasicsScreen(
                language = state.language,
                topics = state.basicTopics,
                functionalGroups = state.functionalGroups,
                selectedHybridization = state.selectedHybridization,
                onSelectHybridization = viewModel::selectHybridization,
                onNavigateToReactions = { viewModel.selectTab(MainTab.REACTIONS) },
                onNavigateToPractice = { viewModel.selectTab(MainTab.PRACTICE) },
              )
            MainTab.REACTIONS ->
              ReactionsScreen(
                language = state.language,
                reactions = state.filteredReactions,
                totalCount = state.allReactions.size,
                searchQuery = state.searchQuery,
                selectedCategory = state.selectedCategory,
                selectedPartFilter = state.selectedPartFilter,
                showBookmarksOnly = state.showBookmarksOnly,
                bookmarkedIds = state.bookmarkedIds,
                onSearchChange = viewModel::updateSearchQuery,
                onSelectCategory = viewModel::selectCategory,
                onSelectPartFilter = viewModel::selectPartFilter,
                onToggleBookmarksOnly = viewModel::toggleBookmarksOnly,
                onToggleBookmark = viewModel::toggleBookmark,
                onOpenReaction = viewModel::openReactionDetail,
              )
            MainTab.PRACTICE ->
              PracticeScreen(
                language = state.language,
                mode = state.practiceMode,
                quizQuestions = state.quizQuestions,
                currentQuizIndex = state.currentQuizIndex,
                selectedQuizOption = state.selectedQuizOption,
                quizScore = state.quizScore,
                quizAttempted = state.quizAttempted,
                flashcardDeck = viewModel.activeFlashcardDeck(),
                flashcardIndex = state.flashcardIndex,
                isFlashcardFlipped = state.isFlashcardFlipped,
                flashcardBookmarksOnly = state.flashcardBookmarksOnly,
                bookmarkedCount = state.bookmarkedIds.size,
                onSelectMode = viewModel::selectPracticeMode,
                onAnswerQuiz = viewModel::answerQuiz,
                onNextQuiz = viewModel::nextQuizQuestion,
                onResetQuiz = viewModel::resetQuiz,
                onFlipFlashcard = viewModel::flipFlashcard,
                onNextFlashcard = viewModel::nextFlashcard,
                onPrevFlashcard = viewModel::prevFlashcard,
                onRandomFlashcard = viewModel::randomFlashcard,
                onToggleFlashcardBookmarksOnly = viewModel::toggleFlashcardBookmarksOnly,
                onOpenReactionById = viewModel::openReactionById,
              )
          }
        }
      }
    }

    state.selectedReaction?.let { reaction ->
      ReactionDetailSheet(
        reaction = reaction,
        language = state.language,
        isBookmarked = reaction.id in state.bookmarkedIds,
        onToggleBookmark = { viewModel.toggleBookmark(reaction.id) },
        onDismiss = viewModel::closeReactionDetail,
      )
    }
  }
}
