package com.additude.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WordRepositoryImpl implements WordRepository{
	
	private NamedParameterJdbcTemplate jdbc;
	
	public WordRepositoryImpl(final NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	private static final String INSERT_WORDS_SQL = "TODO TODO";
	private static final String SELECT_NAME_SQL = "SELECT name FROM word";

	@Override
	public Iterable<String> getWords() {
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> rows = jdbc.getJdbcTemplate().queryForList(SELECT_NAME_SQL);
		for (Map<String, Object> row : rows) {
			list.add((String) row.get("name"));
		}
		return (Iterable<String>) list;
	}

	@Override
	public void addSynonyms(Iterable<String> synonyms) {
		// TODO Auto-generated method stub
	}
}
