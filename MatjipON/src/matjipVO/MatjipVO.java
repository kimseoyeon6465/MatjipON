package matjipVO;

import java.sql.Timestamp;

public class MatjipVO {
   
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
   public Timestamp ordertime;//Timestamp로 변경

   //MATJIP_TABLE
   private int matjipid;
   private String matjipname;
   private String location;
   private String category;
   private int busylevel;


   //WATING_TABLE
   private int waitingid;
   private Timestamp inserttime;

   //MENU_TABLE
   private String menuname;
   private int price;
//view
   private int waitingOrder;
   
   public int getWaitingOrder() {
   return waitingOrder;
}

public void setWaitingOrder(int waitingOrder) {
   this.waitingOrder = waitingOrder;
}

public MatjipVO() {}
   
   public MatjipVO(
         String userid, String password, String username, String phonenumber,
         int reviewid, String reviewtext, int rating,
         int orderid, int menucount, int sumprice, Timestamp ordertime,
         int matjipid, String matjipname, String location, String category, int busylevel,
         int waitingid, Timestamp inserttime,
         String menuname, int price) {//busylevel을 int형으로 수정
      
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
      this.busylevel = busylevel;//int형으로 수정
      
      
      this.waitingid = waitingid;
      this.inserttime = inserttime;
      
      
      this.menuname = menuname;
      this.price = price;
            
            
      
   }
   
   public MatjipVO(int waitingid, String userid, int matjipid) {
       this.waitingid = waitingid;
       this.userid = userid;
       this.matjipid = matjipid;
   }
//추가
   
   public MatjipVO(String name) {
      this.matjipname = name;
   // TODO Auto-generated constructor stub
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

   public Timestamp getOrdertime() {
      return ordertime;
   }

   public void setOrdertime(Timestamp ordertime) {
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
 
   //busylevel은 int형

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

   public Timestamp getInserttime()
   {
      return inserttime;
   }
   public void setInserttime(Timestamp inserttime) {
      // TODO Auto-generated method stub
      this.inserttime = inserttime;
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
