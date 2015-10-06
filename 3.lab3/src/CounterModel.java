/**
 * CounterModel är en klass som representerar en räknare
 * dvs. att öka samt minska en heltalsvariabel.
 * Den innehåler en modulus och ett värde(value).
 * @author Arvid Björklund & Frej Karlsson
 * @version 2.42
 */
public class CounterModel implements CounterInterface {


    //De privata instansvariablarna!
    private int modulus;
    private int x = 0;
    private static int nbrOfCounters = 0;


    /**
     * Sätter modulus värdet och kollar om värdet är > 1
     * och ökar klassvariabeln nbrOfCounters som visar hur många räknare som skapats
     * @throws IllegalArgumentException om modulus är < 1
     * @param modulus bestämmer max-värdet på räknaren.
     */
    public CounterModel (int modulus){
        if(modulus > 1){
            this.modulus = modulus;
        }else{
            throw new IllegalArgumentException("Bad input for input: " + modulus);
        }
        nbrOfCounters +=1;
    }


    //Konstruktur utan argument!
    public CounterModel (){
        modulus = 10;
        nbrOfCounters +=1;

    }

    /**
     *Denna metod ökar den privata variabeln x, om x blir lika med modulus sätts x till
     *noll istället. variabeln x är det värde som "räknaren" visar.
     */
    public void increment(){
        x = ++x % modulus;
    }

    /**
     *Decrement metoden liknar increment väldigt mycket på det vis att dens ända uppgift
     *är att ändra värdet på x. Fast denna metod minskar istället för att öka värdet.
     */
    public void decrement(){
        if(0 != x){
            x--;
        }else{
            x = modulus-1;
        }
    }

    /**
     * Kollar om parametern o har samma värden som räknaren
     * @param o ett objekt som jämnförs med räknaren.
     * @return Returnerar sant om objectet är av samma typ samt har samma värden.
     */
    public boolean equals(Object o){
        if (this==o) {
            return true;
        }

        if( o==null || !(o.getClass().toString().equals(this.getClass().toString()))) {
            return false;
        }
        CounterModel cm = (CounterModel)o;
        if(cm.getModulus()==this.getModulus() && cm.getValue()==this.getValue()){
            return true;
        }
        return false;
    }

    /**
     * toString är en standardmetod som man alltid ska skriva.
     */
    public String toString(){
        return "The counter is at: " + getValue() + ". And the cap is at: " + getModulus();
    }

    /**
     * returnerar modulus
     * @return modulus maximala värdet på räknaren
     */
    public int getModulus() {
        return modulus;
    }
    /**
     * returnerar värdet på räknaren
     * @return x värdet på räknaren
     */
    public int getValue(){
        return x;
    }
    /**
     * returnerar nbrOfCounters
     * @return nbrOfCounters antalet skapade räknare
     */
    public static int getNbrOfCounters() {
        return nbrOfCounters;
    }

    /**
     * Sätter värdet av räknaren till 0 (startvärdet)
     */
    public void reset(){
        x = 0;
    }
}