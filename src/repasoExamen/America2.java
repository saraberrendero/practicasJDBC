package repasoExamen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class America2 {

	public static void main(String[] args) {
		
		/*Se tiene la base de datos América, compuesta por las tablas Personas y Países.
		Hacer un programa en Java que cree la tabla PersonasPaises que tendrá los
		siguientes atributos:
		Id, Nombre, Apellido, Edad, NombrePais y Tamaño. 
		La información que va almacenar es la sacada de las otras dos tablas. Tras crear
		dicha tabla, actualizarla sumando 1 a la edad de las personas de Costa Rica.
		Finalmente sacar un listado con toda la información de la nueva tabla.
		*/
		
		Scanner sc = new Scanner(System.in);
		int opcion;
		
		do {
			System.out.println("1-CREAR TABLA");
			System.out.println("2-SACAR DATOS y METERLOS EN LA TABLA");
			System.out.println("3-ACTUALIZAR TABLA");
			opcion = sc.nextInt();
			
			if(opcion == 1) {
				Connection c = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					c = DriverManager.getConnection("jdbc:mysql://localhost:3306/america2","root","@caxcada18@");
					
					Statement st = c.createStatement();
					
					st.execute("CREATE TABLE PersonasPaises(id INT AUTO_INCREMENT, nombre VARCHAR(15), apellidos VARCHAR (15), edad TINYINT, nombrePais VARCHAR(15), tamanio VARCHAR(15), primary key(id));");
					st.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if (opcion == 2) {
				Connection c = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					c = DriverManager.getConnection("jdbc:mysql://localhost:3306/america2","root","@caxcada18@");
					
					Statement st = c.createStatement();
					ResultSet rs = st.executeQuery("SELECT persona.nombre, persona.apellido, persona.edad, pais.nombre, pais.tamanio FROM persona, pais WHERE pais.id = persona.pais;");
					
					while (rs.next()) {
						String nombre;
						String apellido;
						int edad;
						String nombrepais;
						String tamaño;
						
						nombre = rs.getString(1);
						apellido = rs.getString(2);
						edad = Integer.parseInt(rs.getString(3));
						nombrepais = rs.getString(4);
						tamaño = rs.getString(5);
						
						System.out.println(nombre + " " + apellido + " " + edad + " " + nombrepais + " " + tamaño);
						
						Statement st2 = c.createStatement();
						st2.execute("INSERT INTO personasPaises (nombre, apellidos, edad, nombrePais, tamanio) VALUES ('"+nombre+"','"+apellido+"','"+edad+"','"+nombrepais+"','"+tamaño+"');");
						
					}
					
					st.close();
					rs.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if (opcion == 3) {
				Connection c = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					c = DriverManager.getConnection("jdbc:mysql://localhost:3306/america2","root","@caxcada18@");
					Statement st = c.createStatement();
					st.execute("UPDATE personasPaises SET edad = edad + 1, nombre = 'borrico' WHERE nombrepais = 'Costa Rica';");
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		while(opcion!=5);
		

	}

}
