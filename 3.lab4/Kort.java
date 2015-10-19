
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class Kort extends JColorfulButton
                    implements MouseListener{
    public enum Status{ DOLT, SYNLIGT, SAKNAS}
    private Status status;
    private Icon icon;
/*

Exceptions?

 */

    public Kort(Icon icon){
        this.icon = icon;
        setStatus(Status.SAKNAS);
    }

    public Kort(Icon icon, Status status){
        this.icon = icon;
        setStatus(status);
    }

    public void setStatus(Status status) {
        setIcon(null);
        this.status = status;
        if(this.status == Status.DOLT){
            setBackground(Color.BLUE);
        }else if(this.status == Status.SAKNAS){
            setBackground(Color.WHITE);
        }else{
            setBackground(Color.BLACK);
            setIcon(icon);
        }
    }
    public Status getStatus() {
        return status;
    }
    public Icon getKortIcon(){
        return icon;
    }
    public Kort copy(){
        return new Kort(icon, getStatus());
    }

    public boolean sammaBild(Object o){
        if(o instanceof Kort){
            Kort k = (Kort)o;
            return k.getKortIcon()==this.getKortIcon();
        }
        return false;
    }
}
