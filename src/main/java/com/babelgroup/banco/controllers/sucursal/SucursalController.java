package com.babelgroup.banco.controllers.sucursal;

import org.springframework.ui.Model;

public interface SucursalController {

    public String findAll(Model model);

    public String create(String nombre, String direccion, String director, Model model);

    public String update(Integer id, String nombre, String direccion, String director, Model model);

    public String delete(Integer id, Model model);

}
