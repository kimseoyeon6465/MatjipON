package matjipVO;

public class UserVO {
	//USER_TABLE
		private String userid; //PK
		private String password;
		private String username;
		private String phonenumber;
		
		private ManagerVO managerid; 
		//VO 안에 다른 VO포함하여 연관객체 방식으로 작성하였습니다.
		
		
public UserVO() {}

public UserVO (String userid, String password, String username, String phonenumber, ManagerVO managerid
		) {
	super();
	this.userid = userid;
	this.password = password;
	this.username = username;
	this.phonenumber = phonenumber;
	this.managerid = managerid;
}

public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPhonenumber() {
	return phonenumber;
}

public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}

public ManagerVO getManagerid() {
	return managerid;
}

public void setManagerid(ManagerVO managerid) {
	this.managerid = managerid;
}




}
