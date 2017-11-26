/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.HashMap;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 *
 * @author Barbora
 */
public class AktualniInventar extends FlowPane implements Observer{
    public IHra hra;
    private Map<String, Vec> veciZInventare;
    private ImageView[] images;
    
    
    public AktualniInventar(IHra hra){
        this.hra = hra; 
        hra.getInventar().registerObserver(this); 
        veciZInventare = new HashMap<String, Vec>();
        init();
    }
    
    public void init(){
        this.images = new ImageView[5];
        for (int i=0; i<images.length; i++) {
            images[i] = new ImageView();
            images[i].setImage(new Image(Main.class.getResourceAsStream("/zdroje/prazdny.jpg"),65,65,false,true));
        } 
        this.getChildren().addAll(images);
    }

    @Override
    public void update(){   
        //veciZInventare.clear();
        for (int i=0; i<images.length; i++) {
            images[i].setImage(new Image(Main.class.getResourceAsStream("/zdroje/prazdny.jpg"),65,65,false,true));
        } 
        veciZInventare = hra.getInventar().getVeciVInventari();
        int index = 0;
        for (Map.Entry<String, Vec> entry : veciZInventare.entrySet()) {
            String path = "/zdroje/" + entry.getKey() + ".jpg";
            images[index].setImage(new Image(Main.class.getResourceAsStream(path),65,65,false,true));
            images[index++].setOnMouseClicked(new EventHandler<MouseEvent>() { 
                @Override
               public void handle(MouseEvent click) {
                   if (click.getClickCount() == 2) {
                        hra.zpracujPrikaz("zahod " + entry.getKey());
                   }
               }
            });
        }
    }   
}
