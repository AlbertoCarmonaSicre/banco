package com.babelgroup.banco.services.sucursal;

import com.babelgroup.banco.models.Sucursal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalServiceImpl implements SucursalService {

    private static Integer contador = 0;
    private static List<Sucursal> sucursales;

    @Override
    public List<Sucursal> findAll() {
        return sucursales;
    }

    @Override
    public Sucursal create(String nombre, String direccion, String director) {
        Sucursal sucursal = new Sucursal(contador++, nombre, direccion, director);
        try{
            sucursales.add(sucursal);
            return sucursal;
        }catch (Error e){
            System.out.println("Error al crear la sucursal");
            return null;
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
            System.out.println("No se encontro la sucursal");
            return null;
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
            System.out.println("No se encontro la sucursal");
        }
    }
}
