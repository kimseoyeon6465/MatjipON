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


public class SelectListDAO {
   private static Connection con;
      PreparedStatement pstmt = null;//////
      ResultSet rs = null;
      public SelectListDAO() throws ClassNotFoundException, SQLException {
         
            con = new MatjipDBC().getConnection();
            // 객체.메소드
         }
      public ArrayList<WaitingVO> getAllMatjip() throws SQLException{
         ArrayList<WaitingVO> list = new ArrayList<>();
       String sql = "SELECT MATJIP_ID, MATJIP_NAME, LOCATION, CATEGORY, BUSY_LEVEL FROM MATJIP_TABLE";
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();

          // int search_id = rs.getInt("MATJIP_ID"); 
           
           while (rs.next()) {
               WaitingVO vo = new WaitingVO();
               vo.setMatjipname(rs.getString("MATJIP_NAME"));
               vo.setLocation(rs.getString("LOCATION"));
               vo.setCategory(rs.getString("CATEGORY"));
               vo.setBusylevel(rs.getInt("BUSY_LEVEL"));
               list.add(vo);
           }
           
        rs.close();
          ps.close();
          
           return list;
       
      }

}
