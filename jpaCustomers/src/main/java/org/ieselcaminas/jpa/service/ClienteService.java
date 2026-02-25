package org.ieselcaminas.jpa.service;

import jakarta.transaction.Transactional;
import org.ieselcaminas.jpa.entity.Cliente;
import org.ieselcaminas.jpa.entity.CuentaCorriente;
import org.ieselcaminas.jpa.repository.ClienteRepository;
import org.ieselcaminas.jpa.repository.CuentaCorrienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Se pueden hacer tantos servicios como deseemos, pero siempre hemos de crear métodos relacionados
 * en ellos.
 */
@Service
public class ClienteService {
    /*
    La anotación @Autowired indica a Spring que nos "auto inyecte" esta clase, en este caso ClienteRepository y
    CuentaCorrienteRepository.
    De esta forma ya no es necesario inyectarlas en el constructor
     */
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaCorrienteRepository cuentaCorrienteRepository;

    public void crearClienteConCuenta(String nombreCliente, String nombreCuenta) {
        Cliente cliente = new Cliente(nombreCliente);
        CuentaCorriente cuenta = new CuentaCorriente();
        cuenta.setNombre(nombreCuenta);
        cuenta.addCliente(cliente);
        cliente.addCuenta(cuenta);
        this.clienteRepository.save(cliente);
        this.cuentaCorrienteRepository.save(cuenta);
    }

    @Transactional
    public void crearCuentaConClientes(String nombreCuenta) {
        CuentaCorriente cuenta = new CuentaCorriente();
        cuenta.setNombre(nombreCuenta);
        cuenta.setCantidad(100);

        Cliente cliente1 = new Cliente("Cliente 1");
        Cliente cliente2 = new Cliente("Cliente 2");

        cliente1.getCuentas().add(cuenta); // o cliente1.addCuenta(cuenta);
        cuenta.getClientes().add(cliente1); // o cuenta.addCliente(cliente);

        cliente2.getCuentas().add(cuenta);
        cuenta.getClientes().add(cliente2);

        this.cuentaCorrienteRepository.save(cuenta);
        this.clienteRepository.save(cliente1);
        this.clienteRepository.save(cliente2);
    }

}
