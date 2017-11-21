package logika;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import utils.Observer;
import utils.Subject;

/**
 * Write a description of class Batoh here.
 * 
 *@author     Barbora Mlejnková
 *@version    ZS 2016/2017
 */
public class Inventar implements Subject
{
    // instance variables - replace the example below with your own
    private Map <String, Vec> veciVInventari;      // v�ci v batohu
    private static final int MAX_OBSAH = 7;    // maxim�ln� kapacita
    private HerniPlan plan;
    
    private List<Observer> listObserveru = new ArrayList<Observer>();
    
    /**
     * Konstruktor.
     */
    public Inventar(HerniPlan plan){
        veciVInventari = new HashMap<String, Vec>();
        this.plan = plan;
    }

    /**
     * Kontroluje, zda je místo v inventáři a zda je vkládaná věc přenostilená. Pokud ano, vkládá vec do inventáře a vrací hodnotu boolean.
     * @return Vkládá věc do inventáře
     */
    public boolean vlozVec(Vec vec){
        if(jeMistoVInventari() && (vec.jePrenositelna())) {
            veciVInventari.put(vec.getNazev(), vec);
            notifyObservers();
            return true;
        }
        return false;
    }
    
    /**
     * Vyhledává věc podle názvu a pokud v inventářii danou věc nalezne, odebere ji.
     */ 
    public String zahodVec(String nazev){
        String odpoved;
        if (veciVInventari.containsKey(nazev)) {
            Vec vec = odeberVec(nazev);
            plan.getAktualniProstor().vlozVec(vec);
            odpoved =  "Vyhozeno.\n";
            notifyObservers();
            return odpoved;
        }else{
            odpoved = "Tohle v intventari nemam.\n";
            return odpoved;
        }
    }  
    
   /**
     * Vrací odkaz na věc z inventáře
     * 
     *
     * @return věc podle názvu
     */ 
    public Vec getVec(String nazevVeci){
        return veciVInventari.get(nazevVeci);
    }
    
     /**
     * Vrací zda je v inventáři místo pro ukládání dalších věcí
     * 
     *
     * @return boolean, zda je místo v inventáři
     */ 
    public boolean jeMistoVInventari(){
        return (veciVInventari.size() < MAX_OBSAH);
    }
    
     /**
     * Vrací odkaz na věc, kterou odebere z inventáře
     * 
     *
     * @return  odebíraná věc z inventáře
     */ 
    public Vec odeberVec(String nazev){
        return veciVInventari.remove(nazev);
    }
    
      /**
     * Vrací textový řetězec, který se skládá z názvů věcí, které se nachází v inventáři
     * 
     *
     * @return textový řetězec obsahující věci z inventáře
     */ 
    public String obsahInventare(){
        String veci = "Co mam u sebe: ";
        for(String nazevVeci : veciVInventari.keySet()){
            veci += nazevVeci + "\t\t";
        }
        return veci + "\n";
    }
    
     /**
     * Vrací zda inventář obsahuje hledanou věc
     * 
     *
     * @return boolean zda inventář obsahuje hledanou věc
     */ 
    public boolean jeVInventari(String nazev){
        return veciVInventari.containsKey(nazev);    
    }
    
    public Map<String, Vec> getVeciVInventari(){
        return veciVInventari;
    }
    
    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
}
