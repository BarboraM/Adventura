package logika;


/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2015/2016
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Inventar inventar;
    private Hra hra;
   
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan, Inventar inventar, Hra hra) {
        this.plan = plan;
        this.inventar = inventar;
        this.hra = hra;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        String odpoved;
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            odpoved = "Kam mam jit? Musis zadat jmeno vychodu\n";
        }else{
            String smer = parametry[0];
            // zkoušíme přejít do sousedního prostoru
            Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
            if (sousedniProstor == null) {
                odpoved = "Tam se odsud jít nedá! \n";
            }else{
                switch(sousedniProstor.getNazev()){
                    case "chodba":
                        odpoved = "Vypada to, ze dvere ven jsou zamcene...";
                        if(inventar.jeVInventari("klice")){
                            odpoved += "\nZkusim pouzit ten klic z pokoje\n" + "Pasuje!\n";
                            plan.setAktualniProstor(sousedniProstor);
                            odpoved += sousedniProstor.dlouhyPopis();  
                            return odpoved;
                        }
                        else{
                           odpoved += "Klic musi byt nekde v bunkru \n"; 
                           return odpoved;
                        }
                    case "silnice":
                            if (inventar.jeVInventari("oblek")){
                                odpoved = "Takze, nandat oblek a otevrit dvere...Doufam, ze me oblek vazne ochrani...3, 2, 1 otviram!\n";
                                plan.setAktualniProstor(sousedniProstor);
                                odpoved += sousedniProstor.dlouhyPopis();
                                return odpoved;
                            }else{
                                     odpoved = "Fajn, snad to tam venku zvladnem..."
                                             + "3, 2, 1 otviram!\n"
                                             + "Nejak mi neni dobre, myslim, ze jit ven bez ochranneho obleku nebyl nejlepsi napad."
                                             + "Ja....nemuzu dychat!!! NEMUZU.....!!\n"
                                             + "KONEC HRY";
                                     hra.setKonecHry(true);
                                     return odpoved;
                            }
                    case "auto":
                         odpoved = "RIDIC : Koukej vypadnout od myho auta\n" + 
                            "Ja, omlouvam se, jen se potrebuju dostat na letiste, stejne jako vy. Prosim, POMOZTE MI\n"+ 
                            "RIDIC : No dobre, mozna bych tu mel jeste misto...Ale v tyhle situaci to nebude zadarmo!\n" + 
                            "Par dni uz jsem nejedl, vezmu te za neco k jidlu\n";
                        if(inventar.jeVInventari("konzerva")){
                            odpoved += "Jasne, tady...mam u sebe tuhle konzervu\n" + 
                                                "RIDIC : Fajn, nastup si.\n";
                            plan.setAktualniProstor(sousedniProstor);
                            odpoved += sousedniProstor.dlouhyPopis();
                            hra.setKonecHry(true);
                            return odpoved;
                        }else{
                            odpoved += "No..ja..nic u sebe nemam. Ale pockejte, v bunkru urcite bude neco k jidlu!\n";
                            return odpoved;
                        }
                    default:
                        plan.setAktualniProstor(sousedniProstor); 
                        odpoved = sousedniProstor.dlouhyPopis();
                        return odpoved;
                }
            }
        }
        return "";
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
