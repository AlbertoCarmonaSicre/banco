package com.babelgroup.banco.controllers.cliente;

import org.springframework.ui.Model;
import com.babelgroup.banco.models.Sucursal;
import org.springframework.web.bind.annotation.RequestParam;

public interface ClienteController {
    String listarClientes(Model model);

    String crearCliente(
            @RequestParam String DNI,
            @RequestParam String nombre,
            @RequestParam String direccionPostal,
            @RequestParam String email,
            @RequestParam String telefono,
            @RequestParam Sucursal sucursal,
            Model model
    );

    String actualizarCliente(
            @RequestParam Integer id,
            @RequestParam String DNI,
            @RequestParam String nombre,
            @RequestParam String direccionPostal,
            @RequestParam String email,
            @RequestParam String telefono,
            @RequestParam Sucursal sucursal,
            Model model
    );

    String eliminarCliente(@RequestParam Integer id, Model model);

    String buscarCliente(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String DNI,
            @RequestParam(required = false) String email,
            Model model
    );
}
