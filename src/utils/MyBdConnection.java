
package utils;

/**
 *
 * @author Mokh
 */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyBdConnection {
  String url = "jdbc:mysql:"+ "//localhost:3306/allforkids";
   String login ="root";
   String pwd ="";
   Connection mycon;
   static MyBdConnection instanceBD;
  
    public MyBdConnection() {
      try {
          //get a connection to database
          mycon = DriverManager.getConnection(url, login, pwd);
          System.out.println("connexion etablite !!");
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      }
        
    }
    
    public static MyBdConnection  getInstanceBD(){
        if(instanceBD == null)
            instanceBD = new MyBdConnection();
        return instanceBD;
        
    }
    
    public Connection getConnection(){
        return mycon;
    }
    
    
    
}
