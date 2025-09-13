package loginUser;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.awt.Font;
import java.awt.FontFormatException;
import javax.swing.UIManager;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import customer.Select;
import manager.Manage;
import matjipChat.ChatBotGUI;
import matjipDAO.MatjipDAO;
import matjipDBC.MatjipDBC;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
										
public class Login_main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JButton btnRegister;
	public static int m_matjipid;
	private Connection con; 
	PreparedStatement pstmt=null;
	ResultSet rs = null; // Login_main에서 전역 필드로 ResultSet rs = null;
		
		public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_main frame = new Login_main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws NoSuchAlgorithmException 
	 */	
		
		
	
	public Login_main() throws ClassNotFoundException, SQLException, FontFormatException, IOException, NoSuchAlgorithmException {
		
		
		
		//SHA256 클래스는 MessageDigest를 사용해 문자열을 SHA-256으로 해시하고, 이를 16진수 문자열로 변환
	try {
		//작성 클래스의 인스턴스 생성
		SHA256 sha256 = new SHA256();
	    //비밀번호
	    String password = "pwdText";
	    //cryptogram : SHA256으로 암호화된 비밀번호
	    String cryptogram = sha256.encrypt(password);
	    
	    System.out.println("input         : "+password);
	    System.out.println("After SHA256  : "+ cryptogram);
	    //비밀번호 일치 여부
		
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		
		
		
		
		
		con =new MatjipDBC().getConnection(); //MatjipDBC를 import해서 사용하겠다
		
		getContentPane().setLayout(null);	//자기자신을 참조하는 객체 this=buttonclass 
		//setLayout -null : 그래픽 창 레이아웃 위치 등을 내 마음대로 정하겠다
		getContentPane().setLayout(new BorderLayout());
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(new Color(240, 240, 240));
		panel_1_1_1.setBounds(145, 144, 427, 18);
		contentPane.add(panel_1_1_1);
		
		
		txtId = new JTextField();
		//txtId.setColumns(10);
		txtId.setText(""); 		// 혹시라도 기본값이 있다면 지우기
		txtId.setBounds(198, 187, 252, 57);
		contentPane.add(txtId); 
		
		
/////////////////////////////////		//////커스텀 폰트설정 더 안전한 resource 불러오기 방법

		InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
		Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);
		
		InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
		Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);
		
		//getClass().getResourceAsStream : 클래스 기준으로 리소스 파일을 InputStream 형태로 읽어오기 → .ttf 파일을 자바 코드로 로드하기 위한 준비 과정
		//fonts/Receipt_Demo.ttf --> 프로젝트 내부의 resources/fonts/ 폴더에 있는 폰트 파일 경로 (루트 기준 /).
		//Font.TRUETYPE_FONT 트루타입 폰트임을 명시 (.ttf 확장자)
		//is, is2 --> 위에서 불러온 InputStream으로 폰트를 생성
		//deriveFont()는 기존 폰트 객체에서 스타일/크기를 지정하여 새로운 폰트를 생성하는 메서드
		
