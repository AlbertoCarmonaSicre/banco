package com.babelgroup.banco.dto;

public class CuentaDetalle {
    private String numeroCuenta;
    private Integer balance;

    public CuentaDetalle(String numeroCuenta, Integer balance) {
        this.numeroCuenta = numeroCuenta;
        this.balance = balance;
    }

    // Getters
    public String getNumeroCuenta() { return numeroCuenta; }
    public Integer getBalance() { return balance; }
}
