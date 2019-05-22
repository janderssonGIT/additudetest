package com.additude.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

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
import org.springframework.test.jdbc.JdbcTestUtils;

import com.additude.app.AddtestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddtestApplication.class)
public class ThesaurusServiceImplTests {
	
	@Autowired
	private ThesaurusService thesaurusService;
	
	@Autowired
	private JdbcTemplate jdbc;
	
	private static final String DATAEXTRA = "scripts/data/dataextra.sql";
	private static final String CREATE_TABLE = "scripts/table/schema.sql";
	//private static final String DROP_TABLE = "scripts/table/drop.sql";
	
	@BeforeEach
	public void setup() throws ScriptException, SQLException {
		Connection connection = jdbc.getDataSource().getConnection();
		
		ScriptUtils.executeSqlScript(connection, new ClassPathResource(CREATE_TABLE));
		ScriptUtils.executeSqlScript(connection, new ClassPathResource(DATAEXTRA));

		connection.commit();
		connection.close();
		
		assertEquals(12, JdbcTestUtils.countRowsInTable(jdbc, "word"));
	}
	
//	@AfterEach
//	public void teardown() throws ScriptException, SQLException {
//		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(DROP_TABLE));
//	}
	
	@Test
	void testService_GetAllWords() {
		Iterable<String> wordCollection = this.thesaurusService.getWords();
		assertThat(wordCollection).hasSize(12);
	}
}
