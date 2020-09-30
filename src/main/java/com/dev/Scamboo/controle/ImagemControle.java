package com.dev.Scamboo.controle;

import com.dev.Scamboo.modelos.Produto;
import com.dev.Scamboo.repositorios.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


@Controller
public class ImagemControle {

	private static String caminhoImagens = "imagens/";


	@GetMapping("/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(caminhoImagens+imagem);
		if(imagem!=null || imagem.trim().length() > 0){
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}

}
