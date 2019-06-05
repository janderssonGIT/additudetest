package com.additude.dao;

import java.util.List;

public interface WordRepository {
	
	void addSynonyms(List<String> synonyms);

	List<String> getSynonyms(String word);
	
	List<String> getWords();
	
}
