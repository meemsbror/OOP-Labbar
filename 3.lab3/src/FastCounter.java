
public class FastCounter extends CounterModel {
    //Hur många steg den ska hoppa om man anropar metoden up/downMany
    private int step = 5;

    public FastCounter(){
        super(15);
    }

    public FastCounter(int modulus, int step){
        super(modulus);
        this.step=step;
    }

    public void upMany(){

        for (int i = 0; i < step; i++){

            this.increment();
        }
    }

    public void downMany() {

        for (int i = 0; i < step; i++) {

            this.decrement();

        }
    }

    public String toString(){
        return super.toString() + " step: " + step;
    }

    public boolean equals(Object o){
        if(super.equals(o)){
            FastCounter f = (FastCounter)o;
            return this.getStep()==f.getStep();
        }

        return false;
    }


    public int getStep(){
        return step;
    }
}
