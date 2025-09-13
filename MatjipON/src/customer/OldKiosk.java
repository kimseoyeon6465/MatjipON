package customer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.*;

import customer.OrderDAO;//import
//import matjipVO.MatjipVO;
import matjipVO.OrderVO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class OldKiosk extends JFrame {
	private int menuSet=8;//메뉴 갯수 8개
	private Connection con; 
   PreparedStatement pstmt=null;
   ResultSet rs=null;
   private JPanel contentPane;
   public int neworderid;
   private JTextField count1, count2, count3, count4, count5, count6, count7, count8;


   private JTextField price1, price2, price3, price4, price5, price6, price7, price8;
   private final int price = 1000; // 모든 메뉴의 가격은 1000원
   //추가
   int[] menuIds = {1, 2, 3, 4, 5, 6, 7, 8}; // 메뉴 ID
   int[] qty = new int [menuSet];//해당 메뉴의 주문 수량을 저장하는 배열 
//   JTextField[] countFields = {count1, count2, count3, count4, count5, count6, count7, count8}; // 수량 필드
   JTextField[] countFields = new JTextField[menuSet];
   

   JTextField[] priceFields = new JTextField[menuSet];

   private JTextField textField_7;
   
   public static String[] menuNames = {
	        "비빔밥", "김치", "불고기", "김치찌개", "삼겹살", "떡볶이", "시차진", "반찬"
	    };
   private int orderid = 1000;

   public static void main(String[] args){
	   
	   
      EventQueue.invokeLater(() -> {
         try {
            OldKiosk frame = new OldKiosk();
            frame.setVisible(true);
         } catch (Exception e) {
            e.printStackTrace();
         }
      });
   }

   public OldKiosk() {
//	  String[] imagePaths = KioskImage.getImagePaths(1);//kioskimage.java에서 return한 경로배열
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 953, 731);
      contentPane = new JPanel();
      contentPane.setBackground(new Color(255, 255, 255));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      //수량 필드 초기화
      for (int i = 0; i < menuSet; i++) {
          countFields[i] = new JTextField();
          countFields[i].setText("0");
      }
      
      //가격 필드 초기화

      for(int i=0;i<menuSet;i++)
      {
    	  priceFields[i] = new JTextField();
    	  priceFields[i].setText("0");
      }
      
      // 메뉴1
      JButton Menu1 = new JButton(menuNames[0]);
      Menu1.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      Menu1.setHorizontalTextPosition(SwingConstants.CENTER);
      Menu1.setVerticalTextPosition(SwingConstants.TOP);
      Menu1.setForeground(Color.BLACK);
      Menu1.setFont(new Font("나눔 고딕", Font.BOLD, 18));
      Menu1.setBounds(52, 94, 185, 112);
//      Menu1.setIcon(new ImageIcon(imagePaths[0]));
      Menu1.setVerticalAlignment(SwingConstants.TOP);
      Menu1.setContentAreaFilled(false); // 배경 채우기 제거
      Menu1.setIconTextGap(-30);
      Menu1.setMargin(new Insets(0, 0, 215, 0));
      contentPane.add(Menu1);

      count1 = new JTextField("0");
      count1.setBounds(116, 239, 36, 30);
      count1.setHorizontalAlignment(SwingConstants.CENTER);
      count1.setEditable(false);
      contentPane.add(count1);

      JButton minus1 = new JButton("-");
      minus1.setBounds(52, 238, 52, 30);
      contentPane.add(minus1);

      JButton plus1 = new JButton("+");
      plus1.setBounds(164, 238, 52, 30);
      contentPane.add(plus1);

      // 메뉴2
      JButton Menu2 = new JButton("고등어구이");
      Menu2.setHorizontalTextPosition(SwingConstants.CENTER);
      Menu2.setVerticalTextPosition(SwingConstants.TOP);
      Menu2.setForeground(Color.BLACK);
      Menu2.setFont(new Font("나눔 고딕", Font.BOLD,18));
      Menu2.setBounds(249, 94, 185, 112);
//      Menu2.setIcon(new ImageIcon(imagePaths[1])); // 여기서 seticon 함
      Menu2.setVerticalAlignment(SwingConstants.TOP);
      Menu2.setContentAreaFilled(false); // 배경 채우기 제거
      Menu2.setIconTextGap(-30);
      Menu2.setMargin(new Insets(0, 0, 215, 0));
      contentPane.add(Menu2);

      count2 = new JTextField("0");
      count2.setBounds(334, 239, 36, 30);
      count2.setHorizontalAlignment(SwingConstants.CENTER);
      count2.setEditable(false);
      contentPane.add(count2);

      JButton minus2 = new JButton("-");
      minus2.setBounds(270, 238, 52, 31);
      contentPane.add(minus2);

      JButton plus2 = new JButton("+");
      plus2.setBounds(382, 238, 52, 31);
      contentPane.add(plus2);

      // 메뉴3
      JButton Menu3 = new JButton(menuNames[2]);
      Menu3.setHorizontalTextPosition(SwingConstants.CENTER);
      Menu3.setVerticalTextPosition(SwingConstants.TOP);
      Menu3.setForeground(Color.BLACK);
      Menu3.setFont(new Font("나눔 고딕", Font.BOLD, 18));
      Menu3.setBounds(473, 94, 185, 112);
//      Menu3.setIcon(new ImageIcon(imagePaths[2]));
      Menu3.setVerticalAlignment(SwingConstants.TOP);
      Menu3.setContentAreaFilled(false); // 배경 채우기 제거
      Menu3.setIconTextGap(-30);
      Menu3.setMargin(new Insets(0, 0, 215, 0));
      contentPane.add(Menu3);

      count3 = new JTextField("0");
      count3.setBounds(554, 239, 36, 30);
      count3.setHorizontalAlignment(SwingConstants.CENTER);
      count3.setEditable(false);
      contentPane.add(count3);

      JButton minus3 = new JButton("-");
      minus3.setBounds(494, 238, 52, 30);
      contentPane.add(minus3);

      JButton plus3 = new JButton("+");
      plus3.setBounds(602, 238, 52, 30);
      contentPane.add(plus3);

      // 메뉴4
      JButton Menu4 = new JButton(menuNames[3]);
      Menu4.setHorizontalTextPosition(SwingConstants.CENTER);
      Menu4.setVerticalTextPosition(SwingConstants.TOP);
      Menu4.setForeground(Color.BLACK);
      Menu4.setFont(new Font("나눔 고딕", Font.BOLD, 18));
      Menu4.setBounds(688, 94, 185, 112);
//      Menu4.setIcon(new ImageIcon(imagePaths[3]));
      Menu4.setVerticalAlignment(SwingConstants.TOP);
      Menu4.setContentAreaFilled(false); // 배경 채우기 제거
      Menu4.setIconTextGap(-30);
      Menu4.setMargin(new Insets(0, 0, 215, 0));
      contentPane.add(Menu4);

      count4 = new JTextField("0");
      count4.setBounds(769, 239, 40, 30);
      count4.setHorizontalAlignment(SwingConstants.CENTER);
      count4.setEditable(false);
      contentPane.add(count4);

      JButton minus4 = new JButton("-");
      minus4.setBounds(705, 238, 52, 30);
      contentPane.add(minus4);

      JButton plus4 = new JButton("+");
      plus4.setBounds(821, 238, 52, 30);
      contentPane.add(plus4);

      // 메뉴5
      JButton Menu5 = new JButton(menuNames[4]);
      Menu5.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      Menu5.setHorizontalTextPosition(SwingConstants.CENTER);
      Menu5.setVerticalTextPosition(SwingConstants.TOP);
      Menu5.setForeground(Color.BLACK);
      Menu5.setFont(new Font("나눔 고딕", Font.BOLD, 18));
      Menu5.setBounds(52, 310, 185, 112);
//      Menu5.setIcon(new ImageIcon(imagePaths[4]));
      Menu5.setVerticalAlignment(SwingConstants.TOP);
      Menu5.setContentAreaFilled(false); // 배경 채우기 제거
      Menu5.setIconTextGap(-30);
      Menu5.setMargin(new Insets(0, 0, 215, 0));
      contentPane.add(Menu5);

      count5 = new JTextField("0");
      count5.setBounds(116, 458, 36, 30);
      count5.setHorizontalAlignment(SwingConstants.CENTER);
      count5.setEditable(false);
      contentPane.add(count5);

      JButton minus5 = new JButton("-");
      minus5.setBounds(52, 457, 52, 30);
      contentPane.add(minus5);

      JButton plus5 = new JButton("+");
      plus5.setBounds(164, 457, 52, 30);
      contentPane.add(plus5);

      // 메뉴6
      JButton Menu6 = new JButton(menuNames[5]);
      Menu6.setHorizontalTextPosition(SwingConstants.CENTER);
      Menu6.setVerticalTextPosition(SwingConstants.TOP);
      Menu6.setForeground(Color.BLACK);
      Menu6.setFont(new Font("나눔 고딕", Font.BOLD, 18));
      Menu6.setBounds(249, 310, 185, 112);
//      Menu6.setIcon(new ImageIcon(imagePaths[5]));
      Menu6.setVerticalAlignment(SwingConstants.TOP);
      Menu6.setContentAreaFilled(false); // 배경 채우기 제거
      Menu6.setIconTextGap(-30);
      Menu6.setMargin(new Insets(0, 0, 215, 0));
      contentPane.add(Menu6);

      count6 = new JTextField("0");
      count6.setBounds(334, 458, 36, 30);
      count6.setHorizontalAlignment(SwingConstants.CENTER);
      count6.setEditable(false);
      contentPane.add(count6);

      JButton minus6 = new JButton("-");
      minus6.setBounds(270, 457, 52, 30);
      contentPane.add(minus6);

      JButton plus6 = new JButton("+");
      plus6.setBounds(382, 457, 52, 30);
      contentPane.add(plus6);
      
      // 메뉴7
      JButton Menu7 = new JButton("된장찌개");
      Menu7.setHorizontalTextPosition(SwingConstants.CENTER);
      Menu7.setVerticalTextPosition(SwingConstants.TOP);
      Menu7.setForeground(Color.BLACK);
      Menu7.setFont(new Font("나눔 고딕", Font.BOLD, 18));
      Menu7.setBounds(473, 310, 185, 112);
//      Menu7.setIcon(new ImageIcon(imagePaths[6]));
      Menu7.setVerticalAlignment(SwingConstants.TOP);
      Menu7.setContentAreaFilled(false); // 배경 채우기 제거
      Menu7.setIconTextGap(-30);
      Menu7.setMargin(new Insets(0, 0, 215, 0));
      contentPane.add(Menu7);
      
      count7 = new JTextField("0");
      count7.setBounds(562, 462, 36, 30);
      count7.setHorizontalAlignment(SwingConstants.CENTER);
      count7.setEditable(false);
      contentPane.add(count7);
      
      JButton minus7 = new JButton("-");
      minus7.setBounds(494, 461, 52, 30);
      contentPane.add(minus7);
      
      JButton plus7 = new JButton("+");
      plus7.setBounds(610, 461, 52, 30);
      contentPane.add(plus7);
      
      // 메뉴8
      JButton Menu8 = new JButton("모둠전");
      Menu8.setHorizontalTextPosition(SwingConstants.CENTER);
      Menu8.setVerticalTextPosition(SwingConstants.TOP);
      Menu8.setForeground(Color.BLACK);
      Menu8.setFont(new Font("나눔 고딕", Font.BOLD, 18));
      Menu8.setBounds(688, 310, 185, 112);
//      Menu8.setIcon(new ImageIcon(imagePaths[7]));
      Menu8.setVerticalAlignment(SwingConstants.TOP);
      Menu8.setContentAreaFilled(false); // 배경 채우기 제거
      Menu8.setIconTextGap(-30);
      Menu8.setMargin(new Insets(0, 0, 215, 0));
      contentPane.add(Menu8);
      
      count8 = new JTextField("0");
      count8.setBounds(752, 458, 36, 30);
      count8.setHorizontalAlignment(SwingConstants.CENTER);
      count8.setEditable(false);
      contentPane.add(count8);
      
      JButton minus8 = new JButton("-");
      minus8.setBounds(688, 457, 52, 30);
      contentPane.add(minus8);
      
      JButton plus8 = new JButton("+");
      plus8.setBounds(804, 457, 52, 30);
      contentPane.add(plus8);
      
      countFields[0] = count1;
      countFields[1] = count2;
      countFields[2] = count3;
      countFields[3] = count4;
      countFields[4] = count5;
      countFields[5] = count6;
      countFields[6] = count7;
      countFields[7] = count8;

      // 버튼 이벤트 등록
      setPlusMinusActions(plus1, minus1, count1);
      setPlusMinusActions(plus2, minus2, count2);
      setPlusMinusActions(plus3, minus3, count3);
      setPlusMinusActions(plus4, minus4, count4);
      setPlusMinusActions(plus5, minus5, count5);
      setPlusMinusActions(plus6, minus6, count6);
      setPlusMinusActions(plus7, minus7, count7);
      setPlusMinusActions(plus8, minus8, count8);
      
      price1 = new JTextField();
      price1.setBounds(52, 204, 185, 25);
      price1.setText("                              가격");
      contentPane.add(price1);
      price1.setColumns(10);
      
      price2 = new JTextField();
      price2.setBounds(249, 205, 185, 25);
      price2.setText("                              가격");
      price2.setColumns(10);
      contentPane.add(price2);
      
      price3 = new JTextField();
      price3.setBounds(473, 205, 185, 25);
      price3.setText("                              가격");
      price3.setColumns(10);
      contentPane.add(price3);
      
      price4 = new JTextField();
      price4.setBounds(688, 204, 185, 25);
      price4.setText("                              가격");
      price4.setHorizontalAlignment(SwingConstants.LEFT);
      price4.setColumns(10);
      contentPane.add(price4);
      
      price5 = new JTextField();
      price5.setBounds(52, 422, 185, 25);
      price5.setText("                               가격");
      price5.setHorizontalAlignment(SwingConstants.LEFT);
      price5.setColumns(10);
      contentPane.add(price5);
      
      price6 = new JTextField();
      price6.setBounds(249, 423, 185, 25);
      price6.setText("                              가격");
      price6.setHorizontalAlignment(SwingConstants.LEFT);
      price6.setColumns(10);
      contentPane.add(price6);
      
      price7 = new JTextField();
      price7.setBounds(473, 423, 185, 25);
      price7.setText("                              가격");
      price7.setHorizontalAlignment(SwingConstants.LEFT);
      price7.setColumns(10);
      contentPane.add(price7);
      
      price8 = new JTextField();
      price8.setBounds(688, 423, 185, 25);
      price8.setText("                              가격");
      price8.setHorizontalAlignment(SwingConstants.LEFT);
      price8.setColumns(10);
      contentPane.add(price8);

      
      JButton Yorder = new JButton("주문확인");
      Yorder.setBounds(473, 542, 98, 77);
      contentPane.add(Yorder);
      Yorder.addActionListener(e -> {

          
          for(int i=0;i<menuSet;i++)
          {
        	  qty[i]=Integer.parseInt(countFields[i].getText());
          }
      });

      
      JButton Norder = new JButton("주문취소");
      Norder.setBounds(210, 542, 97, 77);
      Norder.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Norder.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  for(int i=0;i<menuSet;i++)
                  {
                	  countFields[i].setText("0");
                  }

                  // 가격 필드도 원래 텍스트로 초기화 (선택 사항)
                  price1.setText("                              가격");
                  price2.setText("                              가격");
                  price3.setText("                              가격");
                  price4.setText("                              가격");
                  price5.setText("                              가격");
                  price6.setText("                              가격");
                  price7.setText("                              가격");
                  price8.setText("                              가격");

                  // 사용자에게 알림
                  JOptionPane.showMessageDialog(null, "주문이 모두 취소되었습니다.", "취소 완료", JOptionPane.INFORMATION_MESSAGE);
               }
         });
          // 주문 취소 로직
      }
});
      contentPane.add(Norder);
      
      JLabel lblNewLabel_2 = new JLabel("Matjip On");
      lblNewLabel_2.setForeground(new Color(255, 128, 0));
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2.setFont(new Font("Receipt Demo", Font.BOLD, 45));
      lblNewLabel_2.setBounds(300, 0, 334, 83);
      contentPane.add(lblNewLabel_2);
      
      JPanel panel = new JPanel();
      panel.setForeground(Color.DARK_GRAY);
      panel.setBackground(Color.DARK_GRAY);
      panel.setBounds(32, 57, 10, 582);
      contentPane.add(panel);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(Color.DARK_GRAY);
      panel_1.setBounds(29, 14, 293, 46);
      contentPane.add(panel_1);
      
      JPanel panel_2 = new JPanel();
      panel_2.setForeground(Color.DARK_GRAY);
      panel_2.setBackground(Color.DARK_GRAY);
      panel_2.setBounds(898, 57, 10, 582);
      contentPane.add(panel_2);
      
      JPanel panel_1_1 = new JPanel();
      panel_1_1.setBackground(Color.DARK_GRAY);
      panel_1_1.setBounds(43, 629, 865, 10);
      contentPane.add(panel_1_1);
      
      JPanel panel_1_2 = new JPanel();
      panel_1_2.setBackground(Color.DARK_GRAY);
      panel_1_2.setBounds(615, 14, 293, 46);
      contentPane.add(panel_1_2);
      
      JButton btnBack = new JButton("");
      btnBack.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		
      		
      		Select d1 = null;
			try {
				d1 = new Select();
			} catch (FontFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            d1.setVisible(true);
            setVisible(false);

      		
      		
      		
      	}
      });
      btnBack.setOpaque(false);
      btnBack.setIcon(new ImageIcon(OldKiosk.class.getResource("/img_png/iconmonstr-arrow-left-alt-filled-64.png")));
      btnBack.setFocusPainted(false);
      btnBack.setContentAreaFilled(false);
      btnBack.setBorderPainted(false);
      btnBack.setBounds(79, 542, 95, 60);
      contentPane.add(btnBack);
      
 

      // 주문 확인 버튼 클릭 시
      Yorder.addActionListener(e -> {
         try {
			showOrderSummary();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
      });
   }

   private void setPlusMinusActions(JButton plus, JButton minus, JTextField field) {
      plus.addActionListener(e -> {
         int value = Integer.parseInt(field.getText());
         field.setText(String.valueOf(value + 1));
      });

      minus.addActionListener(e -> {
         int value = Integer.parseInt(field.getText());
         if (value > 0) field.setText(String.valueOf(value - 1));
      });
   }
   
   public int setOrderIdFrom1000() {
       orderid++;  // 기존 orderid에서 1 증가
       return orderid;
   }
   

   // 주문 내역을 팝업으로 보여주는 함수
   private void showOrderSummary() throws SQLException, ClassNotFoundException, FontFormatException, IOException {
      int total = 0;
      StringBuilder sb = new StringBuilder();

      sb.append("===== 주문 내역 =====\n");

      
      //print
      for(int i=0;i<menuSet;i++)
      {
    	  qty[i]=Integer.parseInt(countFields[i].getText());
    	  if (qty[i] > 0) {
    	         sb.append("메뉴"+i+ ": " + qty[i] + "개 (합계: " + qty[i] * price + "원)\n");
    	         total += qty[i]* price;
    	         
    	      }
//    	  System.out.println(total);
      }


      // 총 합계
      sb.append("\n총 합계: " + total + "원");
        // MatjipON m1 = new MatjipON(int total);
      Timestamp now = new Timestamp(System.currentTimeMillis());
      OrderDAO ordao = new OrderDAO();
//      ordao.insertOrder(1201, "user1", 101, total, now);
      //아래는 테스트 코드, 이 자리에 인자 넣어서 호출만 하면 됨.
      neworderid =ordao.setOrderId1000();
      
      OrderVO[] orders = new OrderVO[8];

   // 메뉴 ID 1~8번까지 각각 주문 수량과 단가를 지정
   for (int i = 0; i < orders.length; i++) {
       int count = Integer.parseInt(countFields[i].getText()); // 사용자가 입력한 수량
       if (count > 0) {
           orders[i] = new OrderVO();
           orders[i].setOrderid(neworderid);
           orders[i].setMenuId(i + 1);       // 메뉴 ID는 1부터 시작한다고 가정
           orders[i].setCount(count);
           orders[i].setUnitPrice(1000);     // 단가 고정
       }
   }

   // List로 변환 (null 값 제외)
   List<OrderVO> orderList = new ArrayList<>();
   for (OrderVO o : orders) {
       if (o != null) {
           orderList.add(o);
       }
   }
   OrderVO vo = new OrderVO();
	  ordao.insertOrder(neworderid, (String)vo.getUserid(), (int)vo.getMatjipid(), total, now);//여기에 user4자리에 userloginsession??, 101자리에 matjipidsession??
   ordao.insertOrderDetails(neworderid, orderList);

      // 주문 내역 팝업 표시
      

      //matdao.insertOrder(total);
      JOptionPane.showMessageDialog(this, sb.toString(), "주문 내역", JOptionPane.INFORMATION_MESSAGE);
      JOptionPane.showMessageDialog(null, "주문이 완료되었습니다!");
      ShowOrder show1 = new ShowOrder();
      show1.setVisible(true);
      

      dispose();
      
      
      
      
            
   }
   
   
}
