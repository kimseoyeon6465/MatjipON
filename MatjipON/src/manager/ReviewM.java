package manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import matjipDAO.MatjipDAO;
import matjipDAO.ReviewDAO;
import matjipDAO.WaitingDAO.LoginSession;
import matjipVO.MatjipVO;

public class ReviewM extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable reviewtable;
	public int reviewid;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ReviewM frame = new ReviewM();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws FontFormatException
	 */
	public ReviewM() throws ClassNotFoundException, SQLException, FontFormatException, IOException {

		////////////////////더 안전한 resource 불러오기 방법
		InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
		Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);

		InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
		Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblShowReview = new JLabel("우리 매장 리뷰");
		lblShowReview.setFont(customFont2.deriveFont(Font.PLAIN, 25));
		lblShowReview.setBounds(145, 147, 220, 38);
		contentPane.add(lblShowReview);

		matjipDAO.ReviewDAO redao = new matjipDAO.ReviewDAO();
		MatjipDAO dao = new MatjipDAO();
		int mid = dao.getmidForReview(LoginSession.loginUserId);
		System.out.println("넘어온 세션맛집아이디" + mid);
		ReviewDAO.update_review(mid);
		ArrayList<MatjipVO> reviewarray = redao.getAllInfoReview(mid);

		String[] colNames = { "리뷰아이디", "이름", "음식점", "리뷰", "별점" };
		DefaultTableModel model = new DefaultTableModel(colNames, 0);

		for (MatjipVO vo : reviewarray) {
			reviewid = vo.getReviewid();
			Object[] row = { vo.getReviewid(), vo.getUserid(), vo.getMatjipid(), vo.getReviewtext(), vo.getRating() };
			model.addRow(row);
		}

		reviewtable = new JTable(model);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(reviewtable);
		scrollPane.setBounds(145, 200, 398, 118);

		contentPane.add(scrollPane);

		JButton btndelete = new JButton("Delete");
		// JButton 테두리와 배경 제거
		btndelete.setBorderPainted(false); // 테두리 안 그림
		btndelete.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
		btndelete.setFocusPainted(false); // 선택 시 테두리 안 그림
		btndelete.setOpaque(false);
		btndelete.setFont(customFont2.deriveFont(Font.PLAIN, 25));
//		btndelete.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		btndelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// deleteReview호출
				int selectedRow = reviewtable.getSelectedRow();
				System.out.println(selectedRow);
				int reviewId = Integer.parseInt(reviewtable.getValueAt(selectedRow, 0).toString());
//				System.out.println(reviewId);
				try {

					redao.deleteReview(reviewId);
					System.out.println(reviewId);
					ReviewDAO.update_review(mid);
					model.setRowCount(0);// jdbc 화면 초기화

					ArrayList<MatjipVO> newReviewarray = redao.getAllInfoReview(mid);
					for (MatjipVO vo : newReviewarray) {
						Object[] row = { vo.getReviewid(), vo.getUserid(), vo.getMatjipid(), vo.getReviewtext(),
								vo.getRating() };
						model.addRow(row);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btndelete.setBounds(401, 400, 124, 60);
		contentPane.add(btndelete);

		JLabel lblRating = new JLabel(String.valueOf(redao.getAvgRating(mid)));// 수정해야함
		lblRating.setFont(customFont2.deriveFont(Font.PLAIN, 30));
		lblRating.setBounds(206, 400, 97, 38);
		contentPane.add(lblRating);

		JButton btnBack = new JButton("");
		btnBack.setOpaque(false);
		btnBack.setIcon(new ImageIcon(ReviewM.class.getResource("/img_png/iconmonstr-arrow-left-alt-filled-64.png")));
		btnBack.setFocusPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(60, 5, 95, 60);
		contentPane.add(btnBack);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.DARK_GRAY);
		panel_1_1.setBounds(111, 725, 450, 10);
		contentPane.add(panel_1_1);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(UIManager.getColor("Button.background"));
		panel_1_1_1.setBounds(129, 105, 427, 18);
		contentPane.add(panel_1_1_1);

		JLabel lblNewLabel_2 = new JLabel("Matjip On");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(customFont);
		lblNewLabel_2.setBounds(172, 0, 334, 83);
		contentPane.add(lblNewLabel_2);

		JPanel panel = new JPanel();
		panel.setForeground(Color.DARK_GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(113, 93, 10, 420);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(70, 72, 542, 46);
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.DARK_GRAY);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(563, 93, 10, 420);
		contentPane.add(panel_2);

		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setBackground(Color.DARK_GRAY);
		panel_1_1_2.setBounds(122, 503, 450, 10);
		contentPane.add(panel_1_1_2);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ReviewM.class.getResource("/img_png/iconmonstr-star-filled-64.png")));
		lblNewLabel.setBounds(131, 394, 63, 63);
		contentPane.add(lblNewLabel);

		JPanel panel_1_1_2_1 = new JPanel();
		panel_1_1_2_1.setBackground(Color.DARK_GRAY);
		panel_1_1_2_1.setBounds(153, 338, 41, 5);
		contentPane.add(panel_1_1_2_1);

		JPanel panel_1_1_2_1_1 = new JPanel();
		panel_1_1_2_1_1.setBackground(Color.DARK_GRAY);
		panel_1_1_2_1_1.setBounds(206, 338, 41, 5);
		contentPane.add(panel_1_1_2_1_1);

		JPanel panel_1_1_2_1_2 = new JPanel();
		panel_1_1_2_1_2.setBackground(Color.DARK_GRAY);
		panel_1_1_2_1_2.setBounds(259, 338, 41, 5);
		contentPane.add(panel_1_1_2_1_2);

		JPanel panel_1_1_2_1_3 = new JPanel();
		panel_1_1_2_1_3.setBackground(Color.DARK_GRAY);
		panel_1_1_2_1_3.setBounds(312, 338, 41, 5);
		contentPane.add(panel_1_1_2_1_3);

		JPanel panel_1_1_2_1_4 = new JPanel();
		panel_1_1_2_1_4.setBackground(Color.DARK_GRAY);
		panel_1_1_2_1_4.setBounds(365, 338, 41, 5);
		contentPane.add(panel_1_1_2_1_4);

		JPanel panel_1_1_2_1_5 = new JPanel();
		panel_1_1_2_1_5.setBackground(Color.DARK_GRAY);
		panel_1_1_2_1_5.setBounds(418, 338, 41, 5);
		contentPane.add(panel_1_1_2_1_5);

		JPanel panel_1_1_2_1_6 = new JPanel();
		panel_1_1_2_1_6.setBackground(Color.DARK_GRAY);
		panel_1_1_2_1_6.setBounds(471, 338, 41, 5);
		contentPane.add(panel_1_1_2_1_6);

		JPanel panel_1_1_2_1_7 = new JPanel();
		panel_1_1_2_1_7.setBackground(Color.DARK_GRAY);
		panel_1_1_2_1_7.setBounds(524, 338, 41, 5);
		contentPane.add(panel_1_1_2_1_7);

		JPanel panel_1_1_2_1_8 = new JPanel();
		panel_1_1_2_1_8.setBackground(Color.DARK_GRAY);
		panel_1_1_2_1_8.setBounds(114, 338, 25, 5);
		contentPane.add(panel_1_1_2_1_8);
	}

}
