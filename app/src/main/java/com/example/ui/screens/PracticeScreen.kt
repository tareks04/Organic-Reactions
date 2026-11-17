package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Flip
import androidx.compose.material.icons.rounded.Quiz
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material.icons.rounded.Style
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.NamedReaction
import com.example.data.model.QuizQuestion
import com.example.ui.components.CategoryBadge
import com.example.ui.theme.BengaliFontFamily
import com.example.ui.theme.DisplayFontFamily
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EquationSurface
import com.example.ui.theme.EquationText
import com.example.ui.theme.MonoFontFamily
import com.example.ui.theme.RoseAccent
import com.example.ui.theme.RoseDark
import com.example.ui.theme.RoseLight
import com.example.ui.viewmodel.PracticeMode

@Composable
fun PracticeScreen(
  language: AppLanguage,
  mode: PracticeMode,
  quizQuestions: List<QuizQuestion>,
  currentQuizIndex: Int,
  selectedQuizOption: Int?,
  quizScore: Int,
  quizAttempted: Int,
  flashcardDeck: List<NamedReaction>,
  flashcardIndex: Int,
  isFlashcardFlipped: Boolean,
  flashcardBookmarksOnly: Boolean,
  bookmarkedCount: Int,
  onSelectMode: (PracticeMode) -> Unit,
  onAnswerQuiz: (Int) -> Unit,
  onNextQuiz: () -> Unit,
  onResetQuiz: () -> Unit,
  onFlipFlashcard: () -> Unit,
  onNextFlashcard: () -> Unit,
  onPrevFlashcard: () -> Unit,
  onRandomFlashcard: () -> Unit,
  onToggleFlashcardBookmarksOnly: () -> Unit,
  onOpenReactionById: (Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    Column(
      modifier =
        Modifier.fillMaxSize()
          .widthIn(max = 760.dp)
          .verticalScroll(rememberScrollState())
          .padding(16.dp)
    ) {
      Text(
        text =
          if (language == AppLanguage.EN) "HSC / Admission Practice Lab"
          else "এইচএসসি ও ভর্তি অনুশীলন ল্যাব",
        fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground,
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text =
          if (language == AppLanguage.EN)
            "Drill named reactions, reagents, and rules via MCQs or bilingual flashcards."
          else "বহুনির্বাচনি প্রশ্ন ও দ্বিভাষিক ফ্ল্যাশকার্ড দিয়ে বিক্রিয়া ও বিকারক ঝালিয়ে নিন।",
        fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Mode Switcher
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
      ) {
        ModePillButton(
          label = if (language == AppLanguage.EN) "MCQ Quiz" else "MCQ কুইজ",
          icon = Icons.Rounded.Quiz,
          selected = mode == PracticeMode.QUIZ,
          onClick = { onSelectMode(PracticeMode.QUIZ) },
          modifier = Modifier.weight(1f).testTag("practice_mode_quiz"),
        )
        ModePillButton(
          label = if (language == AppLanguage.EN) "Flashcards" else "ফ্ল্যাশকার্ড",
          icon = Icons.Rounded.Style,
          selected = mode == PracticeMode.FLASHCARDS,
          onClick = { onSelectMode(PracticeMode.FLASHCARDS) },
          modifier = Modifier.weight(1f).testTag("practice_mode_flashcards"),
        )
      }

      Spacer(modifier = Modifier.height(16.dp))

      AnimatedContent(targetState = mode, label = "practice_mode") { activeMode ->
        when (activeMode) {
          PracticeMode.QUIZ -> {
            val question = quizQuestions.getOrNull(currentQuizIndex)
            if (question != null) {
              QuizSection(
                language = language,
                question = question,
                currentIndex = currentQuizIndex,
                totalQuestions = quizQuestions.size,
                selectedOption = selectedQuizOption,
                score = quizScore,
                attempted = quizAttempted,
                onSelectOption = onAnswerQuiz,
                onNext = onNextQuiz,
                onReset = onResetQuiz,
                onOpenReaction = { onOpenReactionById(question.relatedReactionId) },
              )
            }
          }
          PracticeMode.FLASHCARDS -> {
            val safeIdx =
              if (flashcardDeck.isEmpty()) 0 else flashcardIndex.coerceIn(0, flashcardDeck.lastIndex)
            val card = flashcardDeck.getOrNull(safeIdx)
            FlashcardSection(
              language = language,
              card = card,
              index = safeIdx,
              total = flashcardDeck.size,
              isFlipped = isFlashcardFlipped,
              bookmarksOnly = flashcardBookmarksOnly,
              bookmarkedCount = bookmarkedCount,
              onFlip = onFlipFlashcard,
              onNext = onNextFlashcard,
              onPrev = onPrevFlashcard,
              onRandom = onRandomFlashcard,
              onToggleBookmarksOnly = onToggleFlashcardBookmarksOnly,
              onOpenDetails = { card?.let { onOpenReactionById(it.id) } },
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))
    }
  }
}

@Composable
private fun ModePillButton(
  label: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  selected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Surface(
    modifier =
      modifier.heightIn(min = 48.dp).clip(RoundedCornerShape(14.dp)).clickable(onClick = onClick),
    shape = RoundedCornerShape(14.dp),
    color =
      if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
    border =
      BorderStroke(
        1.dp,
        if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
      ),
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center,
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint =
          if (selected) MaterialTheme.colorScheme.onPrimary
          else MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.size(18.dp),
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = label,
        fontFamily = BengaliFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color =
          if (selected) MaterialTheme.colorScheme.onPrimary
          else MaterialTheme.colorScheme.onSurface,
      )
    }
  }
}

