
public class FastCounter extends CounterModel {

    private int step = 5;

    public FastCounter(){
        super(15);
    }

    //Konstruktor som tar modulus och step, kallar på countermodels konstruktor samt sätter step som step.
    public FastCounter(int modulus, int step){
        super(modulus);
        this.step=step;
    }
    //Kallar på countermodels increment step antal gånger.
    public void upMany(){
        for (int i = 0; i < step; i++){
            this.increment();
        }
    }
    //Kallar på countermodels decrement step antal gånger.
    public void downMany() {
        for (int i = 0; i < step; i++) {
            this.decrement();
        }
    }
    //Kallar på countermodels toString och skriver ut step också.
    public String toString(){
        return super.toString() + " step: " + step;
    }

    //Kollar om o klarar countermodels equals metod, sen kollar den om step har samma värde.
    public boolean equals(Object o){
        if(super.equals(o)){
            FastCounter f = (FastCounter)o;
            return this.getStep()==f.getStep();
        }

        return false;
    }

    //returnerar step
    public int getStep(){
        return step;
    }
}
