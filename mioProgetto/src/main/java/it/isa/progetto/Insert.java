package it.isa.progetto;
import java.sql.*;
import java.util.Scanner;


public class Insert {
    public static Connection InsertRow(Connection con) {

        Statement stmt;

        try {
            stmt = con.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);	
            						
            String query= "SELECT * FROM CLIENTE;";
            int concurrency= stmt.getResultSetConcurrency();
            ResultSet uprs = stmt.executeQuery(query);
            System.out.println(concurrency);
            uprs.moveToInsertRow();
           
            uprs.updateString(1, "TESTTESTTESTTEST");
            uprs.updateString(2, "TEST");
            uprs.updateString(3, "TEST");
            uprs.updateDate(4,Date.valueOf("2000-08-04"));
            uprs.updateString(5,"F");
            uprs.updateString(6,"TEST");
            uprs.updateInt(7,32);
            uprs.updateString(8, "TEST");
            uprs.updateString(9, "PAL03");
                
            uprs.insertRow(); //inserisco la riga

            con.commit(); //faccio il commit

            uprs.close(); //chiudo il ResultSet 
            uprs = stmt.executeQuery(query); //riapro il ResultSet ottenendo i risultati aggiornati
            
            //muovo il cursore prima della prima riga e mostro i risultati a video
            uprs.beforeFirst();
            System.out.println("Tabella CLIENTE dopo l'inserimento: ");
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
                System.out.println(via +" " + num + " "+paese+" "+ codice);
                
            }
            uprs.close();
            stmt.close();

        } catch(SQLException ex) {
            System.err.println("-----SQLException-----");
			System.err.println("SQLState:  " + ex.getSQLState());
			System.err.println("Message:  " + ex.getMessage());
			System.err.println("Vendor:  " + ex.getErrorCode());
        }
        return con;
    }
}
