package Items;

import MainProgram.CreateItems;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Produkt implements Serializable {

    private static final Random RANDOM = new Random();

    private String id;
    private String zdjecie;
    private String nazwa;
    private String opis;
    private  String dataProdukcji;
    private  double czasTrwania;
    private int dystrybutor;
    private String krajeProdukcji;
    private double ocenaUzytkownika;
    private double cena;
    private double liczba_Wyswietlen;
    private long ileTrwaPromocja;
    private boolean promocjaBool = false;



    private double cenaBackUp;

    public Produkt(){}

    public Produkt(String jaki_rodzaj, String idZnak,  int sellers)
    {
        this.id = dajID(idZnak);
        String[] arr = new String[2];
        arr = Produkt.utworz_nazwe_zdjecie(jaki_rodzaj);
        nazwa = arr[0];
        zdjecie = arr[1];
        opis = "blablabla";
        this.dataProdukcji = losujDateProdukcji();
        this.czasTrwania = losujCzasTrwania();
        this.ocenaUzytkownika = losujOceneUzytkownika();
        this.cena = losojLosowaCene();
        this.krajeProdukcji = losoKrajeProdukcji();
        this.dystrybutor = sellers;
        liczba_Wyswietlen = 0;
    }

    public Produkt(String idZnak, int sellers)
    {
        this.id = dajID(idZnak);
        String[] arr = new String[2];
        arr = Produkt.utworz_nazwe_zdjecie();
        this.nazwa = arr[0];
        this.zdjecie = arr[1];
        this.opis = "blablabla"; // opis = 10 opisow tylko dane w nich beda sie zmienialy o tresci ile ludzi widzialo itp
        this.dataProdukcji = losujDateProdukcji();
        this.czasTrwania = losujCzasTrwania();
        this.ocenaUzytkownika = losujOceneUzytkownika();
        this.cena = losojLosowaCene();
        liczba_Wyswietlen = 0;
    }




    public Produkt(String zdjecie, String nazwa, String opis, String dataProdukcji, double czasTrwania, int dystrybutor, String krajeProdukcji, double ocenaUzytkownika, double cena) {
        //to jeszcze nie dziala zbyt dobrze
        this.zdjecie = zdjecie;
        this.nazwa = nazwa;
        this.opis = opis;
        this.dataProdukcji = dataProdukcji;
        this.czasTrwania = czasTrwania;
        this.dystrybutor = dystrybutor;
        this.krajeProdukcji = krajeProdukcji;
        this.ocenaUzytkownika = ocenaUzytkownika;
        this.cena = cena;
    }

    public String zapisDoPliku()
    {
        //SQL baza danych
        String stringDoZapisu="";
        /*ArrayList<String> krajeProdukcjiTable;
        String krajeProduk = "";
        for(String kPTable : krajeProdukcjiTable )
        {
            krajeProduk = kPTable+"^";
        }

        stringDoZapisu=zdjecie+ "%" + nazwa + "%" + opis + "%" + dataProdukcji + "%" + czasTrwania + "%" + dystrybutor + "%"
                +*/

        return stringDoZapisu;
    }




    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getDataProdukcji() {
        return dataProdukcji;
    }

    public void setDataProdukcji(String dataProdukcji) {
        this.dataProdukcji = dataProdukcji;
    }

    public double getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(double czasTrwania) {
        this.czasTrwania = czasTrwania;
    }

    public int getDystrybutor() {
        return dystrybutor;
    }

    public void setDystrybutor(int dystrybutor) {
        this.dystrybutor = dystrybutor;
    }

    public String getKrajeProdukcji() {
        return krajeProdukcji;
    }

    public void setKrajeProdukcji(String krajeProdukcji) {
        this.krajeProdukcji = krajeProdukcji;
    }

    public double getOcenaUzytkownika() {
        return ocenaUzytkownika;
    }

    public void setOcenaUzytkownika(double ocenaUzytkownika) {
        this.ocenaUzytkownika = ocenaUzytkownika;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getZdjecie() {
        return zdjecie;
    }

    public void setZdjecie(String zdjecie) {
        this.zdjecie = zdjecie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLiczba_Wyswietlen() {
        return liczba_Wyswietlen;
    }

    public void setLiczba_Wyswietlen(double liczba_Wyswietlen) {
        this.liczba_Wyswietlen = liczba_Wyswietlen;
    }

    public double getCenaBackUp() {
        return cenaBackUp;
    }

    public void setCenaBackUp(double cenaBackUp) {
        this.cenaBackUp = cenaBackUp;
    }

    public long getIleTrwaPromocja() {
        return ileTrwaPromocja;
    }

    public void setIleTrwaPromocja(long ileTrwaPromocja) {
        this.ileTrwaPromocja = ileTrwaPromocja;
    }

    public boolean isPromocjaBool() {
        return promocjaBool;
    }

    public void setPromocjaBool(boolean promocjaBool) {
        this.promocjaBool = promocjaBool;
    }

    private static String[] utworz_nazwe_zdjecie(String jaki_rodzaj) {

        String [] listaNazw = new String[]{"Action","Drama","Comedy","Cartoon","Documentary","Criminal"};
        String [][] listaNazwaProduktow = new String[][]{{"Old", "About a", "Animal","American","Elf",
                "School", "Trainwreck","Airplane","Python","House"},
                {"96", "Taxi","Schindler","Angry","Forrest", "Driver", "List","Men","Gump", "Spotlight"},
                {"Dark","Hard", "Bill", "John", "Face","Knight", "Matrix", "Avengers", "Wick", "Terminator"},
                {"Toy", "Frozen", "Lion", "Shrek", "WALL","Nemo","Moana","Aladdin","Bambi","Coraline"},
                {"Cove", "Bowling", "Man","Fog", "Hoop","Killing", "Job","Gardens", "Citizenfour","Icarus"},
                {"Good", "Pulp","Zodiac","Heat","Fargo","Father", "Casino", "Snatch", "Driver", "Baby"}};

        int index=0;
        String [] arr = new String[2];
        for (String nazw : listaNazw)
        {
            if(jaki_rodzaj.equals(nazw))
            {
                int ile_czlonowa_nazwa = RANDOM.nextInt(2);
                int y1;
                if(ile_czlonowa_nazwa == 0) {
                    y1 = RANDOM.nextInt(5);
                    arr[0] = listaNazwaProduktow[index][y1];


                }else {
                    y1 = RANDOM.nextInt(5);
                    int y2 = RANDOM.nextInt(5)+5;
                    arr[0] = listaNazwaProduktow[index][y1] +" "+listaNazwaProduktow[index][y2];
                }
                y1 = RANDOM.nextInt(10)+1;
                arr[1] = jaki_rodzaj + Integer.toString(y1);
                break;
            }
            index++;
        }
        return arr;
    }

    private static String[] utworz_nazwe_zdjecie()
    {
        String [] listaNazwaProduktow = new String[]{"Benefit","Businesses","Creators","Apple","Hits","Cosmetics","Industry","Product","Florida","Sport"};
        String [] arr = new String[2];
        int ile_czlonowa_nazwa = RANDOM.nextInt(1);
        int y1;
        if(ile_czlonowa_nazwa == 0) {
            y1 = RANDOM.nextInt(5);
            arr[0] = listaNazwaProduktow[y1];


        }else {
            y1 = RANDOM.nextInt(5);
            int y2 = RANDOM.nextInt(5)+5;
            arr[0] = listaNazwaProduktow[y1] +" "+listaNazwaProduktow[y2];
        }
        y1 = RANDOM.nextInt(10)+1;
        arr[1] = "LiveStreaming" + Integer.toString(y1);
        return arr;
    }

    private double losojLosowaCene() {

        return (double) (RANDOM.nextInt(20)+30);
    }

    private double losujOceneUzytkownika()
    {
        return (double) (RANDOM.nextInt(6)+1);
    }

    private double losujCzasTrwania() {
        return (double) (RANDOM.nextInt(130)+50);
    }

    private String losujDateProdukcji() {
        String data_produkcji="";
        int yearInt = RANDOM.nextInt(50);
        yearInt = 2018 - yearInt;
        data_produkcji = Integer.toString(yearInt);
        return data_produkcji;
    }

    private String losoKrajeProdukcji() {

        String [] krajeProdukcjiPrzykladowe = new String[]{"USA", "Poland", "Germany", "Spain", "India", "China", "France", "Egypt",
        "Iran","Japan","Korea","Turkey","Pakistan","Bangladesh","Indonesia"};
        String krajeProdukcji = "";

        int ile_czlonowa = RANDOM.nextInt(3);
        int jaki_wyraz;
        if(ile_czlonowa == 0) {
            jaki_wyraz = RANDOM.nextInt(krajeProdukcjiPrzykladowe.length);
            krajeProdukcji = krajeProdukcjiPrzykladowe[jaki_wyraz];
        }else if(ile_czlonowa == 1){
            jaki_wyraz = RANDOM.nextInt(krajeProdukcjiPrzykladowe.length-1);
            krajeProdukcji = krajeProdukcjiPrzykladowe[jaki_wyraz] + " " + krajeProdukcjiPrzykladowe[jaki_wyraz+1];
        }else {
            jaki_wyraz = RANDOM.nextInt(krajeProdukcjiPrzykladowe.length-2);
            krajeProdukcji = krajeProdukcjiPrzykladowe[jaki_wyraz] + " " + krajeProdukcjiPrzykladowe[jaki_wyraz+1] + " " + krajeProdukcjiPrzykladowe[jaki_wyraz+2];
        }
        return krajeProdukcji;
    }

    private String dajID(String idZnak)
    {
        String id = "";
        switch (idZnak){
            case "f":
                id = idZnak+Integer.toString(CreateItems.idF);
                CreateItems.CountItemsF();
                return id;
            case "s":
                id = idZnak+Integer.toString(CreateItems.idS);
                CreateItems.CountItemsS();
                return id;
            case "l":
                id = idZnak+Integer.toString(CreateItems.idL);
                CreateItems.CountItemsL();
                return id;
        }
        return id;
    }

    public void zwiekszLiczbeWyswietlen(){
        liczba_Wyswietlen++;
    }



}
