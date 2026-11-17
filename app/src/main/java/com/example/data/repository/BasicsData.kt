package com.example.data.repository

import com.example.data.model.BasicAccent
import com.example.data.model.BasicTopic
import com.example.data.model.FunctionalGroupItem
import com.example.data.model.QuizQuestion

object BasicsData {

  val basicTopics: List<BasicTopic> =
    listOf(
      BasicTopic(
        id = 1,
        titleEn = "What is Organic Chemistry & Vital Force?",
        titleBn = "জৈব রসায়ন কী ও প্রাণশক্তি মতবাদ?",
        bodyEn =
          "Organic chemistry is the study of carbon compounds (hydrocarbons and their derivatives). People once believed organic molecules required a 'Vital Force' from living organisms—until Friedrich Wöhler (1828) synthesized urea (NH₂)₂CO simply by heating inorganic ammonium cyanate NH₄CNO!",
        bodyBn =
          "কার্বনের যৌগ (বিশেষত হাইড্রোকার্বন ও তাদের জাতক) নিয়ে রসায়নের যে শাখায় আলোচনা করা হয় তা-ই জৈব রসায়ন। আগে ধারণা ছিল জৈব যৌগ কেবল জীবদেহে 'প্রাণশক্তি' (Vital Force) দ্বারা তৈরি হয়। কিন্তু ফ্রেডরিখ ভোলার (১৮২৮) অজৈব অ্যামোনিয়াম সায়ানেট NH₄CNO-কে উত্তপ্ত করে ইউরিয়া (NH₂)₂CO তৈরি করে তা ভুল প্রমাণ করেন!",
        accent = BasicAccent.BLUE,
      ),
      BasicTopic(
        id = 2,
        titleEn = "Why Carbon is Special: Tetravalency & Catenation",
        titleBn = "কার্বনের বিশেষত্ব: চতুর্যোজ্যতা ও ক্যাটেনেশন",
        bodyEn =
          "Carbon (Z = 6, 1s² 2s² 2p²) forms 4 covalent bonds (tetravalency) and links with other carbon atoms to build long chains, branched structures, and rings—a property called catenation. Carbon forms single (C–C), double (C=C), and triple (C≡C) bonds.",
        bodyBn =
          "কার্বন (Z = 6, 1s² 2s² 2p²) ৪টি সমযোজী বন্ধন গঠন করে (চতুর্যোজ্যতা) এবং নিজের পরমাণুর সাথে যুক্ত হয়ে দীর্ঘ শিকল, শাখা ও বলয় তৈরি করতে পারে—যাকে ক্যাটেনেশন (Catenation) বলে। কার্বন একক (C–C), দ্বিবন্ধন (C=C) ও ত্রিবন্ধন (C≡C) গঠন করে।",
        accent = BasicAccent.GREEN,
      ),
      BasicTopic(
        id = 3,
        titleEn = "Bond Fission: Homolytic vs Heterolytic",
        titleBn = "বন্ধন বিভাজন: সুষম বনাম অসম বিভাজন",
        bodyEn =
          "• Homolytic fission: Each atom keeps 1 electron → forms Free Radicals (favoured by UV light, heat, or peroxides).\n• Heterolytic fission: One atom takes both electrons → forms Carbocation (C⁺) and Carbanion (C⁻). Carbocation stability: 3° > 2° > 1° > CH₃⁺.",
        bodyBn =
          "• সুষম বিভাজন (Homolytic): প্রতিটি পরমাণু ১টি করে ইলেকট্রন নেয় → ফ্রি রেডিকেল তৈরি হয় (UV আলো বা তাপের প্রভাবে)।\n• অসম বিভাজন (Heterolytic): একটি পরমাণু উভয় ইলেকট্রন নেয় → কার্বোক্যাটায়ন (C⁺) ও কার্বঅ্যানায়ন (C⁻) তৈরি হয়। কার্বোক্যাটায়নের স্থায়িত্ব: 3° > 2° > 1° > CH₃⁺।",
        accent = BasicAccent.AMBER,
      ),
      BasicTopic(
        id = 4,
        titleEn = "Electrophiles vs Nucleophiles",
        titleBn = "ইলেকট্রোফাইল বনাম নিউক্লিওফাইল",
        bodyEn =
          "• Electrophile (Electron-lover): Electron-deficient species seeking electrons (e.g., H⁺, NO₂⁺, CH₃⁺, AlCl₃, Br⁺).\n• Nucleophile (Nucleus-lover): Electron-rich species with a lone pair or negative charge (e.g., OH⁻, CN⁻, NH₃, H₂O, R-MgX). Curved arrows flow from nucleophile → electrophile.",
        bodyBn =
          "• ইলেকট্রোফাইল (ইলেকট্রন-আকর্ষী): ইলেকট্রন ঘাটতিযুক্ত কণা যারা ইলেকট্রন গ্রহণ করে (যেমন: H⁺, NO₂⁺, CH₃⁺, AlCl₃, Br⁺)।\n• নিউক্লিওফাইল (নিউক্লিয়াস-আকর্ষী): মুক্তজোড় বা ঋণাত্মক চার্জযুক্ত ইলেকট্রন সমৃদ্ধ কণা (যেমন: OH⁻, CN⁻, NH₃, H₂O, R-MgX)। বাঁকা তীর সবসময় নিউক্লিওফাইল → ইলেকট্রোফাইল দিকে যায়।",
        accent = BasicAccent.VIOLET,
      ),
      BasicTopic(
        id = 5,
        titleEn = "Core Reaction Classes",
        titleBn = "প্রধান বিক্রিয়ার ধরনসমূহ",
        bodyEn =
          "• Substitution: One group replaces another (SN1, SN2, EAS).\n• Addition: Atoms add across C=C or C≡C (Markovnikov / Peroxide effect).\n• Elimination: Loss of small molecule to form C=C (Saytzeff rule).\n• Rearrangement: Skeleton reorganizes (Wöhler, Beckmann, Hofmann).",
        bodyBn =
          "• প্রতিস্থাপন (Substitution): একটি মূলক আরেকটিকে সরায় (SN1, SN2, EAS)।\n• সংযোজন (Addition): C=C বা C≡C বন্ধনে পরমাণু যুক্ত হয় (মারকনিকভ / পারঅক্সাইড প্রভাব)।\n• অপসারণ (Elimination): ক্ষুদ্র অণু বের হয়ে C=C তৈরি হয় (সাইটজেফ নীতি)।\n• পুনর্বিন্যাস (Rearrangement): অণুর গঠন বদলে যায় (ভোলার, বেকম্যান, হফম্যান)।",
        accent = BasicAccent.ROSE,
      ),
      BasicTopic(
        id = 6,
        titleEn = "Formula Representations & Isomerism",
        titleBn = "সংকেত লেখার পদ্ধতি ও সমানুতা",
        bodyEn =
          "• Molecular: C₂H₆O (only atom count).\n• Condensed: CH₃-CH₂-OH (ethanol) vs CH₃-O-CH₃ (dimethyl ether — functional isomers!).\n• Bond-line: Zig-zag lines where each vertex/end represents a carbon with implicit hydrogens.",
        bodyBn =
          "• আণবিক সংকেত: C₂H₆O (শুধু পরমাণুর সংখ্যা)।\n• গাঠনিক/সংক্ষিপ্ত সংকেত: CH₃-CH₂-OH (ইথানল) বনাম CH₃-O-CH₃ (ডাইমিথাইল ইথার — কার্যকরী মূলক সমানু!)।\n• বন্ড-লাইন সংকেত: জিগ-জ্যাগ রেখার প্রতিটি কোণা ও প্রান্ত এক-একটি কার্বন নির্দেশ করে।",
        accent = BasicAccent.CYAN,
      ),
    )

