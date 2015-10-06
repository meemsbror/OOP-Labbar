/**
 * FastCounter är en klass som representerar en räknare som hoppar flera steg åt gången
 * dvs. ökar samt minskar en heltalsvariabel.
 * Den ärver CounterModel och innehåler heltalsvärdet step som beskriver hur många steg räknaren ska "hoppa".
 * @author Arvid Björklund & Frej Karlsson
 * @version 4003.2552
 */
public class FastCounter extends CounterModel {

    private int step = 5;

    public FastCounter(){
        super(15);
    }

    //

    /**
     * Konstruktor som tar modulus och step, kallar på countermodels konstruktor samt sätter step som step.
     * @param modulus Maxvärdet på räknaren
     * @param step  hur många steg increment och decrement ska kallas i upMany och downMany
     */
    public FastCounter(int modulus, int step){
        super(modulus);
        this.step=step;
    }

    /**
     * Kallar på countermodels increment step antal gånger.
     */
    public void upMany(){
        for (int i = 0; i < step; i++){
            this.increment();
        }
    }
    /**
     * Kallar på countermodels decrement step antal gånger.
     */
    public void downMany() {
        for (int i = 0; i < step; i++) {
            this.decrement();
        }
    }

    /**
     * Kallar på countermodels toString och skriver ut step också.
     * @return en lättäst beskrivning utav räknaren
     */
    public String toString(){
        return super.toString() + " step: " + step;
    }

    /**
     * Kollar om parametern o har samma värden som räknaren
     * @param o ett objekt som jämnförs med räknaren.
     * @return Returnerar sant om objectet är av samma typ samt har samma värden.
     */
    public boolean equals(Object o){
        if(super.equals(o)){
            FastCounter f = (FastCounter)o;
            return this.getStep()==f.getStep();
        }

        return false;
    }

    /**
     * returnerar värdet på step
     * @return step antalet steg räknaren tar
     */
    public int getStep(){
        return step;
    }
}
