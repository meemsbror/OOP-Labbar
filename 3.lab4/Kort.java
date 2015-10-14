
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by admin on 2015-10-08.
 */
public class Kort extends JColorfulButton
                    implements MouseListener{
    public enum Status{ DOLT, SYNLIGT, SAKNAS}
    private Status status;
    private Icon icon;
/*

Exceptions?

 */
    public	void	mouseClicked(MouseEvent e){

        this.setStatus(Status.SAKNAS);
        this.setStatus(Status.SYNLIGT);
        this.repaint();
        System.out.println("hej");
    }

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
            setIcon(icon);
        }
    }
    public Status getStatus() {
        return status;
    }
    public Kort copy(){
        return new Kort(icon, getStatus());
    }

    public boolean sammaBild(Object o){
        if(o.getClass().toString().equals(this.getClass().toString())){
            Kort k = (Kort)o;
            return k.getIcon()==this.getIcon();
        }
        return false;
    }
}
