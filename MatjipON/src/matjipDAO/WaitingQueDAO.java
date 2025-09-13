package matjipDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import matjipDBC.MatjipDBC;
import matjipVO.WaitingVO;

public class WaitingQueDAO {
     private static Connection con;
      PreparedStatement pstmt = null;//////
      ResultSet rs = null;


public WaitingQueDAO() throws ClassNotFoundException, SQLException {
      
    con = new MatjipDBC().getConnection();
    // 객체.메소드
 
    
  }
public int getWaitingIdByUserId(String userId) throws Exception {
    String sql = "SELECT WAITING_ORDER FROM VW_WAITING_ORDER WHERE USER_ID = ?";
       PreparedStatement pstmt = con.prepareStatement(sql);
       pstmt.setString(1, userId);

       ResultSet rs = pstmt.executeQuery();
       int waitingOrder = -1;

       if (rs.next()) {
           waitingOrder = rs.getInt("WAITING_ORDER");
       }

//        rs.close();
//       pstmt.close();

       return waitingOrder;
   
}


}