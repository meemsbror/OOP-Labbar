/**
 * ChainedCounterModel kopplar ihop flera räknare genom att
 * öka nästa när denna når sitt maxvärde.
 * @author Arvid Björklund & Frej Karlsson
 * @version 1.0000002
 */
public class ChainedCounterModel extends CounterModel{

    CounterInterface next;

    /**
     * Konstruktor som tar emot nästa CounterInterface, ett innitiellt värde och en modulus. Kallar på Countermodels konstruktor,
     * kör increment så många gånger som init säger.
     * @param init Räknarens startvärde
     * @param modulus Räknarens maxvärde
     * @param next Nästa räknare
     */
    public ChainedCounterModel(int init, int modulus, CounterInterface next){
        super(modulus);
        this.next = next;
        for(int i=0;i<init;i++){
            this.increment();
        }
    }

    /**
     * Kör CounterModels increment och kollar om värdet slått runt (blivit noll), och ökar i så fall nästa räknare.
     */
    public void increment(){
        super.increment();
        if(this.getValue()==0 && next!=null){
            next.increment();
        }
    }
}
