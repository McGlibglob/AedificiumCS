package com.aedificium.view.presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.aedificium.controller.Controller;
import com.aedificium.model.domain.Book;

public class AddBookUI extends JInternalFrame {

	private static final long serialVersionUID = -2925343194277160467L;
	
	String feedback = null;
	
	// Naming of widgets
	JLabel titleLbl = new JLabel("Title");
	JTextField titleFld = new JTextField(50);

	JLabel authorLbl = new JLabel("Author (Last, First Middle)");
	JTextField authorFld = new JTextField(25);

	JLabel yearPubLbl = new JLabel("Year Published");
	JTextField yearPubFld = new JTextField(4);

	JLabel isbnLbl = new JLabel("ISBN (No spaces/hyphons)");
	JTextField isbnFld = new JTextField(13);

	JLabel stickerIDLbl = new JLabel("Scan Book's RFID Sticker");
	JTextField stickerIDFld = new JTextField(25);

	JLabel libLdapLbl = new JLabel("Librarian's LDAP");
	JTextField libLdapFld = new JTextField(20);

	JButton addBookBtn = new JButton("Add Book");
	JLabel feedbackLbl = new JLabel(feedback);

	// Methods

	// Constructor with arguments
	public AddBookUI(String name) {
		super(name);

		// Create container.
		Container container = getContentPane();

		// Make container a GridLayout.
		container.setLayout(new GridLayout(7, 2));

		// Place widgets
		container.add(titleLbl);
		container.add(titleFld);

		container.add(authorLbl);
		container.add(authorFld);

		container.add(isbnLbl);
		container.add(isbnFld);

		container.add(yearPubLbl);
		container.add(yearPubFld);

		container.add(stickerIDLbl);
		container.add(stickerIDFld);

		container.add(libLdapLbl);
		container.add(libLdapFld);

		container.add(addBookBtn);

		addBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				// Local variables
				Book book = new Book();
				String title = titleFld.getText();
				String yearString = yearPubFld.getText();
				String author = authorFld.getText();
				String stickerID = stickerIDFld.getText();
				String libLdap = libLdapFld.getText();
				int year = Integer.parseInt(yearString);
				String isbn = isbnFld.getText();
				String action = "Add Book";
				boolean status;
				String bookWasAdded = "Book was Added";
				String bookWasNotAdded = "Book was not Added";

				book.setTitle(title);
				book.setYearPublished(year);
				book.setIsbn(isbn);
				book.setAuthor(author);
				book.setStickerID(stickerID);
				book.setCheckedOut(false);

				System.out.println(book.toString());
				Controller controller = new Controller();
				status = controller.performAction(book, action);
				
				
				

			}// End of actionPerformed

		});// End of addBookBtn.addActionListener.

		pack();
		setVisible(true);

	}// End of AddBookUI constructor.

}// End of AddBookUI class.