@Composable
private fun QuizSection(
  language: AppLanguage,
  question: QuizQuestion,
  currentIndex: Int,
  totalQuestions: Int,
  selectedOption: Int?,
  score: Int,
  attempted: Int,
  onSelectOption: (Int) -> Unit,
  onNext: () -> Unit,
  onReset: () -> Unit,
  onOpenReaction: () -> Unit,
) {
  Card(
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(
          text = "Q ${currentIndex + 1} / $totalQuestions",
          style = MaterialTheme.typography.labelLarge,
          color = MaterialTheme.colorScheme.primary,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
          Surface(shape = CircleShape, color = EmeraldLight) {
            Text(
              text =
                if (language == AppLanguage.EN) "Score: $score / $attempted"
                else "স্কোর: $score / $attempted",
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              fontFamily = BengaliFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 12.sp,
              color = EmeraldDark,
            )
          }
          if (attempted > 0) {
            Spacer(modifier = Modifier.width(6.dp))
            TextButton(
              onClick = onReset,
              contentPadding = PaddingValues(horizontal = 8.dp),
              modifier = Modifier.testTag("quiz_reset_button"),
            ) {
              Icon(
                imageVector = Icons.Rounded.Refresh,
                contentDescription = "Reset quiz score",
                modifier = Modifier.size(16.dp),
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))
      LinearProgressIndicator(
        progress = { (currentIndex + 1).toFloat() / totalQuestions.toFloat() },
        modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape),
      )

      Spacer(modifier = Modifier.height(16.dp))

      Text(
        text = if (language == AppLanguage.EN) question.questionEn else question.questionBn,
        fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        lineHeight = 24.sp,
        color = MaterialTheme.colorScheme.onSurface,
      )

      Spacer(modifier = Modifier.height(14.dp))

      val options = if (language == AppLanguage.EN) question.optionsEn else question.optionsBn
      options.forEachIndexed { idx, optText ->
        val isAnswered = selectedOption != null
        val isCorrect = idx == question.correctIndex
        val isSelected = idx == selectedOption

        val (bgColor, borderColor, textColor) =
          when {
            !isAnswered ->
              Triple(
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                MaterialTheme.colorScheme.outline,
                MaterialTheme.colorScheme.onSurface,
              )
            isCorrect -> Triple(EmeraldLight, EmeraldAccent, EmeraldDark)
            isSelected -> Triple(RoseLight, RoseAccent, RoseDark)
            else ->
              Triple(
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
                MaterialTheme.colorScheme.outline,
                MaterialTheme.colorScheme.onSurfaceVariant,
              )
          }

        Surface(
          modifier =
            Modifier.fillMaxWidth()
              .padding(vertical = 5.dp)
              .heightIn(min = 48.dp)
              .testTag("quiz_option_$idx")
              .clip(RoundedCornerShape(12.dp))
              .clickable(enabled = !isAnswered) { onSelectOption(idx) },
          shape = RoundedCornerShape(12.dp),
          color = bgColor,
          border = BorderStroke(1.5.dp, borderColor),
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
          ) {
            Text(
              text = "${('A' + idx)}. $optText",
              fontFamily = BengaliFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 14.sp,
              color = textColor,
              modifier = Modifier.weight(1f),
            )
            if (isAnswered && isCorrect) {
              Icon(
                imageVector = Icons.Rounded.CheckCircle,
                contentDescription = "Correct option",
                tint = EmeraldDark,
                modifier = Modifier.size(20.dp),
              )
            }
          }
        }
      }

      if (selectedOption != null) {
        Spacer(modifier = Modifier.height(12.dp))
        Surface(
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(12.dp),
          color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f),
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Text(
              text =
                if (language == AppLanguage.EN) question.explanationEn
                else question.explanationBn,
              fontFamily =
                if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
              fontSize = 13.sp,
              lineHeight = 19.sp,
              color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.height(6.dp))
            TextButton(
              onClick = onOpenReaction,
              contentPadding = PaddingValues(0.dp),
              modifier = Modifier.testTag("quiz_view_reaction_btn"),
            ) {
              Text(
                text =
                  if (language == AppLanguage.EN) "Open Reaction #${question.relatedReactionId} →"
                  else "বিক্রিয়া #${question.relatedReactionId} বিস্তারিত দেখুন →",
                fontFamily = BengaliFontFamily,
                fontWeight = FontWeight.Bold,
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))
      Button(
        onClick = onNext,
        modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp).testTag("quiz_next_button"),
        shape = RoundedCornerShape(12.dp),
      ) {
        Text(
          text = if (language == AppLanguage.EN) "Next Question" else "পরবর্তী প্রশ্ন",
          fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
          fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(6.dp))
        Icon(
          imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
          contentDescription = null,
          modifier = Modifier.size(18.dp),
        )
      }
    }
  }
}

