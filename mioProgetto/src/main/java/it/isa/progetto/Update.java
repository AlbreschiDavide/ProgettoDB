package it.isa.progetto;
import java.sql.*;

public class Update {
    public static void update(String query) {

		String url = "jdbc:db2://localhost:50000/palestra";
		Connection con=null;
		Statement stmt;

		PreparedStatement updateSales;
		PreparedStatement updateTotal;

		String updateString = "update CLIENTE " +
						"set NUMERO = ? where NOME = ?";

		//String updateStatement = "update COFFEES " +
		//		"set TOTAL = TOTAL + ? where COF_NAME = ?";

		//String query = "select COF_NAME, SALES, TOTAL from COFFEES";

		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");

		} catch(java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {

			con = DriverManager.getConnection(url,"db2inst1","Spillo_1998");

			updateSales = con.prepareStatement(updateString);
			//updateTotal = con.prepareStatement(updateStatement);
			int [] num_civico = {175, 150, 60, 155, 90, 18, 90};
			String [] nome = {"Alex", "Fabio","Nicole", "Rita",
								"Sara","Tiziano","Victor"};
			int len = nome.length;
			con.setAutoCommit(false);
			for (int i = 0; i < len; i++) {
				updateSales.setInt(1, num_civico[i]);
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
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String c = rs.getString("NOME");
				int s = rs.getInt("NUMERO");
				//int t = rs.getInt("TOTAL");
				System.out.println(c + " " +  s + " ");
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
