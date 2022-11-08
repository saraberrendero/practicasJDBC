import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTablas {

	public static void main(String[] args) {
		Connection c = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //cargar el driver en memoria
			 c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicios","root","");
			Statement st = c.createStatement();
			 st.execute("create table coches (nMat varchar(20) primary key, marca varchar(20), modelo varchar(20));");
			 Statement st2 = c.createStatement();
			 st2.executeUpdate("insert into coches VALUES ('1234DBB', 'SEAT', 'IBIZA');");	
			 st2.executeUpdate("insert into coches VALUES ('4567AGF', 'AUDI', 'C5');");
			 st2.executeUpdate("insert into coches VALUES ('5489MHB', 'FORD', 'FIESTA');");
			 ResultSet rs = st.executeQuery("SELECT marca from coches;");
			 while(rs.next()) {
					System.out.println(rs.getString(1));
				}
			 
			/* rs.next();
			 System.out.println(rs.getString(1));
			 rs.next();
			 System.out.println(rs.getString(1));
			 rs.next();
			 System.out.println(rs.getString(1));
			 */
			c.close();
			
			
			
		}	catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
}