package com.babelgroup.banco.controllers;

import com.babelgroup.banco.dto.CuentaDetalle;
import com.babelgroup.banco.models.Cliente;
import com.babelgroup.banco.models.Cuenta;
import com.babelgroup.banco.models.Sucursal;
import com.babelgroup.banco.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<Cuenta> altaCuenta(@RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.cuentaAlta(
                cuenta.getSucursal(),
                cuenta.getCliente().getId(),
                cuenta.getBalance()
        ));
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<Void> modificarCuenta(
            @PathVariable String numeroCuenta,
            @RequestBody Cuenta cuenta) {
        cuentaService.cuentaModificar(
                numeroCuenta,
                cuenta.getSucursal(),
                cuenta.getCliente().getId(),
                cuenta.getBalance()
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> borrarCuenta(@PathVariable String numeroCuenta) {
        cuentaService.cuentaBorrar(numeroCuenta);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaDetalle> detalleCuenta(@PathVariable String numeroCuenta) {
        return ResponseEntity.ok(cuentaService.cuentaDetalle(numeroCuenta));
    }
}
