package matjipDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import matjipDBC.MatjipDBC;

public class ChatBotDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	
	public List<String[]> ChatBotStore () throws ClassNotFoundException, SQLException {
		con= new MatjipDBC().getConnection();
		List<String[]> StoreList = new ArrayList<>();
		String sql = "SELECT MATJIP_NAME, CATEGORY, BUSY_LEVEL FROM MATJIP_TABLE";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			String matjipname = rs.getString("MATJIP_NAME");
			String category = rs.getString("CATEGORY");
			String busylevel = rs.getString("BUSY_LEVEL");
			
			String[] storeinfo = { matjipname, category, busylevel};
			StoreList.add(storeinfo);
			
		}
		
		return StoreList;		
		
		}
}

