/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy PrikazSeber představují ...
 *
 *@author     Barbora Mlejnková
 *@version    ZS 2016/2017
 */
public class PrikazZahod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "zahod";
    private HerniPlan plan;
    private Inventar inventar; 
   
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazZahod(HerniPlan plan, Inventar inventar)
    {
        this.plan = plan;
        this.inventar = inventar;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Metoda pro provedení příkazu ve hře.
     *  Počet parametrů je závislý na konkrétním příkazu,
     *  např. příkazy konec a napoveda nemají parametry
     *  příkazy jdi, seber, polož mají jeden parametr
     *  příkaz pouzij může mít dva parametry.
     *  
     *  @param parametry počet parametrů závisí na konkrétním příkazu.
     *  
     */
    public String proved(String... parametry){
        if(parametry.length == 0){
            return "Co mam zahodit?\n";
        }       
        String nazevVyhazovaneVeci = parametry[0];                 
        return inventar.zahodVec(nazevVyhazovaneVeci);
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev(){
        return NAZEV;
    }
}

    //== Soukromé metody (instancí i třídy) ========================================

  
