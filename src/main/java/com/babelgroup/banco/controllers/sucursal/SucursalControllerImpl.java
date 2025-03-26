package com.babelgroup.banco.controllers.sucursal;

import com.babelgroup.banco.models.Sucursal;
import com.babelgroup.banco.services.sucursal.SucursalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/sucursales")
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
        sucursalService.create(nombre, direccion, director);
        List<Sucursal> sucursales = sucursalService.findAll();
        model.addAttribute("sucursales", sucursales);
        return "sucursales";
    }

    @Override
    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, String nombre, String direccion, String director, Model model) {
        Sucursal sucursal = sucursalService.update(new Sucursal(id, nombre, direccion, director));
        List<Sucursal> sucursales = sucursalService.findAll();
        model.addAttribute("sucursales", sucursales);
        model.addAttribute("sucursal", sucursal);
        return "sucursales";
    }

    @Override
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, Model model) {
        sucursalService.delete(id);
        List<Sucursal> sucursales = sucursalService.findAll();
        model.addAttribute("sucursales", sucursales);
        return "sucursales";
    }
}
