package com.dev.Scamboo.controle;

import com.dev.Scamboo.modelos.Cidade;
import com.dev.Scamboo.modelos.Cliente;
import com.dev.Scamboo.repositorios.CidadeRepositorio;
import com.dev.Scamboo.repositorios.ClienteRepositorio;
import com.dev.Scamboo.repositorios.EstadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/cliente")
public class ClienteControle {

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@Autowired
	private CidadeRepositorio cidadeRepositorio;



	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/cadastrar");
		mv.addObject("cliente",cliente);
		mv.addObject("listaCidades",cidadeRepositorio.findAll());
		return mv;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id){
		Optional<Cliente> cliente = clienteRepositorio.findById(id);
		return cadastrar(cliente.get());
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result){
		if(result.hasErrors()){
			return cadastrar(cliente);
		}
		cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
		clienteRepositorio.saveAndFlush(cliente);
		return cadastrar(new Cliente());

	}

}
