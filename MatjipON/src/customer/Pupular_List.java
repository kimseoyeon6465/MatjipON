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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import matjipDAO.PupularDAO;
import matjipDAO.WaitingDAO;
import matjipDAO.WaitingDAO.LoginSession;
import matjipDAO.WaitingDAO.MatjipSession;
import matjipVO.WaitingVO;

public class Pupular_List extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Pupular_List frame = new Pupular_List();
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
   public Pupular_List() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	   

////////////////////더 안전한 resource 불러오기 방법
InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);

InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);	



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setBounds(100, 100, 700, 706);
         contentPane = new JPanel();
         contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

         setContentPane(contentPane);
         contentPane.setLayout(null);
         

         
         JLabel lblNewLabel = new JLabel("MATJIP TOP RANKING");
         lblNewLabel.setBounds(203, 241, 349, 60);
         lblNewLabel.setFont(customFont.deriveFont(Font.BOLD, 30));
         contentPane.add(lblNewLabel);
          
         String[] colNames = {"MATJIP_ID","랭킹","맛집"};//첫째줄에 출력할 칼럼명
         DefaultTableModel model = new DefaultTableModel(colNames, 0){//객체 선언 후 colNames를 맨 위에 출력
             @Override
             public boolean isCellEditable(int row, int column) {
                return false; // 모든 셀 수정 불가
           }
          };
       //여기부터
         PupularDAO watdao = new PupularDAO();//객체 선언
         ArrayList<WaitingVO> recommendarray = watdao.getAllInfoMatjip();//db에 있는 데이터 꺼내서 arraylist에 저장
         
         int rank = 1;
         for(WaitingVO vo : recommendarray)//recommendarray에 값이 있을때
         {
            Object[] row = {vo.getMatjipid(),(rank++)+"위" ,vo.getMatjipname()};//busylevel 자료형은 int로 수정, 값을 getter로 꺼내서 저장
            model.addRow(row);//한줄씩 추가
            
         }
         JTable table = new JTable(model);//Jtable 객체 선언
        // 🔽 MATJIP_ID 컬럼 숨기기 (index 0)
         table.getColumnModel().getColumn(0).setMinWidth(0);
          table.getColumnModel().getColumn(0).setMaxWidth(0);
          table.getColumnModel().getColumn(0).setWidth(0);
         
      // 🔽 셀 가운데 정렬용 렌더러 설정
         DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
         centerRenderer.setHorizontalAlignment(JLabel.CENTER);

         // 모든 열(column)에 렌더러 적용
         for (int i = 0; i < table.getColumnCount(); i++) {
             table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
         }
         
         table.setBackground(new Color(255, 204, 153)); 
         table.setBounds(34, 71, 366, 74);
         
         
