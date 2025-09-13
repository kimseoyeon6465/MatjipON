package customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import matjipDAO.ClickedDAO;
import matjipDAO.SelectListDAO;
import matjipDAO.WaitingDAO;
import matjipDAO.WaitingDAO.LoginSession;
import matjipDAO.WaitingDAO.MatjipSession;
import matjipDBC.MatjipDBC;
import matjipVO.WaitingVO;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;

public class Select_List extends JFrame {
	private DefaultTableModel model;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	protected DefaultRowSorter<DefaultTableModel, Integer> sorter;
    int mid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Select_List frame = new Select_List();
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
	public Select_List() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		

////////////////////더 안전한 resource 불러오기 방법
InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);

InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 702);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(118, 189, 304, 60);
		textField.setFont(customFont2.deriveFont(Font.PLAIN, 25));
		contentPane.add(textField);
		textField.setColumns(10);

		
		
		//이미지 조절	
		ImageIcon icon = new  ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-magnifier-dots-lined-96.png"));
        Image img = icon.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH); //가로, 세로
        JButton btnNewButton = new JButton(new ImageIcon(img));
	
//		JButton btnNewButton = new JButton("");
//		btnNewButton.setIcon(new ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-magnifier-dots-lined-96.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String keyword = textField.getText().trim();

				// 🔄 1. 테이블 초기화
				model.setRowCount(0);

				try {
					// 🔄 2. DB에서 다시 데이터 불러오기
					SelectListDAO matdao = new SelectListDAO();
					ArrayList<WaitingVO> list = matdao.getAllMatjip();

					// 🔄 3. 키워드가 포함된 데이터만 테이블에 추가
					for (WaitingVO vo : list) {
						String name = vo.getMatjipname();
						String location = vo.getLocation();
						String category = vo.getCategory();
						if (name.contains(keyword) || location.contains(keyword) || category.contains(keyword)) {
							Object[] row = { name, location, category, vo.getBusylevel() };
							model.addRow(row);
						}
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

				/*
				 * String keyword = textField.getText().trim(); // ✅ 올바른 텍스트필드 참조 if
				 * (keyword.length() == 0) { sorter.setRowFilter(null); // 전체 출력 } else {
				 * sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword)); // 대소문자 구분 없이
				 * 필터링 }
				 */
			}
		});
		btnNewButton.setBorderPainted(false);   // 테두리 안 그림
		btnNewButton.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
		btnNewButton.setFocusPainted(false);    // 선택 시 테두리 안 그림
		btnNewButton.setOpaque(false);
		btnNewButton.setBounds(434, 182, 77, 67);
		contentPane.add(btnNewButton);

		String[] columnNames = { "맛집 이름", "위치", "카테고리", "혼잡도" };
		model = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // 모든 셀 수정 불가
			}
		};
		// model = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(model);
//		table.setBackground(new Color(255, 224, 179));
		sorter = new TableRowSorter<>(model); // this.sorter 필드에 저장
		table.setRowSorter(sorter);
		//
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					if (row != -1) {
						int modelRow = table.convertRowIndexToModel(row);

						String name = model.getValueAt(modelRow, 0).toString();
						//int testmatid = ((Integer) model.getValueAt(modelRow, 1));

						ClickedDAO c1 = new ClickedDAO();

						try {
							int matid = c1.selectmatjip(name);
							String id = LoginSession.loginUserId;
							//MatjipSession.selectedMatjipId = matid;
							Select_List2_Kfood detailPage = new Select_List2_Kfood(matid);
							ReviewView viewpage = new ReviewView(id,matid);
							detailPage.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(114, 281, 397, 105);
		table.setFillsViewportHeight(true);

//		scrollPane.setFont(customFont2.deriveFont(Font.PLAIN, 25));
		//////////////////////////////
//		Font headerFont = customFont2.deriveFont(Font.BOLD, 12f); // 헤더용 폰트
//		table.setFont(customFont2); // 폰트 적용
//		table.setRowHeight(25);     // 폰트에 맞춰 줄 높이 조정
		    // 헤더에 커스텀 폰트 적용
			
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(customFont2.deriveFont(Font.BOLD, 13f));
		contentPane.add(scrollPane);
		
		JButton btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				Select d1 = null;
				try {
					d1 = new Select();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            d1.setVisible(true);
	            setVisible(false);
			}
			
			
		});
		btnBack.setOpaque(false);
		btnBack.setIcon(new ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-arrow-left-alt-filled-64.png")));
		btnBack.setFocusPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(45, 10, 95, 60);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_2 = new JLabel("Matjip On");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Receipt Demo", Font.BOLD, 45));
		lblNewLabel_2.setBounds(147, 10, 334, 83);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(45, 82, 542, 46);
		contentPane.add(panel_1);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(UIManager.getColor("Button.background"));
		panel_1_1_1.setBounds(104, 115, 427, 18);
		contentPane.add(panel_1_1_1);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.DARK_GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(88, 103, 10, 420);
		contentPane.add(panel);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.DARK_GRAY);
		panel_1_1.setBounds(97, 513, 450, 10);
		contentPane.add(panel_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.DARK_GRAY);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(538, 103, 10, 420);
		contentPane.add(panel_2);
		
		JButton btnsearch = new JButton("--------- SEARCH ---------");
		btnsearch.setOpaque(false);
		btnsearch.setFocusPainted(false);
		btnsearch.setContentAreaFilled(false);
		btnsearch.setBorderPainted(false);
		btnsearch.setFont(customFont.deriveFont(Font.BOLD, 23));
		btnsearch.setBounds(110, 138, 416, 46);
		contentPane.add(btnsearch);
		
		JLabel lblNewLabel_1_1 = new JLabel("-----------------------------");
		lblNewLabel_1_1.setBounds(104, 259, 427, 23);
		lblNewLabel_1_1.setFont(customFont.deriveFont(Font.BOLD, 23));
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
		lblNewLabel.setBounds(104, 472, 37, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_4 = new JLabel("");
		lblNewLabel_1_4.setIcon(new ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
		lblNewLabel_1_4.setBounds(147, 472, 37, 46);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
		lblNewLabel_4.setBounds(182, 472, 37, 46);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("");
		lblNewLabel_1_5.setIcon(new ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
		lblNewLabel_1_5.setBounds(219, 472, 37, 46);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
		lblNewLabel_5.setBounds(257, 472, 37, 46);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("");
		lblNewLabel_1_6.setIcon(new ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
		lblNewLabel_1_6.setBounds(298, 472, 37, 46);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
		lblNewLabel_6.setBounds(339, 472, 37, 46);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_1_9 = new JLabel("");
		lblNewLabel_1_9.setIcon(new ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
		lblNewLabel_1_9.setBounds(371, 472, 37, 46);
		contentPane.add(lblNewLabel_1_9);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
		lblNewLabel_7.setBounds(420, 472, 37, 46);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_1_10 = new JLabel("");
		lblNewLabel_1_10.setIcon(new ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
		lblNewLabel_1_10.setBounds(455, 472, 37, 46);
		contentPane.add(lblNewLabel_1_10);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(Select_List.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
		lblNewLabel_8.setBounds(494, 472, 37, 46);
		contentPane.add(lblNewLabel_8);

		// 맛집 리스트 불러오기 (DB에서 받아오는 부분이라 가정)
		SelectListDAO matdao = new SelectListDAO();

	}
}
