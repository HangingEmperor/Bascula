package sample;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerHome implements Initializable {

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
        /* Falta poder modificar ambas cosas al hacer Enter. */
        if (event.getCode() == KeyCode.ENTER) {
            if (textfieldKilogramos.getText().matches("[a-zA-Z]+")
                    || textfieldKilogramos.getText().matches("[0-9]+[a-zA-Z]+")
                    || textfieldKilogramos.getText().matches("[a-zA-Z]+[0-9]+")) {
                textfieldKilogramos.setText("");
            } else {
                sliderKilogramos.setValue(Double.parseDouble(textfieldKilogramos.getText()));
                labelKilogramos.setText("Kilogramos: " + textfieldKilogramos.getText() + " kg");
            }
        }
    }

    @FXML
    void saveData(ActionEvent event) {
        File file = new File("log.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Date ahora = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

            String aux = "";
            String oldData = "";
            FileReader hoja = new FileReader(file);
            BufferedReader lee = new BufferedReader(hoja);
            while ((aux = lee.readLine()) != null) {
                oldData += aux + "\n";
            }
            lee.close();

            FileWriter archivo = new FileWriter(file);
            archivo.append(oldData);
            archivo.append("Fecha: " + formateador.format(ahora));
            archivo.append(" | KG: " + labelKilogramos.getText() + " , LB: " + labelLibras.getText());
            archivo.close();
        } catch (IOException ex) {
            System.out.println("Hubo un error");
        }
    }

    @FXML
    void viewData(ActionEvent event) {
        File file = new File("log.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            String aux = "";
            String oldData = "";

            FileReader hoja = new FileReader(file);
            BufferedReader lee = new BufferedReader(hoja);
            while ((aux = lee.readLine()) != null) {
                oldData += aux + "\n";
            }
            lee.close();

            Data.setAllSaveData(oldData);
            Parent root = FXMLLoader.load(getClass().getResource("fxml/information.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("View data");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException io) {
            JOptionPane.showConfirmDialog(null, "No se encontro el archivo");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sliderKilogramos.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelKilogramos.setText("Kilogramos: " + (float) sliderKilogramos.getValue() + " kg");
            labelLibras.setText("Libras: " + (float) (sliderKilogramos.getValue() * 2.20462) + " lb");
            sliderLibras.setValue(sliderKilogramos.getValue() * 2.20462);
        });

        sliderLibras.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelLibras.setText("Kilogramos: " + (float) sliderLibras.getValue() + " lb");
            labelKilogramos.setText("Libras: " + (float) (sliderLibras.getValue() * 0.453592) + " kg");
        });
    }
}
