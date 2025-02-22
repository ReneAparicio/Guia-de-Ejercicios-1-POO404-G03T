import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio5 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PagoHorasExtra().setVisible(true);
            }
        });
    }
}

class PagoHorasExtra extends JFrame {
    private JTextField nombreField, salarioField, horasField;
    private JComboBox<String> departamentoCombo;
    private JTextArea resultadoArea;

    public PagoHorasExtra() {
        setTitle("Calculadora de Horas Extras");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Nombre: "));
        nombreField = new JTextField();
        inputPanel.add(nombreField);

        inputPanel.add(new JLabel("Salario: "));
        salarioField = new JTextField();
        inputPanel.add(salarioField);

        inputPanel.add(new JLabel("Horas: "));
        horasField = new JTextField();
        inputPanel.add(horasField);

        inputPanel.add(new JLabel("Departamento: "));
        String[] departamentos = {"Gerencia", "Auditoria", "Tecnologia", "Contabilidad"};
        departamentoCombo = new JComboBox<>(departamentos);
        inputPanel.add(departamentoCombo);

        JButton calcular = new JButton("Calcular");
        inputPanel.add(calcular);

        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        calcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPagoHorasExtra();
            }
        });
    }

    private void calcularPagoHorasExtra() {
        try {
            String nombre = nombreField.getText();
            double salario = Double.parseDouble(salarioField.getText());
            String departamento = (String) departamentoCombo.getSelectedItem();
            int horas = Integer.parseInt(horasField.getText());

            if (horas > 20) {
                JOptionPane.showMessageDialog(this, "Advertencia: Se ha registrado más de 20 horas extra. Se tomarán como 20 horas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                horas = 20;
            }

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

            double pagoHorasExtra = (salario / 30) * horas + (bono * horas);

            resultadoArea.append("Resultados para el empleado " + nombre + ":\n");
            resultadoArea.append("Nombre: " + nombre + "\n");
            resultadoArea.append("Salario: $" + salario + "\n");
            resultadoArea.append("Departamento: " + departamento + "\n");
            resultadoArea.append("Horas realizadas en el mes: " + horas + "\n");
            resultadoArea.append("Pago total de horas extras: $" + pagoHorasExtra + "\n\n");

            nombreField.setText("");
            salarioField.setText("");
            horasField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
