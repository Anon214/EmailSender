package email;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements ActionListener{ // implements ActionListener
	
	//global variables
	
	JButton button1 = new JButton();
	JButton button2 = new JButton();
	JTextArea textAreaBody = new JTextArea();
	JTextArea textAreaSub = new JTextArea();
	JTextField field1 = new JTextField();
    JPasswordField field2 = new JPasswordField();
    File file = new File(getTitle());
    String fileName = new String();
	
	MyFrame() {
		// TODO Auto-generated method stub
		
		//Dialogue boxes for warning and user name and pass input
		JOptionPane.showMessageDialog(this, "Please keep note that the Excel sheet has to be in the order of " +
				"First Name, Last Name, and Email.");
		
		field1 = new JTextField();
		field2 = new JPasswordField();
	        
	    Object [] fields = {
	        "Username: ", field1,
	        "Password: ", field2
	    };
	        
	    JOptionPane.showConfirmDialog(this, fields, "Email Sender", JOptionPane.OK_CANCEL_OPTION);
		
		//frame defaults
		JPanel panel = new JPanel();
		
		this.setTitle("Email Sender from Excel Sheet");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 800);
		this.setResizable(false);
		setLocationRelativeTo(null);
		
		panel.setLayout(null);
		
		//creates labels and text area
		JLabel lblTitle = new JLabel("Enter your email message: ");
		lblTitle.setBounds(160, 20, 600, 20);
		lblTitle.setFont(new Font("Comic Sans", Font.BOLD, 18));
		JLabel lblsubTitle = new JLabel("(note: put FIRSTNAME for first name and LASTNAME for last name)");
		lblsubTitle.setBounds(80, 40, 600, 20);
		lblsubTitle.setFont(new Font("Comic Sans", Font.PLAIN, 12));
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(27, 60, 600, 10);
		lblSubject.setFont(new Font("Comic Sans", Font.ITALIC, 12));
		JLabel lblBody = new JLabel("Body");
		lblBody.setBounds(27, 113, 600, 20);
		lblBody.setFont(new Font("Comic Sans", Font.ITALIC, 12));
		
		textAreaSub = new JTextArea();
		textAreaSub.setBounds(27, 80, 525, 30);
		textAreaSub.setFont(new Font("Comic Sans", Font.PLAIN, 14));
		
		textAreaBody = new JTextArea();
		textAreaBody.setBounds(27, 133, 525, 550);
		textAreaBody.setFont(new Font("Comic Sans", Font.PLAIN, 14));
		textAreaBody.setLineWrap(true);
		textAreaBody.setWrapStyleWord(true);
		
		//creates button
		button1 = new JButton("Select File");
		button1.setBounds(100, 705, 120, 30);
		button1.addActionListener(this);
		
		button2 = new JButton("Done");
		button2.setBounds(340, 705, 120, 30);
		button2.addActionListener(this);
		
		
		
		//add --------------------------------
		panel.add(lblTitle);
		panel.add(lblsubTitle);
		panel.add(lblSubject);
		panel.add(lblBody);
		panel.add(textAreaSub);
		panel.add(textAreaBody);
		panel.add(button1);
		panel.add(button2);
		//    --------------------------------
		this.add(panel);
		this.setVisible(true);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) { //make send function parameter with emailText
		// TODO Auto-generated method stub
		if (e.getSource() == button1) {
			JFileChooser fileChooser = new JFileChooser();
			int response = fileChooser.showOpenDialog(null);
			
			if (response == JFileChooser.APPROVE_OPTION) {
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				fileName = new String();
				fileName = file.toString();

			}

		}
		
		if (e.getSource() == button2) {
			if (textAreaBody.getText() == "{firstName}") {
				
			}
			try {
				JavaMail.send(textAreaSub.getText(), textAreaBody.getText(), field1.getText(), field2.getText(), fileName);
			} catch (AddressException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Message Sent.");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

		}
	}
	

}
