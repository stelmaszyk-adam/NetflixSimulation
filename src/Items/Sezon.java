package Items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sezon implements Serializable {

    private static final Random RANDOM = new Random();
    private int dataPremiery;
    private int dlugoscOgolna;
    private int liczbaOdcinkow;
    private List<Odcinek> odcinki;


    public Sezon()
    {
        dlugoscOgolna = 0;
        liczbaOdcinkow = losojLiczbeOdcinkow();
        odcinki = new ArrayList<>();
        tworzenieOdcinkow();
        this.dataPremiery = RANDOM.nextInt(35)+1990;
    }

    private void tworzenieOdcinkow() {

        int i = RANDOM.nextInt(23)+1;
        int ileDodac = 330/liczbaOdcinkow;
        for(;i<366; i=i+ileDodac)
        {
            Odcinek pomocOdcinek = new Odcinek(i);
            dlugoscOgolna=dlugoscOgolna+pomocOdcinek.getDlugoscOdcinka();
            odcinki.add(pomocOdcinek);
        }
    }

    public Sezon(int dataPremiery) {
        this();
        this.dataPremiery = dataPremiery+1;
    }

    private int losojLiczbeOdcinkow() {

        return RANDOM.nextInt(15)+4;
    }

    public int getDataPremiery() {
        return dataPremiery;
    }

    public void setDataPremiery(int dataPremiery) {
        this.dataPremiery = dataPremiery;
    }
}
