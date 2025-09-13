package customer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.JTableHeader;

import matjipON.Kiosk;
import matjipVO.OrderVO;

public class ShowOrder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable myOrderTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ShowOrder frame = new ShowOrder();
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
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws FontFormatException
	 */
	public ShowOrder() throws ClassNotFoundException, SQLException, FontFormatException, IOException {

		////////////////////더 안전한 resource 불러오기 방법
		InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
		Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);

		InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
		Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);

		Font headerFont = customFont2.deriveFont(Font.PLAIN, 14f); // 헤더용 폰트

//	.setFont(customFont); //커스텀 폰트 사용
//	.setFont(customFont2.deriveFont(Font.PLAIN, 14));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblShoworder = new JLabel("ORDER");
		lblShoworder.setFont(customFont.deriveFont(Font.PLAIN, 23));
		lblShoworder.setBounds(129, 170, 184, 43);
		contentPane.add(lblShoworder);

		JButton btnNewButton = new JButton("결제하기");
		// JButton 테두리와 배경 제거
		btnNewButton.setBorderPainted(false); // 테두리 안 그림
		btnNewButton.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
		btnNewButton.setFocusPainted(false); // 선택 시 테두리 안 그림
		btnNewButton.setOpaque(false);
		btnNewButton.setFont(customFont2.deriveFont(Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(123, 412, 128, 48);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("리뷰쓰기");
		btnNewButton_1.setFont(customFont2.deriveFont(Font.PLAIN, 18));
		// JButton 테두리와 배경 제거
		btnNewButton_1.setBorderPainted(false); // 테두리 안 그림
		btnNewButton_1.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
		btnNewButton_1.setFocusPainted(false); // 선택 시 테두리 안 그림
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				dispose(); // 현재 창 닫기
				ReviewC rc = null;
				try {
					rc = new ReviewC();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				rc.setVisible(true); // 로그인 창 다시 열기
			}
		});

		btnNewButton_1.setBounds(416, 412, 134, 48);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("Matjip On");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(customFont);
		lblNewLabel_2.setBounds(166, 10, 334, 83);
		contentPane.add(lblNewLabel_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(64, 82, 542, 46);
		contentPane.add(panel_1);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(UIManager.getColor("Button.background"));
		panel_1_1_1.setBounds(123, 115, 427, 18);
		contentPane.add(panel_1_1_1);

		JPanel panel = new JPanel();
		panel.setForeground(Color.DARK_GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(107, 103, 10, 420);
		contentPane.add(panel);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.DARK_GRAY);
		panel_1_1.setBounds(116, 513, 450, 10);
		contentPane.add(panel_1_1);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.DARK_GRAY);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(557, 103, 10, 420);
		contentPane.add(panel_2);

		OrderDAO ordao = new OrderDAO();
//		OldKiosk kio = new OldKiosk();
		Kiosk newkio = new Kiosk();
		ArrayList<OrderVO> myorderarray = ordao.getMyOrder(newkio.newOrderId);
		System.out.println("주문번호" + newkio.newOrderId);

		String[] colNames = { "주문번호", "음식점", "합계", "주문시간" };
		DefaultTableModel model = new DefaultTableModel(colNames, 0);

		for (OrderVO vo : myorderarray) {
			Object[] row = { vo.getOrderid(), vo.getMatjipid(), vo.getSumprice(), vo.getOrdertime() };
			model.addRow(row);
		}
		myOrderTable = new JTable(model);

		myOrderTable.setFont(new Font("둥근모꼴", Font.PLAIN, 13)); // 폰트 적용
		myOrderTable.setRowHeight(25); // 폰트에 맞춰 줄 높이 조정
		// 헤더에 커스텀 폰트 적용
		JTableHeader tableHeader = myOrderTable.getTableHeader();
		tableHeader.setFont(customFont2.deriveFont(Font.BOLD, 16f));

		JScrollPane scrollPane = new JScrollPane(myOrderTable);
		scrollPane.setViewportView(myOrderTable);
		scrollPane.setBounds(123, 229, 427, 48);
		contentPane.add(scrollPane);

//		// 세로로 보이도록 행에 이름과 값을 나열
//		ArrayList<OrderVO> myorderarray = ordao.getMyOrder(kio.neworderid);
//
//		if (!myorderarray.isEmpty()) {
//		    OrderVO vo = myorderarray.get(0); // 최신 주문 1건 기준

//		OrderVO vo = new OrderVO(); //보이나 테스트용
//		vo.setOrderid(1234);
//		vo.setMatjipid(99);
//		vo.setSumprice(15000);
//		vo.setOrdertime(new Timestamp(System.currentTimeMillis())); // java.sql.Timestamp import 필요

