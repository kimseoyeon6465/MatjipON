package loginUser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import matjipDAO.MatjipDAO;
import matjipDBC.MatjipDBC;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.*;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public class Userinfo extends JFrame {

	
	//뒤로가기버튼 private JFrame frame; 
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserId;
	private JTextField txtPw1;
	private JTextField txtPw2;
	private JTextField txtName;
	private JTextField txtPhone;
	
	// 클래스 내에 플래그 선언 (중복 방지용) // 경고문구 반복 선언 막는 것
	private boolean warningShown = false;
	
	private Connection con; 
	PreparedStatement pstmt=null;
	ResultSet resultinfo = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Userinfo frame = new Userinfo(); //???
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
	
		public Userinfo() throws ClassNotFoundException, SQLException, FontFormatException, IOException {

		con =new MatjipDBC().getConnection(); //MatjipDBC를 import해서 사용
		
		getContentPane().setLayout(null);
		
///////////////////////////////		//////커스텀 폰트설정/////////////
		InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
		Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);
		
		InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
		Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);
		

///////////////////////////////////////////////////////////////////////		

		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 798);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("중복확인");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton_2.setForeground(new Color(240, 154, 60));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String info_id = txtUserId.getText().trim();	 //입력값 // 앞뒤 공백을 제거
				
				 if (info_id.length() < 5) {				//다섯글자가 안 되면
			            JOptionPane.showMessageDialog(null, "다섯자 이상 입력해주세요.");
			            return;
			        }

				try {
		            MatjipDAO dao = new MatjipDAO(); 				// DB 연동 DAO 생성
		            boolean isDuplicate = dao.idCheck(info_id);  	// 중복 검사 메서드 호출 dao.idCheck()는 DB에서 해당 아이디가 이미 있는지를 확인하는 메서드

		            if (isDuplicate) {
		                JOptionPane.showMessageDialog(null, "이미 사용 중인 아이디입니다.", "중복 확인", JOptionPane.WARNING_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다!", "중복 확인", JOptionPane.INFORMATION_MESSAGE);
		            }

		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "중복 확인 중 오류 발생: " + ex.getMessage());
		        }
		    }
		});

		btnNewButton_2.setFont(customFont2.deriveFont(Font.BOLD, 22));
		btnNewButton_2.setBounds(443, 169, 128, 65);
		contentPane.add(btnNewButton_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.DARK_GRAY);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(583, 101, 10, 644);
		contentPane.add(panel_2);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBounds(149, 113, 427, 18);
		contentPane.add(panel_1_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(90, 80, 542, 46);
		contentPane.add(panel_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.DARK_GRAY);
		panel_1_1.setBounds(143, 735, 450, 10);
		contentPane.add(panel_1_1);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.DARK_GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(133, 101, 10, 644);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// 텍스트 비우기
		        txtPw1.setText(""); // txtPw1.setText("") → 입력된 값을 모두 지워줌 (입력창 초기화)
		        txtPw1.requestFocus(); // 커서를 다시 텍스트 필드로 이동		
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// 텍스트 비우기
				txtPw2.setText(""); 
		        txtPw2.requestFocus(); 			
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Userinfo.class.getResource("/img_png/iconmonstr-lock-31-24.png")));
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 17));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(518, 377, 53, 65);
		contentPane.add(btnNewButton_1);
		
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 17));
		btnNewButton.setIcon(new ImageIcon(Userinfo.class.getResource("/img_png/iconmonstr-lock-31-24.png")));
		btnNewButton.setBounds(518, 274, 53, 65);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(customFont2.deriveFont(Font.BOLD, 22)); //폰트 바꾼 거
		lblNewLabel.setBounds(159, 122, 416, 56);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblNewLabel_2 = new JLabel("MatjipOn");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Receipt Demo", Font.BOLD, 45));
		lblNewLabel_2.setBounds(179, 10, 334, 83);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setFont(customFont);
		
		txtUserId = new JTextField();
		txtUserId.setBounds(160, 169, 288, 65);
		contentPane.add(txtUserId);
		txtUserId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setFont(customFont2.deriveFont(Font.BOLD, 22));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(159, 233, 416, 56);
		contentPane.add(lblNewLabel_1);
		
		txtPw1 = new JTextField();
		txtPw1.setColumns(10);
		txtPw1.setBounds(160, 274, 357, 65);
		contentPane.add(txtPw1);
		
		txtPw2 = new JTextField();
		txtPw2.setColumns(10);
		txtPw2.setBounds(160, 377, 357, 65);
		contentPane.add(txtPw2);
		
		JLabel lblNewLabel_1_1 = new JLabel("비밀번호 재확인");
		lblNewLabel_1_1.setFont(customFont2.deriveFont(Font.BOLD, 22));
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setBounds(159, 333, 416, 56);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("이름");
		lblNewLabel_1_2.setFont(customFont2.deriveFont(Font.BOLD, 22));
		lblNewLabel_1_2.setBackground(Color.WHITE);
		lblNewLabel_1_2.setBounds(159, 440, 416, 56);
		contentPane.add(lblNewLabel_1_2);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(160, 480, 411, 65);
		contentPane.add(txtName);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("휴대폰");
		lblNewLabel_1_1_1.setFont(customFont2.deriveFont(Font.BOLD, 22));
		lblNewLabel_1_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1_1.setBounds(159, 542, 416, 56);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(160, 586, 411, 65);
		contentPane.add(txtPhone);
		
