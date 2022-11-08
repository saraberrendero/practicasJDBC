/*1º.-Crear la base de datos Instituto, cuyas tablas y valores se encuentran en el fichero
instituto.sql.
Una vez creada la base de datos hacer un programa en Java que cree una nueva
tabla llamada NotasFinales que tendrá la siguiente estructura:
NotasFinales(Mat,Cod,NotaMedia);
Y cuyos valores se sacaran de la tabla Notas.
Por último se imprimirá un listado de todos los alumnos con el siguiente formato:
Nombre Alumno  Nombre Asignatura  Nota 1 Nota 2 Nota 3  NotaMedia*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Instituto {

	public static void main(String[] args) {
		Connection c = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");
			
			Statement st = c.createStatement();
			st.execute("create table notasFinales (Mat varchar(20), Cod varchar(20), notaMedia varchar(20), primary key(Mat,cod));");
			st.close();
			
			//desc notasfinales : para ver si la clave primaria esta bien en sql
			
			Statement st2 = c.createStatement();
			ResultSet rs2 = st2.executeQuery("SELECT * from notas;");
			
			//select mat, cod,(nota1+nota2+nota3)/3 from notas; te hace la nota media directamente en sql mediante una consulta.
			
			while (rs2.next()) {
				double notaMedia;
				double nota1 = 0;
				double nota2;
				double nota3;
				String mat;
				String cod;
				
				/*
				 * System.out.println(rs.getString(3)); System.out.println(rs.getString(4));
				 * System.out.println(rs.getString(5));
				 * 
				 */

				nota1 = Integer.parseInt(rs2.getString(3));
				//System.out.println(nota1);

				nota2 = Integer.parseInt(rs2.getString(4));
				//System.out.println(nota2);

				nota3 = Integer.parseInt(rs2.getString(5));
				//System.out.println(nota3);

				notaMedia = (nota1 + nota2 + nota3) / 3;
				//System.out.println("la nota media es: " + notaMedia);

				mat = rs2.getString(1);
				//System.out.println(mat);

				cod = rs2.getString(2);
				//System.out.println(cod);

				Statement st3 = c.createStatement();
				st3.execute("insert into notasFinales (Mat, Cod, notaMedia) VALUES ('" + mat + "', '" + cod + "','" + notaMedia + "');");
				st3.close();
				
			}
			
			rs2.close();
			st2.close();
			
			Statement st4 = c.createStatement();
			ResultSet rs4 = st4.executeQuery("SELECT apel_nom, nombre, nota1, nota2, nota3, notaMedia FROM alumnos, asignaturas, notas, notasfinales WHERE alumnos.mat = notas.mat AND alumnos.mat = notasfinales.mat AND asignaturas.cod = notas.cod AND asignaturas.cod = notasfinales.cod;");
			
			while (rs4.next()) {
				String nombrealumno;
				String asignatura;
				double nota1;
				double nota2;
				double nota3;
				double notamedia;

				nombrealumno = rs4.getString(1);
				asignatura = rs4.getString(2);
				nota1 = Integer.parseInt(rs4.getString(3));
				nota2 = Integer.parseInt(rs4.getString(4));
				nota3 = Integer.parseInt(rs4.getString(5));
				notamedia = Double.parseDouble(rs4.getString(6)); //IMPORTANTE QUE SEA DOUBLE
				System.out.println(nombrealumno +"\t" + asignatura +"\t" + nota1 +"\t" + nota2 +"\t" + nota3 +"\t" + notamedia);
			}
			st4.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
