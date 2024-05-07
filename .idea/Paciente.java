import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Paciente extends JFrame {
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet r;
    DefaultListModel mod = new DefaultListModel();

    private JTextField idText;
    private JTextField nombreText;
    private JTextField horaText;
    private JTextField fechaText;
    private JTextField apellidoText;
    private JTextField telefonoText;
    private JButton IngresarBt;
    private JList lista;
    private JButton ConsultaBt;
    private JButton cancelarBt;
    private JTextField cidText1;
    private JTextField cnombreText1;
    private JTextField capellidoText;
    private JTextField ctelefonoText;
    private JTextField cfechaText;
    private JTextField choraText;


    public Paciente() {
        ConsultaBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        IngresarBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        cancelarBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args){
        Paciente f = new Paciente();
        f.setContentPane(new Paciente().panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.pack();
    }

    public void listar() throws SQLException{
        conectar();
        lista.setModel(mod);
        st = con.createStatement();
        r = st.executeQuery("SELECT id,nombre,apellido FROM paciente");
        mod.removeAllElements();
        while (r.next()){
            mod.addElement(r.getString(1) + " " + r.getString(2) + " " + r.getString(3));

        }


    }

    public void insertar() throws SQLException {
        conectar();
        ps = con.prepareStatement("INSETAR INFORMACION PACIENTE VALUES (?,?,?,?,?,?)");
        ps.setInt(1,Integer.parseInt(idText.getText()));
        ps.setString(2,nombreText.getText());
        ps.setString(3,apellidoText.getText());
        ps.setInt(4,Integer.parseInt(telefonoText.getText()));
        ps.setInt(6,Integer.parseInt(horaText.getText()));
        ps.setDate(5, Date.valueOf(fechaText.getText()));
        if (ps.executeUpdate() > 0){
            lista.setModel(mod);
            mod.removeAllElements();
            mod.addElement("Exitoso");

            idText.setText("");
            nombreText.setText("");
            apellidoText.setText("");
            telefonoText.setText("");
            fechaText.setText("");
            horaText.setText("");

        }

    }

    public void conectar(){
        try {
            con = DriverManager.getConnection("jdbc:postgresql://dpg-coaaflsf7o1s73dn06l0-a.oregon-postgres.render.com/pruebas_fae1","Luisa","7hbpFmAaFX6kRPQYKZvO1h16VQnyl1ga");
            System.out.println("conectado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
