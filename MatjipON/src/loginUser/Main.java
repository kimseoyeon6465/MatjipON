package loginUser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	 * 
	 */
	public Main() throws FontFormatException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
///////////////////////////////		//////커스텀 폰트설정/////////////
		InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
		Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);
		
		InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
		Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);
	
	JPanel panel_1_1_1 = new JPanel();
	panel_1_1_1.setBounds(173, 157, 416, 18);
	contentPane.add(panel_1_1_1);
		

///////////////////////////////////////////////////////////////////////		
	JLabel lblNewLabel_2 = new JLabel("Matjip On");
	lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_2.setFont(new Font("Receipt Demo", Font.BOLD, 45));
	lblNewLabel_2.setBounds(214, 38, 334, 83);
	contentPane.add(lblNewLabel_2);
	// 해당 객체를 직접 적용
	lblNewLabel_2.setFont(customFont);	

//		JLabel lblNewLabel_2 = new JLabel("Matjip On");
//		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 45));
//		lblNewLabel_2.setBounds(241, 92, 334, 83);
//		contentPane.add(lblNewLabel_2);
//		
		JPanel panel = new JPanel();
		panel.setForeground(Color.DARK_GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(151, 141, 10, 420);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(108, 120, 542, 46);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.DARK_GRAY);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(601, 141, 10, 420);
		contentPane.add(panel_2);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.DARK_GRAY);
		panel_1_1.setBounds(160, 551, 450, 10);
		contentPane.add(panel_1_1);
		
		//이미지 크기만 키우기
		ImageIcon originalIcon = new ImageIcon(Main.class.getResource("/img_png/Logo01.png"));

		// 원하는 크기로 이미지 리사이즈 (예: 427x354)
		Image scaledImage = originalIcon.getImage().getScaledInstance(650, 560, Image.SCALE_SMOOTH);

		ImageIcon resizedIcon = new ImageIcon(scaledImage);
		
		JLabel lblmain = new JLabel("");
		lblmain.setIcon(resizedIcon); //이미지크기는 내가 알아서 하겠다
		lblmain.setBounds(52, 120, 616, 477);
		contentPane.add(lblmain);
		
		
		// 로그인 버튼
		ImageIcon originalIcon2 = new ImageIcon(Main.class.getResource("/img_png/iconmonstr-accessibility-circle-lined-120.png"));

		// 원하는 크기로 이미지 리사이즈 (예: 427x354)
		Image scaledImage2 = originalIcon2.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon2 = new ImageIcon(scaledImage2);
		
		JButton btnLogin = new JButton("");
				btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.setIcon(resizedIcon2);
		btnLogin.setBounds(311, 582, 141, 119);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Login_main으로 가도록
				  setVisible(false); // Main 창 감추기

			        try {
			            Login_main loginFrame = new Login_main();  // Userinfo 클래스 생성
			            loginFrame.setVisible(true);           // 창 띄우기
			        } catch (FontFormatException | IOException | ClassNotFoundException | SQLException | NoSuchAlgorithmException ex) {
			            ex.printStackTrace();  // 모든 예외 한 번에 처리
			        }
			    }
			});


//		// 챗 버튼
//		ImageIcon chatIcon = new ImageIcon(Main.class.getResource("/img_png/iconmonstr-speech-bubble-25-120.png"));
//
//		// 원하는 크기로 이미지 리사이즈 (예: 427x354)
//		Image scaledImage3 = chatIcon.getImage().getScaledInstance(90,90, Image.SCALE_SMOOTH);
//		ImageIcon resizedIcon3 = new ImageIcon(scaledImage3);
		
		// 테두리와 배경 제거
		btnLogin.setBorderPainted(false);   // 테두리 안 그림
		btnLogin.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
		btnLogin.setFocusPainted(false);    // 선택 시 테두리 안 그림
		btnLogin.setOpaque(false);
		
	}

}
