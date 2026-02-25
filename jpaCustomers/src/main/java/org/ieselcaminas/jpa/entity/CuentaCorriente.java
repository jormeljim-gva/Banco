package org.ieselcaminas.jpa.entity;

import jakarta.persistence.*;
import org.ieselcaminas.jpa.entity.Cliente;
import java.util.HashSet;
import java.util.Set;

@Entity
// Se le puede indicar que el nombre de la tabla no es "cuenta_corriente", nombre por
// defecto que es igual al nombre la entidad (CuentaCorriente), sino "cuentas_corrientes"
@Table(name = "cuentas_corrientes")
public class CuentaCorriente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private double cantidad;

    //Siempre debe tener un constructor vacío
    public CuentaCorriente() {
    }

    //Se pueden hacer tantos constructores como deseemos
    public CuentaCorriente(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Le decimos que, en la entidad Cliente, las cuentas se almacenan en el atributo con nombre cuentas
    @ManyToMany(mappedBy = "cuentas", fetch = FetchType.EAGER)
    //Hay que inicializar la lista siempre
    private Set<Cliente> clientes = new HashSet<>();

    public Set<Cliente> getClientes() {
        return this.clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    // Se puede crear un método para añadir la entidad relacionada a la lista.
    // En otro caso, habría que usar cuenta.getClientes().add(cliente)
    public void addCliente(Cliente cliente){
        this.clientes.add(cliente);
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    // Otros setters
    public void ingresar(double cantidad){
        if (cantidad > 0 )
            this.cantidad += cantidad;
    }

    public void retirar(double cantidad){
        if (cantidad > 0 )
            this.cantidad -= cantidad;
    }

    @Override
    public String toString(){
        return this.nombre + " - " + this.cantidad;
    }


}
