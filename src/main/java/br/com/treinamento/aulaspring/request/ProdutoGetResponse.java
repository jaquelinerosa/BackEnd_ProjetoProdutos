package br.com.treinamento.aulaspring.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoGetResponse {
	private Integer idProduto;
	private String nome;
	private String descricao;
	private Double preco;
	private Integer quantidade;
	private Double total;

}
