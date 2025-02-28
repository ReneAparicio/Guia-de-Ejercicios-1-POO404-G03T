//Hecho por: Rafael Alejandro Escorcia Siliézar #ES232712

import java.util.InputMismatchException; //<-- sirve para la lectura de la entrada del usuario
import java.util.Scanner;


public class AlquilerJets {

    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);

        //datos del piloto

        System.out.print("Favor ingresar el nombre del piloto: ");
        String nombre = scanner.nextLine();
        System.out.print("Favor ingresar el Apellido del piloto: ");
        String apellido= scanner.nextLine();

        //modelos de Jet
        int tipoJet = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.println("Modelos de Jets disponibles");
            System.out.println("1. Light Jet ($3,000/hora)");
            System.out.println("2. Super Jet ($4,500/hora)");
            System.out.println("Seleccionar el tipo de jet (1 o 2): ");
//validacion que solo pueda ingresar 1 o 2 no letras, otros numeros o simbolos
            try {
                tipoJet = scanner.nextInt();
                if (tipoJet == 1 || tipoJet == 2) {
                    entradaValida = true;
                } else {
                    System.out.println("Entrada no valida. Debe seleccionar 1 o 2.");
                }
            } catch (InputMismatchException e){
                System.out.println("Entrada no valida. Debe ingresar un número.");
                scanner.next(); //limpia el buffer  del scanner
            }
        }
        //verificar la cantidad de horas
        //validacion que solo pueda ingresar numeros, no letras o simbolos
        int cantidadHoras = 0;
        boolean entradaValidaHoras = false;
        while (!entradaValidaHoras) {
            System.out.print("Ingrese la cantidad de horas de alquiler: ");
            try {
                cantidadHoras = scanner.nextInt();
                if (cantidadHoras > 0){
                    entradaValidaHoras = true;
                } else {
                    System.out.println("Entrada no valida. Debe ingresar un numero entero");
                }

            } catch (InputMismatchException e){
                System.out.println("Entrada no valida. debe ingresar un numero.");
                scanner.next();
            }
        }



        //calculo del precio

        double precioHora = (tipoJet == 1) ? 3000 : 4500;
        double precioBase = precioHora * cantidadHoras;

        //calculo del descuento

        double descuento = calcularDescuento(cantidadHoras);
        double precioConDescuento = precioBase * (1 - descuento);

        //mostrar info
        System.out.println("\n---Informacion de Alquiler---");
        System.out.println("Piloto: " + nombre + " " + apellido);
        System.out.println("Tipo de jet seleccionado: " + (tipoJet == 1 ? "Light Jet" : "Super Jet"));
        System.out.println("Cantidad de horas: " + cantidadHoras);
        System.out.printf("Precio por hora: $%,d\n", (int) precioHora); // $%,d = separacion de miles en numero entero
        System.out.printf("Descuento aplicado: %.0f%%\n", descuento * 100); //%.0f%% = mostrar un número decimal como un número entero,con un símbolo de porcentaje.
        System.out.printf("Precio con descuento: $%,d\n", (int) precioConDescuento); //$%,d = para separacion de miles y que el resultado sea entero

        scanner.close();
    }

    //funcion calculo de descuento segun horas
    private static double calcularDescuento(int cantidadHoras){
        if (cantidadHoras > 0 && cantidadHoras <= 3){
            return 0; //sin descuento
        } else if (cantidadHoras >= 4 && cantidadHoras <= 8){
            return 0.15; //15% descuento
        } else if (cantidadHoras >= 9 && cantidadHoras <= 12) {
            return 0.20; //20% descuento
        }else if (cantidadHoras >= 13 && cantidadHoras <= 16) {
            return 0.25; //25% descuento
        }else{
            return 0.30; //30% descuento
        }
    }
}

