//Arushi Singh
//SurveyMedia.java
//06-13-22
//all the imports 



import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;	
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.Adjustable;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

import java.security.NoSuchAlgorithmException;  
import java.security.MessageDigest;   

public class SurveyMedia
{
	public SurveyMedia()
	{
	}
	
	public static void main(String[] args)
	{
		SurveyMedia sm = new SurveyMedia();
		sm.runIt();
		
	}
	
	public void runIt()
	{
		//All the basic JFrame stuff
		JFrame media = new JFrame("Survey Media");
		media.setSize(1500,900);
		media.setDefaultCloseOperation(media.EXIT_ON_CLOSE); 
		media.setLocation(100,15);
		media.setResizable(true);
		SurveyHolder bph = new SurveyHolder();
		media.getContentPane().add(bph);
		media.setVisible(true);
	}
	
}//end of class

class SurveyHolder extends JPanel
{
	public SurveyHolder()
	{
		CardLayout myCards = new CardLayout();
		setLayout(myCards);
		
		WelcomePagePanel wpp = new WelcomePagePanel(this, myCards);
		add(wpp, "First");
		//Survey sv = new Survey(this, myCards);
		//add(sv, "Survey");
	}
}


class WelcomePagePanel extends JPanel
{
	private SurveyHolder sm;
	private CardLayout card;
	private JButton register;
	private JButton log;
	
	public WelcomePagePanel(SurveyHolder se, CardLayout cards)
	{
		sm = se;
		card = cards; 
		Color color = new Color(175,238,238);
		
		setLayout(null);
		setBackground(color);

		
		JLabel welcomeLabel = new JLabel("Welcome to SurveyMedia!"); //welcome message
		welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 45));
		welcomeLabel.setBounds(475, -300, 800, 800);
		add(welcomeLabel);
		
		register = new JButton("Sign Up");
		register.setFont(new Font("Times New Roman", Font.BOLD, 25));
		register.setBounds(640, 500, 200, 60);
		ButtonHandlerHere bh = new ButtonHandlerHere();
		register.addActionListener(bh);
		add(register);
		log = new JButton("Login");
		log.setFont(new Font("Times New Roman", Font.BOLD, 25));
		log.setBounds(640, 300, 200, 60);
		add(log);
		log.addActionListener(bh);
		
		JButton quit = new JButton("Quit");
		quit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		quit.setBounds(50,20, 100,50);
		add(quit);
		quit.addActionListener(bh);
	}
	
	class ButtonHandlerHere implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("Sign Up"))
			{
				Register rg = new Register(sm, card);
				sm.add(rg, "Register");
				card.show(sm, "Register");
			}
			else if(command.equals("Login"))
			{
				LogIn li = new LogIn(sm, card);
				sm.add(li, "Logging");
				card.show(sm, "Logging");
			}
			else if(command.equals("Quit"))
			{
				System.exit(1);
			}
		}
	}
}


class Register extends JPanel
{
	public SurveyHolder su;
	public CardLayout cl;
	public String username;
	public JTextField jtf;
	public JTextField jth;
	private boolean stop;
	private int ct;
			
		public Register(SurveyHolder sh, CardLayout card)
		{
			su = sh;
			cl = card;
			
			setBackground(Color.YELLOW);
			setLayout(null);
			
			JTextArea jl = new JTextArea("Welcome! Please enter your desired username down " 
				+ "below your username must be alphanumeric and within 15 characters. Then you will be asked to give a password.");
			jl.setFont(new Font("Times New Roman", Font.BOLD, 42));
			jl.setBounds(275, 90, 900, 250);
			jl.setBackground(Color.YELLOW);
			jl.setLineWrap(true); 
			jl.setWrapStyleWord(true);
			jl.setEditable(false);
			add(jl);
			
			TextFieldHandler jtfh = new TextFieldHandler();
			
			jtf = new JTextField("Enter USERNAME here then click enter");
			jtf.setFont(new Font("Times New Roman", Font.BOLD, 18));
			jtf.setEditable(true);
			jtf.setBounds(560, 450, 350, 40);
			add(jtf);
			jtf.addActionListener(jtfh);
			
			ButtonHandler ba = new ButtonHandler();
			JButton next = new JButton("Next");
			next.addActionListener(ba);
			next.setFont(new Font("Times New Roman", Font.BOLD, 22));
			next.setBounds(675, 600, 125, 50);
			add(next);
			
			JButton back = new JButton("Back");
			back.addActionListener(ba);
			back.setFont(new Font("Times New Roman", Font.BOLD, 22));
			back.setBounds(50, 20, 100, 50);
			add(back);
			
		}
		
