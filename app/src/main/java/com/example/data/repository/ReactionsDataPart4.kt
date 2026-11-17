package com.example.data.repository

import com.example.data.model.HazariNagChapter
import com.example.data.model.NamedReaction
import com.example.data.model.ReactionCategory

object ReactionsDataPart4 {

  val reactions: List<NamedReaction> =
    listOf(
      NamedReaction(
        id = 57,
        nameEn = "Fittig reaction",
        nameBn = "ফিটিগ বিক্রিয়া (ডাইফেনাইল প্রস্তুতি)",
        category = ReactionCategory.NAME_REACTION,
        tags = "name reaction, aromatic, diphenyl, biphenyl, sodium, hazari nag",
        equation = "2 C₆H₅-Cl + 2 Na --Dry Ether--> C₆H₅-C₆H₅ (Diphenyl) + 2 NaCl",
        conceptEn =
          "Two molecules of an aryl halide (such as chlorobenzene or bromobenzene) react with metallic sodium in dry ether to couple and form diphenyl (biphenyl).",
        conceptBn =
          "শুষ্ক ইথারে দ্রবীভূত দুই অণু অ্যারাইল হ্যালাইড (যেমন ক্লোরোবেনজিন) ধাতব সোডিয়ামের সাথে বিক্রিয়া করে দুটি বেনজিন বলয় যুক্ত হয়ে ডাইফেনাইল (বাইফেনাইল) তৈরি করে।",
        reagents = "Aryl halide (C₆H₅Cl / C₆H₅Br), metallic Sodium (Na)",
        conditions = "Dry ether (anhydrous)",
        mechanismEn = "Organosodium / aryl radical coupling between two aryl halide molecules.",
        mechanismBn = "শুষ্ক ইথারে সোডিয়াম হ্যালোজেন অপসারণ করে দুটি ফিনাইল মূলককে যুক্ত করে।",
        example = "2 C₆H₅Cl + 2 Na --Dry Ether--> C₆H₅-C₆H₅ + 2 NaCl",
        applicationEn = "Preparation of diphenyl (biphenyl) from aryl halides.",
        applicationBn = "ক্লোরোবেনজিন বা ব্রোমোবেনজিন থেকে ডাইফেনাইল প্রস্তুতিতে ব্যবহৃত হয়।",
        limitationEn = "Requires dry ether; aryl halides are less reactive than alkyl halides.",
        limitationBn = "শুষ্ক ইথার অপরিহার্য; অ্যারাইল হ্যালাইড অ্যালকাইল হ্যালাইডের চেয়ে কম সক্রিয়।",
        examNoteEn =
          "Wurtz = Alkyl + Alkyl; Fittig = Aryl + Aryl; Wurtz-Fittig = Aryl + Alkyl.",
        examNoteBn =
          "হাজারী ও নাগ টিপস: অ্যালকাইল + অ্যালকাইল = উর্টজ; অ্যারাইল + অ্যারাইল = ফিটিগ; অ্যারাইল + অ্যালকাইল = উর্টজ-ফিটিগ।",
        pdfCitations = listOf("Hazari & Nag HSC Ch. 2 (#3)"),
        parts = listOf("hazari-nag"),
        hazariNagChapter = HazariNagChapter.HYDROCARBONS_AROMATICS,
        hazariNagTopicLabel = "হাজারী ও নাগ · Ch.2 #3",
      ),
      NamedReaction(
        id = 58,
        nameEn = "Kolbe's electrolysis",
        nameBn = "কোলবে ইলেকট্রোলাইসিস (কোলবের তড়িৎ বিশ্লেষণ)",
        category = ReactionCategory.NAME_REACTION,
        tags = "name reaction, electrolysis, alkane, decarboxylation, anode, hazari nag",
        equation = "2 R-COONa + 2 H₂O --Electrolysis--> R-R (at anode) + 2 CO₂↑ + H₂↑ (at cathode) + 2 NaOH",
        conceptEn =
          "Electrolysis of a concentrated aqueous solution of sodium or potassium salt of a carboxylic acid produces an alkane with an EVEN number of carbons and CO₂ at the anode, while H₂ and NaOH form at the cathode.",
        conceptBn =
          "ফ্যাটি এসিডের সোডিয়াম বা পটাশিয়াম লবণের গাঢ় জলীয় দ্রবণকে তড়িৎ বিশ্লেষণ করলে অ্যানোডে জোড়-সংখ্যক কার্বনযুক্ত অ্যালকেন ও CO₂ গ্যাস এবং ক্যাথোডে H₂ গ্যাস ও NaOH উৎপন্ন হয়।",
        reagents = "Aqueous sodium/potassium carboxylate (e.g. CH₃COONa)",
        conditions = "Electrolysis (Pt electrodes)",
        mechanismEn =
          "At anode: RCOO⁻ loses e⁻ → RCOO• → R• + CO₂; two R• radicals couple to R-R.",
        mechanismBn =
          "অ্যানোডে RCOO⁻ জারিত হয়ে CO₂ ত্যাগ করে R• ফ্রি রেডিকেল তৈরি করে, যা যুক্ত হয়ে R-R অ্যালকেন দেয়।",
        example = "2 CH₃COONa + 2 H₂O → C₂H₆ (Ethane) + 2 CO₂ + H₂ + 2 NaOH",
        applicationEn = "Preparation of symmetrical alkanes (and alkenes/alkynes from dicarboxylates).",
        applicationBn = "জোড় সংখ্যার কার্বনযুক্ত অ্যালকেন প্রস্তুতিতে (বিশেষত ইথেন/বিউটেন)।",
        limitationEn = "Cannot produce methane (CH₄); yields symmetrical even-carbon alkanes.",
        limitationBn = "এই পদ্ধতিতে মিথেন (CH₄) তৈরি করা যায় না; কেবল জোড় সংখ্যক কার্বনের অ্যালকেন হয়।",
        examNoteEn = "Alkane + CO₂ form at the ANODE; H₂ gas forms at the CATHODE.",
        examNoteBn = "ভর্তি পরীক্ষার প্রশ্ন: অ্যালকেন ও CO₂ উৎপন্ন হয় অ্যানোডে, আর H₂ গ্যাস বের হয় ক্যাথোডে!",
        pdfCitations = listOf("Hazari & Nag HSC Ch. 2 (#5)"),
        parts = listOf("hazari-nag"),
        hazariNagChapter = HazariNagChapter.HYDROCARBONS_AROMATICS,
        hazariNagTopicLabel = "হাজারী ও নাগ · Ch.2 #5",
      ),
      NamedReaction(
        id = 59,
        nameEn = "Wolff-Kishner reduction",
        nameBn = "উলফ-কিশনার বিজারণ বিক্রিয়া",
        category = ReactionCategory.NAME_REACTION,
        tags = "name reaction, reduction, carbonyl, aldehyde, ketone, alkane, hazari nag",
        equation = ">C=O + NH₂NH₂ → >C=NNH₂ --KOH, ethylene glycol, 180-200 °C--> >CH₂ + N₂↑ + H₂O",
        conceptEn =
          "Converts an aldehyde or ketone (>C=O) completely into a methylene (>CH₂) alkane by heating with hydrazine (NH₂NH₂) and a strong base (KOH) in ethylene glycol.",
        conceptBn =
          "অ্যালডিহাইড বা কিটোনের কার্বোনিল মূলককে (>C=O) হাইড্রাজিন (NH₂NH₂) ও ক্ষার (KOH) সহ ইথিলিন গ্লাইকল মাধ্যমে উত্তপ্ত করলে >C=O মূলক বিজারিত হয়ে সরাসরি মিথিলিন (>CH₂) তথা অ্যালকেনে পরিণত হয়।",
        reagents = "Hydrazine (NH₂NH₂), KOH in ethylene glycol",
        conditions = "Strong heating (180–200 °C), alkaline medium",
        mechanismEn =
          "Formation of hydrazone (>C=NNH₂), followed by base-induced proton shifts and loss of N₂ gas.",
        mechanismBn = "প্রথমে হাইড্রাজোন তৈরি হয়, পরে ক্ষারের উপস্থিতিতে N₂ গ্যাস বের হয়ে >CH₂ গঠিত হয়।",
        example = "CH₃COCH₃ (Acetone) --NH₂NH₂ / KOH--> CH₃CH₂CH₃ (Propane) + N₂",
        applicationEn =
          "Deoxygenation of acid-sensitive aldehydes/ketones to alkanes under basic conditions.",
        applicationBn = "ক্ষারীয় মাধ্যমে কার্বোনিল যৌগ থেকে সরাসরি অ্যালকেন প্রস্তুতিতে।",
        limitationEn = "Strongly basic & high temperature — unsuitable for base-sensitive substrates.",
        limitationBn = "তীব্র ক্ষারীয় মাধ্যম হওয়ায় ক্ষার-সংবেদনশীল যৌগে ব্যবহার করা যায় না।",
        examNoteEn =
          "Clemmensen = Acidic (Zn-Hg / conc. HCl); Wolff-Kishner = Basic (NH₂NH₂ / KOH).",
        examNoteBn =
          "মনে রাখার কৌশল: ক্লেমেনসেন = এসিডীয় মাধ্যম (Zn-Hg/HCl); উলফ-কিশনার = ক্ষারীয় মাধ্যম (NH₂NH₂/KOH)।",
        pdfCitations = listOf("Hazari & Nag HSC Ch. 2 (#13)"),
        parts = listOf("hazari-nag"),
        hazariNagChapter = HazariNagChapter.ALDEHYDES_KETONES,
        hazariNagTopicLabel = "হাজারী ও নাগ · Ch.2 #13",
      ),
      NamedReaction(
        id = 60,
        nameEn = "Reimer-Tiemann reaction",
        nameBn = "রাইমার-টাইম্যান বিক্রিয়া (ফেনল থেকে স্যালিসিল্যালডিহাইড)",
        category = ReactionCategory.NAME_REACTION,
        tags = "name reaction, phenol, salicylaldehyde, chloroform, hazari nag",
        equation = "C₆H₅-OH + CHCl₃ + 3 KOH --60-70 °C, then H⁺--> o-HO-C₆H₄-CHO (Salicylaldehyde) + 3 KCl + 2 H₂O",
        conceptEn =
          "Heating phenol with chloroform (CHCl₃) and aqueous KOH/NaOH at 60–70 °C followed by acidification introduces a -CHO group ortho to -OH, forming salicylaldehyde.",
        conceptBn =
          "ফেনলকে ক্লোরোফর্ম (CHCl₃) এবং KOH/NaOH ক্ষার দ্রবণসহ ৬০–৭০°C তাপমাত্রায় উত্তপ্ত করে পরে অম্লীয় করলে ফেনলের অর্থো অবস্থানে -CHO মূলক যুক্ত হয়ে স্যালিসিল্যালডিহাইড উৎপন্ন হয়।",
        reagents = "Phenol, Chloroform (CHCl₃), aqueous KOH/NaOH, then dilute HCl",
        conditions = "60–70 °C",
        mechanismEn =
          "CHCl₃ + OH⁻ generates electrophilic dichlorocarbene (:CCl₂) which attacks phenoxide at the ortho position.",
        mechanismBn =
          "CHCl₃ ও ক্ষার থেকে সৃষ্ট ইলেকট্রোফাইল ডাইক্লোরোকার্বিন (:CCl₂) ফেনোক্সাইড আয়নের অর্থো অবস্থানে আক্রমণ করে।",
        example = "Phenol + CHCl₃ + 3 KOH (60–70 °C) → Salicylaldehyde (2-hydroxybenzaldehyde)",
        applicationEn =
          "Synthesis of salicylaldehyde (if CCl₄ is used instead of CHCl₃, salicylic acid is formed).",
        applicationBn = "ফেনল থেকে স্যালিসিল্যালডিহাইড প্রস্তুতিতে (আর CCl₄ নিলে স্যালিসিলিক এসিড হয়)।",
        limitationEn = "Requires strongly alkaline phenoxide; minor para-isomer also forms.",
        limitationBn = "সামান্য পরিমাণ প্যারা-হাইড্রক্সিবেনজালডিহাইড উপজাত হিসেবে তৈরি হতে পারে।",
        examNoteEn = "Same reagents as Carbylamine (CHCl₃ + KOH), but substrate is PHENOL → Salicylaldehyde!",
        examNoteBn =
          "গুরুত্বপূর্ণ পার্থক্য: CHCl₃ + KOH + ১° অ্যামিন = কার্বিলঅ্যামিন; আর CHCl₃ + KOH + ফেনল = রাইমার-টাইম্যান (স্যালিসিল্যালডিহাইড)!",
        pdfCitations = listOf("Hazari & Nag HSC Ch. 2 (#16)"),
        parts = listOf("hazari-nag"),
        hazariNagChapter = HazariNagChapter.ALCOHOLS_PHENOLS,
        hazariNagTopicLabel = "হাজারী ও নাগ · Ch.2 #16",
      ),
      NamedReaction(
        id = 61,
        nameEn = "Kolbe-Schmitt reaction (Kolbe's reaction)",
        nameBn = "কোলব-স্মিট বিক্রিয়া (স্যালিসিলিক এসিড প্রস্তুতি)",
        category = ReactionCategory.NAME_REACTION,
        tags = "name reaction, phenol, salicylic acid, aspirin, co2, hazari nag",
        equation = "C₆H₅-ONa + CO₂ --125 °C, 4-7 atm--> o-HO-C₆H₄-COONa --HCl--> o-HO-C₆H₄-COOH (Salicylic acid)",
        conceptEn =
          "Sodium phenoxide reacts with carbon dioxide (CO₂) at 125 °C and 4–7 atm pressure, followed by acidification, to give 2-hydroxybenzoic acid (salicylic acid).",
        conceptBn =
          "সোডিয়াম ফেনোক্সাইডকে (ফেনোট) কার্বন ডাইঅক্সাইডের (CO₂) সাথে ১২৫°C তাপমাত্রা ও ৪–৭ atm চাপে উত্তপ্ত করে লঘু HCl দ্বারা অম্লীয় করলে অর্থো-হাইড্রক্সিবেনজোয়িক এসিড বা স্যালিসিলিক এসিড পাওয়া যায়।",
        reagents = "Sodium phenoxide (C₆H₅ONa), CO₂ gas, then dilute HCl",
        conditions = "125 °C (approx. 400 K), 4–7 atm pressure",
        mechanismEn =
          "Weak electrophile CO₂ attacks the electron-rich ortho position of phenoxide ion.",
        mechanismBn = "দুর্বল ইলেকট্রোফাইল CO₂ ফেনোট আয়নের অর্থো কার্বনে যুক্ত হয়ে স্যালিসিলিক এসিড দেয়।",
        example = "Sodium phenoxide + CO₂ (125 °C, 4–7 atm) → Salicylic acid (→ Aspirin)",
        applicationEn =
          "Industrial precursor to Aspirin (acetylsalicylic acid) and methyl salicylate (oil of wintergreen).",
        applicationBn = "অ্যাসপিরিন (ব্যথানাশক ওষুধ) তৈরির মূল কাঁচামাল স্যালিসিলিক এসিড উৎপাদনে।",
        limitationEn = "Requires dry sodium phenoxide under pressure.",
        limitationBn = "উচ্চ চাপ (৪–৭ atm) ও শুষ্ক সোডিয়াম ফেনোক্সাইড প্রয়োজন হয়।",
        examNoteEn =
          "Reimer-Tiemann gives SalicylALDEHYDE (-CHO); Kolbe-Schmitt gives Salicylic ACID (-COOH)!",
        examNoteBn =
          "মনে রাখবে: রাইমার-টাইম্যানে তৈরি হয় স্যালিসিল্যালডিহাইড (-CHO), আর কোলব-স্মিটে তৈরি হয় স্যালিসিলিক এসিড (-COOH)!",
        pdfCitations = listOf("Hazari & Nag HSC Ch. 2 (#17)"),
        parts = listOf("hazari-nag"),
        hazariNagChapter = HazariNagChapter.ALCOHOLS_PHENOLS,
        hazariNagTopicLabel = "হাজারী ও নাগ · Ch.2 #17",
      ),
      NamedReaction(
        id = 62,
        nameEn = "Sandmeyer reaction",
        nameBn = "স্যান্ডমেয়ার বিক্রিয়া (ডায়াজোনিয়াম থেকে হ্যালোবেনজিন)",
        category = ReactionCategory.NAME_REACTION,
        tags = "name reaction, diazonium, chlorobenzene, bromobenzene, benzonitrile, hazari nag",
        equation = "C₆H₅-N₂⁺Cl⁻ --Cu₂Cl₂ / HCl--> C₆H₅-Cl + N₂↑\nC₆H₅-N₂⁺Cl⁻ --Cu₂Br₂ / HBr--> C₆H₅-Br + N₂↑\nC₆H₅-N₂⁺Cl⁻ --CuCN / KCN--> C₆H₅-CN + N₂↑",
        conceptEn =
          "Treating a cold benzenediazonium salt solution with cuprous chloride (Cu₂Cl₂/HCl), cuprous bromide (Cu₂Br₂/HBr), or cuprous cyanide (CuCN/KCN) replaces the -N₂⁺ group with -Cl, -Br, or -CN.",
        conceptBn =
          "বেনজিন ডায়াজোনিয়াম ক্লোরাইডকে কিউপ্রাস হ্যালাইড (Cu₂Cl₂ / Cu₂Br₂) ও সংশ্লিষ্ট এসিড (HCl / HBr) অথবা CuCN সহ উত্তপ্ত করলে ডায়াজোনিয়াম মূলক অপসারিত হয়ে ক্লোরোবেনজিন, ব্রোমোবেনজিন বা সায়ানোবেনজিন তৈরি হয়।",
        reagents = "Benzenediazonium chloride + Cu₂Cl₂/HCl, Cu₂Br₂/HBr, or CuCN/KCN",
        conditions = "Warming after diazotization",
        mechanismEn =
          "Cu(I)-catalysed radical/electron-transfer substitution releasing stable N₂ gas.",
        mechanismBn = "Cu(I) প্রভাবিত প্রতিস্থাপন বিক্রিয়ার মাধ্যমে স্থায়ী N₂ গ্যাস বের হয়ে যায়।",
        example = "Benzenediazonium chloride + Cu₂Cl₂/HCl → Chlorobenzene + N₂",
        applicationEn =
          "Introducing -Cl, -Br, or -CN onto an aromatic ring cleanly at a specific position.",
        applicationBn = "অ্যানিলিন/ডায়াজোনিয়াম লবণ থেকে ক্লোরোবেনজিন, ব্রোমোবেনজিন ও সায়ানোবেনজিন প্রস্তুতিতে।",
        limitationEn = "Not used for -I (use KI) or -F (use HBF₄ / Balz-Schiemann).",
        limitationBn = "আয়োডোবেনজিন তৈরিতে Cu₂I₂ লাগে না, সরাসরি KI দিলেই হয়।",
        examNoteEn =
          "Sandmeyer uses CuPROUS SALTS (Cu₂Cl₂/Cu₂Br₂); Gattermann (#63) uses COPPER POWDER (Cu)!",
        examNoteBn =
          "পার্থক্য মনে রাখবে: স্যান্ডমেয়ারে Cu₂Cl₂/Cu₂Br₂ লবণ লাগে, কিন্তু গ্যাটারম্যানে কপার চূর্ণ (Cu powder) লাগে!",
        pdfCitations = listOf("Hazari & Nag HSC Ch. 2 (#22)"),
        parts = listOf("hazari-nag"),
        hazariNagChapter = HazariNagChapter.AMINES_NITROGEN,
        hazariNagTopicLabel = "হাজারী ও নাগ · Ch.2 #22",
      ),
      NamedReaction(
        id = 63,
        nameEn = "Gattermann reaction",
        nameBn = "গ্যাটারম্যান বিক্রিয়া (কপার চূর্ণের সাহায্যে)",
        category = ReactionCategory.NAME_REACTION,
        tags = "name reaction, diazonium, copper powder, chlorobenzene, hazari nag",
        equation = "C₆H₅-N₂⁺Cl⁻ --Cu powder / HCl--> C₆H₅-Cl + N₂↑ + CuCl\nC₆H₅-N₂⁺Cl⁻ --Cu powder / HBr--> C₆H₅-Br + N₂↑",
        conceptEn =
          "A modification of the Sandmeyer reaction in which freshly prepared copper powder (instead of cuprous halide salt) with HCl or HBr converts benzenediazonium chloride into chlorobenzene or bromobenzene.",
        conceptBn =
          "এটি স্যান্ডমেয়ার বিক্রিয়ারই বিকল্প রূপ। এখানে কিউপ্রাস লবণের (Cu₂Cl₂) পরিবর্তে সদ্য প্রস্তুত কপার চূর্ণ (Cu powder) এবং গাঢ় HCl বা HBr ব্যবহার করে ডায়াজোনিয়াম লবণ থেকে ক্লোরোবেনজিন বা ব্রোমোবেনজিন তৈরি করা হয়।",
        reagents = "Benzenediazonium chloride, Copper powder (Cu), HCl or HBr",
        conditions = "Gentle warming",
        mechanismEn = "Cu(0) metal-mediated replacement of -N₂⁺ group by Cl or Br with N₂ evolution.",
        mechanismBn = "কপার চূর্ণের উপস্থিতিতে N₂ গ্যাস অপসারিত হয়ে অ্যারাইল হ্যালাইড গঠিত হয়।",
        example = "Benzenediazonium chloride + Cu/HCl → Chlorobenzene + N₂",
        applicationEn = "Alternative laboratory synthesis of aryl chlorides and bromides from diazonium salts.",
        applicationBn = "ডায়াজোনিয়াম লবণ থেকে হ্যালোবেনজিন তৈরির সহজ বিকল্প পদ্ধতি।",
        limitationEn = "Generally gives slightly lower yield (~40%) than Sandmeyer reaction.",
        limitationBn = "স্যান্ডমেয়ার বিক্রিয়ার তুলনায় উৎপাদের পরিমাণ কিছুটা কম হতে পারে।",
        examNoteEn = "Key difference from Sandmeyer: uses metallic Cu powder, NOT Cu₂Cl₂.",
        examNoteBn = "ভর্তি পরীক্ষায় খুব আসে: কপার চূর্ণ (Cu powder) + HCl থাকলে গ্যাটারম্যান, আর Cu₂Cl₂ + HCl থাকলে স্যান্ডমেয়ার!",
        pdfCitations = listOf("Hazari & Nag HSC Ch. 2 (#23)"),
        parts = listOf("hazari-nag"),
        hazariNagChapter = HazariNagChapter.AMINES_NITROGEN,
        hazariNagTopicLabel = "হাজারী ও নাগ · Ch.2 #23",
      ),
    )
}
