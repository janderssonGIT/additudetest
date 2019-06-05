package com.additude.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import com.additude.model.Synonym;

@Repository
public class WordRepositoryImpl implements WordRepository{
	
	private NamedParameterJdbcTemplate jdbc;
	
	public WordRepositoryImpl(final NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	private static final String LOOKUP_CURRENT_MAX_SYNID = "SELECT MAX(synid) FROM words";
	private static final String INSERT_WORDS_SQL = "INSERT INTO words (id, synid, strval)" + 
			"VALUES (GENERATED_ID_SEQ.NEXTVAL, :synid, :strval)";
	private static final String SELECT_SYNONYMS_OF_WORD_SQL = "SELECT w.strval FROM words w, words m WHERE w.synid = m.synid AND m.strval = :strval";
	private static final String SELECT_ALL_WORDS_SQL = "SELECT strval FROM words";

	@Override
	public void addSynonyms(List<String> synonyms) {
		// Get current MAX value in column synId and increment by 10 for the new collection of synonyms.
		int synid = jdbc.getJdbcTemplate().queryForObject(LOOKUP_CURRENT_MAX_SYNID, new Object[] {}, Integer.class) + 10;
	
		List<Synonym> batchArgs = new ArrayList<>();
		for (String str : synonyms) {
			batchArgs.add(new Synonym(synid, str));
		}
		
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(batchArgs.toArray());
		jdbc.batchUpdate(INSERT_WORDS_SQL, batch);
	}
	
	@Override
	public List<String> getSynonyms(String word) {
		SqlParameterSource parameters = new MapSqlParameterSource("strval", word);
		List<String> synonyms = jdbc.queryForList(SELECT_SYNONYMS_OF_WORD_SQL, parameters)
				.stream()
				.map(map -> map.get("strval"))
				.map(object -> Objects.toString(object, null))
			    .collect(Collectors.toList());
		return synonyms;
	}

	@Override
	public List<String> getWords() {
		List<String> words = jdbc.getJdbcTemplate().queryForList(SELECT_ALL_WORDS_SQL)
				.stream()
				.map(map -> map.get("strval"))
				.map(object -> Objects.toString(object, null))
			    .collect(Collectors.toList());
		return words;
	}

}
