//SurveyHolder.java

import java.awt.CardLayout;
import javax.swing.JFrame;	
import javax.swing.JPanel;

public class SurveyHolder extends JPanel
{
	public SurveyHolder()
	{
	}
	
	public static void main(String[] args)
	{
	}
	
	public void setIt()
	{
		CardLayout myCards = new CardLayout();
		setLayout(myCards);
		
		WelcomePagePanel we = new WelcomePagePanel(this, myCards);
		we.set();
		Register rg = new Register(this, myCards);
		rg.paintIt();
		LogIn lg = new LogIn(this, myCards);
		lg.checkIt();
		Create ct = new Create(this, myCards);
		ct.start();
		System.out.print("SurveyHolder");
	}
}
