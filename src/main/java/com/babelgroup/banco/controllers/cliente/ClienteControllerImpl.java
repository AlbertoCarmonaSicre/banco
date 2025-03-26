package com.babelgroup.banco.controllers.cliente;

import com.babelgroup.banco.models.Cliente;
import com.babelgroup.banco.models.Sucursal;
import com.babelgroup.banco.services.cliente.ClienteService;
import com.babelgroup.banco.services.sucursal.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteControllerImpl implements ClienteController{

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.getAllClients();
        model.addAttribute("listadoClientes", clientes);
        return "clientes-vista";
    }

    @PostMapping
    public String crearCliente(
            @RequestParam String DNI,
            @RequestParam String nombre,
            @RequestParam String direccionPostal,
            @RequestParam String email,
            @RequestParam String telefono,
            @RequestParam String sucursal,
            Model model
    ) {
        try {
            clienteService.create(DNI, nombre, direccionPostal, email, telefono, sucursal);
            model.addAttribute("notificacion", "Cliente creado correctamente");
        } catch (NoSuchElementException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("listadoClientes", clienteService.getAllClients());
        return "clientes-vista";
    }

    @PostMapping("/actualizar")
    public String actualizarCliente(
            @RequestParam Integer id,
            @RequestParam String DNI,
            @RequestParam String nombre,
            @RequestParam String direccionPostal,
            @RequestParam String email,
            @RequestParam String telefono,
            @RequestParam String sucursal,
            Model model
    ) {
        try {
            clienteService.update(id, DNI, nombre, direccionPostal, email, telefono, sucursal);
            model.addAttribute("notificacion", "Cliente actualizado correctamente");
        } catch (NoSuchElementException e) {
            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("listadoClientes", clienteService.getAllClients());
        return "clientes-vista";
    }

    @PostMapping("/eliminar")
    public String eliminarCliente(@RequestParam Integer id, Model model) {
        try {
            clienteService.delete(id);
            model.addAttribute("notificacion", "Cliente eliminado correctamente");
        } catch (NoSuchElementException e) {
            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("listadoClientes", clienteService.getAllClients());
        return "clientes-vista";
    }

    @GetMapping("/buscar")
    public String buscarCliente(@RequestParam(required = false) Integer id,
                                @RequestParam(required = false) String DNI,
                                @RequestParam(required = false) String email,
                                Model model) {

        Optional<Cliente> cliente = Optional.empty();

        if (id != null) {
            cliente = clienteService.getClientById(id);
        } else if (DNI != null) {
            cliente = clienteService.getClientByDNI(DNI);
        } else if (email != null) {
            cliente = clienteService.getClientByEmail(email);
        }

        if (cliente.isPresent()) {
            model.addAttribute("clienteBuscado", cliente.get());
        } else {
            model.addAttribute("error", "Cliente no encontrado");
        }

        model.addAttribute("listadoClientes", clienteService.getAllClients());
        return "clientes-vista";
    }
}
