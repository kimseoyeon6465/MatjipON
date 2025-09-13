package matjipDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import matjipDBC.MatjipDBC;

public class KioskMenuIdDAO {
    private Connection con;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    // 생성자
    public KioskMenuIdDAO() throws ClassNotFoundException, SQLException {
        con = new MatjipDBC().getConnection();
    }

    // 메뉴 ID 배열 반환
    public int[] getMenuID(int matjipId) throws SQLException {
        String sql = "SELECT MENU_ID FROM MENU_TABLE WHERE MATJIP_ID = ?";
        ArrayList<Integer> menuIDList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, matjipId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                menuIDList.add(rs.getInt("MENU_ID"));
            }

            // ArrayList → int[]
            int[] result = new int[menuIDList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = menuIDList.get(i);
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("메뉴 ID 조회 실패.");
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
    }
}
