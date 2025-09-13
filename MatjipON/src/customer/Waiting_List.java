package customer;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import loginUser.Userinfo;
import matjipDAO.WaitingDAO;
import matjipDAO.WaitingDAO.LoginSession;
import matjipDAO.WaitingDAO.MatjipSession;
import matjipVO.WaitingVO;

import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.Icon;

public class Waiting_List extends JFrame {
   private static Connection con;
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
               Waiting_List frame = new Waiting_List();
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
   public Waiting_List() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	   
	   
		InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
		Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);
		
		InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
		Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);	
		
		
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 700, 690);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);
      
		ImageIcon icon = new  ImageIcon(Userinfo.class.getResource("/img_png/Rabbit01.png"));
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); //가로, 세로
      

      
      JLabel lblNewLabel = new JLabel("웨이팅 적은 맛집 추천");
      lblNewLabel.setFont(customFont2.deriveFont(Font.PLAIN, 20));
      lblNewLabel.setBounds(202, 138, 257, 36);
      contentPane.add(lblNewLabel);
      //여기부터
      WaitingDAO matdao = new WaitingDAO();//객체 선언
      ArrayList<WaitingVO> recommendarray = matdao.getAllInfoMatjip();//db에 있는 데이터 꺼내서 arraylist에 저장
      
      String[] colNames = {"MATJIP_ID","맛집", "혼잡도"};//첫째줄에 출력할 칼럼명
      DefaultTableModel model = new DefaultTableModel(colNames, 0){//객체 선언 후 colNames를 맨 위에 출력
          @Override
          public boolean isCellEditable(int row, int column) {
             return false; // 모든 셀 수정 불가
        }
       };
      for(WaitingVO vo : recommendarray)//recommendarray에 값이 있을때
      {
    	  String himdeulda = "";
    	  switch(vo.getBusylevel()) {
          case 1:
             himdeulda = "한산";
              break;
          case 2:
             himdeulda = "보통";
              break;
          case 3:
             himdeulda = "혼잡";
              break;
          default:
             himdeulda = "알 수 없음";
              break;
      }
         Object[] row = {vo.getMatjipid(),vo.getMatjipname(), himdeulda};//busylevel 자료형은 int로 수정, 값을 getter로 꺼내서 저장
         model.addRow(row);//한줄씩 추가
         
      }
      JTable table = new JTable(model);//Jtable 객체 선언
   // 🔽 MATJIP_ID 컬럼 숨기기 (index 0)
     table.getColumnModel().getColumn(0).setMinWidth(0);
      table.getColumnModel().getColumn(0).setMaxWidth(0);
      table.getColumnModel().getColumn(0).setWidth(0);
   
      // 가운데 정렬
      DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
      centerRenderer.setHorizontalAlignment(JLabel.CENTER);

      for (int i = 0; i < table.getColumnCount(); i++) {
          table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
      }
      
      table.setBackground(new Color(255, 179, 102));		//스크롤패널 색상
      table.setBounds(34, 71, 366, 74);
       
      JTableHeader tableHeader = table.getTableHeader();
      tableHeader.setFont(customFont2.deriveFont(Font.BOLD, 16f));
      
      //Jtable 자체는 스크롤을 지원하지 않으므로 scrollPane에 갖다 붙여야함.
      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            
        	 
        	
         }
      });
      scrollPane.setBounds(110, 218, 416, 86);  // 원래 table 위치 그대로 사용
      contentPane.add(scrollPane);
      
      JButton btnNewButton = new JButton("-----------GO WAITING-----------");
      btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
      btnNewButton.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
