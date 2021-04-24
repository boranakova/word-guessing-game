/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kelimeoyunu;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;



public class SorguIslemleri {
    static Connection conn=null;
   
   
    
    public static void Listele()
    {
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kelimeoynu","root","");
            Statement stmt=conn.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from sozcuk"); 
            while(rs.next())  
            System.out.println(rs.getInt(1)+"  "+rs.getInt(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
 
        }catch(Exception e){ System.out.println(e);}
         
    }
    
    
   
   
      public static void main (String [] args){
      
         Listele();
      }
    
    
    
}
