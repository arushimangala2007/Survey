//SignIn.java

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

public class Register extends JPanel
{
	public SurveyHolder su;
	public CardLayout cl;
	public String username;
	public JTextField jtf;
	public JTextField jth;
			
		public Register(SurveyHolder sh, CardLayout card)
		{
			su = sh;
			cl = card;
		}
		
		public static void main(String[] args)
	{
	}
		
		public void paintIt()
		{
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
					cl.show(su, "First");
				}
				else if(command.equals("Next") && !username.equals("") && !username.equals("Enter USERNAME here then click enter"))
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

