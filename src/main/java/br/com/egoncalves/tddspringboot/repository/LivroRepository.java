package br.com.egoncalves.tddspringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.egoncalves.tddspringboot.model.Livro;


public interface LivroRepository extends JpaRepository<Livro, Long>{
	
	Optional<Livro> findByNome(String nome);

}
