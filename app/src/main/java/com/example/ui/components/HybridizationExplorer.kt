package com.example.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.HybridizationType
import com.example.ui.theme.BengaliFontFamily
import com.example.ui.theme.DisplayFontFamily
import com.example.ui.theme.EquationSurface
import com.example.ui.theme.MonoFontFamily

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HybridizationExplorer(
  language: AppLanguage,
  selected: HybridizationType,
  onSelect: (HybridizationType) -> Unit,
  modifier: Modifier = Modifier,
) {
  Card(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(
        text =
          if (language == AppLanguage.EN) "Interactive Carbon Hybridization Lab"
          else "ইন্টারঅ্যাক্টিভ কার্বন সংকরায়ন ল্যাব",
        fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface,
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text =
          if (language == AppLanguage.EN)
            "Tap a hybridization state to inspect geometry, bond angles, and σ/π bonds."
          else "জ্যামিতি, বন্ধন কোণ ও σ/π বন্ধন দেখতে যেকোনো সংকরায়নে ট্যাপ করুন।",
        fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )

      Spacer(modifier = Modifier.height(14.dp))

      FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        HybridizationType.entries.forEach { hyb ->
          val active = hyb == selected
          Surface(
            modifier =
              Modifier.testTag("hyb_tab_${hyb.name.lowercase()}")
                .heightIn(min = 48.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable { onSelect(hyb) },
            shape = RoundedCornerShape(12.dp),
            color =
              if (active) MaterialTheme.colorScheme.primary
              else MaterialTheme.colorScheme.surfaceVariant,
            border =
              BorderStroke(
                1.dp,
                if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
              ),
          ) {
            Box(
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
              contentAlignment = Alignment.Center,
            ) {
              Text(
                text = if (language == AppLanguage.EN) hyb.tabEn else hyb.tabBn,
                fontFamily =
                  if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color =
                  if (active) MaterialTheme.colorScheme.onPrimary
                  else MaterialTheme.colorScheme.onSurface,
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      AnimatedContent(targetState = selected, label = "hybridization_content") { hyb ->
        Column {
          // Dark Canvas Stage for Molecular Geometry
          Box(
            modifier =
              Modifier.fillMaxWidth()
                .height(175.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(EquationSurface)
                .padding(12.dp)
          ) {
            HybridizationDiagramCanvas(
              hybridization = hyb,
              modifier = Modifier.fillMaxWidth().height(150.dp),
            )
          }

          Spacer(modifier = Modifier.height(14.dp))

          Text(
            text = if (language == AppLanguage.EN) hyb.titleEn else hyb.titleBn,
            fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = if (language == AppLanguage.EN) hyb.descEn else hyb.descBn,
            fontFamily = if (language == AppLanguage.EN) DisplayFontFamily else BengaliFontFamily,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )

          Spacer(modifier = Modifier.height(12.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            MetricTile(
              label = if (language == AppLanguage.EN) "Geometry" else "জ্যামিতি",
              value = hyb.geometry,
              modifier = Modifier.weight(1f),
            )
            MetricTile(
              label = if (language == AppLanguage.EN) "Bond Angle" else "বন্ধন কোণ",
              value = hyb.bondAngle,
              modifier = Modifier.weight(1f),
            )
          }
          Spacer(modifier = Modifier.height(8.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            MetricTile(
              label = if (language == AppLanguage.EN) "s-Character" else "s-চরিত্র",
              value = hyb.sCharacter,
              modifier = Modifier.weight(1f),
            )
            MetricTile(
              label = if (language == AppLanguage.EN) "Examples" else "উদাহরণ",
              value = hyb.example,
              modifier = Modifier.weight(1f),
            )
          }
        }
      }
    }
  }
}

@Composable
private fun MetricTile(label: String, value: String, modifier: Modifier = Modifier) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(10.dp),
    color = MaterialTheme.colorScheme.surfaceVariant,
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Text(
        text = label,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
      Spacer(modifier = Modifier.height(2.dp))
      Text(
        text = value,
        fontFamily = BengaliFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.onSurface,
      )
    }
  }
}

@Composable
private fun HybridizationDiagramCanvas(
  hybridization: HybridizationType,
  modifier: Modifier = Modifier,
) {
  val textMeasurer = rememberTextMeasurer()
  val atomStyle =
    TextStyle(
      color = Color.White,
      fontSize = 13.sp,
      fontWeight = FontWeight.Bold,
      fontFamily = MonoFontFamily,
    )
  val badgeStyle =
    TextStyle(
      color = Color(0xFFFBBF24),
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold,
      fontFamily = MonoFontFamily,
    )

  Canvas(modifier = modifier) {
    val w = size.width
    val h = size.height
    val cx = w / 2f
    val cy = h / 2f
    val bondColor = Color(0xFF93C5FD)
    val piColor = Color(0xFFFBBF24)
    val cColor = Color(0xFF3B82F6)
    val hColor = Color(0xFF10B981)

    fun drawAtom(pos: Offset, radius: Float, fill: Color, label: String) {
      drawCircle(color = fill, radius = radius, center = pos)
      val measured = textMeasurer.measure(label, style = atomStyle)
      drawText(
        textLayoutResult = measured,
        topLeft = Offset(pos.x - measured.size.width / 2f, pos.y - measured.size.height / 2f),
      )
    }

    when (hybridization) {
      HybridizationType.SP3 -> {
        val cPos = Offset(cx, cy + 4f)
        val top = Offset(cx, cy - 52f)
        val left = Offset(cx - 56f, cy + 36f)
        val right = Offset(cx + 56f, cy + 36f)
        val back = Offset(cx, cy + 56f)

        drawLine(bondColor, cPos, top, strokeWidth = 5f, cap = StrokeCap.Round)
        drawLine(bondColor, cPos, left, strokeWidth = 5f, cap = StrokeCap.Round)
        drawLine(bondColor, cPos, right, strokeWidth = 5f, cap = StrokeCap.Round)
        drawLine(
          bondColor.copy(alpha = 0.7f),
          cPos,
          back,
          strokeWidth = 4f,
          pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f)),
        )

        drawAtom(cPos, 20f, cColor, "C")
        drawAtom(top, 13f, hColor, "H")
        drawAtom(left, 13f, hColor, "H")
        drawAtom(right, 13f, hColor, "H")
        drawAtom(back, 11f, hColor.copy(alpha = 0.85f), "H")

        val angleText = textMeasurer.measure("109.5° · 4 σ", style = badgeStyle)
        drawText(angleText, topLeft = Offset(cx + 38f, cy - 42f))
      }
      HybridizationType.SP2 -> {
        val c1 = Offset(cx - 42f, cy)
        val c2 = Offset(cx + 42f, cy)
        val h1 = Offset(cx - 88f, cy - 38f)
        val h2 = Offset(cx - 88f, cy + 38f)
        val h3 = Offset(cx + 88f, cy - 38f)
        val h4 = Offset(cx + 88f, cy + 38f)

        drawLine(piColor, Offset(c1.x, c1.y - 6f), Offset(c2.x, c2.y - 6f), strokeWidth = 5f)
        drawLine(bondColor, Offset(c1.x, c1.y + 6f), Offset(c2.x, c2.y + 6f), strokeWidth = 5f)
        drawLine(bondColor, c1, h1, strokeWidth = 4f)
        drawLine(bondColor, c1, h2, strokeWidth = 4f)
        drawLine(bondColor, c2, h3, strokeWidth = 4f)
        drawLine(bondColor, c2, h4, strokeWidth = 4f)

        drawAtom(c1, 18f, cColor, "C")
        drawAtom(c2, 18f, cColor, "C")
        drawAtom(h1, 12f, hColor, "H")
        drawAtom(h2, 12f, hColor, "H")
        drawAtom(h3, 12f, hColor, "H")
        drawAtom(h4, 12f, hColor, "H")

        val label = textMeasurer.measure("1σ + 1π · 120°", style = badgeStyle)
        drawText(label, topLeft = Offset(cx - label.size.width / 2f, 8f))
      }
      HybridizationType.SP -> {
        val h1 = Offset(cx - 102f, cy)
        val c1 = Offset(cx - 42f, cy)
        val c2 = Offset(cx + 42f, cy)
        val h2 = Offset(cx + 102f, cy)

        drawLine(bondColor, h1, c1, strokeWidth = 4f)
        drawLine(bondColor, c2, h2, strokeWidth = 4f)
        drawLine(piColor, Offset(c1.x, c1.y - 9f), Offset(c2.x, c2.y - 9f), strokeWidth = 4f)
        drawLine(bondColor, c1, c2, strokeWidth = 4f)
        drawLine(piColor, Offset(c1.x, c1.y + 9f), Offset(c2.x, c2.y + 9f), strokeWidth = 4f)

        drawAtom(c1, 18f, cColor, "C")
        drawAtom(c2, 18f, cColor, "C")
        drawAtom(h1, 13f, hColor, "H")
        drawAtom(h2, 13f, hColor, "H")

        val label = textMeasurer.measure("1σ + 2π · 180°", style = badgeStyle)
        drawText(label, topLeft = Offset(cx - label.size.width / 2f, 12f))
      }
    }
  }
}
