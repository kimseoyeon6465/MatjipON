package matjipVO;

public class WaitingVO {
   
   //USER_TABLE
   private String userid;
   private String password;
   private String username;
   private String phonenumber;

   //REVIEW_TABLE
   private int reviewid;
   private String reviewtext;
   private int rating;;

   //ORDER_TABLE
   private int orderid;
   private int menucount;
   private int sumprice;
   private String ordertime;

   //MATJIP_TABLE
   private int matjipid;
   private String matjipname;
   private String location;
   private String category;
   private int busylevel;
   private int matjipId;
   //WATING_TABLE
   private int waitingid;

   //MENU_TABLE
   private String menuname;
   private int price;
   
   public WaitingVO() {}
   
   public WaitingVO(
         String userid, String password, String username, String phonenumber,
         int reviewid, String reviewtext, int rating,
         int orderid, int menucount, int sumprice, String ordertime,
         int matjipid, String matjipname, String location, String category, int busylevel,
         int waitingid, 
         String menuname, int price) {
      
      //인자 있는 생성자로 만듬
      
      super();
      this.userid = userid;
      this.password = password;
      this.username = username;
      this.phonenumber = phonenumber;
      
      
      this.reviewid = reviewid;
      this.reviewtext = reviewtext;
      this.rating = rating;
      
      
      this.orderid = orderid;;
      this.menucount = menucount;
      this.sumprice = sumprice;
      this.ordertime = ordertime;
      
      
      this.matjipid = matjipid;
      this.matjipname = matjipname;
      this.location = location;
      this.category = category;
      this.busylevel = busylevel;
      
      
      this.waitingid = waitingid;
      
      
      this.menuname = menuname;
      this.price = price;
            
            
      
   }
   
   

   public WaitingVO(String name) {
      // TODO Auto-generated constructor stub
        this.matjipname = name;
        
   }
   public WaitingVO(int matjipid, String matjipname) {
	      this.matjipid = matjipid;
	        this.matjipname = matjipname;
	   }
   public WaitingVO(int id ,String name, int busy) {
   // TODO Auto-generated constructor stub
      this.matjipid = id;
     this.matjipname = name;
     this.busylevel = busy;
}
   public int getMatjipId() {
       return matjipId;
   }
   public void setMatjipId(int matjipId) {
       this.matjipId = matjipId;
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

   public int getReviewid() {
      return reviewid;
   }

   public void setReviewid(int reviewid) {
      this.reviewid = reviewid;
   }

   public String getReviewtext() {
      return reviewtext;
   }

   public void setReviewtext(String reviewtext) {
      this.reviewtext = reviewtext;
   }

   public int getRating() {
      return rating;
   }

   public void setRating(int rating) {
      this.rating = rating;
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

   public String getOrdertime() {
      return ordertime;
   }

   public void setOrdertime(String ordertime) {
      this.ordertime = ordertime;
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

   public int getWaitingid() {
      return waitingid;
   }

   public void setWaitingid(int waitingid) {
      this.waitingid = waitingid;
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

   public class MatjipVO {
       private String matjipName;
       private String location;
       private String category;
       private int busyLevel;

       // Getters & Setters
       public String getMatjipName() {
           return matjipName;
       }

       public void setMatjipName(String matjipName) {
           this.matjipName = matjipName;
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

       public int getBusyLevel() {
           return busyLevel;
       }

       public void setBusyLevel(int busyLevel) {
           this.busyLevel = busyLevel;
       }
   }
}
