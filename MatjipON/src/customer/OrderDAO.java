package customer;

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
import matjipVO.OrderVO;
public class OrderDAO {
	private Connection con;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	public OrderDAO() throws ClassNotFoundException, SQLException {
		   
		   con = new MatjipDBC().getConnection();
		}
	public void insertOrder(int orderid, String userid, int matjipid, int sumprice, Timestamp ordertime) throws SQLException
	   {
		   ArrayList<MatjipVO> orderarray = new ArrayList<MatjipVO> ();
		   String sql ="INSERT INTO ORDER_TABLE  VALUES(?,?,?,?,?)";
		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1, orderid);
		   pstmt.setString(2, userid);
		   pstmt.setInt(3, matjipid);
		   pstmt.setInt(4, sumprice);
		   pstmt.setTimestamp(5, ordertime);
		   pstmt.executeUpdate();
	   }
	
	   public ArrayList <MatjipVO> getAllInfoOrder(int input_matjipid) throws SQLException
	   {
		   ArrayList<MatjipVO> orderarray = new ArrayList<MatjipVO>();
		   String sql ="SELECT * FROM ORDER_TABLE WHERE MATJIP_ID=?";
		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1, input_matjipid);
		   rs=pstmt.executeQuery();
		   while(rs.next())
		   {
			   int orderid = rs.getInt("order_id");
			   String userid = rs.getString("user_id");
			   int matjipid = rs.getInt("matjip_id");
			   Timestamp ordertime = rs.getTimestamp("order_time");
			   
			   MatjipVO matvo = new MatjipVO();
			   matvo.setOrderid(orderid);
			   matvo.setUserid(userid);
			   matvo.setMatjipid(matjipid);
			   matvo.setOrdertime(ordertime);
			   
			   orderarray.add(matvo);
		   }
		   return orderarray;
	   }
	   
	   public ArrayList<OrderVO> getMyOrder(int input_orderid) throws SQLException
	   {
		   ArrayList<OrderVO> orderarray = new ArrayList<OrderVO>();
		   String sql ="SELECT ORDER_ID, MATJIP_ID, SUM_PRICE, ORDER_TIME FROM ORDER_TABLE WHERE ORDER_ID=?";
		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1, input_orderid);
		   rs=pstmt.executeQuery();
		   while(rs.next())
		   {
			   int orderid = rs.getInt("order_id");
//			   String userid = rs.getString("user_id");
			   int matjipid = rs.getInt("matjip_id");
			   int sumprice = rs.getInt("sum_price");
			   Timestamp ordertime = rs.getTimestamp("order_time");
			   
//			   MatjipVO matvo = new MatjipVO();
			   OrderVO vo = new OrderVO();
			   vo.setOrderid(orderid);
//			   vo.setUserid(userid);
			   vo.setMatjipid(matjipid);
			   vo.setSumprice(sumprice);
			   vo.setOrdertime(ordertime);
			   
			   orderarray.add(vo);
		   }
		   return orderarray;
	   }
	   
//	   public void insertOrderDetails(Connection conn, int orderId, List<MenuOrder> orderList) throws SQLException {
//		    String insertSql = "INSERT INTO ORDER_DETAIL_TABLE (ORDER_ID, MENU_ID, MENU_COUNT, PRICE) VALUES (?, ?, ?, ?)";
//		    
//		    try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
//		        for (MenuOrder item : orderList) {
//		            pstmt.setInt(1, orderId);
//		            pstmt.setInt(2, item.getMenuId());
//		            pstmt.setInt(3, item.getCount());
//		            pstmt.setInt(4, item.getUnitPrice());
//		            pstmt.executeUpdate();  // 또는 addBatch() 사용 가능
//		        }
//		    }
//		}
	   public void insertOrderDetails2(Connection conn, int orderId, List<OrderVO> orderList) throws SQLException {
		    String insertSql = "INSERT INTO ORDER_DETAIL_TABLE (ORDER_ID, MENU_ID, MENU_COUNT, PRICE) VALUES (?, ?, ?, ?)";
		    
		    try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
		        for (OrderVO item : orderList) {
		            pstmt.setInt(1, orderId);
		            pstmt.setInt(2, item.getMenuId());
		            pstmt.setInt(3, item.getCount());
		            pstmt.setInt(4, item.getUnitPrice());
		            pstmt.executeUpdate();  // 또는 addBatch() 사용 가능
		        }
		    }
		}
	   
	   public void insertOrderDetails(int orderid, List<OrderVO> orderList) throws SQLException
	   {
		   String sql = "INSERT INTO ORDER_DETAIL_TABLE (ORDER_ID, MENU_ID, MENU_COUNT, PRICE) VALUES (?,?,?,?)";
		   
		   pstmt =con.prepareStatement(sql);
		   for(OrderVO item : orderList)
		   {
			   pstmt.setInt(1, orderid);
			   pstmt.setInt(2, item.getMenuId());
			   pstmt.setInt(3, item.getCount());
	           pstmt.setInt(4, item.getUnitPrice());
	           pstmt.executeUpdate();
		   }
		   
	   }

	   public int manageOrderId()
	   {
		   int orderid=1000;//order id를 1000번부터 증가시킴
		   orderid++;		   
		   return orderid;
	   }
	   
	   public int setOrderId1000() throws SQLException {
		    String sql = "SELECT NVL(MAX(ORDER_ID), 1000) FROM ORDER_TABLE";
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    ResultSet rs = pstmt.executeQuery();
		    int orderId = 1000;
		    if (rs.next()) {
		        orderId = rs.getInt(1) + 1;
		    }
		    rs.close();
		    pstmt.close();
		    return orderId;
		}

	   
}
