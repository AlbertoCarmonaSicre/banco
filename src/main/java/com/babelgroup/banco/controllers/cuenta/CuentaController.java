package com.babelgroup.banco.controllers.cuenta;


import com.babelgroup.banco.models.Cuenta;
import org.springframework.ui.Model;

public interface CuentaController {

    String altaCuenta(Model model, Cuenta cuenta);

    String modificarCuenta(Model model, String numeroCuenta, Cuenta cuenta);

    String borrarCuenta(Model model,String numeroCuenta);

    String detalleCuenta(Model model,String numeroCuenta);
}
