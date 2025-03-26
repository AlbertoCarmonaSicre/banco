package com.babelgroup.banco.models;

import java.util.Optional;

public class Cuenta {

    private String numCuenta;
    private Sucursal sucursal;
    private Optional<Cliente> cliente;
    private Integer balance;

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Optional<Cliente> getCliente() {
        return cliente;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }


    public Cuenta(String numCuenta, Sucursal sucursal, Optional<Cliente> cliente, Integer balance) {
        this.numCuenta = numCuenta;
        this.sucursal = sucursal;
        this.cliente = cliente.get();
        this.balance = balance;
    }


    public void setCliente(Optional<Cliente> cliente) {
        this.cliente = cliente.get();
    }
}
