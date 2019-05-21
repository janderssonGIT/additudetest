package com.additude.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.additude.model.Word;

@Repository
public interface WordRepositoryOld extends JpaRepository<Word, Integer>{
	
	@Query("SELECT name FROM Word w")
	List<Word> getWords();
}
