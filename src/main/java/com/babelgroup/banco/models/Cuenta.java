package com.babelgroup.banco.models;

public class Cuenta {

    private Integer numCuenta;
    private Sucursal sucursal;
    private Cliente cliente;
    private Integer balance;

    public Integer getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(Integer numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Cuenta() {
    }

    public Cuenta(Integer numCuenta, Cliente cliente, Integer balance) {
        this.numCuenta = numCuenta;
        this.cliente = cliente;
        this.balance = balance;
        this.sucursal = cliente.getSucursal();
    }


}
