package org.ieselcaminas.jpa.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
// Se le puede indicar que el nombre de la tabla no es "cliente", nombre por defecto que es // igual al nombre la entidad (Cliente), sino "clientes"
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    // Siempre un constructor por defecto
    public Cliente() {
    }
    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return this.nombre;
    }

    /**
     * IMPORTANTE fetch = FetchType.EAGER. En caso contrario es FetchType.LAZY y da
     * error porque no se inicializa la lista de cuentas
     */
    @ManyToMany(fetch = FetchType.EAGER)
    /*
       En este caso le decimos:
       - que la tabla se llama cliente_cuenta
       - que mi columna id se llama en esa tabla cliente_id
       - que la columna de la cuenta se llama cuenta_id en dicha tabla
     */
    @JoinTable(
            name = "cliente_cuenta",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "cuenta_id")

    )
    // `cuentas` es el nombre que tiene el atributo `mappedBy` de
    // la relación `ManyToMany` en `CuentaCorriente`
    // Inicializar siempre el Set
    private Set<CuentaCorriente> cuentas = new HashSet<>();

    public Set<CuentaCorriente> getCuentas() {
        return this.cuentas;
    }

    public void setCuentas(Set<CuentaCorriente> cuentas) {
        this.cuentas = cuentas;
    }

    // Se puede crear un método para añadir la entidad relacionada a la lista.
    // En otro caso, habría que usar cliente.getCuentas().add(cuenta)
    public void addCuenta(CuentaCorriente cuenta) {
        this.cuentas.add(cuenta);
    }

}