  val functionalGroups: List<FunctionalGroupItem> =
    listOf(
      FunctionalGroupItem(
        "Alkane",
        "অ্যালকেন",
        "C–C (single)",
        "-ane",
        "CH₃–CH₃ (Ethane)",
        "Inert to Br₂/KMnO₄; Radical halogenation",
        "Br₂/KMnO₄-এ নিষ্ক্রিয়; ফ্রি-রেডিকেল হ্যালোজিনেশন",
      ),
      FunctionalGroupItem(
        "Alkene",
        "অ্যালকিন",
        "C=C",
        "-ene",
        "CH₂=CH₂ (Ethene)",
        "Markovnikov addition, Ozonolysis, Baeyer's test",
        "মারকনিকভ সংযোজন, ওজোনোলাইসিস, বেয়ার পরীক্ষা",
      ),
      FunctionalGroupItem(
        "Alkyne",
        "অ্যালকাইন",
        "C≡C",
        "-yne",
        "HC≡CH (Ethyne)",
        "Kucherov hydration (HgSO₄/H₂SO₄), Hydrogenation",
        "কুচেরভ আর্দ্রবিশ্লেষণ, হাইড্রোজেনেশন",
      ),
      FunctionalGroupItem(
        "Alkyl Halide",
        "অ্যালকাইল হ্যালাইড",
        "–X (F,Cl,Br,I)",
        "halo- (prefix)",
        "CH₃CH₂Br",
        "SN1/SN2, Wurtz, Grignard, Swarts",
        "SN1/SN2, উর্টজ, গ্রিগনার্ড, সোয়ার্টস বিক্রিয়া",
      ),
      FunctionalGroupItem(
        "Alcohol",
        "অ্যালকোহল",
        "–OH",
        "-ol",
        "CH₃CH₂OH (Ethanol)",
        "Lucas test (3°>2°>1°), Esterification, Iodoform",
        "লুকাস পরীক্ষা, এস্টারীকরণ, আয়োডোফর্ম",
      ),
      FunctionalGroupItem(
        "Ether",
        "ইথার",
        "R–O–R'",
        "alkoxy-",
        "C₂H₅–O–C₂H₅",
        "Williamson ether synthesis (SN2)",
        "উইলিয়ামসন ইথার সংশ্লেষণ",
      ),
      FunctionalGroupItem(
        "Aldehyde",
        "অ্যালডিহাইড",
        "–CHO",
        "-al",
        "CH₃CHO (Ethanal)",
        "2,4-DNP, Tollens, Fehling, Aldol, Cannizzaro",
        "2,4-DNP, টলেন, ফেলিং, অ্যালডল, ক্যানিজারো",
      ),
      FunctionalGroupItem(
        "Ketone",
        "কিটোন",
        ">C=O",
        "-one",
        "CH₃COCH₃ (Acetone)",
        "2,4-DNP, Haloform, Clemmensen, Popoff's rule",
        "2,4-DNP, হ্যালোফর্ম, ক্লিমেনসেন, পপফ নীতি",
      ),
      FunctionalGroupItem(
        "Carboxylic Acid",
        "কার্বক্সিলিক এসিড",
        "–COOH",
        "-oic acid",
        "CH₃COOH",
        "Fischer Esterification, Soda-lime Decarboxylation",
        "ফিশার এস্টারীকরণ, সোডা-লাইম ডিকার্বক্সিলেশন",
      ),
      FunctionalGroupItem(
        "Ester",
        "এস্টার",
        "–COOR",
        "-oate",
        "CH₃COOC₂H₅",
        "Saponification (base hydrolysis → soap)",
        "সাবানায়ন (ক্ষারীয় আর্দ্রবিশ্লেষণ)",
      ),
      FunctionalGroupItem(
        "Amide",
        "অ্যামাইড",
        "–CONH₂",
        "-amide",
        "CH₃CONH₂",
        "Hofmann bromamide degradation (→ 1° amine)",
        "হফম্যান ব্রোমামাইড ক্ষুদ্রাংশকরণ",
      ),
      FunctionalGroupItem(
        "Amine",
        "অ্যামিন",
        "–NH₂",
        "-amine",
        "CH₃NH₂ / C₆H₅NH₂",
        "Carbylamine test, Diazotization, Azo coupling",
        "কার্বিলঅ্যামিন পরীক্ষা, ডায়াজোটিকরণ, অ্যাজো যুগলায়ন",
      ),
    )

