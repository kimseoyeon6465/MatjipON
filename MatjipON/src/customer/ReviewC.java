package customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loginUser.Login_main;
import matjipDAO.ReviewDAO;
import matjipDAO.WaitingDAO.LoginSession;
import matjipDAO.WaitingDAO.MatjipSession;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class ReviewC extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtReview;
//생성자 매개변수로 userId 추가:
//	private String userid;
//	private int matjip_id;
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//	                ReviewC frame = new ReviewC();
//	                frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	//Review frame = new Review(); // 인자 없이 호출 ❌
	//new Review();는 아무 인자도 전달하지 않는데, 현재 클래스에는 기본 생성자 (Review())가 존재하지 않기 때문에 오류가 발생
	//main에서 인자 전달 / 가장 올바른 방법은 main에서 필요한 값을 직접 전달하는 방식
	
	
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public ReviewC() throws FontFormatException, IOException {
		
		
					// ReservationDAO와 같은 이름
//		 this.userid = userid;
//		 this.matjip_id = matjip_id;
		//이 코드는 Review 클래스의 **생성자(Constructor)**입니다.
		//이 생성자는 userid와 matjip_id 두 개의 인자를 받아야만 객체를 생성할 수 있어요.
		
		
////////////////////폰트 - 더 안전한 resource 불러오기 방법
///
		InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
		Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);
		
		InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
		Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);




		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(Color.WHITE);
		panel_1_1_1.setBounds(90, 110, 427, 18);
		contentPane.add(panel_1_1_1);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.DARK_GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(74, 98, 10, 420);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(31, 77, 542, 46);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.DARK_GRAY);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(524, 98, 10, 420);
		contentPane.add(panel_2);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.DARK_GRAY);
		panel_1_1.setBounds(83, 508, 450, 10);
		contentPane.add(panel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Matjip On");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(customFont);
		lblNewLabel_2.setBounds(140, 10, 334, 83);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("REVIEW");
		lblNewLabel.setBounds(239, 127, 180, 89);
		lblNewLabel.setFont(customFont2.deriveFont(Font.BOLD, 45));
		contentPane.add(lblNewLabel);
		
	
		// 1. 별 아이콘 로딩
		ImageIcon starEmpty = new ImageIcon(getClass().getResource("/img_png/iconmonstr-star-lined-64.png"));
		ImageIcon starFilled = new ImageIcon(getClass().getResource("/img_png/iconmonstr-star-filled-64.png"));

		// 2. 별점 관련 변수
		JButton[] stars = new JButton[5]; // 별 5개 배열
		int[] rating = {0}; // 현재 별점 (0~5)

		// 3. 별 버튼 생성 및 이벤트 설정
		for (int i = 0; i < 5; i++) {
		    final int index = i; // 반복문 내부에서 사용할 고정 인덱스
		    JButton star = new JButton(); // 별 하나 생성
		    star.setBounds(81 + (i * 90), 207, 75, 89); // 화면 내 위치와 크기 setBounds(x, y, width, height)
		    star.setIcon(starEmpty); // 기본은 빈 별

		    // 스타일링: 배경과 테두리 제거
		    star.setBorderPainted(false);
		    star.setContentAreaFilled(false);
		    star.setFocusPainted(false);
		    star.setOpaque(false);

		    // 클릭 시 동작
		    star.addActionListener(e -> {
		        rating[0] = index + 1; // 별점 저장 (1~5)
		        for (int j = 0; j < 5; j++) {
		            stars[j].setIcon(j <= index ? starFilled : starEmpty);
		        }
		    });

		    stars[i] = star;
		    contentPane.add(star);
		}

			
			JButton btnComplete = new JButton("작성하기");
			
			// JButton 테두리와 배경 제거
			btnComplete.setBorderPainted(false);   // 테두리 안 그림
			btnComplete.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
			btnComplete.setFocusPainted(false);    // 선택 시 테두리 안 그림
			btnComplete.setOpaque(false);
			btnComplete.setFont(customFont2.deriveFont(Font.BOLD, 20));
			
			btnComplete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
			        
				    // selectedRating, reviewText는 JFrame에서 잘 받아오고 있고
				    //id, matjip_id는 LoginSession, MatjipSession에서 잘 가져오고 있으며
					
					 int selectedRating = rating[0]; // 선택한 별 개수
				        String reviewText = txtReview.getText().trim();

				        // 유효성 검사
				        if (selectedRating == 0) {
				            JOptionPane.showMessageDialog(null, "별점을 선택해주세요.");
				            return;
				        }
				        if (reviewText.isEmpty() || reviewText.equals("리뷰를 남겨주세요 10자 이내")) {
				            JOptionPane.showMessageDialog(null, "리뷰 내용을 입력해주세요.");
				            return;
				        }

				        try {
		// DAO 호출 // id, matjip_id는 dao와 맞춘 것 (이어 받을 거라서?)
							ReviewDAO dao = new ReviewDAO();
							String id = LoginSession.loginUserId;  // 로그인된 사용자 ID
							int matjip_id1 = MatjipSession.selectedMatjipId ;
							Map<String, Object> result = dao.review(id, matjip_id1, reviewText, selectedRating);
							
				            if ((boolean) result.get("success")) {
				                JOptionPane.showMessageDialog(null, "리뷰가 성공적으로 등록되었습니다!");
				                
				                // 여기서 현재 창 닫고 Main 창 열기
				                setVisible(false); // 현재 Review 창 감추기
				                try {
				                    loginUser.Main mainFrame = new loginUser.Main(); // 새 Main 창 열기
				                    mainFrame.setVisible(true);
				                } catch (FontFormatException | IOException ex) {
				                    ex.printStackTrace();
				                }
				                
				            } else {
				            	JOptionPane.showMessageDialog(null, "리뷰 등록 실패");
				                //JOptionPane.showMessageDialog(null, result.get("message"));
				            }
				        } catch (Exception ex) {
				            ex.printStackTrace();
				            JOptionPane.showMessageDialog(null, "DB 오류: " + ex.getMessage());
				        }
				    }
				});
		
		btnComplete.setBounds(200, 415, 222, 83);
		contentPane.add(btnComplete);
		
		
		txtReview = new JTextField(); // 선언과 동시에 생성
		txtReview.setText("리뷰를 남겨주세요 10자 이내");
		txtReview.setBounds(90, 296, 427, 109);
		txtReview.setColumns(10);
		txtReview.setForeground(Color.GRAY);
		txtReview.setFont(customFont2.deriveFont(Font.PLAIN, 14));
		txtReview.addFocusListener(new FocusAdapter() { //FocusListener를 사용해서 텍스트 필드에 포커스가 들어오거나 나갈 때의 동작을 지정
		    @Override					//여기서는 FocusAdapter를 상속받아 필요한 메서드만 오버라이드 (focusGained, focusLost).
		    public void focusGained(FocusEvent e) { 	//focusGained: 텍스트 필드에 포커스가 들어올 때
		        if (txtReview.getText().equals("리뷰를 남겨주세요 10자 이내")) {
		        	txtReview.setText("");					 // 텍스트 삭제
		        	txtReview.setForeground(Color.BLACK);	 // 입력을 위한 글씨 색 변경
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {		//focusLost: 텍스트 필드에 포커스가 나갈 때
		        if (txtReview.getText().isEmpty()) {
		        	txtReview.setText("리뷰를 남겨주세요 10자 이내");				// 플레이스홀더 다시 표시
		        	txtReview.setForeground(Color.GRAY);	 // 안내 문구 스타일로 변경
		        }
		    }
		});
		contentPane.add(txtReview);
		
		
		
	}
	
	
//	Java에서 프로그램은 항상 main() 메서드부터 시작됩니다.
//	그런데 현재 ReviewC 클래스는 JFrame 기반 GUI 클래스일 뿐,
//	직접 실행할 수 있는 진입점이 없기 때문에 에러가 발생
	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    ReviewC frame = new ReviewC();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	}


//}