@Composable
private fun FlashcardSection(
  language: AppLanguage,
  card: NamedReaction?,
  index: Int,
  total: Int,
  isFlipped: Boolean,
  bookmarksOnly: Boolean,
  bookmarkedCount: Int,
  onFlip: () -> Unit,
  onNext: () -> Unit,
  onPrev: () -> Unit,
  onRandom: () -> Unit,
  onToggleBookmarksOnly: () -> Unit,
  onOpenDetails: () -> Unit,
) {
  Column {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Surface(
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primaryContainer,
      ) {
        Text(
          text = "${if (total == 0) 0 else index + 1} / $total",
          modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
          style = MaterialTheme.typography.labelLarge,
          color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
      }

      FilterChip(
        selected = bookmarksOnly,
        onClick = onToggleBookmarksOnly,
        enabled = bookmarkedCount > 0 || bookmarksOnly,
        modifier = Modifier.heightIn(min = 48.dp).testTag("flashcard_bookmarks_chip"),
        leadingIcon = {
          Icon(
            imageVector =
              if (bookmarksOnly) Icons.Rounded.Bookmark else Icons.Rounded.BookmarkBorder,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
          )
        },
        label = {
          Text(
            text =
              if (language == AppLanguage.EN) "Saved Deck ($bookmarkedCount)"
              else "সংরক্ষিত ($bookmarkedCount)",
            fontFamily = BengaliFontFamily,
          )
        },
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    if (card == null) {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
      ) {
        Text(
          text =
            if (language == AppLanguage.EN) "No saved cards yet."
            else "এখনো কোনো বিক্রিয়া সংরক্ষণ করা হয়নি।",
          modifier = Modifier.padding(24.dp),
          fontFamily = BengaliFontFamily,
        )
      }
      return
    }

    Card(
      modifier =
        Modifier.fillMaxWidth()
          .testTag("flashcard_surface")
          .clip(RoundedCornerShape(20.dp))
          .clickable(onClick = onFlip),
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
      border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
      elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
    ) {
      Column(modifier = Modifier.padding(20.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            text = "#${card.id}",
            fontFamily = MonoFontFamily,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
          )
          CategoryBadge(category = card.category, language = language)
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
          text = card.nameEn,
          fontFamily = DisplayFontFamily,
          fontWeight = FontWeight.ExtraBold,
          fontSize = 20.sp,
          lineHeight = 26.sp,
          color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = card.nameBn,
          fontFamily = BengaliFontFamily,
          fontWeight = FontWeight.Bold,
          fontSize = 18.sp,
          lineHeight = 24.sp,
          color = EmeraldAccent,
        )

        Spacer(modifier = Modifier.height(14.dp))

        if (!isFlipped) {
          Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
          ) {
            Row(
              modifier = Modifier.padding(16.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center,
            ) {
              Icon(
                imageVector = Icons.Rounded.Flip,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text =
                  if (language == AppLanguage.EN) "Tap card to reveal equation & answer"
                  else "সমীকরণ ও ব্যাখ্যা দেখতে কার্ডে ট্যাপ করুন",
                fontFamily = BengaliFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
              )
            }
          }
        } else {
          HorizontalDivider(color = MaterialTheme.colorScheme.outline)
          Spacer(modifier = Modifier.height(12.dp))

          Box(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(EquationSurface)
                .padding(12.dp)
          ) {
            Text(
              text = card.equation,
              fontFamily = MonoFontFamily,
              fontSize = 12.sp,
              lineHeight = 18.sp,
              color = EquationText,
            )
          }

          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = card.conceptEn,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = card.conceptBn,
            fontFamily = BengaliFontFamily,
            fontSize = 14.sp,
            lineHeight = 21.sp,
            color = EmeraldDark,
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = "🧪 ${card.reagents}",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
          )
          Spacer(modifier = Modifier.height(4.dp))
          TextButton(
            onClick = onOpenDetails,
            contentPadding = PaddingValues(0.dp),
          ) {
            Text(
              text = if (language == AppLanguage.EN) "Open Full Details →" else "বিস্তারিত দেখুন →",
              fontFamily = BengaliFontFamily,
              fontWeight = FontWeight.Bold,
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      OutlinedButton(
        onClick = onPrev,
        modifier = Modifier.weight(1f).heightIn(min = 48.dp).testTag("flashcard_prev_btn"),
        shape = RoundedCornerShape(12.dp),
      ) {
        Icon(
          imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
          contentDescription = "Previous flashcard",
        )
      }
      Button(
        onClick = onFlip,
        modifier = Modifier.weight(2f).heightIn(min = 48.dp).testTag("flashcard_flip_btn"),
        shape = RoundedCornerShape(12.dp),
      ) {
        Icon(
          imageVector = Icons.Rounded.Flip,
          contentDescription = null,
          modifier = Modifier.size(18.dp),
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = if (language == AppLanguage.EN) "Flip" else "উল্টান",
          fontFamily = BengaliFontFamily,
          fontWeight = FontWeight.Bold,
        )
      }
      OutlinedButton(
        onClick = onRandom,
        modifier = Modifier.weight(1f).heightIn(min = 48.dp).testTag("flashcard_random_btn"),
        shape = RoundedCornerShape(12.dp),
      ) {
        Icon(imageVector = Icons.Rounded.Shuffle, contentDescription = "Random flashcard")
      }
      OutlinedButton(
        onClick = onNext,
        modifier = Modifier.weight(1f).heightIn(min = 48.dp).testTag("flashcard_next_btn"),
        shape = RoundedCornerShape(12.dp),
      ) {
        Icon(
          imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
          contentDescription = "Next flashcard",
        )
      }
    }
  }
}
