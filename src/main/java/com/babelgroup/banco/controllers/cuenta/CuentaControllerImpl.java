package com.babelgroup.banco.controllers.cuenta;

import com.babelgroup.banco.models.Cuenta;
import com.babelgroup.banco.services.cuenta.CuentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cuentas")
public class CuentaControllerImpl implements CuentaController {

    @Autowired
    private CuentaServiceImpl cuentaService;

    @GetMapping
    public String listarCuentas(Model model) {
        model.addAttribute("cuentas", cuentaService.listarCuentas());
        return "cuentas-vista";
    }

    @Override
    @PostMapping("/alta")
    public String altaCuenta(Model model, Cuenta cuenta) {
        try {
            Cuenta nuevaCuenta = cuentaService.cuentaAlta(
                    cuenta.getSucursal(),
                    cuenta.getCliente().getId(),
                    cuenta.getBalance()
            );
            model.addAttribute("notificacion", "Cuenta creada: " + nuevaCuenta.getNumCuenta());
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/cuentas";
    }

    @Override
    @PostMapping("/modificar/{numeroCuenta}")
    public String modificarCuenta(Model model, @PathVariable String numeroCuenta, Cuenta cuenta) {
        try {
            cuentaService.cuentaModificar(
                    numeroCuenta,
                    cuenta.getSucursal(),
                    cuenta.getCliente().getId(),
                    cuenta.getBalance()
            );
            model.addAttribute("notificacion", "Cuenta modificada correctamente");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/cuentas";
    }

    @Override
    @PostMapping("/borrar/{numeroCuenta}")
    public String borrarCuenta(Model model, @PathVariable String numeroCuenta) {
        try {
            cuentaService.cuentaBorrar(numeroCuenta);
            model.addAttribute("notificacion", "Cuenta eliminada correctamente");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/cuentas";
    }

    @Override
    @GetMapping("/{numeroCuenta}")
    public String detalleCuenta(Model model, @PathVariable String numeroCuenta) {
        try {
            model.addAttribute("cuenta", cuentaService.cuentaDetalle(numeroCuenta));
            return "cuenta-detalle";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/cuentas";
        }
    }
}