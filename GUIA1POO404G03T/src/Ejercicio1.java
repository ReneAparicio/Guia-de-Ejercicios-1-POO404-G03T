public class Ejercicio1 {
  import java.util.Scanner;

class importationVehicular{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada de datos del vehículo
        System.out.print("Ingrese el tipo de vehículo (Sedan, Pickups, Microbuses, Motos): ");
        String tipoVehiculo = scanner.nextLine().trim();

        System.out.print("Ingrese la marca del vehículo: ");
        String marca = scanner.nextLine().trim();

        System.out.print("Ingrese el año de fabricación del vehículo: ");
        int edadFabricacion = scanner.nextInt();

        System.out.print("Ingrese el valor del vehículo: ");
        double valorVehiculo = scanner.nextDouble();

        System.out.print("Ingrese el país de origen (China o Estados Unidos): ");
        scanner.nextLine(); // Limpiar buffer
        String paisOrigen = scanner.nextLine().trim();

        // Validaciones básicas
        int edadActual = 2025;
        int edadVehiculo = edadActual - edadFabricacion;
        if (edadVehiculo > 15) {
            System.out.println("No se aceptan vehículos mayores a 15 años de fabricación.");
            return;
        }

        // Cálculo del impuesto según la edad del vehículo
        double porcentajeImpuesto;
        if (edadVehiculo >= 10) {
            porcentajeImpuesto = 0.16;
        } else if (edadVehiculo >= 5) {
            porcentajeImpuesto = 0.13;
        } else {
            porcentajeImpuesto = 0.11;
        }
        double costoImpuesto = valorVehiculo * porcentajeImpuesto;

        // Cálculo del flete marítimo según país de origen y tipo de vehículo
        double flete = obtenerCostoFlete(paisOrigen, tipoVehiculo);

        // Cálculo de la matrícula inicial
        double matricula = obtenerCostoMatricula(edadFabricacion);

        // Cálculo del total de importación
        double totalImportacion = costoImpuesto + flete + matricula;

        // Mostrar resultados
        System.out.println("\nDetalles de importación del vehículo:");
        System.out.println("Tipo: " + tipoVehiculo);
        System.out.println("Marca: " + marca);
        System.out.println("Año de fabricación: " + edadFabricacion);
        System.out.println("Porcentaje de impuesto: " + (porcentajeImpuesto * 100) + "%");
        System.out.println("Costo del impuesto: $" + costoImpuesto);
        System.out.println("Flete marítimo desde " + paisOrigen + ": $" + flete);
        System.out.println("Matrícula inicial: $" + matricula);
        System.out.println("Total de importación: $" + totalImportacion);

        scanner.close();
    }

    // Método para obtener el costo del flete marítimo
    private static double obtenerCostoFlete(String pais, String tipo) {
        double[][] tarifas = {
                {1700, 1900, 2400, 1300}, // China
                {1200, 1500, 1700, 900}   // Estados Unidos
        };

        int paisIndex = pais.equalsIgnoreCase("China") ? 0 : 1;
        int tipoIndex = switch (tipo.toLowerCase()) {
            case "sedan" -> 0;
            case "pickups" -> 1;
            case "microbuses" -> 2;
            case "motos" -> 3;
            default -> -1;
        };

        if (tipoIndex == -1) {
            System.out.println("Tipo de vehículo no válido.");
            System.exit(0);
        }

        return tarifas[paisIndex][tipoIndex];
    }

    // Método para obtener el costo de la matrícula inicial
    private static double obtenerCostoMatricula(int anio) {
        if (anio >= 2020 && anio <= 2025) return 11.99;
        if (anio >= 2016 && anio <= 2019) return 13.99;
        if (anio >= 2010 && anio <= 2015) return 15.99;
        return 0; // En caso de un error
    }
}

  
}
