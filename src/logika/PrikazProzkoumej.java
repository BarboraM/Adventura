/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy PrikazSeber představují ...
 *
 *@author     Barbora Mlejnková
 *@version    ZS 2016/2017
 */
public class PrikazProzkoumej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prozkoumej";
    private HerniPlan plan;
    private Inventar inventar; 
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazProzkoumej(HerniPlan plan, Inventar inventar)
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
            return plan.getAktualniProstor().getPopisVeci();
        }       
        String nazevVeci = parametry[0];        
        Prostor aktualni = plan.getAktualniProstor();        
        Vec prozkoumavana = aktualni.odeberVec(nazevVeci);        
        if(prozkoumavana == null){
            return "Tuhle vec tu nevidim";
        }
        else{
            if(prozkoumavana.jeProzkoumatelna()){
                switch (prozkoumavana.getNazev()){
                    case "knihovna": 
                        System.out.println("Hmm...Pohadka o princezne...Italske speciality....To je ono! Pruvodce chemickym utokem!!!");
                        aktualni.vlozVec(new Vec("pohadka", false, false));
                        aktualni.vlozVec(new Vec("kucharka", false, false));
                        aktualni.vlozVec(new Vec("pruvodce", true, true));
                        return aktualni.getPopisVeci();
                    case "matrace" : 
                        System.out.println("Co na ni muze byt zajimaveho?...Pockat, co je to tu pod ni?! Klice!");
                        aktualni.vlozVec(new Vec("klice", true, false));
                        return aktualni.getPopisVeci();
                    case "pruvodce" :
                        return "'Jak vytvorit ochranny oblek: Pro pohyb v kontaminovanem prostredi je nutne vytvorit ochranny oblek slepenim nekolika kusu neprodysne latky....'\n"
                        + "No dobre, techhle par veci tu urcite nekde sezenu..";
                    case "kredenc" :
                        System.out.println("1.suplik nic...2.suplik nic...V poslednim jsou jen nuzky..");
                        aktualni.vlozVec(new Vec("nuzky", true, false));
                        return aktualni.getPopisVeci();
                    default: return "Nic zvlastniho tu nevidim";
                }
            }
            else{
                aktualni.vlozVec(prozkoumavana);
                return "Nic zvlastniho nevidim";
            }
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev(){
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
