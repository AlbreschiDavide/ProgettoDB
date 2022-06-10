package it.isa.progetto;

import it.isa.progetto.Insert;
import it.isa.progetto.Update;
import it.isa.progetto.Join;
import it.isa.progetto.Delete;

import java.util.*;
import java.sql.*;
import javax.sql.*;

public class App 
{
    public static void main( String[] args )
    {
        String url = "jdbc:db2://localhost:50000/palestra";
        Connection con;
        //Statement stmt;

        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");

        } catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
            System.out.println("Ciao! Benvenuto!");
            
           // while(true){
                con = DriverManager.getConnection(url,"db2inst1","Spillo_1998");
                System.out.println("Connessione al Database riuscita");
		    
                System.out.println("Che operazione si vuole eseguire sul DB:\n(Inserire il corrispettivo numero)\n"
                                +"1. Insert\n2. Delete\n"
                                +"3. Update\n4. Join");
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter a number for input: ");
                int num = sc.nextInt();
            
                switch(num){
                case 1:
                    Insert.InsertRow(con);
                    break;
                case 2:
                    Delete.DeleteRow(con);
                    break;
                case 3:
                    Update.UpdateColumn(con);
                    break;
                case 4:
                    Join.JoinTables(con);
                    break;
                default:
                    System.out.println("Non è stata inserita una delle funzionalità richieste...");
                    System.out.println("Chiudo la connessione e termino...");
                    con.close();
                    System.exit(0);
                }
                //System.out.println("\n"); 
                con.close();     
            //} 
        }
        catch(SQLException ex) {
        System.err.println("-----SQLException-----");
        System.err.println("SQLState:  " + ex.getSQLState());
        System.err.println("Message:  " + ex.getMessage());
        System.err.println("Vendor:  " + ex.getErrorCode());
        }
    	
    }

}
