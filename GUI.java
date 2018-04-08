import java.awt.*;  
import java.awt.event.*;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.swing.*;  

public class GUI extends JFrame implements ActionListener{
	JPanel cards;
	JTextArea receipt = new JTextArea(50, 50);
	JButton nextScreen = new JButton("Make appointment");
	JButton searchBarber = new JButton("See open times");
	JButton newOrder = new JButton("Go Back");
	JButton findFree = new JButton("Search");
	JButton restart = new JButton("Go Back");
	JTextField EnterName = new JTextField(10);
	JTextArea layout = new JTextArea(50,50);
	
	// The possible amount of appointments per day
	public int ACount = 1;
	public int CCount = 10;
	public int JCount = 19;
	public int ECount = 28;
	
	
	JLabel findBarber = new JLabel("Find avalible appointments for the barber of your choice");
	JTextField search = new JTextField(10);
	String[] names = {
			"Al", "Charles", "Johnny", "Elizabeth"
	};
	
	JComboBox BarberNames = new JComboBox(names);
	JComboBox BarberNames2 = new JComboBox(names);
	
	String[] times = {
			"10:00 AM", "11:00 AM",
			"12:00 PM", "1:00 PM",
			"2:00 PM", "3:00 PM",
			"4:00 PM", "5:00 PM",
			"6:00 PM"
	};
	JComboBox Times = new JComboBox(times);
	
	
	public GUI(){
		initUI();
	}
	
	public void showSchedule (String S) throws SQLException, ClassNotFoundException{
		int temp = 0; String customerName = "";
		String appointTime = "";
		
		try {
			// Load the JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			// Establish a connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test");
			System.out.println("Database connected");
			Statement statement = connection.createStatement();
			
			// Deciding who to show the schedule for
			if (S.equals("Al")){
				temp = ACount;
			}
			else if (S.equals("Charles")){
				temp = CCount;
			}
			else if (S.equals("Johnny")){
				temp = JCount;
			}
			else {
				temp = ECount;
			}
			

			String sql = "select * from barbers where fname = ?";
			
			ResultSet resultSet = statement.executeQuery("select * from barbers where fname = 'Al';");
			while (resultSet.next()){	   
				customerName = resultSet.getString(2);
				appointTime = resultSet.getString(4);
				
				layout.append(customerName  + " " + appointTime);
				}
			}
			
			catch (SQLException e){
				System.out.println("Error: Unable to link SQL");
			}
			
			catch (ClassNotFoundException e){
				System.out.println("Error: Unable to find Class");
			}
		}
	
	public void makeAppointment(String B, String C, String T) throws  SQLException, ClassNotFoundException{
		int temp = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			// Establish a connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test");
			System.out.println("Database connected");
			// Create a statement
			Statement statement = connection.createStatement();
		
		//Deciding who to schedule the appointment for and when
		if (B.equals("Al")){
			temp = ACount;
		}
		else if (B.equals("Charles")){
			temp = CCount;
		}
		else if (B.equals("Johnny")){
			temp = JCount;
		}
		else {
			temp = ECount;
		}
		System.out.println("update barbers set cust_name = '" + C + "'" + ", appt_time = '" + T + "'" + 
				" where fname = '" + B + "'" + " and appt_number = " + temp + ";");
		int n = statement.executeUpdate("update barbers set cust_name = '" + C + "'" + ", appt_time = '" + T + "'" + 
				" where fname = '" + B + "'" + " and appt_number = " + temp + ";");
		
		if (B.equals("Al")){
			ACount++;
		}
		else if (B.equals("Chales")){
			CCount++;
		}
		else if (B.equals("Johnny")){
			JCount++;
		}
		else {
			ECount++;
		}
		
		System.out.println("Appointment made!");
		}
		
		catch (SQLException e){
			System.out.println("Error: Unable to link SQL");
		}
		
