package customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loginUser.Login_main;
import loginUser.Userinfo;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.Icon;

public class Select extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Select frame = new Select();
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
   public Select() throws FontFormatException, IOException {
      
      
	   
////////////////////더 안전한 resource 불러오기 방법
InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);

InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);	



      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 700, 700);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);

  
      
      

      JButton btnNewButton = new JButton("맛집 추천");
      btnNewButton.setIcon(new ImageIcon(Select.class.getResource("/img_png/iconmonstr-eat-8-96.png")));
      // JButton 테두리와 배경 제거
      // 텍스트 위치 조정
      btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER); // 수평 중앙
      btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);   // 아이콘 아래 텍스트
      btnNewButton.setFont(customFont2.deriveFont(Font.BOLD, 18));
      btnNewButton.setBorderPainted(false);   // 테두리 안 그림
      btnNewButton.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
      btnNewButton.setFocusPainted(false);    // 선택 시 테두리 안 그림
      btnNewButton.setOpaque(false);
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      btnNewButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
                   // 새로운 JFrame을 띄운다
            
                 Recommendation w1 = null;
				try {
					w1 = new Recommendation();
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
                 w1.setVisible(true);  // 창을 보이게 함      
               setVisible(false);
            
         }
      });
      

      btnNewButton.setBounds(140, 332, 175, 181);
      contentPane.add(btnNewButton);
      
      JButton btnNewButton_1 = new JButton("맛집 검색");
      btnNewButton_1.setFont(customFont2.deriveFont(Font.BOLD, 18));
      btnNewButton_1.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      btnNewButton_1.setIcon(new ImageIcon(Select.class.getResource("/img_png/iconmonstr-magnifier-dots-lined-96.png")));
      
      
   // 텍스트 위치 조정
      btnNewButton_1.setHorizontalTextPosition(SwingConstants.CENTER); // 수평 중앙
      btnNewButton_1.setVerticalTextPosition(SwingConstants.BOTTOM);   // 아이콘 아래 텍스트

      
      // JButton 테두리와 배경 제거
      btnNewButton_1.setBorderPainted(false);   // 테두리 안 그림
      btnNewButton_1.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
      btnNewButton_1.setFocusPainted(false);    // 선택 시 테두리 안 그림
      btnNewButton_1.setOpaque(false);
      btnNewButton_1.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            Select_List l1 = null;
            
            
            try {
				l1 = new Select_List();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
           
            l1.setVisible(true);
            setVisible(false);
            
         }
      });
      btnNewButton_1.setBounds(338, 332, 175, 181);
      contentPane.add(btnNewButton_1);
      
      
		
		
		//이미지 조절	
		ImageIcon icon = new  ImageIcon(Select.class.getResource("/img_png/Rabbit01.png"));
        Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH); //가로, 세로
        JLabel Rabbit = new JLabel(new ImageIcon(img));
        Rabbit.setOpaque(false);              // 완전 투명 처리
        
		Rabbit.setBounds(211, 157, 213, 224);
		contentPane.add(Rabbit);

      
		
      
      JButton btnBack = new JButton("");
      btnBack.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		
      		Login_main d1 = null;
			try {
				d1 = new Login_main();
			} catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException | FontFormatException
					| IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            d1.setVisible(true);
            setVisible(false);

      		
      	}
      });
      btnBack.setOpaque(false);
      btnBack.setIcon(new ImageIcon(Select.class.getResource("/img_png/iconmonstr-arrow-left-alt-filled-64.png")));
      btnBack.setFocusPainted(false);
      btnBack.setContentAreaFilled(false);
      btnBack.setBorderPainted(false);
      btnBack.setBounds(31, 21, 95, 60);
      contentPane.add(btnBack);
      
      JPanel panel_1_1_1 = new JPanel();
      panel_1_1_1.setBackground(Color.WHITE);
      panel_1_1_1.setBounds(115, 129, 416, 18);
      contentPane.add(panel_1_1_1);
      
      JLabel lblNewLabel_2 = new JLabel("Matjip On");
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2.setFont(new Font("Receipt Demo", Font.BOLD, 45));
      lblNewLabel_2.setBounds(156, 10, 334, 83);
      contentPane.add(lblNewLabel_2);
      
      JPanel panel = new JPanel();
      panel.setForeground(Color.DARK_GRAY);
      panel.setBackground(Color.DARK_GRAY);
      panel.setBounds(93, 113, 10, 420);
      contentPane.add(panel);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(Color.DARK_GRAY);
      panel_1.setBounds(50, 92, 542, 46);
      contentPane.add(panel_1);
      
      JPanel panel_2 = new JPanel();
      panel_2.setForeground(Color.DARK_GRAY);
      panel_2.setBackground(Color.DARK_GRAY);
      panel_2.setBounds(543, 113, 10, 420);
      contentPane.add(panel_2);
      
      JPanel panel_1_1 = new JPanel();
      panel_1_1.setBackground(Color.DARK_GRAY);
      panel_1_1.setBounds(102, 523, 450, 10);
      contentPane.add(panel_1_1);
      
   
   }
}
