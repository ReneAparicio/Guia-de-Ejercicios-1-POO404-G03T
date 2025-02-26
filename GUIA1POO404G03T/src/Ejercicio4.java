import javax.swing.JOptionPane;

public class Descuentodematricula {
    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del estudiante:");
        String tipoIngreso = JOptionPane.showInputDialog("Ingrese el tipo de ingreso (Antiguo/Nuevo):");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del estudiante:"));

        double matricula;
        if (edad > 15) {
            matricula = 100;
        } else if (edad > 10) {
            matricula = 125;
        } else {
            matricula = 150;
        }

        double descuento = 0;
        if (tipoIngreso.equalsIgnoreCase("Antiguo")) {
            descuento = matricula * 0.25;
        }

        double totalPagar = matricula - descuento;

        String mensaje = "Nombre: " + nombre + "\n"
                + "Apellido: " + apellido + "\n"
                + "Valor de matrícula: $" + matricula + "\n"
                + "Descuento obtenido: $" + descuento + "\n"
                + "Total a pagar: $" + totalPagar;

        JOptionPane.showMessageDialog(null, mensaje, "Resumen de Matrícula", JOptionPane.INFORMATION_MESSAGE);
    }
}
