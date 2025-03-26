package com.babelgroup.banco.dto;

public class CuentaDetalle {
    private String numeroCuenta;
    private String sucursal;
    private String cliente;
    private Integer balance;

    // Constructor
    public CuentaDetalle(String numeroCuenta, String sucursal, String cliente, Integer balance) {
        this.numeroCuenta = numeroCuenta;
        this.sucursal = sucursal;
        this.cliente = cliente;
        this.balance = balance;
    }

    // Getters
    public String getNumeroCuenta() { return numeroCuenta; }
    public Integer getBalance() { return balance; }
    public String getSucursal() { return sucursal; }
    public String getCliente() { return cliente; }
}
