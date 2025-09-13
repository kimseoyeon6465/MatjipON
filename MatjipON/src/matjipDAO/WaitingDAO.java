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




public class WaitingDAO {
   private static Connection con;
   PreparedStatement pstmt = null;//////
   ResultSet rs = null;
   public static class LoginSession{
      public static String loginUserId = "user2";
   
   }
  
   

   public WaitingDAO() throws ClassNotFoundException, SQLException {
      
      con = new MatjipDBC().getConnection();
      // 객체.메소드
   }

    public ArrayList<WaitingVO> getAllInfoMatjip() throws SQLException{
         ArrayList<WaitingVO> matjiplist = new ArrayList<WaitingVO>();
         
      
         try {
            String sql = "SELECT MATJIP_ID, MATJIP_NAME, BUSY_LEVEL FROM MATJIP_TABLE ORDER BY BUSY_LEVEL ASC"; // 혼잡도 낮은순
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
               int id = rs.getInt("MATJIP_ID");
               String name = rs.getString("MATJIP_NAME");
               int busy = rs.getInt("BUSY_LEVEL");
               WaitingVO vo = new WaitingVO(id,name, busy);


               matjiplist.add(vo);
               //matjiplist.add(new String[] { name , busy });
            }

         } catch (SQLException e) {
            e.printStackTrace();      
         }

         return matjiplist;
         }

     public int insertWaiting(String userId, String matjipName) throws Exception {
         String sql = "SELECT MATJIP_ID FROM MATJIP_TABLE WHERE MATJIP_NAME = ?";
         PreparedStatement pstmt = con.prepareStatement(sql);
         pstmt.setString(1, matjipName);
         ResultSet rs = pstmt.executeQuery();
         
             int matjipId = -1;
              if (rs.next()) {
                  matjipId = rs.getInt("MATJIP_ID");
              }
              rs.close();
              pstmt.close();

              if (matjipId == -1) throw new Exception("맛집 ID를 찾을 수 없습니다.");
              

              // 2. WAITING_TABLE에 INSERT
        
             String insertSql = "INSERT INTO WAITING_TABLE (USER_ID, MATJIP_ID) VALUES (?, ?)";
              PreparedStatement ps2 = con.prepareStatement(insertSql);
              ps2.setString(1, userId);
              ps2.setInt(2, matjipId);

              int result = ps2.executeUpdate();
              
              

              ps2.close();
          

              return result;
         
      }
    public static class MatjipSession{
       public static int selectedMatjipId = -1;

        
    }
    
    
 // Select_List2의 DAO, 예약하기버튼 누르면 USER_ID와 MATJIP_ID로 바로 insert
    public int insertWaitingById(String userId, int matjipId) throws SQLException {
        String sql = "INSERT INTO WAITING_TABLE (USER_ID, MATJIP_ID) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, userId);
        ps.setInt(2, matjipId);

        int result = ps.executeUpdate();
        ps.close();
        return result;
    }
    
 // 혼잡도(BUSY_LEVEL)를 matjipId 기준으로 반환
    public int getBusyLevelById(int matjipid) throws SQLException {
        for (WaitingVO vo : getAllInfoMatjip()) {
            if (vo.getMatjipid() == matjipid) return vo.getBusylevel();
        }
        return -1;
    }
    


 }
   
