package com.additude.service;

import java.util.List;

public interface ThesaurusService {
	
	/// Adds the given words as synonyms to each other
	void addSynonyms(List<String> synonyms);

	/// Gets the synonyms for a word
	List<String> getSynonyms(String word);

	/// Gets all words that are stored in the thesaurus
	List<String> getWords();

}
