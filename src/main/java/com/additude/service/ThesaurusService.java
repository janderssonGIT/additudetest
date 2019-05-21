package com.additude.service;

public interface ThesaurusService {
	
	/// Adds the given words as synonyms to each other
	void addSynonyms(Iterable<String> synonyms);

	/// Gets the synonyms for a word
	Iterable<String> getSynonyms(String word);

	/// Gets all words that are stored in the thesaurus
	Iterable<String> getWords();

}
