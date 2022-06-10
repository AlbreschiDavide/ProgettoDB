package it.isa.progetto;
import java.sql.*;

public class Update {
    public static void UpdateColumn(Connection con) {

		//Connection con=null;
		Statement stmt;
		PreparedStatement updateStipendio;
		
		String query = "select CF, NOME, COGNOME, STIPENDIO from PERSONALE";
		
		String updateString = "update PERSONALE " +
						"set STIPENDIO = ? where NOME = ?";
		try {
			updateStipendio = con.prepareStatement(updateString);

			Integer [] stipendio = {1350, 1200, 1250, 1200, 1100, 1300};
			String [] nome = {"Anna", "Cristian","Gianfranco", "Sara","Mario","Elena"};
			
			int len = nome.length;
			con.setAutoCommit(false);
			for (int i = 0; i < len; i++) {
				updateStipendio.setInt(1, stipendio[i]);
				updateStipendio.setString(2, nome[i]);
				updateStipendio.executeUpdate();

				con.commit();
			}
			con.setAutoCommit(true);

			updateStipendio.close();

			stmt = con.createStatement();
			//System.out.println("Query che eseguiremo: \n UPDATE PERSONALE set STIPENDIO = ? where NOME = ?");
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Risultato della query:");
			while (rs.next()) {
				String cf=rs.getString("CF");
				String n = rs.getString("NOME");
				String c=rs.getString("COGNOME");
				String s = rs.getString("STIPENDIO");
				System.out.println(cf +" "+ n +" "+ c +" "+ s +" ");
			}
			stmt.close();
			con.close();

		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back");
					con.rollback();
				} catch(SQLException excep) {
					System.err.print("SQLException: ");
					System.err.println(excep.getMessage());
				}
			}
		}
	}
}