  val quizQuestions: List<QuizQuestion> =
    listOf(
      QuizQuestion(
        id = 1,
        questionEn = "Which aldehyde undergoes the Cannizzaro reaction with 50% NaOH?",
        questionBn = "৫০% NaOH-এর সাথে নিচের কোন অ্যালডিহাইড ক্যানিজারো বিক্রিয়া দেয়?",
        optionsEn = listOf("CH₃CHO (Ethanal)", "HCHO (Methanal)", "CH₃CH₂CHO (Propanal)", "CH₃COCH₃ (Acetone)"),
        optionsBn = listOf("CH₃CHO (ইথান্যাল)", "HCHO (মিথান্যাল)", "CH₃CH₂CHO (প্রোপান্যাল)", "CH₃COCH₃ (অ্যাসিটোন)"),
        correctIndex = 1,
        explanationEn = "Cannizzaro requires NO alpha-hydrogen (α-H). HCHO and C₆H₅CHO lack α-H.",
        explanationBn = "ক্যানিজারো বিক্রিয়ার জন্য অ্যালডিহাইডে α-H থাকা যাবে না। HCHO এবং C₆H₅CHO-তে কোনো α-H নেই।",
        relatedReactionId = 7,
      ),
      QuizQuestion(
        id = 2,
        questionEn = "In Lucas test (anhydrous ZnCl₂ + conc. HCl), which alcohol gives immediate turbidity?",
        questionBn = "লুকাস পরীক্ষায় (অনার্দ্র ZnCl₂ + গাঢ় HCl) কোন অ্যালকোহল তাৎক্ষণিক ঘোলাটে অধঃক্ষেপ দেয়?",
        optionsEn = listOf("1° Alcohol", "2° Alcohol", "3° Alcohol", "Methanol"),
        optionsBn = listOf("১° অ্যালকোহল", "২° অ্যালকোহল", "৩° অ্যালকোহল", "মিথানল"),
        correctIndex = 2,
        explanationEn = "Lucas test proceeds via carbocation stability (3° > 2° > 1°), so 3° alcohols react immediately.",
        explanationBn = "কার্বোক্যাটায়নের স্থায়িত্ব (3° > 2° > 1°) অনুসারে ৩° অ্যালকোহল তাৎক্ষণিক সাদা ঘোলাটে ভাব দেয়।",
        relatedReactionId = 30,
      ),
      QuizQuestion(
        id = 3,
        questionEn = "What is the role of BaSO₄ in the Rosenmund reduction (R-COCl + H₂ → R-CHO)?",
        questionBn = "রোজেনমুন্ড বিজারণে (Pd/BaSO₄) BaSO₄-এর কাজ কী?",
        optionsEn =
          listOf(
            "Acts as a strong oxidant",
            "Poisons Pd to stop reduction at the aldehyde stage",
            "Supplies H⁺ ions",
            "Removes H₂O from the product",
          ),
        optionsBn =
          listOf(
            "তীব্র জারক হিসেবে কাজ করে",
            "Pd-কে বিষাক্ত করে বিক্রিয়া অ্যালডিহাইড ধাপে থামায়",
            "H⁺ আয়ন সরবরাহ করে",
            "উৎপাদ থেকে পানি অপসারণ করে",
          ),
        correctIndex = 1,
        explanationEn = "BaSO₄ partially deactivates ('poisons') Pd so the aldehyde is not over-reduced to an alcohol.",
        explanationBn = "BaSO₄ প্রভাবক Pd-এর সক্রিয়তা কমিয়ে দেয়, ফলে অ্যালডিহাইড পুনরায় বিজারিত হয়ে অ্যালকোহল হয় না।",
        relatedReactionId = 45,
      ),
      QuizQuestion(
        id = 4,
        questionEn = "Which reagent selectively oxidises toluene to benzaldehyde in the Etard reaction?",
        questionBn = "ইটার্ড বিক্রিয়ায় টলুইনকে সরাসরি বেনজালডিহাইডে জারিত করতে কোন বিকারক ব্যবহৃত হয়?",
        optionsEn = listOf("Hot KMnO₄", "CrO₂Cl₂ in CCl₄", "Conc. HNO₃", "Zn(Hg) + HCl"),
        optionsBn = listOf("উত্তপ্ত KMnO₄", "CCl₄-এ দ্রবীভূত CrO₂Cl₂", "গাঢ় HNO₃", "Zn(Hg) + HCl"),
        correctIndex = 1,
        explanationEn = "Chromyl chloride (CrO₂Cl₂) stops oxidation cleanly at benzaldehyde without forming benzoic acid.",
        explanationBn = "ক্রোমিল ক্লোরাইড (CrO₂Cl₂) টলুইনকে বেনজোয়িক এসিডে না নিয়ে বেনজালডিহাইড ধাপে থামায়।",
        relatedReactionId = 18,
      ),
      QuizQuestion(
        id = 5,
        questionEn = "Anti-Markovnikov addition in the presence of organic peroxides (Kharasch effect) works ONLY with:",
        questionBn = "জৈব পারঅক্সাইডের উপস্থিতিতে অ্যান্টি-মারকনিকভ (খারাশ) প্রভাব কেবল নিচের কোনটির সাথে ঘটে?",
        optionsEn = listOf("HCl", "HI", "HBr", "HF"),
        optionsBn = listOf("HCl", "HI", "HBr", "HF"),
        correctIndex = 2,
        explanationEn = "Only HBr has both propagation steps exothermic in the radical chain mechanism.",
        explanationBn = "তাপগতিবিদ্যার কারণে ফ্রি-রেডিকেল শিকল বিক্রিয়ার উভয় ধাপ কেবল HBr-এর ক্ষেত্রে তাপোৎপাদী।",
        relatedReactionId = 35,
      ),
      QuizQuestion(
        id = 6,
        questionEn = "Heating ethanol with conc. H₂SO₄ at 140 °C (413 K) mainly produces:",
        questionBn = "ইথানলকে গাঢ় H₂SO₄ সহ ১৪০°C (413 K) তাপে উত্তপ্ত করলে প্রধানত কী উৎপন্ন হয়?",
        optionsEn = listOf("Ethene", "Diethyl ether", "Ethanal", "Ethanoic acid"),
        optionsBn = listOf("ইথিন", "ডাইইথাইল ইথার", "ইথান্যাল", "ইথানয়িক এসিড"),
        correctIndex = 1,
        explanationEn = "At 413 K (140 °C) intermolecular dehydration gives diethyl ether; at 443 K (170 °C) it gives ethene.",
        explanationBn = "১৪০°C (413 K)-এ ডাইইথাইল ইথার এবং ১৭০°C (443 K)-এ ইথিন উৎপন্ন হয়।",
        relatedReactionId = 42,
      ),
      QuizQuestion(
        id = 7,
        questionEn = "Which test distinguishes primary (1°) amines via a foul-smelling isocyanide (R-NC)?",
        questionBn = "কোন পরীক্ষার মাধ্যমে তীব্র দুর্গন্ধযুক্ত আইসোসায়ানাইড (R-NC) তৈরি করে ১° অ্যামিন শনাক্ত করা হয়?",
        optionsEn = listOf("Tollens' test", "Carbylamine test", "Lassaigne's test", "Fehling's test"),
        optionsBn = listOf("টলেন পরীক্ষা", "কার্বিলঅ্যামিন পরীক্ষা", "ল্যাসেইন পরীক্ষা", "ফেলিং পরীক্ষা"),
        correctIndex = 1,
        explanationEn = "1° amine + CHCl₃ + alc. KOH (60–70 °C) produces foul-smelling R-NC.",
        explanationBn = "১° অ্যামিন + CHCl₃ + অ্যালকোহলীয় KOH (৬০–৭০°C) বিক্রিয়ায় দুর্গন্ধযুক্ত R-NC উৎপন্ন হয়।",
        relatedReactionId = 8,
      ),
      QuizQuestion(
        id = 8,
        questionEn = "What is the product when acetone is heated with Zn(Hg) and conc. HCl (Clemmensen)?",
        questionBn = "ক্লিমেনসেন বিজারণে অ্যাসিটোনকে Zn(Hg) ও গাঢ় HCl সহ উত্তপ্ত করলে কী পাওয়া যায়?",
        optionsEn = listOf("Propan-2-ol", "Propane", "Propene", "Propanoic acid"),
        optionsBn = listOf("প্রোপান-২-অল", "প্রোপেন", "প্রোপিন", "প্রোপানয়িক এসিড"),
        correctIndex = 1,
        explanationEn = "Clemmensen reduction converts >C=O completely into >CH₂, yielding propane.",
        explanationBn = "ক্লিমেনসেন বিজারণে কার্বনিল মূলক (>C=O) সম্পূর্ণ বিজারিত হয়ে >CH₂ (প্রোপেন) তৈরি করে।",
        relatedReactionId = 11,
      ),
      QuizQuestion(
        id = 9,
        questionEn = "Which compound gives a positive Haloform (Iodoform) yellow precipitate (CHI₃)?",
        questionBn = "নিচের কোন যৌগটি হলুদ বর্ণের আয়োডোফর্ম (CHI₃) অধঃক্ষেপ দেয়?",
        optionsEn = listOf("Methanol (CH₃OH)", "Ethanol (CH₃CH₂OH)", "Methanal (HCHO)", "Propan-1-ol"),
        optionsBn = listOf("মিথানল (CH₃OH)", "ইথানল (CH₃CH₂OH)", "মিথান্যাল (HCHO)", "প্রোপান-১-অল"),
        correctIndex = 1,
        explanationEn = "Ethanol has the CH₃-CH(OH)- unit required for the iodoform test; methanol does not.",
        explanationBn = "ইথানলে CH₃-CH(OH)- মূলক থাকায় এটি আয়োডোফর্ম পরীক্ষা দেয়; মিথানল দেয় না।",
        relatedReactionId = 24,
      ),
      QuizQuestion(
        id = 10,
        questionEn = "In Hofmann bromamide degradation, CH₃CONH₂ reacts with Br₂/KOH to form:",
        questionBn = "হফম্যান ব্রোমামাইড বিক্রিয়ায় CH₃CONH₂ এর সাথে Br₂ ও KOH বিক্রিয়া করে কী তৈরি হয়?",
        optionsEn = listOf("Ethylamine (CH₃CH₂NH₂)", "Methylamine (CH₃NH₂)", "Acetonitrile", "Ethanol"),
        optionsBn = listOf("ইথাইলঅ্যামিন (CH₃CH₂NH₂)", "মিথাইলঅ্যামিন (CH₃NH₂)", "অ্যাসিটোনিট্রাইল", "ইথানল"),
        correctIndex = 1,
        explanationEn = "Hofmann degradation drops the C=O carbon, giving a 1° amine with ONE fewer carbon.",
        explanationBn = "হফম্যান ব্রোমামাইড বিক্রিয়ায় ১টি কার্বন কমে গিয়ে মিথাইলঅ্যামিন (CH₃NH₂) তৈরি হয়।",
        relatedReactionId = 26,
      ),
      QuizQuestion(
        id = 11,
        questionEn = "What is the bond angle and geometry of an sp²-hybridized carbon atom?",
        questionBn = "sp² সংকরিত কার্বন পরমাণুর জ্যামিতিক আকৃতি ও বন্ধন কোণ কত?",
        optionsEn = listOf("Tetrahedral, 109.5°", "Trigonal planar, 120°", "Linear, 180°", "Octahedral, 90°"),
        optionsBn = listOf("চতুস্তলকীয়, ১০৯.৫°", "সমতলীয় ত্রিভুজাকার, ১২০°", "সরলরৈখিক, ১৮০°", "অষ্টতলকীয়, ৯০°"),
        correctIndex = 1,
        explanationEn = "sp² carbons have 3 hybrid orbitals at 120° in a trigonal planar geometry.",
        explanationBn = "sp² সংকরিত কার্বনের তিনটি সংকর অরবিটাল একই সমতলে ১২০° কোণে অবস্থান করে।",
        relatedReactionId = 27,
      ),
      QuizQuestion(
        id = 12,
        questionEn = "Which reaction converts a chlorobenzene + alkyl halide with Na/dry ether into an alkylbenzene?",
        questionBn = "শুষ্ক ইথারে ধাতব সোডিয়ামের উপস্থিতিতে অ্যারাইল হ্যালাইড ও অ্যালকাইল হ্যালাইডের বিক্রিয়াকে কী বলে?",
        optionsEn = listOf("Wurtz reaction", "Wurtz-Fittig reaction", "Friedel-Crafts acylation", "Swarts reaction"),
        optionsBn = listOf("উর্টজ বিক্রিয়া", "উর্টজ-ফিটিগ বিক্রিয়া", "ফ্রিডেল-ক্রাফটস অ্যাসাইলেশন", "সোয়ার্টস বিক্রিয়া"),
        correctIndex = 1,
        explanationEn = "Aryl halide + Alkyl halide + 2 Na (dry ether) → Alkylbenzene is the Wurtz-Fittig reaction.",
        explanationBn = "অ্যারাইল হ্যালাইড + অ্যালকাইল হ্যালাইড + Na (শুষ্ক ইথার) বিক্রিয়াটিকে উর্টজ-ফিটিগ বিক্রিয়া বলে।",
        relatedReactionId = 56,
      ),
    )
}
