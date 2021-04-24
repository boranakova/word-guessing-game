
package kelimeoyunu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VeritabaniBilgileri {
    String username ="root";
    String password = "";
    String databaseName="kelimeoynu";
    String host = "localhost";
    int port = 3306;

  Connection conn = null;
    
  
  
  public VeritabaniBilgileri(){
      
   String url = "jdbc:mysql://"+host+":"+port+"/"+databaseName;
   try {
       Class.forName("com.mysql.cj.jdbc.Driver");
   
   }catch(ClassNotFoundException ex){
       System.out.println("Driver Bulunamadı");
   }
   try {
       Class.forName("com.mysql.cj.jdbc.Driver");
   
   }catch(ClassNotFoundException ex){
       System.out.println("Driver Bulunamadı");
   }
   
   try {
       conn = DriverManager.getConnection(url,username,password);
       System.out.println("Bağlantı Başarılı");
   
   }catch(SQLException ex){
       System.out.println("Bağlantı Başarısız");
   }
   
  }
  
  
  
  
    public static void main(String[] args) {
       
   VeritabaniBilgileri v1 = new VeritabaniBilgileri();
    
    }
    
    
    
}