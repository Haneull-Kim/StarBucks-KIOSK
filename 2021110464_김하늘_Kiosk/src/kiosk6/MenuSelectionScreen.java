package kiosk6;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class MenuSelectionScreen extends JPanel {

    private String mode;
    private JPanel mainPanel; 
    private List<CartItem> cartItems;
    private Cart cart;
    private String previousPanel;
    private CardLayout cardLayout;
    private JPanel cartItemsPanel;
    private JLabel totalItemsLabel;
    private JLabel totalPriceLabel;
    private double totalCartPrice;
    private int totalCartItems;  
    private Receipt receipt;
    private ColorChangePanel colorChangePanel;
    private Filter filterpanel;
    
    private JPanel textPanel;
    private JTabbedPane menuTabbedPane;
    private JPanel cartPanel;
    private JPanel paymentPanel;
    
    public MenuSelectionScreen(String mode, JPanel mainPanel) {
        this.mode = mode;
        this.mainPanel = mainPanel;
        this.cartItems = new ArrayList<>();
        this.cart = new Cart();
        this.previousPanel = "";
        this.cardLayout = new CardLayout();
        this.cartItemsPanel = new JPanel();
        this.cartItemsPanel.setLayout(new BoxLayout(this.cartItemsPanel, BoxLayout.Y_AXIS));
        this.totalItemsLabel = new JLabel("총 음료 수: 0");
        this.totalPriceLabel = new JLabel("총 가격: $0.0");
        this.totalCartPrice = 0.0;
        this.totalCartItems = 0;
        this.receipt = new Receipt();
        this.colorChangePanel = new ColorChangePanel();
        this.filterpanel = new Filter();
        

        initComponents();
    }
   
    private void initComponents() {
        setLayout(new BorderLayout());

        // 이미지가 들어갈 패널
        textPanel = createTextPanel();
        add(textPanel, BorderLayout.NORTH);

        // JTabbedPane을 이용한 음료 카테고리 및 메뉴
        menuTabbedPane = createMenuTabbedPane();
        add(menuTabbedPane, BorderLayout.CENTER);
        
        // Setting 탭 패널 생성
        JPanel settingPanel = new JPanel(new BorderLayout());
        settingPanel.add(colorChangePanel, BorderLayout.CENTER); 

        // 이후 탭에 "Setting" 탭 추가
        menuTabbedPane.addTab("Setting", settingPanel);
        
        // 장바구니 및 결제 버튼 패널
        JPanel cartAndPaymentPanel = new JPanel(new BorderLayout());
        cartAndPaymentPanel.setPreferredSize(new Dimension(450, 180));

        // 장바구니 패널
        cartPanel = createCartPanel();
        cartAndPaymentPanel.add(cartPanel, BorderLayout.CENTER);
        
        // 음료 총 개수 및 가격 표시 라벨 패널
        JPanel totalPanel = new JPanel();
        totalPanel.add(totalItemsLabel);
        totalPanel.add(Box.createHorizontalStrut(10)); // 간격 추가
        totalPanel.add(totalPriceLabel);

        cartAndPaymentPanel.add(totalPanel, BorderLayout.SOUTH);

        // 결제 및 처음으로 버튼이 있는 패널
        paymentPanel = createPaymentPanel();
        cartAndPaymentPanel.add(paymentPanel, BorderLayout.SOUTH);
        
        add(cartAndPaymentPanel, BorderLayout.SOUTH);
       

    }

    private JPanel createTextPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 100, 0)); // Dark Green 배경색

        JLabel label = new JLabel("STARBUCKS");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(Color.WHITE); 

        panel.add(label);

        return panel;
    }

    private JTabbedPane createMenuTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        int tabbedPaneHeight = 250;
        tabbedPane.setPreferredSize(new Dimension(tabbedPane.getPreferredSize().width, tabbedPaneHeight));

        // MenuIndex 객체 생성
        MenuIndex menuIndex = new MenuIndex();

        // 음료 카테고리들을 가져오기
        List<BeverageCategory> beverageCategories = menuIndex.getCategories();
        List<String> categories = beverageCategories.stream()
                                                   .map(BeverageCategory::getCategoryName)
                                                   .collect(Collectors.toList());

        // 각 카테고리에 대한 탭과 패널을 생성하여 JTabbedPane에 추가
        for (String category : categories) {
            JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

            // 카테고리에 속한 음료 메뉴들을 가져오기
            List<Menu> menus = menuIndex.getMenusByCategory(category);

            // 각 음료 메뉴에 대한 버튼을 생성하여 패널에 추가
            for (Menu menu : menus) {
                MenuButton menuButton = new MenuButton(menu);
                categoryPanel.add(menuButton);
                menuButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 메뉴 버튼이 클릭되었을 때의 동작
                        showMenuPopup(menu);
                    }
                });
            }

            JScrollPane scrollPane = new JScrollPane(categoryPanel);
            tabbedPane.addTab(category, scrollPane);
        }

        // Filter 탭 패널 생성
        JPanel filterTabPanel = new JPanel(new BorderLayout());
        filterTabPanel.add(filterpanel, BorderLayout.CENTER);

        // 이후 탭에 "Filter" 탭 추가
        tabbedPane.addTab("Filter", filterTabPanel);

        return tabbedPane;
    }


    private void showMenuPopup(Menu menu) {
        MenuPopup menuPopup = new MenuPopup(menu, this);
        menuPopup.setVisible(true);
    }

    private JPanel createCartPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 장바구니 아이템들을 표시
        cartItemsPanel.removeAll();  // 기존 아이템 제거

        for (CartItem item : cartItems) {
            // 각 장바구니 항목을 표시할 패널 생성
            JPanel cartItemPanel = new JPanel(new BorderLayout());

            // 장바구니 항목 정보를 표시할 레이블 생성
            JLabel cartLabel = new JLabel(item.toString());
            cartItemPanel.add(cartLabel, BorderLayout.WEST);

            // 수량 조절 버튼을 담을 패널 생성
            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

            // 수량 증가 버튼
            JButton increaseButton = new JButton("+");
            increaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 수량 증가 및 장바구니 패널 업데이트
                    item.increaseQuantity();
                    updateCartPanel();
                    
                    // 추가: 총 가격 및 총 개수 업데이트
                    totalCartPrice += item.getPrice();
                    totalCartItems++;
                    updateTotalLabels();
                }
            });

            // 수량 감소 버튼
            JButton decreaseButton = new JButton("-");
            decreaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 수량 감소, 수량이 0이 되면 항목 제거하고 장바구니 패널 업데이트
                    item.decreaseQuantity();
                    if (item.getQuantity() == 0) {
                        cartItems.remove(item);
                    }
                    updateCartPanel();
                    
                    // 추가: 총 가격 및 총 개수 업데이트
                    totalCartPrice -= item.getPrice();
                    totalCartItems--;
                    updateTotalLabels();
                }
            });

            // 수량 조절 버튼을 수량 패널에 추가
            quantityPanel.add(decreaseButton);
            quantityPanel.add(increaseButton);

            // 수량 패널을 장바구니 항목 패널에 추가
            cartItemPanel.add(quantityPanel, BorderLayout.EAST);

            // 장바구니 항목 패널을 cartItemsPanel에 추가
            cartItemsPanel.add(cartItemPanel);
        }

        // JScrollPane을 이용하여 cartItemsPanel을 감싸고, panel에 추가
        JScrollPane scrollPane = new JScrollPane(cartItemsPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 총 개수와 총 가격을 표시할 라벨을 생성하고 추가
        JPanel totalPanel = new JPanel();
        totalPanel.add(totalItemsLabel);
        totalPanel.add(Box.createHorizontalStrut(10)); // 간격 추가
        totalPanel.add(totalPriceLabel);

        // totalPanel을 먼저 추가하고 그 다음에 cartItemsPanel 추가
        panel.add(totalPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void updateTotalLabels() {
        totalItemsLabel.setText("총 음료 수: " + totalCartItems);
        totalPriceLabel.setText("총 가격: $" + totalCartPrice);
    }

    public void addToCart(CartItem cartItem) {
        cartItems.add(cartItem);

        // 장바구니에 아이템이 추가될 때마다 총 가격과 총 개수 업데이트
        totalCartPrice += cartItem.getTotal();
        totalCartItems += cartItem.getQuantity();

        updateCartPanel();
    }
   

    public void updateCartPanel() {
        // 장바구니 아이템이 추가되었을 때 화면을 업데이트
        cartPanel.removeAll();

        // cart 객체에서 장바구니 아이템 리스트를 가져와서 업데이트
        List<CartItem> updatedCartItems = cart.getItems();
        for (CartItem item : updatedCartItems) {
            JLabel cartLabel = new JLabel(item.toString());
            cartItemsPanel.add(cartLabel);
        }

        // totalPanel을 먼저 추가하고 그 다음에 cartItemsPanel 추가
        cartPanel.add(createCartPanel());

        // 총 개수 및 총 가격 업데이트
        totalItemsLabel.setText("총 음료 수: " + totalCartItems);
        totalPriceLabel.setText("총 가격: $" + totalCartPrice);

        revalidate();
        repaint();
    }

    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton payButton = new JButton("결제하기");
        JButton cancelButton = new JButton("취소하기");

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 결제 버튼이 눌렸을 때의 동작
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "결제하시겠습니까?",
                        "주문 확인",
                        JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.YES_OPTION) {
                    // 사용자가 "예"를 선택한 경우
                    // 결제 완료 다이얼로그 표시
                    int paymentResult = JOptionPane.showConfirmDialog(
                            null,
                            "결제가 완료되었습니다. 영수증을 출력하시겠습니까?",
                            "알림",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (paymentResult == JOptionPane.YES_OPTION) {
                        // 사용자가 "예"를 선택한 경우
                        // 영수증 출력
                    	receipt.printReceipt(cartItems, totalCartPrice, totalCartItems);
                    	// 장바구니 아이템 비우기
                        cartItems.clear();

                         // 총 가격과 총 개수 초기화
                         totalCartPrice = 0.0;
                         totalCartItems = 0;

                         // 장바구니 패널 업데이트
                         updateCartPanel();
                    }

                } else {
                    // 사용자가 "아니오"를 선택한 경우
                    JOptionPane.getRootFrame().dispose();
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // "취소하기" 버튼이 눌렸을 때의 동작
                int result = JOptionPane.showConfirmDialog(null, "주문이 취소되었습니다.", "알림", JOptionPane.DEFAULT_OPTION);

                // 장바구니 아이템 비우기
                cartItems.clear();

                // 총 가격과 총 개수 초기화
                totalCartPrice = 0.0;
                totalCartItems = 0;

                // 장바구니 패널 업데이트
                updateCartPanel();
            }
        });

        panel.add(payButton);
        panel.add(cancelButton);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Menu Selection");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel mainPanel = new JPanel(); // mainPanel 생성
            frame.getContentPane().add(new MenuSelectionScreen("매장", mainPanel));

            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

