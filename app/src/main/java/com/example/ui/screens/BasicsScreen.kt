package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Science
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.AppLanguage
import com.example.data.model.BasicAccent
import com.example.data.model.BasicTopic
import com.example.data.model.FunctionalGroupItem
import com.example.data.model.HybridizationType
import com.example.ui.components.HybridizationExplorer
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
import com.example.ui.theme.MonoFontFamily
import com.example.ui.theme.RoseDark
import com.example.ui.theme.RoseLight
import com.example.ui.theme.VioletDark
import com.example.ui.theme.VioletLight

@Composable
fun BasicsScreen(
  language: AppLanguage,
  topics: List<BasicTopic>,
  functionalGroups: List<FunctionalGroupItem>,
  selectedHybridization: HybridizationType,
  onSelectHybridization: (HybridizationType) -> Unit,
  onNavigateToReactions: () -> Unit,
  onNavigateToPractice: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
    LazyColumn(
      modifier = Modifier.fillMaxSize().widthIn(max = 840.dp),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      // Hero Banner Card
      item {
        HeroSectionCard(
          language = language,
          onNavigateToReactions = onNavigateToReactions,
          onNavigateToPractice = onNavigateToPractice,
        )
      }

      // Section 1 Heading
      item {
        Column(modifier = Modifier.padding(top = 4.dp)) {
          Text(
            text =
              if (language == AppLanguage.EN) "1. Very Basics of Organic Chemistry"
              else "১. জৈব রসায়নের একদম মৌলিক ধারণা",
            fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text =
              if (language == AppLanguage.EN)
                "Everything begins with carbon's 4 bonds, electron flow, and functional groups."
              else "সবকিছুর শুরু কার্বনের চারটি যোজ্যতা, ইলেকট্রনের স্থানান্তর এবং কার্যকরী মূলক থেকে।",
            fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
        }
      }

      // 6 Basic Topic Cards
      items(topics, key = { it.id }) { topic ->
        BasicTopicCard(topic = topic, language = language)
      }

      // Interactive Hybridization Lab
      item {
        HybridizationExplorer(
          language = language,
          selected = selectedHybridization,
          onSelect = onSelectHybridization,
        )
      }

      // Functional Groups Heading
      item {
        Column(modifier = Modifier.padding(top = 6.dp)) {
          Text(
            text =
              if (language == AppLanguage.EN) "2. Essential Functional Groups & Series"
              else "২. গুরুত্বপূর্ণ কার্যকরী মূলক ও সমগোত্রীয় শ্রেণি",
            fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text =
              if (language == AppLanguage.EN)
                "Functional groups determine how an organic molecule reacts."
              else "কার্যকরী মূলকই নির্ধারণ করে একটি জৈব যৌগ কীভাবে বিক্রিয়া করবে।",
            fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
        }
      }

      // Functional Groups List Card
      item {
        Card(
          shape = RoundedCornerShape(18.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        ) {
          Column(modifier = Modifier.padding(vertical = 6.dp)) {
            functionalGroups.forEachIndexed { index, fg ->
              FunctionalGroupRow(item = fg, language = language)
              if (index < functionalGroups.lastIndex) {
                HorizontalDivider(
                  modifier = Modifier.padding(horizontal = 16.dp),
                  color = MaterialTheme.colorScheme.outline,
                )
              }
            }
          }
        }
      }

      item { Spacer(modifier = Modifier.height(12.dp)) }
    }
  }
}

@Composable
private fun HeroSectionCard(
  language: AppLanguage,
  onNavigateToReactions: () -> Unit,
  onNavigateToPractice: () -> Unit,
) {
  Card(
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
  ) {
    Column {
      Box(modifier = Modifier.fillMaxWidth().height(155.dp)) {
        Image(
          painter = painterResource(id = R.drawable.img_chem_hero),
          contentDescription = "Organic chemistry molecular illustration banner",
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop,
        )
        Box(
          modifier =
            Modifier.fillMaxSize()
              .background(
                Brush.verticalGradient(
                  colors = listOf(Color(0x330F172A), Color(0xEE0F172A))
                )
              )
              .padding(16.dp),
          contentAlignment = Alignment.BottomStart,
        ) {
          Column {
            Surface(
              shape = CircleShape,
              color = EmeraldAccent.copy(alpha = 0.9f),
            ) {
              Text(
                text =
                  if (language == AppLanguage.EN) "HSC · Admission · B.Sc."
                  else "এইচএসসি · ভর্তি · স্নাতক",
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                fontFamily =
                  if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                color = Color.White,
              )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text =
                if (language == AppLanguage.EN)
                  "Master Organic Basics & All 56 Named Reactions"
                else "জৈব রসায়নের মৌলিক ভিত্তি ও ৫৬টি নামীয় বিক্রিয়া",
              fontFamily =
                if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
              fontWeight = FontWeight.ExtraBold,
              fontSize = 19.sp,
              lineHeight = 25.sp,
              color = Color.White,
            )
          }
        }
      }

      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text =
            if (language == AppLanguage.EN)
              "Explore carbon bonding, sp³/sp²/sp hybridization, functional groups, and every indexed reaction with English & বাংলা explanations."
            else
              "কার্বনের বন্ধন, সংকরায়ন, কার্যকরী মূলক এবং প্রতিটি নামীয় বিক্রিয়া, পরীক্ষা ও নীতি ইংরেজি এবং বাংলায় সহজভাবে শিখুন।",
          fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Quick 3 Stat Badges
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          StatPill("56", if (language == AppLanguage.EN) "Reactions" else "বিক্রিয়া", Modifier.weight(1f))
          StatPill("sp³·sp²·sp", if (language == AppLanguage.EN) "3D Lab" else "সংকরায়ন", Modifier.weight(1f))
          StatPill("EN + বাংলা", if (language == AppLanguage.EN) "Bilingual" else "দ্বিভাষিক", Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          Button(
            onClick = onNavigateToReactions,
            modifier = Modifier.weight(1f).heightIn(min = 48.dp).testTag("hero_reactions_button"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
          ) {
            Text(
              text = if (language == AppLanguage.EN) "56 Reactions" else "৫৬টি বিক্রিয়া",
              fontFamily =
                if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
              fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
              imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
              contentDescription = null,
              modifier = Modifier.size(16.dp),
            )
          }

          OutlinedButton(
            onClick = onNavigateToPractice,
            modifier = Modifier.weight(1f).heightIn(min = 48.dp).testTag("hero_quiz_button"),
            shape = RoundedCornerShape(12.dp),
          ) {
            Icon(
              imageVector = Icons.Rounded.Science,
              contentDescription = null,
              modifier = Modifier.size(16.dp),
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = if (language == AppLanguage.EN) "Quiz Mode" else "কুইজ ও কার্ড",
              fontFamily =
                if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
              fontWeight = FontWeight.Bold,
            )
          }
        }
      }
    }
  }
}

@Composable
private fun StatPill(value: String, label: String, modifier: Modifier = Modifier) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(12.dp),
    color = MaterialTheme.colorScheme.surfaceVariant,
  ) {
    Column(
      modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        text = value,
        fontFamily = DisplayFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.primary,
      )
      Text(
        text = label,
        fontFamily = BengaliFontFamily,
        fontSize = 11.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }
  }
}

