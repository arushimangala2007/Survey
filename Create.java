//Create.java
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

public class Create extends JPanel
{
	private SurveyHolder sl;
	private CardLayout ct;
	
	public Create(SurveyHolder se, CardLayout ca)
	{
		sl = se;
		ct = ca;
	}
	
	public static void main(String[] args)
	{
	}
	
	public void start()
	{	
		setBackground(Color.YELLOW);
		setLayout(null);
		
		JTextArea choose = new JTextArea("Choose the type of survey you want to create below. For each of them you will be able to ask 1-10 questions multiple choice or written.");
		choose.setBounds(300, 150, 900, 200);
		choose.setFont(new Font("Times New Roman", Font.BOLD, 37));
		choose.setBackground(Color.YELLOW);
		choose.setLineWrap(true); 
		choose.setWrapStyleWord(true);
		choose.setEditable(false);
		add(choose);
		
		ButtonHandler bh = new ButtonHandler();
		JButton home = new JButton("Home");
		home.setBounds(50, 20, 100, 50);
		add(home);
		home.addActionListener(bh);
		
		JButton multiple = new JButton("Multiple Choice Survey");
		multiple.setBounds(300, 500, 200, 100);
		multiple.setFont(new Font("Times New Roman", Font.BOLD, 22));
		add(multiple);
		multiple.addActionListener(bh);
		
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
		}
	}
}
