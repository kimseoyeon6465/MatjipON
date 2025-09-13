package customer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import matjipDAO.ReviewDAO;
import matjipDAO.WaitingDAO;
import matjipDAO.WaitingDAO.LoginSession;

public class Select_List2_Kfood extends JFrame {

	// matid를 기반으로 DB 조회하고, 결과를 라벨 등에 표시하는 코드 필요
	public Select_List2_Kfood() {
		// 초기화 코드 (예: setTitle, setSize 등)
		setTitle("MatjipOn");
		setSize(400, 300);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Select_List2_Kfood frame = new Select_List2_Kfood();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws FontFormatException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Select_List2_Kfood(int matid) throws FontFormatException, IOException, ClassNotFoundException, SQLException {

		////////////////////더 안전한 resource 불러오기 방법
		InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
		Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);

		InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
		Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(Color.WHITE);
		panel_1_1_1.setBounds(136, 118, 427, 18);
		contentPane.add(panel_1_1_1);

		JPanel panel = new JPanel();
		panel.setForeground(Color.DARK_GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(120, 106, 10, 446);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(77, 85, 542, 46);
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.DARK_GRAY);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(570, 106, 10, 446);
		contentPane.add(panel_2);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.DARK_GRAY);
		panel_1_1.setBounds(130, 542, 450, 10);
		contentPane.add(panel_1_1);

		///////////////////////수정필요	 101을 모두 MatjipSession.selectedMatjipId	
		WaitingDAO d1 = new WaitingDAO();

		// JLabel lblNewLabel_2 = new JLabel();

		ReviewDAO Reviewdao = new ReviewDAO();
		String searchname = Reviewdao.view3(matid); // ID로 이름 조회

		JLabel lblMatjipName = new JLabel(searchname); // ReviewDAO view1의 matjipname2로 받아야됨
		lblMatjipName.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatjipName.setFont(customFont2.deriveFont(Font.BOLD, 50));
		lblMatjipName.setBounds(179, 10, 334, 83);
		contentPane.add(lblMatjipName);

		JLabel lblNewLabel_2_1 = new JLabel(searchname); // view1의 matjipname2로 받아야됨
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(customFont2.deriveFont(Font.BOLD, 25));
		lblNewLabel_2_1.setBounds(136, 398, 140, 56);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblimg = new JLabel("");
		lblimg.setBounds(146, 133, 408, 261);
		contentPane.add(lblimg);

		// 이미지 배열 준비 슬라이드쇼에 사용할 이미지 파일들의 경로를 문자열 배열로 저장
		String[] imagePaths = { "/img_png/traditional.jpg", "/img_png/traditional food.jpg",
				"/img_png/traditionalfood2.jpg" };

