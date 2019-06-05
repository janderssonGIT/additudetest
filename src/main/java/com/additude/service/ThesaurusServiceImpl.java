package com.additude.service;

import java.util.List;

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
	public void addSynonyms(List<String> synonyms) {
		this.wordRepository.addSynonyms(synonyms);
	}

	@Override
	public List<String> getSynonyms(String word) {
		return this.wordRepository.getSynonyms(word);
	}

	@Override
	public List<String> getWords() {
		return this.wordRepository.getWords();
	}
	
}
