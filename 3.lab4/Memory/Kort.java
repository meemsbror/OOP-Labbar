package Memory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 2015-10-08.
 */
public class Kort extends JColorfulButton {
    public enum Status{ DOLT, SYNLIGT, SAKNAS}
    private Status status;
    Icon icon;
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
        if(o instanceof Kort){
            Kort k = (Kort)o;
            return k.getIcon()==this.getIcon();
        }
        return false;
    }
}
