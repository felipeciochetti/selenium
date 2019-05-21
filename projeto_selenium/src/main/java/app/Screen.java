package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Parameters;
import service.Emulator;

public class Screen {

	static Emulator emulator;
	
	public JTextArea logTextArea ;
	public JPasswordField passwordText ; 
	public JTextField userText ;

	public  void start() {    

		// Creating instance of JFrame
		JFrame frame = new JFrame("Emulator");
		// Setting the width and height of frame
		frame.pack();
		frame.setLocationRelativeTo(null);  
		frame.setSize(350, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JTabbedPane tabbedPane = new JTabbedPane();

		frame.add(tabbedPane);
		placeComponents(tabbedPane);
		frame.setVisible(true);


		emulator = new Emulator();
	}

	private  void placeComponents(JTabbedPane tabbedPane) {


		addTabelOne( tabbedPane);
		addTabelTwo( tabbedPane);





	}





	private  void addTabelTwo(JTabbedPane tabbedPane) {
		JPanel panel = new JPanel();    
		panel.setLayout(null);

		tabbedPane.addTab("Settings", panel);


		// Creating JLabel
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10,20,100,25);
		panel.add(userLabel);

		userText = new JTextField(20);
		userText.setText(Parameters.getInstance().getLogin());
		userText.setBounds(120,20,180,25);
		panel.add(userText);
		

		// Same process for password label and text field.
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10,50,100,25);
		panel.add(passwordLabel);


		 passwordText = new JPasswordField(20);
		passwordText.setText(Parameters.getInstance().getPassword());
		passwordText.setBounds(120,50,180,25);
		panel.add(passwordText);
		
		JLabel pathLabel = new JLabel("Path File");
		pathLabel.setBounds(10,80,100,25);
		panel.add(pathLabel);

		JLabel pathLabelTwo = new JLabel(Parameters.getInstance().getPath_xls());
		pathLabelTwo.setBounds(120,80,170,25);
		panel.add(pathLabelTwo);


		
	}

	private  void addTabelOne(JTabbedPane tabbedPane) {

		JPanel panel = new JPanel();    
		panel.setLayout(null);

		tabbedPane.addTab("Log", panel);

		// Creating login button
		JButton stepOneButton = new JButton("Step one");
		stepOneButton.setBounds(10, 10, 150, 25);
		panel.add(stepOneButton);
		stepOneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				step_One();
			}
		});

		JButton stepTwoButton = new JButton("Step two");
		stepTwoButton.setBounds(150, 10, 150, 25);
		panel.add(stepTwoButton);
		stepTwoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				step_Two();
			}
		});



		logTextArea = new JTextArea("");
		logTextArea.setBounds(10, 40, 300, 200);
		panel.add(logTextArea);




	}

	public  void step_One(){
		try{
			emulator.step_One(this);
		}catch(Exception e){
			Parameters.getInstance().setLog(e.getMessage());
		}
		
		logTextArea.setText(Parameters.getInstance().getLog());
		
	}
	public  void step_Two(){
		try{
			emulator.step_Two();
		}catch(Exception e){
			Parameters.getInstance().setLog(e.getMessage());
		}
		
		logTextArea.setText(Parameters.getInstance().getLog());
	}



}
