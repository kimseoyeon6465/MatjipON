package matjipVO;

public class ManagerVO {
	private int managerid;      // PK
    private UserVO userid;      // FK
    private MatjipTabVO matjipid;       // FK
  //VO 안에 다른 VO포함하여 연관객체 방식으로 작성하였습니다.
    
    public ManagerVO() {}
    public ManagerVO(int managerid, UserVO userid, MatjipTabVO matjipid) {
    	super();
    	this.managerid = managerid;
    	this.matjipid = matjipid;
    	this.userid = userid;
    	
    }
	public int getManagerid() {
		return managerid;
	}
	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}
	public UserVO getUserid() {
		return userid;
	}
	public void setUserid(UserVO userid) {
		this.userid = userid;
	}
	public MatjipTabVO getMatjipid() {
		return matjipid;
	}
	public void setMatjipid(MatjipTabVO matjipid) {
		this.matjipid = matjipid;
	}
    
}
