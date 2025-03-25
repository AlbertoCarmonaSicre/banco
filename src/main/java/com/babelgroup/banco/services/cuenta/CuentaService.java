package com.babelgroup.banco.services.cuenta;

import com.babelgroup.banco.dto.CuentaDetalle;
import com.babelgroup.banco.models.Cliente;
import com.babelgroup.banco.models.Cuenta;
import com.babelgroup.banco.models.Sucursal;

import java.util.List;

public interface CuentaService {

    Cuenta cuentaAlta(Sucursal sucursal, Integer id, Integer balance);

    void cuentaModificar(String numeroCuenta, Sucursal sucursal, Integer id, Integer balance);

    List<Cuenta> cuentaListarPorClientes(Cliente cliente);

    List<Cuenta> cuentaListarPorSucursal(Sucursal sucursal);

    void cuentaBorrar(String numeroCuenta);

    CuentaDetalle cuentaDetalle(String numeroCuenta);
}
