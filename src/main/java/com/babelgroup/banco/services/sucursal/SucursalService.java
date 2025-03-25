package com.babelgroup.banco.services.sucursal;

import com.babelgroup.banco.models.Sucursal;

import java.util.List;

public interface SucursalService {

    List<Sucursal> findAll();

    Sucursal create(String nombre, String direccion, String director);

    Sucursal update(Sucursal sucursal);

    void delete(Integer id);

}
