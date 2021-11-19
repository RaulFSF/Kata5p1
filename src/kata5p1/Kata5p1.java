package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Kata5p1 {
    private final String name = "KATA5.db";
    
    public static void main(String[] args) {
        Kata5p1 kata5p1= new Kata5p1();
        kata5p1.testQuery();
        
    }
    
    private Connection connect(){
        Connection conn = null;
        String url = "jdbc:sqlite:" + name;
        try{
            conn = DriverManager.getConnection(url);
        }catch(SQLException ex){
            System.out.println(""+ex.getMessage());
        }
        return conn;
    }
    
    private void close(Connection conn){
        if(conn!=null){
            try{
                conn.close();
            } catch(SQLException ex) {
                System.out.println(""+ex.getMessage());
            }
        }
    }
    
    public void testQuery(){
        
        Connection conn=null;
        String sql="SELECT * FROM PEOPLE";
        
        try{
            conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getInt("Id")+ "\t" + 
                        rs.getString("Name")+ "\t" + 
                        rs.getString("Apellidos") + "\t" + 
                        rs.getString("Departamento"));
            }
        }catch(SQLException ex){
            System.out.println(""+ex.getMessage());
        } finally{
            close(conn);
        }
    }
}
