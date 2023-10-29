package com.example.calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;

public class MainController {

    @FXML
    private TextArea textoCalculadora;

    private double numeroAnterior = 0;
    private String operador = "";
    private boolean nuevoNumero = true;

    @FXML
    private VBox historyPanel;

    @FXML
    private TextArea historyTextArea;

    @FXML
    private VBox scientificPanel;

    @FXML
    void toggleHistory(ActionEvent event) {
        historyPanel.setVisible(!historyPanel.isVisible());
    }

    @FXML
    void toggleScientific(ActionEvent event) {
        scientificPanel.setVisible(!scientificPanel.isVisible());
    }

    @FXML
    void handleNumberClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String numero = clickedButton.getText();

        if (nuevoNumero) {
            textoCalculadora.setText(numero);
            nuevoNumero = false;
        } else {
            textoCalculadora.setText(textoCalculadora.getText() + numero);
        }
    }

    @FXML
    void handleOperatorClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String nuevoOperador = clickedButton.getText();

        if (!operador.isEmpty()) {
            calcularResultado();
        }

        operador = nuevoOperador;
        numeroAnterior = Double.parseDouble(textoCalculadora.getText());
        nuevoNumero = true;
    }

    @FXML
    void handleEqualsClick(ActionEvent event) {
        calcularResultado();
        operador = "";
    }

    void calcularResultado() {
        double numeroActual = Double.parseDouble(textoCalculadora.getText());
        switch (operador) {
            case "+":
                numeroAnterior += numeroActual;
                break;
            case "-":
                numeroAnterior -= numeroActual;
                break;
            case "*":
                numeroAnterior *= numeroActual;
                break;
            case "/":
                if (numeroActual != 0) {
                    numeroAnterior /= numeroActual;
                } else {
                    textoCalculadora.setText("Error");
                }
                break;
            case "log":
                if (numeroAnterior > 0 && numeroActual > 0) {
                    numeroAnterior = Math.log(numeroActual) / Math.log(numeroAnterior);
                } else {
                    textoCalculadora.setText("Error");
                }
                break;
            case "sin":
                numeroAnterior = Math.sin(numeroActual);
                break;
            case "cos":
                numeroAnterior = Math.cos(numeroActual);
                break;
            case "tan":
                numeroAnterior = Math.tan(numeroActual);
                break;
        }
        textoCalculadora.setText(String.valueOf(numeroAnterior));
        nuevoNumero = true;

        // Historial
        String operacion = numeroAnterior + " " + operador + " " + numeroActual + " = " + numeroAnterior;
        historyTextArea.appendText(operacion + "\n");
    }
}
