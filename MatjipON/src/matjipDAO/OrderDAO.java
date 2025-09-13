package matjipDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import matjipDBC.MatjipDBC;
import matjipVO.MatjipVO;
import matjipVO.OrderVO;

public class OrderDAO {
    private Connection con;

    public OrderDAO() throws ClassNotFoundException, SQLException {
        con = new MatjipDBC().getConnection();
    }

    public void insertOrder(int orderId, String userId, int matjipId, int totalPrice, Timestamp orderTime) throws SQLException {
        String sql = "INSERT INTO ORDER_TABLE (ORDER_ID, USER_ID, MATJIP_ID, SUM_PRICE, ORDER_TIME) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.setString(2, userId);
            pstmt.setInt(3, matjipId);
            pstmt.setInt(4, totalPrice);
            pstmt.setTimestamp(5, orderTime);
            pstmt.executeUpdate();
        }
    }

    public void insertOrderDetails(int orderId, List<OrderVO> orderList) throws SQLException {
        String sql = "INSERT INTO ORDER_DETAIL_TABLE (ORDER_ID, MENU_ID, MENU_COUNT, PRICE) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            for (OrderVO item : orderList) {
                if (item.getCount() > 0) {
                    pstmt.setInt(1, orderId);
                    pstmt.setInt(2, item.getMenuId());
                    pstmt.setInt(3, item.getCount());
                    pstmt.setInt(4, item.getUnitPrice());
                    pstmt.executeUpdate();
                }
            }
        }
    }

    public List<MatjipVO> getAllInfoOrder(int matjipId) throws SQLException {
        String sql = "SELECT * FROM ORDER_TABLE WHERE MATJIP_ID=?";
        List<MatjipVO> orderList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, matjipId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    MatjipVO vo = new MatjipVO();
                    vo.setOrderid(rs.getInt("order_id"));
                    vo.setUserid(rs.getString("user_id"));
                    vo.setMatjipid(rs.getInt("matjip_id"));
                    vo.setOrdertime(rs.getTimestamp("order_time"));
                    orderList.add(vo);
                }
            }
        }
        return orderList;
    }

    public List<OrderVO> getMyOrder(int orderId) throws SQLException {
        String sql = "SELECT * FROM ORDER_TABLE WHERE ORDER_ID=?";
        List<OrderVO> orderList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrderVO vo = new OrderVO();
                    vo.setOrderid(rs.getInt("order_id"));
                    vo.setUserid(rs.getString("user_id"));
                    vo.setMatjipid(rs.getInt("matjip_id"));
                    vo.setSumprice(rs.getInt("sum_price"));
                    vo.setOrdertime(rs.getTimestamp("order_time"));
                    orderList.add(vo);
                }
            }
        }
        return orderList;
    }

    public int setOrderId1000() throws SQLException {
        String sql = "SELECT NVL(MAX(ORDER_ID), 1000) FROM ORDER_TABLE";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        }
        return 1001;
    }
} 