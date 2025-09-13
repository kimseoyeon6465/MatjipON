package customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;

public class Recommendation extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Recommendation frame = new Recommendation();
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
   public Recommendation() throws FontFormatException, IOException {
	   
////////////////////더 안전한 resource 불러오기 방법
InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);

InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);	
	   
	   
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 691, 703);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);
      

      
		ImageIcon icon = new  ImageIcon(Recommendation.class.getResource("/img_png/hourglass2.png"));
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH); //가로, 세로
        JButton btnNewButton = new JButton(new ImageIcon(img));
        btnNewButton.setBorderPainted(false);   // 테두리 안 그림
        btnNewButton.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
        btnNewButton.setFocusPainted(false);    // 선택 시 테두리 안 그림
        btnNewButton.setOpaque(false);
        btnNewButton.setBounds(266, 316, 185, 160);
		
      btnNewButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            customer.Waiting_List w2 = null;
            //customer.Waiting_Queue w3 = null;
            try {
               w2 = new Waiting_List();
            } catch (Exception e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
                w2.setVisible(true);  // 창을 보이게 함      
               setVisible(false);
         }
      });
      btnNewButton.setBounds(151, 218, 172, 160);
      contentPane.add(btnNewButton);
      
      JButton btnTop = new JButton("");
      btnTop.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      btnTop.setHorizontalTextPosition(SwingConstants.CENTER); // 수평 중앙
      btnTop.setVerticalTextPosition(SwingConstants.BOTTOM);   // 아이콘 아래 텍스트
      btnTop.setFont(customFont.deriveFont(Font.BOLD, 15)); //커스텀 폰트 사용
	     // JButton 테두리와 배경 제거
      btnTop.setBorderPainted(false);   // 테두리 안 그림
      btnTop.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
      btnTop.setFocusPainted(false);    // 선택 시 테두리 안 그림
      btnTop.setOpaque(false);
      btnTop.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-medal-16-120.png")));
      btnTop.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            customer.Pupular_List w = null;
            //customer.Waiting_Queue w3 = null;
            try {
               w = new Pupular_List();
            } catch (Exception e1) {
               e1.printStackTrace();
            } 
                w.setVisible(true);  // 창을 보이게 함      
               setVisible(false);
         
         }
      });
      btnTop.setBounds(335, 206, 172, 168);
      contentPane.add(btnTop);
      
      JButton btnBack = new JButton("");
      btnBack.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		
      		Select d1 = null;
			try {
				d1 = new Select();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
            d1.setVisible(true);
            setVisible(false);

      		
      		
      	}
      });
      btnBack.setOpaque(false);
      btnBack.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-arrow-left-alt-filled-64.png")));
      btnBack.setFocusPainted(false);
      btnBack.setContentAreaFilled(false);
      btnBack.setBorderPainted(false);
      btnBack.setBounds(708, 47, 95, 60);
      contentPane.add(btnBack);
      
      JLabel lblNewLabel_2 = new JLabel("Matjip On");
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2.setFont(customFont); //커스텀 폰트 사용
      lblNewLabel_2.setBounds(151, 10, 334, 83);
      contentPane.add(lblNewLabel_2);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(Color.DARK_GRAY);
      panel_1.setBounds(49, 82, 542, 46);
      contentPane.add(panel_1);
      
      JPanel panel_1_1_1 = new JPanel();
      panel_1_1_1.setBackground(UIManager.getColor("Button.background"));
      panel_1_1_1.setBounds(108, 115, 427, 18);
      contentPane.add(panel_1_1_1);
      
      JPanel panel = new JPanel();
      panel.setForeground(Color.DARK_GRAY);
      panel.setBackground(Color.DARK_GRAY);
      panel.setBounds(92, 103, 10, 420);
      contentPane.add(panel);
      
      JPanel panel_1_1 = new JPanel();
      panel_1_1.setBackground(Color.DARK_GRAY);
      panel_1_1.setBounds(101, 513, 450, 10);
      contentPane.add(panel_1_1);
      
      JPanel panel_2 = new JPanel();
      panel_2.setForeground(Color.DARK_GRAY);
      panel_2.setBackground(Color.DARK_GRAY);
      panel_2.setBounds(542, 103, 10, 420);
      contentPane.add(panel_2);
      
      JTextArea txtrMatjipTopRanking = new JTextArea();
      txtrMatjipTopRanking.setText("   MATJIP\r\nTOP RANKING");
      txtrMatjipTopRanking.setFont(customFont.deriveFont(Font.BOLD, 20));
      txtrMatjipTopRanking.setBounds(356, 374, 150, 65);
      contentPane.add(txtrMatjipTopRanking);
      
      JTextArea txtrMatjipTopRanking_1 = new JTextArea();
      txtrMatjipTopRanking_1.setText("NO WAITING");
      txtrMatjipTopRanking_1.setFont(customFont.deriveFont(Font.BOLD, 20));
      txtrMatjipTopRanking_1.setBounds(173, 388, 150, 65);
      contentPane.add(txtrMatjipTopRanking_1);
      
      JLabel lblNewLabel = new JLabel("");
      lblNewLabel.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel.setBounds(108, 138, 37, 46);
      contentPane.add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("");
      lblNewLabel_1.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1.setBounds(108, 191, 37, 46);
      contentPane.add(lblNewLabel_1);
      
      JLabel lblNewLabel_3 = new JLabel("");
      lblNewLabel_3.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_3.setBounds(108, 233, 37, 46);
      contentPane.add(lblNewLabel_3);
      
      JLabel lblNewLabel_1_1 = new JLabel("");
      lblNewLabel_1_1.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_1.setBounds(108, 281, 37, 46);
      contentPane.add(lblNewLabel_1_1);
      
      JLabel lblNewLabel_1_2 = new JLabel("");
      lblNewLabel_1_2.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_2.setBounds(108, 381, 37, 46);
      contentPane.add(lblNewLabel_1_2);
      
      JLabel lblNewLabel_1_3 = new JLabel("");
      lblNewLabel_1_3.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_3.setBounds(108, 466, 37, 46);
      contentPane.add(lblNewLabel_1_3);
      
      JLabel lblNewLabel_3_1 = new JLabel("");
      lblNewLabel_3_1.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_3_1.setBounds(108, 332, 37, 46);
      contentPane.add(lblNewLabel_3_1);
      
      JLabel lblNewLabel_3_2 = new JLabel("");
      lblNewLabel_3_2.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_3_2.setBounds(108, 421, 37, 46);
      contentPane.add(lblNewLabel_3_2);
      
      JLabel lblNewLabel_1_4 = new JLabel("");
      lblNewLabel_1_4.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_4.setBounds(151, 138, 37, 46);
      contentPane.add(lblNewLabel_1_4);
      
      JLabel lblNewLabel_1_5 = new JLabel("");
      lblNewLabel_1_5.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_5.setBounds(223, 138, 37, 46);
      contentPane.add(lblNewLabel_1_5);
      
      JLabel lblNewLabel_1_6 = new JLabel("");
      lblNewLabel_1_6.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_6.setBounds(302, 138, 37, 46);
      contentPane.add(lblNewLabel_1_6);
      
      JLabel lblNewLabel_1_7 = new JLabel("");
      lblNewLabel_1_7.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1_7.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_7.setBounds(498, 191, 37, 46);
      contentPane.add(lblNewLabel_1_7);
      
      JLabel lblNewLabel_4 = new JLabel("");
      lblNewLabel_4.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_4.setBounds(186, 138, 37, 46);
      contentPane.add(lblNewLabel_4);
      
      JLabel lblNewLabel_5 = new JLabel("");
      lblNewLabel_5.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_5.setBounds(261, 138, 37, 46);
      contentPane.add(lblNewLabel_5);
      
      JLabel lblNewLabel_6 = new JLabel("");
      lblNewLabel_6.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_6.setBounds(343, 138, 37, 46);
      contentPane.add(lblNewLabel_6);
      
      JLabel lblNewLabel_7 = new JLabel("");
      lblNewLabel_7.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_7.setBounds(424, 138, 37, 46);
      contentPane.add(lblNewLabel_7);
      
      JLabel lblNewLabel_8 = new JLabel("");
      lblNewLabel_8.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_8.setBounds(498, 138, 37, 46);
      contentPane.add(lblNewLabel_8);
      
      JLabel lblNewLabel_1_8 = new JLabel("");
      lblNewLabel_1_8.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_8.setBounds(108, 138, 37, 46);
      contentPane.add(lblNewLabel_1_8);
      
      JLabel lblNewLabel_1_9 = new JLabel("");
      lblNewLabel_1_9.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_9.setBounds(375, 138, 37, 46);
      contentPane.add(lblNewLabel_1_9);
      
      JLabel lblNewLabel_1_10 = new JLabel("");
      lblNewLabel_1_10.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_10.setBounds(459, 138, 37, 46);
      contentPane.add(lblNewLabel_1_10);
      
      JLabel lblNewLabel_1_3_1 = new JLabel("");
      lblNewLabel_1_3_1.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_3_1.setBounds(183, 463, 37, 46);
      contentPane.add(lblNewLabel_1_3_1);
      
      JLabel lblNewLabel_1_3_2 = new JLabel("");
      lblNewLabel_1_3_2.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_3_2.setBounds(261, 470, 37, 39);
      contentPane.add(lblNewLabel_1_3_2);
      
      JLabel lblNewLabel_1_3_3 = new JLabel("");
      lblNewLabel_1_3_3.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_3_3.setBounds(355, 466, 37, 46);
      contentPane.add(lblNewLabel_1_3_3);
      
      JLabel lblNewLabel_1_3_4 = new JLabel("");
      lblNewLabel_1_3_4.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_3_4.setBounds(436, 466, 37, 46);
      contentPane.add(lblNewLabel_1_3_4);
      
      JLabel lblNewLabel_1_3_5 = new JLabel("");
      lblNewLabel_1_3_5.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1_3_5.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_3_5.setBounds(498, 466, 37, 46);
      contentPane.add(lblNewLabel_1_3_5);
      
      JLabel lblNewLabel_3_3 = new JLabel("");
      lblNewLabel_3_3.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3_3.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_3_3.setBounds(139, 463, 37, 46);
      contentPane.add(lblNewLabel_3_3);
      
      JLabel lblNewLabel_3_4 = new JLabel("");
      lblNewLabel_3_4.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3_4.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_3_4.setBounds(210, 463, 43, 46);
      contentPane.add(lblNewLabel_3_4);
      
      JLabel lblNewLabel_3_4_1 = new JLabel("");
      lblNewLabel_3_4_1.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_3_4_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3_4_1.setBounds(302, 463, 43, 46);
      contentPane.add(lblNewLabel_3_4_1);
      
      JLabel lblNewLabel_3_4_2 = new JLabel("");
      lblNewLabel_3_4_2.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_3_4_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3_4_2.setBounds(381, 466, 43, 46);
      contentPane.add(lblNewLabel_3_4_2);
      
      JLabel lblNewLabel_3_4_3 = new JLabel("");
      lblNewLabel_3_4_3.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_3_4_3.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3_4_3.setBounds(459, 466, 43, 46);
      contentPane.add(lblNewLabel_3_4_3);
      
      JLabel lblNewLabel_3_4_4 = new JLabel("");
      lblNewLabel_3_4_4.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_3_4_4.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3_4_4.setBounds(498, 421, 43, 46);
      contentPane.add(lblNewLabel_3_4_4);
      
      JLabel lblNewLabel_3_4_5 = new JLabel("");
      lblNewLabel_3_4_5.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_3_4_5.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3_4_5.setBounds(498, 332, 43, 46);
      contentPane.add(lblNewLabel_3_4_5);
      
      JLabel lblNewLabel_3_4_6 = new JLabel("");
      lblNewLabel_3_4_6.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
      lblNewLabel_3_4_6.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3_4_6.setBounds(498, 233, 43, 46);
      contentPane.add(lblNewLabel_3_4_6);
      
      JLabel lblNewLabel_1_7_1 = new JLabel("");
      lblNewLabel_1_7_1.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_7_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1_7_1.setBounds(498, 281, 37, 46);
      contentPane.add(lblNewLabel_1_7_1);
      
      JLabel lblNewLabel_1_7_2 = new JLabel("");
      lblNewLabel_1_7_2.setIcon(new ImageIcon(Recommendation.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
      lblNewLabel_1_7_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1_7_2.setBounds(498, 381, 37, 46);
      contentPane.add(lblNewLabel_1_7_2);
      
      
      
   
   }
}
