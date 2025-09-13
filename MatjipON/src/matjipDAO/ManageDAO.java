package matjipDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import matjipDBC.MatjipDBC;

public class ManageDAO {

	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public ManageDAO() throws ClassNotFoundException, SQLException {
		con = new MatjipDBC().getConnection();
		// 객체.메소드()
	}

//manage 자리
	public boolean updatePopular(int busylevel, int matjipid) throws SQLException {
		String sql = "UPDATE MATJIP_TABLE SET BUSY_LEVEL=? WHERE MATJIP_ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, busylevel);
			pstmt.setInt(2, matjipid);
			int result = pstmt.executeUpdate();
			return result > 0;

		} catch (SQLException e) {
			System.err.println("DB Error: " + e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
		}
		return false;

	}
}
