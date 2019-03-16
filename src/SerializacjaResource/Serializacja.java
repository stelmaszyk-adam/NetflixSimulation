package SerializacjaResource;

import Controler.ControlApp;
import Items.*;
import MainProgram.CreateItems;
import MainProgram.MainController;

import java.io.*;
import java.util.ArrayList;

public class Serializacja implements Serializable {

    public static void zapisDoPliku(){



        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("src/SerializacjaResource/serialized/produkty.ser")))){
            outputStream.writeObject(CreateItems.listaProduktow);
            outputStream.writeObject(ControlApp.listaWszystkichSprzedawcow);
            outputStream.writeObject(ControlApp.listaWszystkichUzytkownikow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void odczytZPliku(){
        try (ObjectInputStream readStream = new ObjectInputStream(new BufferedInputStream((new FileInputStream("src/SerializacjaResource/serialized/produkty.ser"))))) {

            for(Uzytkownik uzytkownik : ControlApp.listaWszystkichUzytkownikow){
                uzytkownik.stop();
            }
            for(Sellers sellers : ControlApp.listaWszystkichSprzedawcow){
                sellers.stop();
            }

            CreateItems.listaProduktow.clear();
            ControlApp.listaDicount.clear();
            MainController.listViewD.clear();
            CreateItems.listaProduktow = (ArrayList<Produkt>)readStream.readObject();
            MainController.listView.clear();
            for(Produkt produkt : CreateItems.listaProduktow){
                MainController.listView.add(produkt);
                if(produkt.isPromocjaBool()){
                    ControlApp.listaDicount.add(produkt);
                    MainController.dodawanieDoListViewDicount(produkt);
                }

            }
            ControlApp.listaWszystkichSprzedawcow = (ArrayList<Sellers>)readStream.readObject();
            MainController.listViewS.clear();
            for(Sellers sellers : ControlApp.listaWszystkichSprzedawcow){
                MainController.listViewS.add(sellers);
            }

            ControlApp.listaWszystkichUzytkownikow = (ArrayList<Uzytkownik>)readStream.readObject();
            MainController.listViewC.clear();
            for(Uzytkownik uzytkownik : ControlApp.listaWszystkichUzytkownikow){
                MainController.listViewC.add(uzytkownik);
            }

            for(Uzytkownik uzytkownik : ControlApp.listaWszystkichUzytkownikow){
                uzytkownik.createThread();
            }
            for(Sellers sellers : ControlApp.listaWszystkichSprzedawcow){
                sellers.createThread();
            }

        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
