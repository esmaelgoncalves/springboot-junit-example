package br.com.egoncalves.tddspringboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.egoncalves.tddspringboot.model.Livro;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LivroResourceIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testBuscarLivro() {
		ResponseEntity<Livro> responseEntity = restTemplate.getForEntity("/livros/1", Livro.class);
		Livro livro = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Java Efetivo", livro.getNome());
	}

	@Test
	public void testBuscarLivroNotExist() {
		ResponseEntity<Livro> responseEntity = restTemplate.getForEntity("/livros/100", Livro.class);
		Livro livro = responseEntity.getBody();

		assertNull(livro);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	public void testNovoLivroSucesso() {
		ResponseEntity<Livro> responseEntity = restTemplate.postForEntity("/livros", new Livro(null, "Foo"),
				Livro.class);
		Livro livro = responseEntity.getBody();
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals("Foo", livro.getNome());
	}
	
	@Test
	public void testNovoLivroFalha() {
		ResponseEntity<Livro> responseEntity = restTemplate.postForEntity("/livros", new Livro(null, null),
				Livro.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

}
