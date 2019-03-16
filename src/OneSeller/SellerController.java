package OneSeller;

import Controler.ControlApp;
import Items.Produkt;
import Items.Sellers;
import Items.Uzytkownik;
import MainProgram.CreateItems;
import MainProgram.MainController;
import MainProgram.option;
import OneItem.ItemController;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class SellerController extends ListView<Produkt> implements Initializable {

    private static final Random RANDOM = new Random();
    @FXML
    private Label zarobKI;
    @FXML
    private Label umowaLicencyjnaNumer;
    @FXML
    private Label ilePrzelac;
    @FXML
    private Label labelID;
    @FXML
    private JFXListView<Produkt> produktyDystrybutora;

    public static ObservableList<Produkt> listView = FXCollections.observableArrayList();

    static class Cell extends ListCell<Produkt>
    {
        HBox hbox = new HBox();
        Label label = new Label("");
        Label label1 = new Label("");
        Pane pane = new Pane();
        Image profile = new Image("/Resources/Action1.jpg"); // to napewno trzeba zmienic
        ImageView img = new ImageView(profile);
        Object object = new Object();


        public Cell()
        {
            super();
            synchronized (object) {
                changeSize();
                hbox.getChildren().addAll(img, label, pane);
                hbox.setHgrow(pane, Priority.ALWAYS);
            }
        }

        public synchronized void updateItem(Produkt produkt, boolean empty)
        {
            super.updateItem(produkt, empty);
            setText(null);
            setGraphic(null);

            if(produkt != null && !empty)
            {
                label.setText(produkt.getNazwa());
                label1.setText(produkt.getId());
                Image profile = new Image("/Resources/"+produkt.getZdjecie()+".jpg");
                img.setImage(profile);
                setGraphic(hbox);
            }
        }

        private void changeSize()
        {
            img.setFitHeight(50);
            img.setFitWidth(50);
            label.setPadding(new Insets(5,30,5,30));
            label.setStyle("-fx-font: 15 arial");
        }

    }

    public void create(String uzytkownikString){
        Sellers sellers = ControlApp.listaWszystkichSprzedawcow.get(Integer.valueOf(uzytkownikString));
        listView.clear();
        for(Produkt produkt : sellers.produktyDystrybutora){
            listView.add(produkt);
        }
        zarobKI.setText(Double.toString(sellers.getZarobki()));
        umowaLicencyjnaNumer.setText(sellers.getUmowaLicencyjnaNumer());
        ilePrzelac.setText(Double.toString(sellers.getIlePrzelac()));
        labelID.setText(Integer.toString(sellers.getId()));
    }

    //close i zapomina wszystko




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        produktyDystrybutora.setItems(listView);

        produktyDystrybutora.setCellFactory(param -> new Cell());
    }


}
