package SimpleCounter;

public class FastCounter extends CounterModel {

    public void upMany(int up){

        for (int i = 0; i <up; i++){

            this.increment();
        }
    }

    public void downMany(int down) {

        for (int i = 0; i < down; i++) {

            this.decrement();

        }
    }
}
