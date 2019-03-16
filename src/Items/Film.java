package Items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Film extends SerialFilm implements Serializable {

    private static final Random RANDOM = new Random();

    ArrayList<String> linkiDoZwiastunow;
    int czasPoZakupie;

    public Film(String rodzajSerialuFilmu, String idZnak, int  sellers) {
        super(rodzajSerialuFilmu, idZnak, sellers);
        linkiDoZwiastunow = new ArrayList<>();
        this.linkiDoZwiastunow = generowanieLinkowDoZwiastunow();
        for(String link : linkiDoZwiastunow){
            System.out.println(link);
        }
        this.czasPoZakupie = ileMoznaOgladacPoZakupie();
    }


    public Film(String rodzajSerialuFilmu,ArrayList<String> linkiDoZwiastunow, double czasPoZakupie,String idZnak,  int sellers) {
        //To trzeba poprawic
        super(rodzajSerialuFilmu, idZnak,sellers);
        this.linkiDoZwiastunow = new ArrayList<>();
        this.linkiDoZwiastunow = generowanieLinkowDoZwiastunow();
        this.czasPoZakupie = ileMoznaOgladacPoZakupie();
    }


    public ArrayList<String> getLinkiDoZwiastunow() {
        return linkiDoZwiastunow;
    }

    public void setLinkiDoZwiastunow(ArrayList<String> linkiDoZwiastunow) {
        this.linkiDoZwiastunow = linkiDoZwiastunow;
    }

    public int getCzasPoZakupie() {
        return czasPoZakupie;
    }

    public void setCzasPoZakupie(int czasPoZakupie) {
        this.czasPoZakupie = czasPoZakupie;
    }

    private ArrayList<String> generowanieLinkowDoZwiastunow() {
        ArrayList<String> linkiDoZwiastunow = new ArrayList<>();
        String link1 = "https://www.filmweb.pl/"+this.getNazwa();
        String link2 = "http://cda.pl/"+this.getNazwa();
        String link3 = "http://netflix.com/"+this.getNazwa();
        String link4 = "https://www.kino.pl/"+this.getNazwa();
        linkiDoZwiastunow.add(link1);
        linkiDoZwiastunow.add(link2);
        linkiDoZwiastunow.add(link3);
        linkiDoZwiastunow.add(link4);
        return linkiDoZwiastunow;

    }

    private int ileMoznaOgladacPoZakupie() {
        int ilePoZakupie =  RANDOM.nextInt(30)+30;
        return ilePoZakupie;
    }


}
