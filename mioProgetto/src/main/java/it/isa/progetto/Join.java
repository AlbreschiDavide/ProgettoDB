package Progetto.ProgettoDB.mioProgetto.src.main.java.it.isa.progetto;
import java.sql.*;

public class Join {
    public static void join(String query) {
		  
		String url = "jdbc:db2://localhost:50000/palestra";

		Connection con;
		/*String query = "select SUPPLIERS.SUP_NAME, COFFEES.COF_NAME " +
		*			   "from COFFEES, SUPPLIERS " +
		*			   "where SUPPLIERS.SUP_NAME like 'Acme, Inc.' and " +
		*			   "SUPPLIERS.SUP_ID = COFFEES.SUP_ID";
		*/			   
		Statement stmt;
	
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");

		} catch(java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
	
		try {
			con = DriverManager.getConnection(url,"db2inst1","Spillo_1998");
	
			stmt = con.createStatement();							
	
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Join tra le tabelle richieste:");
			while (rs.next()) {
				String supName = rs.getString(1);
				String cofName = rs.getString(2);
				System.out.println("    " + supName + ", " + cofName);
			}

			stmt.close();
			con.close();

		} catch(SQLException ex) {
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}	
	}
}

