package com.babelgroup.banco.controllers.cuenta;

import com.babelgroup.banco.dto.CuentaDetalle;
import com.babelgroup.banco.models.Cuenta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CuentaController {

    @PostMapping
    ResponseEntity<Cuenta> altaCuenta(@RequestBody Cuenta cuenta);

    @PutMapping("/{numeroCuenta}")
    ResponseEntity<Void> modificarCuenta(
            @PathVariable String numeroCuenta,
            @RequestBody Cuenta cuenta);

    @DeleteMapping("/{numeroCuenta}")
    ResponseEntity<Void> borrarCuenta(@PathVariable String numeroCuenta);

    @GetMapping("/{numeroCuenta}")
    ResponseEntity<CuentaDetalle> detalleCuenta(@PathVariable String numeroCuenta);
}
