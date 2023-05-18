
import net.proteanit.sql.DbUtils;

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
    private JLabel Cname;
    private JLabel Cnumber;

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
    //read on table
    public void table_view(){
        try{
            pst=con.prepareStatement("select*from mycontacts");
            ResultSet rs= pst.executeQuery();
            contactTable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException ex){
            ex.printStackTrace();


        }
    }


    public contacts() {
        connect();
        table_view();

        //save button
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        String name,number;

        name=Cname.getText();
        number=Cnumber.getText();

        try{
            pst=con.prepareStatement("insert into mycontacts(name,number)values(?,?)");
            pst.setString(1,name);
            pst.setString(2,number);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Contact saved successfully!");

            Cname.setText("");
            Cnumber.setText("");
            Cname.requestFocus();

        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);

        }


        }
    });





        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
