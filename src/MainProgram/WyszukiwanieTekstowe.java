package MainProgram;


import Items.Produkt;
import Items.SerialFilm;

public class WyszukiwanieTekstowe {


    public WyszukiwanieTekstowe(){}

    public static void Wyszukaj(String ciagLiter){

        MainController.listView.clear();
        System.out.println(MainController.listView.size());
        for(Produkt produkt : CreateItems.listaProduktow)
        {
            if((produkt.getNazwa()).contains(ciagLiter)) {
                MainController.listView.add(produkt);
                continue;
            }
            else if(produkt.getId().contains("f") || produkt.getId().contains("s"))
            {
                SerialFilm serialFilm = new SerialFilm("Action","f", -1);
                serialFilm = (SerialFilm) produkt;
                for(String pro :  serialFilm.getListaAktorow()) {
                    if(pro.contains(ciagLiter)) {
                        MainController.listView.add(produkt);
                        continue;
                    }
                }
            }
        }
    }
}
