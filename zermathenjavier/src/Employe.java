import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Employe {
    private JPanel Main;
    private JTextField txtName;
    private JTextField txtSalario;
    private JTextField textMovil;
    private JButton guardarButton;
    private JTable table1;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JButton buscarButton;
    private JTextField textid;
    private JScrollPane table_1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Employe");
        frame.setContentPane(new Employe().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    Connection con;
    PreparedStatement pst;

    public void connect(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_db?serverTimezone=UTC","root","root123");
            System.out.println("CORRECTO");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();

        }

        }

        void table_load(){
            try{

            pst = con.prepareStatement(("select * from java_db.trabajadores"));
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            }





    public Employe() {
        connect();
        table_load();
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre,salario,movil;

                nombre = txtName.getText();
                salario = txtSalario.getText();
                movil = textMovil.getText();

                try {
                    pst = con.prepareStatement("insert into java_db.trabajadores(nombre,salario,movil) values(?,?,?) ");
                    pst.setString(1, nombre);
                    pst.setString(2, salario);
                    pst.setString(3, movil);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Datos guardados");
                    //table_load();
                    txtName.setText("");
                    txtSalario.setText("");
                    textMovil.setText("");
                    txtName.requestFocus();

                }

                catch (SQLException ex)
                {
                    ex.printStackTrace();

                }


            }
        });


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String empid = textid.getText();

                    pst = con.prepareStatement("select nombre,salario,movil from java_db.trabajadores where id=?");
                    pst.setString(1, empid);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next() == true) {
                        String name = rs.getString(1);
                        String salario = rs.getString(2);
                        String movil = rs.getString(3);

                        txtName.setText(name);
                        txtSalario.setText(salario);
                        textMovil.setText(movil);
                    } else {
                        txtName.setText("");
                        txtSalario.setText("");
                        textMovil.setText("");
                        JOptionPane.showMessageDialog(null, "Datos invalidos");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empid,nombre,salario,movil;

                nombre = txtName.getText();
                salario = txtSalario.getText();
                movil = textMovil.getText();
                empid = textid.getText();

                try {
                    pst = con.prepareStatement("update java_db.trabajadores set nombre = ?, salario = ?, movil = ? where id=? ");
                    pst.setString(1,nombre);
                    pst.setString(2,salario);
                    pst.setString(3,movil);
                    pst.setString(4,empid);


                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Se actualizo correctamente");
                    table_load();
                    txtName.setText("");
                    txtSalario.setText("");
                    textMovil.setText("");
                    textid.setText("");

                }
                catch (SQLException el)
                {
                el.printStackTrace();
                }
            }
        });


        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empid;

                empid =textid.getText();

                try {
                    pst = con.prepareStatement("delete from java_db.trabajadores where id = ?");

                    pst.setString(1,empid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Se ha eliminado correctamente");
                    table_load();
                    txtName.setText("");
                    txtSalario.setText("");
                    textMovil.setText("");
                    txtName.requestFocus();

                }
                catch (SQLException el)
                {
                    el.printStackTrace();
                }



            }
        });
    }

}
