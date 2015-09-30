package labyrint;

public class LabSolverTest {

    private static void fail(String str) {
        System.out.println("\nFAILED to find solution for:\n" + str + "\nFAILURE\n");
        System.exit(0);
    }

    private static void testFindPath(Labyrinth l) {
        String str = l.toString();
        if (LabSolver.findPath(0,0,l.getWidth()-1,l.getHeight()-1,l)) {
            str = l.toString();
            int x = 0;
            int y = 0;
            l.setMark(x,y,false);
            while ((x != l.getWidth()-1) || (y != l.getHeight()-1)) {
                l.setMark(x,y,false);
                if (l.canMove(Labyrinth.Direction.RIGHT,x,y) && l.getMark(x+1,y)) {
                    x = x+1;
                } else if (l.canMove(Labyrinth.Direction.LEFT,x,y) && l.getMark(x-1,y)) {
                    x = x-1;
                } else if (l.canMove(Labyrinth.Direction.UP,x,y) && l.getMark(x,y-1)) {
                    y = y-1;
                } else if (l.canMove(Labyrinth.Direction.DOWN,x,y) && l.getMark(x,y+1)) {
                    y = y+1;
                } else {
                    fail(str);
                }
            }
            l.setMark(x,y,false);
            // check that all cells are empty
            for (int i=0; i<l.getWidth(); i++) {
                for (int j=0; j<l.getHeight(); j++) {
                    if (l.getMark(i,j)) {
                        fail(str);
                    }
                }
            }
            // System.out.println(str); // for debugging only
        } else {
            fail(str);
        }
    }

    public static void main(String[] args) {
        testFindPath(new Lab(3,3));
        testFindPath(new Lab(5,5));
        testFindPath(new Lab(10,10));
        testFindPath(new Lab(20,20));
        testFindPath(new Lab(30,20));
        testFindPath(new Lab(40,100));
        System.out.println("\nSUCCESS\n");
    }

}
