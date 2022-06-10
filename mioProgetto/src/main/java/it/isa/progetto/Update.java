package it.isa.progetto;
import java.sql.*;

public class Update {
    public static void UpdateColumn(Connection con) {

		
		//Connection con=null;
		Statement stmt;

		PreparedStatement updateSales;

		String updateString = "update CLIENTE " +
						"set SESSO = ? where NOME = ?";

		//String updateStatement = "update COFFEES " +
		//		"set TOTAL = TOTAL + ? where COF_NAME = ?";

		String query = "select SESSO, NOME from CLIENTE";

		try {

			updateSales = con.prepareStatement(updateString);
			//updateTotal = con.prepareStatement(updateStatement);
			String [] sesso = {"M"};//{175, 150, 60, 155, 90, 18, 90};
			String [] nome = {"TEST"};//{"Alex", "Fabio","Nicole", "Rita",
								//"Sara","Tiziano","Victor"};
			int len = nome.length;
			con.setAutoCommit(false);
			for (int i = 0; i < len; i++) {
				updateSales.setString(1, sesso[i]);
				updateSales.setString(2, nome[i]);
				updateSales.executeUpdate();

				//updateTotal.setInt(1, salesForWeek[i]);
				//updateTotal.setString(2, coffees[i]);
				//updateTotal.executeUpdate();
				con.commit();
			}

			con.setAutoCommit(true);

			updateSales.close();
			//updateTotal.close();

			stmt = con.createStatement();
			System.out.println("Query che eseguiremo: \nUPDATE CLIENTE set SESSO = ? where NOME = ?;");
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Risultato della query:");
			while (rs.next()) {
				String n = rs.getString("NOME");
				String s = rs.getString("SESSO");
				//int t = rs.getInt("TOTAL");
				System.out.println(n + " " +  s + " ");
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
