package com.babelgroup.banco.services.cliente;

import com.babelgroup.banco.models.Cliente;
import com.babelgroup.banco.models.Sucursal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    private static Integer contador = 0;
    private static List<Cliente> clientes;

    @Override
    public List<Cliente> getAllClients() {
        return clientes;
    }

    @Override
    public Optional<Cliente> getClientById(Integer id) {
        if (clientes == null) {
            return Optional.empty();
        }

        return clientes.stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst();
    }

    @Override
    public Optional<Cliente> getClientByDNI(String DNI) {
        if (clientes == null) {
            return Optional.empty();
        }

        return clientes.stream()
                .filter(c -> DNI.equals(c.getDNI()))
                .findFirst();
    }

    @Override
    public Optional<Cliente> getClientByEmail(String email) {
        if (clientes == null) {
            return Optional.empty();
        }

        return clientes.stream()
                .filter(c -> email.equals(c.getEmail()))
                .findFirst();
    }

    @Override
    public void create(String DNI, String nombre, String direccionPostal, String email,
                       String telefono, Sucursal sucursal) {

        Cliente clienteNuevo = new Cliente(contador++, DNI, nombre, direccionPostal, email, telefono, sucursal);

        clientes.add(clienteNuevo);
    }

    @Override
    public void update(Integer id, String DNI, String nombre, String direccionPostal, String email,
                       String telefono, Sucursal sucursal) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id)) {
                c.setDNI(DNI);
                c.setNombre(nombre);
                c.setDireccionPostal(direccionPostal);
                c.setEmail(email);
                c.setTelefono(telefono);
                c.setSucursal(sucursal);
                return;
            }
        }

        throw new NoSuchElementException("No se encontró un cliente con ID: " + id);
    }

    @Override
    public void delete(Integer id) {
        if (clientes == null) {
            throw new IllegalStateException("La lista de clientes no ha sido inicializada.");
        }

        clientes.removeIf(c -> id.equals(c.getId()));
    }
}
