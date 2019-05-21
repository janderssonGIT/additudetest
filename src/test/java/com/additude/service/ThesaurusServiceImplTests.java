package com.additude.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.junit4.SpringRunner;

import com.additude.app.AddtestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddtestApplication.class)
public class ThesaurusServiceImplTests {
	
	@Autowired
	private ThesaurusService thesaurusService;
	
	@Autowired
	private JdbcTemplate jdbc;
	
	private static final String DATA = "scripts/data/data.sql";
	private static final String CREATE_TABLE = "scripts/table/schema.sql";
	private static final String DROP_TABLE = "scripts/table/schema.sql";
	
//	@BeforeEach
//	public void setup() {
//		List<Word> words = new ArrayList<>();
//		Word w1 = new Word(new Long(1), "Angry");
//		Word w2 = new Word(new Long(1), "Mad");
//		Word w3 = new Word(new Long(1), "Furious");
//		words.add(w1);
//		words.add(w2);
//		words.add(w3);
//		Mockito.when(wordRepository.getWords()).thenReturn(words);
//	}
	
	@BeforeEach
	public void setup() throws ScriptException, SQLException {
		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(CREATE_TABLE));
	}
	
	@AfterEach
	public void teardown() throws ScriptException, SQLException {
		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(DROP_TABLE));
	}
	
	@Test
	void testService_GetAllWords() {
		Iterable<String> wordCollection = this.thesaurusService.getWords();
		assertThat(wordCollection).hasSize(3);
	}

}
