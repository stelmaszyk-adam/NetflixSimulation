package Items;

import java.io.Serializable;

public class LiveStreaming extends Produkt implements Serializable {

    String dataWyswietlenia;

    public LiveStreaming(String idZnak,  int  sellers)
    {
        super(idZnak, sellers);
        dataWyswietlenia = "20.10.2010";//zrobic date wyswietlenia
    }

    public String getDataWyswietlenia() {
        return dataWyswietlenia;
    }

    public void setDataWyswietlenia(String dataWyswietlenia) {
        this.dataWyswietlenia = dataWyswietlenia;
    }
}
