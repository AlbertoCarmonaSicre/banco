package com.babelgroup.banco.services.sucursal;

import com.babelgroup.banco.models.Sucursal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SucursalServiceImpl implements SucursalService {

    private static Integer contador = 0;
    private static List<Sucursal> sucursales = new ArrayList<>();


    @Override
    public List<Sucursal> findAll() {
        return sucursales;
    }

    @Override
    public Optional<Sucursal> findSucursalByName(String name) {
        Sucursal sucursal = sucursales.stream().filter(s -> name.equals(s.getNombre())).findFirst().orElse(null);
        if (sucursal == null){
            return Optional.empty();
        }
        return Optional.of(sucursal);
    }


    @Override
    public Sucursal create(String nombre, String direccion, String director) {
        Sucursal sucursal = new Sucursal(contador++, nombre, direccion, director);
        try{
            sucursales.add(sucursal);
            return sucursal;
        }catch (Error e){
            throw new IllegalArgumentException("Error al crear la sucursal");
        }
    }

    @Override
    public Sucursal update(Sucursal sucursal) {
        Optional<Sucursal> sucursalOptional = sucursales.stream()
                .filter(s -> s.getId().equals(sucursal.getId()))
                .findFirst();
        if(sucursalOptional.isPresent()){
            Sucursal sucursalActual = sucursalOptional.get();
            sucursalActual.setNombre(sucursal.getNombre());
            sucursalActual.setDireccion(sucursal.getDireccion());
            sucursalActual.setDirector(sucursal.getDirector());
            return sucursalActual;
        }else{
            throw new NoSuchElementException("No se encontro la sucursal");
        }
    }

    @Override
    public void delete(Integer id) {
        Optional<Sucursal> sucursalOptional = sucursales.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
        if(sucursalOptional.isPresent()){
            sucursales.remove(sucursalOptional.get());
        }else{
            throw new NoSuchElementException("No se encontro la sucursal");
        }
    }

    @Override
    public Sucursal findById(Integer id) {
        return sucursales.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No se encontró la sucursal"));
    }
}