@Composable
private fun BasicTopicCard(topic: BasicTopic, language: AppLanguage) {
  val (badgeBg, badgeFg) =
    when (topic.accent) {
      BasicAccent.BLUE -> CobaltLight to CobaltDark
      BasicAccent.GREEN -> EmeraldLight to EmeraldDark
      BasicAccent.AMBER -> AmberLight to AmberDark
      BasicAccent.VIOLET -> VioletLight to VioletDark
      BasicAccent.ROSE -> RoseLight to RoseDark
      BasicAccent.CYAN -> CyanLight to CyanDark
    }

  Card(
    modifier = Modifier.fillMaxWidth().testTag("basic_topic_card_${topic.id}"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier.size(32.dp).clip(RoundedCornerShape(9.dp)).background(badgeBg),
          contentAlignment = Alignment.Center,
        ) {
          Text(
            text = "${topic.id}",
            fontFamily = DisplayFontFamily,
            fontWeight = FontWeight.ExtraBold,
            color = badgeFg,
            fontSize = 14.sp,
          )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
          text = if (language == AppLanguage.EN) topic.titleEn else topic.titleBn,
          fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
          fontWeight = FontWeight.Bold,
          fontSize = 16.sp,
          color = MaterialTheme.colorScheme.onSurface,
        )
      }
      Spacer(modifier = Modifier.height(10.dp))
      Text(
        text = if (language == AppLanguage.EN) topic.bodyEn else topic.bodyBn,
        fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }
  }
}

@Composable
private fun FunctionalGroupRow(item: FunctionalGroupItem, language: AppLanguage) {
  Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = if (language == AppLanguage.EN) item.classEn else item.classBn,
          fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
          fontWeight = FontWeight.Bold,
          fontSize = 15.sp,
          color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
          text = if (language == AppLanguage.EN) item.classBn else item.classEn,
          fontFamily = if (language == AppLanguage.EN) BengaliFontFamily else DisplayFontFamily,
          fontSize = 12.sp,
          color = EmeraldAccent,
        )
      }
      Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Surface(
          shape = RoundedCornerShape(7.dp),
          color = MaterialTheme.colorScheme.primaryContainer,
        ) {
          Text(
            text = item.groupFormula,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            fontFamily = MonoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
          )
        }
        Surface(
          shape = RoundedCornerShape(7.dp),
          color = MaterialTheme.colorScheme.surfaceVariant,
        ) {
          Text(
            text = item.iupacSuffix,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            fontFamily = MonoFontFamily,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
        }
      }
    }
    Spacer(modifier = Modifier.height(6.dp))
    Text(
      text = "Ex: ${item.example}",
      fontFamily = MonoFontFamily,
      fontSize = 12.sp,
      color = MaterialTheme.colorScheme.onSurface,
    )
    Spacer(modifier = Modifier.height(2.dp))
    Text(
      text = if (language == AppLanguage.EN) item.keyTestEn else item.keyTestBn,
      fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
      fontSize = 12.sp,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
  }
}
