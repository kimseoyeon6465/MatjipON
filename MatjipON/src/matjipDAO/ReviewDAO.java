package matjipDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import matjipDBC.MatjipDBC;
import matjipVO.MatjipVO;

public class ReviewDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 리뷰 평균 평점 추가
	public double getAvgRating(int matjipId) throws SQLException, ClassNotFoundException {
		Connection con = new MatjipDBC().getConnection();
		String sql = "SELECT AVG(RATING) AS avg_rating FROM REVIEW_TABLE WHERE MATJIP_ID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, matjipId);

		ResultSet rs = pstmt.executeQuery();
		double avgRating = 0.0; // 초기화 추가!

		if (rs.next()) {
			avgRating = rs.getDouble("avg_rating");
		}

		rs.close();
		pstmt.close();
		con.close();

		return avgRating;
	}

	// Review창
	public ArrayList<MatjipVO> getAllInfoReview(int input_matjipid) throws SQLException, ClassNotFoundException {
		con = new MatjipDBC().getConnection();
		ArrayList<MatjipVO> reviewarray = new ArrayList<MatjipVO>();
		String sql = "SELECT REVIEW_ID, USER_ID, MATJIP_ID, REVIEW_TEXT, RATING FROM REVIEW_TABLE WHERE MATJIP_ID=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, input_matjipid);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			int reviewid = rs.getInt("review_id");
			String id = rs.getString("user_id");
			int matjip_id = rs.getInt("matjip_id");
			String reviewtext = rs.getString("review_text");
			int rating = rs.getInt("rating");

			MatjipVO matvo = new MatjipVO();
			matvo.setReviewid(reviewid);
			matvo.setUserid(id);
			matvo.setMatjipid(matjip_id);
			matvo.setReviewtext(reviewtext);
			matvo.setRating(rating);

			reviewarray.add(matvo);
		}
		return reviewarray;
	}

	public void deleteReview(int delid) throws SQLException, ClassNotFoundException {

		con = new MatjipDBC().getConnection();
		String sql = "DELETE FROM REVIEW_TABLE WHERE REVIEW_ID=?";
		// String dltsql = "DELETE FROM REVIEW_TABLE WHERE ROWNUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, delid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.print("없는 값입니다.");
		}
	}

	// id, matjip_id, reviewText, selectedRating
	public Map<String, Object> review(String id, int matjip_id, String reviewtxt, int rating)
			throws SQLException, ClassNotFoundException {
		Map<String, Object> resultMap = new HashMap<>();

		String sql = "INSERT INTO REVIEW_TABLE (USER_ID, MATJIP_ID, REVIEW_TEXT, RATING) VALUES (?, ?, ?, ?)";

		// selectedRating, reviewText는 JFrame에서 잘 받아오고 있고
		// id, matjip_id는 LoginSession, MatjipSession에서 잘 가져오고 있으며
		// ReviewDAO.review() 안에서 DB 연결을 반드시 새로 설정해야 정상 동작

		try (Connection con = new MatjipDBC().getConnection(); // ReviewDAO.review() 안에서 DB 연결을 반드시 새로 설정해야 정상 동작//
																// Connection 생성 추가 필요!!
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.setInt(2, matjip_id);
			pstmt.setString(3, reviewtxt);
			pstmt.setInt(4, rating);

			int result = pstmt.executeUpdate(); // 삽입 결과

			if (result > 0) {
				resultMap.put("success", true);
				resultMap.put("message", "리뷰등록성공!");
			} else {
				resultMap.put("success", false);
				resultMap.put("message", "리뷰등록실패");
			}
		}

		return resultMap;
	}

	public String view3(int input_matjipid) throws SQLException, ClassNotFoundException {
		con = new MatjipDBC().getConnection();
		String sql = "SELECT MATJIP_NAME FROM MATJIP_TABLE WHERE MATJIP_ID=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, input_matjipid);
		rs = pstmt.executeQuery();

		String searchname = null;
		if (rs.next()) {
			searchname = rs.getString("MATJIP_NAME");
		}

		return searchname;
	}

	// ReviewView창
	public String view1(int input_matjipid) throws SQLException, ClassNotFoundException {

		con = new MatjipDBC().getConnection();
		String sql = "SELECT MATJIP_NAME FROM MATJIP_TABLE WHERE MATJIP_ID=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, input_matjipid);
		rs = pstmt.executeQuery();

		String matjipname2 = rs.getString("MATJIP_NAME");

		return matjipname2;
	}

	public ArrayList<HashMap<String, Object>> view2(int input_matjipid) throws SQLException, ClassNotFoundException {
		ArrayList<HashMap<String, Object>> viewarray = new ArrayList<>();

		con = new MatjipDBC().getConnection();
		String sql = "SELECT REVIEW_ID, USER_ID, MATJIP_ID, REVIEW_TEXT, RATING FROM REVIEW_TABLE WHERE MATJIP_ID=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, input_matjipid);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			HashMap<String, Object> row = new HashMap<>();
			row.put("REVIEW_ID", rs.getInt("REVIEW_ID"));
			row.put("USER_ID", rs.getString("USER_ID"));
			row.put("MATJIP_ID", rs.getInt("MATJIP_ID"));
			row.put("REVIEW_TEXT", rs.getString("REVIEW_TEXT"));
			row.put("RATING", rs.getInt("RATING"));

			viewarray.add(row);
		}

		return viewarray;
	}

	public static class MatjipSession {
		public static int selectedMatjipId = -1;
	}

	public ArrayList<MatjipVO> getAllInfoReview2(int matid) throws SQLException, ClassNotFoundException {

		con = new MatjipDBC().getConnection();
		ArrayList<MatjipVO> reviewarray = new ArrayList<MatjipVO>();
		String sql = "SELECT REVIEW_ID, USER_ID, MATJIP_ID, REVIEW_TEXT, RATING FROM REVIEW_TABLE WHERE MATJIP_ID=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, matid);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			int reviewid = rs.getInt("review_id");
			String id = rs.getString("user_id");
			int matjip_id = rs.getInt("matjip_id");
			String reviewtext = rs.getString("review_text");
			int rating = rs.getInt("rating");

			MatjipVO matvo = new MatjipVO();
			matvo.setReviewid(reviewid);
			matvo.setUserid(id);
			matvo.setMatjipid(matjip_id);
			matvo.setReviewtext(reviewtext);
			matvo.setRating(rating);

			reviewarray.add(matvo);
		}
		return reviewarray;
	}

	public static void update_review(int input_matjipid) throws ClassNotFoundException, SQLException {
		String sql = "MERGE INTO REVIEW_TABLE t " + "USING ( "
				+ "    SELECT ROWID AS rid, ROW_NUMBER() OVER (ORDER BY RATING) AS new_id " + "    FROM REVIEW_TABLE "
				+ "    WHERE MATJIP_ID = ? " + ") sub " + "ON (t.ROWID = sub.rid) " + "WHEN MATCHED THEN "
				+ "UPDATE SET t.REVIEW_ID = sub.new_id";
		try (Connection con = new MatjipDBC().getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			con.setAutoCommit(false);
			pstmt.setInt(1, input_matjipid);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("오류 발생: " + e.getMessage());
			throw e; // 롤백은 호출하는 쪽에서 처리하거나 로그로만 남겨도 됩니다.
		}
	}
}
