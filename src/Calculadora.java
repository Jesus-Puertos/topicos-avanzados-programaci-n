/*Autor Jesus Alberto Rodriguez Puertos*/

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {
    private JTextField display;
    private double valor1 = 0;
    private String operador = "";
    private Boolean inicio = true;

    //Constructor de la pantalla
    public Calculadora(){
        setTitle("Calculadora de Jesus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,400);
        setLayout(new BorderLayout());

        //Terminando de crear objeto y dandole caracteristicas
        display = new JTextField();
        display.setFont(new Font("System-ui", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        //Crear el borde interno
        Border bordeInterno = BorderFactory.createEmptyBorder(10,20,10,20);
        //Crear el borde externo
        Border bordeExterno = BorderFactory.createEmptyBorder(10,20,10,20);
        //Crear linea entre los bordes
        Border linea = BorderFactory.createMatteBorder(1,1,1,1,Color.PINK);
        //Combinar los bordes y la linea
        Border bordeFinal = BorderFactory.createCompoundBorder(bordeExterno, BorderFactory.createCompoundBorder(linea,bordeInterno));
        display.setBorder(bordeFinal);

        //Agregar a la ventana el dispplay
        add(display, BorderLayout.NORTH);

        //Creacion del panel que tendra los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4,4,10,10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        String[] botones = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0",".","=","+"
        };
        for(String boton : botones){
            JButton btn = new JButton(boton);
            btn.setFont(new Font("System-ui", Font.PLAIN,18));
            btn.addActionListener(new BotonClickListener());
            panelBotones.add(btn);
        }
        add(panelBotones, BorderLayout.CENTER);
    }

    private void handleNumber(String buttonText) {
        if (inicio) {
            display.setText(buttonText);
            inicio = false;
        } else {
            display.setText(display.getText() + buttonText);
        }
    }

    private void handleDecimal(String buttonText) {
        if (!display.getText().contains(".")) {
            display.setText(display.getText() + buttonText);
        }
    }

    private void handleOperator(String buttonText) {
        valor1 = Double.parseDouble(display.getText());
        operador = buttonText;
        inicio = true;
    }

    private void handleEquals() {
        double valor2 = Double.parseDouble(display.getText());
        switch (operador) {
            case "+":
                valor1 += valor2;
                break;
            case "-":
                valor1 -= valor2;
                break;
            case "*":
                valor1 *= valor2;
                break;
            case "/":
                if (valor2 != 0) {
                    valor1 /= valor2;
                } else {
                    JOptionPane.showMessageDialog(null, "El número es infinito, no lo podemos calcular. Piensa");
                }
                break;
        }
        display.setText(String.valueOf(valor1));
        inicio = true;
    }

    private class BotonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String textoBoton = source.getText();

            switch (textoBoton) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    handleNumber(textoBoton);
                    break;
                case ".":
                    handleDecimal(textoBoton);
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    handleOperator(textoBoton);
                    break;
                case "=":
                    handleEquals();
                    break;
            }
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater( ()-> {
            Calculadora calculadora = new Calculadora();
            calculadora.setVisible(true);
        });
    }
}

