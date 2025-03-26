package com.babelgroup.banco.services.cuenta;

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

import com.babelgroup.banco.services.sucursal.SucursalService;

@Service
public class CuentaServiceImpl {
    @Autowired
    private SucursalService sucursalService;

    String numeroCuenta = String.format("%020d", 1); // 20 dígitos con ceros a la izquierda

    private static List<Cuenta> cuentas = new ArrayList<>();  // Inicialmente vacía

    /** Prueba  metiendo una cuenta en la lista

    private void init() {
        List<Sucursal> sucursales = sucursalService.findAll();
        cuentas.add(new Cuenta("00000000000000000001",
                sucursales.get(0),
                Optional.of(new Cliente(1,"12345678A","Paco","Falsa 123, Mairena",
                        "pacoelflaco@gmail.com", "666123123",
                        sucursales.get(0))),
                1000));
    }
    */

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
        Optional<Cliente> cliente = clienteService.getClientById(id);


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
                .filter(cuenta -> cuenta.getCliente().get().getId().equals(cliente.getId()))
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
