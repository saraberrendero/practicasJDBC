import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
Cada registro de la agenda deberá contener, los siguientes datos: Nombre, Apellidos, Nº de Teléfono, E-mail. 

El programa deberá permitir las siguientes operaciones, mediante un menú.
 
a) Leer los datos de un nuevo registro (dar de alta), almacenándolo.)

b) Buscar una persona de la agenda leyendo de teclado su nombre y apellidos. Se 
visualizará el resto de los datos. 

c) Modificar el teléfono o e_mail, de una persona que se pedirá por teclado.  

d) Eliminar una persona de la agenda telefónica dando su nombre y apellidos.

e ) Deberá existir un contador de personas añadidas, consultadas, modificadas y borradas. 
Finalmente, cuando se salga del menú, se escribirá el número de contactos añadidas, el número      de contactos consultadas, el número de contactos modificados y el número de contactos  eliminados.
*/
public class Agenda {

	public static void main(String[] args) {
		
		int contadorAlta = 0;
		int contadorBuscar = 0;
		int contadorModificar = 0;
		int contadorBorrar = 0;
		
		Scanner sc = new Scanner(System.in);
		
		int opcion;
		
		Connection c = null;
		
		
		do {
			System.out.println("1.ALTA");
			System.out.println("2.BUSCAR");
			System.out.println("3.MODIFICAR");
			System.out.println("4.ELIMINAR");
			System.out.println("5.SALIR");
			opcion = sc.nextInt();
			
			if(opcion == 1) {
				
				String nombre;
				String apellidos;
				int tlf;
				String email;
				
				
				System.out.println("ALTA");
				
				System.out.println("introduce nombre");
				nombre = sc.next();
				System.out.println("introduce apellidos");
				apellidos = sc.next();
				System.out.println("introduce telefono");
				tlf = sc.nextInt();
				System.out.println("introduce email");
				email = sc.next();
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					c = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "");
					
					Statement st = c.createStatement();
					st.execute("insert into usuarios (nombre, apellidos, telefono, email) VALUES ('" + nombre + "','" + apellidos + "','" + tlf + "','" + email + "');");
					contadorAlta++;
					st.close();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if(opcion == 2) {
				
				String nombre;
				String apellidos;
				System.out.println("BUSCAR");
				System.out.println("introduzca nombre a buscar");
				nombre = sc.next();
				System.out.println("introduzca apellidos a buscar");
				apellidos = sc.next();
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					c = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "");
					
					Statement st2 = c.createStatement();
					ResultSet rs2= st2.executeQuery("select * from usuarios u where u.nombre = '"+nombre+"' and u.apellidos = '"+apellidos+"';");
					contadorBuscar++;
					
					while(rs2.next()) {
						
						String textonombre;
						String textoapellidos;
						int textotlf;
						String textoemail;
						
						textonombre = rs2.getString(1);
						textoapellidos = rs2.getString(2);
						textotlf = Integer.parseInt(rs2.getString(3));
						textoemail = rs2.getString(4);
						
						System.out.println(textonombre + " " + textoapellidos + " " + textotlf + " " + textoemail);
						
					}
					st2.close();
					rs2.close();				
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(opcion == 3) {
			System.out.println("MODIFICAR");	
			
			String nombre;
			String apellidos;
			String email;
			String respuesta;
			int telefono;
			System.out.println("Escriba el nombre de la persona que quiera modificar");
			nombre = sc.next();
			System.out.println("Escriba los apellidos de la persona que quiera modificar");
			apellidos = sc.next();
			System.out.println("¿Que desea modificar el email(E) o el telefono(T)?");
			respuesta = sc.next();
			
			if(respuesta.equalsIgnoreCase("e")) {
				System.out.println("introduzca nuevo email");
				email = sc.next();
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					c = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "");
					
					Statement st = c.createStatement();
					st.execute("UPDATE usuarios set email= '"+email+"' where nombre= '"+nombre+"' AND apellidos ='"+apellidos+"';");
					contadorModificar++;
					st.close();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			else if(respuesta.equalsIgnoreCase("t")) {
				System.out.println("introduzca nuevo telefono");
				telefono = sc.nextInt();
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					c = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "");
					Statement st = c.createStatement();
					st.execute("UPDATE usuarios set telefono= '"+telefono+"' where nombre= '"+nombre+"' AND apellidos ='"+apellidos+"';");
					contadorModificar++;
					st.close();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			}
			
			if(opcion == 4) {
			System.out.println("ELIMINAR");
			
			String nombre;
			String apellidos;
			
			
			System.out.println("introduce nombre de la persona que quiera eliminar");
			nombre = sc.next();
			System.out.println("introduce apellidos de la persona que quiera eliminar");
			apellidos = sc.next();
		
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				c = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "");
				
				Statement st = c.createStatement();
				st.execute("DELETE FROM Usuarios WHERE nombre = '"+nombre+"' AND apellidos = '"+apellidos+"';");
				contadorBorrar++;
				st.close();
				System.out.println(nombre + " ha sido eliminado/a");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			if(opcion == 5) {
			System.out.println("SALIR");
			System.out.println("numero de usuarios dados de alta: "+contadorAlta);
			System.out.println("numero de usuarios buscados: "+contadorBuscar);
			System.out.println("numero de usuarios modificados: "+contadorModificar);
			System.out.println("numero de usuarios borrados: "+contadorBorrar);
			}
		}
		
		while(opcion!=5);
		

	}

}