/**
 * 
 */
package br.com.egoncalves.tddspringboot.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.egoncalves.tddspringboot.model.Livro;
import br.com.egoncalves.tddspringboot.repository.LivroRepository;
import br.com.egoncalves.tddspringboot.service.LivroService;

/**
 * @author Esmael
 *
 */
@RestController
@RequestMapping("/livros")
public class LivroResource {

	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	LivroService livroService;

	@GetMapping
	public List<Livro> listarLivros() {
		return livroRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Livro> buscarLivro(@PathVariable Long codigo) {
		Livro livro =  livroRepository.findOne(codigo);
		return livro == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(livro);
	}

	@PostMapping
	public ResponseEntity<Livro> novoLivro(@RequestBody @Valid Livro livro, HttpServletResponse response) {
		Livro livroSalvo = livroService.novoLivro(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(livroSalvo.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return new ResponseEntity<Livro>(livroSalvo, HttpStatus.CREATED);
	}

}
