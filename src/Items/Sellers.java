package Items;

import Controler.ControlApp;
import MainProgram.CreateItems;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Sellers implements Runnable, Serializable {

    transient private Thread thread;
    private static final Random RANDOM = new Random();
    private int id;
    private String umowaLicencyjnaNumer;
    private int ilePrzelac;
    private double zarobki=0;
    public ArrayList<Produkt> produktyDystrybutora;// zrobic przejscie na wskaznikach tego elementu


    public Sellers() {
        produktyDystrybutora = new ArrayList<>();
        this.id = ControlApp.listaWszystkichSprzedawcow.size();
        tworzenieProduktowPrzezDystrybutora();
        ilePrzelac = createilePrzelac();
        createThread();
    }

    public void createThread(){
        thread = new Thread(this);
        thread.start();
    }

    public void stop(){
        thread.stop();
    }

    public int createilePrzelac(){
        int randoCena = RANDOM.nextInt(500)+500;
        return randoCena;
    }

    public String getUmowaLicencyjnaNumer() {
        return umowaLicencyjnaNumer;
    }

    public void setUmowaLicencyjnaNumer(String umowaLicencyjnaNumer) {
        this.umowaLicencyjnaNumer = umowaLicencyjnaNumer;
    }

    public int getIlePrzelac() {
        zarobki = zarobki + ilePrzelac;
        return ilePrzelac;
    }

    public void setIlePrzelac(int ilePrzelac) {
        this.ilePrzelac = ilePrzelac;
    }

    public double getZarobki() {
        return zarobki;
    }

    public void setZarobki(double zarobki) {
        this.zarobki = zarobki;
    }

    public ArrayList<Produkt> getProduktyDystrybutora() {
        return produktyDystrybutora;
    }

    public void setProduktyDystrybutora(ArrayList<Produkt> produktyDystrybutora) {
        this.produktyDystrybutora = produktyDystrybutora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void tworzenieProduktowPrzezDystrybutora(){
        CreateItems.CreateSerial(this);
        CreateItems.CreateFilm(this);
        CreateItems.CreateLiveStreaming(this);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("WatekS: "+id);
            try {
                ControlApp.updateProgramu(this);
                int ileCzekania = RANDOM.nextInt(50);
                ileCzekania = ileCzekania*100;
                Thread.sleep(ileCzekania);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
