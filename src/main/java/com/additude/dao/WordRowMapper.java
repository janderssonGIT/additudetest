package com.additude.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.additude.model.Word;

public class WordRowMapper implements RowMapper<Word>{
	public Word mapRow(ResultSet row, int rowNum) throws SQLException {
		Word word = new Word(row.getLong("synid"), row.getString("name"));
		return word;
	}
}
