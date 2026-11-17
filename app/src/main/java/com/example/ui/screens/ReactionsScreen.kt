package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoStories
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.HazariNagChapter
import com.example.data.model.NamedReaction
import com.example.data.model.ReactionCategory
import com.example.ui.components.ReactionCard
import com.example.ui.theme.AmberDark
import com.example.ui.theme.AmberLight
import com.example.ui.theme.BengaliFontFamily
import com.example.ui.theme.DisplayFontFamily
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight

private val pdfPartOptions =
  listOf(
    "all" to "All Sources (63)",
    "hazari-nag" to "Hazari & Nag (HSC Ch. 2)",
    "part 1" to "Part 1 (Wöhler)",
    "part 2" to "Part 2 (Alkanes/Wurtz-Fittig)",
    "part 3" to "Part 3 (Aldol/FC/Markovnikov)",
    "part 4" to "Part 4 (Clemmensen/Etard/Wurtz)",
    "part 5" to "Part 5 (Haloform/Lucas/Grignard)",
    "part 6" to "Part 6 (Azo/Carbylamine/Tollens)",
    "part 7" to "Part 7 (Cumene/Ester/Lassaigne)",
    "part 8" to "Part 8 (Beckmann/Polymers)",
  )

@Composable
fun ReactionsScreen(
  language: AppLanguage,
  reactions: List<NamedReaction>,
  totalCount: Int,
  searchQuery: String,
  selectedCategory: ReactionCategory,
  selectedPartFilter: String,
  hazariNagOnly: Boolean,
  selectedHazariNagChapter: HazariNagChapter?,
  showBookmarksOnly: Boolean,
  bookmarkedIds: Set<Int>,
  onSearchChange: (String) -> Unit,
  onSelectCategory: (ReactionCategory) -> Unit,
  onSelectPartFilter: (String) -> Unit,
  onToggleHazariNagOnly: () -> Unit,
  onSelectHazariNagChapter: (HazariNagChapter?) -> Unit,
  onToggleBookmarksOnly: () -> Unit,
  onToggleBookmark: (Int) -> Unit,
  onResetAllFilters: () -> Unit,
  onOpenReaction: (NamedReaction) -> Unit,
  modifier: Modifier = Modifier,
) {
  var partDropdownOpen by remember { mutableStateOf(false) }

  Column(modifier = modifier.fillMaxSize()) {
    // Search & Filters Header
    Surface(
      color = MaterialTheme.colorScheme.surface,
      shadowElevation = 2.dp,
    ) {
      Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            text =
              if (language == AppLanguage.EN) "63 Named Reactions & Tests"
              else "৬৩টি নামীয় বিক্রিয়া ও পরীক্ষা",
            fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
          )
          Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
          ) {
            Text(
              text = "${reactions.size} / $totalCount",
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              style = MaterialTheme.typography.labelLarge,
              color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
          value = searchQuery,
          onValueChange = onSearchChange,
          modifier = Modifier.fillMaxWidth().testTag("reaction_search_input"),
          singleLine = true,
          shape = RoundedCornerShape(12.dp),
          leadingIcon = {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search reactions")
          },
          trailingIcon = {
            if (searchQuery.isNotEmpty()) {
              IconButton(
                onClick = { onSearchChange("") },
                modifier = Modifier.testTag("clear_search_button"),
              ) {
                Icon(imageVector = Icons.Rounded.Close, contentDescription = "Clear search")
              }
            }
          },
          placeholder = {
            Text(
              text =
                if (language == AppLanguage.EN)
                  "Search Aldol, অ্যালডল, Reimer-Tiemann, Zn-Hg..."
                else "খুঁজুন: অ্যালডল, রাইমার-টাইম্যান, Zn-Hg...",
              fontFamily = BengaliFontFamily,
              fontSize = 14.sp,
            )
          },
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Category Filter Row
        Row(
          modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          ReactionCategory.entries.forEach { cat ->
            FilterChip(
              selected = selectedCategory == cat,
              onClick = { onSelectCategory(cat) },
              modifier = Modifier.testTag("cat_chip_${cat.name.lowercase()}"),
              label = {
                Text(
                  text = if (language == AppLanguage.EN) cat.labelEn else cat.labelBn,
                  fontFamily =
                    if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
                  fontWeight = FontWeight.SemiBold,
                )
              },
            )
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Source Dropdown + Hazari & Nag Top 23 Toggle + Bookmarks Toggle
        Row(
          modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Box {
            val partLabel =
              pdfPartOptions.find { it.first == selectedPartFilter }?.second ?: "All Sources (63)"
            Surface(
              modifier =
                Modifier.heightIn(min = 48.dp)
                  .testTag("pdf_part_dropdown_button")
                  .clip(RoundedCornerShape(10.dp))
                  .clickable { partDropdownOpen = true },
              shape = RoundedCornerShape(10.dp),
              color = MaterialTheme.colorScheme.surfaceVariant,
              border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
              ) {
                Icon(
                  imageVector = Icons.Rounded.FilterList,
                  contentDescription = "Filter by source",
                  modifier = Modifier.size(18.dp),
                  tint = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = partLabel,
                  style = MaterialTheme.typography.labelLarge,
                  color = MaterialTheme.colorScheme.onSurface,
                )
              }
            }

            DropdownMenu(
              expanded = partDropdownOpen,
              onDismissRequest = { partDropdownOpen = false },
            ) {
              pdfPartOptions.forEach { (key, label) ->
                DropdownMenuItem(
                  text = { Text(label) },
                  onClick = {
                    onSelectPartFilter(key)
                    partDropdownOpen = false
                  },
                )
              }
            }
          }

          FilterChip(
            selected = hazariNagOnly,
            onClick = onToggleHazariNagOnly,
            modifier = Modifier.heightIn(min = 48.dp).testTag("hazari_nag_chip"),
            colors =
              FilterChipDefaults.filterChipColors(
                selectedContainerColor = EmeraldLight,
                selectedLabelColor = EmeraldDark,
                selectedLeadingIconColor = EmeraldDark,
              ),
            leadingIcon = {
              Icon(
                imageVector = Icons.Rounded.AutoStories,
                contentDescription = "Hazari & Nag HSC Ch. 2 Top 23",
                modifier = Modifier.size(18.dp),
              )
            },
            label = {
              Text(
                text =
                  if (language == AppLanguage.EN) "Hazari & Nag (Ch.2)"
                  else "হাজারী ও নাগ (Ch.2)",
                fontFamily =
                  if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
                fontWeight = FontWeight.SemiBold,
              )
            },
          )

          FilterChip(
            selected = showBookmarksOnly,
            onClick = onToggleBookmarksOnly,
            modifier = Modifier.heightIn(min = 48.dp).testTag("bookmarks_only_chip"),
            leadingIcon = {
              Icon(
                imageVector =
                  if (showBookmarksOnly) Icons.Rounded.Bookmark else Icons.Rounded.BookmarkBorder,
                contentDescription = "Saved bookmarks",
                modifier = Modifier.size(18.dp),
              )
            },
            label = {
              Text(
                text =
                  if (language == AppLanguage.EN) "Saved (${bookmarkedIds.size})"
                  else "সংরক্ষিত (${bookmarkedIds.size})",
                fontFamily =
                  if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
              )
            },
          )
        }

        // Hazari & Nag Chapter Sub-Filter Row
        if (hazariNagOnly) {
          Spacer(modifier = Modifier.height(6.dp))
          Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
          ) {
            FilterChip(
              selected = selectedHazariNagChapter == null,
              onClick = { onSelectHazariNagChapter(null) },
              modifier = Modifier.testTag("hn_chapter_all"),
              label = {
                Text(
                  text = if (language == AppLanguage.EN) "All Ch. 2" else "সকল Ch. 2",
                  fontFamily =
                    if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
                  fontWeight = FontWeight.SemiBold,
                )
              },
            )
            HazariNagChapter.entries.forEach { chapter ->
              FilterChip(
                selected = selectedHazariNagChapter == chapter,
                onClick = { onSelectHazariNagChapter(chapter) },
                modifier = Modifier.testTag("hn_chapter_${chapter.name.lowercase()}"),
                label = {
                  Text(
                    text = if (language == AppLanguage.EN) chapter.titleEn else chapter.titleBn,
                    fontFamily =
                      if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
                    fontWeight = FontWeight.Medium,
                  )
                },
              )
            }
          }
        }
      }
    }

    // Reactions Adaptive Grid
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
      val columns = if (maxWidth >= 700.dp) 2 else 1

      if (reactions.isEmpty()) {
        Column(
          modifier = Modifier.fillMaxSize().padding(32.dp),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
        ) {
          Icon(
            imageVector = Icons.Rounded.SearchOff,
            contentDescription = null,
            modifier = Modifier.size(56.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
          )
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text =
              if (language == AppLanguage.EN) "No matching reactions found"
              else "কোনো মিল পাওয়া যায়নি",
            fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text =
              if (language == AppLanguage.EN) "Try clearing your search or category filter."
              else "সার্চ বা ফিল্টার পরিষ্কার করে আবার চেষ্টা করুন।",
            fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
          )
          Spacer(modifier = Modifier.height(14.dp))
          Button(
            onClick = onResetAllFilters,
            modifier = Modifier.heightIn(min = 48.dp).testTag("reset_filters_button"),
          ) {
            Text(
              text = if (language == AppLanguage.EN) "Reset Filters" else "ফিল্টার রিসেট করুন",
              fontFamily =
                if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
            )
          }
        }
      } else {
        LazyVerticalGrid(
          columns = GridCells.Fixed(columns),
          modifier = Modifier.fillMaxSize(),
          contentPadding = PaddingValues(16.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp),
          horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
          // Hazari & Nag Study Tips Banner when Ch. 2 filter is active
          if (hazariNagOnly) {
            item(span = { GridItemSpan(columns) }) {
              Surface(
                modifier = Modifier.fillMaxWidth().testTag("hazari_nag_tips_card"),
                shape = RoundedCornerShape(14.dp),
                color = EmeraldLight,
                border = BorderStroke(1.dp, EmeraldAccent.copy(alpha = 0.4f)),
              ) {
                Column(modifier = Modifier.padding(14.dp)) {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                      imageVector = Icons.Rounded.Lightbulb,
                      contentDescription = "Study tips",
                      tint = EmeraldDark,
                      modifier = Modifier.size(18.dp),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                      text =
                        if (language == AppLanguage.EN)
                          "HSC Study Tips (Hazari & Nag · Ch. 2)"
                        else "পড়াশোনার বিশেষ পরামর্শ (হাজারী ও নাগ · অধ্যায় ২)",
                      fontFamily =
                        if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
                      fontWeight = FontWeight.Bold,
                      fontSize = 13.sp,
                      color = EmeraldDark,
                    )
                  }
                  Spacer(modifier = Modifier.height(6.dp))
                  Text(
                    text =
                      if (language == AppLanguage.EN)
                        "1. Memorize exact reagents & conditions (e.g., Zn-Hg/HCl for Clemmensen vs NH₂-NH₂/KOH for Wolff-Kishner, Dry Ether for Wurtz/Fittig).\n2. Master organic conversions & identification tests: Aldol vs Cannizzaro (α-H rule), Carbylamine (1° amine), and Haloform/Iodoform."
                      else
                        "১. প্রভাবক ও বিক্রিয়ার শর্ত (যেমন: শুষ্ক ইথার, Zn-Hg ও গাঢ় HCl, অথবা NH₂-NH₂/KOH) খাতায় আলাদা করে মুখস্থ রাখো।\n২. রূপান্তর (Conversion) ও পার্থক্যকরণ পরীক্ষা—বিশেষত অ্যালডল বনাম ক্যানিজারো (α-H নিয়ম), কার্বিল-অ্যামিন ও আয়োডোফর্ম বিক্রিয়া—সৃজনশীল ও ভর্তির জন্য সবচেয়ে বেশি গুরুত্বপূর্ণ।",
                    fontFamily =
                      if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = EmeraldDark,
                  )
                }
              }
            }
          }

          items(reactions, key = { it.id }) { reaction ->
            ReactionCard(
              reaction = reaction,
              language = language,
              isBookmarked = reaction.id in bookmarkedIds,
              onToggleBookmark = { onToggleBookmark(reaction.id) },
              onClick = { onOpenReaction(reaction) },
            )
          }

          // Source Audit Footer Note
          item(span = { GridItemSpan(columns) }) {
            Surface(
              modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
              shape = RoundedCornerShape(14.dp),
              color = AmberLight,
              border = BorderStroke(1.dp, Color(0xFFFDE68A)),
            ) {
              Text(
                text =
                  if (language == AppLanguage.EN)
                    "Source Audit Note: All 63 entries combine the 56 reactions from Organic Chemistry page part 1.pdf–part 8.pdf (part 9–10 contain no indexed named reactions) with all 23 essential HSC Chemistry 2nd Paper Chapter 2 reactions from Hazari & Nag (হাজারী ও নাগ)."
                  else
                    "উৎস ও অডিট নোট: এখানে প্রদত্ত ৬৩টি এন্ট্রি Organic Chemistry page part 1.pdf থেকে part 8.pdf-এর ৫৬টি সূচিবদ্ধ বিক্রিয়ার (part 9–10-এ নামীয় বিক্রিয়া নেই) সাথে হাজারী ও নাগ (Hazari & Nag) স্যারের এইচএসসি রসায়ন ২য় পত্র, ২য় অধ্যায়ের ২৩টি গুরুত্বপূর্ণ নামধারী বিক্রিয়ার সমন্বয়ে সংকলিত।",
                modifier = Modifier.padding(14.dp),
                fontFamily =
                  if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = AmberDark,
              )
            }
          }
        }
      }
    }
  }
}