//         Font headerFont = customFont2.deriveFont(Font.BOLD, 13f); // 헤더용 폰트
//         table.setFont(customFont2); // 폰트 적용
//         table.setRowHeight(25);     // 폰트에 맞춰 줄 높이 조정
 // 헤더에 커스텀 폰트 적용
	
	    JTableHeader tableHeader = table.getTableHeader();
	    tableHeader.setFont(customFont2.deriveFont(Font.BOLD, 16f));
 
         //Jtable 자체는 스크롤을 지원하지 않으므로 scrollPane에 갖다 붙여야함.
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               
    
            }
         });
         scrollPane.setBounds(179, 311, 380, 86);  // 원래 table 위치 그대로 사용
         contentPane.add(scrollPane);
         
         JButton btnNewButton = new JButton("웨이팅");
	     // JButton 테두리와 배경 제거
         btnNewButton.setBorderPainted(false);   // 테두리 안 그림
         btnNewButton.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
         btnNewButton.setFocusPainted(false);    // 선택 시 테두리 안 그림
         btnNewButton.setOpaque(false);
         btnNewButton.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         	}
         });
         btnNewButton.setFont(customFont2.deriveFont(Font.PLAIN, 20));
         btnNewButton.addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent e) {
              
             try {
                
              
               int row = table.getSelectedRow(); // 클릭된 행 번호

               if (row != -1) { // 유효한 행이 선택되었을 때만
            	   int matjipId = (int) table.getValueAt(row, 0);
                   MatjipSession.selectedMatjipId = matjipId;
                  String matjipName = (String) table.getValueAt(row, 2); // 클릭된 행의 MATJIP_NAME 값
                  String id = LoginSession.loginUserId;  // 로그인된 사용자 ID
                   WaitingDAO matdao = new WaitingDAO();
                 
                  // "웨이팅 하시겠습니까?" 라는 팝업창 띄우기
                  int response = JOptionPane.showConfirmDialog(null,  matjipName + " 맛집에 웨이팅 하시겠습니까?",  "웨이팅 확인", JOptionPane.YES_NO_OPTION);
                  if (response == JOptionPane.YES_OPTION) {
                  int result = matdao.insertWaiting(id,matjipName);//테스트용
    
                  if (result > 0) {
                     // 여기에 웨이팅 관련 로직 추가
                     JOptionPane.showMessageDialog(null, "웨이팅 요청이 완료되었습니다.");
                     Waiting_Queue w3 = new Waiting_Queue();
                     w3.setVisible(true);
                     setVisible(false);   
                  setVisible(false);
                  } else {
                     // 웨이팅을 취소했을 경우의 로직
                     JOptionPane.showMessageDialog(null, "웨이팅이 실패되었습니다.");
                  }
                } else {
                  // 아니오나 X 누른 경우 처리
                  JOptionPane.showMessageDialog(null, "웨이팅이 취소되었습니다.");
              }
                  }
               
             }catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "에러 발생: " + ex.getMessage());
            }
         }
         });
         
         btnNewButton.setBounds(306, 407, 97, 23);
         contentPane.add(btnNewButton);
         
         JButton btnBack = new JButton("");
         btnBack.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		
         		Recommendation w1 = null;
				try {
					w1 = new Recommendation();
				} catch (Exception  e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                w1.setVisible(true);  // 창을 보이게 함      
              setVisible(false);

         		
         	}
         });
         btnBack.setOpaque(false);
         btnBack.setIcon(new ImageIcon(Pupular_List.class.getResource("/img_png/iconmonstr-arrow-left-alt-filled-64.png")));
         btnBack.setFocusPainted(false);
         btnBack.setContentAreaFilled(false);
         btnBack.setBorderPainted(false);
         btnBack.setBounds(77, 10, 95, 60);
         contentPane.add(btnBack);
         
         JLabel lblNewLabel_2 = new JLabel("Matjip On");
         lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_2.setFont(customFont);
         lblNewLabel_2.setBounds(179, 0, 334, 83);
         contentPane.add(lblNewLabel_2);
         
         JPanel panel_1_1_1 = new JPanel();
         panel_1_1_1.setBackground(UIManager.getColor("Button.background"));
         panel_1_1_1.setBounds(146, 95, 427, 18);
         contentPane.add(panel_1_1_1);
         
         JPanel panel = new JPanel();
         panel.setForeground(Color.DARK_GRAY);
         panel.setBackground(Color.DARK_GRAY);
         panel.setBounds(130, 83, 10, 420);
         contentPane.add(panel);
         
         JPanel panel_1 = new JPanel();
         panel_1.setBackground(Color.DARK_GRAY);
         panel_1.setBounds(87, 62, 542, 46);
         contentPane.add(panel_1);
         
         JPanel panel_2 = new JPanel();
         panel_2.setForeground(Color.DARK_GRAY);
         panel_2.setBackground(Color.DARK_GRAY);
         panel_2.setBounds(580, 83, 10, 420);
         contentPane.add(panel_2);
         
         JPanel panel_1_1 = new JPanel();
         panel_1_1.setBackground(Color.DARK_GRAY);
         panel_1_1.setBounds(139, 493, 450, 10);
         contentPane.add(panel_1_1);
         
         JLabel lblNewLabel_1 = new JLabel("");
         lblNewLabel_1.setIcon(new ImageIcon(Pupular_List.class.getResource("/img_png/iconmonstr-medal-16-120.png")));
         lblNewLabel_1.setBounds(296, 93, 129, 148);
         contentPane.add(lblNewLabel_1);
         
         JLabel lblNewLabel_1_1 = new JLabel("----------------------------");
         lblNewLabel_1_1.setFont(customFont.deriveFont(Font.BOLD, 23));
         lblNewLabel_1_1.setBounds(156, 284, 417, 23);
         contentPane.add(lblNewLabel_1_1);
         
         JLabel lblNewLabel_3 = new JLabel("");
         lblNewLabel_3.setIcon(new ImageIcon(Pupular_List.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
         lblNewLabel_3.setBounds(146, 454, 37, 46);
         contentPane.add(lblNewLabel_3);
         
         JLabel lblNewLabel_1_4 = new JLabel("");
         lblNewLabel_1_4.setIcon(new ImageIcon(Pupular_List.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
         lblNewLabel_1_4.setBounds(189, 454, 37, 46);
         contentPane.add(lblNewLabel_1_4);
         
         JLabel lblNewLabel_4 = new JLabel("");
         lblNewLabel_4.setIcon(new ImageIcon(Pupular_List.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
         lblNewLabel_4.setBounds(224, 454, 37, 46);
         contentPane.add(lblNewLabel_4);
         
         JLabel lblNewLabel_1_5 = new JLabel("");
         lblNewLabel_1_5.setIcon(new ImageIcon(Pupular_List.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
         lblNewLabel_1_5.setBounds(261, 454, 37, 46);
         contentPane.add(lblNewLabel_1_5);
         
         JLabel lblNewLabel_5 = new JLabel("");
         lblNewLabel_5.setIcon(new ImageIcon(Pupular_List.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
         lblNewLabel_5.setBounds(299, 454, 37, 46);
         contentPane.add(lblNewLabel_5);
         
         JLabel lblNewLabel_1_6 = new JLabel("");
         lblNewLabel_1_6.setIcon(new ImageIcon(Pupular_List.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
         lblNewLabel_1_6.setBounds(340, 454, 37, 46);
         contentPane.add(lblNewLabel_1_6);
         
         JLabel lblNewLabel_6 = new JLabel("");
         lblNewLabel_6.setIcon(new ImageIcon(Pupular_List.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
         lblNewLabel_6.setBounds(381, 454, 37, 46);
         contentPane.add(lblNewLabel_6);
         
         JLabel lblNewLabel_1_9 = new JLabel("");
         lblNewLabel_1_9.setIcon(new ImageIcon(Pupular_List.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
         lblNewLabel_1_9.setBounds(413, 454, 37, 46);
         contentPane.add(lblNewLabel_1_9);
         
         JLabel lblNewLabel_7 = new JLabel("");
         lblNewLabel_7.setIcon(new ImageIcon(Pupular_List.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
         lblNewLabel_7.setBounds(462, 454, 37, 46);
         contentPane.add(lblNewLabel_7);
         
         JLabel lblNewLabel_1_10 = new JLabel("");
         lblNewLabel_1_10.setIcon(new ImageIcon(Pupular_List.class.getResource("/img_png/iconmonstr-eat-5-thin-24.png")));
         lblNewLabel_1_10.setBounds(497, 454, 37, 46);
         contentPane.add(lblNewLabel_1_10);
         
         JLabel lblNewLabel_8 = new JLabel("");
         lblNewLabel_8.setIcon(new ImageIcon(Pupular_List.class.getResource("/img_png/iconmonstr-eat-1-24.png")));
         lblNewLabel_8.setBounds(536, 454, 37, 46);
         contentPane.add(lblNewLabel_8);
           
      }
}
