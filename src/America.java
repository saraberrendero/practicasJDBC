/*Se tiene la base de datos América, compuesta por las tablas Personas y Países.
Hacer un programa en Java que cree la tabla PersonasPaises que tendrá los
siguientes atributos:
Id, Nombre, Apellido, Edad, NombrePais y Tamaño. 
La información que va almacenar es la sacada de las otras dos tablas. Tras crear
dicha tabla, actualizarla sumando 1 a la edad de las personas de Costa Rica.
Finalmente sacar un listado con toda la información de la nueva tabla.
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class America {
	

	public static void main(String[] args) {
		Connection c = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/america", "root", "");
			
			Statement st = c.createStatement();
			st.execute("create table PersonasPaises (ID int NOT NULL AUTO_INCREMENT primary key, Nombre varchar(15), apellido varchar(15), edad TINYINT(4), nombrePais varchar(15), tamanio varchar(15));");
			st.close();
			
			Statement st2 = c.createStatement();
			ResultSet rs2 = st2.executeQuery("SELECT  pais.nombre, persona.nombre, apellido, edad, tamanio FROM pais, persona WHERE pais.id = persona.pais;");
			
			while (rs2.next()) {
				
				String nombrePais;
				String nombrePersona;
				String apellido;
				int edad;
				String tamaño;
				
				nombrePais = rs2.getString(1);
				nombrePersona = rs2.getString(2);
				apellido = rs2.getString(3);
				edad = Integer.parseInt(rs2.getString(4));
				tamaño = rs2.getString(5);
				//System.out.println(tamaño);
				
				Statement st3 = c.createStatement();
				st3.execute("insert into PersonasPaises (nombre, apellido, edad, nombrePais, tamanio) VALUES ('" + nombrePersona + "','" + apellido + "','" + edad + "','" + nombrePais + "','" + tamaño + "');");
				st3.close();
				
			}
			
			rs2.close();
			st2.close();
			
			
			
		Statement st5 = c.createStatement();
		st5.execute("update personasPaises set edad = edad + 1 where nombrePais='Costa Rica';");
		st5.close();
		
		
		Statement st4 = c.createStatement();
		ResultSet rs4 = st4.executeQuery("SELECT * from PersonasPaises;");
		

		while (rs4.next()) {
			
			int id;
			String nombrePersona;
			String apellido;
			int edad;
			String nombrePais;
			String tamaño;
			
			id = Integer.parseInt(rs4.getString(1)); //rs4.getInt(1)
			nombrePersona = rs4.getString(2);
			apellido = rs4.getString(3);
			edad = Integer.parseInt(rs4.getString(4));
			nombrePais = rs4.getString(5);
			tamaño = rs4.getString(6);
	
			System.out.println(id +"\t" + nombrePersona +"\t" + apellido +"\t" + edad +"\t" + nombrePais +"\t" + tamaño);
			
		}
		
		rs4.close();
		st4.close();
				
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}