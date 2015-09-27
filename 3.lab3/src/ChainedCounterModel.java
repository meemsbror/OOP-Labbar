import org.w3c.dom.css.Counter;

public class ChainedCounterModel extends CounterModel{

    CounterInterface next;

    public ChainedCounterModel(int init, int modulus, CounterInterface next){
        super(modulus);
        this.next = next;
        for(int i=0;i<init;i++){
            this.increment();
        }
    }

    public void increment(){
        super.increment();
        if(this.getValue()==0 && next!=null){
            next.increment();
        }
    }
}
