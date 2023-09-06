package c3g4e1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class C3g4E1 {

    public static void main(String[] args) {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/g4e1", "root", "");
                // String sql="INSERT INTO alumno( dni, apellido, nombre, nacimiento, estado) "
                //    + "VALUES (40699675,'Terán','Alberto','1997-09-14',1) ";
                // String sql2="INSERT INTO alumno( dni, apellido, nombre, nacimiento, estado) VALUES (46000123,'Terán','Manuel','2000-03-1',true),(44342312,'Terán','Alberto','1999-10-16',true) ";
                //String sql=" INSERT INTO materia(nombre, año, estado) VALUES ('Lengua',3,true),('Matematica',2,true),('Programacion',1,true)";
                //String sql="INSERT INTO inscripcion(idAlumno, idMateria, nota) VALUES (1,2,9),(1,1,10),(2,3,6),(2,2,9),(3,1,5),(3,3,7)";  
                //    PreparedStatement ps= conexion.prepareStatement(sql);
                // ps.executeUpdate();
                String sql = "SELECT * FROM `alumno` join inscripcion on (alumno.idalumno=inscripcion.idalumno) WHERE nota>7";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet resultado = ps.executeQuery();
                while (resultado.next()) {
                    System.out.print("dni:" + resultado.getInt("dni"));
                    System.out.print(" Apellido: " + resultado.getString("apellido"));
                    System.out.print(" Nombre:" + resultado.getString("nombre"));
                    System.out.print(" nacimiento: " + resultado.getDate("nacimiento"));
                    System.out.print(" estado:" + resultado.getBoolean("estado"));
                    System.out.println("");
                }
                String sql1 = "DELETE FROM inscripcion WHERE nota<6 ";
                PreparedStatement ps1 = conexion.prepareStatement(sql1);
                ps1.executeUpdate();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error de conexion" + ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "error cargando driver" + ex.getMessage());
        }
    }
}
