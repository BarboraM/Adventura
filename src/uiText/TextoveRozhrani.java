package uiText;

import java.util.Scanner;
import logika.IHra;
import java.io.*;
import logika.HerniPlan;
/**
 *  Class TextoveRozhrani
 * 
 *  Toto je uživatelského rozhraní aplikace Adventura
 *  Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 *  Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *  
 *  
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2015/2016 LS
 */

public class TextoveRozhrani {
    private IHra hra;
    private HerniPlan plan;

    /**
     *  Vytváří hru.
     */
    public TextoveRozhrani(IHra hra, HerniPlan plan) {
        this.hra = hra;
        this.plan = plan;
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(hra.vratUvitani()+ "\n");
        

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.
        
        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek)); 
        }

        System.out.println(hra.vratEpilog());
        
    }

    public void hrajZeSouboru(String nazevSouboru) {
        
        try (BufferedReader ctecka = new BufferedReader( new FileReader(nazevSouboru)))
        {
            System.out.println(hra.vratUvitani());
            String radek = ctecka.readLine();
            while (!hra.konecHry()&&radek != null) {
                System.out.println("*"+radek+"*");
                System.out.println(hra.zpracujPrikaz(radek));
                radek = ctecka.readLine();
            }
            
            System.out.println(hra.vratEpilog());
        }
        catch (FileNotFoundException e){
            System.out.println("soubor nenalezen "+e);
        }
         catch (IOException e){
            System.out.println("chyba vstupu "+e);
        }
        
    }

    /**
     *  Metoda přečte příkaz z příkazového řádku
     *
     *@return    Vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

}