//		    String[] labels = {"주문번호", "음식점", "합계", "주문시간"};
//		    String[] values = {
//		        String.valueOf(vo.getOrderid()),
//		        String.valueOf(vo.getMatjipid()),  // 혹시 matjip 이름이 아니라 ID면 별도 변환 필요
//		        String.valueOf(vo.getSumprice()),
//		        String.valueOf(vo.getOrdertime())
//		    };
//
//		    DefaultTableModel model = new DefaultTableModel(new String[]{"항목", "내용"}, 0);
//		    for (int i = 0; i < labels.length; i++) {
//		        model.addRow(new Object[]{labels[i], values[i]});
//		    }
//
//		    JTable receiptTable = new JTable(model);
//		    receiptTable.setFont(customFont2.deriveFont(16f));
//		    receiptTable.setRowHeight(30);
//		    receiptTable.setEnabled(false); // 사용자 편집 불가
//		    receiptTable.setTableHeader(null);
//		    receiptTable.setShowGrid(false);
//		    receiptTable.setIntercellSpacing(new Dimension(0, 0));
//		JTableHeader header = receiptTable.getTableHeader();
//		header.setFont(customFont2.deriveFont(Font.BOLD, 17f));
//
//		// 스크롤 없이도 볼 수 있게 크기 조절
//		receiptTable.setPreferredScrollableViewportSize(receiptTable.getPreferredSize());
//		
//		receiptTable.setShowGrid(false);
//		receiptTable.setIntercellSpacing(new Dimension(0, 0));
//		receiptTable.setTableHeader(null); //

		// 핵심: 테이블 크기 설정
		// receiptTable.setPreferredScrollableViewportSize(new Dimension(400, 120));

		// 패널에 추가
//		JScrollPane scrollPane = new JScrollPane(receiptTable);
		scrollPane.setBounds(123, 229, 427, 160); // 높이를 넉넉히 확보하세요
		contentPane.add(scrollPane);

		JLabel lblNewLabel = new JLabel("-----------------------------");
		lblNewLabel.setBounds(123, 203, 427, 23);
		lblNewLabel.setFont(customFont.deriveFont(Font.BOLD, 23));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("-----------------------------");
		lblNewLabel_1.setBounds(123, 399, 427, 23);
		lblNewLabel_1.setFont(customFont.deriveFont(Font.BOLD, 23));
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("-----------------------------");
		lblNewLabel_1_1.setBounds(123, 458, 427, 23);
		contentPane.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(customFont.deriveFont(Font.BOLD, 23));

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3
				.setIcon(new ImageIcon(ShowOrder.class.getResource("/img_png/iconmonstr-triangle-filled-48 (1).png")));
		lblNewLabel_3.setBounds(107, 488, 52, 35);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("New label");
		lblNewLabel_3_1
				.setIcon(new ImageIcon(ShowOrder.class.getResource("/img_png/iconmonstr-triangle-filled-48 (1).png")));
		lblNewLabel_3_1.setBounds(149, 488, 52, 35);
		contentPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("New label");
		lblNewLabel_3_2
				.setIcon(new ImageIcon(ShowOrder.class.getResource("/img_png/iconmonstr-triangle-filled-48 (1).png")));
		lblNewLabel_3_2.setBounds(186, 488, 52, 35);
		contentPane.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_3 = new JLabel("New label");
		lblNewLabel_3_3
				.setIcon(new ImageIcon(ShowOrder.class.getResource("/img_png/iconmonstr-triangle-filled-48 (1).png")));
		lblNewLabel_3_3.setBounds(227, 488, 52, 35);
		contentPane.add(lblNewLabel_3_3);

		JLabel lblNewLabel_3_4 = new JLabel("New label");
		lblNewLabel_3_4
				.setIcon(new ImageIcon(ShowOrder.class.getResource("/img_png/iconmonstr-triangle-filled-48 (1).png")));
		lblNewLabel_3_4.setBounds(275, 488, 52, 35);
		contentPane.add(lblNewLabel_3_4);

		JLabel lblNewLabel_3_5 = new JLabel("New label");
		lblNewLabel_3_5
				.setIcon(new ImageIcon(ShowOrder.class.getResource("/img_png/iconmonstr-triangle-filled-48 (1).png")));
		lblNewLabel_3_5.setBounds(319, 488, 52, 35);
		contentPane.add(lblNewLabel_3_5);

		JLabel lblNewLabel_3_6 = new JLabel("New label");
		lblNewLabel_3_6
				.setIcon(new ImageIcon(ShowOrder.class.getResource("/img_png/iconmonstr-triangle-filled-48 (1).png")));
		lblNewLabel_3_6.setBounds(363, 488, 52, 35);
		contentPane.add(lblNewLabel_3_6);

		JLabel lblNewLabel_3_7 = new JLabel("New label");
		lblNewLabel_3_7
				.setIcon(new ImageIcon(ShowOrder.class.getResource("/img_png/iconmonstr-triangle-filled-48 (1).png")));
		lblNewLabel_3_7.setBounds(409, 488, 52, 35);
		contentPane.add(lblNewLabel_3_7);

		JLabel lblNewLabel_3_8 = new JLabel("New label");
		lblNewLabel_3_8
				.setIcon(new ImageIcon(ShowOrder.class.getResource("/img_png/iconmonstr-triangle-filled-48 (1).png")));
		lblNewLabel_3_8.setBounds(448, 488, 52, 35);
		contentPane.add(lblNewLabel_3_8);

		JLabel lblNewLabel_3_9 = new JLabel("New label");
		lblNewLabel_3_9
				.setIcon(new ImageIcon(ShowOrder.class.getResource("/img_png/iconmonstr-triangle-filled-48 (1).png")));
		lblNewLabel_3_9.setBounds(483, 488, 52, 35);
		contentPane.add(lblNewLabel_3_9);

		JLabel lblNewLabel_3_10 = new JLabel("New label");
		lblNewLabel_3_10
				.setIcon(new ImageIcon(ShowOrder.class.getResource("/img_png/iconmonstr-triangle-filled-48 (1).png")));
		lblNewLabel_3_10.setBounds(515, 488, 52, 35);
		contentPane.add(lblNewLabel_3_10);
		scrollPane.setVisible(true);
		// contentPane.setComponentZOrder(scrollPane, 0);
//		}

	}
}
