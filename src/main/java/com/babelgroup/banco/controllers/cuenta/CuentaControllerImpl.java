package com.babelgroup.banco.controllers.cuenta;

import com.babelgroup.banco.dto.CuentaDetalle;
import com.babelgroup.banco.models.Cliente;
import com.babelgroup.banco.models.Cuenta;
import com.babelgroup.banco.models.Sucursal;
import com.babelgroup.banco.services.cuenta.CuentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaControllerImpl implements CuentaController {

    @Autowired
    private CuentaServiceImpl cuentaServiceImpl;

    @PostMapping
    public ResponseEntity<Cuenta> altaCuenta(@RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaServiceImpl.cuentaAlta(
                cuenta.getSucursal(),
                cuenta.getCliente().getId(),
                cuenta.getBalance()
        ));
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<Void> modificarCuenta(
            @PathVariable String numeroCuenta,
            @RequestBody Cuenta cuenta) {
        cuentaServiceImpl.cuentaModificar(
                numeroCuenta,
                cuenta.getSucursal(),
                cuenta.getCliente().getId(),
                cuenta.getBalance()
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> borrarCuenta(@PathVariable String numeroCuenta) {
        cuentaServiceImpl.cuentaBorrar(numeroCuenta);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaDetalle> detalleCuenta(@PathVariable String numeroCuenta) {
        return ResponseEntity.ok(cuentaServiceImpl.cuentaDetalle(numeroCuenta));
    }
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Cuenta>> listarCuentasPorCliente(@PathVariable Integer clienteId) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        return ResponseEntity.ok(cuentaServiceImpl.cuentaListarPorClientes(cliente));
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<Cuenta>> listarCuentasPorSucursal(@PathVariable Integer sucursalId) {
        Sucursal sucursal = new Sucursal("Sucursal 1", "Direccion 1", "Director 1");
        sucursal.setId(sucursalId);
        return ResponseEntity.ok(cuentaServiceImpl.cuentaListarPorSucursal(sucursal));
    }

}
