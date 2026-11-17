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
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
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
import com.example.data.model.NamedReaction
import com.example.data.model.ReactionCategory
import com.example.ui.components.ReactionCard
import com.example.ui.theme.AmberDark
import com.example.ui.theme.AmberLight
import com.example.ui.theme.BengaliFontFamily
import com.example.ui.theme.DisplayFontFamily

private val pdfPartOptions =
  listOf(
    "all" to "All Parts (1–8)",
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
  showBookmarksOnly: Boolean,
  bookmarkedIds: Set<Int>,
  onSearchChange: (String) -> Unit,
  onSelectCategory: (ReactionCategory) -> Unit,
  onSelectPartFilter: (String) -> Unit,
  onToggleBookmarksOnly: () -> Unit,
  onToggleBookmark: (Int) -> Unit,
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
              if (language == AppLanguage.EN) "56 Named Reactions & Tests"
              else "৫৬টি নামীয় বিক্রিয়া ও পরীক্ষা",
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
                  "Search Aldol, অ্যালডল, Zn-Hg, 2,4-DNP..."
                else "খুঁজুন: অ্যালডল, Aldol, Zn-Hg, 2,4-DNP...",
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

        // PDF Part Filter + Bookmarks Toggle
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Box(modifier = Modifier.weight(1f)) {
            val partLabel =
              pdfPartOptions.find { it.first == selectedPartFilter }?.second ?: "All Parts (1–8)"
            Surface(
              modifier =
                Modifier.fillMaxWidth()
                  .heightIn(min = 48.dp)
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
                horizontalArrangement = Arrangement.SpaceBetween,
              ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Icon(
                    imageVector = Icons.Rounded.FilterList,
                    contentDescription = "Filter by PDF part",
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
            onClick = {
              onSearchChange("")
              onSelectCategory(ReactionCategory.ALL)
              onSelectPartFilter("all")
              if (showBookmarksOnly) onToggleBookmarksOnly()
            },
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
                    "Source Audit Note: All 56 entries are consolidated from Organic Chemistry page part 1.pdf through part 8.pdf. No indexed named-reaction entries were recorded in part 9.pdf or part 10.pdf."
                  else
                    "উৎস ও অডিট নোট: এখানে প্রদত্ত ৫৬টি এন্ট্রি Organic Chemistry page part 1.pdf থেকে part 8.pdf-এর সূচি অনুযায়ী সংকলিত। part 9.pdf ও part 10.pdf-এ সূচিবদ্ধ কোনো নামীয় বিক্রিয়া নেই।",
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
