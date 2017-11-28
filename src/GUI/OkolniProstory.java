/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import logika.IHra;
import logika.Prostor;
import main.Main;
import utils.Observer;

/**
 *
 * @author Barbora
 */
public class OkolniProstory extends ListView implements Observer {
    public IHra hra;
    private ObservableList<String> prostory;
    private Main main;
    
    /**
     *  Registruje observer k hernímu plánu
     */  
    public OkolniProstory(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    
    /**
     *  Založí list s aktuálními východy a přidá ho do zobrazení
     */  
    public void init(){
       prostory = FXCollections.observableArrayList();
       this.setItems(prostory);
       this.setOnMouseClicked(e -> {
                    String vychod;
                    vychod =  this.getSelectionModel().getSelectedItems().toString();
                    vychod = vychod.substring(1, vychod.length() - 1);
                    if(!vychod.equals(""))
                    main.getCentralText().appendText("\n" + hra.zpracujPrikaz("jdi " + vychod) + "\n");
                    if(hra.konecHry()){
                        main.setKonec();
                    }
            }); 
       this.setMaxHeight(400.0);
       this.setMinWidth(100.0);
       this.setMaxWidth(110.0);
       update();
    }
    
    /**
     *  Aktualizuje list s východy
     */  
    @Override
    public void update(){ 
       prostory.clear();
       for (Prostor prostor : hra.getHerniPlan().getAktualniProstor().getVychody()){
           prostory.add(prostor.getNazev());
       }
    }  
}
