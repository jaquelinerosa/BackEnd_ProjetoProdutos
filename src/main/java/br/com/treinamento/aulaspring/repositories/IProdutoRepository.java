package br.com.treinamento.aulaspring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.treinamento.aulaspring.entities.Produto;

@Repository
public interface IProdutoRepository extends CrudRepository<Produto,Integer>{

}
