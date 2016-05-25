#######################################
SitEnt Syntactic-Semantic Features
#######################################


#############################################
1. LICENSE
#############################################

Copyright 2015 Annemarie Friedrich

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

IMPORTANT NOTE:
All components in this package are licensed under the Apache Software License (ASL) version 2 - but their dependencies may not be:

IMPORTANT LICENSE NOTE - It must be pointed out that while the component’s source code itself is licensed under the ASL, individual components might make use of third-party libraries or products that are not licensed under the ASL, such as LGPL libraries or libraries which are free for research but may not be used in commercial scenarios. Please be aware of the third party licenses and respect them.

Specifically, the runnable JAR contains packages licensed under the GNU General Public License version 3.


#############################################
2. CITATION
#############################################

If you use this software, please cite:

Annemarie Friedrich and Manfred Pinkal: Discourse-sensitive Automatic Identification of Generic Expressions. August 2015. In Proceedings of the 53rd Annual Meeting of the Association for Computational Linguistics (ACL). Beijing, China.

Please also be aware that part of these features are re-implemented from:
Nils Reiter and Anette Frank: Identifying generic noun phrases. ACL 2010.

and from:
Sharid Loaiciga, Thomas Meyer, and Andrei Popescu-Belis. English-French Verb Phrase Alignment in Europarl for Tense Translation Modeling. LREC 2014. Reykjavik, Iceland.



#############################################
3. HOWTO
############################################# 

-----------------------------------------
3.1 Additional required resources.
-----------------------------------------

This software makes us of several resources that are either publically available (e.g. WordNet) or that need to be obtained from LDC.
 
Countability resource: In the paper, we used countability information extracted directly from Celex2 (https://catalog.ldc.upenn.edu/LDC96L14. The countability values are 'Y' and 'N'.

The WebCelex -- database is a freely available (open source) version, see: https://www.cbs.mpg.de/lib/Datenbanken http://celex.mpi.nl https://wiki.hmdc.harvard.edu/LPLab/Main/UsingCelex

The provided file webcelex_countabilityNouns.txt was extracted from the WebCelex database.

You also need a copy of WordNet 3.0. It is sufficient to download the 'database only' version from https://wordnet.princeton.edu/wordnet/download/current-version/#nix. Unzip this file and make sure your the parameter points to this directory.


-----------------------------------------
3.2 Using this code.
-----------------------------------------

The github code is a Maven eclipse project. For easy use, a Runnable jar file has been compiled and can be downloaded separately. You can run the code as follows:

java -jar sitentSyntSemFeatureExtractionv1.0.jar -input resources/examples/input -countability resources/countability/webcelex_countabilityNouns.txt -wordnet my-resources/wordnet3.0 -csvOutput csv-output

This will create the CSV output (described below) in the folder csv-output. Be a little patient while running this -- it needs to parse your data.

The feature extraction requires preprocessing using the Stanford Parser (collapsed and propagated dependencies). Examples can be found in the package sitent.syntSemFeatures.examples.

Take a look at FeatureExtractionPipeline.java. You can execute the feature extraction pipeline as follows:
java -jar sitent.syntSemFeatures.FeatureExtractionPipeline <parameters>

The parameters are:
 -countability <arg>   Path to file with countability information
                       (optional).
 -csvOutput <arg>      Path to folder for CSV output (standoff format,
                       optional).
 -input <arg>          Path to directories with input XMIs/texts,
                       separated by commas (required).
 -wordnet <arg>        Path to WordNet database (required).
 -xmiOutput <arg>      Output path for XMI (optional).

We recommend giving both the csvOutput and xmiOutput parameters for getting started.

The software will create one file ending in "_np.csv" and one file ending in "_verb.csv" for the extracted NP-based and verb-based features respectively. All fields are quoted using double-quotes, and columns are separated using tabs. The fields "begin" and "end" indicate the character offset within the documents of the span to which the respective feature belongs.

More conveniently, you can take a look at the annotations using the UIMA annotation viewer (see "Tools" section of https://uima.apache.org/documentation.html). If you're not familiar with UIMA, I simply recommend that you deselect all annotations and select only the ClassificationAnnotation. This annotation has an attribute "features" which contains the extracted features as name-value pairs.

If you want to use the package in your own code, and you are familiar with UIMA, here's some more information. The relevant features created by this software are of the following UIMA types:

- VerbFeatures: this captures the information about tense and voice, and is used by the other components.
- ClassificationAnnotation: this captures the span for which NP- or verb-based features are extracted. The attribute "task" indicates "NP" or "VERB" respectively.
- SEFeature: this type is a name-value combination for one feature.

Each ClassificationAnnotation has a field called "features" which contains an FSList with SEFeatures extracted for the span. If you need to iterate over this in your code, you can create a simple list from this FSList using SitEntUimaUtils.getList(classAnnot.getFeatures()).

If you want to extract features for clauses, you need to give your own ClauseAnnotations. (In our project, we used SPADE (http://www.isi.edu/licensed-sw/spade/) to create those annotations.)


#############################################
4. Feature set
#############################################

In this section, we describe the set of features that you can extract using this toolkit.
 
 ----------------------------------------
 - 4.1 Noun Phrase (NP) features		-
 ----------------------------------------
 - barePlural:        whether the NP is a bare plural or not.
 - countability:      from WebCelex.
 - depRel0-4:         dependency relations between target and head, head and the head's head etc.
 - determinerType:    def, indef, none
 - mentionLemma:      the head's lemma
 - mentionPOS:        the head's part-of-speech tag
 - nounType:          proper, common, pronoun, unknown
 - number:            sg/pl
 - person:            1/2/3
 - sense0-3:          synset ID of the lemma's sense in WordNet (most frequent sense, no WSD), and synset IDs of direct hypernym, hypernym of hypernym etc.
 - senseTop:          synset ID of top sense in WordNet
 - wnGranularity:     length of path to top node in WordNet
 - wnLexicalFilename: lexical filename in WordNet, e.g. noun.location, noun.person etc.
 
 ------------------------------------------
 - 4.2 Clause-based (verb-based) features -
 ------------------------------------------
 (The clause is here defined as the phrase headed by the verb.)
 - clauseAdverbDegree: if adverbial is present: positive, comparative etc
 - clauseAdverbPred:  lemma of the head of the adverbial clause, it one is present.
 - clauseHasTmod:     whether the clause has a temporal modifier.
 - clauseNumMod:      number of modifiers the clause has.
 - depRel0-4:         dependency relations between target and head, head and the head's head etc.
 - predicate_lemma:   lemma of the verb's head
 - predicate_pos:     part-of-speech of verb's head
 - predicate_sense-3: synset ID of the lemma's sense in WordNet (most frequent sense, no WSD), and synset IDs of direct hypernym, hypernym of hypernym etc.
 - predicate_senseTop:          synset ID of top sense in WordNet
 - predicate_wnGranularity:     length of path to top node in WordNet
 - predicate_wnLexicalFilename: lexical filename in WordNet, e.g. noun.location, noun.person etc.
 - verb_coarseTense:  present, past, future
 - verb_lemma:        same as predicate_lemma
 - verb_perfect:      whether verb is in perfect
 - verb_pos:          part-of-speech of verb's head
 - verb_progressive:  whether verb is in progressive
 - verb_tense:        pres_pref, sim_past etc.
 - verb_voice:        active/passive
 
