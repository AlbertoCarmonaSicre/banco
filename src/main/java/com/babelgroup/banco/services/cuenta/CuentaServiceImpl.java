package com.babelgroup.banco.services.cuenta;

import ch.qos.logback.core.net.server.Client;
import com.babelgroup.banco.dto.CuentaDetalle;
import com.babelgroup.banco.models.Cliente;
import com.babelgroup.banco.models.Cuenta;
import com.babelgroup.banco.models.Sucursal;
import com.babelgroup.banco.services.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl {
    String numeroCuenta = String.format("%020d", 1); // 20 dígitos con ceros a la izquierda
    private List<Cuenta> cuentas = new ArrayList<>();

    @Autowired
    private ClienteService clienteService;

    public Cuenta cuentaAlta(Sucursal sucursal, Integer id, Integer balance) {

        //TODO comprobar que pilla getClientById (comprobar nomenclatura)
        Optional<Cliente> cliente = clienteService.getClientById(id);

        // Validar que el cliente existe y pertenece a la sucursal
        if (cliente.isEmpty()) {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + id);
        }

        // Crea cuenta con el numeroCuenta actual
        Cuenta cuenta = new Cuenta(
                numeroCuenta,
                sucursal,
                cliente,
                balance
        );
        cuentas.add(cuenta); // Añadir la cuenta a la lista

        // Incrementamos el numeroCuenta
        Long numeroAux = Long.parseLong(numeroCuenta);
        numeroAux++;
        numeroCuenta = String.format("%020d", numeroAux);

        return cuenta;
    }


    public void cuentaModificar(String numeroCuenta, Sucursal sucursal, Integer id, Integer balance) {
        Optional<Client> cliente = clienteService.getClientById(id);



        // Validar formato del número de cuenta (20 dígitos)
        if (numeroCuenta == null || !numeroCuenta.matches("\\d{20}")) {
            throw new IllegalArgumentException("El número de cuenta debe tener exactamente 20 dígitos");
        }

        // Buscar la cuenta
        Cuenta cuentaExistente = cuentas.stream()
                .filter(c -> c.getNumCuenta().equals(numeroCuenta))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + numeroCuenta));


        // Actualizar los campos de la cuenta
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
