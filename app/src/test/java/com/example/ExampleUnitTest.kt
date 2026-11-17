package com.example

import com.example.data.repository.ChemistryRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

  private val repository = ChemistryRepository()

  @Test
  fun repository_containsAll56IndexedReactionsWithBilingualFields() {
    val reactions = repository.getAllReactions()
    assertEquals(56, reactions.size)
    assertEquals((1..56).toList(), reactions.map { it.id })
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
  fun repository_quizQuestionsReferenceValidReactions() {
    val reactionIds = repository.getAllReactions().map { it.id }.toSet()
    val questions = repository.getQuizQuestions()
    assertTrue(questions.size >= 10)
    questions.forEach { q ->
      assertTrue(q.relatedReactionId in reactionIds)
      assertEquals(4, q.optionsEn.size)
      assertEquals(4, q.optionsBn.size)
      assertTrue(q.correctIndex in 0..3)
    }
  }
}
