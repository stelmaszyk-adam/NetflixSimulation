package OneItem;

import Controler.ControlApp;
import Items.*;
import MainProgram.CreateItems;
import MainProgram.MainController;
import MainProgram.option;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    Produkt produkt;
    @FXML
    Label FXnazwa;
    @FXML
    ImageView FXimageView;
    @FXML
    Label FXopis;
    @FXML
    Label FXdataProdukcji;
    @FXML
    Label FXczasTrwania;
    @FXML
    Label FXdystrybutor;
    @FXML
    Label FsXkrajeProdukcji;
    @FXML
    Label FXocenaUzytkownika;
    @FXML
    Label FXCena;
    @FXML
    Label linkiDoZwiastunow;
    @FXML
    Label liczbaSeriali;
    @FXML
    Label ilePoKupnie;
    @FXML
    Label kiedyNajlepiejOgladac;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    @FXML
    private LineChart<?, ?> LineChart;

    @FXML
    private void usunIteM(ActionEvent actionEvent){
        Stage stage = (Stage) FXnazwa.getScene().getWindow();
        stage.close();
        MainController.listView.remove(produkt);
        CreateItems.listaProduktow.remove(produkt);
        ControlApp.listaWszystkichSprzedawcow.get(produkt.getDystrybutor()).getProduktyDystrybutora().remove(produkt);
        for(Uzytkownik kupujacy : ControlApp.listaWszystkichUzytkownikow){
            for(Produkt produkt1 : kupujacy.getWykupioneBezAbonamentu()) {
                if (produkt.getId().equals(produkt1.getId())) {
                    kupujacy.getWykupioneLivy().remove(produkt);
                }
            }
            for(Produkt produkt1 : kupujacy.getWykupioneBezAbonamentu()){
                if(produkt1.getId().equals(produkt.getId())){
                    kupujacy.getWykupioneBezAbonamentu().remove(produkt);
                }
            }
        }
    }

    public void create(String id_item)
    {
        for(Produkt p : CreateItems.listaProduktow)
        {
            if(p.getId().equals(id_item))
            {
                produkt = p;
                break;
            }
        }
        char rodzaj = id_item.charAt(0);
        zaladowacToSamo();
        switch (rodzaj)
        {
            case('s'):
                TworzenieWykresu();
                zaladujFilm();
                break;
            case('f'):
                TworzenieWykresu();
                zaladujSerial();
                break;
            case('l'):
                zaladujLiveStreaming();
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    public void zaladujFilm(){
        System.out.println("Czesc tutaj jestem1");
        //linkiDoZwiastunow.setVisible(true);
        //ilePoKupnie.setVisible(true);
        //Film film = new Film("Action","f", -1);
        //film = (Film) produkt;
        //ilePoKupnie.setText(Double.toString(film.getCena()));
        //String linkow="Lista linkow do zwiastnunow: ";
                /*(for(String link : film.getLinkiDoZwiastunow()){
                    linkow = linkow +"\n" + link ;
                }
                linkiDoZwiastunow.setText(linkow);
                //liczbaSeriali.setDisable(true);*/
    }

    public void zaladujSerial(){
        System.out.println("Czesc tutaj jestem2");
        liczbaSeriali.setVisible(true);
        //SerialFilm serialFilm = (SerialFilm) produkt;
        Serial serial = (Serial) produkt;
        liczbaSeriali.setText(Integer.toString(serial.getLiczbaSezonow()));
        //serial = (Serial) produkt;
        liczbaSeriali.setText("Liczba seriali: "+Integer.toString(serial.getLiczbaSezonow()));
        //linkiDoZwiastunow.setDisable(false);
    }

    public void zaladujLiveStreaming(){
        LiveStreaming liveStreaming = (LiveStreaming) produkt;
        FXdataProdukcji.setText("Data wydarzenia: "+liveStreaming.getDataWyswietlenia());

    }

    private void zaladowacToSamo() {
        FXnazwa.setText(produkt.getNazwa());
        Image image = new Image("/Resources/"+produkt.getZdjecie()+".jpg");
        FXimageView.setImage(image);
        FXopis.setText("Opis: "+produkt.getOpis());
        FXczasTrwania.setText("Czas trwania: "+Double.toString(produkt.getCzasTrwania()));
        FXdystrybutor.setText("Dystrybutor: nr."+Integer.toString(produkt.getDystrybutor()+1));
        FsXkrajeProdukcji.setText("Kraje produkcji: "+produkt.getKrajeProdukcji());
        FXocenaUzytkownika.setText("Ocena: "+Double.toString(produkt.getOcenaUzytkownika()));
        FXCena.setText("Cena produktu: "+Double.toString(produkt.getCena()));

    }

    private void TworzenieWykresu()
    {
        XYChart.Series series = new XYChart.Series();
        SerialFilm serialFilm = new SerialFilm("Drama", "f2", -1);
        serialFilm = (SerialFilm) produkt;
        FXdataProdukcji.setText("Data produkcji: "+produkt.getDataProdukcji());
        int i = 0;
        LineChart.setVisible(true);
        for(int punkty : serialFilm.getOgladalnoscWCzasie())
        {
            series.getData().add(new XYChart.Data(Integer.toString(i),punkty));
            i++;
        }

        kiedyNajlepiejOgladac.setVisible(true);
        switch (serialFilm.getKiedyNajlepiejOgladac()){
            case 0:
                kiedyNajlepiejOgladac.setText("Monday is the best day for watching");
                break;
            case 1:
                kiedyNajlepiejOgladac.setText("Tuesday is the best day for watching");
                break;
            case 2:
                kiedyNajlepiejOgladac.setText("Wednesday is the best day for watching");
                break;
            case 3:
                kiedyNajlepiejOgladac.setText("Thursday is the best day for watching");
                break;
            case 4:
                kiedyNajlepiejOgladac.setText("Friday is the best day for watching");
                break;
            case 5:
                kiedyNajlepiejOgladac.setText("Saturday is the best day for watching");
                break;
            case 6:
                kiedyNajlepiejOgladac.setText("Sunday is the best day for watching");
                break;

        }
        LineChart.getData().addAll(series);
    }


}
