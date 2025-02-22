import javax.swing.JOptionPane;
//Sábado 22/02 Rene Aparicio #AR240329
public class Ejercicio2 {

    public static void main(String[] args) {
        try {
            // Mensaje de bienvenida
            JOptionPane.showMessageDialog(null, "Bienvenido a Encomiendas express");

            // Preguntamos el peso del paquete
            String pesoInput = JOptionPane.showInputDialog("Ingrese el peso del paquete (kg):");
            double peso = validarNumero(pesoInput, "peso");

            // Preguntamos la distancia del envío
            String distanciaInput = JOptionPane.showInputDialog("Ingrese la distancia del envío (km):");
            double distancia = validarNumero(distanciaInput, "distancia");

            // preguntar si tiene tarjeta VIP
            String tieneVIPInput = JOptionPane.showInputDialog("¿Tiene tarjeta VIP? (s/n):");
            boolean tieneVIP = tieneVIPInput != null && tieneVIPInput.equalsIgnoreCase("s");

            // Preguntar si desea envío express
            String esExpressInput = JOptionPane.showInputDialog("¿Desea envío express? (s/n):");
            boolean esExpress = esExpressInput != null && esExpressInput.equalsIgnoreCase("s");

            // Calcular tarifas y totales
            double tarifaPeso = calcularTarifaPeso(peso);
            double tarifaDistancia = calcularTarifaDistancia(distancia);
            double subtotalGeneral = tarifaPeso + tarifaDistancia;

            double costoExpress = esExpress ? subtotalGeneral * 0.13 : 0;
            double descuentoVIP = tieneVIP ? (subtotalGeneral + costoExpress) * 0.10 : 0;
            double precioTotal = subtotalGeneral + costoExpress - descuentoVIP;

            // mostramos los datos ingresados
            String mensaje = "--- Detalles del envío ---\n" +
                    String.format("Peso del paquete: %.2f kg - Tarifa aplicada: $%.2f%n", peso, tarifaPeso) +
                    String.format("Distancia del envío: %.2f km - Tarifa aplicada: $%.2f%n", distancia, tarifaDistancia) +
                    String.format("Subtotal de peso: $%.2f%n", tarifaPeso) +
                    String.format("Subtotal de distancia: $%.2f%n", tarifaDistancia) +
                    String.format("Subtotal general: $%.2f%n", subtotalGeneral) +
                    String.format("Costo por envío express: $%.2f%n", costoExpress) +
                    String.format("Descuento aplicado por tarjeta VIP: $%.2f%n", descuentoVIP) +
                    String.format("Precio total a pagar: $%.2f%n", precioTotal);

            JOptionPane.showMessageDialog(null, mensaje);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    //Validación de los campos
    private static double validarNumero(String input, String campo) throws Exception {
        if (input == null || input.isEmpty()) {
            throw new Exception("El campo " + campo + " no puede estar vacío.");
        }
        try {
            double valor = Double.parseDouble(input);
            if (valor <= 0) {
                throw new Exception("El valor de " + campo + " debe ser mayor que 0.");
            }
            return valor;
        } catch (NumberFormatException e) {
            throw new Exception("El valor de " + campo + " debe ser un número válido.");
        }
    }

    //calculamos la tarifa según el peso
    private static double calcularTarifaPeso(double peso) {
        if (peso >= 1 && peso <= 4) return peso * 1.20;
        else if (peso >= 5 && peso <= 10) return peso * 1.35;
        else if (peso >= 11 && peso <= 20) return peso * 1.60;
        else return peso * 1.75;
    }

    //calculamos la tarifa según la distancia
    private static double calcularTarifaDistancia(double distancia) {
        if (distancia >= 1 && distancia <= 20) return distancia * 0.20;
        else if (distancia >= 21 && distancia <= 40) return distancia * 0.08;
        else if (distancia >= 41 && distancia <= 60) return distancia * 0.06;
        else return distancia * 0.04;
    }
}