package labyrint;

public class LabSolver {
    static int counter=0;

    public static void main(String[] args) {
        int width = 20;
        int height = 10;
        if (args.length > 1) {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
        }
        Labyrinth l = new Lab(width,height);
        System.out.println("\nCreated a random labyrinth:");
        System.out.println(l);
        boolean success = findPath(0,0,width-1,height-1,l);
        if (success) {
            System.out.println("Solution found:");
            System.out.println(l);
        } else {
            System.out.println("Failed to find a solution. (Bug in LabSolver.java)");
        }
    }

    public static boolean findPath(int x0, int y0, int x1, int y1, Labyrinth l) {
        //Markerar att vi har varit på platsen vi studerar just nu.
        l.setMark(x0,y0,true);
        //Basvilkoret som returnerar sant om vi löst labyrinten
        if ((x0 == x1) && (y0 == y1))
            return true;
        /*
        Loopar igenom alla riktningar och ändrar värde på de nya variablarna x && y som är värdet på nästa plats
        därefter testas om det går att flytta till platsen x,y (om den är upptagen), går det så anropar metoden
        sig själv och gör om funktionen med de nya kordinaterna. Om det inte går att flytta någonstans kommer
        rekursionen kolapsa med värdet false och senaste otestade vägval kommer börja köras igen. Tillslut kommer
        x0 == x1 && y0 == y1 och rekursionen kommer kolapsa med värdet true samt en markering på varje plats på vägen
        */
        for (Labyrinth.Direction dir : Labyrinth.Direction.values()) {
            int x=x0;
            int y=y0;
            if(dir.toString().equals("RIGHT"))
                x++;
            else if (dir.toString().equals("LEFT"))
                x--;
            else if (dir.toString().equals("DOWN"))
                y++;
            else if (dir.toString().equals("UP"))
                y--;
            //kallar på findPath rekursivt om det går att röra sig i den riktningen vi har i dir och vi inte redan markerat riktningen som True.
            if (l.canMove(dir, x0, y0) && !(l.getMark(x,y))) {
                //Sätter markeringen false om vi inte hittar någon lösning i den riktningen.
                if(findPath(x,y,x1,y1,l))
                    return true;
                else
                    l.setMark(x,y,false);
            }
        }
        return false;
    }
}
