package matjipDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import matjipDBC.MatjipDBC;
import matjipVO.MatjipTabVO;
import matjipVO.MatjipVO;
import matjipVO.OrderVO;
import manager.Manage;
import matjipDAO.WaitingDAO.LoginSession;


public class MatjipDAO {
   
   private Connection con; 
   PreparedStatement pstmt=null;
   ResultSet rs=null;
   ResultSet resultinfo=null;
   public static int mid_r=1000;
   public MatjipDAO()
         throws ClassNotFoundException, SQLException {
   con= new MatjipDBC().getConnection();
   // 객체.메소드()
   }
   
//   public static class LoginSession {
//       public static String loginUserId;
//   }
   
//로그인 메인 자리
   public boolean Login (String id, String hashedPw) throws SQLException { 	//그냥 pw아니고 암호화된 hashedPw
	   String sql = "SELECT * FROM USER_TABLE WHERE USER_ID = ? AND PASSWORD = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, hashedPw);
		rs = pstmt.executeQuery();
		
		//return rs.next(); 
        if (rs.next()) {					//DB에서 결과가 있으면
            LoginSession.loginUserId = id;   // 로그인 성공 시, 세션에 아이디 저장, LoginSession.loginUserId에 로그인한 유저의 ID를 저장해서 다른 클래스에서도 접근 가능하게 함.
            return true;
        }
        return false;
	}
   
// manager_id 조회 메서드 null 이냐 아니냐로 유저창,매니저창이 나뉘는 부분 (추가된 부분)
   public String getManagerId(String id) throws SQLException {
       String sql = "SELECT manager_id FROM USER_TABLE WHERE USER_ID = ?";
       try (PreparedStatement pstmt = con.prepareStatement(sql)) {
           pstmt.setString(1, id);
           try (ResultSet rs = pstmt.executeQuery()) {
               if (rs.next()) {
                   return rs.getString("manager_id");	// manager_id가 있다면 반환
               }
           }
       }
       return null;		// 없으면 null 반환 → 일반 사용자
   }
  
   
 public int getmidForReview(String user_id) throws SQLException
 {
	 String sql ="SELECT MATJIP_ID FROM MANAGER_TABLE WHERE USER_ID = ?";
	 PreparedStatement pstmt = con.prepareStatement(sql);
	 pstmt.setString(1, user_id);
	 rs = pstmt.executeQuery();
	 
	 if(rs.next()){
		 mid_r=rs.getInt("matjip_id");
	 }
	 
	 return mid_r;
 }
 // 회원가입 시 ID가 이미 존재하는지 확인하는 메서드
 public boolean idCheck(String info_id) throws SQLException { //중복확인
	
	 String sql = "SELECT * FROM USER_TABLE WHERE USER_ID = ?";
	    pstmt = con.prepareStatement(sql);
	    pstmt.setString(1, info_id);
	    resultinfo = pstmt.executeQuery();
	    return resultinfo.next();  // 존재하면 true (중복), 없으면 false
}  
 
 //회원 정보를 USER_TABLE에 삽입하는 메서드
 public Map<String, Object> userinfo(String info_id, String info_pw, String info_name, String info_phone) throws SQLException {
	    Map<String, Object> resultMap = new HashMap<>();
	    
	    String sql = "INSERT INTO USER_TABLE (USER_ID, PASSWORD, USER_NAME, PHONE_NUMBER) VALUES (?, ?, ?, ?)";
	    pstmt = con.prepareStatement(sql);
	    pstmt.setString(1, info_id);
	    pstmt.setString(2, info_pw);
	    pstmt.setString(3, info_name);
	    pstmt.setString(4, info_phone);

	    int result = pstmt.executeUpdate(); // 삽입 결과// 성공적으로 삽입되면 1 반환

	    if (result > 0) {
	        resultMap.put("success", true);
	        resultMap.put("message", "회원가입 성공!");
	    } else {
	        resultMap.put("success", false);
	        resultMap.put("message", "회원가입 실패: 데이터베이스에 저장되지 않았습니다.");
	    }

	    return resultMap;
	}
	 
//manage 자리
   


//----------------------------------------------------------
public ArrayList<MatjipTabVO> getAllInfoMatjip() throws SQLException{
    ArrayList<MatjipTabVO> matjiplist = new ArrayList<MatjipTabVO>();
    
 
    try {
       String sql = "SELECT MATJIP_NAME, BUSY_LEVEL FROM MATJIP_TABLE ORDER BY BUSY_LEVEL ASC"; // 혼잡도 낮은순
       pstmt = con.prepareStatement(sql);
       rs = pstmt.executeQuery();

       while (rs.next()) {
          String name = rs.getString("MATJIP_NAME");
          int busy = rs.getInt("BUSY_LEVEL");
          MatjipTabVO vo = new MatjipTabVO(name, busy);
          

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
//------------------------------------------------------
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
public void deleteByWaitingOrder(int waitingOrder) {
     String sql =
         "DELETE FROM WAITING_TABLE " +
         "WHERE ROWID IN ( " +
         "  SELECT rid FROM ( " +
         "    SELECT ROWID AS rid, ROW_NUMBER() OVER (PARTITION BY MATJIP_ID ORDER BY INSERT_TIME) AS rn " +
         "    FROM WAITING_TABLE " +
         "    WHERE MATJIP_ID = ? " +
         "  ) WHERE rn = ? " +
         ")";
     try {
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, 101); // MATJIP_ID는 고정되어 있거나 파라미터로 받아야 함
         pstmt.setInt(2, waitingOrder);
         pstmt.executeUpdate();
     } catch (SQLException e) {
         System.out.println("삭제 중 오류 발생: " + e.getMessage());
     }
 }
public void cancel(String userid)//예약 취소-버튼 클릭 시 실행되도록 호출해야함
{
    String sql = "delete from WAITING_TABLE WHERE USER_ID=?";
       try {
          pstmt=con.prepareStatement(sql);
          pstmt.setString(1, userid);
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
  String sql = "SELECT * FROM VW_WAITING_ORDER WHERE MATJIP_ID=? ORDER BY WAITING_ORDER";
  pstmt = con.prepareStatement(sql);
  pstmt.setInt(1, input_matjipid);
  rs=pstmt.executeQuery();
  
  while(rs.next()) {
        
        String userid = rs.getString("user_id");
        int matjipid = rs.getInt("matjip_id");
        Timestamp inserttime = rs.getTimestamp("insert_time");//update 
        int waitingOrder = rs.getInt("waiting_order");
              

        MatjipVO matvo = new MatjipVO();
       
        matvo.setUserid(userid);
        matvo.setMatjipid(matjipid);
        matvo.setInserttime(inserttime);
        matvo.setWaitingOrder(waitingOrder);

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
//← 순번
              String name = rs.getString("MATJIP_NAME");
//              int busylevel=rs.getInt("BUSY_LEVEL");

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