		class TextFieldHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				if(jtf.getText()!= null && !jtf.getText().equals("Enter USERNAME here then click enter") && !jtf.getText().equals(""))
				{
					username = jtf.getText();
					checkUser();
				}
			}
		}
		
		public void checkUser()
		{
			Scanner inFile = null;
			String fileName = "UserInfo.txt";
			File inputFile = new File(fileName);
			try
			{
				inFile = new Scanner(inputFile);
			}
			catch(FileNotFoundException e)
			{
				System.err.printf("ERROR: Cannot open %s\n", fileName);
				System.out.println(e);
			}
			
			boolean keep = true;
			while(inFile.hasNextLine() && keep == true) {
			String line = inFile.nextLine();
			if(line.equals(username))
			{
				stop = false;
				repaint();
				keep = false;
				ct = 2;
			}
			else
			{
				stop = true;
				repaint();
				ct = 1;
			}
		}
				
			
		}
		
		public void paintComponent(Graphics g)
		{
			if(stop == false && ct == 2)
			{
				super.paintComponent(g);
				g.drawString("This username is taken. Please try again.", 600, 410);
			}
			
			else if(stop == true && ct == 1)
			{
				super.paintComponent(g);
				g.drawString("This username is available. Please click next to continue.", 550, 410);
			}
		}
		
		class ButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{

				String command = evt.getActionCommand();
				if(command.equals("Back"))
				{
					cl.show(su, "First");
				}
				else if(command.equals("Next") && !username.equals("") && !username.equals("Enter USERNAME here then click enter") && stop == true)
				{
					cl.show(su, "Page");
					jtf.setText("Enter USERNAME here then click enter");
					Page pg = new Page(su, cl, username);
					su.add(pg, "Page");
					cl.show(su, "Page");
				}
				
			}
		} 
	}
			

	class Page extends JPanel
	{
		private SurveyHolder su;
		private CardLayout cl;
		public JTextField jth;
		public String password;
		public String encrypt;
		public String username;

		
		public Page( SurveyHolder sh, CardLayout card, String name)
		{
			su = sh;
			cl = card;
			username = name;
			
			setBackground(Color.YELLOW);
			setLayout(null);
			
			
			JTextArea jl = new JTextArea("Welcome! Please enter your password down " 
				+ "below your password must be alphanumeric and within 15 characters. Then you will taken to a panel to create a survey");
			jl.setFont(new Font("Times New Roman", Font.BOLD, 42));
			jl.setBounds(275, 90, 900, 250);
			jl.setBackground(Color.YELLOW);
			jl.setLineWrap(true);
			jl.setWrapStyleWord(true);
			jl.setEditable(false);
			add(jl);
			
			TextFieldHandler jtfh = new TextFieldHandler();
			
			jth = new JTextField("Enter an alphanumeric PASSWORD here then click enter");
			jth.setFont(new Font("Times New Roman", Font.BOLD, 16));
			jth.setEditable(true);
			jth.setBounds(525, 450, 420, 40);
			add(jth);
			jth.addActionListener(jtfh);
			
			ButtonHandler ba = new ButtonHandler();
			JButton next = new JButton("Sign Up");
			next.addActionListener(ba);
			next.setFont(new Font("Times New Roman", Font.BOLD, 22));
			next.setBounds(675, 600, 125, 50);
			add(next);
			
			JButton back = new JButton("Back");
			back.addActionListener(ba);
			back.setFont(new Font("Times New Roman", Font.BOLD, 22));
			back.setBounds(50, 20, 100, 50);
			add(back);
		}
		
		class TextFieldHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				if(jth.getText() != null && !jth.getText().equals("Enter an alphanumeric PASSWORD here then click enter") && !jth.getText().equals(""))
				{
					password = jth.getText();
					System.out.print(password);
					encryptPassword();
				}
			}
		}
		
		class ButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{

				String command = evt.getActionCommand();
				if(command.equals("Back"))
				{
					cl.show(su, "Register");
				}
				else if(command.equals("Sign Up") && !password.equals("") && !password.equals("Enter USERNAME here then click enter"))
				{
					cl.show(su, "Survey");
					jth.setText("Enter an alphanumeric PASSWORD here then click enter");
				}
				
			}
		}
	
		public void encryptPassword()
		{
			encrypt = null;
			try 
			{  
		
				MessageDigest mess = MessageDigest.getInstance("MD5");   
				mess.update(password.getBytes());    
				byte[] bytes = mess.digest();  
				StringBuilder s = new StringBuilder();  
				for(int i=0; i< bytes.length ;i++)  
				{  
					s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
				}  
				encrypt = s.toString();  
			}   
			catch (NoSuchAlgorithmException e)   
			{  
				e.printStackTrace(); 
			}   
			
			System.out.print(encrypt);
			printToFile();
		}
		
		public void printToFile()
		{
			try(FileWriter fw = new FileWriter("UserInfo.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
			{
				out.println(username);
				out.println(encrypt);
			} catch (IOException e) {
				
			}
		}
	
	}

class LogIn extends JPanel
{
	private String nameenter;
	private SurveyHolder sd;
	private CardLayout ct;
	private JTextField jtf;
	public String username;
	private boolean checked;
	private int countIt;
	
	public LogIn(SurveyHolder sr, CardLayout ca)
	{
		sd = sr;
		ct = ca;
		
		setLayout(null);
		setBackground(Color.WHITE);

		JTextArea jl = new JTextArea(" Please enter your username down below");
		jl.setFont(new Font("Times New Roman", Font.BOLD, 42));
		jl.setBounds(370, 150, 750, 50);
		jl.setLineWrap(true); 
		jl.setWrapStyleWord(true);
		jl.setEditable(false);
		jl.setBackground(Color.WHITE);

		add(jl);
		
		TextFieldHandler jtfh = new TextFieldHandler();
	
		jtf = new JTextField("");
		jtf.setFont(new Font("Times New Roman", Font.BOLD, 18));
		jtf.setEditable(true);
		jtf.setBounds(560, 350, 350, 40);
		add(jtf);
		jtf.addActionListener(jtfh);
		
		ButtonHandler ba = new ButtonHandler();
		JButton next = new JButton("Next");
		next.addActionListener(ba);
		next.setFont(new Font("Times New Roman", Font.BOLD, 22));
		next.setBounds(675, 550, 125, 50);
		add(next);
		
		JButton back = new JButton("Back");
		back.addActionListener(ba);
		back.setFont(new Font("Times New Roman", Font.BOLD, 22));
		back.setBounds(50, 20, 100, 50);
		add(back);
		
	}
	
	class TextFieldHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			if(jtf.getText()!= null && !jtf.getText().equals(""))
			{
				username = jtf.getText();
				System.out.print(username);
				checkUsername();
				
			}
		}
	}
		
	public void checkUsername()
	{
			boolean go = true;
			Scanner inFile = null;
			String fileName = "UserInfo.txt";
			File inputFile = new File(fileName);
			try
			{
				inFile = new Scanner(inputFile);
			}
			catch(FileNotFoundException e)
			{
				System.err.printf("ERROR: Cannot open %s\n", fileName);
				System.out.println(e);
			}
			
			while(inFile.hasNextLine() && go == true) {
			String line = inFile.nextLine();
			if(line.equals(username))
			{
				checked = true;
				go = false;
				countIt = 2;
			}
		
			else
			{
				checked = false;
				repaint();
				countIt = 1;
			}
		}
	}
			
			
	public void paintComponent(Graphics g)
		{
			if(checked == false && countIt == 1)
			{
				super.paintComponent(g);
				g.drawString("This username does not exist. Please try again", 580, 410);
			}
			
			else if(checked == true && countIt == 2)
			{
				super.paintComponent(g);
				g.drawString("Username exists. Please click next to enter password.", 565, 410);
			}
		}
				
	class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("Back"))
			{
				ct.show(sd, "First");
				jtf.setText("");
			}
			else if(command.equals("Next") && !username.equals(""))
			{
				PassPage pa = new PassPage(sd, ct, username);
				sd.add(pa, "Pass");
				ct.show(sd, "Pass");
			}
				
		}
	}
}
	class PassPage extends JPanel
	{
		private SurveyHolder sd;
		private CardLayout ct;
		private String username;
		private String password;
		private JTextField jth;
		private boolean correct;
		private String encryptThis;
		private int count;
		
		public PassPage(SurveyHolder sr, CardLayout ly, String nameOf)
		{
			sd = sr;
			ct = ly;
			username = nameOf;
			count = 0;
			
			setLayout(null);
			setBackground(Color.WHITE);
			
			JTextArea jl = new JTextArea("Welcome " + username + "! Please enter your password down below");
			jl.setFont(new Font("Times New Roman", Font.BOLD, 42));
			jl.setBounds(350, 90, 800, 100);
			jl.setBackground(Color.WHITE);
			jl.setLineWrap(true); 
			jl.setWrapStyleWord(true);
			jl.setEditable(false);
			add(jl);
			
			TextFieldHandler jtfh = new TextFieldHandler();
		
			jth = new JTextField("");
			jth.setFont(new Font("Times New Roman", Font.BOLD, 18));
			jth.setEditable(true);
			jth.setBounds(560, 350, 350, 40);
			add(jth);
			jth.addActionListener(jtfh);
			
			ButtonHandler ba = new ButtonHandler();
			JButton next = new JButton("Log In");
			next.addActionListener(ba);
			next.setFont(new Font("Times New Roman", Font.BOLD, 22));
			next.setBounds(675, 550, 125, 50);
			add(next);
			
			JButton back = new JButton("Back");
			back.addActionListener(ba);
			back.setFont(new Font("Times New Roman", Font.BOLD, 22));
			back.setBounds(50, 20, 100, 50);
			add(back);
			
		}
		
		
		class TextFieldHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				if(jth.getText()!= null && !jth.getText().equals(""))
				{
					password = jth.getText();
					System.out.print(password);
					checkPassword();
				}
			}
		}
			
		class ButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				String command = evt.getActionCommand();
				if(command.equals("Back"))
				{
					ct.show(sd, "Logging");
					jth.setText("");
				}
				else if(command.equals("Log In") && !jth.equals("") && correct == true)
				{
					Survey sv = new Survey(sd, ct, username);
					sd.add(sv, "Survey");
					ct.show(sd, "Survey");
				}
					
			}
		}
		
		public void checkPassword()
		{
			String encrypt = "";
			Scanner inFile = null;
			String fileName = "UserInfo.txt";
			File inputFile = new File(fileName);
			try
			{
				inFile = new Scanner(inputFile);
			}
			catch(FileNotFoundException e)
			{
				System.err.printf("ERROR: Cannot open %s\n", fileName);
				System.out.println(e);
			}
			
			boolean tryThis = true;
			while(inFile.hasNextLine() && tryThis == true) {
			String line = inFile.nextLine();
			String line2 = inFile.nextLine();
			if(line.equals(username))
			{
				try 
				{  
			
					MessageDigest mess = MessageDigest.getInstance("MD5");   
					mess.update(password.getBytes());    
					byte[] bytes = mess.digest();  
					StringBuilder s = new StringBuilder();  
					for(int i=0; i< bytes.length ;i++)  
					{  
						s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
					}  
					encrypt = s.toString();  
				}   
				catch (NoSuchAlgorithmException e)   
				{  
					e.printStackTrace(); 
				}   
				
				if(encrypt.equals(line2))
				{
					correct = true;
					System.out.print("THIS IS RIGHT" + encrypt);
					tryThis = false;
					count = 2;
					repaint();
				}
				
				else
				{
					correct = false;
					count = 1;
				    repaint();
					
				}
			}
			}
		}
		
		public void paintComponent(Graphics g)
		{
			if(correct == false && count == 1)
			{
				super.paintComponent(g);
				g.drawString("Password is incorrect. Please try again", 610, 410);
			}
			
			else if(correct == true && count == 2)
			{
				super.paintComponent(g);
				g.drawString("Password is correct. Please press 'Log In' to continue", 565, 410);
			}
		}	
		
}
	
