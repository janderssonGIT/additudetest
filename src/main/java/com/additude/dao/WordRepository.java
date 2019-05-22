package com.additude.dao;

public interface WordRepository {
	
	Iterable<String> getWords();
	
	void addSynonyms(Iterable<String> synonyms);

}
