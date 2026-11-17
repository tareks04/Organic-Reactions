package com.example.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.example.ui.theme.AmberDark
import com.example.ui.theme.AmberLight
import com.example.ui.theme.BengaliFontFamily
import com.example.ui.theme.DisplayFontFamily
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EquationSurface
import com.example.ui.theme.EquationText

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ReactionDetailSheet(
  reaction: NamedReaction,
  language: AppLanguage,
  isBookmarked: Boolean,
  onToggleBookmark: () -> Unit,
  onDismiss: () -> Unit,
) {
  BackHandler(onBack = onDismiss)
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = MaterialTheme.colorScheme.surface,
  ) {
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .verticalScroll(rememberScrollState())
          .padding(horizontal = 20.dp)
          .padding(bottom = 32.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
          ) {
            Text(
              text = "#${reaction.id}",
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              style = MaterialTheme.typography.labelLarge,
              color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          CategoryBadge(category = reaction.category, language = language)
        }
        Row {
          IconButton(
            onClick = onToggleBookmark,
            modifier = Modifier.testTag("detail_bookmark_button"),
          ) {
            Icon(
              imageVector =
                if (isBookmarked) Icons.Rounded.Bookmark else Icons.Rounded.BookmarkBorder,
              contentDescription = "Bookmark reaction",
              tint =
                if (isBookmarked) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }
          IconButton(
            onClick = onDismiss,
            modifier = Modifier.testTag("detail_close_button"),
          ) {
            Icon(
              imageVector = Icons.Rounded.Close,
              contentDescription = "Close details",
              tint = MaterialTheme.colorScheme.onSurface,
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Bilingual Titles
      Text(
        text = reaction.nameEn,
        fontFamily = DisplayFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        color = MaterialTheme.colorScheme.onSurface,
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = reaction.nameBn,
        fontFamily = BengaliFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 25.sp,
        color = EmeraldAccent,
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Equation Block
      Box(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(EquationSurface)
            .padding(14.dp)
      ) {
        Text(
          text = reaction.equation,
          fontFamily = com.example.ui.theme.MonoFontFamily,
          fontSize = 13.sp,
          lineHeight = 20.sp,
          color = EquationText,
        )
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Bilingual Concept Section
      DetailSectionCard(
        title =
          if (language == AppLanguage.EN) "Core Concept (EN + বাংলা)"
          else "মূল ধারণা (বাংলা + EN)",
      ) {
        Text(
          text = reaction.conceptEn,
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurface,
        )
        HorizontalDivider(
          modifier = Modifier.padding(vertical = 8.dp),
          color = MaterialTheme.colorScheme.outline,
        )
        Text(
          text = reaction.conceptBn,
          fontFamily = BengaliFontFamily,
          fontSize = 14.sp,
          lineHeight = 21.sp,
          color = EmeraldDark,
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      DetailSectionCard(
        title =
          if (language == AppLanguage.EN) "Reagents & Conditions" else "বিকারক ও শর্তাবলি",
      ) {
        Text(
          text = "• Reagents: ${reaction.reagents}",
          style = MaterialTheme.typography.bodyMedium,
          fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "• Conditions: ${reaction.conditions}",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      DetailSectionCard(
        title = if (language == AppLanguage.EN) "Mechanism & Example" else "বিক্রিয়া কৌশল ও উদাহরণ",
      ) {
        Text(
          text = reaction.mechanismEn,
          style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = reaction.mechanismBn,
          fontFamily = BengaliFontFamily,
          fontSize = 13.sp,
          lineHeight = 20.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
        ) {
          Text(
            text = "Ex: ${reaction.example}",
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            fontFamily = com.example.ui.theme.MonoFontFamily,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      DetailSectionCard(
        title =
          if (language == AppLanguage.EN) "Applications & Limitations" else "ব্যবহার ও সীমাবদ্ধতা",
      ) {
        Text(
          text =
            if (language == AppLanguage.EN) "Use: ${reaction.applicationEn}"
            else "ব্যবহার: ${reaction.applicationBn}",
          fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
          style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text =
            if (language == AppLanguage.EN) "Limit: ${reaction.limitationEn}"
            else "সীমাবদ্ধতা: ${reaction.limitationBn}",
          fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Exam Pitfall Alert
      Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = AmberLight,
        border = BorderStroke(1.dp, Color(0xFFFDE68A)),
      ) {
        Row(
          modifier = Modifier.padding(12.dp),
          verticalAlignment = Alignment.Top,
        ) {
          Icon(
            imageVector = Icons.Rounded.WarningAmber,
            contentDescription = "Exam tip",
            tint = AmberDark,
            modifier = Modifier.size(20.dp),
          )
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text =
                if (language == AppLanguage.EN) "Exam Tip / Pitfall" else "পরীক্ষার টিপস ও সতর্কতা",
              fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 13.sp,
              color = AmberDark,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = "${reaction.examNoteEn}\n${reaction.examNoteBn}",
              fontFamily = BengaliFontFamily,
              fontSize = 13.sp,
              lineHeight = 19.sp,
              color = AmberDark,
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
      ) {
        reaction.pdfCitations.forEach { cite ->
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
          ) {
            Text(
              text = "PDF: $cite",
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
              style = MaterialTheme.typography.labelMedium,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }
        }
      }
    }
  }
}

@Composable
private fun DetailSectionCard(title: String, content: @Composable () -> Unit) {
  Surface(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(12.dp),
    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Text(
        text = title,
        fontFamily = BengaliFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.primary,
      )
      Spacer(modifier = Modifier.height(6.dp))
      content()
    }
  }
}
