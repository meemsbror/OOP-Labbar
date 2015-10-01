
public class CounterModel implements CounterInterface {


    //De privata instansvariablarna!
    private int modulus;
    private int x = 0;
    private static int nbrOfCounters = 0;


    //Konstruktur med ett argument!
    public CounterModel (int modulus){
        if(modulus > 1){
            this.modulus = modulus;
        }else{
            throw new IllegalArgumentException("For input: " + modulus);
        }
        nbrOfCounters +=1;
    }


    //Konstruktur utan argument!
    public CounterModel (){
        modulus = 10;
        nbrOfCounters +=1;

    }

    //Metoden som ökar den privata variabeln x med ett! Om x går över modulus blir x noll.
    public void increment(){
        x = ++x % modulus;
    }

    //Metoden som minskar den privata variabeln x med ett! Om x går under 0 blir x modulus-1.
    public void decrement(){
        if(0 != x){
            x--;
        }else{
            x = modulus-1;
        }
    }

    //Ser om ett object o har samma värdern/är av samma objekttyp som detta.
    public boolean equals(Object o){
        if (this==o) {
            return true;
        }

        if( o==null || !o.getClass().equals(this.getClass())) {
            return false;
        }
            CounterModel cm = (CounterModel)o;
            if(cm.getModulus()==this.getModulus() && cm.getValue()==this.getValue()){
                return true;
            }
        return false;
    }

    //toString metod för att göra snygga utskrifter.
    public String toString(){
        return "The counter is at: " + getValue() + ". And the cap is at: " + getModulus();
    }

    //Getters
    public int getModulus() {
        return modulus;
    }
    public int getValue(){
        return x;
    }
    public static int getNbrOfCounters() {
        return nbrOfCounters;
    }
    //Reset
    public void reset(){
        x = 0;
    }
}