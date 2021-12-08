package com.example.utilitaire.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Menu menuIMCcalc;
    @FXML
    private MenuItem yes;
    @FXML
    private AnchorPane main;
    @FXML
    private HBox formIMCcalc;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main.getChildren().removeAll(formIMCcalc);
        menuIMCcalc.setOnMenuValidation(launchbinary ->{
            main.getChildren().removeAll(formIMCcalc);
            main.getChildren().add(formIMCcalc);
        });
        yes.setOnMenuValidation(exit ->{
            Platform.exit();
        });
    }
}