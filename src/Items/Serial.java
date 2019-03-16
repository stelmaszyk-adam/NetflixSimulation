package Items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Serial extends SerialFilm implements Serializable {

    private static final Random RANDOM = new Random();

    private int liczbaSezonow;
    private ArrayList<Sezon> sezon;

    public Serial(){
        super();
    }

    public Serial(String rodzajSerialuFilmu, String idZnak,  int sellers)
    {
        super(rodzajSerialuFilmu, idZnak,sellers);
        sezon = new ArrayList<>();
        this.liczbaSezonow = RANDOM.nextInt(1)+4;
        System.out.println("Liczba sezonow: "+this.liczbaSezonow);
        tworzenieListySezonow();
    }

    public Serial(String zdjecie, String nazwa, String opis, String dataProdukcji, double czasTrwania, int dystrybutor, String krajeProdukcji, double ocenaUzytkownika, double cena, int kiedyNajlepiejOgladac, ArrayList<String> listaAktorow) {
        super(zdjecie, nazwa, opis, dataProdukcji, czasTrwania, dystrybutor, krajeProdukcji, ocenaUzytkownika, cena, kiedyNajlepiejOgladac, listaAktorow);

        sezon = new ArrayList<>();
        liczbaSezonow = RANDOM.nextInt(1)+4;
    }

    private void tworzenieListySezonow()
    {
        for(int i=0; i<liczbaSezonow; i++)
        {
            Sezon pomocSezon = null;
            if(i==0)
            {
                pomocSezon = new Sezon();
            }
            else
            {
                pomocSezon = new Sezon(sezon.get(i-1).getDataPremiery());
            }

            sezon.add(pomocSezon);
        }
    }

    public int getLiczbaSezonow() {
        return liczbaSezonow;
    }

    public void setLiczbaSezonow(int liczbaSezonow) {
        this.liczbaSezonow = liczbaSezonow;
    }

    public List<Sezon> getSezon() {
        return sezon;
    }

    public void setSezon(ArrayList<Sezon> sezon) {
        this.sezon = sezon;
    }
}
