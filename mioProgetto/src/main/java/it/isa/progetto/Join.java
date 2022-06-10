package it.isa.progetto;
import java.sql.*;
import java.util.*;

public class Join {
    public static Connection JoinTables(Connection con) {
		  
		String query = "select CLIENTE.CF, CLIENTE.NOME, CLIENTE.COGNOME, CLIENTE.PAESE " +
					   "from CLIENTE, SEDE " +
					   "where SEDE.PAESE like 'Ferrara' and " +
					   "CLIENTE.CODICE_P = SEDE.CODICE";
		
		try {
	
			Statement stmt = con.createStatement();							
			System.out.println("QUERY DI JOIN: "+query);
			ResultSet rs = stmt.executeQuery(query);
		
			System.out.println("Risultato del Join:");
			while (rs.next()) {
				String cf = rs.getString(1);
				String name = rs.getString(2);
				String surname= rs.getString(3);
				String paese=rs.getString(4);
				System.out.println(cf + ", " + name+", "+surname+", "+paese);
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

