package Items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SerialFilm extends Produkt implements Serializable {

    private static final Random RANDOM = new Random();

    private int kiedyNajlepiejOgladac;
    //enum rodzajSerialu; Musze zaincjalizowac enum
    private ArrayList<String> listaAktorow;
    private String rodzajSerialuFilmu;
    private int [] ogladalnoscWCzasie;


    public SerialFilm(){}

    public SerialFilm(String zdjecie, String nazwa, String opis, String dataProdukcji, double czasTrwania, int dystrybutor, String krajeProdukcji, double ocenaUzytkownika, double cena, int kiedyNajlepiejOgladac, ArrayList<String> listaAktorow) {
        super(zdjecie, nazwa, opis, dataProdukcji, czasTrwania, dystrybutor, krajeProdukcji, ocenaUzytkownika, cena);
        this.kiedyNajlepiejOgladac = kiedyNajlepiejOgladac;
        this.listaAktorow = listaAktorow;

        //HasMapa nie dzialal wiec trzeba uwazac
    }

    public SerialFilm(String rodzajSerialuFilmu, String idZnak,  int  sellers) {
        super(rodzajSerialuFilmu, idZnak, sellers);
        this.rodzajSerialuFilmu = rodzajSerialuFilmu;
        this.kiedyNajlepiejOgladac = NajlepiejOgladac();
        this.listaAktorow = tworzAktorow();
        for(String aktor : this.listaAktorow)
        {
            System.out.println(aktor);
        }
        wykresOgladalnosciWCzasie();
        //bez sensu ale jest
    }

    private void wykresOgladalnosciWCzasie() {
        ogladalnoscWCzasie = new int[24];
        int ileProcentObejrzano = 80;
        for( int i=12; i<24; i++)
        {
            ogladalnoscWCzasie[i] = RANDOM.nextInt((int)ileProcentObejrzano/2);
            ileProcentObejrzano = ileProcentObejrzano -ogladalnoscWCzasie[i];
        }
        for(int i=0; i<12; i++)
        {
            ogladalnoscWCzasie[i] = RANDOM.nextInt(ileProcentObejrzano);
            ileProcentObejrzano = ileProcentObejrzano - ogladalnoscWCzasie[i];

        }
    }


    public String zapisDoPliku()
    {
        String stringDoZapisu="";
        //PRoblem trzeba cos zmienic
        stringDoZapisu = super.zapisDoPliku();

        return  stringDoZapisu;
    }

    public int getKiedyNajlepiejOgladac() {
        return kiedyNajlepiejOgladac;
    }

    public void setKiedyNajlepiejOgladac(int kiedyNajlepiejOgladac) {
        this.kiedyNajlepiejOgladac = kiedyNajlepiejOgladac;
    }

    private ArrayList<String> tworzAktorow() {
        String [] listaAktorowPrzykladowa = new String[]{"Jack Nicholson", "Al Pacino", "Leonardo DiCaprio","Will Smith","Marlon Brando",
        "Brad Pitt", "Daniel Day", "Denzel Washington", "Tom Hanks", "Johnny Depp",
        "Anthony Hopkins", "Kevin Spacey", "Morgan Freeman", "Christian Bale", "Sean Penn",
        "Matt Damon", "Jake Gyllenhaal", "Tim Robbins", "Heath Ledger", "Edward Norton"};
        ArrayList<String> listaAktorow = new ArrayList<>();

        int iluAktorowGralo = RANDOM.nextInt(4)+1;
        for(int i=0; i<(iluAktorowGralo*5); i+=5)
        {
            int indexAktora = RANDOM.nextInt(5)+i;
            listaAktorow.add(listaAktorowPrzykladowa[indexAktora]);
        }
        return listaAktorow;
    }

    private int NajlepiejOgladac() {
        int najlepiej_ogladac;
        najlepiej_ogladac = RANDOM.nextInt(7);
        return najlepiej_ogladac;
    }

    public int[] getOgladalnoscWCzasie() {
        return ogladalnoscWCzasie;
    }

    public void setOgladalnoscWCzasie(int[] ogladalnoscWCzasie) {
        this.ogladalnoscWCzasie = ogladalnoscWCzasie;
    }

    public ArrayList<String> getListaAktorow() {
        return listaAktorow;
    }

    public void setListaAktorow(ArrayList<String> listaAktorow) {
        this.listaAktorow = listaAktorow;
    }



}
