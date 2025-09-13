package matjipDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import matjipDBC.MatjipDBC;

public class CircleChartDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public List<String[]> circleChart(String order) throws ClassNotFoundException, SQLException {
		con = new MatjipDBC().getConnection();
		List<String[]> result = new ArrayList<>();
		String sql = "SELECT M.MENU_NAME MENU_NAME, (O.MENU_COUNT*O.PRICE) PRICE "
				+ "FROM MENU_TABLE M, ORDER_DETAIL_TABLE O " + 
				"WHERE M.MENU_ID = O.MENU_ID AND M.MATJIP_ID = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, order);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String menuName = rs.getString("MENU_NAME");
				String sumprice = rs.getString("PRICE");
				result.add(new String[] { menuName, sumprice });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
