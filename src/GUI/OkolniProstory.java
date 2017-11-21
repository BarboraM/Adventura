/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import logika.IHra;
import logika.Prostor;
import utils.Observer;

/**
 *
 * @author Barbora
 */
public class OkolniProstory extends FlowPane implements Observer {
    public IHra hra;
    private ObservableList<String> prostory;
    private ListView<String> listProstoru;
    
    public OkolniProstory(IHra hra){
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        prostory = FXCollections.observableArrayList();
        init();
    }
    
    public void init(){
       listProstoru = new ListView<String>();
       listProstoru.setItems(prostory);
       listProstoru.setMaxHeight(400.0);
       listProstoru.setMinWidth(100.0);
       listProstoru.setMaxWidth(110.0);
       this.getChildren().add(listProstoru);
       update();
    }
    
    @Override
    public void update(){ 
       prostory.clear();
       for (Prostor prostor : hra.getHerniPlan().getAktualniProstor().getVychody()){
           prostory.add(prostor.getNazev());
       }      
       //prostory.addAll(hra.getHerniPlan().getAktualniProstor().getVychody().stream().map(Object::toString).collect(Collectors.toList()));
    }  
}
