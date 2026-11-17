package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.NamedReaction
import com.example.data.model.ReactionCategory
import com.example.ui.theme.AmberDark
import com.example.ui.theme.AmberLight
import com.example.ui.theme.BengaliFontFamily
import com.example.ui.theme.CobaltDark
import com.example.ui.theme.CobaltLight
import com.example.ui.theme.CyanDark
import com.example.ui.theme.CyanLight
import com.example.ui.theme.DisplayFontFamily
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EquationSurface
import com.example.ui.theme.EquationText
import com.example.ui.theme.MonoFontFamily
import com.example.ui.theme.VioletDark
import com.example.ui.theme.VioletLight

@Composable
fun CategoryBadge(
  category: ReactionCategory,
  language: AppLanguage,
  modifier: Modifier = Modifier,
) {
  val (bg, fg) =
    when (category) {
      ReactionCategory.NAME_REACTION -> CobaltLight to CobaltDark
      ReactionCategory.TEST -> EmeraldLight to EmeraldDark
      ReactionCategory.RULE -> VioletLight to VioletDark
      ReactionCategory.PREPARATION -> AmberLight to AmberDark
      ReactionCategory.REACTION_TYPE -> CyanLight to CyanDark
      ReactionCategory.ALL -> CobaltLight to CobaltDark
    }
  Surface(
    modifier = modifier,
    shape = CircleShape,
    color = bg,
  ) {
    Text(
      text = if (language == AppLanguage.EN) category.labelEn else category.labelBn,
      modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
      fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
      fontWeight = FontWeight.Bold,
      fontSize = 11.sp,
      color = fg,
    )
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReactionCard(
  reaction: NamedReaction,
  language: AppLanguage,
  isBookmarked: Boolean,
  onToggleBookmark: () -> Unit,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Card(
    modifier =
      modifier
        .fillMaxWidth()
        .testTag("reaction_card_${reaction.id}")
        .clip(RoundedCornerShape(16.dp))
        .clickable(onClick = onClick),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      // Top row: #ID + Category + Bookmark
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Surface(
            shape = RoundedCornerShape(7.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
          ) {
            Text(
              text = "#${reaction.id}",
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
              fontFamily = MonoFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurface,
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          CategoryBadge(category = reaction.category, language = language)
        }

        IconButton(
          onClick = onToggleBookmark,
          modifier = Modifier.testTag("bookmark_btn_${reaction.id}"),
        ) {
          Icon(
            imageVector =
              if (isBookmarked) Icons.Rounded.Bookmark else Icons.Rounded.BookmarkBorder,
            contentDescription = if (isBookmarked) "Remove bookmark" else "Bookmark reaction",
            tint =
              if (isBookmarked) MaterialTheme.colorScheme.primary
              else MaterialTheme.colorScheme.onSurfaceVariant,
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      // Bilingual Titles (Always shows both English & বাংলা)
      Text(
        text = reaction.nameEn,
        fontFamily = DisplayFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        color = MaterialTheme.colorScheme.onSurface,
      )
      Spacer(modifier = Modifier.height(2.dp))
      Text(
        text = reaction.nameBn,
        fontFamily = BengaliFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 21.sp,
        color = EmeraldAccent,
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Equation box
      Box(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(EquationSurface)
            .padding(horizontal = 12.dp, vertical = 9.dp)
      ) {
        Text(
          text = reaction.equation,
          fontFamily = MonoFontFamily,
          fontSize = 12.sp,
          lineHeight = 18.sp,
          color = EquationText,
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Bilingual Concept Box
      Row(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f))
      ) {
        Box(
          modifier =
            Modifier.width(3.dp)
              .height(105.dp)
              .background(MaterialTheme.colorScheme.primary)
        )
        Column(modifier = Modifier.padding(10.dp).weight(1f)) {
          Text(
            text = reaction.conceptEn,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
          )
          HorizontalDivider(
            modifier = Modifier.padding(vertical = 6.dp),
            color = MaterialTheme.colorScheme.outline,
          )
          Text(
            text = reaction.conceptBn,
            fontFamily = BengaliFontFamily,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            color = EmeraldDark,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
      ) {
        MetaChip(text = "🧪 ${reaction.reagents}")
      }

      Spacer(modifier = Modifier.height(10.dp))
      HorizontalDivider(color = MaterialTheme.colorScheme.outline)
      Spacer(modifier = Modifier.height(8.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(
          text = reaction.pdfCitations.joinToString(" · "),
          style = MaterialTheme.typography.labelMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          modifier = Modifier.weight(1f),
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = if (language == AppLanguage.EN) "Full Details" else "বিস্তারিত",
            fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.primary,
          )
          Spacer(modifier = Modifier.width(4.dp))
          Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
            contentDescription = "Open reaction details",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp),
          )
        }
      }
    }
  }
}

@Composable
private fun MetaChip(text: String) {
  Surface(
    shape = RoundedCornerShape(7.dp),
    color = MaterialTheme.colorScheme.surfaceVariant,
  ) {
    Text(
      text = text,
      modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
      style = MaterialTheme.typography.labelMedium,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      maxLines = 2,
      overflow = TextOverflow.Ellipsis,
    )
  }
}
