/**
 * En testklass som testar klasserna CounterModel, FastCounter och ChainedCounterModel
 * @author Arvid Björklund & Frej Karlsson
 * @version 3.141592965358979
 */
public class TestCounter {
    public static void main (String [] args){
        //Skapar 2 CounterModels
        CounterModel cm1 = new CounterModel();
        CounterModel cm2 = new CounterModel(5);
        //Skapar de klasser som ärver utav CounterModel
        FastCounter cm3 = new FastCounter(5, 2);
        FastCounter cm6 = new FastCounter(5, 2);
        ChainedCounterModel cm4 = new ChainedCounterModel(5,10,cm1);

        //Ser om den statiska variabeln är 4.
        System.out.println("Number of counters created: \t\t" + CounterModel.getNbrOfCounters() + "\t\t---Should be 4.");

        //Skriver ut och ändrar värden på do olika CounterModel objekten.
        System.out.println("Is cm1 equal to cm2? \t\t\t\t" + cm1.equals(cm2) + " \t---Should give False.");

        //Ökar både cm1 och cm2 med 10 så att båda står på 0
        for(int i = 0; 10 < i++;){
            cm1.increment();
            cm2.increment();
        }
        System.out.println("Is cm1 at the same count as cm2?\t" + (cm1.getValue() == cm2.getValue()) + " \t---Should give True.");


        System.out.println("Is cm1 equal to cm3? \t\t\t\t" + cm1.equals(cm3) + " \t---Should give False.");
        //Minskar cm2 med 3 så att den stannar på 2 (5-3)
        cm2.decrement();
        cm2.decrement();
        cm2.decrement();
        //Ökar cm3 med 1 (*2) så att den stannar på 2;
        cm3.upMany();

        System.out.println("Is cm2 at the same count as cm3?\t" + (cm2.getValue() == cm3.getValue()) + " \t---Should give True.");

        //Ökar ChainedCounterModel till dess modulus så att cm1 (countermodelen som den får som argument) ökar med 1
        for(int i = 0; 5 >i++;){
            cm4.increment();
        }
        System.out.println("Did cm1s count increase by one?\t\t" + (cm1.getValue() == 1) + " \t---Should give True.");

        cm1.reset();
        System.out.println("Is cm1 at 0 after reset? "+ (cm1.getValue()==0)+ "\tShould be true");

        cm1.decrement();
        System.out.println("did cm1 wraparound on decrement?\t"+(cm1.getModulus()-1==cm1.getValue()) + "\tshould be true");


        //Testar equals på Fastcounter och countermodel
        cm3.reset();
        System.out.println("should be true\t"+cm3.equals(cm6));

        cm3.increment();
        cm6.increment();
        System.out.println("should be true\t"+cm3.equals(cm6));
        cm3.increment();
        cm6.increment();
        cm3.increment();
        cm6.increment();
        System.out.println("should be true\t"+cm3.equals(cm6));

        //Test illegal argument on Countermodel
        try {
            CounterModel cm7 = new CounterModel(-2);
        }
        catch (IllegalArgumentException e1){
            System.out.println(e1.getMessage());
        }



        //Testar de olika toString metoderna:
        System.out.println(cm1);
        System.out.println(cm2);
        System.out.println(cm3);
        System.out.println(cm4);

        //Skapar en ChainedCounterModel med null som in samt ser om nmbOfCounters har uppdaterats
        ChainedCounterModel cm5 = new ChainedCounterModel(10,20,null);
        System.out.println(CounterModel.getNbrOfCounters());
    }
}
