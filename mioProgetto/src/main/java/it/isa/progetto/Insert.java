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

            uprs.updateString("CF", "VCTRMB99M537P889");
            uprs.updateString("NOME", "Ciao");
            uprs.updateString("COGNOME", "TEST");
            uprs.updateString("DATANASCITA","NULL");
            uprs.updateString("SESSO","F");
            uprs.updateString("VIA","Rigone");
            uprs.updateInt("NUMERO",32); //valore precedente 33
            uprs.updateString("PAESE", "TEST");
            uprs.updateString("CODICE", "PAL03");

            uprs.insertRow();
            uprs.beforeFirst();

            System.out.println("Table CLIENTE after insertion:");
            while (uprs.next()) {
                String name = uprs.getString("NOME");
                String id = uprs.getString("CF");
                String surname = uprs.getString("COGNOME");
                String date = uprs.getString("DATANASCITA");
                String sesso = uprs.getString("SALES");
                String via = uprs.getString("TOTAL");
                int num =uprs.getInt("NUMERO");
                String paese=uprs.getString("PAESE");
                String codice=uprs.getString("CODICE");

                System.out.print(id + " " + name + " " + surname+" "+date +" "+ sesso);
                System.out.println(via +" " + num + " "+paese+" "+codice);
                
            }
            uprs.close();
            stmt.close();
            con.close();

        } catch(SQLException ex) {
            System.err.print("SQLException: ");
            System.err.println(ex.getMessage());
        }
    }
}
