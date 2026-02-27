package org.ieselcaminas.jpa;

import jakarta.transaction.Transactional;
import org.ieselcaminas.jpa.entity.CuentaCorriente;
import org.ieselcaminas.jpa.repository.ClienteRepository;
import org.ieselcaminas.jpa.repository.CuentaCorrienteRepository;
import org.ieselcaminas.jpa.service.ClienteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {
    private final ClienteService clienteService;
    private final CuentaCorrienteRepository cuentaCorrienteRepository;
    private final ClienteRepository clienteRepository;
    public JpaApplication(ClienteService clienteService, CuentaCorrienteRepository cuentaCorrienteRepository, ClienteRepository clienteRepository) {
        this.clienteService = clienteService;
        this.cuentaCorrienteRepository = cuentaCorrienteRepository;
        this.clienteRepository = clienteRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    //En este método definimos nuestro propio código
    @Transactional
    @Override
    public void run(String... args) {
        this.clienteService.crearCuentaConClientes("CuentaConMásDeUnCliente");
    }

}