class Survey extends JPanel
{
	private SurveyHolder sl;
	private CardLayout mc;
	private String username;
	
	public Survey(SurveyHolder sr, CardLayout cl, String user)
	{
		sl = sr;
		mc = cl;
		username = user;
		
		setBackground(Color.ORANGE);
		setLayout(null);
		
		ButtonHandler bh = new ButtonHandler();
		JButton home = new JButton("Home");
		home.setBounds(50, 20, 100, 50);
		add(home);
		home.addActionListener(bh);
		
		JTextArea wc = new JTextArea("Below you are given two options as to whether you want to CREATE a survey or TAKE a survey. Click one of them to continue.");
		wc.setBounds(300, 150, 900, 200);
		wc.setFont(new Font("Times New Roman", Font.BOLD, 37));
		wc.setBackground(Color.ORANGE);
		wc.setLineWrap(true); 
		wc.setWrapStyleWord(true);
		wc.setEditable(false);
		add(wc);
		
		JButton create = new JButton("Create a survey");
		create.setBounds(400, 500, 200, 100);
		create.setFont(new Font("Times New Roman", Font.BOLD, 22));
		add(create);
		create.addActionListener(bh);
		
		JButton ct = new JButton("Take a survey");
		ct.setBounds(900, 500, 200, 100);
		ct.setFont(new Font("Times New Roman", Font.BOLD, 22));
		add(ct);
		ct.addActionListener(bh);
		
		
	}
	
