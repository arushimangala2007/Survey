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

package welcome;

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
