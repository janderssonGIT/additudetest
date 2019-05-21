package com.additude.dao;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.additude.model.Word;

@Repository
public class WordRepositoryImpl implements WordRepository{
	
	private NamedParameterJdbcTemplate jdbc;
	
	public WordRepositoryImpl(final NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	private static final String INSERT_SQL = "TODO TODO";
	private static final String SELECT_ALL_SQL = "SELECT name FROM Word w";

	@Override
	public List<Word> getWords() {
		RowMapper<Word> rowMapper = new WordRowMapper();
		List<Word> rows =  jdbc.query(SELECT_ALL_SQL, rowMapper);
		return rows;
	}

	@Override
	public void addSynonyms(Iterable<String> synonyms) {
		// TODO Auto-generated method stub
	}
}
