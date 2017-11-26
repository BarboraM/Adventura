/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;
import main.Main;

/**
 *
 * @author Barbora
 */
public class VeciVProstoru extends FlowPane implements Observer{
    public IHra hra;
    private Map<String, Vec> veciZMistnosti;
    private ImageView[] images;
    private Main main;
    
    
    
    public VeciVProstoru(IHra hra, Main main){
        this.main = main;
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        hra.getHerniPlan().getAktualniProstor().registerObserver(this);
        veciZMistnosti = new HashMap<String, Vec>();
        this.images = new ImageView[5];
        for (int i=0; i<images.length; i++) {
            images[i] = new ImageView();
        }
        this.getChildren().addAll(images);
        init();
    }
    
    public void init(){
        for (int i=0; i<images.length; i++) {
            images[i].setImage(new Image(Main.class.getResourceAsStream("/zdroje/prazdny.jpg"),80, 80,false,true));   
        }
        update();
    }

    @Override
    public void update(){
        hra.getHerniPlan().getAktualniProstor().removeObserver(this);
        for (int i=0; i<images.length; i++) {
            images[i].setImage(new Image(Main.class.getResourceAsStream("/zdroje/prazdny.jpg"),80, 80,false,true));   
            images[i].setAccessibleText("prazdny");
        }
        veciZMistnosti = hra.getHerniPlan().getAktualniProstor().getVeciZProstoru();
        int index = 0;
        for (Map.Entry<String, Vec> entry : veciZMistnosti.entrySet()) {          
            String path = "/zdroje/" + entry.getKey() + ".jpg";       
            images[index].setImage(new Image(Main.class.getResourceAsStream(path),80, 80,false,true));
            images[index++].setOnMouseClicked(new EventHandler<MouseEvent>() { 
                @Override
               public void handle(MouseEvent click) {
                   if (click.getClickCount() == 2) {
                       main.getCentralText().appendText("\n" + hra.zpracujPrikaz("seber " + entry.getKey())+ "\n");
                   } else {
                       main.getCentralText().appendText("\n" + hra.zpracujPrikaz("prozkoumej " + entry.getKey()) + "\n");
                   }
               }
            });
        }
        hra.getHerniPlan().getAktualniProstor().registerObserver(this);
    }   
}
