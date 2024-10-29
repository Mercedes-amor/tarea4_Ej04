package mag.ej05.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class CalculatorController {

    //Variables para almacenar valores a sumar

    int sumando1;
    int sumando2;

    public Integer sumar(int num1, int num2) {
        int result = num1 + num2;
        return result;

    }

    @GetMapping("/")
    public String showMovies() {
     
      

        return "indexView";
    } 


    @GetMapping("/digito/{num}")
    public String getMethodName(@PathVariable Integer num) {
        
        
        return "redirect /";
    }
    
}

