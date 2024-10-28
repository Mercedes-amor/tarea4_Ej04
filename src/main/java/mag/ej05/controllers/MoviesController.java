package mag.ej05.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class MoviesController {
    //Declaramos las variables de los votos de cada foto
    int votosFoto0 = 0;
    int votosFoto1 = 0;
    int votosFoto2 = 0;


    @GetMapping("/")
    public String showMovies(Model model) {
     
        model.addAttribute("votosFoto0", votosFoto0);
        model.addAttribute("votosFoto1", votosFoto1);
        model.addAttribute("votosFoto2", votosFoto2);

        return "indexView";
    } 



    @GetMapping("/voto")
    public String getVote(@RequestParam("foto") String foto) {

        //Comprobar valor de las variables
        System.out.println("foto: " + foto + ", votosFoto0: " + votosFoto0);
        System.out.println("foto: " + foto + ", votosFoto1: " + votosFoto1);
        System.out.println("foto: " + foto + ", votosFoto2: " + votosFoto2);

        if (foto.equals("0")) {
            votosFoto0 += 1;
        } else if (foto.equals("1")) {
            votosFoto1 += 1;
        } else if (foto.equals("2")) {
            votosFoto2 += 1;
        }
        
        return "redirect:/";
    }
    
}
