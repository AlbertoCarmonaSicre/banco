package com.babelgroup.banco.models;

public class Sucursal {

    private Integer id;
    private String nombre;
    private String director;
    private String direccion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Sucursal(Integer id, String nombre, String direccion, String director) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.director = director;
    }

    public Sucursal(String s, String string, String s1){}

    @Override
    public String toString() {
        return "Sucursal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", director='" + director + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
