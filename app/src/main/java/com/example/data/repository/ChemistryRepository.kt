package com.example.data.repository

import com.example.data.model.BasicTopic
import com.example.data.model.FunctionalGroupItem
import com.example.data.model.NamedReaction
import com.example.data.model.QuizQuestion

class ChemistryRepository {

  fun getBasicTopics(): List<BasicTopic> = BasicsData.basicTopics

  fun getFunctionalGroups(): List<FunctionalGroupItem> = BasicsData.functionalGroups

  fun getAllReactions(): List<NamedReaction> =
    ReactionsDataPart1.reactions +
      ReactionsDataPart2.reactions +
      ReactionsDataPart3.reactions +
      ReactionsDataPart4.reactions

  fun getQuizQuestions(): List<QuizQuestion> = BasicsData.quizQuestions
}
