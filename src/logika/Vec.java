/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Vec představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private boolean prozkoumatelnost;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, boolean prenositelnost, boolean prozkoumatelnost)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.prozkoumatelnost = prozkoumatelnost;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
     /**
     * Vrátí název věci
     * 
     *
     * @return Vrací název věci
     */
    public String getNazev(){
        return nazev;
    }
    
     /**
     * Vrátí hodnotu boolean, zda je věc přenositelná
     * 
     *
     * @return Vrací, zda je věc přenositelná
     */
    public boolean jePrenositelna(){
        return prenositelnost;
    }
    
     /**
     * Vrátí hodnotu boolean, zda je věc prozkoumatelná
     * 
     *
     * @return Vrací, zda je věc prozkoumatelná
     */
    public boolean jeProzkoumatelna(){
        return prozkoumatelnost;
    }

    public void setProzkoumatelna(boolean prozkoumatelnost){
        this.prozkoumatelnost = prozkoumatelnost;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
