import org.w3c.dom.css.Counter;

public class ChainedCounterModel extends CounterModel{

    CounterInterface next;

    //Konstruktor som tar emot nästa CounterInterface, ett innitiellt värde och en modulus. Kallar på Countermodels konstruktor,
    //kör increment så många gånger som init säger.
    public ChainedCounterModel(int init, int modulus, CounterInterface next){
        super(modulus);
        this.next = next;
        for(int i=0;i<init;i++){
            this.increment();
        }
    }

    //Kör CounterModels increment och kollar om värdet slått runt (blivit noll), och ökar i så fall next.
    public void increment(){
        super.increment();
        if(this.getValue()==0 && next!=null){
            next.increment();
        }
    }
}
