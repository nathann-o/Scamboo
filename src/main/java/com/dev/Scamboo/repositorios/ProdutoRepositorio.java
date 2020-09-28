package com.dev.Scamboo.repositorios;

import com.dev.Scamboo.modelos.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

}
