package it.isa.progetto;
import java.sql.*;
import java.util.*;

public class Join {
    public static Connection JoinTables(Connection con) {
		  
		String query = "SELECT CLIENTE.CF, CLIENTE.NOME, CLIENTE.COGNOME, CORSO.NOME\n" +
					   "FROM CLIENTE, CORSO, SEGUE\n" +
					   "WHERE CLIENTE.CF = SEGUE.CF_CLIENTE and CORSO.CODICE = SEGUE.ID_CORSO";
		
		try {
	
			Statement stmt = con.createStatement();							
			System.out.println("QUERY DI JOIN: \n"+query);
			ResultSet rs = stmt.executeQuery(query);
		
			System.out.println("Risultato del Join:");
			while (rs.next()) {
				String cf = rs.getString(1);
				String name = rs.getString(2);
				String surname= rs.getString(3);
				String corso=rs.getString(4);
				System.out.println(cf + ", " + name+", "+surname+", "+corso);
				}
			rs.close();
			stmt.close();
		}
			
			//con.close();
		catch(SQLException ex) {
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}	
		return con;
	}
}

