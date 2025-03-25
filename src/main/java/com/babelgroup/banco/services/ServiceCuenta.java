package com.babelgroup.banco.services;

import com.babelgroup.banco.models.Cliente;
import com.babelgroup.banco.models.Cuenta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceCuenta {
    String numeroCuenta = String.format("%020d", 1); // 20 dígitos con ceros a la izquierda
    private List<Cuenta> cuentas = new ArrayList<>();

    public Cuenta cuentaAlta(Cliente cliente, Integer balance) {
        // Crea cuenta con el numeroCuenta actual
        Cuenta cuenta = new Cuenta(
                numeroCuenta,
                cliente,
                balance
        );
        // La sucursal se asigna automáticamente en el constructor de Cuenta
        // usando la sucursal del cliente

        // Incrementamos el numeroCuenta
        Long numeroAux = Long.parseLong(numeroCuenta);
        numeroAux++;
        numeroCuenta = String.format("%020d", numeroAux);

        return cuenta;
    }

    public void cuentaModificar(Cuenta cuenta, Cliente cliente, Integer nuevoBalance) {
        cuenta.setCliente(cliente);
        cuenta.setBalance(nuevoBalance);
    }

    public List<Cuenta> cuentaListarPorClientes(Cliente cliente) {

        return cuentas.stream()
                .filter(cuenta -> cuenta.getCliente().getId().equals(cliente.getId()))
                .collect(Collectors.toList());
    }
}
