import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/tienda";
        String user="root";
        String pass="0000";
        try{
            Connection conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Conectado correctamente!");
            ArrayList<Producto> productos = obtenerProductos(conn);

            for(Producto p : productos){
                System.out.println("ID: " + p.getId() + ", Nombre: " + p.getNombre() + ", Precio: " + p.getPrecio());
            }

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }


    public static void insertarProducto(Producto producto, Connection cn) throws SQLException {
        String sql ="INSERT INTO productos (nombre, precio, cantidad) VALUES (?,?,?)";
        PreparedStatement ps = cn.prepareStatement(sql);

        try(ps){
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getPrecio());
            ps.setInt(3, producto.getCantidad());

            int filas = ps.executeUpdate();

            if(filas>0){
                System.out.println("Producto insertado correctamente --> " + producto.getNombre());
            } else{
                System.out.println("No se insertó ningún producto!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modificarPrecioProducto(int idProducto, int nuevoPrecio, Connection cn) throws SQLException {
        String  sql ="UPDATE productos SET precio = ? WHERE id = ?";
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, nuevoPrecio);
            ps.setInt(2, idProducto);

            int filas = ps.executeUpdate();
            if(filas>0){
                System.out.println("Producto modificado correctamente --> " + idProducto);
            } else {
                System.out.println("No se encontró ningún producto con el ID: " + idProducto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Producto> obtenerProductos(Connection cn) throws SQLException {
        String sql ="SELECT * FROM productos";
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<Producto> lista = new ArrayList<>();
        while(rs.next()){
            int id =  rs.getInt("id");
            String nombre = rs.getString("nombre");
            int precio = rs.getInt("precio");
            int cantidad = rs.getInt("cantidad");

            Producto p = new Producto(id,nombre,precio,cantidad);
            lista.add(p);
        }
        return lista;
    }
}
