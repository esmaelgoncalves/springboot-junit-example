package br.com.egoncalves.tddspringboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.egoncalves.tddspringboot.model.Livro;
import br.com.egoncalves.tddspringboot.repository.LivroRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LivroRespositoryTest {
	
	@Autowired
	private LivroRepository repository;
	
	@Test
	public void testNovoLivro() {
		Livro livro = repository.save(new Livro(null, "Novo Livro"));
		
		assertNotNull(livro);
		assertNotNull(livro.getCodigo());
	}
	
	@Test
	public void testFindByNome() {
		repository.save(new Livro(null, "EJB in Action"));
		Optional<Livro> livroSalvo = repository.findByNome("EJB in Action");
		assertEquals("EJB in Action", livroSalvo.get().getNome());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testFindByNomeNotExist() {
		Optional<Livro> livro = repository.findByNome("Nome de um livro inexistente");
		livro.get();
	}
	
}
