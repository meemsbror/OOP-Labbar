
public class FastCounter extends CounterModel {

    private int step = 5;

    public FastCounter(){
        super(15);
    }

    public FastCounter(int modulus, int step){
        super(modulus);
        this.step=step;
    }

    public void upMany(){

        for (int i = 0; i <step; i++){

            this.increment();
        }
    }

    public void downMany() {

        for (int i = 0; i < step; i++) {

            this.decrement();

        }
    }


    public int getStep(){
        return step;
    }
}
