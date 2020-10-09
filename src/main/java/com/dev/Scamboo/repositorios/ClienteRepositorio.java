package com.dev.Scamboo.repositorios;

import com.dev.Scamboo.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
