package it.isa.progetto;

import it.isa.progetto.Insert;
import it.isa.progetto.Update;
import it.isa.progetto.Join;

import java.util.*;
import java.sql.*;
import javax.sql.*;

public class App 
{
    public static void main( String[] args )
    {
        //Database select = new Database();
        //Database from = new Database();
        //Database where = new Database();
        System.out.println("Ciao!");

        String select = "SELECT *";
        String from = "FROM CLIENTE";
        String where = "";
        String query= select+" "+from+" "+ where+";" ;
        
		//Database query = new Database();
        //query.createQuery(select,from,where);

        //System.out.println("Query: "+ select);
        //System.out.println("Query: "+ from);
        //System.out.println("Query: "+ where);
        //System.out.println("Query: "+ query);
        
		System.out.println("Che operazione si vuole eseguire sul DB:\n(Inserire il corrispettivo numero)\n"
                                +"1. Insert\n2. Delete\n"
                                +"3. Update\n4. Join");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number for input: ");
        try{
            int num = sc.nextInt();
            System.out.println(num);
            switch(num){
                case 1:
                    Insert.InsertRow(query);
                    break;
                case 2:
                    //Delete.(query);
                    break;
                case 3:
                    Update.UpdateColumn(query);
                    break;
                case 4:
                    Join.JoinTables(query);
                    break;
                default:
                    System.out.println("Non è stato inserito uno dei numeri richiesti...\nTermino...");
                    System.exit(0);
            }
        }catch(Exception e) {
            System.out.println(e);
        }
    	
    }

}
