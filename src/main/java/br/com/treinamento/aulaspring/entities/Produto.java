package br.com.treinamento.aulaspring.entities;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Table(name="produto")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idproduto")
	private Integer IdProduto;
	
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;
	
	@Column(name = "preco", nullable = false)
	private Double preco;
	
	@Column(name = "quantidade",  nullable = false)
	private Integer quantidade;
	
	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;
	

}
