package com.example.utilitaire.controller;

import com.example.utilitaire.objet.General;
import com.example.utilitaire.objet.Soldat;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArmadaController implements Initializable {
    List<General> armada = new ArrayList<>();
    List<Soldat> green = new ArrayList<>();
    TreeItem<String> armadatree = new TreeItem<>("Armée");
    @FXML
    private TextField nameSelect;
    @FXML
    private TextField hpSelect;
    @FXML
    private TreeView treemada;
    @FXML
    private MenuItem createg;
    @FXML
    private MenuItem create;
    public void treeRefresh(){
        treemada.setRoot(null);
        TreeItem<String> armadatree = new TreeItem<>("Armée");
        for (int j = 0; j < armada.size(); j++) {
            TreeItem<String> generaltree = new TreeItem<>(armada.get(j).Matricule());
            if (armada.get(j).haveTroupe()) {
                for (int i = 0; i < armada.get(j).numTroupe(); i++) {
                    TreeItem<String> soldattree = new TreeItem<>(armada.get(j).troupeAssign(i));
                    generaltree.getChildren().add(soldattree);
                }
            }
            armadatree.getChildren().add(generaltree);
        }
        treemada.setRoot(armadatree);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        treeRefresh();
        create.setOnAction(create -> {
            General selectedgeneral = new General();
            MultipleSelectionModel msm = treemada.getSelectionModel();
            int selecte = -1;
            for (int i = 0; i < armada.size(); i++) {
                if ((msm.getSelectedItem() + "").equals("TreeItem [ value: " + armada.get(i).Matricule() + " ]")){
                    selecte=i;
                }
            }
            if (selecte != -1) {
                selectedgeneral.General(armada.get(selecte).Matricule(), armada.get(selecte).getTroupe());
                Soldat recrue = new Soldat();
                recrue.Soldat("Soldat" + (selectedgeneral.numTroupe() + 1),10);
                selectedgeneral.addSoldat(recrue);
                armada.set(selecte, selectedgeneral);
                treeRefresh();
            }
        });
        createg.setOnAction(createg -> {
            General recrue = new General();
            List<Soldat> troupe = new ArrayList<>();
            recrue.General("Général"+(armada.size()+1),troupe);
            armada.add(recrue);
            treeRefresh();
        });
        treemada.setOnMouseClicked(selection ->{
            MultipleSelectionModel msm = treemada.getSelectionModel();
            int selecte = -1;
            int selectesol = -1;
            for (int i = 0; i < armada.size(); i++) {
                if ((msm.getSelectedItem() + "").equals("TreeItem [ value: " + armada.get(i).Matricule() + " ]")){
                    selecte=i;
                    i=armada.size();
                }
            }
            if (selecte == -1){
                for (int i = 0; i < armada.size(); i++) {
                    System.out.println(i + "i");
                    for (int j = 0; j < (armada.get(i).numTroupe()); j++) {
                        System.out.println(j + "j");
                        if ((msm.getSelectedItem() + "").equals("TreeItem [ value: " + armada.get(i).troupeAssign(j) + " ]")){
                            selecte=i;
                            selectesol=j;
                            j=armada.get(i).numTroupe();
                            i=armada.size();
                        }
                    }
                }
            }
            if (selectesol == -1 && selecte != -1) {
                nameSelect.setText(armada.get(selecte).Matricule());
            }
            if (selectesol != -1 && selecte != -1) {
                nameSelect.setText(armada.get(selecte).troupeAssign(selectesol));
                hpSelect.setText(armada.get(selecte).troupeAssignStatut(selectesol)+"");
            }
        });
    }
}