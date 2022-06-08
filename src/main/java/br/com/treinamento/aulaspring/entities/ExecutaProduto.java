package br.com.treinamento.aulaspring.entities;

public class ExecutaProduto {

	public static void main(String[] args) {
	 Produto p = new Produto();
	 p.setIdProduto(100);
	 p.setNome("Computador");
	 p.setPreco(5000.);
	 p.setDescricao("Computador de mesa ssd 256gb");
	 
	 System.out.println(p);
	}

}
