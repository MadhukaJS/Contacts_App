
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;


public class contacts {
    private JTextField contactNum;
    private JTextField contactName;
    private JTable contactTable;
    private JPanel contacts;
    private JButton saveButton;
    private JButton updateButton;
    private JButton deleteButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("contacts");
        frame.setContentPane(new contacts().contacts);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    //database connection
    Connection con;
    PreparedStatement pst;
    public void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/contacts","root","");
            System.out.println("connected successfully!");
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }

    }



    public contacts() {
        connect();
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
}
}
