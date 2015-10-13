import java.util.Random;

/**
 * Created by frej on 2015-10-13.
 */
public class Verktyg {
    private static Random rnd = new Random();

    public static void slumpOrdning(Object o []){
        Object [] tempArr = new Object [o.length];
        int rndInt;
        for(int i = 0; i<o.length; i++){
            rndInt=rnd.nextInt(o.length);

            while(tempArr[rndInt] != null){
                rndInt=rnd.nextInt(o.length);
            }
            tempArr[rndInt]=o[i];
        }
        for(int i = 0; i<o.length; i++){
            o[i]=tempArr[i];
        }


    }
}
