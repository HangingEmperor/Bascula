package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerInformation implements Initializable {

    @FXML
    private TextArea textfieldAllData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textfieldAllData.setText(Data.getAllSaveData());
    }
}
