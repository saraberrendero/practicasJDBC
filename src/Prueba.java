import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Prueba {

	public static void main(String[] args) {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //cargar el driver en memoria
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");
			Statement st = c.createStatement();
			
			ResultSet rs = st.executeQuery("select * from user");
			while(rs.next()) {
				System.out.println(rs.getString(1)+ " " + rs.getString("User"));
			}
			c.close();
		}	catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}