//      btnNewButton.addActionListener(new ActionListener() {
//      	public void actionPerformed(ActionEvent e) {
//      	}
//      });
      btnNewButton.setFont(customFont.deriveFont(Font.BOLD, 20));
	     // JButton 테두리와 배경 제거
      btnNewButton.setBorderPainted(false);   // 테두리 안 그림
      btnNewButton.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
      btnNewButton.setFocusPainted(false);    // 선택 시 테두리 안 그림
      btnNewButton.setOpaque(false);
      btnNewButton.addMouseListener(new MouseAdapter() {
        @Override
         public void mouseClicked(MouseEvent e) {
           
          try {
             
            
           
            int row = table.getSelectedRow(); // 클릭된 행 번호

            if (row != -1) { // 유효한 행이 선택되었을 때만
               int matjipId = (int) table.getValueAt(row, 0);
                  MatjipSession.selectedMatjipId = matjipId;
               String matjipName = (String) table.getValueAt(row, 1); // 클릭된 행의 MATJIP_NAME 값
               String id = LoginSession.loginUserId;  // 로그인된 사용자 ID
            
               WaitingDAO matdao = new WaitingDAO();
              
               // "웨이팅 하시겠습니까?" 라는 팝업창 띄우기
               int response = JOptionPane.showConfirmDialog(null,  matjipName + " 맛집에 웨이팅 하시겠습니까?",  "웨이팅 확인", JOptionPane.YES_NO_OPTION);
               if (response == JOptionPane.YES_OPTION) {
                   int result = matdao.insertWaiting(id, matjipName); // 여기는 YES 눌렀을 때만 실행됨

                   if (result > 0) {
                       JOptionPane.showMessageDialog(null, "웨이팅 요청이 완료되었습니다.");

                       Waiting_Queue w3 = new Waiting_Queue();
                       w3.setVisible(true);
                       setVisible(false);

                   } else {
                       JOptionPane.showMessageDialog(null, "웨이팅이 실패되었습니다.");
                   }
               } else {
                   // 아니오나 X 누른 경우 처리
                   JOptionPane.showMessageDialog(null, "웨이팅이 취소되었습니다.");
               }
            }
          }
          catch (Exception ex) {
             ex.printStackTrace();
             JOptionPane.showMessageDialog(null, "에러 발생: " + ex.getMessage());
         }
      }
      });
      
      btnNewButton.setBounds(104, 457, 444, 46);
      contentPane.add(btnNewButton);
      
      JButton btnBack = new JButton("");
      btnBack.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		
      		 Recommendation w1 = null;
			try {
				w1 = new Recommendation();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
             w1.setVisible(true);  // 창을 보이게 함      
             setVisible(false);

      		
      		
      		
      	}
      });
      btnBack.setOpaque(false);
      btnBack.setIcon(new ImageIcon(Waiting_List.class.getResource("/img_png/iconmonstr-arrow-left-alt-filled-64.png")));
      btnBack.setFocusPainted(false);
      btnBack.setContentAreaFilled(false);
      btnBack.setBorderPainted(false);
      btnBack.setBounds(45, 22, 95, 60);
      contentPane.add(btnBack);
      
      JPanel panel_1_1_1 = new JPanel();
      panel_1_1_1.setBackground(new Color(240, 240, 240));
      panel_1_1_1.setBounds(104, 115, 427, 18);
      contentPane.add(panel_1_1_1);
      
      JLabel lblNewLabel_2 = new JLabel("Matjip On");
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2.setFont(customFont);
      lblNewLabel_2.setBounds(147, 10, 334, 83);
      contentPane.add(lblNewLabel_2);
      
      JPanel panel = new JPanel();
      panel.setForeground(Color.DARK_GRAY);
      panel.setBackground(Color.DARK_GRAY);
      panel.setBounds(88, 103, 10, 420);
      contentPane.add(panel);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(Color.DARK_GRAY);
      panel_1.setBounds(45, 82, 542, 46);
      contentPane.add(panel_1);
      
      JPanel panel_2 = new JPanel();
      panel_2.setForeground(Color.DARK_GRAY);
      panel_2.setBackground(Color.DARK_GRAY);
      panel_2.setBounds(538, 103, 10, 420);
      contentPane.add(panel_2);
      
      JPanel panel_1_1 = new JPanel();
      panel_1_1.setBackground(Color.DARK_GRAY);
      panel_1_1.setBounds(97, 513, 450, 10);
      contentPane.add(panel_1_1);
      
      JLabel lblNewLabel_1 = new JLabel("----------------------------");
      lblNewLabel_1.setBounds(109, 185, 417, 23);
      lblNewLabel_1.setFont(customFont.deriveFont(Font.BOLD, 23));
      contentPane.add(lblNewLabel_1);
      
      JLabel lblNewLabel_1_1 = new JLabel("----------------------------");
      lblNewLabel_1_1.setBounds(110, 314, 417, 23);
      lblNewLabel_1_1.setFont(customFont.deriveFont(Font.BOLD, 23));
      contentPane.add(lblNewLabel_1_1);
      
      
    //이미지 조절	
    		ImageIcon icon5 = new  ImageIcon(Waiting_List.class.getResource("/img_png/Rabbit01.png"));
            Image img5 = icon5.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); //가로, 세로
      JButton btnNewButton_1 = new JButton(new ImageIcon(img5)); // JButton에 이미지 삽입
      btnNewButton_1.setBounds(240, 329, 159, 134);
      btnNewButton_1.setBorderPainted(false);
      btnNewButton_1.setContentAreaFilled(false);
      btnNewButton_1.setFocusPainted(false);
      btnNewButton_1.setOpaque(false);

      btnNewButton_1.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
              try {
                  int row = table.getSelectedRow();

                  if (row != -1) {
                      int matjipId = (int) table.getValueAt(row, 0);
                      MatjipSession.selectedMatjipId = matjipId;
                      String matjipName = (String) table.getValueAt(row, 1);
                      String id = LoginSession.loginUserId;

                      WaitingDAO matdao = new WaitingDAO();
                      int response = JOptionPane.showConfirmDialog(null, matjipName + " 맛집에 웨이팅 하시겠습니까?", "웨이팅 확인", JOptionPane.YES_NO_OPTION);
                      if (response == JOptionPane.YES_OPTION) {
                          int result = matdao.insertWaiting(id, matjipName);
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
                  }
              } catch (Exception ex) {
                  ex.printStackTrace();
                  JOptionPane.showMessageDialog(null, "에러 발생: " + ex.getMessage());
              }
          }
      });

      contentPane.add(btnNewButton_1);

      
        
   }
 }

