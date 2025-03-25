package com.babelgroup.banco.models;

public class Cuenta {

    private String numCuenta;
    private Sucursal sucursal;
    private Cliente cliente;
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

    public Cuenta(String numCuenta, Cliente cliente, Integer balance) {
        this.numCuenta = numCuenta;
        this.cliente = cliente;
        this.balance = balance;
        this.sucursal = cliente.getSucursal();
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
