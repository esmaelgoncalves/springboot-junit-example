package br.com.egoncalves.tddspringboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.egoncalves.tddspringboot.model.Livro;
import br.com.egoncalves.tddspringboot.repository.LivroRepository;
import br.com.egoncalves.tddspringboot.service.LivroService;

public class LivroResourceTest {

	
	private LivroService livroService;
	private LivroRepository repository;
	
	@Before
	public void setUp() {
		repository = Mockito.mock(LivroRepository.class);
		livroService = new LivroService(repository);
	}

	@Test
	public void testNovoLivro() {
		when(repository.findByNome(eq("Novo Livro Teste"))).thenReturn(Optional.empty());
		doAnswer(new Answer<Livro>() {

			@Override
			public Livro answer(InvocationOnMock invocation) throws Throwable {
				//final Object originalArgument = (invocation.getArguments())[0];
				return new Livro(1L, "Novo Livro Teste");
			}
			
		}).when(repository).save(any(Livro.class));
		
		Livro livro = livroService.novoLivro(new Livro (null, "Novo Livro Teste"));
				
		assertEquals("Novo Livro Teste", livro.getNome());
		assertNotNull(livro.getCodigo());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNovoLivroNomeNull() {
		livroService.novoLivro(new Livro ());
	}

}
