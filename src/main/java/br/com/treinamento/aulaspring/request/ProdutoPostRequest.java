package br.com.treinamento.aulaspring.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPostRequest {

	private String nome;
	private Double preco;
	private Integer quantidade;
	private String descricao;
}
