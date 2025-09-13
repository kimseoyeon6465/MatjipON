package matjipDAO;
import java.sql.Timestamp;
//bd랑 연결
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import matjipDBC.MatjipDBC;
import matjipVO.MatjipVO;

public class ReservationDAO {

   private Connection con;
   PreparedStatement pstmt=null;
   ResultSet rs=null;
   public ReservationDAO() throws ClassNotFoundException, SQLException {
   
   con = new MatjipDBC().getConnection();
}

  //by 서연 
   public void delete(int delid) {
      ArrayList<MatjipVO> waitarray = new ArrayList<MatjipVO> ();
      String sql = "delete from WAITING_TABLE where waiting_id=?";
      try {
         pstmt=con.prepareStatement(sql);
         pstmt.setInt(1, delid);
         pstmt.executeUpdate();
      }   catch(SQLException e){
         System.out.print("없는 값입니다.");

      }
      
   }
   
   public void cancel(int userid,int matjipid)//예약 취소-버튼 클릭 시 실행되도록 호출해야함
   {
	   String sql = "delete from WAITING_TABLE WHERE USER_ID=? and MATJIP_ID=?";
	      try {
	         pstmt=con.prepareStatement(sql);
	         pstmt.setInt(1, userid);
	         pstmt.setInt(2, matjipid);
	         pstmt.executeUpdate();
	      }   catch(SQLException e){
	         System.out.print("없는 값입니다.");

	      }
   }
   public void update_waiting() {
       String updateSql = 
                 "UPDATE WAITING_TABLE t " +
                 "SET t.WAITING_ID = ( " +
                 "    SELECT rn " +
                 "    FROM ( " +
                 "        SELECT ROW_NUMBER() OVER (ORDER BY INSERT_TIME) AS rn, ROWID AS rid " +
                 "        FROM WAITING_TABLE " +
                 "    ) sub " +
                 "    WHERE t.ROWID = sub.rid " +
                 ")";
             try {
                 pstmt = con.prepareStatement(updateSql);
                 pstmt.executeUpdate();
                 //System.out.println("WAITING_ID가 INSERT_TIME 순서대로 업데이트되었습니다.");
             } catch(SQLException e) {
                 System.out.println("오류: " + e.getMessage());
             }finally {
                 try {
                     con.setAutoCommit(true);  // 자동 커밋 다시 켜기
                 } catch (SQLException e) {
                     System.out.println("자동 커밋 설정 중 오류가 발생했습니다: " + e.getMessage());
                 }
             }
   }
   
   public ArrayList <MatjipVO> getAllInfo(int input_matjipid) throws SQLException
   {
      ArrayList<MatjipVO> waitarray = new ArrayList<MatjipVO> ();
      String sql = "SELECT * FROM WAITING_TABLE WHERE MATJIP_ID=? ORDER BY INSERT_TIME";
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, input_matjipid);
      rs=pstmt.executeQuery();
      
      while(rs.next()) {
            int waitingid = rs.getInt("waiting_id");
            String userid = rs.getString("user_id");
            int matjipid = rs.getInt("matjip_id");
            Timestamp inserttime = rs.getTimestamp("insert_time");//update 
            

            MatjipVO matvo = new MatjipVO();
            matvo.setWaitingid(waitingid);
            matvo.setUserid(userid);
            matvo.setMatjipid(matjipid);
            matvo.setInserttime(inserttime);
  
            waitarray.add(matvo);


         }
      return waitarray;
   }
   
   public int getWatingIdForPrint(String userid, int matjip_id) throws SQLException
   {
	   String sql = "SELECT WAITING_ID FROM WAITING_TABLE WHERE USER_ID=? AND MATJIP_ID=?";
	   pstmt=con.prepareStatement(sql);
	   pstmt.setString(1, userid);
	   pstmt.setInt(2, matjip_id);
	   rs=pstmt.executeQuery();
	   int position =-1;
	   while(rs.next())
	   {
		   position = rs.getInt("waiting_id");
		   //System.out.println(position);
	   }
	   
	   return position;
   }

 //by 서연 끝
   
   public ArrayList<MatjipVO> getAllInfoBusyRanking() throws SQLException{
	      ArrayList<MatjipVO> matjiplist = new ArrayList<MatjipVO>();
	      
	   
	      try {
	         String sql = "SELECT MATJIP_NAME FROM MATJIP_TABLE ORDER BY BUSY_LEVEL DESC"; // 혼잡도 높은순
	         pstmt = con.prepareStatement(sql);
	         rs = pstmt.executeQuery();

	         while (rs.next()) {
 // ← 순번
	             String name = rs.getString("MATJIP_NAME");
//	             int busylevel=rs.getInt("BUSY_LEVEL");

	             MatjipVO vo = new MatjipVO(name);
	             matjiplist.add(vo);
	            //matjiplist.add(new String[] { name , busy });
	         }

	      } catch (SQLException e) {
	         e.printStackTrace();      
	      }

	      return matjiplist;
	      }


   //성민님 코드
 //manage 자리
   public boolean updatePopular (int busylevel, String matjipname) throws SQLException {
      String sql = "UPDATE MATJIP_TABLE SET BUSY_LEVEL=? WHERE MATJIP_ID = ?";
      pstmt = con.prepareStatement(sql);
       pstmt.setInt(1, busylevel);
        pstmt.setString(2, matjipname);
      int result = pstmt.executeUpdate();
      return result > 0;
   }
   //CircleChart 자리
   public List<String[]> circleChart (String order) {
      List<String[]> result = new ArrayList<>();
      String sql = "SELECT m.MENU_NAME, SUM(o.SUM_PRICE) AS TOTAL_SALES " +
               "FROM ORDER_TABLE o " +
               "JOIN MENU_TABLE m ON o.MENU_ID = m.MENU_ID " +
               "WHERE o.MATJIP_ID = ? " +
               "GROUP BY m.MENU_NAME " +
               "ORDER BY TOTAL_SALES DESC";
      try (PreparedStatement pstmt= con.prepareStatement(sql)) {
         pstmt.setString(1, order);
         ResultSet rs = pstmt.executeQuery();
         
          while (rs.next()) {
                  String menuName = rs.getString("MENU_NAME");
                  String totalSales = rs.getString("TOTAL_SALES");
                  result.add(new String[]{ menuName, totalSales });
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }

          return result;
      } 
}