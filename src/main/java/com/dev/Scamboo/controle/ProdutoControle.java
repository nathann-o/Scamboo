package com.dev.Scamboo.controle;

import com.dev.Scamboo.modelos.Cidade;
import com.dev.Scamboo.modelos.Estado;
import com.dev.Scamboo.modelos.Produto;
import com.dev.Scamboo.repositorios.CidadeRepositorio;
import com.dev.Scamboo.repositorios.EstadoRepositorio;
import com.dev.Scamboo.repositorios.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/administrativo/produtos")
public class ProdutoControle {

	@Autowired
	private ProdutoRepositorio produtoRepositorio;


	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Produto produto) {
		ModelAndView mv = new ModelAndView("administrativo/produtos/cadastro");
		mv.addObject("produto",produto);
		return mv;
	}

	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/produtos/lista");
		mv.addObject("listaProdutos", produtoRepositorio.findAll());


		return mv;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id){
		Optional<Produto> produto = produtoRepositorio.findById(id);
		return cadastrar(produto.get());
	}

	@GetMapping("/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id){
		Optional<Produto> produto = produtoRepositorio.findById(id);
		produtoRepositorio.delete(produto.get());
		return listar();
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Produto produto, BindingResult result){
		if(result.hasErrors()){
			return cadastrar(produto);
		}
		produtoRepositorio.saveAndFlush(produto);
		return cadastrar(new Produto());

	}

}
