package manager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import matjipDAO.MatjipDAO;
import matjipVO.MatjipVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FontFormatException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class WaitingList extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTable table;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               
               WaitingList frame = new WaitingList();
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
    */
   public WaitingList() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	   
	   
////////////////////더 안전한 resource 불러오기 방법
InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);

InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);	


      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 698, 695);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JPanel panel_1_1_1 = new JPanel();
      panel_1_1_1.setBackground(UIManager.getColor("Button.background"));
      panel_1_1_1.setBounds(129, 105, 427, 18);
      contentPane.add(panel_1_1_1);
      
      JLabel lblWaiting = new JLabel("예약명단관리");
      lblWaiting.setFont(customFont2.deriveFont(Font.PLAIN, 25));
      lblWaiting.setBounds(244, 128, 234, 72);
      contentPane.add(lblWaiting);
      
      MatjipDAO matdao = new MatjipDAO();
      matdao.update_waiting();
      ArrayList<MatjipVO> waitarray = matdao.getAllInfo(101); // DAO 호출

      String[] colNames = {"순번", "이름", "음식점", "대기시작"};
      DefaultTableModel model = new DefaultTableModel(colNames, 0);

      for (MatjipVO vo : waitarray) {
          Object[] row = { vo.getWaitingOrder(), vo.getUserid(), vo.getMatjipid(), vo.getInserttime() };
          model.addRow(row);
      }

      JTable table = new JTable(model);

      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setBounds(127, 196, 427, 144);  // 원래 table 위치 그대로 사용
      contentPane.add(scrollPane);
      
      JButton btnSelect = new JButton("삭제");
      btnSelect.setFont(customFont2.deriveFont(Font.PLAIN, 20));
      btnSelect.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
             try {
                     int selectedRow = table.getSelectedRow();
                     //System.out.println("선택된 행은: " + (selectedRow + 1) + "번째");
                     // 예: DAO 메서드 호출
                     if (selectedRow == -1) {
                        JOptionPane.showInputDialog(null,"삭제할 줄을 선택하세요.");
                        return;
                     }
                  // 선택된 row에서 순번(=waiting_order) 가져오기
                     int waitingOrder = (int) model.getValueAt(selectedRow, 0); // 0번 컬럼: 순번
                     matdao.deleteByWaitingOrder(waitingOrder);// 수정할 DAO 메서드               
                     //update문         
                     model.setRowCount(0);//jdbc 화면 초기화
                     ArrayList<MatjipVO> newWaitarray = matdao.getAllInfo(101); // DAO 호출
                     for (MatjipVO vo : newWaitarray) {
                       Object[] row = {vo.getWaitingOrder(), vo.getUserid(), vo.getMatjipid(), vo.getInserttime()};
                       model.addRow(row);
                   }

                 } catch (Exception ex) {
                     ex.printStackTrace(); // 에러 로그 출력
                     JOptionPane.showMessageDialog(null, "삭제 중 오류가 발생했습니다.");
                 }
         }
      });

      btnSelect.setBackground(Color.WHITE);
      btnSelect.setBounds(285, 388, 97, 40);
      contentPane.add(btnSelect);
      
      JButton btnBack = new JButton("");
      btnBack.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		
      	   Manage d1 = null;
		try {
			d1 = new Manage();
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
           d1.setVisible(true);
           setVisible(false);

      		
      		
      		
      	}
      });
      btnBack.setOpaque(false);
      btnBack.setIcon(new ImageIcon(WaitingList.class.getResource("/img_png/iconmonstr-arrow-left-alt-filled-64.png")));
      btnBack.setFocusPainted(false);
      btnBack.setContentAreaFilled(false);
      btnBack.setBorderPainted(false);
      btnBack.setBounds(70, 10, 95, 60);
      contentPane.add(btnBack);
      
      JLabel lblNewLabel_2 = new JLabel("Matjip On");
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2.setFont(new Font("Receipt Demo", Font.BOLD, 45));
      lblNewLabel_2.setBounds(172, 0, 334, 83);
      contentPane.add(lblNewLabel_2);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(Color.DARK_GRAY);
      panel_1.setBounds(70, 72, 542, 46);
      contentPane.add(panel_1);
      
      JPanel panel = new JPanel();
      panel.setForeground(Color.DARK_GRAY);
      panel.setBackground(Color.DARK_GRAY);
      panel.setBounds(113, 93, 10, 420);
      contentPane.add(panel);
      
      JPanel panel_1_1 = new JPanel();
      panel_1_1.setBackground(Color.DARK_GRAY);
      panel_1_1.setBounds(122, 503, 450, 10);
      contentPane.add(panel_1_1);
      
      JPanel panel_2 = new JPanel();
      panel_2.setForeground(Color.DARK_GRAY);
      panel_2.setBackground(Color.DARK_GRAY);
      panel_2.setBounds(563, 93, 10, 420);
      contentPane.add(panel_2);
               
      
   }
}
