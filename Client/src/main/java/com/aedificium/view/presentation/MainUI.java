package com.aedificium.view.presentation;

import javax.swing.*;
import java.awt.event.*;

public class MainUI extends JFrame {

	private static final long serialVersionUID = -2254581829135234661L;
	
	//Create a JDesktopPane named theDesktop.
	private JDesktopPane theDesktop = new JDesktopPane();
	
	//Constructor for MainUI.
	public MainUI(){
		super("Aedificium");
		

		//Call theDesktop
		getContentPane().add(theDesktop);
		
		//Add JMenuBar named menuBar.
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Create JMenu item "File".
		JMenu fileMenu = new JMenu("File");
		
		//Create Add Book.
		JMenuItem addBookItem = new JMenuItem("Add Book");
		
		//Create Add Borrower.
		JMenuItem addBorrowerItem = new JMenuItem("Add Borrower");
		
		//Create Add Librarian.
		JMenuItem addLibrarianItem = new JMenuItem("Add Librarian");
		
		//Checkout item.
		JMenuItem checkoutItem = new JMenuItem("Checkout item");
		
		//Checkout item.
		JMenuItem checkInItem = new JMenuItem("Check in item");
		
		//Create Exit.
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('X');
		
		//ACTION LISTENERS FOR MENU.
		
		//Action Listener for exitItem.
		exitItem.addActionListener (
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			}
		);//End AL for exitItem.
		
		//Action Listener for addBookItem.
		addBookItem.addActionListener (
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddBookUI addBookUI = new AddBookUI("Add Book");
					theDesktop.add(addBookUI);
					addBookUI.setVisible(true);
				}
			}
		);//End AL for addBookItem.
		

		
		//Place menu items.
		fileMenu.add(addBorrowerItem);
		fileMenu.add(addBookItem);
		fileMenu.add(addLibrarianItem);
		fileMenu.add(checkoutItem);
		fileMenu.add(checkInItem);
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
			
		//Setup Window.
		this.setSize(400,1600);
		this.setVisible(true);
		pack();
	}//End of constructor.
	
	
	
	
	//main() method.
	public static void main(String[] args) {
		new MainUI();
	}//End of main() method.
	
	
	
	
	


}//End of the MainFrame.