///////////////////////////////////////////////////////////////////////		
		
		JLabel lblNewLabel_2 = new JLabel("Matjip On");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(customFont); //커스텀 폰트 사용
		lblNewLabel_2.setBounds(188, 39, 334, 83);
		contentPane.add(lblNewLabel_2);

		//아이디 글씨 보이게 추가
		txtId.setText("아이디");
		txtId.setForeground(Color.GRAY);
		txtId.setFont(customFont2.deriveFont(Font.PLAIN, 14));
		txtId.addFocusListener(new FocusAdapter() { //FocusListener를 사용해서 텍스트 필드에 포커스가 들어오거나 나갈 때의 동작을 지정
		    @Override					//여기서는 FocusAdapter를 상속받아 필요한 메서드만 오버라이드 (focusGained, focusLost).
		    public void focusGained(FocusEvent e) { 	//focusGained: 텍스트 필드에 포커스가 들어올 때
		        if (txtId.getText().equals("아이디")) {
		            txtId.setText("");					 // "아이디" 텍스트 삭제
		            txtId.setForeground(Color.BLACK);	 // 입력을 위한 글씨 색 변경
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {		//focusLost: 텍스트 필드에 포커스가 나갈 때
		        if (txtId.getText().isEmpty()) {
		            txtId.setText("아이디");				// 플레이스홀더 "아이디" 다시 표시
		            txtId.setForeground(Color.GRAY);	 // 안내 문구 스타일로 변경
		        }
		    }
		});

		//비밀번호도 
		passwordField = new JPasswordField("비밀번호");		//JPasswordField는 기본적으로 입력값을 ●로 마스킹
		passwordField.setEchoChar((char)0); 				// 비밀번호 보이도록 (힌트처럼), (EchoChar을 0으로 해 평문으로 보여줌)
		passwordField.setForeground(Color.GRAY);
		passwordField.setFont(customFont2.deriveFont(Font.PLAIN, 14));
		passwordField.setBounds(198, 249, 252, 54);
		contentPane.add(passwordField);
		
		passwordField.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        // 포커스를 얻으면 힌트 제거
		        String pwdText = String.valueOf(passwordField.getPassword());
		        if (pwdText.equals("비밀번호")) {
		            passwordField.setText("");				// 힌트 제거
		            passwordField.setEchoChar('●'); 		// 다시 마스킹 시작
		            passwordField.setForeground(Color.BLACK);
		        }
		    }
		    				
		    @Override
		    public void focusLost(FocusEvent e) {
		        // 포커스를 잃고 비었으면 다시 힌트 표시
		        String pwdText = String.valueOf(passwordField.getPassword());
		        if (pwdText.isEmpty()) {
		            passwordField.setText("비밀번호");			// 아무것도 입력하지 않으면 "비밀번호" 글자가 보이게
		            passwordField.setEchoChar((char)0); 	//텍스트필드에 있는 내용을  str으로 재설정함. (원래 쓰인대로 글씨가 표시됨)
		            passwordField.setForeground(Color.GRAY);
		        }
		    }
		});
		
		//2. setEchoChar((char)0); — 힌트 마스킹 제거
		//비밀번호 입력창은 일반적으로 입력한 글자를 ●●●● 이렇게 가려서 보여주는데 
		//setEchoChar(char c)는 입력된 문자를 어떻게 보여줄지 결정하는 메서드/ 지정된 문자를 EchoChar로(비밀번호 입력에 주로 사용됨)
		//보통은 setEchoChar('●') 이런 식으로 글자를 마스킹
		//그런데 setEchoChar((char)0)는 마스킹을 해제하는 기능 즉, 입력한 텍스트가 그대로 보이게 만들어서
		//사용자가 아무것도 입력하지 않으면 "비밀번호"라는 글자가보이고, 그 힌트는 ●로 가리지 않고 그냥 보이도록 마스킹을 없앤것
		
		JLabel lblNewLabel_3 = new JLabel("--------------------------비회원---------------------------");
		lblNewLabel_3.setFont(customFont2.deriveFont(Font.BOLD, 14)); 
		lblNewLabel_3.setBounds(138, 324, 521, 37);
		contentPane.add(lblNewLabel_3);
		
		JButton btnRegister = new JButton("회원가입");
	
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	
				setVisible(false); // 로그인 창 감추기

			        try {
			            Userinfo userinfoFrame = new Userinfo();  // Userinfo 클래스 생성
			            userinfoFrame.setVisible(true);           // 창 띄우기
			        } catch (FontFormatException | IOException | ClassNotFoundException | SQLException ex) {
			            ex.printStackTrace();  // 모든 예외 한 번에 처리
			        }
			    }
			});
		
		
		btnRegister.setBackground(new Color(240, 240, 240));
		btnRegister.setFont(customFont2.deriveFont(Font.BOLD, 20));
		btnRegister.setBounds(188, 371, 349, 57);
		contentPane.add(btnRegister);
		
		JButton btnHelp = new JButton(" 챗봇");
		btnHelp.setIcon(new ImageIcon(Login_main.class.getResource("/img_png/help_icon.png")));
		btnHelp.setFont(customFont2.deriveFont(Font.BOLD, 20));
		btnHelp.setBackground(new Color(240, 240, 240));
		btnHelp.setHorizontalTextPosition(SwingConstants.LEFT); // 텍스트를 왼쪽에 (아이콘이 오른쪽)
		btnHelp.setIconTextGap(9); // 간격 조정(아이콘, 글자 사이)
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				  setVisible(false); // 로그인 창 감추기

			        ChatBotGUI chatFrame = new ChatBotGUI();  // ChatBotGUI 클래스 생성
					chatFrame.setVisible(true);           // 창 띄우기		
			}
		});
		
		btnHelp.setBounds(188, 433, 349, 57);
		contentPane.add(btnHelp);
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String id = txtId.getText();
				String pw = new String(passwordField.getPassword()); //JPasswordField는 getText() 대신 getPassword()로 읽어야 하고 
																	//배열로 리턴되기 때문에 new String()으로 문자열로 바꿔줌
	
				
				try {
		            // 1. SHA256 암호화 처리
				
				SHA256 sha256 = new SHA256();
				String hashedPw = sha256.encrypt(pw);	//그냥pw가 아닌 암호화된 hashedPw를 db에 넘겨야함
				
	///리뷰
				
		            MatjipDAO matdao = new MatjipDAO();  // dao import DAO 클래스에서 DB에 연결해서 Login() 메서드로 로그인 여부를 확인
//		            boolean loginSuccess = matdao.Login(id, pw);  // 로그인 시도 (이러면 암호화가 안 됨)
		            
		         // 로그인 시도
		            boolean loginSuccess = matdao.Login(id, hashedPw); //db로 넘길때도 암호화된 heshedPw를 넘김

					// 넘겨줄거야
					//matdao.Login(id,pw);
					//ResultSet rs1 = dao.Login(id, pw);  // 반환된 ResultSet을 변수에 저장

		            if (loginSuccess) { //로그인 성공시 manager_id가 null이면 유저창, null이 아니면 매니저창으로 갈라지게 
		                
		            	setVisible(false); // 성공시 로그인 창 감추기
		            	
		            	// 로그인 성공한 유저의 manager_id 가져오기
		                	String managerId = matdao.getManagerId(id); //USER_TABLE의 manager_id

		                    if (managerId == null) {
		                        JOptionPane.showMessageDialog(null, "일반 유저로 로그인 되었습니다!");
		                    // Select.java 창(유저창) 실행
		                        Select selectWindow = new Select();
		                        selectWindow.setVisible(true);
		                      
		                        
		                    } else {
		                        JOptionPane.showMessageDialog(null, "매니저로 로그인 되었습니다!");
		                    // Manage.java 창 실행
		                        //함수 호출
		                        Manage manageWindow = new Manage();
		                        manageWindow.setVisible(true);
		                       
		                    }
		    	
			
					} else { 			// 로그인 실패 또는 예외 처리
						JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다.");
					}
		    //예외 발생 시 메시지 출력 + 리소스 정리
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "오류 발생: " + ex.getMessage());
				} finally {
					try { if (rs != null) rs.close(); } catch (Exception ex) {}
					try { if (pstmt != null) pstmt.close(); } catch (Exception ex) {}
					try { if (con != null) con.close(); } catch (Exception ex) {}
				} //예외가 발생해도 DB 연결 리소스(ResultSet, PreparedStatement, Connection)를 안전하게 닫아줌, 
				//rs, pstmt, con이 현재 클래스에 선언돼 있어서 정상 작동 가능
			}
		});		
		
				
		btnLogin.setSelectedIcon(new ImageIcon(Login_main.class.getResource("/img_png/on_icon.png")));
		btnLogin.setBackground(new Color(240, 154, 60));
//		btnLogin.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		btnLogin.setFont(customFont2.deriveFont(Font.BOLD, 19));
		btnLogin.setBounds(462, 187, 95, 119);
		contentPane.add(btnLogin);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setForeground(Color.DARK_GRAY);
		panel.setBounds(129, 132, 10, 420);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(86, 111, 542, 46);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.DARK_GRAY);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(579, 132, 10, 420);
		contentPane.add(panel_2);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.DARK_GRAY);
		panel_1_1.setBounds(138, 542, 450, 10);
		contentPane.add(panel_1_1);
		
	
	}


	
	
	public void actionPerformed1(ActionEvent e) {
		
		
     
	                
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}

