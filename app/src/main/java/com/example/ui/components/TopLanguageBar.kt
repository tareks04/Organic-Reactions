package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.ui.theme.BengaliFontFamily
import com.example.ui.theme.DisplayFontFamily
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.EmeraldDark

@Composable
fun TopLanguageBar(
  language: AppLanguage,
  onToggleLanguage: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Surface(
    modifier = modifier.fillMaxWidth(),
    shadowElevation = 6.dp,
    color = Color.Transparent,
  ) {
    Box(
      modifier =
        Modifier.fillMaxWidth()
          .background(
            Brush.horizontalGradient(
              colors = listOf(Color(0xFF0F172A), Color(0xFF1E293B), Color(0xFF1E3A8A))
            )
          )
          .statusBarsPadding()
          .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f),
        ) {
          Box(
            modifier =
              Modifier.size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                  Brush.linearGradient(colors = listOf(Color(0xFF38BDF8), Color(0xFF34D399)))
                ),
            contentAlignment = Alignment.Center,
          ) {
            Text(
              text = "C₆",
              fontFamily = DisplayFontFamily,
              fontWeight = FontWeight.ExtraBold,
              fontSize = 15.sp,
              color = Color(0xFF0F172A),
            )
          }
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text =
                if (language == AppLanguage.EN) "Tarek's Organic Chem"
                else "তারেকের জৈব রসায়ন",
              fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp,
              color = Color.White,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis,
            )
            Text(
              text =
                if (language == AppLanguage.EN) "HSC · Admission · 56 Reactions"
                else "এইচএসসি · ভর্তি · ৫৬টি বিক্রিয়া",
              fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
              fontSize = 11.sp,
              color = Color(0xFFCBD5E1),
              maxLines = 1,
              overflow = TextOverflow.Ellipsis,
            )
          }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Fixed top Bangla / English Switcher
        Row(
          modifier =
            Modifier.testTag("language_toggle_button")
              .heightIn(min = 48.dp)
              .clip(CircleShape)
              .background(Brush.linearGradient(colors = listOf(EmeraldAccent, EmeraldDark)))
              .border(1.5.dp, Color.White.copy(alpha = 0.35f), CircleShape)
              .clickable(
                role = Role.Button,
                onClickLabel = "Switch between Bangla and English",
                onClick = onToggleLanguage,
              )
              .padding(horizontal = 14.dp, vertical = 8.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Icon(
            imageVector = Icons.Rounded.Translate,
            contentDescription = "Switch language",
            tint = Color.White,
            modifier = Modifier.size(18.dp),
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = if (language == AppLanguage.EN) "বাংলা" else "English",
            fontFamily = if (language == AppLanguage.EN) BengaliFontFamily else DisplayFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.White,
          )
          Spacer(modifier = Modifier.width(6.dp))
          Box(
            modifier =
              Modifier.clip(CircleShape)
                .background(Color.White.copy(alpha = 0.22f))
                .padding(horizontal = 7.dp, vertical = 2.dp)
          ) {
            Text(
              text = if (language == AppLanguage.EN) "EN" else "BN",
              style = MaterialTheme.typography.labelMedium,
              color = Color.White,
              fontWeight = FontWeight.ExtraBold,
            )
          }
        }
      }
    }
  }
}
