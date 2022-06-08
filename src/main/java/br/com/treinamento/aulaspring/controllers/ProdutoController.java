package br.com.treinamento.aulaspring.controllers;



import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.treinamento.aulaspring.entities.Produto;
import br.com.treinamento.aulaspring.repositories.IProdutoRepository;
import br.com.treinamento.aulaspring.request.ProdutoGetResponse;
import br.com.treinamento.aulaspring.request.ProdutoPostRequest;
import br.com.treinamento.aulaspring.request.ProdutoPutRequest;
import io.swagger.annotations.ApiOperation;
/*Annotation que define o esteriotipo de uma classe para 
 * funcionar dentro do projeto Spring como um controlador*/
@Controller
@Transactional

public class ProdutoController {

	@Autowired
	private IProdutoRepository produtosRepository;
	
	//definido o endereço do serviço
	private static final String ENDPOINT = "/api/produtos";
	
	//método para realizar o serviço de cadastro de produto
	@CrossOrigin
	@ApiOperation("Servico para cadastro de Produto")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	public ResponseEntity<String>post(@RequestBody ProdutoPostRequest request){
		try {
			Produto p = new Produto();
			p.setNome(request.getNome());
			p.setPreco(request.getPreco());
			p.setQuantidade(request.getQuantidade());
			p.setDescricao(request.getDescricao());
			
			produtosRepository.save(p);
			
			return ResponseEntity.status(HttpStatus.OK).body("Produto cadastrado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO: "+e.getMessage());
		}
	}
	
	//método para realizar o serviço de edição de produto
	@CrossOrigin
	@ApiOperation("Servico para atualizacao de Produto")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	public ResponseEntity<String>put(@RequestBody ProdutoPutRequest request){
		try {
			//consultar o produto no banco de  dados através do ID
			Optional<Produto> item = produtosRepository.findById(request.getIdProduto());
			
			//verifica se o produto não foi encontrado
			if(item.isEmpty()){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Produto não encontrado, por favor verifique o código digitado");
			}else {
				/*método de salvar... cria uma instância do objeto do tipo
				 * produtoe recebe as informações*/
				Produto p = item.get();
				p.setNome(request.getNome());
				p.setPreco(request.getPreco());
				p.setQuantidade(request.getQuantidade());
				p.setDescricao(request.getDescricao());
				
				produtosRepository.save(p);
				return ResponseEntity.status(HttpStatus.OK)
						.body("Produto atualizado com sucesso");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("ERRO: "+e.getMessage());
		}
	}
	//método para realizar o serviço de exclusão de produto
	@CrossOrigin
	@ApiOperation("Servico para exclusão de Produto")
	@RequestMapping(value = ENDPOINT + "/{idProduto}", method = RequestMethod.DELETE)
	public ResponseEntity<String>delete(@PathVariable("idProduto")Integer idProduto){
		try {
			//consultar o produto no banco de dados através do ID
			Optional<Produto> item = produtosRepository.findById(idProduto);
			//verificar se o produto não foi encontrado 
			if(item.isEmpty()){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Produto não encontrado, por favor verifique o código digitado");
			}else {
				Produto prod = item.get();
				produtosRepository.delete(prod);
				return ResponseEntity.status(HttpStatus.OK)
						.body("Produto excluído com sucesso!!!");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("ERROR: "+e.getMessage());
		}
	}
	//método para realizar o serviço de consulta de produto
	@CrossOrigin
	@ApiOperation("Servico para consulta de Produto")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	public ResponseEntity<List<ProdutoGetResponse>>get(){
		//lista vaziapara receber produtos vindo do banco
		List<ProdutoGetResponse> response = new ArrayList<ProdutoGetResponse>();
		//enquanto existir produtos. . . .
		for(Produto p: produtosRepository.findAll()) {
			//objeto item instancia na memória
			ProdutoGetResponse item = new ProdutoGetResponse();
			//elemento de produto sendo "colocado" em item
			item.setIdProduto(p.getIdProduto());
			item.setNome(p.getNome());
			item.setDescricao(p.getDescricao());
			item.setPreco(p.getPreco());
			item.setQuantidade(p.getQuantidade());
			item.setTotal(p.getPreco()*p.getQuantidade());
			response.add(item);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	//método para consultar 1 produto baseado por ID
		@CrossOrigin
		@ApiOperation("Servico para busca de Produto por id")
		@RequestMapping(value = ENDPOINT +"/{idProduto}", method = RequestMethod.GET)
		public ResponseEntity<ProdutoGetResponse> getById(@PathVariable("idProduto")Integer idProduto){
			//consultar o produto no banco de dados através do id
			Optional<Produto> item = produtosRepository.findById(idProduto);
			
			//verificar se o produto não foi encontrado
			if(item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}else {
				ProdutoGetResponse response = new ProdutoGetResponse();
				Produto p = item.get();
				
				response.setIdProduto(p.getIdProduto());
				response.setNome(p.getNome());
				response.setDescricao(p.getDescricao());
				response.setPreco(p.getPreco());
				response.setQuantidade(p.getQuantidade());
				response.setTotal(p.getPreco()*p.getQuantidade());
				
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
		}
}