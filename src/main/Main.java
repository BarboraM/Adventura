/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.AktualniInventar;
import GUI.Mapa;
import GUI.MenuLista;
import GUI.OkolniProstory;
import GUI.VeciVProstoru;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 *
 * @author Barbora
 */
public class Main extends Application {
    private IHra hra;
    private Stage stage;
    private TextArea centralText;
    private TextField zadejPrikazTextArea;  
    private Mapa mapa;
    private OkolniProstory okolniProstory;
    private AktualniInventar aktualniInventar;
    private VeciVProstoru veciVProstoru;
    private MenuLista menuLista;

    

    public void setHra(IHra hra) {
        this.hra = hra;
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        hra = new Hra();
        mapa = new Mapa(hra);
        okolniProstory = new OkolniProstory(hra, this);
        aktualniInventar = new AktualniInventar(hra);
        veciVProstoru = new VeciVProstoru(hra, this);
        menuLista = new MenuLista(hra, this);
        
        //vnější border pane
        BorderPane borderPane = new BorderPane(); 
        borderPane.setPrefWidth(800.0);
        
        borderPane.setTop(menuLista);                      

        //vnitřní border pane
        BorderPane mainArea = new BorderPane();
        
        mainArea.setCenter(mapa);                          
        
        mainArea.setRight(okolniProstory); 
        okolniProstory.setPrefWidth(150.0);                
        
        mainArea.setLeft(veciVProstoru);   
        veciVProstoru.setMaxWidth(80.0);
        veciVProstoru.setMaxHeight(400.0);
        
        mainArea.setTop(aktualniInventar);
        aktualniInventar.setAlignment(Pos.CENTER);
        aktualniInventar.setMinWidth(800.0);
        mainArea.getTop().setStyle("-fx-background-image: url(\"/zdroje/cloverfield.jpg/\");-fx-background-size: 830, 70;-fx-background-repeat: no-repeat;");
                   
        //text s prubehem hry
        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        centralText.setPrefHeight(130.0);
        centralText.setPrefWidth(800.0);
        
        mainArea.setBottom(centralText);                        
        mainArea.setAlignment(centralText, Pos.CENTER);  
             
        borderPane.setCenter(mainArea);                
        
        //label s textem zadej prikaz
        Label zadejPrikazLabel = new Label("Zadej prikaz:");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
      
        //text area do ktere piseme prikazy
        zadejPrikazTextArea = new TextField("...");
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {
           
            @Override
            public void handle(ActionEvent event) {
                String vstupniPrikaz = zadejPrikazTextArea.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                
                centralText.appendText("\n>>> " + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");
                zadejPrikazTextArea.setText("");
                
                if(hra.konecHry()){
                    setKonec();                            
                }
            }
        });
        
        
               
        //dolni lista s elementy
        FlowPane dolniLista = new FlowPane();
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);
         
        borderPane.setBottom(dolniLista);                   
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.setPrefHeight(30.0);
        dolniLista.setMaxHeight(800.0);
        
        
        Scene scene = new Scene(borderPane, 800, 650);
        primaryStage.setTitle("Adventura");
        primaryStage.setResizable(false);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }

    /**
     * vrátí aktuální mapu
     *  
     *  @return     odkaz na mapu
     */
    public Mapa getMapa() {
        return mapa;
    }
    
    /**
     * nastaví okno pro zpracování příkazů jako neaktivní
     *  
     */
    public void setKonec(){
        zadejPrikazTextArea.setEditable(false);
        //okolniProstory.setInactive
        //veciVProstoru.setInactive
    }
    
    /**
     * vrátí pole s hlavním textem hry
     *  
     *  @return     odkaz pole s hlavním textem hry
     */
    public TextArea getCentralText() {
        return centralText;
    }

    /**
     * vrátí odkaz na stage
     *  
     *  @return     odkaz na stage
     */
    public Stage getStage() {
        return stage;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length == 0){
            launch(args);  //zavola metodu Start
        }else{
            if(args[0].equals("-txt")){
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }    
        }  
    } 
}
