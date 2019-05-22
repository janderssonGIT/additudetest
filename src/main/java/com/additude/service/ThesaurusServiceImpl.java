package com.additude.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.additude.dao.WordRepository;

@Service
@Transactional
public class ThesaurusServiceImpl implements ThesaurusService {
	
	@Autowired
	private WordRepository wordRepository;

	@Override
	public void addSynonyms(Iterable<String> synonyms) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<String> getSynonyms(String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<String> getWords() {
		return this.wordRepository.getWords();
	}
}
