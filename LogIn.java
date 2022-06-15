//LogIn.java

//all the imports 
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;	
import javax.swing.JPanel;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer; 

import java.security.NoSuchAlgorithmException;  
import java.security.MessageDigest; 

public class LogIn extends JPanel
{
	private String nameenter;
	private SurveyHolder sd;
	private CardLayout ct;
	private JTextField jtf;
	public String username;
	
	public LogIn(SurveyHolder sr, CardLayout ca)
	{
		sd = sr;
		ct = ca;
	}
	
	public static void main(String[] args)
	{
	}
	
	public void checkIt()
	{
		setBackground(Color.YELLOW);
		setLayout(null);
			
		JTextArea jl = new JTextArea("Please enter your username down below");
		jl.setFont(new Font("Times New Roman", Font.BOLD, 42));
		jl.setBounds(350, 90, 900, 250);
		jl.setBackground(Color.YELLOW);
		jl.setLineWrap(true); 
		jl.setWrapStyleWord(true);
		jl.setEditable(false);
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
			
			setBackground(Color.YELLOW);
			setLayout(null);
			
			JTextArea jl = new JTextArea("Welcome " + username + "! Please enter your password down below");
			jl.setFont(new Font("Times New Roman", Font.BOLD, 42));
			jl.setBounds(350, 90, 900, 250);
			jl.setBackground(Color.YELLOW);
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
				else if(command.equals("Next") && !jth.equals("") && correct == true)
				{
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
				setBackground(Color.YELLOW);
				g.drawString("Password is incorrect. Please try again", 600, 400);
			}
		}	
}		

