package matjipVO;
import java.sql.Timestamp;

public class OrderVO {
	private String userid;
	private int matjipid;
	private int menuid;
    private int count;
    private int unitPrice;
    private int orderid;
    private int menucount;
    private int sumprice;
    private Timestamp ordertime;

    public OrderVO(String userid,int matjipid, int menuid, int count, int unitPrice, int orderid, int menucount, int sumprice, Timestamp ordertime) {
        this.userid = userid;
        this.matjipid = matjipid;
    	this.menuid = menuid;
        this.count = count;
        this.unitPrice = unitPrice;
        this.orderid = orderid;;
        this.menucount = menucount;
        this.sumprice = sumprice;
        this.ordertime = ordertime;
        
    }

    public OrderVO() {
		// TODO Auto-generated constructor stub
	}

	public int getMenuId() {
        return menuid;
    }
    
    public void setMenuId(int menuid) {
    	this.menuid = menuid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
    	this.count = count;
    }
    
    public int getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(int unitPrice) {
    	this.unitPrice=unitPrice;
    }
    
    public int getOrderid() {
        return orderid;
     }

    
     public void setOrderid(int orderid) {
        this.orderid = orderid;
     }

     public int getMenucount() {
        return menucount;
     }

     public void setMenucount(int menucount) {
        this.menucount = menucount;
     }

     public int getSumprice() {
        return sumprice;
     }

     public void setSumprice(int sumprice) {
        this.sumprice = sumprice;
     }

     public Timestamp getOrdertime() {
        return ordertime;
     }

     public void setOrdertime(Timestamp ordertime) {
        this.ordertime = ordertime;
     }
     
     public Object getMatjipid() {
 		// TODO Auto-generated method stub
 		return matjipid;
 	}
	public void setMatjipid(int matjipid) {
		// TODO Auto-generated method stub
		this.matjipid= matjipid;
	}

	public void setUserid(String userid) {
		// TODO Auto-generated method stub
		
	}

	public Object getUserid() {
		// TODO Auto-generated method stub
		return userid;
	}

	

}
