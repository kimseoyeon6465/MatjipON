package customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import matjipDAO.ClickedDAO;
import matjipDAO.ReviewDAO;
import matjipDAO.ReviewDAO.MatjipSession;
import matjipVO.MatjipVO;

public class ReviewView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable ReviewTable;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            // Entry point, not used directly here
        });
    }

    public ReviewView(String id, int matid) throws FontFormatException, IOException, ClassNotFoundException, SQLException {

        InputStream is = getClass().getResourceAsStream("/fonts/Receipt_Demo.ttf");
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 45f);

        InputStream is2 = getClass().getResourceAsStream("/fonts/DungGeunMo.ttf");
        Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, is2);

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 708, 706);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_1_1_1 = new JPanel();
        panel_1_1_1.setBounds(87, 110, 427, 18);
        panel_1_1_1.setBackground(Color.WHITE);
        contentPane.add(panel_1_1_1);

        JPanel panel = new JPanel();
        panel.setBounds(71, 98, 10, 420);
        panel.setBackground(Color.DARK_GRAY);
        contentPane.add(panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(28, 77, 542, 46);
        panel_1.setBackground(Color.DARK_GRAY);
        contentPane.add(panel_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(521, 98, 10, 420);
        panel_2.setBackground(Color.DARK_GRAY);
        contentPane.add(panel_2);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBounds(80, 508, 450, 10);
        panel_1_1.setBackground(Color.DARK_GRAY);
        contentPane.add(panel_1_1);

        ReviewDAO Reviewdao = new ReviewDAO();
        String searchname = Reviewdao.view3(matid);

        JLabel lblNewLabel_2 = new JLabel(searchname);
        lblNewLabel_2.setBounds(137, 10, 334, 83);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(customFont2.deriveFont(Font.BOLD, 45));
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel = new JLabel("REVIEW");
        lblNewLabel.setBounds(236, 127, 180, 89);
        lblNewLabel.setFont(customFont2.deriveFont(Font.BOLD, 45));
        contentPane.add(lblNewLabel);

        JButton btnBack = new JButton("");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Select_List d1;
                try {
                    d1 = new Select_List();
                    d1.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnBack.setOpaque(false);
        btnBack.setIcon(new ImageIcon(ReviewView.class.getResource("/img_png/iconmonstr-arrow-left-alt-filled-64.png")));
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setBounds(28, 10, 95, 60);
        contentPane.add(btnBack);

        JPanel reviewListPanel = new JPanel();
        reviewListPanel.setLayout(new BoxLayout(reviewListPanel, BoxLayout.Y_AXIS));
        reviewListPanel.setBackground(Color.WHITE);

        JLabel lblRabbit2 = new JLabel();
        lblRabbit2.setPreferredSize(new Dimension(104, 96));
        lblRabbit2.setAlignmentX(LEFT_ALIGNMENT);
        reviewListPanel.add(lblRabbit2);

        ImageIcon icon2 = new ImageIcon(getClass().getResource("/img_png/Rabbit03.jpg"));
        Image image = icon2.getImage().getScaledInstance(104, 96, Image.SCALE_SMOOTH);
        lblRabbit2.setIcon(new ImageIcon(image));

        ArrayList<MatjipVO> reviewarray = Reviewdao.getAllInfoReview2(matid);
        String[] reviewName = {"작성자", "별점", "리뷰"};

        DefaultTableModel model = new DefaultTableModel(reviewName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (MatjipVO vo : reviewarray) {
            Object[] row = {vo.getUserid(), vo.getRating(), vo.getReviewtext()};
            model.addRow(row);
        }

        ReviewTable = new JTable(model);
        resizeColumnWidth(ReviewTable);

        JScrollPane scrollPane = new JScrollPane(ReviewTable);
        scrollPane.setBounds(90, 200, 427, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPane.add(scrollPane);
    }

    // ✅ 테이블 컬럼 너비 자동 조절 메서드
    private void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50;
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
