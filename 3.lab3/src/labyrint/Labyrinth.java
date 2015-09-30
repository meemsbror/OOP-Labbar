package labyrint;

public interface Labyrinth {

    public enum Direction { RIGHT, LEFT, UP, DOWN }

    // den viktigaste funktionen

    public boolean canMove(Direction dir, int x, int y);

    // getters

    public int getWidth();
    public int getHeight();

    // man kan spara en boolean vid varje koordinat

    public void setMark(int x, int y, boolean b);
    public boolean getMark(int x, int y);

    // man kan skriva ut tillståndet till sträng

    public String toString();

}
