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
		SurveyHolder so = new SurveyHolder();
		so.setIt();
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
