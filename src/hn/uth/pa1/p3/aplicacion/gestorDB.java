package hn.uth.pa1.p3.aplicacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class gestorDB {
     public static Connection getConexion() {
        try {
            String usuario = "uth";
            String contrasenia = "uth";
            String url = "jdbc:derby://localhost:1527/uth";

            return DriverManager.getConnection(url, usuario, contrasenia);
        } catch (Exception e) {
            System.out.println("Error getConexion: " + e.toString());
        }
        return null;
    }

    public static void guardarPantalla_de_Catalogo(int precio,int TotalCompra,int TotalDeseos,String nombre,String marca,String descripcion) {
        try {
            //1.- Obtener la conexion
            Connection con = getConexion();

            //2.- SQL de insert
            String sql = "insert into uth.humanos "
                    + "(Precio,TOTALCOMPRA,TOTALDESEOS, NOMBRE, MARCA, DESCRIPCION)  "
                    + "values "
                    + "(?,?,?,?,?,?)";
            
            //3. Preparar el query
            PreparedStatement ps=con.prepareStatement(sql);
            
            //4. Asignar valores a los signos de interrogacion
            ps.setInt(1, precio);
            ps.setInt(2, TotalCompra);
            ps.setInt(3, TotalDeseos);
            ps.setString(4, nombre);
            ps.setString(5, marca);
            ps.setString(6, descripcion);
            
            //5. Ejecutar el query
            ps.execute();
            
            //6. Cerrar la conexion
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error guardar: "+e.toString());
        }
    }
}
