package mag.ej04.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import mag.ej04.services.CalculatorService;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService; // Inyectamos el servicio

    @GetMapping("/")
    public String showCalculator(Model model) {
        // Obtenemos los valores actuales de los sumandos
        model.addAttribute("sumando1", calculatorService.getSumando1() == -1 ? "" : calculatorService.getSumando1());
        model.addAttribute("sumando2", calculatorService.getSumando2() == -1 ? "" : calculatorService.getSumando2());
        return "indexView";
    }

    @GetMapping("/digito/{num}")
    public String getSumandos(@PathVariable Integer num) {
        // Establecemos el primer o segundo sumando
        if (calculatorService.getSumando1() == -1) {
            calculatorService.setSumando1(num);
        } else if (calculatorService.getSumando2() == -1) {
            calculatorService.setSumando2(num);
        }
        return "redirect:/";
    }

    @GetMapping("/suma")
    public String botonSumar(Model model) {
        model.addAttribute("sumando1", calculatorService.getSumando1());
        calculatorService.iniciarSuma();
        return "indexView";
    }

    // Ruta para la operación de resta
    @GetMapping("/resta")
    public String botonRestar(Model model) {
        model.addAttribute("sumando1", calculatorService.getSumando1());
        calculatorService.iniciarResta();
        return "indexView";
    }

    @GetMapping("/igual")
    public String calcularResultado(Model model) {

        if (calculatorService.isAdding) { // Si se está sumando
            Integer resultado = calculatorService.calcularResultadoSuma();
            //mandamos nuevamente los valores de los sumandos y resultado para que se muestren en la vista
            model.addAttribute("sumando1", calculatorService.getSumando1());
            model.addAttribute("sumando2", calculatorService.getSumando2());
            model.addAttribute("resultado", resultado);
            return "indexView";
        }

        if (calculatorService.isSustracting) { // Si se está restando
            Integer resultado = calculatorService.calcularResultadoResta();
            //mandamos nuevamente los valores de los sumandos y resultado para que se muestren en la vista
            model.addAttribute("sumando1", calculatorService.getSumando1());
            model.addAttribute("sumando2", calculatorService.getSumando2());
            model.addAttribute("resultado", resultado);
            return "indexView";
        } else {
            return "indexView";
        }

    }

    @GetMapping("/clear")
    public String clearResults() {
        // Resetamos los valores
        calculatorService.reset();
        return "redirect:/";
    }
}