	class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("Home"))
			{
				mc.show(sl, "First");
			
			}
			else if(command.equals("Create a survey"))
			{
				Take tk = new Take(sl, mc, username);
				sl.add(tk, "Take");
				mc.show(sl, "Take");
			}
		}
	}
		
		
}

class WrittenNow extends JPanel
{
	private SurveyHolder sl;
	private CardLayout ca;
	private JTextField firstQ;
	private JTextField secondQ;
	private JTextField thirdQ;
	private JTextField fourthQ;
	private JTextField fifthQ;
	private JTextField sixthQ;
	private JTextField seventhQ;
	private JTextField eightQ;
	private JTextField ninthQ;
	private JTextField tenthQ;
	private String userName;
	private String[] questions;
	private String sName;
	private String instructions;
	private int count;
	
	public WrittenNow(SurveyHolder sy, CardLayout ct, String name, String survey, String intro)
	{
		sl = sy;
		ca = ct;
		Color color = new Color(51, 204, 255);
		userName = name;
		questions = new String[10];
		sName = survey;
		instructions = intro;
		count = 0;
		
		setBackground(color);
		setLayout(null);
		
		ButtonHandler ba = new ButtonHandler();
		JButton home = new JButton("Home");
		home.setBounds(50, 20, 100, 50);
		add(home);
		home.addActionListener(ba);
		
		JButton back = new JButton("Back");
		back.setBounds(50, 725, 100, 50);
		add(back);
		back.addActionListener(ba);
		
		JButton next = new JButton("Next");
		next.setBounds(1300, 725, 100, 50);
		add(next);
		next.addActionListener(ba);
				
		JTextArea ja = new JTextArea("This panel has 10 empty boxes for you to enter questions in. If you want to ask 10 questions fill them all, then CLICK ENTER. If you want to ask less than 10 questions, leave some empty.");
		ja.setFont(new Font("Times New Roman", Font.BOLD, 25));
		ja.setBounds(250, 50, 1100, 60);
		ja.setBackground(color);
		ja.setLineWrap(true); 
		ja.setWrapStyleWord(true);
		ja.setEditable(false);
		add(ja);
		
		TextFieldHandler tfh = new TextFieldHandler();
		JLabel first = new JLabel("Question 1:");
		first.setFont(new Font("Times New Roman", Font.BOLD, 18));
		first.setBounds(60, 120, 200, 50);
		add(first);
		
		firstQ = new JTextField("");
		firstQ.setFont(new Font("Times New Roman", Font.BOLD, 17));
		firstQ.setBounds(60, 170, 600, 38);
		add(firstQ);
		firstQ.addActionListener(tfh);
		
		JLabel second = new JLabel("Question 2:");
		second.setFont(new Font("Times New Roman", Font.BOLD, 18));
		second.setBounds(60, 240, 200, 50);
		add(second);		
		
		secondQ = new JTextField("");
		secondQ.setFont(new Font("Times New Roman", Font.BOLD, 17));
		secondQ.setBounds(60, 290, 600, 38);
		add(secondQ);
		secondQ.addActionListener(tfh);

		
		JLabel third = new JLabel("Question 3:");
		third.setFont(new Font("Times New Roman", Font.BOLD, 18));
		third.setBounds(60, 360, 200, 50);
		add(third);
		
		thirdQ = new JTextField("");
		thirdQ.setFont(new Font("Times New Roman", Font.BOLD, 17));
		thirdQ.setBounds(60, 410, 600, 38);
		add(thirdQ);
		thirdQ.addActionListener(tfh);

		
		JLabel fourth = new JLabel("Question 4:");
		fourth.setFont(new Font("Times New Roman", Font.BOLD, 18));
		fourth.setBounds(60, 480, 200, 50);
		add(fourth);
		
		fourthQ = new JTextField("");
		fourthQ.setFont(new Font("Times New Roman", Font.BOLD, 17));
		fourthQ.setBounds(60, 530, 600, 38);
		add(fourthQ);
		fourthQ.addActionListener(tfh);
		
		JLabel fifth = new JLabel("Question 5:");
		fifth.setFont(new Font("Times New Roman", Font.BOLD, 18));
		fifth.setBounds(60, 600, 200, 50);
		add(fifth);
		
		fifthQ = new JTextField("");
		fifthQ.setFont(new Font("Times New Roman", Font.BOLD, 17));
		fifthQ.setBounds(60, 650, 600, 38);
		add(fifthQ);
		fifthQ.addActionListener(tfh);
		
		JLabel sixth = new JLabel("Question 6:");
		sixth.setFont(new Font("Times New Roman", Font.BOLD, 18));
		sixth.setBounds(800, 120, 200, 50);
		add(sixth);
		
		sixthQ = new JTextField("");
		sixthQ.setFont(new Font("Times New Roman", Font.BOLD, 17));
		sixthQ.setBounds(800, 170, 600, 38);
		add(sixthQ);
		sixthQ.addActionListener(tfh);
		
		JLabel seventh = new JLabel("Question 7:");
		seventh.setFont(new Font("Times New Roman", Font.BOLD, 18));
		seventh.setBounds(800, 240, 200, 50);
		add(seventh);
		
		seventhQ = new JTextField("");
		seventhQ.setFont(new Font("Times New Roman", Font.BOLD, 17));
		seventhQ.setBounds(800, 290, 600, 38);
		add(seventhQ);
		seventhQ.addActionListener(tfh);
		
		JLabel eight = new JLabel("Question 8:");
		eight.setFont(new Font("Times New Roman", Font.BOLD, 18));
		eight.setBounds(800, 360, 200, 50);
		add(eight);
		
		eightQ = new JTextField("");
		eightQ.setFont(new Font("Times New Roman", Font.BOLD, 17));
		eightQ.setBounds(800, 410, 600, 38);
		add(eightQ);
		eightQ.addActionListener(tfh);
		
		JLabel ninth = new JLabel("Question 9:");
		ninth.setFont(new Font("Times New Roman", Font.BOLD, 18));
		ninth.setBounds(800, 480, 200, 50);
		add(ninth);
		
		ninthQ = new JTextField("");
		ninthQ.setFont(new Font("Times New Roman", Font.BOLD, 17));
		ninthQ.setBounds(800, 530, 600, 38);
		add(ninthQ);
		ninthQ.addActionListener(tfh);
		
		JLabel tenth = new JLabel("Question 10:");
		tenth.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tenth.setBounds(800, 600, 200, 50);
		add(tenth);
		
		tenthQ = new JTextField("");
		tenthQ.setFont(new Font("Times New Roman", Font.BOLD, 17));
		tenthQ.setBounds(800, 650, 600, 38);
		add(tenthQ);
		tenthQ.addActionListener(tfh);
		
		
		
	}
	
