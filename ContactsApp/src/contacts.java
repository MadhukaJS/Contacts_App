
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
    private JTextField idc;

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

        name=contactName.getText();
        number=contactNum.getText();

        try{
            pst=con.prepareStatement("insert into mycontacts(name,number)values(?,?)");
            pst.setString(1,name);
            pst.setString(2,number);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Contact saved successfully!");

            contactName.setText("");
            contactNum.setText("");
            contactName.requestFocus();
            table_view();

        }
        catch (SQLException ex) {
            ex.printStackTrace();

        }


        }
    });



//update button

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name,number,cid;

                name=contactName.getText();
                number=contactNum.getText();
                cid=idc.getText();

                try{
                    pst=con.prepareStatement("update mycontacts set name=?,number=? where cid=?");
                    pst.setString(1,name);
                    pst.setString(2,number);
                    pst.setString(3,cid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Contact updated successfully!");

                    contactName.setText("");
                    contactNum.setText("");
                    idc.setText("");
                    contactName.requestFocus();
                    table_view();

                }
                catch (SQLException ex) {
                    ex.printStackTrace();

                }

            }
        });


//delete button


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cid;

                cid=idc.getText();

                try{
                    pst=con.prepareStatement("delete from mycontacts where cid=?");
                    pst.setString(1,cid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Contact deleted successfully!");

                    contactName.setText("");
                    contactNum.setText("");
                    idc.setText("");
                    contactName.requestFocus();
                    table_view();


                }
                catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        });

        //crud working. tested.
    }
}