//전화번호에 자동 하이픈 들어가기
		txtPhone.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();

		        // 숫자가 아니고 백스페이스도 아니면 입력 차단
		        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
		            e.consume(); // 입력 무시, 숫자만 입력가능
		            
		            if (!warningShown) {
		                warningShown = true;
		                JOptionPane.showMessageDialog(txtPhone, "숫자를 입력해주세요!", "입력 오류", JOptionPane.WARNING_MESSAGE);

		                // 0.5초 후 플래그를 초기화 (계속 뜨지 않게) 
		                //일정 시간(여기선 0.5초) 후에 다시 false로 초기화해서 또 입력할 수 있게 함.
		                new javax.swing.Timer(500, new ActionListener() {
		                    public void actionPerformed(ActionEvent evt) {
		                        warningShown = false;
		                    }
		                }).start(); 
		            }
		        }
		    }
		    public void keyReleased(KeyEvent e) {
		        String input = txtPhone.getText().replaceAll("[^0-9]", ""); // 숫자만 남기기
//		        입력된 전화번호 문자열에서 숫자만 남기고 나머지를 다 제거
//		        [^0-9] : 정규표현식(Regex)에서 "^"는 부정 ==> 숫자가 아닌 모든 문자
//		        replaceAll() : 조건에 맞는 문자들을 "없애라"는 뜻 (""은 빈 문자열) :입력값: "010-1234-5678" --> replaceAll 결과: "01012345678"가 input

		        StringBuilder sb = new StringBuilder();

		        if (input.length() >= 3) {			//입력 숫자가 3 이상이 되면,
		            sb.append(input.substring(0, 3)); // substring(a, b)는 a부터 b 전까지 문자열의 부분을 추출, 인덱스는 0부터 시작 (a 포함, b 미포함앞 3자리(0,1,2)
		            sb.append("-");
		            if (input.length() >= 7) {
		                sb.append(input.substring(3, 7)); // 중간 4자리 (1234)
		                sb.append("-");
		                if (input.length() > 11) {
		                    sb.append(input.substring(7, 11)); // 마지막 4자리만 제한 (5678)
		                } else {
		                    sb.append(input.substring(7)); // 남은 전체 출력 (567)
		                }
		            } else {
		                sb.append(input.substring(3)); // 중간 4자리가 안되면 그냥 남은 거 출력
		            }
		        } else {
		            sb.append(input); // 3자리 안 될 때는 그대로 출력
		        }

		        txtPhone.setText(sb.toString());
		    }
		});
		
		
		JButton btnSignin = new JButton("가입하기");
		btnSignin.setIcon(new ImageIcon(Userinfo.class.getResource("/img_png/iconmonstr-accessibility-circle-filled-24.png")));
		btnSignin.setHorizontalTextPosition(SwingConstants.LEFT); // 텍스트를 왼쪽에
		btnSignin.setIconTextGap(9); // 간격 조정
		btnSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//사용자 입력값 사져오기
				String info_id = txtUserId.getText();				//아이디
				String info_pw = new String(txtPw1.getText());		//비번	
				String info_repw = new String(txtPw2.getText());	//확인용 비번
				String info_name = txtName.getText();				//이름
				String info_phone = txtPhone.getText();				//전화번호
															
				//유효성 검사 비밀번호가 영문 + 숫자 + 특수문자 포함 8자 이상인지 검사
				Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$"); //8자 영문+특문+숫자
				Matcher passMatcher = passPattern1.matcher(info_pw);

		        if (!passMatcher.find()) {
		            JOptionPane.showMessageDialog(null, "비밀번호는 영문+특수문자+숫자 8자 이상으로 구성되어야 합니다", "비밀번호 오류", 1);
		        } else if (!info_pw.equals(info_repw)) {
		            JOptionPane.showMessageDialog(null, "비밀번호가 서로 맞지 않습니다", "비밀번호 오류", 1);
		        } else {
		            // DB에 회원정보 저장
		            try {
		            	
		 // SHA-256으로 비밀번호 암호화
		            	try {
		            	    // 비밀번호 암호화
		            	    SHA256 sha256 = new SHA256();
		            	    info_pw = sha256.encrypt(info_pw);
		            	} catch (Exception ex) {
		            	    ex.printStackTrace();
		            	    JOptionPane.showMessageDialog(null, "비밀번호 암호화 중 오류 발생");
		            	    return; // 에러 시 가입 중단
		            	}
		            	
		            	
		                MatjipDAO dao = new MatjipDAO();  // DAO 객체 생성
		                Map<String, Object> result = dao.userinfo(info_id, info_pw, info_name, info_phone);  // DB insert
		                								//DAO의 userinfo() 메서드를 통해 사용자 정보를 DB에 저장
		                boolean isSuccess = (boolean) result.get("success");
		                String message = (String) result.get("message");
		          //결과는 Map 객체로 받아서 success, message 값을 추출하여 성공/실패 처리
		                if (isSuccess) {
		                    JOptionPane.showMessageDialog(null, message);
		                    setVisible(false);  // 회원가입 창 닫기

		                    try {
		                        new Login_main().setVisible(true);  // 로그인 화면 열기
		                    } catch (ClassNotFoundException | SQLException ex) {
		                        ex.printStackTrace();
		                        JOptionPane.showMessageDialog(null, "로그인 화면 열기 실패: " + ex.getMessage());
		                    }

		                } else {
		                    JOptionPane.showMessageDialog(null, message, "회원가입 실패", JOptionPane.ERROR_MESSAGE);
		                }

		            } catch (Exception e1) {
		                e1.printStackTrace();
		                JOptionPane.showMessageDialog(null, "DB 오류 발생: " + e1.getMessage());
		            }
		        }
		    }
		});
		btnSignin.setFont(customFont2.deriveFont(Font.BOLD, 25));
		btnSignin.setBackground(new Color(240, 154, 60));
		btnSignin.setForeground(new Color(255, 255, 255));
		btnSignin.setBounds(160, 661, 411, 64);
		contentPane.add(btnSignin);
		
		JButton btnBack = new JButton("");
		// JButton 테두리와 배경 제거
		btnBack.setBorderPainted(false);   // 테두리 안 그림
		btnBack.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
		btnBack.setFocusPainted(false);    // 선택 시 테두리 안 그림
		btnBack.setOpaque(false);
		
		btnBack.setIcon(new ImageIcon(Userinfo.class.getResource("/img_png/iconmonstr-arrow-left-alt-filled-64.png")));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        dispose(); // 현재 창 닫기
		      
		        try {
		            Login_main login = new Login_main();
		            login.setVisible(true); // 로그인 창 다시 열기
		        }  catch (FontFormatException | IOException | ClassNotFoundException | SQLException | NoSuchAlgorithmException ex) {
		            ex.printStackTrace(); // 모든 예외 한번에 처리
		            //JOptionPane.showMessageDialog(null, "로그인 화면 열기 실패: " + ex.getMessage());
		        }
				
			}
		});
		
		btnBack.setBounds(92, 15, 95, 60);
		contentPane.add(btnBack);
		
	//이미지 조절	
		ImageIcon icon = new  ImageIcon(Userinfo.class.getResource("/img_png/Rabbit01.png"));
        Image img = icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH); //가로, 세로
        JLabel Rabbit = new JLabel(new ImageIcon(img));
		Rabbit.setBounds(484, 10, 100, 65);
		contentPane.add(Rabbit);
		

}


		
		
	protected boolean isIdDuplicated(String info_id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Userinfo(Userinfo loginUser) {
		// TODO Auto-generated constructor stub
	}

}
