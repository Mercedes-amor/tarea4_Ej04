package mag.ej04.services;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    // Métodos para manejar la lógica de los sumandos
    public int sumando1 = -1;
    public int sumando2 = -1;
    public boolean isAdding = false;
    public boolean isSustracting = false;

    //ALMACENAR SUMANDOS/RESTANDOS
    // Método para almacenar el primer sumando/restando
    public void setSumando1(int num) {
        if (sumando1 == -1) {
            sumando1 = num;
        }
    }

    // Método para almacenar el segundo sumando/restando
    public void setSumando2(int num) {
        if (sumando1 != -1 && sumando2 == -1 && isAdding) {
            sumando2 = num;
        }

        if (sumando1 != -1 && sumando2 == -1 && isSustracting) {
            sumando2 = -num; //convertimos el número en negativo para mostrarlo
        }
    }

    //INICIAR OPERACIONES SUMA O RESTA
    // Método para iniciar la operación suma
    public void iniciarSuma() {
        if (sumando1 != -1) {
            isAdding = true;
        }
    }

    // Método para iniciar la operación resta
    public void iniciarResta() {
        if (sumando1 != -1) {
            isSustracting = true;
        }
    }

    //CALCULAR RESULTADOS
    // Suma
    public Integer calcularResultadoSuma() {
        if (sumando1 != -1 && sumando2 != -1 && isAdding) {
            return sumando1 + sumando2;
        }
        return null;
    }

    // Resta
    public Integer calcularResultadoResta() {
        if (sumando1 != -1 && sumando2 != -1 && isSustracting) {
            return sumando1 + sumando2; //Lo sumamos porque ya hemos convertido el sumando2 en negativo
        }
        return null;
    }

    // Método para resetear los valores
    public void reset() {
        sumando1 = -1;
        sumando2 = -1;
        isAdding = false;
        isSustracting = false;

    }

    // Métodos para obtener los valores de los sumandos
    public int getSumando1() {
        return sumando1;
    }

    public int getSumando2() {
        return sumando2;
    }

}
