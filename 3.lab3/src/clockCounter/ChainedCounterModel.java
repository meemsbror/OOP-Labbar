package ClockCounter;

import SimpleCounter.CounterInterface;
import SimpleCounter.CounterModel;

public class ChainedCounterModel extends CounterModel {

    private CounterInterface next;

    ChainedCounterModel(int init, int modulus, CounterInterface next){
        super(modulus);
        this.next = next;
        for(int i = 0; i<init;i++){
            this.increment();
        }

    }

    @Override
    public void increment() {
        int x;

        super.increment();

        x=getValue();
        if(x==0 && next!=null){

            next.increment();
        }
    }
}
