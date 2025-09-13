package matjipChat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import matjipDAO.ChatBotDAO;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;

public class ChatBotStore extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void StoreInfo () {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatBotStore frame = new ChatBotStore();
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
	 */
	public ChatBotStore() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//-------------------------------------------Jtable 기본양식 전
		String[] columnNames = {"가게 이름", "카테고리", "현재 혼잡도"}; // 열이름 정의
		DefaultTableModel model = new DefaultTableModel(columnNames, 0); // 테이블 모델은 디폴트 내장함수로 가져옴
//-------------------------------------------Jtable 기본양식 후		
		table = new JTable();
		table.setBounds(12, 10, 602, 407);
//-------------------------------------------Jtable 생성 / 행추가

//		//test 시 활용
//		Object[] row1 = {"스시집", "일식", 5};
//		Object[] row2 = {"양식집", "양식", 1};
//		model.addRow(row1);
//	    model.addRow(row2);
	    
	    //DB불러오는 메인함수
		
		ChatBotDAO chatdao = new ChatBotDAO();
		List<String[]> stores = chatdao.ChatBotStore();
		for (String[] row : stores) {
			model.addRow(row);
		}
		
		
		table.setModel(model); // table을 보여주는 명령어
		
		// JTable을 JScrollPane으로 감싼다
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 10, 602, 407);
		contentPane.add(scrollPane);
		
	}
}
