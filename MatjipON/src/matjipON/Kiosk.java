package matjipON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import customer.OrderDAO;
import matjipDAO.KioskMenuIdDAO;
import matjipDAO.KioskMenuPriceDAO;
import matjipDAO.PupularDAO.LoginSession;
import matjipDAO.ReviewDAO.MatjipSession;
import matjipVO.OrderVO;

@SuppressWarnings("serial")
public class Kiosk extends JFrame {
	


    private List<OrderItem> orderItems;
    private JLabel totalLabel;
    private JButton confirmButton, printButton;
    private int total = 0;

    private String userId;
    private int matjipId;
    public static int newOrderId;
    public Kiosk(String[] menuNames, int[] prices, String[] imagePaths, int[] menuIds, String userId, int matjipId) {
        this.userId = userId;
        this.matjipId = matjipId;
        this.orderItems = new ArrayList<>();

        for (int i = 0; i < menuNames.length; i++) {
            orderItems.add(new OrderItem(menuNames[i], prices[i], imagePaths[i], menuIds[i]));
        }

        setupUI();
    }

    public Kiosk() {
		// TODO Auto-generated constructor stub
	}

	private void setupUI() {
        setTitle("주문 부탁드립니다.");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 900);
        setLayout(new BorderLayout(10, 10));

        JPanel menuPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        for (OrderItem item : orderItems) {
            menuPanel.add(createMenuPanel(item));
        }

        totalLabel = new JLabel("총액: 0원");
        confirmButton = new JButton("주문확인");
        printButton = new JButton("주문내역 출력");

        confirmButton.addActionListener(e -> onConfirm());
        printButton.addActionListener(e -> showOrderSummary());

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        bottom.add(printButton);
        bottom.add(totalLabel);
        bottom.add(confirmButton);

        add(menuPanel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        setVisible(true);
    }

    private JPanel createMenuPanel(OrderItem item) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel nameLabel = new JLabel(item.name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));

        ImageIcon icon = new ImageIcon(item.imagePath);
        Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));

        JLabel priceLabel = new JLabel(item.price + "원", SwingConstants.CENTER);
        priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        JButton minusButton = new JButton("－");
        JButton plusButton = new JButton("＋");
        JLabel qtyLabel = new JLabel("0", SwingConstants.CENTER);
        
        
	     // JButton 테두리와 배경 제거
        minusButton.setBorderPainted(false);   // 테두리 안 그림
        minusButton.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
        minusButton.setFocusPainted(false);    // 선택 시 테두리 안 그림
        minusButton.setOpaque(true);
        minusButton.setBackground(new Color(255, 224, 179)); // 밝은 빨간 배경
        
	     // JButton 테두리와 배경 제거
        plusButton.setBorderPainted(false);   // 테두리 안 그림
        plusButton.setContentAreaFilled(false); // 버튼 배경 채우기 안 함
        plusButton.setFocusPainted(false);    // 선택 시 테두리 안 그림
        plusButton.setOpaque(true);
        plusButton.setBackground(new Color(255, 224, 179)); // 밝은 빨간 배경
        
        
        minusButton.addActionListener(e -> {
            if (item.quantity > 0) {
                item.quantity--;
                qtyLabel.setText(String.valueOf(item.quantity));
                recalcTotal();
            }
        });

        plusButton.addActionListener(e -> {
            item.quantity++;
            qtyLabel.setText(String.valueOf(item.quantity));
            recalcTotal();
        });

        JPanel qtyPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        qtyPanel.add(minusButton);
        qtyPanel.add(qtyLabel);
        qtyPanel.add(plusButton);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(priceLabel, BorderLayout.NORTH);
        southPanel.add(qtyPanel, BorderLayout.SOUTH);

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void recalcTotal() {
        total = orderItems.stream()
                .mapToInt(item -> item.price * item.quantity)
                .sum();
        totalLabel.setText("총액: " + total + "원");
    }

    private void onConfirm() {
        try {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            OrderDAO dao = new OrderDAO();

            newOrderId = dao.setOrderId1000();
            System.out.println("키오스크에서 생성된 오더아이디:"+newOrderId);
            dao.insertOrder(newOrderId, userId, matjipId, total, now);
            insertOrderDetails(newOrderId);

            JOptionPane.showMessageDialog(this, "주문이 완료되었습니다!\n주문번호: " + newOrderId);

            	dispose(); // 현재 창 닫기
		        try {
		            customer.ShowOrder lastorder = new customer.ShowOrder(); //order id 넘겨받아야하는대ㅔ....
		            lastorder.setVisible(true); // Showorder 창 다시 열기
		        } catch (Exception e) {
		            e.printStackTrace();
		            JOptionPane.showMessageDialog(this, "주문 확인 창을 여는 데 실패했습니다.", "ERROR", JOptionPane.ERROR_MESSAGE);
		        }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "주문 처리 중 오류가 발생했습니다.", "ERROR", JOptionPane.ERROR_MESSAGE);
        
        }
    }

    private void insertOrderDetails(int orderId) throws ClassNotFoundException {
        try {
            OrderDAO dao = new OrderDAO();
            List<OrderVO> orderList = new ArrayList<>();

            for (OrderItem item : orderItems) {
                if (item.quantity > 0) { // 수량이 0보다 큰 항목만 처리
                    OrderVO vo = new OrderVO();
                    vo.setOrderid(orderId);
                    vo.setMenuId(item.menuId);
                    vo.setCount(item.quantity);
                    vo.setUnitPrice(item.price);
                    orderList.add(vo);
                }
            }

            if (!orderList.isEmpty()) { // 주문 항목이 있으면만 insert
                dao.insertOrderDetails(orderId, orderList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void showOrderSummary() {
        StringBuilder sb = new StringBuilder("▶ 주문 내역\n\n");

        boolean hasOrder = false;
        for (OrderItem item : orderItems) {
            if (item.quantity > 0) {
                sb.append(String.format("- %s: %d개 × %,d원 = %,d원\n",
                        item.name, item.quantity, item.price, item.price * item.quantity));
                hasOrder = true;
            }
        }

        if (!hasOrder) sb.append("주문된 항목이 없습니다.");

        JOptionPane.showMessageDialog(this, sb.toString(), "주문 내역", JOptionPane.INFORMATION_MESSAGE);
    }

    public class KioskLauncher {
        public static void launchKioskFor(String userId, int matjipId) {
            try {
                String[] menuNames = Kioskmenu.getMenuByMatjipId(matjipId);
                String[] strPrices = new KioskMenuPriceDAO().getMenuPrice(matjipId);
                int[] prices = Arrays.stream(strPrices).mapToInt(Integer::parseInt).toArray();
                int[] menuIds = new KioskMenuIdDAO().getMenuID(matjipId);
                String[] imagePaths = KioskImage.getImagePaths(matjipId);

                new Kiosk(menuNames, prices, imagePaths, menuIds, userId, matjipId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class OrderItem {
        String name;
        int price;
        String imagePath;
        int menuId;
        int quantity;

        public OrderItem(String name, int price, String imagePath, int menuId) {
            this.name = name;
            this.price = price;
            this.imagePath = imagePath;
            this.menuId = menuId;
            this.quantity = 0;
        }
    }
}