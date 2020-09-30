package com.dev.Scamboo.controle;

import com.dev.Scamboo.modelos.Cidade;
import com.dev.Scamboo.repositorios.CidadeRepositorio;
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
public class NegadoControle {


	@GetMapping("/negado")
	public ModelAndView cadastrar(Cidade cidade) {
		ModelAndView mv = new ModelAndView("/negado");
		return mv;
	}


}
