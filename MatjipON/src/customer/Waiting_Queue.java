package customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loginUser.Main;
import loginUser.Userinfo;
import matjipDAO.MatjipDAO;
import matjipDAO.WaitingDAO.MatjipSession;
import matjipDAO.WaitingDAO;
import matjipDAO.WaitingDAO.LoginSession;
import matjipDAO.WaitingQueDAO;
import matjipON.Kiosk;
import matjipON.Kiosk.KioskLauncher;
import matjipVO.MatjipVO;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Waiting_Queue extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTextField txtWaitnum;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Waiting_Queue frame = new Waiting_Queue();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
 * @throws IOException 
 * @throws FontFormatException 
    */
  
   public Waiting_Queue() throws FontFormatException, IOException {
	   
	   
	   
	////////////////////폰트 - 더 안전한 resource 불러오기 방법
	///
	InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
	Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);
	
	InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
	Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);



      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 769, 645);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JButton btnNewButton = new JButton("대기번호 확인");  	
      btnNewButton.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      btnNewButton.setFont(customFont2.deriveFont(Font.BOLD, 35));
      // JButton 테두리와 배경 제거
	  btnNewButton.setBorderPainted(false);   // 테두리 안 그림
	  btnNewButton.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
	  btnNewButton.setFocusPainted(false);    // 선택 시 테두리 안 그림
	  btnNewButton.setOpaque(false);    
      btnNewButton.addMouseListener(new MouseAdapter() {

          
         @Override
         public void mouseClicked(MouseEvent e) {
              try {
                  String userId = LoginSession.loginUserId;
                  WaitingQueDAO dao = new WaitingQueDAO();
                  int waitingId = dao.getWaitingIdByUserId(userId);
//                WaitingQueDAO matdao =new WaitingQueDAO();             

                  if (waitingId == 1) {
                	  txtWaitnum.setText("0 0 0 " + waitingId);
                      int option = JOptionPane.showConfirmDialog(null, "지금 입장하시겠습니까?", "입장 안내", JOptionPane.YES_NO_OPTION);
                      if (option == JOptionPane.YES_OPTION) {
                          // 입장 처리 로직
                    	 // dispose();
                    	  
                    	// Kiosk 실행
                    	  KioskLauncher.launchKioskFor(LoginSession.loginUserId, MatjipSession.selectedMatjipId);

                    	  
                    	  
                         
                      }
                  } else if (waitingId > 1) {
                      txtWaitnum.setText("0 0 0 " + waitingId);
                      txtWaitnum.setEditable(false);
                  } else {
                      txtWaitnum.setText("대기 정보가 없습니다.");
                  }
              } catch (Exception ex) {
                  ex.printStackTrace();
                  JOptionPane.showMessageDialog(null, "에러 발생: " + ex.getMessage());
              }
          }
      });
      
      JLabel lblNewLabel_1_1_1 = new JLabel("----------------------------");
      lblNewLabel_1_1_1.setFont(customFont.deriveFont(Font.BOLD, 23));
      lblNewLabel_1_1_1.setBounds(143, 290, 417, 23);
      contentPane.add(lblNewLabel_1_1_1);
      
      JLabel lblNewLabel_1_1 = new JLabel("----------------------------");
      lblNewLabel_1_1.setFont(customFont.deriveFont(Font.BOLD, 23));
      lblNewLabel_1_1.setBounds(143, 221, 417, 23);
      contentPane.add(lblNewLabel_1_1);
      btnNewButton.setFont(customFont2.deriveFont(Font.PLAIN, 30));
      btnNewButton.setBounds(237, 313, 240, 38);
      contentPane.add(btnNewButton);
      
      txtWaitnum = new JTextField();
      txtWaitnum.setHorizontalAlignment(SwingConstants.CENTER);
      txtWaitnum.setBounds(143, 232, 416, 71);
      txtWaitnum.setFont(customFont2.deriveFont(Font.BOLD, 40));
      contentPane.add(txtWaitnum);
      txtWaitnum.setColumns(10);
      
      JPanel panel_1_1_1 = new JPanel();
      panel_1_1_1.setBackground(Color.WHITE);
      panel_1_1_1.setBackground(new Color(240, 240, 240));
      panel_1_1_1.setBounds(137, 110, 427, 18);
      contentPane.add(panel_1_1_1);
      
      JPanel panel = new JPanel();
      panel.setForeground(Color.DARK_GRAY);
      panel.setBackground(Color.DARK_GRAY);
      panel.setBounds(121, 98, 10, 420);
      contentPane.add(panel);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(Color.DARK_GRAY);
      panel_1.setBounds(78, 77, 542, 46);
      contentPane.add(panel_1);
      
      JPanel panel_2 = new JPanel();
      panel_2.setForeground(Color.DARK_GRAY);
      panel_2.setBackground(Color.DARK_GRAY);
      panel_2.setBounds(571, 98, 10, 420);
      contentPane.add(panel_2);
      
      JPanel panel_1_1 = new JPanel();
      panel_1_1.setBackground(Color.DARK_GRAY);
      panel_1_1.setBounds(130, 508, 450, 10);
      contentPane.add(panel_1_1);
      
      JLabel lblNewLabel_2 = new JLabel("Matjip On");
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2.setFont(customFont);
      lblNewLabel_2.setBounds(187, 10, 334, 83);
      contentPane.add(lblNewLabel_2);
      
      JLabel lblNewLabel_1 = new JLabel("대 기 번 호");
      lblNewLabel_1.setFont(customFont2.deriveFont(Font.BOLD, 45));
      lblNewLabel_1.setBounds(224, 133, 297, 89);
      contentPane.add(lblNewLabel_1);
      
      JLabel lblClock = new JLabel("(현재시간)");
      lblClock.setHorizontalAlignment(SwingConstants.CENTER);
      lblClock.setFont(customFont2.deriveFont(Font.PLAIN, 20));
      lblClock.setVerticalAlignment(SwingConstants.TOP);
      lblClock.setBounds(143, 373, 416, 71);
      contentPane.add(lblClock);
      
      JButton btnNewButton_1 = new JButton("웨이팅취소");
      btnNewButton_1.setFont(customFont2.deriveFont(Font.PLAIN, 18));
      
	     // JButton 테두리와 배경 제거
      btnNewButton_1.setBorderPainted(false);   // 테두리 안 그림
      btnNewButton_1.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
      btnNewButton_1.setFocusPainted(false);    // 선택 시 테두리 안 그림
      btnNewButton_1.setOpaque(false);

