/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.IHra;
import main.Main;

/**
 *
 * @author Barbora
 */
public class MenuLista extends MenuBar{
    private IHra hra;
    private Main main;
    
    public MenuLista(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        init();
    }
    
    private void init(){
        Menu novySoubor = new Menu("Adventura");
        Menu napoveda = new Menu("Help");
        
        MenuItem novaHra = new MenuItem("Nova hra", new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/znovu.png"))));       
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        
        MenuItem konecHry = new MenuItem("Konec hry",new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/krizek.png"))));
        novaHra.setAccelerator(KeyCombination.keyCombination("Esc"));
        
        novySoubor.getItems().addAll(novaHra, konecHry);
        
        MenuItem oProgramu = new MenuItem("O programu",new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/info.png"))));
        MenuItem napovedaItem = new MenuItem("Napoveda",new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/napoveda.png"))));
        
        napoveda.getItems().addAll(oProgramu, napovedaItem);
        
        this.getMenus().addAll(novySoubor, napoveda);
        
        konecHry.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                System.exit(0);
            }
        });
        
        novaHra.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                main.getStage().close();
                main = new Main();
                //hra = new Hra();
                //main.getMapa().newGame(hra);
               // main.setHra(hra);
                //main.getCentralText().setText(hra.vratUvitani());  
                main.start(new Stage());
            }
        }); 
        
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);
                
                oProgramuAlert.setTitle("O programu");
                oProgramuAlert.setHeaderText("Cloverfield");
                oProgramuAlert.setContentText("Jednoduchá adventura z prostředí ochranného bunkru. \n" +
                                               "Úkolem hráče je dostat se pomocí zadávání jednotlivých příkazů \n" +
                                               "z prostoru bunru na vojenské letiště. \n" +
                                               "\n" +
                                               "Autor : Barbora Mlejnková\n" +
                                               "Version : 2.0");
                oProgramuAlert.initOwner(main.getStage());
                
                oProgramuAlert.showAndWait(); 
            }
        });
        
        napovedaItem.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                Stage stage = new Stage();
                stage.setTitle("Napoveda");
                
                WebView webView = new WebView();
                webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
                
                stage.setScene(new Scene(webView, 500, 500));
                stage.show();
            }
        });     
    }
}
