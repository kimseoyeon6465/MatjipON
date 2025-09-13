package matjipDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MatjipDBC {
	
	 private Connection con;
	   public Connection getConnection() {//접속객체를 꺼내는 메소드 getter
	      
	      return con;
	   }
	   public MatjipDBC() throws ClassNotFoundException, SQLException {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      con= DriverManager.getConnection  // DriverManager 필요
	            ("jdbc:oracle:thin:@127.0.0.1:1521:xe","hr","hr");   //접속 
	   }



}
