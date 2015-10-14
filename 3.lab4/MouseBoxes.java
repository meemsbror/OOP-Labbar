import	java.awt.*;
import	java.awt.event.*;
import	javax.swing.*;
public	class	MouseBoxes	extends	JFrame	{
    private	class	DemoPanel	extends	JPanel
            implements	MouseListener	{
        private	int	BOX_WIDTH	=	40;
        private	int	x	=	-50;
        private	int	y	=	-50;
        public	void	paintComponent(Graphics	g)	{
            super.paintComponent(g);
            for	(int	i=0;	i<5;	i++)	{
                for	(int	j=0;	j<5;	j++)	{
                    if	(x	==	i	&&	y	==	j)	{
                        g.fillRect(i*BOX_WIDTH,j*BOX_WIDTH,
                                BOX_WIDTH,BOX_WIDTH);
                    }	else	{
                        g.drawRect(i*BOX_WIDTH,j*BOX_WIDTH,
                                BOX_WIDTH,BOX_WIDTH);
                    }
                }
            }
        }
        public	void	mouseClicked(MouseEvent	e)	{
            x	=	e.getX()	/	BOX_WIDTH;
            y	=	e.getY()	/	BOX_WIDTH;
            System.out.println("test");
            this.repaint();
        }
        public	void	mouseEntered(MouseEvent	e)	{}
        public	void	mouseExited(MouseEvent	e)	{}
        public	void	mousePressed(MouseEvent	e)	{}
        public	void	mouseReleased(MouseEvent	e)	{}
    }
    public	MouseBoxes()	{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(50,50);
        DemoPanel	panel	=	new	DemoPanel();
        panel.addMouseListener(panel);
        panel.setPreferredSize(new	java.awt.Dimension(200,200));
        add(panel);	pack();	setVisible(true);
    }
    public	static	void	main(String[]	args)	{
        MouseBoxes	f	=	new	MouseBoxes();
    }
}	