package Controler;

import Items.Abonament;
import Items.Produkt;
import Items.Sellers;
import Items.Uzytkownik;
import MainProgram.CreateItems;
import MainProgram.MainController;

import java.util.ArrayList;
import java.util.Random;

public class ControlApp  {
    private static final Random RANDOM = new Random();
    public static final int SZTYWNALICZA = 300000;
    public static long poczatkowyCzas = 0;
    public static double pieniadzeZarobione = 0;
    public static ArrayList<Sellers> listaWszystkichSprzedawcow;
    public static ArrayList<Uzytkownik> listaWszystkichUzytkownikow;
    public static ArrayList<Produkt> listaDicount;

    private static int ileMiesiecyMinelo = 0;

    public static void startControlApp(){

        CreateItems.UtworzPoczatkowaListe();
        poczatkowyCzas = System.currentTimeMillis();
        listaWszystkichSprzedawcow = new ArrayList<>();
        listaWszystkichUzytkownikow = new ArrayList<>();
        listaDicount = new ArrayList<>();
        utworzDystrybutora();
        utworzUzytkownika();
    }

    public static void updateProgramu(Sellers sellers) {
        long obecnyCzas = System.currentTimeMillis();
        if(obecnyCzas >= (SZTYWNALICZA+poczatkowyCzas))
        {
            poczatkowyCzas = obecnyCzas;
            placenieDystrybutora(sellers.getIlePrzelac());
            if(ileMiesiecyMinelo < 3)
            {
                ileMiesiecyMinelo++;
            }
            else {
               if (pieniadzeZarobione < 0){
                   System.out.println("ERROR");
               }

            }
        }

        int czyTworzymyNowyItem = RANDOM.nextInt(6);
        if(czyTworzymyNowyItem==5){
            int coTworzymy = RANDOM.nextInt(3);
            switch (coTworzymy) {
                case 0:
                    CreateItems.CreateSerial(sellers);
                    break;
                case 1:
                    CreateItems.CreateFilm(sellers);
                    break;
                case 2:
                    CreateItems.CreateLiveStreaming(sellers);
                    break;
            }
        }
        int czyTworzymyNowegoUzytkownika = RANDOM.nextInt(3);
        if(czyTworzymyNowegoUzytkownika == 2)
        {
            utworzUzytkownika();
        }
    }

    public static synchronized void utworzDystrybutora(){
        Runnable runners = new Sellers();
        listaWszystkichSprzedawcow.add((Sellers) runners);
        MainController.dodawanieDoListViewSeller((Sellers) runners);

    }

    public static synchronized void utworzUzytkownika() {
        Runnable runners = new Uzytkownik();
        listaWszystkichUzytkownikow.add((Uzytkownik) runners);
        MainController.dodawanieDoListViewCustomer((Uzytkownik) runners);

    }

    public static void dodawaniePieniedzy(int zarabionePieniadze){
        pieniadzeZarobione = pieniadzeZarobione + zarabionePieniadze;
    }

    public static void placenieDystrybutora(int placonaKwota){
        pieniadzeZarobione = pieniadzeZarobione - placonaKwota;
    }


}
