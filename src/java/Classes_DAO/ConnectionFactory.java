
package Classes_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionFactory {
    
    private static final String URL ="jdbc:derby://localhost:1527//lpto-2017-1";
    private static final String DRIVER ="org.apache.derby";
    private static final String USER ="usuario";
    private static final String PASSWORD ="senha";
    static Connection getConnection;
    
    public static Connection createConnection() throws Exception{
         Connection conexao = null;
        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL,USER,PASSWORD);
           
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Erro ao carregar o Driver", ex);     
        }  catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Erro ao carregar a conexao!", ex);       
        }
         return conexao;
    }
        
}
