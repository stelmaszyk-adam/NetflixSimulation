package Items;

import java.io.Serializable;
import java.util.Random;

public class Odcinek implements Serializable {

    private static final Random RANDOM = new Random();

    private String dataProdukcji;
    private int dlugoscOdcinka;

    Odcinek(int dataProdukcji) {
        dlugoscOdcinka = losujDlugoscOdcinka();
        final int [] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for(int i=0; i<12; i++)
        {
            if(dataProdukcji<daysInMonths[i])
            {
                this.dataProdukcji = Integer.toString(dataProdukcji) + Integer.toString(i+1);
                break;
            }
            dataProdukcji = dataProdukcji-daysInMonths[i];
        }

    }

    private int losujDlugoscOdcinka() {
        return RANDOM.nextInt(15) + 20;
    }

    public String getDataProdukcji() {
        return dataProdukcji;
    }

    public void setDataProdukcji(String dataProdukcji) {
        this.dataProdukcji = dataProdukcji;
    }

    public int getDlugoscOdcinka() {
        return dlugoscOdcinka;
    }

    public void setDlugoscOdcinka(int dlugoscOdcinka) {
        this.dlugoscOdcinka = dlugoscOdcinka;
    }

}
