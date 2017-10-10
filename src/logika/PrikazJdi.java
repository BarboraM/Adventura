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
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mam jit? Musis zadat jmeno vychodu";
        }else{
            String smer = parametry[0];
            // zkoušíme přejít do sousedního prostoru
            Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
            if (sousedniProstor == null) {
                return "Tam se odsud jít nedá! \n";
            }else{
                switch(sousedniProstor.getNazev()){
                    case "prechodova komora":
                        System.out.println("Vypada to, ze dvere ven jsou zamcene...");
                        if(inventar.jeVInventari("klice")){
                            System.out.println("Zkusim pouzit ten klic z pokoje\n" + "Pasuje!\n");
                            return sousedniProstor.dlouhyPopis();                            
                        }
                        else{
                           return "Klic musi byt nekde v bunkru \n"; 
                        }
                    case "silnice":
                            if (inventar.jeVInventari("oblek")){
                                System.out.println("Takze, nandat oblek a otevrit dvere...Doufam, ze me oblek vazne ochrani...\n3, 2, 1 otviram!");
                                plan.setAktualniProstor(sousedniProstor);
                                return sousedniProstor.dlouhyPopis();
                            }else{
                                     System.out.println("Fajn, snad to tam venku zvladnem...\n3, 2, 1 otviram!");
                                     try {
                                         Thread.sleep(1000);                 //1000 milliseconds is one second.
                                        } catch(InterruptedException ex) {
                                            Thread.currentThread().interrupt();
                                        }
                                     hra.setKonecHry(true);
                                     return ("Nejak mi neni dobre, myslim, ze jit ven bez ochranneho obleku nebyl nejlepsi napad....\n" +
                                     "Ja....nemuzu dychat!!! NEMUZU.....!!");
                            }
                    case "auto":
                         System.out.println("RIDIC : CO MI LEZES DO AUTA??\n\n" + 
                            "JA...OMLOUVAM SE, JEN SE POTREBUJU DOSTAT NA LETISTE, STEJNE JAKO VY..PROSIM, POMOZTE MI\n\n"+ 
                            "RIDIC : NO DOBRE, MOZNA BYCH TU MEL JESTE MISTO....ALE V TYHLE SITUACI TO NEBUDE ZADARMO!!\n" + 
                            "VEZMU TE S SEBOU ZA NECO K JIDLU\n");
                        if(inventar.jeVInventari("konzerva")){
                            System.out.println(inventar.obsahInventare()+"\n");
                            System.out.println("JASNE...TADY, MAM U SEBE TUHLE KONZERVU! BERTE...\n\n" + 
                                                "RIDIC : NO DOBRE, NASTUP SI\n");
                            plan.setAktualniProstor(sousedniProstor);
                            hra.setKonecHry(true);
                            return sousedniProstor.dlouhyPopis();
                        }else{
                            System.out.println(inventar.obsahInventare());
                            return "NO..JA...NIC U SEBE NEMAM...ALE POCKEJTE, V BUNKRU JE SPOUSTA JIDLA, NECO SEZENU!!\n";
                        }
                    default:
                        plan.setAktualniProstor(sousedniProstor); 
                        return sousedniProstor.dlouhyPopis();
                }
            }
        }
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
