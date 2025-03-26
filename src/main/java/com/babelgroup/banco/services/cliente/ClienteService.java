package com.babelgroup.banco.services.cliente;

import com.babelgroup.banco.models.Cliente;
import com.babelgroup.banco.models.Sucursal;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    void create(String DNI, String nombre, String direccionPostal, String email, String telefono, String sucursal);
    List<Cliente> getAllClients();
    void update(Integer id, String DNI, String nombre, String direccionPostal, String email, String telefono, String sucursal);
    void delete(Integer id);

    Optional<Cliente> getClientById(Integer id);
    Optional<Cliente> getClientByDNI(String DNI);
    Optional<Cliente> getClientByEmail(String email);
}
