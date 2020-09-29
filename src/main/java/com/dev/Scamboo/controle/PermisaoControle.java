package com.dev.Scamboo.controle;

import com.dev.Scamboo.modelos.Papel;
import com.dev.Scamboo.modelos.Permissao;
import com.dev.Scamboo.repositorios.FuncionarioRepositorio;
import com.dev.Scamboo.repositorios.PapelRepositorio;
import com.dev.Scamboo.repositorios.PermissaoRepositorio;
import com.dev.Scamboo.repositorios.EstadoRepositorio;
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
@RequestMapping("/administrativo/permissoes")
public class PermisaoControle {

	@Autowired
	private PermissaoRepositorio permissaoRepositorio;

	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;

	@Autowired
	private PapelRepositorio papelRepositorio;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Permissao permissao) {
		ModelAndView mv = new ModelAndView("administrativo/permissoes/cadastro");
		mv.addObject("permissao",permissao);
		mv.addObject("listaFuncionarios",funcionarioRepositorio.findAll());
		mv.addObject("listaPapeis", papelRepositorio.findAll());
		return mv;
	}

	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/permissoes/lista");
		mv.addObject("listaPermissoes", permissaoRepositorio.findAll());


		return mv;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id){
		Optional<Permissao> permissao = permissaoRepositorio.findById(id);
		return cadastrar(permissao.get());
	}

	@GetMapping("/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id){
		Optional<Permissao> permissao = permissaoRepositorio.findById(id);
		permissaoRepositorio.delete(permissao.get());
		return listar();
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Permissao permissao, BindingResult result){
		if(result.hasErrors()){
			return cadastrar(permissao);
		}
		permissaoRepositorio.saveAndFlush(permissao);
		return cadastrar(new Permissao());

	}

}
