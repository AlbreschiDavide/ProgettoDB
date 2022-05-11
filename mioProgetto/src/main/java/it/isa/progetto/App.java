package it.isa.progetto;

import it.isa.progetto.Insert;
import java.sql.*;
import javax.sql.*;

public class App 
{
    public static void main( String[] args )
    {
        //Database select = new Database();
        //Database from = new Database();
        //Database where = new Database();
        System.out.println("Hello Word");

        String select = "SELECT *";
        String from = "FROM CLIENTE";
        String where = "";
        String query = select+" "+from+" "+ where+";" ;
        
		//Database query = new Database();
        //query.createQuery(select,from,where);

        System.out.println("Query: "+ select);
        System.out.println("Query: "+ from);
        System.out.println("Query: "+ where);
        System.out.println("Query: "+ query);
        
        //Insert.InsertRow(query);
        Update.update(query);
		
       
        	
    }
}