	class TextFieldHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			File ioFile = new File(sName + ".txt");
			PrintWriter outFile = null;
			try
			{
				outFile = new PrintWriter(ioFile);
			}
			catch(IOException e)
			{
				e.printStackTrace();
				System.exit(1);
			}
			
			outFile.println(userName);
			outFile.println(sName);
			outFile.println(instructions);
			
			if(!firstQ.getText().equals(""))
			{
				questions[0] = firstQ.getText();
				System.out.print(questions[0]);
				count++;
				outFile.println(questions[0]);
			}
			else if(!secondQ.getText().equals(""))
			{
				questions[1] = secondQ.getText();
				count++;
				outFile.println(questions[1]);
			}
			else if(!thirdQ.getText().equals(""))
			{
				questions[2] = thirdQ.getText();
				count++;
				outFile.println(questions[2]);
			}
			else if(!fourthQ.getText().equals(""))
			{
				questions[3] = fourthQ.getText();
				count++;
				outFile.println(questions[3]);
			}
			else if(!fifthQ.getText().equals(""))
			{
				questions[4] = fifthQ.getText();
				count++;
				outFile.println(questions[4]);
			}
			else if(!sixthQ.getText().equals(""))
			{
				questions[5] = sixthQ.getText();
				count++;
				outFile.println(questions[5]);
			}
			else if(!seventhQ.getText().equals(""))
			{
				questions[6] = seventhQ.getText();
				count++;
				outFile.println(questions[6]);
			}
			else if(!eightQ.getText().equals(""))
			{
				questions[7] = eightQ.getText();
				count++;
				outFile.println(questions[7]);
			}
			else if(!ninthQ.getText().equals(""))
			{
				questions[8] = ninthQ.getText();
				count++;
				outFile.println(questions[8]);
			}
			else if(!tenthQ.getText().equals(""))
			{
				questions[9] = tenthQ.getText();
				count++;
				outFile.println(questions[9]);
			}
			
