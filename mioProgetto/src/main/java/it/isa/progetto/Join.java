package it.isa.progetto;
import java.sql.*;
import java.io.*;

public class Join {
    public static void JoinTables(String query) {
		  
		String url = "jdbc:db2://localhost:50000/palestra";

		Connection con;
		/*String query = "select SUPPLIERS.SUP_NAME, COFFEES.COF_NAME " +
		*			   "from COFFEES, SUPPLIERS " +
		*			   "where SUPPLIERS.SUP_NAME like 'Acme, Inc.' and " +
		*			   "SUPPLIERS.SUP_ID = COFFEES.SUP_ID";
		*/			   
		Statement stmt;
		String select;
        String from;
        String where;
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");

		} catch(java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
	
		try {
			con = DriverManager.getConnection(url,"db2inst1","Spillo_1998");
	
			stmt = con.createStatement();							
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try{
				System.out.println("Inserire contenuto SELECT: ");
				String s= br.readLine();
				select="SELECT "+ s;
				System.out.println("Inserire contenuto FROM: ");
				String f= br.readLine();
				from="FROM "+ f;
				System.out.println("Inserire contenuto WHERE: ");
				String w = br.readLine();
				where="WHERE "+ w;
				query = select+" "+from+" "+ where+";" ;
				System.out.println("QUERY RICHIESTA: "+query);
			}catch(Exception e) {
				System.out.println(e);
			}
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

