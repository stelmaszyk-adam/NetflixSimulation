package OneClient;

import Controler.ControlApp;
import Items.Uzytkownik;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class UzytkownikControler {
    @FXML
    private Label FXimie;
    @FXML
    private Label FXdataurodzenia;
    @FXML
    private Label FXemail;
    @FXML
    private Label FXnumerKarty;
    @FXML
    private Label FXnumerProduktow;
    @FXML
    private Label FXabonament;
    @FXML
    private Label FXwykupioneLivy;
    @FXML
    private Label FXstartAbonamentu;
    @FXML
    private ImageView FXimageView;

    public void create(String uzytkownikString){
        Uzytkownik uzytkownik = ControlApp.listaWszystkichUzytkownikow.get(Integer.valueOf(uzytkownikString));
        FXimie.setText("Name: "+uzytkownik.getImie());
        FXdataurodzenia.setText("Date of birthday: "+uzytkownik.getDataUrodzenia());
        FXemail.setText("Email: "+uzytkownik.getEmail());
        FXnumerKarty.setText("Number of credit card: "+uzytkownik.getNumerKartyKredytowej());
        FXabonament.setText("Subscription: "+Boolean.toString(uzytkownik.getCzyMaAbonament()));
        if(uzytkownik.getCzyMaAbonament()){
            FXstartAbonamentu.setText("Start of subscription: "+String.valueOf(uzytkownik.getCzasStartuAbonamentu()));
        }
        Image profile = new Image("/Resources/user.jpg");
        FXimageView.setImage(profile);
    }


}
