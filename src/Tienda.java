import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*3º.-Sea la base de datos Tienda, ya creada a través del script Tienda.sql, compuesta
de las tablas Artículos y Fabricante.
Hacer un programa enJava, que cree una tabla ArtFab que contendrá el nombre
del artículo, el nombre del fabricante, el precio y una nueva columna denominada
iva que será el 10% del precio de aquellos artículos que cuesten menos de 200 €,
el 8% del precio deaquellos artículos que cuesten menos de 500€, el 6% para
aquellos artículos que cuesten menos de 700€ y el resto se quedan como están.
Finalmente, sacar un listado de toda la información contenida en la nueva tabla.
 */
public class Tienda {

	public static void main(String[] args) {
		Connection c = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "");
			
			Statement st = c.createStatement();
			st.execute("create table ArtFab (NombreArticulo varchar(30), NombreFabricante varchar(30), precio INT(11), iva double, primary key(NombreArticulo,NombreFabricante));");
			
			st.close();
			
			Statement st2 = c.createStatement();
			ResultSet rs2 = st2.executeQuery("SELECT fabricantes.Nombre, articulos.nombre, precio FROM fabricantes, articulos WHERE fabricantes.CLFAB = articulos.CLFAB;");
			
			while (rs2.next()) {
				
				String fabricante;
				String articulo;
				int precio;
				double iva = 0;
				
				fabricante = rs2.getString(1);
				articulo = rs2.getString(2);
				precio = rs2.getInt(3);
				
				if(precio<200) {
					
					iva = precio * 0.1;
				}
				else if(precio>=200 && precio<=500) {
					
					iva = precio * 0.08;
				}
				else if(precio>=500 && precio<=700) {
					
					iva = precio * 0.06;
				}
				
				Statement st3 = c.createStatement();
				st3.execute("insert into ArtFab (NombreArticulo, NombreFabricante, precio, iva) VALUES ('" + articulo + "','" + fabricante + "','" + precio + "','" + iva + "');");
				st3.close();
				
			}
			
			rs2.close();
			st2.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
