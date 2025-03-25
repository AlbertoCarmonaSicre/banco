package com.babelgroup.banco.controllers.sucursal;

import com.babelgroup.banco.models.Sucursal;
import com.babelgroup.banco.services.sucursal.SucursalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sucursales")
public class SucursalControllerImpl implements SucursalController{

    SucursalService sucursalService;

    public SucursalControllerImpl(SucursalService sucursalService){
        this.sucursalService = sucursalService;
    }

    @Override
    @GetMapping("")
    public String findAll(Model model) {
        List<Sucursal> sucursales = sucursalService.findAll();
        model.addAttribute("sucursales", sucursales);
        return "sucursales";
    }

    @Override
    @PostMapping("")
    public String create(String nombre, String direccion, String director, Model model) {
        Sucursal sucursal = sucursalService.create(nombre, direccion, director);
        model.addAttribute("sucursal", sucursal);
        return "sucursales";
    }

    @Override
    @PutMapping("")
    public String update(Integer id, String nombre, String direccion, String director, Model model) {
        Sucursal sucursal = sucursalService.update(new Sucursal(id, nombre, direccion, director));
        model.addAttribute("sucursal", sucursal);
        return "sucursales";
    }

    @Override
    public String delete(Integer id, Model model) {
        sucursalService.delete(id);
        return "sucursales";
    }
}
