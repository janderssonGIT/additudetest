package com.additude.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.additude.app.AddtestApplication;

@SpringBootTest(classes = AddtestApplication.class)
public class ThesaurusServiceImplTests {
	
	@Autowired
	private ThesaurusService thesaurusService;
	
	@Autowired
	private JdbcTemplate jdbc;
	
	private static final String INSERT_DATA = "scripts/data/data.sql";
	private static final String CREATE_TABLE = "scripts/table/schema.sql";
	private static final String DROP_TABLE = "scripts/table/drop.sql";
	
	@BeforeEach
	private void setup() throws ScriptException, SQLException {
		Connection connection = jdbc.getDataSource().getConnection();
		
		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(DROP_TABLE));
		ScriptUtils.executeSqlScript(connection, new ClassPathResource(CREATE_TABLE));
		ScriptUtils.executeSqlScript(connection, new ClassPathResource(INSERT_DATA));
		
		assertEquals(11, JdbcTestUtils.countRowsInTable(jdbc, "words"));
	}
	
//	@AfterEach
//	private void teardown() throws ScriptException, SQLException {
//		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(DROP_TABLE));
//	}
	
	@Test
	public void testService_getWords() throws Exception {
		List<String> wordCollection = thesaurusService.getWords();
		assertThat(wordCollection).hasSize(11);
	}
	
	@Test
	public void testService_getSynonyms() throws Exception {
		List<String> synonyms = thesaurusService.getSynonyms("Angry");
		assertThat(synonyms).hasSize(4);
	}
	
	@Test
	public void testService_addSynonyms() throws Exception {
		printTableInConsole("\nTest: - (testService_addSynonyms)\nTABLE 'words'\n");
		
		List<String> addTheseWords = Arrays.asList("House", "Mansion", "Crib", "Flat");
		thesaurusService.addSynonyms(addTheseWords);
		assertEquals(15, JdbcTestUtils.countRowsInTable(jdbc, "words"));
		
		printTableInConsole("\n\nTest: - (testService_addSynonyms) After:\nTABLE 'words'\n");
	}
	
	@Test
 	public void testService_addSynonymsMultiThreaded() throws InterruptedException {
		List<String> addTheseWords = Arrays.asList( "SynonomousString_1", "SynonomousString__2", 
				"SynonomousString___3", "SynonomousString____4", "SynonomousString_____5");
 		
 		runMultithreaded( new Runnable() {
            public void run() {
                try {
                	thesaurusService.addSynonyms(addTheseWords);                    
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }, 2);
 		printTableInConsole("\nTest: - (testService_addSynonymsMultiThread)\nTABLE 'words'\n");
  	}
	
	private void printTableInConsole(String header) {
		System.out.print(header);
		List<Map<String, Object>> rows = jdbc.queryForList("SELECT * FROM words");
        rows.forEach(System.out::println);
	}
	
	private static void runMultithreaded(Runnable  runnable, int threadCount) throws InterruptedException {
		List<Thread>  threadList = new LinkedList<Thread>();
		
		for(int i = 0 ; i < threadCount; i++) {
			threadList.add(new Thread(runnable));
		}
		
		for( Thread t :  threadList) {
			t.start();
		}
		
		for( Thread t :  threadList) {
			t.join();
		}
	}
}
