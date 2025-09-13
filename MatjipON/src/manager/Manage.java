package manager;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loginUser.Login_main;
import matjipDAO.CircleChartDAO;
import matjipDAO.ManageDAO;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class Manage extends JFrame {
	

	int matjipid;
	String popular;
	String order;


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException, NumberFormatException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage frame = new Manage();
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
	public Manage() throws FontFormatException, IOException {
		
		
/////////////////////////////////		//////커스텀 폰트설정 더 안전한 resource 불러오기 방법

InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);

InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		//혼잡도 시작
		JButton ppbtn = new JButton("우리 가게 혼잡도 제출");
		ppbtn.setHorizontalAlignment(SwingConstants.LEFT);
		ppbtn.setFont(customFont2.deriveFont(Font.PLAIN, 20));
		
	     // JButton 테두리와 배경 제거
		ppbtn.setBorderPainted(false);   // 테두리 안 그림
		ppbtn.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
		ppbtn.setFocusPainted(false);    // 선택 시 테두리 안 그림
		ppbtn.setOpaque(false);
		// 텍스트 위치 조정
		ppbtn.setHorizontalTextPosition(SwingConstants.CENTER); // 수평 중앙
		ppbtn.setVerticalTextPosition(SwingConstants.BOTTOM);   // 아이콘 아래 텍스트
		ppbtn.setBounds(213, 200, 286, 67);
		ppbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				popular = JOptionPane.showInputDialog( "혼잡도를 입력하세요. (1~3)" );
				matjipid = Integer.parseInt(JOptionPane.showInputDialog( "가게 id를 입력하세요." ));
				if (popular == null) {
					JOptionPane.showMessageDialog(ppbtn, "입력을 취소하였습니다.");
					return;
				}
				
				try {
					int busyLevel = Integer.parseInt(popular);
					//if문시작
					if (busyLevel >= 1 && busyLevel <= 3 ) {
						JOptionPane.showMessageDialog
						(ppbtn, "입력하신 혼잡도 : " + busyLevel + " 입력하신 가게이름 : " + matjipid );
						ManageDAO matdao = new ManageDAO();
						boolean success = matdao.updatePopular(busyLevel, matjipid);
						
						if (success) {
							JOptionPane.showMessageDialog(ppbtn, "DB에 성공적으로 저장되었습니다.");
						} else {
							JOptionPane.showMessageDialog(ppbtn, "DB 저장 실패!");
						}
					}
					else {
						JOptionPane.showMessageDialog(ppbtn, "정확한 값을 입력해주세요.");
					}  //if문 끝
					
				} catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
					
						// TODO: handle exception
						 JOptionPane.showMessageDialog(ppbtn, "잘못된 입력값입니다." + ex.getMessage());
					}
						
			}
		});
		contentPane.setLayout(null);
		
		txtManager = new JTextField();
		txtManager.setHorizontalAlignment(SwingConstants.CENTER);
		txtManager.setText("MANAGER");
		txtManager.setFont(customFont2.deriveFont(Font.PLAIN, 50));
		txtManager.setBounds(223, 128, 195, 62);
		contentPane.add(txtManager);
		txtManager.setColumns(10);
		contentPane.add(ppbtn);
		
		JButton reservbtn = new JButton("예약 명단 관리"); 
		reservbtn.setHorizontalAlignment(SwingConstants.LEFT);
		reservbtn.setFont(customFont2.deriveFont(Font.PLAIN, 20));
	     // JButton 테두리와 배경 제거
		reservbtn.setBorderPainted(false);   // 테두리 안 그림
		reservbtn.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
		reservbtn.setFocusPainted(false);    // 선택 시 테두리 안 그림
		reservbtn.setOpaque(false);
		// 텍스트 위치 조정
		reservbtn.setHorizontalTextPosition(SwingConstants.CENTER); // 수평 중앙
		reservbtn.setVerticalTextPosition(SwingConstants.BOTTOM);   // 아이콘 아래 텍스트
		reservbtn.setBounds(223, 271, 195, 67);
		reservbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				WaitingList frame = null;
				try {
					frame = new WaitingList();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	               frame.setVisible(true);
	               
				
			}
		});
		contentPane.add(reservbtn);
		
		JButton orderbtn = new JButton("매출 그래프 출력");
		orderbtn.setHorizontalAlignment(SwingConstants.LEFT);
		orderbtn.setFont(customFont2.deriveFont(Font.PLAIN, 20));
	     // JButton 테두리와 배경 제거
		orderbtn.setBorderPainted(false);   // 테두리 안 그림
		orderbtn.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
		orderbtn.setFocusPainted(false);    // 선택 시 테두리 안 그림
		orderbtn.setOpaque(false);
		orderbtn.setHorizontalTextPosition(SwingConstants.CENTER); // 수평 중앙
		orderbtn.setVerticalTextPosition(SwingConstants.BOTTOM);   // 아이콘 아래 텍스트
		
		orderbtn.setBounds(218, 349, 237, 74);
		orderbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		orderbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				order = JOptionPane.showInputDialog("운영하고 계시는 매장id를 써주세요. (101~105)");
				if (order != null) {
					//MatjipDAO matdao = new MatjipDAO(); 
					JOptionPane.showMessageDialog(orderbtn, "선택하신 매장의 매출 정보를 표시합니다.");
					try {
						CircleChartDAO matdao = new CircleChartDAO();
						List<String[]> success = matdao.circleChart(order);
						if (!success.isEmpty()) {
							 // 여기서 CircleChart 객체 생성 후 매장 ID 전달
					      
					        new CircleChart().showChart(order);  // 여기서 order 값 사용!
						}
						
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showInputDialog("101 ~ 105값을 입력해주세요.");
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(orderbtn, "잘못된 입력값입니다.");
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(orderbtn);
		
		JButton cancelbtn = new JButton("");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		cancelbtn.setIcon(new ImageIcon(Manage.class.getResource("/img_png/iconmonstr-arrow-left-alt-filled-64.png")));
		cancelbtn.setBorderPainted(false);   // 테두리 안 그림
		cancelbtn.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
		cancelbtn.setFocusPainted(false);    // 선택 시 테두리 안 그림
		cancelbtn.setOpaque(false);
		cancelbtn.setBounds(63, 10, 97, 62);
		cancelbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
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
		contentPane.add(cancelbtn);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(Color.WHITE);
		panel_1_1_1.setBounds(122, 105, 427, 18);
		contentPane.add(panel_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Matjip On");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(customFont);
		lblNewLabel_2.setBounds(165, 0, 334, 83);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.DARK_GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(106, 93, 10, 420);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(63, 72, 542, 46);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.DARK_GRAY);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(556, 93, 10, 420);
		contentPane.add(panel_2);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.DARK_GRAY);
		panel_1_1.setBounds(115, 503, 450, 10);
		contentPane.add(panel_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Manage.class.getResource("/img_png/iconmonstr-cook-3-48.png")));
		lblNewLabel_4.setBounds(165, 353, 48, 67);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setIcon(new ImageIcon(Manage.class.getResource("/img_png/iconmonstr-cook-3-48.png")));
		lblNewLabel_4_1.setBounds(165, 271, 48, 67);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.setIcon(new ImageIcon(Manage.class.getResource("/img_png/iconmonstr-cook-3-48.png")));
		lblNewLabel_4_2.setBounds(165, 194, 48, 67);
		contentPane.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("");
		lblNewLabel_4_3.setIcon(new ImageIcon(Manage.class.getResource("/img_png/iconmonstr-cook-3-48.png")));
		lblNewLabel_4_3.setBounds(165, 430, 48, 67);
		contentPane.add(lblNewLabel_4_3);
		
		JButton btnNewButton = new JButton("리뷰관리");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//매니저 리뷰 창 띄우기
				try {
					ReviewM managerReview = new ReviewM();
					dispose();
					managerReview.setVisible(true);
				} catch (ClassNotFoundException | SQLException | FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnNewButton.setFont(customFont2.deriveFont(Font.PLAIN, 20));
	     // JButton 테두리와 배경 제거
		btnNewButton.setBorderPainted(false);   // 테두리 안 그림
		btnNewButton.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
		btnNewButton.setFocusPainted(false);    // 선택 시 테두리 안 그림
		btnNewButton.setOpaque(false);
		btnNewButton.setBounds(228, 433, 190, 55);
		contentPane.add(btnNewButton);
	}
}
