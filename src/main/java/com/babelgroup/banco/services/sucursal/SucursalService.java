package com.babelgroup.banco.services.sucursal;

import com.babelgroup.banco.models.Sucursal;

import java.util.List;
import java.util.Optional;

public interface SucursalService {

    List<Sucursal> findAll();

    Optional<Sucursal> findSucursalByName(String sucursalName);

    Sucursal create(String nombre, String direccion, String director);

    Sucursal update(Sucursal sucursal);

    void delete(Integer id);

    Sucursal findById(Integer id);

}
