package matjipDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import matjipDAO.WaitingDAO.MatjipSession;
import matjipDBC.MatjipDBC;

public class ClickedDAO {
      private Connection con;
      PreparedStatement pstmt = null;//////
      ResultSet rs = null;
      
public int selectmatjip(String name) throws SQLException, ClassNotFoundException {
	con = new MatjipDBC().getConnection();
	String sql = "SELECT MATJIP_ID FROM MATJIP_TABLE WHERE MATJIP_NAME = ?";
   pstmt = con.prepareStatement(sql);
   pstmt.setString(1, name);
   rs = pstmt.executeQuery();//
   
   
   
   int matjipid = -1;  // 기본값
   if (rs.next()) {
       matjipid = rs.getInt("MATJIP_ID");
   }
   MatjipSession.selectedMatjipId = matjipid;
   return matjipid;
   }

}