		catch (ClassNotFoundException e){
			System.out.println("Error: Unable to find Class");
		}
	}

	
	public final void initUI(){
		JPanel card1 = new JPanel(new FlowLayout());
        JPanel card2 = new JPanel(new FlowLayout());
        JPanel card3 = new JPanel(new FlowLayout());
        
        cards = new JPanel(new CardLayout());
        
        Box h1 = Box.createHorizontalBox();
        Box h2 = Box.createHorizontalBox();
        Box h3 = Box.createHorizontalBox();
        Box v1 = Box.createVerticalBox();
        
        JLabel display1 = new JLabel("Choose a barber");
        JLabel display2 = new JLabel("Choose a time");
        JLabel CustName = new JLabel("Customer Name");
        JLabel display1v1 = new JLabel("Choose a barber");
        
        h1.add(h1.createHorizontalStrut(15));
        h1.add(display1);
        h1.add(h1.createHorizontalStrut(5));
        h1.add(BarberNames);
        
        h2.add(display2);
        h2.add(h2.createHorizontalStrut(5));
        h2.add(Times);
        h2.add(h2.createHorizontalStrut(15));
        
        h3.add(CustName);
        h3.add(h3.createHorizontalStrut(5));
        h3.add(EnterName);
        h3.add(h3.createHorizontalStrut(5));
        h3.add(nextScreen);
        h3.add(h3.createHorizontalStrut(5));
        h3.add(searchBarber);
        
        
        card1.add(h1);
        card1.add(h2);
        card1.add(h3);
        
        card2.add(newOrder);
        card2.add(receipt);
        
        card3.add(display1v1);
        card3.add(BarberNames2);
        card3.add(findFree);
        card3.add(restart);
        card3.add(layout);
       
        newOrder.addActionListener(this);
        nextScreen.addActionListener(this);
        searchBarber.addActionListener(this);
        findFree.addActionListener(this);
        restart.addActionListener(this);
        
        cards.add(card1, "Screen 1");
        cards.add(card2, "Screen 2");
        cards.add(card3, "Screen 3");
        
        getContentPane().add(cards);
        
		BarberNames.setEditable(false);
		BarberNames2.setEditable(false);
		Times.setEditable(false);
		receipt.setEditable(false);
		layout.setEditable(false);
		
		setSize(600,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("Make appointment")){
			CardLayout cardLayout = (CardLayout) cards.getLayout();
			cardLayout.show(cards, "Screen 2");
			
			receipt.append("Cust Name: " + EnterName.getText() + "\n\n");
			receipt.append("Barber : " +  BarberNames.getSelectedItem().toString() + "\n\n");
			receipt.append("Time : " +  Times.getSelectedItem().toString());	
			
			try{
			makeAppointment(BarberNames.getSelectedItem().toString(), EnterName.getText(), Times.getSelectedItem().toString());
			}
			catch (SQLException s){
				System.out.println("Error: Unable to link SQL");
			}
			
			catch (ClassNotFoundException s){
				System.out.println("Error: Unable to find Class");
			}
			
		}
		
		if (e.getActionCommand().equals("Go Back")){
			CardLayout cardLayout = (CardLayout) cards.getLayout();
			cardLayout.show(cards, "Screen 1");
			
			receipt.setText("");
			layout.setText("");
			EnterName.setText("");
		}
		
		if(e.getActionCommand().equals("See open times")){
			CardLayout cardLayout = (CardLayout) cards.getLayout();
			cardLayout.show(cards, "Screen 3");
		}
		
		if(e.getActionCommand().equals("Search")){
			try{
				showSchedule(BarberNames.getSelectedItem().toString());
			}
			catch (SQLException s){
				System.out.println("Error: Unable to link SQL");
			}	
			catch (ClassNotFoundException s){
				System.out.println("Error: Unable to find Class");
			}
		}
	}

	
	public static void main(String[] args){
		GUI gui = new GUI();
		gui.setVisible(true);
	}
}