package MainProgram;

import Controler.ControlApp;
import Items.*;
import OneClient.UzytkownikControler;
import OneItem.ItemController;
import OneSeller.SellerController;
import SerializacjaResource.Serializacja;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.PipedReader;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController extends ListView<Produkt> implements Initializable, Runnable{

    private static final Random RANDOM = new Random();
    private Thread thread;
    public static String textPrice;
    @FXML
    private JFXListView<Produkt> listItems;
    @FXML
    private JFXListView<Uzytkownik> listCustomers;
    @FXML
    private JFXListView<Sellers> listSellers;
    @FXML
    private JFXListView<Produkt> listDiscounts;
    @FXML
    private ComboBox<option> combobox;
    @FXML
    private TextField wyszukiwanyTekst;
    @FXML
    private  Label outPutRevenue;



    public static ObservableList<Produkt> listView = FXCollections.observableArrayList();
    public static ObservableList<Sellers> listViewS = FXCollections.observableArrayList();
    public static ObservableList<Uzytkownik> listViewC = FXCollections.observableArrayList();
    public static ObservableList<Produkt> listViewD = FXCollections.observableArrayList();

    @FXML
    public void szukajPoText(ActionEvent event)
    {
        WyszukiwanieTekstowe.Wyszukaj(wyszukiwanyTekst.getText());
    }

    @FXML
    public void CreateItem(ActionEvent event)
    {
        //SerializacjaResource.Serializacja.odczytZPliku();
        int indexLosowy = RANDOM.nextInt(ControlApp.listaWszystkichSprzedawcow.size());
        switch (((option)this.combobox.getValue()).toString())
        {
            case "Serial":
                CreateItems.CreateSerial(ControlApp.listaWszystkichSprzedawcow.get(indexLosowy));
                break;
            case "Film":
                CreateItems.CreateFilm(ControlApp.listaWszystkichSprzedawcow.get(indexLosowy));
                break;
            case "LiveStreaming":
                CreateItems.CreateLiveStreaming(ControlApp.listaWszystkichSprzedawcow.get(indexLosowy));
                break;
            case "Customer":
                ControlApp.utworzUzytkownika();
                break;
            case "Seller":
                ControlApp.utworzDystrybutora();
                break;
        }
    }


    @FXML
    public void OnClickSerialize(ActionEvent event){
        SerializacjaResource.Serializacja.zapisDoPliku();
    }

    @FXML
    public void OnClickLoad(ActionEvent event){
        Serializacja.odczytZPliku();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        outPutRevenue.setText(textPrice);
        System.out.println("WEszlo tutaj");
    }


    static class Cell extends ListCell<Produkt>
    {
        HBox hbox = new HBox();
        Button btn = new Button("More Info");
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
                hbox.getChildren().addAll(img, label, pane, btn);
                hbox.setHgrow(pane, Priority.ALWAYS);
                //btn.setOnAction(e->getListView().getItems().remove(getItem()));//usuwanie elementu
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println(label1.getText());
                        goToItem(label1.getText());
                    }
                });
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

                //tu trzeba dodac wczytywanie image
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

        private synchronized void goToItem(String id_item) {
            try{
                Stage userStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = (Pane) loader.load(getClass().getResource("/OneItem/itemFXML.fxml").openStream());

                ItemController itemController = (ItemController) loader.getController();
                itemController.create(id_item);

                Scene scene= new Scene(root);
                userStage.setScene(scene);
                userStage.setTitle("Item");
                //
                // userStage.setResizable(false);
                userStage.show();
            }catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }


    static class CellS extends ListCell<Sellers>
    {
        HBox hbox = new HBox();
        Button btn = new Button("More Info");
        Label label = new Label("");
        Label label1 = new Label("");
        Pane pane = new Pane();
        Image profile = new Image("/Resources/Action1.jpg"); // to napewno trzeba zmienic
        ImageView img = new ImageView(profile);
        Object object = new Object();


        public CellS()
        {
            super();
            synchronized (object) {
                changeSize();
                hbox.getChildren().addAll(img, label, pane, btn);
                hbox.setHgrow(pane, Priority.ALWAYS);
                //btn.setOnAction(e->getListView().getItems().remove(getItem()));//usuwanie elementu
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println(label1.getText());
                        goToItem(label1.getText());
                    }
                });
            }
        }

        public synchronized void updateItem(Sellers seller, boolean empty)
        {
            super.updateItem(seller, empty);
            setText(null);
            setGraphic(null);

            if(seller != null && !empty)
            {
                label.setText("Seller nr."+Integer.toString(seller.getId()));
                label1.setText(Integer.toString(seller.getId()));
                Image profile = new Image("/Resources/producer.jpg");
                img.setImage(profile);
                //tu trzeba dodac wczytywanie image
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

        private void goToItem(String id_item) {
            try{
                Stage userStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = (Pane) loader.load(getClass().getResource("/OneSeller/sellerFXML.fxml").openStream());

                SellerController sellerController = (SellerController) loader.getController();
                sellerController.create(id_item);

                Scene scene= new Scene(root);
                userStage.setScene(scene);
                userStage.setTitle("Seller");
                //
                // userStage.setResizable(false);
                userStage.show();
            }catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    static class CellC extends ListCell<Uzytkownik>
    {
        HBox hbox = new HBox();
        Button btn = new Button("More Info");
        Label label = new Label("");
        Label label1 = new Label("");
        Pane pane = new Pane();
        Image profile = new Image("/Resources/Action1.jpg"); // to napewno trzeba zmienic
        ImageView img = new ImageView(profile);
        Object  object = new Object();


        public CellC()
        {
            super();
            synchronized (object){
                changeSize();
                hbox.getChildren().addAll(img, label, pane, btn);
                hbox.setHgrow(pane, Priority.ALWAYS);
                //btn.setOnAction(e->getListView().getItems().remove(getItem()));//usuwanie elementu
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println(label1.getText());
                        goToItem(label1.getText());
                    }
                });

            }
        }

        public synchronized void updateItem(Uzytkownik uzytkownik, boolean empty)
        {
            super.updateItem(uzytkownik, empty);
            setText(null);
            setGraphic(null);

            if(uzytkownik != null && !empty)
            {
                label.setText(uzytkownik.getImie());
                label1.setText(Integer.toString(uzytkownik.getId()));
                Image profile = new Image("/Resources/user.jpg");
                img.setImage(profile);
                //tu trzeba dodac wczytywanie image
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

        private void goToItem(String id_item) {
            try{
                Stage userStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = (Pane) loader.load(getClass().getResource("/OneClient/uzytkownikFXML.fxml").openStream());

                UzytkownikControler uzytkownikControler = (UzytkownikControler) loader.getController();
                uzytkownikControler.create(id_item);

                Scene scene= new Scene(root);
                userStage.setScene(scene);
                userStage.setTitle("Item");
                //
                // userStage.setResizable(false);
                userStage.show();
            }catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    static public void dodawanieDoListViewProdukt(Produkt produkt){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
               listView.add(produkt);

            }
        });
    }

    static public void dodawanieDoListViewCustomer(Uzytkownik uzytkownik){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                listViewC.add(uzytkownik);
            }
        });
    }

    static public void dodawanieDoListViewSeller(Sellers sellers){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                listViewS.add(sellers);
            }
        });
    }

    static public void dodawanieDoListViewDicount(Produkt produkt){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                listViewD.add(produkt);
            }
        });
    }

    static public void usuwanieZListyViewDicount(int number){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                listViewD.remove(number);
            }
        });
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //listView.add(); Dodawanie przed tym z bazy danych stringow
        listItems.setItems(listView);
        listSellers.setItems(listViewS);
        listCustomers.setItems(listViewC);
        listDiscounts.setItems(listViewD);
        //outPutRevenue.setText(textPrice);
        //thread = new Thread();
        //thread.start();

        this.combobox.setItems(FXCollections.observableArrayList(option.values()));
        listItems.setCellFactory(param -> new Cell());
        listSellers.setCellFactory(param -> new CellS());
        listCustomers.setCellFactory(param -> new CellC());
        listDiscounts.setCellFactory(param -> new Cell());
    }
}
