package it.isa.progetto;

import java.sql.*;
import java.util.Scanner;

public class Delete {
    public static Connection DeleteRow(Connection con) {

        try {
            //con = DriverManager.getConnection(url,"db2inst1","Spillo_1998");
        
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                                        ResultSet.CONCUR_UPDATABLE);
            
           
            System.out.println("Cancelleremo l'ultima riga nella tabella CLIENTE: ");
            
            String query= "SELECT * FROM CLIENTE ;";
            ResultSet uprs = stmt.executeQuery(query);
            
            uprs.last(); //identifica la riga da cancellare dal resultset
            uprs.deleteRow(); //cancella la riga dal result set e dal DB
            uprs.beforeFirst();

            System.out.println("Tabella CLIENTE dopo la cancellazione: ");
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
        }
            //con.close();
        catch(SQLException ex) {
            System.err.println("-----SQLException-----");
			System.err.println("SQLState:  " + ex.getSQLState());
			System.err.println("Message:  " + ex.getMessage());
			System.err.println("Vendor:  " + ex.getErrorCode());
        }
        return con;
    }    
}