			outFile.close();
			
			
		}
	}
	
	/*
	public void printThis()
	{
		
		File ioFile = new File(sName + ".txt");
		PrintWriter outFile = null;
		try
		{
			outFile = new PrintWriter(ioFile);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		outFile.println(userName);
		outFile.println(sName);
		outFile.println(instructions);
		outFile.close();
		
		
		try(FileWriter fw = new FileWriter((sName + ".txt"), true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
			{
				for(int i = 0; i < questions.length; i++)
				{
					if(questions[i] != null)
					{
						out.println(questions[i]);
					}
				}
						
			} catch (IOException e) {
				
			}
		
	}
	*/
	
	class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("Home"))
			{
				ca.show(sl, "First");
			}
			else if(command.equals("Back"))
			{
				ca.show(sl, "Take");
			}
			else if(command.equals("Next"))
			{
	
			}
		}
	}
	
}

class Take extends JPanel
{
	private SurveyHolder sl;
	private CardLayout ct;
	private String name;
	private JTextField jd;
	private JTextField jf;
	private String sName;
	private String instructions;
	
	public Take(SurveyHolder sh, CardLayout ca, String username)
	{
		sl = sh;
		ct = ca;
		name = username;

		setBackground(Color.YELLOW);
		setLayout(null);
		
		ButtonHandler ba = new ButtonHandler();
		JButton home = new JButton("Home");
		home.setBounds(50, 20, 100, 50);
		add(home);
		home.addActionListener(ba);
		
		JButton back = new JButton("Back");
		back.setBounds(50, 725, 100, 50);
		add(back);
		back.addActionListener(ba);
		
		JButton next = new JButton("Next");
		next.setBounds(1300, 725, 100, 50);
		add(next);
		next.addActionListener(ba);
		
		TextFieldHandler th = new TextFieldHandler();
		JTextArea jt = new JTextArea("Add a name for your survey down below and then add an instruction for the user to understand how to answer questions in the survey.");
		jt.setBounds(300, 150, 900, 70);
		jt.setFont(new Font("Times New Roman", Font.BOLD, 30));
		jt.setBackground(Color.YELLOW);
		jt.setLineWrap(true); 
		jt.setWrapStyleWord(true);
		jt.setEditable(false);
		add(jt);
				
		jd = new JTextField("Enter name for survey here");
		jd.setFont(new Font("Times New Roman", Font.BOLD, 20));
		jd.setBounds(600, 300, 300, 50);
		add(jd);
		jd.addActionListener(th);
		
		jf = new JTextField("Enter instructions for survey here.");
		jf.setEditable(true);
		jf.setFont(new Font("Times New Roman", Font.BOLD, 25));
		jf.setBounds(400, 400, 700, 60);
		add(jf);
		jf.addActionListener(th);

		
	}
	class TextFieldHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			if(!jd.getText().equals("") && !jf.getText().equals(""))
			{
				sName = jd.getText();
				instructions = jf.getText();
				createFile();
			}

		}
	}
	
	public void createFile()
	{
		File file = new File(sName + ".txt");
		
		try {

		 // create a new file with name specified
		 // by the file object
		 boolean value = file.createNewFile();
		 if (value) {
			System.out.println("New Java File is created.");
		 }
		 else {
			System.out.println("The file already exists.");
		 }
		}
		catch(Exception e) {
		  e.getStackTrace();
		}
		
		
	}
			
	class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("Home"))
			{
				ct.show(sl, "First");
			}
			else if(command.equals("Back"))
			{
				ct.show(sl, "Survey");
			}
			else if(command.equals("Next"))
			{
				WrittenNow wn = new WrittenNow(sl, ct, name, sName, instructions);
				sl.add(wn, "Written");
				ct.show(sl, "Written");
			
			}
		}
	}
}
