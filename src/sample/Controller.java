package sample;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane panelBackground;
    @FXML
    private Button buttonViewData;
    @FXML
    private Label labelKilogramos;
    @FXML
    private TextField textfieldLibras;
    @FXML
    private Slider sliderKilogramos;
    @FXML
    private Slider sliderLibras;
    @FXML
    private Pane panelBar;
    @FXML
    private Label labelTitle;
    @FXML
    private Button buttonSaveData;
    @FXML
    private Label labelLibras;
    @FXML
    private TextField textfieldKilogramos;

    @FXML
    void putData(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (textfieldKilogramos.getText().matches("[a-zA-Z]+")
                    || textfieldKilogramos.getText().matches("[0-9]+[a-zA-Z]+")
                    || textfieldKilogramos.getText().matches("[a-zA-Z]+[0-9]+")) {
                JOptionPane.showMessageDialog(null, "Solo se admiten digitos.");
                textfieldKilogramos.setText("");
            } else {
                sliderKilogramos.setValue(Double.parseDouble(textfieldKilogramos.getText()));
                // falta colocar slider libras
                labelKilogramos.setText(textfieldKilogramos.getText() + " kg");
            }
        }
    }

    @FXML
    void saveData(ActionEvent event) {

    }

    @FXML
    void viewData(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Falta mover los sliders al desplazar uno
        sliderKilogramos.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelKilogramos.setText("Kilogramos: " + (float) sliderKilogramos.getValue() + " kg");
            labelLibras.setText("Libras: " + (float) (sliderKilogramos.getValue() * 2.20462) + " lb");
        });

        sliderLibras.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelLibras.setText("Kilogramos: " + (float) sliderLibras.getValue() + " lb");
            labelKilogramos.setText("Libras: " + (float) (sliderLibras.getValue() * 0.453592) + " kg");

        });
    }
}
