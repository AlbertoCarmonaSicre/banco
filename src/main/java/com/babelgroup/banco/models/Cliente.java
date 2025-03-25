package com.babelgroup.banco.models;

public class Cliente {

    private Integer id;
    private String DNI;
    private String nombre;
    private String direccionPostal;
    private String email;
    private String telefono;
    private Sucursal sucursal;

    public Cliente(Integer id, String DNI, String nombre, String direccionPostal, String email, String telefono, Sucursal sucursal) {
        this.id = id;
        this.DNI = DNI;
        this.nombre = nombre;
        this.direccionPostal = direccionPostal;
        this.email = email;
        this.telefono = telefono;
        this.sucursal = sucursal;
    }

    public Cliente() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccionPostal() {
        return direccionPostal;
    }

    public void setDireccionPostal(String direccionPostal) {
        this.direccionPostal = direccionPostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
