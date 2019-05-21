package com.additude.dao;

import java.util.List;

import com.additude.model.Word;

public interface WordRepository {
	
	List<Word> getWords();
	
	void addSynonyms(Iterable<String> synonyms);

}
