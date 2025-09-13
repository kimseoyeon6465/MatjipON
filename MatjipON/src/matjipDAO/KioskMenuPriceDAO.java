package matjipDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import matjipDBC.MatjipDBC;

public class KioskMenuPriceDAO {
    private Connection con;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    // 생성자
    public KioskMenuPriceDAO() throws ClassNotFoundException, SQLException {
        con = new MatjipDBC().getConnection();
    }

    // 메뉴 이름을 배열로 반환하는 메서드
    public String[] getMenuPrice(int matjipId) throws SQLException {
        String sql = "SELECT PRICE FROM MENU_TABLE WHERE MATJIP_ID = ?";
        ArrayList<String> menuList = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, matjipId); // 입력받은 matjipId를 쿼리에 설정
            rs = pstmt.executeQuery();

            // 쿼리 결과로 반환된 메뉴 이름을 리스트에 추가
            while (rs.next()) {
                menuList.add(rs.getString("PRICE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("메뉴 가격 조회 실패.");
        } finally {
            // 자원 해제
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }

        // 리스트의 내용을 배열로 변환하여 반환
        return menuList.toArray(new String[0]);
    }
}
