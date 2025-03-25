package com.babelgroup.banco.services;

import com.babelgroup.banco.dto.CuentaDetalle;
import com.babelgroup.banco.models.Cliente;
import com.babelgroup.banco.models.Cuenta;
import com.babelgroup.banco.models.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CuentaService {
    String numeroCuenta = String.format("%020d", 1); // 20 dígitos con ceros a la izquierda
    private List<Cuenta> cuentas = new ArrayList<>();

    public Cuenta cuentaAlta(Sucursal sucursal, Integer id, Integer balance) {

        //TODO comprobar que pilla getClientById (comprobar nomenclatura)
        Cliente cliente = serviceCliente.getClientById(id);
        // Validar que el cliente existe y pertenece a la sucursal
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + id);
        }

        // Crea cuenta con el numeroCuenta actual
        Cuenta cuenta = new Cuenta(
                numeroCuenta,
                sucursal,
                cliente,
                balance
        );

        // Incrementamos el numeroCuenta
        Long numeroAux = Long.parseLong(numeroCuenta);
        numeroAux++;
        numeroCuenta = String.format("%020d", numeroAux);

        return cuenta;
    }


    public void cuentaModificar(String numeroCuenta, Sucursal sucursal, Integer id, Integer balance) {
        Cliente cliente = serviceCliente.getClientById(id);


        // Validar formato del número de cuenta (20 dígitos)
        if (numeroCuenta == null || !numeroCuenta.matches("\\d{20}")) {
            throw new IllegalArgumentException("El número de cuenta debe tener exactamente 20 dígitos");
        }

        // Buscar la cuenta
        Cuenta cuentaExistente = cuentas.stream()
                .filter(c -> c.getNumCuenta().equals(numeroCuenta))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + numeroCuenta));

        // Validar que la cuenta pertenece al cliente y sucursal indicados

        // Actualizar el balance
        cuentaExistente.setNumCuenta(numeroCuenta);
        cuentaExistente.setSucursal(sucursal);
        cuentaExistente.setCliente(cliente);
        cuentaExistente.setBalance(balance);
    }

    public List<Cuenta> cuentaListarPorClientes(Cliente cliente) {

        return cuentas.stream()
                .filter(cuenta -> cuenta.getCliente().getId().equals(cliente.getId()))
                .collect(Collectors.toList());
    }

    public List<Cuenta> cuentaListarPorSucursal(Sucursal sucursal) {

        return cuentas.stream()
                .filter(cuenta -> cuenta.getSucursal().getId().equals(sucursal.getId()))
                .collect(Collectors.toList());
    }

    public void cuentaBorrar(String numeroCuenta) {
        // Validar formato del número de cuenta (20 dígitos)
        if (numeroCuenta == null || !numeroCuenta.matches("\\d{20}")) {
            throw new IllegalArgumentException("El número de cuenta debe tener exactamente 20 dígitos");
        }

        // Buscar y eliminar la cuenta
        boolean cuentaEliminada = cuentas.removeIf(cuenta -> cuenta.getNumCuenta().equals(numeroCuenta));
    }

    public CuentaDetalle cuentaDetalle(String numeroCuenta) {
        // Validar formato del número de cuenta (20 dígitos)
        if (numeroCuenta == null || !numeroCuenta.matches("\\d{20}")) {
            throw new IllegalArgumentException("El número de cuenta debe tener exactamente 20 dígitos");
        }

        // Buscar la cuenta
        Cuenta cuenta = cuentas.stream()
                .filter(c -> c.getNumCuenta().equals(numeroCuenta))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + numeroCuenta));

        // Devolver solo número de cuenta y balance
        return new CuentaDetalle(cuenta.getNumCuenta(), cuenta.getBalance());
    }
}
