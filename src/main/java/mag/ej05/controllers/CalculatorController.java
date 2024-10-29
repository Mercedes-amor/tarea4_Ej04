package mag.ej05.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CalculatorController {

    // Variables para almacenar valores a sumar,
    //Los inicializamos en -1, ya que si no por defecto estaría en 0, y
    //el cero sí es un valor válido para sumar.
    int sumando1 = -1;
    int sumando2 = -1;

    // Variable para saber si se está sumando
    boolean isAdding = false;

    public Integer sumar(int num1, int num2) {
        int result = num1 + num2;
        return result;
    }

    @GetMapping("/")
    public String showCalculator(Model model) {

        // mandamos desde este @Getmapping("/") los valores a mostrar en el html
        // Ya que si lo hacemos desde los otros e reinician los valores con el redirect

        // Hacemos un condicional ternario para que solo muestre los valores si las
        // variables están inicializadas
        model.addAttribute("sumando1", sumando1 == -1 ? "" : sumando1);
        model.addAttribute("sumando2", sumando2 == -1 ? "" : sumando2);

        return "indexView";
    }

    @GetMapping("/digito/{num}")
    public String getSumandos(@PathVariable Integer num) {

        if (sumando1 == -1) {
            // Almacena en sumando1 si aún no tiene valor
            sumando1 = num;

        } else if (sumando2 == -1 && isAdding) {
            // Almacena en sumando2 solo si ya se ha pulsado "+"
            sumando2 = num;
        }

        return "redirect:/";

    }

    // Botón +
    // Actualizamos el valor del booleano si estamos sumando
    @GetMapping("/suma")
    public String botonSumar() {

        // Solo permite sumar si hay un primer número, 
        //si no, no hace nada
        if (sumando1 != -1) {
            isAdding = true;
        }
        return "redirect:/";
    }

    // Botón =
    @GetMapping("/igual")
    public String calcularResultado(Model model) {

        // Primero mostramos los valores de los sumandos, ya que al no poder hacer
        // redirect aquí se pierde su visualizacion.
        model.addAttribute("sumando1", sumando1 == -1 ? "" : sumando1);
        model.addAttribute("sumando2", sumando2 == -1 ? "" : sumando2);

        // Ahora hacemos un condicional para realizar la operación
        //Si no se han introducido los sumandos y pulsado el botón +, el = no hará nada.
        if (sumando1 != -1 && sumando2 != -1 && isAdding) {
            int resultado = sumar(sumando1, sumando2);
            model.addAttribute("resultado", resultado);

            // Al principio reseteabamos los valores aquí, pero limpiaba la
            // vista al redirigir a "/"
        }

        // Al principio hacíamos redirect, pero no nos mostraba el resultado
        // ya que limpiaba la vista.
        return "indexView";
    }

    // Botón Clear
    @GetMapping("/clear")
    public String clearResults() {

        // Reseteamos todos los valores
        sumando1 = -1;
        sumando2 = -1;
        isAdding = false;

        return "redirect:/";
    }

}
