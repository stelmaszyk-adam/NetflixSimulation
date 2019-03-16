package Items;

import Controler.ControlApp;
import MainProgram.CreateItems;
import MainProgram.MainController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Uzytkownik implements Runnable, Abonament, Serializable {



    transient private Thread thread;
    private static final Random RANDOM = new Random();
    private int id;
    private String imie;
    private String dataUrodzenia;
    private String email;
    private String numerKartyKredytowej;
    protected int parametruAbonaemntu1 = 0;
    protected int parametruAbonaemntu2 = 0;
    protected int parametruAbonaemntu3 = 0;
    //private Enum<> abonamentRodzaj; Dodac enumu dobre
    private ArrayList<Integer> numeryProduktow;
    private Boolean czyMaAbonament = false;
    private ArrayList<LiveStreaming> wykupioneLivy;
    private ArrayList<SerialFilm> wykupioneBezAbonamentu;
    private int typAbonamentu = -1;



    private long czasStartuAbonamentu; // Tutaj musi byc wskaznik na ten obiekt


    public Uzytkownik()
    {

        wykupioneLivy = new ArrayList<>();
        numeryProduktow = new ArrayList<>();
        wykupioneBezAbonamentu = new ArrayList<>();
        this.imie = losujImie();
        this.dataUrodzenia = losujDateUrodzenia();
        this.email = this.imie+"@gmail.com";
        this.numerKartyKredytowej = losujNumerKartyKredytowej();
        createThread();
    }

    public void createThread(){
        thread = new Thread(this);
        thread.start();
    }


    public void stop(){
        thread.stop();
    }

    private String losujNumerKartyKredytowej() {
        String numerKartyKredytowej = "";
        for(int i=0; i<16; i++)
        {
            int numerInt = RANDOM.nextInt(10);
            numerKartyKredytowej = numerKartyKredytowej + Integer.toString(numerInt);
        }
        return numerKartyKredytowej;
    }

    private String losujDateUrodzenia() {
        String dataUrodzenia = "";
        int yearInt = RANDOM.nextInt(50)+20;
        yearInt = 2018 - yearInt;
        dataUrodzenia = Integer.toString(yearInt);
        return dataUrodzenia;
    }


    private String losujImie() {
        String name;
        String[] imiePrzykladowe = new String[]{"Oliver","Jake","Bethany","Connor","Harry","Callum",
        "Jacob","Isla","Kyle","Thomas","Joe","Megan","Reece","Oscar","Rhys","James",
        "Samantha","Olivia","Margaret","Amelia"};

        name = imiePrzykladowe[RANDOM.nextInt(imiePrzykladowe.length)];
        return name;
    }

    public void wyswietlanie() {
        int randoProdukt = RANDOM.nextInt(CreateItems.listaProduktow.size());
        ControlApp.pieniadzeZarobione = ControlApp.pieniadzeZarobione + CreateItems.listaProduktow.get(randoProdukt).getCena();
    }



    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumerKartyKredytowej() {
        return numerKartyKredytowej;
    }

    public void setNumerKartyKredytowej(String numerKartyKredytowej) {
        this.numerKartyKredytowej = numerKartyKredytowej;
    }

    public ArrayList<LiveStreaming> getWykupioneLivy() {
        return wykupioneLivy;
    }

    public void setWykupioneLivy(ArrayList<LiveStreaming> wykupioneLivy) {
        this.wykupioneLivy = wykupioneLivy;
    }

    public ArrayList<SerialFilm> getWykupioneBezAbonamentu() {
        return wykupioneBezAbonamentu;
    }

    public void setWykupioneBezAbonamentu(ArrayList<SerialFilm> wykupioneBezAbonamentu) {
        this.wykupioneBezAbonamentu = wykupioneBezAbonamentu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getCzyMaAbonament() {
        return czyMaAbonament;
    }

    public void setCzyMaAbonament(Boolean czyMaAbonament) {
        this.czyMaAbonament = czyMaAbonament;
    }

    public long getCzasStartuAbonamentu() {
        return czasStartuAbonamentu;
    }

    public void setCzasStartuAbonamentu(long czasStartuAbonamentu) {
        this.czasStartuAbonamentu = czasStartuAbonamentu;
    }

    @Override
    public void run() {
        while (true) {
            //to nie to ale niech tu bedzie
            System.out.println("WatekU: " + this.imie);
            try {
                Thread.sleep(10000);
                uzytkownikaRuchy();
                uzytkownkWidzial();
                sprawdzanieDiscount();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void uzytkownkWidzial() {
        int czyCosDzisiajObejrzec = RANDOM.nextInt(4);
        if (this.czyMaAbonament) {
            switch (czyCosDzisiajObejrzec) {
                case 4:
                    int randoProdukt = RANDOM.nextInt(CreateItems.listaProduktow.size());
                    if (CreateItems.listaProduktow.get(randoProdukt).getId().contains("l")) {
                        for (LiveStreaming liveStreaming : wykupioneLivy) {
                            if (CreateItems.listaProduktow.get(randoProdukt).getId().equals(liveStreaming.getId())) {
                                break;
                            }
                        }
                        wykupioneLivy.remove(CreateItems.listaProduktow.get(randoProdukt));
                    }
                    break;
            }
        }
    }


    public void uzytkownikaRuchy(){

        sprawdzanieczyAbonamentSieNieKonczy();

        int czyKupicAbonament = RANDOM.nextInt(15);

        if(czyKupicAbonament == 14 && (!this.czyMaAbonament)){
            this.czyMaAbonament = true;
            czasStartuAbonamentu = System.currentTimeMillis();
            typAbonamentu = RANDOM.nextInt(3);
            if (typAbonamentu == 0){
                Basic();
            }
            else if(typAbonamentu == 1){
                Family();
            }
            else {
                Premium();
            }
            ControlApp.dodawaniePieniedzy(parametruAbonaemntu1*3);
            return;
        }
        else if(czyMaAbonament && (czyKupicAbonament == 3 || czyKupicAbonament == 7 || czyKupicAbonament == 13)){
            int randoProdukt = RANDOM.nextInt(CreateItems.listaProduktow.size());
            for(int numer : numeryProduktow) {
                if(numer == randoProdukt){
                    return;
                }
            }
            if(CreateItems.listaProduktow.get(randoProdukt).getId().contains("l")){
                wykupioneLivy.add((LiveStreaming) CreateItems.listaProduktow.get(randoProdukt));
                ControlApp.dodawaniePieniedzy((int) CreateItems.listaProduktow.get(randoProdukt).getCena());
            }
        }
        else if(!this.czyMaAbonament && (czyKupicAbonament<3  || czyKupicAbonament>10)) {
            int randoProdukt = RANDOM.nextInt(CreateItems.listaProduktow.size());
            if(CreateItems.listaProduktow.get(randoProdukt).getId().contains("l")){
                wykupioneLivy.add((LiveStreaming) CreateItems.listaProduktow.get(randoProdukt));

            }else{
                wykupioneBezAbonamentu.add((SerialFilm) CreateItems.listaProduktow.get(randoProdukt));
            }
            ControlApp.dodawaniePieniedzy((int) CreateItems.listaProduktow.get(randoProdukt).getCena());
        }
        MainController.textPrice = Double.toString(ControlApp.pieniadzeZarobione);

        //jak bede kupione to dadaje sie do nich hajs za kupno...

    }

    private void sprawdzanieDiscount(){

        int czyJestDiscount = RANDOM.nextInt(15);
        if(czyJestDiscount == 2 || czyJestDiscount == 4 || czyJestDiscount == 6 || czyJestDiscount == 8  ){

            int randomNumer = RANDOM.nextInt(CreateItems.listaProduktow.size());
            for(Produkt produkt : ControlApp.listaDicount){
                if(produkt.getId().equals(CreateItems.listaProduktow.get(randomNumer).getId())){
                    return;
                }
            }
            ControlApp.listaDicount.add(CreateItems.listaProduktow.get(randomNumer));
            MainController.dodawanieDoListViewDicount(CreateItems.listaProduktow.get(randomNumer));
            CreateItems.listaProduktow.get(randomNumer).setPromocjaBool(true);
            CreateItems.listaProduktow.get(randomNumer).setCenaBackUp(CreateItems.listaProduktow.get(randomNumer).getCena());
            CreateItems.listaProduktow.get(randomNumer).setCena(RANDOM.nextInt(20)+10);
            CreateItems.listaProduktow.get(randomNumer).setIleTrwaPromocja(System.currentTimeMillis());
        }
        ArrayList<Integer> coUsunac = new ArrayList<>();
        int i = 0;
        for(Produkt produkt : ControlApp.listaDicount){
            long obecnyCzas = System.currentTimeMillis();
            if(obecnyCzas - produkt.getIleTrwaPromocja() > ControlApp.SZTYWNALICZA){
                coUsunac.add(i);
            }
            i++;
        }
        for(int co : coUsunac){
            CreateItems.listaProduktow.get(co).setPromocjaBool(false);
            CreateItems.listaProduktow.get(co).setCena(CreateItems.listaProduktow.get(co).getCenaBackUp());
            ControlApp.listaDicount.remove(co);
            MainController.usuwanieZListyViewDicount(co);
        }

    }

    private void sprawdzanieczyAbonamentSieNieKonczy() {
        if(czyMaAbonament){
            long obecnyCzas = System.currentTimeMillis();
            if(obecnyCzas - czasStartuAbonamentu > 3*ControlApp.SZTYWNALICZA){
                czyMaAbonament = false;
                czasStartuAbonamentu = 0;
            }
        }
    }

    @Override
    public void Basic() {
        this.parametruAbonaemntu1 = 100;
        this.parametruAbonaemntu2 = 2;
        this.parametruAbonaemntu3 = 4;
    }

    @Override
    public void Family() {
        this.parametruAbonaemntu1 = 150;
        this.parametruAbonaemntu2 = 5;
        this.parametruAbonaemntu3 = 7;
    }

    @Override
    public void Premium() {
        this.parametruAbonaemntu1 = 180;
        this.parametruAbonaemntu2 = 15;
        this.parametruAbonaemntu3 = 20;
    }
}
