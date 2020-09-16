package com.dev.Scamboo.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrativo/funcionarios")
public class FuncionarioControle {
	
	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "administrativo/usuarios/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "administrativo/usuarios/lista";
	}

}
