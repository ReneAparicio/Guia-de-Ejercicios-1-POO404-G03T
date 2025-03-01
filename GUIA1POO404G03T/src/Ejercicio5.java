import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Axel Barahona - BL232702
public class Ejercicio5 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Preguntar al usuario cuántos registros desea crear
                String input = JOptionPane.showInputDialog(null, "¿Cuántos registros desea crear?", "Número de registros", JOptionPane.QUESTION_MESSAGE);
                int numRegistros = Integer.parseInt(input);

                // Crear la ventana principal
                new PagoHorasExtra(numRegistros).setVisible(true);
            }
        });
    }
}

class PagoHorasExtra extends JFrame {
    //Inicialización de los campos para ingresar datos
    private JTextField nombreField, salarioField, horasField;
    private JComboBox<String> departamentoCombo;
    private JTextArea resultadoArea;
    private JButton siguienteButton; 
    private int numRegistros;
    private int registroActual = 0;

    public PagoHorasExtra(int numRegistros) {
        this.numRegistros = numRegistros;

        setTitle("Calculadora de Horas Extras");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //Campo Nombre
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Nombre: "));
        nombreField = new JTextField();
        inputPanel.add(nombreField);
        //Campo Salario
        inputPanel.add(new JLabel("Salario: "));
        salarioField = new JTextField();
        inputPanel.add(salarioField);
        //Campo Horas
        inputPanel.add(new JLabel("Horas: "));
        horasField = new JTextField();
        inputPanel.add(horasField);
        //Campo departamento
        inputPanel.add(new JLabel("Departamento: "));
        String[] departamentos = {"Gerencia", "Auditoria", "Tecnologia", "Contabilidad"};
        departamentoCombo = new JComboBox<>(departamentos);
        inputPanel.add(departamentoCombo);
        //Btn Siguiente
        siguienteButton = new JButton("Siguiente"); 
        inputPanel.add(siguienteButton);
        //Acá se imprimen los datos previamente ingresados
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        //Se ejecuta esto cada vez que se le da al boton "Siguiente"
        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPagoHorasExtra();
            }
        });
    }

    private void calcularPagoHorasExtra() {
        try {
            //Obtención de datos
            String nombre = nombreField.getText();
            double salario = Double.parseDouble(salarioField.getText());
            String departamento = (String) departamentoCombo.getSelectedItem();
            int horas = Integer.parseInt(horasField.getText());
            //Validación de las horas
            if (horas > 20) {
                JOptionPane.showMessageDialog(this, "Advertencia: Se ha registrado más de 20 horas extra. Se tomarán como 20 horas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                horas = 20;
            }
            //Asignación de bonos
            double bono = 0;
            switch (departamento.toLowerCase()) {
                case "gerencia":
                    bono = 3.50;
                    break;
                case "auditoria":
                    bono = 1.75;
                    break;
                case "tecnologia":
                    bono = 2.25;
                    break;
                case "contabilidad":
                    bono = 2.00;
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Departamento no reconocido. No se aplicará bono.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
            //Operación para calcular el pago de las horas extra
            double pagoHorasExtra = (salario / 30) * horas + (bono * horas);
            //Impresión de los datos en ResultArea
            resultadoArea.append("Resultados para el empleado " + nombre + ":\n");
            resultadoArea.append("Nombre: " + nombre + "\n");
            resultadoArea.append("Salario: $" + salario + "\n");
            resultadoArea.append("Departamento: " + departamento + "\n");
            resultadoArea.append("Horas realizadas en el mes: " + horas + "\n");
            resultadoArea.append("Pago total de horas extras: $" + pagoHorasExtra + "\n\n");

            // Limpiar campos para el siguiente registro
            nombreField.setText("");
            salarioField.setText("");
            horasField.setText("");

            registroActual++;

            // Verificar si se han ingresado todos los registros
            if (registroActual >= numRegistros) {
                JOptionPane.showMessageDialog(this, "Se han ingresado todos los registros.", "Fin", JOptionPane.INFORMATION_MESSAGE);
                siguienteButton.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
