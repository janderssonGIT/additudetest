package com.additude.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.additude.app.AddtestApplication;
import com.additude.model.Word;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddtestApplication.class)
class ThesaurusRepositoryIntegrationTests {

	@Autowired
	WordRepositoryOld wordRepository;
	
//	@BeforeEach
//	public void setup() {
//		List<String> words = new ArrayList<>(Arrays.asList("Angry", "Mad", "Furious"));
//		for (int i = 0; i < words.size(); i++) {
//			Word word = new Word(new Long(1) ,words.get(i));
//			entityManager.persist(word);
//		}
//		entityManager.flush();
//	}
	
	@Test
	void testRepository_GetAllWords() {
		List<Word> wordCollection = this.wordRepository.getWords();
		Iterable<String> results = wordCollection.stream().map(x -> x.getName()).collect(Collectors.toList());
		assertThat(results).hasSize(3);
	}
}