		int[] currentIndex = { 0 }; // 현재 화면에 표시되고 있는 이미지의 인덱스(위 배열에서 몇 번째인지)를 저장 (배열로 감싸서 final처럼 사용)
									// 배열로 감싼 이유는 내부 익명 클래스(ActionListener)에서도 수정 가능하게 하기 위함
		// JLabel에 첫 이미지 설정
		ImageIcon icon = new ImageIcon(getClass().getResource(imagePaths[currentIndex[0]]));
		Image resizedImage = icon.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(),
				Image.SCALE_SMOOTH);
		lblimg.setIcon(new ImageIcon(resizedImage)); // getScaledInstance(...)로 이미지 크기를 lblimg의 크기에 맞게 리사이즈

		// 타이머 설정 (2초마다 이미지 교체)
		Timer timer = new Timer(2000, new ActionListener() { // 2000 밀리초(= 2초)마다 동작할 스윙 타이머
			@Override
			public void actionPerformed(ActionEvent e) {
				currentIndex[0] = (currentIndex[0] + 1) % imagePaths.length; // 이미지 인덱스를 1 증가시킴, % imagePaths.length는
																				// 마지막 이미지까지 가면 다시 처음으로 돌아가도록 순환 처리 다음
																				// 이미지로 순환
				ImageIcon nextIcon = new ImageIcon(getClass().getResource(imagePaths[currentIndex[0]]));
				Image resized = nextIcon.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(),
						Image.SCALE_SMOOTH);
				lblimg.setIcon(new ImageIcon(resized));
			}
		});
		timer.start(); // 타이머 시작

		JLabel lblStaricon = new JLabel("");
		lblStaricon
				.setIcon(new ImageIcon(Select_List2_Kfood.class.getResource("/img_png/iconmonstr-star-filled-48.png")));
		lblStaricon.setBounds(143, 457, 48, 55);

		contentPane.add(lblStaricon);

		// 평균별점 (평균 rating)을 REVIEW_TABLE에서 가져와 JLabel에 표시
		// DAO 호출 // matjip_id는 dao와 맞춘 것 (이어 받을 거라서?)
		ReviewDAO dao = new ReviewDAO();
		//// 선택된 맛집 ID (예: 101)이 있으니까 필요없는 코드
		String id = LoginSession.loginUserId;// 테스트
		// 맛집 ID 101번의 리뷰 목록 가져오기 (리뷰 배열)
		// ArrayList<MatjipVO> reviewarray = dao.getAllInfoReview(matid1);
		// //ReviewDAO?에서 받아 올 matjip_id (101대신 matjip_id넣어도 됨)
		// 아래처럼 소수점 둘째 자리까지만 표시하면 더 좋음
		double avg = dao.getAvgRating(matid);
		String formatted = String.format("%.2f", avg); // 소수점 둘째 자리까지
		JLabel lblRating2 = new JLabel(formatted);
		// 맛집 ID 101번의 평균 별점 가져오기
		// JLabel lblRating2 = new JLabel(String.valueOf(dao.getAvgRating(101)));
		lblRating2.setBounds(195, 445, 64, 67);
		lblRating2.setFont(customFont2.deriveFont(Font.PLAIN, 25));

		contentPane.add(lblRating2);

		// dao.getAvgRating(101) → double값 반환 (예: 4.2)
		// String.valueOf(...) → "4.2" 문자열로 변환
		// JLabel lblRating2 = new JLabel("4.2"); → 라벨에 평균 점수 표시
		// contentPane.add(lblRating2); → 실제 창에 라벨 붙이기

		JButton btnReview = new JButton("리뷰보기 >");
		btnReview.setFont(customFont2.deriveFont(Font.PLAIN, 20));
		btnReview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 리뷰보기 창 열림
				setVisible(false);

				ReviewView frame = null;
				try {
					frame = new ReviewView(id, matid);// 테스트

				} catch (Exception e3) {
					e3.printStackTrace();
				}
				frame.setVisible(true); // 창 띄우기

			}
		});
		// JButton 테두리와 배경 제거
		btnReview.setBorderPainted(false); // 테두리 안 그림
		btnReview.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
		btnReview.setFocusPainted(false); // 선택 시 테두리 안 그림
		btnReview.setOpaque(false);
		btnReview.setBounds(269, 404, 147, 46);
		contentPane.add(btnReview);

		/////혼잡도 찍히게
	 ///

		JLabel lblBusy2 = new JLabel("New label");
		lblBusy2.setBounds(271, 450, 194, 56);
		lblBusy2.setFont(customFont2.deriveFont(Font.PLAIN, 18));
		contentPane.add(lblBusy2);

		// waitingDAO에 만든 BusyLevelById 메서드 호출하여 matjip_id 101번 혼잡도 가져오기
		WaitingDAO matdao = new WaitingDAO(); // waitingDAO 불러옴

		int busyLevel = matdao.getBusyLevelById(matid);
		// int targetMatjipId = 101; // 예시 ID
		// int busyLevel = -1;
		// String busyText = "정보 없음"; // 기본값

		// 모든 맛집 정보 중에서 원하는 ID 찾아서 busyLevel 추출
		// ArrayList<WatingVO> matjipList = matdao.getAllInfoMatjip();
		// for (WatingVO vo : matjipList) {
		// if (vo.getMatjipId() == targetMatjipId) {
		// busyLevel = vo.getBusyLevel();
		// break;
		// }
		// }
		// 혼잡도 레벨 숫자를 텍스트로 변환 (예: 1=한산, 2=보통, 3=혼잡 등)
		String busyText = "";
		switch (busyLevel) {
		case 1:
			busyText = "한산";
			break;
		case 2:
			busyText = "보통";
			break;
		case 3:
			busyText = "혼잡";
			break;
		default:
			busyText = "정보 없음";
			break;
		}
		;

		lblBusy2.setText("혼잡도 : " + busyText);

		JButton btnWaiting = new JButton("웨이팅하기");
		btnWaiting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					WaitingDAO matdao = new WaitingDAO();
					String userId = LoginSession.loginUserId; // 로그인된 사용자 ID
					// 맛집아이디

					// 웨이팅 확인 팝업
					int response = JOptionPane.showConfirmDialog(null, "이 맛집에 웨이팅 하시겠습니까?", "웨이팅 확인",
							JOptionPane.YES_NO_OPTION);

					if (response == JOptionPane.YES_OPTION) {
						int result = matdao.insertWaitingById(userId, matid);

						if (result > 0) {
							JOptionPane.showMessageDialog(null, "웨이팅 요청이 완료되었습니다.");

							Waiting_Queue w3 = new Waiting_Queue();
							w3.setVisible(true);
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "웨이팅이 실패되었습니다.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "웨이팅이 취소되었습니다.");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "에러 발생: " + ex.getMessage());
				}
			}
		});

		btnWaiting.setBounds(437, 485, 140, 46);

		// JButton 테두리와 배경 제거
		btnWaiting.setBorderPainted(false); // 테두리 안 그림
		btnWaiting.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
		btnWaiting.setFocusPainted(false); // 선택 시 테두리 안 그림
		btnWaiting.setOpaque(false);
		btnWaiting.setFont(customFont2.deriveFont(Font.PLAIN, 18));
		contentPane.add(btnWaiting);

		// 1. JLabel 생성 및 위치 지정
		JLabel lblRabbit = new JLabel("");
		lblRabbit.setBounds(437, 387, 126, 122); // 원하는 위치와 크기 설정
		contentPane.add(lblRabbit);

		// 2. 원본 이미지 불러오기
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/img_png/Rabbit03.jpg"));

		// 3. 이미지 리사이즈 (JLabel 크기에 맞게)
		Image image = icon2.getImage().getScaledInstance(lblRabbit.getWidth(), lblRabbit.getHeight(),
				Image.SCALE_SMOOTH);

		// 4. JLabel에 리사이즈된 이미지 적용
		lblRabbit.setIcon(new ImageIcon(image));

		lblRabbit.setHorizontalTextPosition(SwingConstants.CENTER); // 텍스트 가로 중앙 정렬
		lblRabbit.setVerticalTextPosition(SwingConstants.BOTTOM);

	}
}
