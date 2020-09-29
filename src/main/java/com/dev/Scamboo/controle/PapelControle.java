package com.dev.Scamboo.controle;

import com.dev.Scamboo.modelos.Papel;
import com.dev.Scamboo.repositorios.PapelRepositorio;
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
@RequestMapping("/administrativo/papeis")
public class PapelControle {

	@Autowired
	private PapelRepositorio papelRepositorio;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Papel papel) {
		ModelAndView mv = new ModelAndView("administrativo/papeis/cadastro");
		mv.addObject("papel",papel);
		return mv;
	}

	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/papeis/lista");
		mv.addObject("listaPapeis", papelRepositorio.findAll());


		return mv;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id){
		Optional<Papel> papel = papelRepositorio.findById(id);
		return cadastrar(papel.get());
	}

	@GetMapping("/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id){
		Optional<Papel> papel = papelRepositorio.findById(id);
		papelRepositorio.delete(papel.get());
		return listar();
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Papel papel, BindingResult result){
		if(result.hasErrors()){
			return cadastrar(papel);
		}
		papelRepositorio.saveAndFlush(papel);
		return cadastrar(new Papel());

	}

}
