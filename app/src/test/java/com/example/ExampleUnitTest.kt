package com.example

import com.example.data.model.HazariNagChapter
import com.example.data.repository.ChemistryRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

  private val repository = ChemistryRepository()

  @Test
  fun repository_containsAll63IndexedReactionsWithBilingualFields() {
    val reactions = repository.getAllReactions()
    assertEquals(63, reactions.size)
    assertEquals((1..63).toList(), reactions.map { it.id })
    reactions.forEach { reaction ->
      assertTrue("Missing English name for #${reaction.id}", reaction.nameEn.isNotBlank())
      assertTrue("Missing Bangla name for #${reaction.id}", reaction.nameBn.isNotBlank())
      assertTrue("Missing English concept for #${reaction.id}", reaction.conceptEn.isNotBlank())
      assertTrue("Missing Bangla concept for #${reaction.id}", reaction.conceptBn.isNotBlank())
      assertTrue("Missing equation for #${reaction.id}", reaction.equation.isNotBlank())
      assertTrue("Missing PDF citations for #${reaction.id}", reaction.pdfCitations.isNotEmpty())
    }
  }

  @Test
  fun repository_coversAllFourHazariNagChapters() {
    val reactions = repository.getAllReactions()
    val hazariReactions = reactions.filter { it.hazariNagChapter != null }
    assertTrue("Expected at least 20 Hazari & Nag reactions", hazariReactions.size >= 20)
    HazariNagChapter.entries.forEach { chapter ->
      assertTrue(
        "Missing reactions for Hazari & Nag chapter $chapter",
        hazariReactions.any { it.hazariNagChapter == chapter },
      )
    }
    hazariReactions.forEach { reaction ->
      assertNotNull("Missing Hazari & Nag topic label for #${reaction.id}", reaction.hazariNagTopicLabel)
      assertTrue("Missing hazari-nag tag in parts for #${reaction.id}", "hazari-nag" in reaction.parts)
    }
  }

  @Test
  fun repository_quizQuestionsReferenceValidReactions() {
    val reactionIds = repository.getAllReactions().map { it.id }.toSet()
    val questions = repository.getQuizQuestions()
    assertEquals(18, questions.size)
    questions.forEach { q ->
      assertTrue(q.relatedReactionId in reactionIds)
      assertEquals(4, q.optionsEn.size)
      assertEquals(4, q.optionsBn.size)
      assertTrue(q.correctIndex in 0..3)
    }
  }
}
