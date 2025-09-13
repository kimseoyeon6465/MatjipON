package matjipVO;

public class MenuVO {
	private int menuid;
	private MatjipTabVO matjipid;
	private String menuname;
	private int price;
	  //VO 안에 다른 VO포함하여 연관객체 방식으로 작성하였습니다.
	
	
	public MenuVO() {}
	
	public MenuVO(
			int menuid, MatjipTabVO matjipid, 
			String menuname, int price
			) {
		super();
		this.menuid = menuid;
		this.matjipid = matjipid;
		this.menuname = menuname;
		this.price = price;
	}

	public int getMenuid() {
		return menuid;
	}

	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}

	public MatjipTabVO getMatjipid() {
		return matjipid;
	}

	public void setMatjipid(MatjipTabVO matjipid) {
		this.matjipid = matjipid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
