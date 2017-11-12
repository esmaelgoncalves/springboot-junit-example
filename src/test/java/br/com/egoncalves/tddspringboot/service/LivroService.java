package br.com.egoncalves.tddspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.egoncalves.tddspringboot.model.Livro;
import br.com.egoncalves.tddspringboot.repository.LivroRepository;

@Service
public class LivroService {

	private LivroRepository livroRepository;
	
	@Autowired
	public LivroService(LivroRepository livroRepository) {
		this.livroRepository = livroRepository; 
	}

	
	public Livro novoLivro(Livro livro) {
		if(StringUtils.isEmpty(livro.getNome())){
			throw new IllegalArgumentException();
		}
		return livroRepository.save(livro);
	}
}
