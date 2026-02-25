package org.ieselcaminas.jpa.repository;

import org.ieselcaminas.jpa.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
