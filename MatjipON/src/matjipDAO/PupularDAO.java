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


public class PupularDAO {
    private static Connection con;
      PreparedStatement pstmt ;//////
      ResultSet rs;
      public static class LoginSession{
         public static String loginUserId = "user5";
      
      }
      
      

      public PupularDAO() throws ClassNotFoundException, SQLException {
         
         con = new MatjipDBC().getConnection();
         // 객체.메소드
      
      }

      
       public ArrayList<WaitingVO> getAllInfoMatjip() throws SQLException{
            ArrayList<WaitingVO> matjiplist = new ArrayList<WaitingVO>();
            
         
            try {
               String sql2 ="SELECT MATJIP_ID, MATJIP_NAME, BUSY_LEVEL FROM MATJIP_TABLE ORDER BY BUSY_LEVEL DESC"; // 혼잡도 높은순
                     
               System.out.println(sql2);
               pstmt = con.prepareStatement(sql2);
               rs = pstmt.executeQuery();

               while (rs.next()) {
                  int matid12 = rs.getInt("MATJIP_ID");
                  String name = rs.getString("MATJIP_NAME");
                  System.out.println("id: " + matid12 + ", name: " + name);
                //  int busy = rs.getInt("BUSY_LEVEL");
                  WaitingVO vo = new WaitingVO(matid12, name);


                  matjiplist.add(vo);
                  //matjiplist.add(new String[] { name , busy });
               }

            } catch (SQLException e) {
               e.printStackTrace();      
            } catch (Exception e) {
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

    }
      