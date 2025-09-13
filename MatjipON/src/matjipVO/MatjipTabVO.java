package matjipVO;

public class MatjipTabVO {
	
	 	private int matjipid; //PK
		private String matjipname;
		private String location;
		private String category;
		private int busylevel;
	    private ManagerVO managerid;      // FK
	    //VO 안에 다른 VO포함하여 연관객체 방식으로 작성하였습니다.
	    
	    
	    public MatjipTabVO() {}
	    
	    public MatjipTabVO(int matjipid, String matjipname, 
	    		String location, String category, int busylevel, ManagerVO managerid)
	    {
	    	super();
	    	this.matjipid = matjipid;
			this.matjipname = matjipname;
			this.location = location;
			this.category = category;
			this.busylevel = busylevel;
			this.managerid = managerid;
	    }

	    public MatjipTabVO(String name) {
			// TODO Auto-generated constructor stub
			  this.matjipname = name;
			  
		}

		   public MatjipTabVO(String name, int busy) {
			// TODO Auto-generated constructor stub
			  this.matjipname = name;
			  this.busylevel = busy;
		}
		    
	  
	    
	    
	    
	    
		public int getMatjipid() {
			return matjipid;
		}

		public void setMatjipid(int matjipid) {
			this.matjipid = matjipid;
		}

		public String getMatjipname() {
			return matjipname;
		}

		public void setMatjipname(String matjipname) {
			this.matjipname = matjipname;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public int getBusylevel() {
			return busylevel;
		}

		public void setBusylevel(int busylevel) {
			this.busylevel = busylevel;
		}

		public ManagerVO getManagerid() {
			return managerid;
		}

		public void setManagerid(ManagerVO managerid) {
			this.managerid = managerid;
		}
	    
	
}
