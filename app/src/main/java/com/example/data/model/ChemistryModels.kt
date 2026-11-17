package com.example.data.model

enum class AppLanguage {
  EN,
  BN,
}

enum class MainTab {
  BASICS,
  REACTIONS,
  PRACTICE,
}

enum class ReactionCategory(val labelEn: String, val labelBn: String) {
  ALL("All (56)", "সকল (৫৬)"),
  NAME_REACTION("Name Reactions", "নামীয় বিক্রিয়া"),
  TEST("Chemical Tests", "শনাক্তকারী পরীক্ষা"),
  RULE("Rules & Concepts", "নীতি ও ধারণা"),
  PREPARATION("Preparations", "প্রস্তুতি পদ্ধতি"),
  REACTION_TYPE("Reaction Types", "বিক্রিয়ার ধরন"),
}

enum class HybridizationType(
  val symbol: String,
  val tabEn: String,
  val tabBn: String,
  val titleEn: String,
  val titleBn: String,
  val descEn: String,
  val descBn: String,
  val geometry: String,
  val bondAngle: String,
  val sCharacter: String,
  val example: String,
  val orbitalSummary: String,
) {
  SP3(
    symbol = "sp³",
    tabEn = "sp³ · Alkane",
    tabBn = "sp³ · একক বন্ধন",
    titleEn = "sp³ Hybridization — Tetrahedral",
    titleBn = "sp³ সংকরায়ন — চতুস্তলকীয়",
    descEn =
      "One 2s and three 2p orbitals mix to form four equivalent sp³ hybrid orbitals pointing to the corners of a tetrahedron. All C–C bonds are strong sigma (σ) bonds allowing free rotation.",
    descBn =
      "একটি 2s ও তিনটি 2p অরবিটাল মিশ্রিত হয়ে চারটি সমশক্তির sp³ সংকর অরবিটাল তৈরি করে। সব একক বন্ধনই শক্তিশালী সিগমা (σ) বন্ধন এবং মুক্ত আবর্তন সম্ভব।",
    geometry = "Tetrahedral (চতুস্তলকীয়)",
    bondAngle = "109.5°",
    sCharacter = "25% s · 75% p",
    example = "CH₄, C₂H₆",
    orbitalSummary = "4 σ bonds · Free rotation",
  ),
  SP2(
    symbol = "sp²",
    tabEn = "sp² · Alkene",
    tabBn = "sp² · দ্বিবন্ধন",
    titleEn = "sp² Hybridization — Trigonal Planar",
    titleBn = "sp² সংকরায়ন — সমতলীয় ত্রিভুজাকার",
    descEn =
      "One 2s and two 2p orbitals form three sp² orbitals in one plane (120°), leaving one unhybridized p-orbital to overlap sideways and form a pi (π) bond. Rotation is restricted.",
    descBn =
      "একটি 2s ও দুটি 2p অরবিটাল মিলে একই সমতলে ১২০° কোণে তিনটি sp² অরবিটাল তৈরি করে। অসংকরিত একটি p-অরবিটাল পাশাপাশি অধিক্রমণ করে পাই (π) বন্ধন গঠন করে।",
    geometry = "Trigonal Planar (সমতলীয় ত্রিভুজ)",
    bondAngle = "120°",
    sCharacter = "33.3% s · 66.7% p",
    example = "CH₂=CH₂, C₆H₆",
    orbitalSummary = "1 σ + 1 π · Restricted rotation",
  ),
  SP(
    symbol = "sp",
    tabEn = "sp · Alkyne",
    tabBn = "sp · ত্রিবন্ধন",
    titleEn = "sp Hybridization — Linear",
    titleBn = "sp সংকরায়ন — সরলরৈখিক",
    descEn =
      "One 2s and one 2p orbital mix to give two sp orbitals at 180°. Two unhybridized p orbitals form two perpendicular pi (π) bonds. High s-character makes terminal alkyne H slightly acidic.",
    descBn =
      "একটি 2s ও একটি 2p অরবিটাল মিশে ১৮০° কোণে দুটি sp অরবিটাল গঠন করে এবং বাকি দুটি p-অরবিটাল দুটি π-বন্ধন তৈরি করে। ৫০% s-চরিত্র থাকায় প্রান্তীয় অ্যালকাইনের H সামান্য অম্লীয় হয়।",
    geometry = "Linear (সরলরৈখিক)",
    bondAngle = "180°",
    sCharacter = "50% s · 50% p",
    example = "HC≡CH, R-C≡N",
    orbitalSummary = "1 σ + 2 π · Acidic terminal H",
  ),
}

enum class BasicAccent {
  BLUE,
  GREEN,
  AMBER,
  VIOLET,
  ROSE,
  CYAN,
}

data class BasicTopic(
  val id: Int,
  val titleEn: String,
  val titleBn: String,
  val bodyEn: String,
  val bodyBn: String,
  val accent: BasicAccent,
)

data class FunctionalGroupItem(
  val classEn: String,
  val classBn: String,
  val groupFormula: String,
  val iupacSuffix: String,
  val example: String,
  val keyTestEn: String,
  val keyTestBn: String,
)

data class NamedReaction(
  val id: Int,
  val nameEn: String,
  val nameBn: String,
  val category: ReactionCategory,
  val tags: String,
  val equation: String,
  val conceptEn: String,
  val conceptBn: String,
  val reagents: String,
  val conditions: String,
  val mechanismEn: String,
  val mechanismBn: String,
  val example: String,
  val applicationEn: String,
  val applicationBn: String,
  val limitationEn: String,
  val limitationBn: String,
  val examNoteEn: String,
  val examNoteBn: String,
  val pdfCitations: List<String>,
  val parts: List<String>,
)

data class QuizQuestion(
  val id: Int,
  val questionEn: String,
  val questionBn: String,
  val optionsEn: List<String>,
  val optionsBn: List<String>,
  val correctIndex: Int,
  val explanationEn: String,
  val explanationBn: String,
  val relatedReactionId: Int,
)
