package com.babelgroup.banco.controllers.cuenta;

import com.babelgroup.banco.dto.CuentaDetalle;
import com.babelgroup.banco.models.Cuenta;
import com.babelgroup.banco.services.cuenta.CuentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String altaCuenta(Model model,
                             @RequestParam Integer sucursalId,
                             @RequestParam Integer clienteId,
                             @RequestParam Integer balance) {
        try {
            Cuenta nuevaCuenta = cuentaService.cuentaAlta(
                    sucursalId,
                    clienteId,
                    balance
            );
            model.addAttribute("notificacion", "Cuenta creada: " + nuevaCuenta.getNumCuenta());
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/cuentas";
    }

    @Override
    @PostMapping("/modificar/{num}")
    public String modificarCuenta(Model model, @PathVariable("num") String num, Cuenta cuenta) {
        try {
            // Obtener la cuenta existente primero
            CuentaDetalle cuentaExistente = cuentaService.cuentaDetalle(num);

            // Actualizar solo el balance
            cuentaService.actualizarBalance(num, cuenta.getBalance());

            return "redirect:/cuentas";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/cuentas";
        }
    }

    @Override
    @PostMapping("/borrar/{num}")
    public String borrarCuenta(Model model, @PathVariable("num") String num) {
        try {
            cuentaService.cuentaBorrar(num);
            model.addAttribute("notificacion", "Cuenta eliminada correctamente");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/cuentas";
    }

    @GetMapping("/detalle/{numCuenta}")
    public String detalleCuenta(Model model, @PathVariable String numCuenta) {
        try {
            CuentaDetalle cuenta = cuentaService.cuentaDetalle(numCuenta);
            model.addAttribute("cuenta", cuenta);
            return "cuenta-detalle"; // Cambiado de cuentas-detalle a cuenta-detalle
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/cuentas";
        }
    }
}