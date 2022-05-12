package it.isa.progetto;
import java.sql.*;


public class Insert {
    public static void InsertRow(String query) {

        String url = "jdbc:db2://localhost:50000/palestra";
        Connection con;
        Statement stmt;

        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");

        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }

        try {
            con = DriverManager.getConnection(url,"db2inst1","Spillo_1998");

            stmt = con.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);							

            ResultSet uprs = stmt.executeQuery(query);
            uprs.moveToInsertRow();

            uprs.updateString(1, "VCTRMB99M537P701");
            uprs.updateString(2, "TEST3");
            uprs.updateString(3, "TEST3");
            uprs.updateDate(4,Date.valueOf("2000-08-03"));
            uprs.updateString(5,"F");
            uprs.updateString(6,"TEST3");
            uprs.updateInt(7,32);
            uprs.updateString(8, "TEST3");
            uprs.updateString(9, "PAL03");

            uprs.insertRow();
            uprs.beforeFirst();

            System.out.println("Table CLIENTE after insertion:");
            while (uprs.next()) {
                String id = uprs.getString(1);
                String name = uprs.getString(2);
                String surname = uprs.getString(3);
                Date date = uprs.getDate(4);
                String sesso = uprs.getString(5);
                String via = uprs.getString(6);
                int num = uprs.getInt(7);
                String paese = uprs.getString(8);
                String codice = uprs.getString(9);

                System.out.print(id + " " + name + " " + surname+" "+date +" "+ sesso +" ");
                System.out.println(via +" " + num + " "+paese+" "+codice);
                
            }
            uprs.close();
            stmt.close();
            con.close();

        } catch(SQLException ex) {
            System.err.println("-----SQLException-----");
			System.err.println("SQLState:  " + ex.getSQLState());
			System.err.println("Message:  " + ex.getMessage());
			System.err.println("Vendor:  " + ex.getErrorCode());
        }
    }
}
