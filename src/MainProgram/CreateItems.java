package MainProgram;

import Items.*;

import java.util.ArrayList;
import java.util.Random;

public class  CreateItems {

    private static final Random RANDOM = new Random();
    public static int idF = 0;
    public static int idS = 0;
    public static int idL = 0;
    public static ArrayList<Produkt> listaProduktow;
    public static void UtworzPoczatkowaListe()
    {
        listaProduktow = new ArrayList<>();
    }

    public static void CountItemsF()
    {
        idF++;
    }
    public static void CountItemsS()
    {
        idS++;
    }
    public static void CountItemsL()
    {
        idL++;
    }

    public static synchronized void CreateSerial(Sellers sellers) {
        Serial serial = new Serial(losowanieGatunku(), "f", sellers.getId());
        sellers.produktyDystrybutora.add(serial);
        MainController.dodawanieDoListViewProdukt(serial);
        listaProduktow.add(serial);
    }
    public static synchronized void CreateFilm(Sellers sellers) {
        Film film = new Film(losowanieGatunku(), "s",sellers.getId());
        sellers.produktyDystrybutora.add(film);
        MainController.dodawanieDoListViewProdukt(film);
        listaProduktow.add(film);

    }
    public static synchronized void CreateLiveStreaming(Sellers sellers) {
        LiveStreaming liveStreaming = new LiveStreaming("l",sellers.getId());
        sellers.produktyDystrybutora.add(liveStreaming);
        MainController.dodawanieDoListViewProdukt(liveStreaming);
        listaProduktow.add(liveStreaming);
    }

    private static String losowanieGatunku()
    {
        String gatunek="";
        String [] listaNazw = new String[]{"Action","Drama","Comedy","Cartoon","Documentary","Criminal"};
        gatunek = listaNazw[RANDOM.nextInt(listaNazw.length)];
        return gatunek;
    }


}