//      btnNewButton_1.addMouseListener(new MouseAdapter() {
//      	@Override
//      	public void mouseClicked(MouseEvent e) {
//      	}
//      });
      btnNewButton_1.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		
      		
			try {
				MatjipDAO m1 = new MatjipDAO();
				String uid=LoginSession.loginUserId;
	      		m1.cancel(uid);
	      		dispose();
	      		Main mainpage;
				try {
					mainpage = new Main();
					mainpage.setVisible(true);
		      		
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
      		
      		
      		
      	}
      });
      btnNewButton_1.setBounds(384, 427, 148, 71);
      contentPane.add(btnNewButton_1);
      
  	//이미지 조절	
      ImageIcon icon = new  ImageIcon(Userinfo.class.getResource("/img_png/Rabbit01.png"));
      Image img = icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH); //가로, 세로
      JLabel Rabbit = new JLabel(new ImageIcon(img));
      Rabbit.setBounds(491, 427, 90, 65);
      contentPane.add(Rabbit);
      
      JLabel lblNewLabel_4 = new JLabel("");
      lblNewLabel_4.setIcon(new ImageIcon(Waiting_Queue.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_4.setBounds(153, 313, 37, 46);
      contentPane.add(lblNewLabel_4);
      
      JLabel lblNewLabel_1_5 = new JLabel("");
      lblNewLabel_1_5.setIcon(new ImageIcon(Waiting_Queue.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_5.setBounds(190, 313, 37, 46);
      contentPane.add(lblNewLabel_1_5);
      
      JLabel lblNewLabel_5 = new JLabel("");
      lblNewLabel_5.setIcon(new ImageIcon(Waiting_Queue.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_5.setBounds(228, 313, 37, 46);
      contentPane.add(lblNewLabel_5);
      
      JLabel lblNewLabel_1_5_1 = new JLabel("");
      lblNewLabel_1_5_1.setIcon(new ImageIcon(Waiting_Queue.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_5_1.setBounds(463, 313, 37, 46);
      contentPane.add(lblNewLabel_1_5_1);
      
      JLabel lblNewLabel_5_1 = new JLabel("");
      lblNewLabel_5_1.setIcon(new ImageIcon(Waiting_Queue.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_5_1.setBounds(501, 313, 37, 46);
      contentPane.add(lblNewLabel_5_1);
      
      JLabel lblNewLabel_1_6 = new JLabel("");
      lblNewLabel_1_6.setIcon(new ImageIcon(Waiting_Queue.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_6.setBounds(534, 313, 37, 46);
      contentPane.add(lblNewLabel_1_6);
      
		
		LocalDateTime now = LocalDateTime.now();

		// 날짜 포맷: "2025년 04월 20일"
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일", Locale.KOREAN);
		// 시간 포맷: "오전/오후 02시 57분"
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("a hh시 mm분", Locale.KOREAN);

		Timer timer = new Timer(1000, (ActionListener) new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        LocalDateTime now = LocalDateTime.now();
		        String formattedDateTime = "<html>" + now.format(dateFormatter) + "<br>" + now.format(timeFormatter) + "</html>";
		        lblClock.setText(formattedDateTime);
		    }
		});
		timer.start(); // 타이머 시작
      
      
      
      
   }
}
