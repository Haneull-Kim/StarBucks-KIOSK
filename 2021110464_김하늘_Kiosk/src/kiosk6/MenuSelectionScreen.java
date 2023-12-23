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
        this.totalItemsLabel = new JLabel("�� ���� ��: 0");
        this.totalPriceLabel = new JLabel("�� ����: $0.0");
        this.totalCartPrice = 0.0;
        this.totalCartItems = 0;
        this.receipt = new Receipt();
        this.colorChangePanel = new ColorChangePanel();
        this.filterpanel = new Filter();
        

        initComponents();
    }
   
    private void initComponents() {
        setLayout(new BorderLayout());

        // �̹����� �� �г�
        textPanel = createTextPanel();
        add(textPanel, BorderLayout.NORTH);

        // JTabbedPane�� �̿��� ���� ī�װ� �� �޴�
        menuTabbedPane = createMenuTabbedPane();
        add(menuTabbedPane, BorderLayout.CENTER);
        
        // Setting �� �г� ����
        JPanel settingPanel = new JPanel(new BorderLayout());
        settingPanel.add(colorChangePanel, BorderLayout.CENTER); 

        // ���� �ǿ� "Setting" �� �߰�
        menuTabbedPane.addTab("Setting", settingPanel);
        
        // ��ٱ��� �� ���� ��ư �г�
        JPanel cartAndPaymentPanel = new JPanel(new BorderLayout());
        cartAndPaymentPanel.setPreferredSize(new Dimension(450, 180));

        // ��ٱ��� �г�
        cartPanel = createCartPanel();
        cartAndPaymentPanel.add(cartPanel, BorderLayout.CENTER);
        
        // ���� �� ���� �� ���� ǥ�� �� �г�
        JPanel totalPanel = new JPanel();
        totalPanel.add(totalItemsLabel);
        totalPanel.add(Box.createHorizontalStrut(10)); // ���� �߰�
        totalPanel.add(totalPriceLabel);

        cartAndPaymentPanel.add(totalPanel, BorderLayout.SOUTH);

        // ���� �� ó������ ��ư�� �ִ� �г�
        paymentPanel = createPaymentPanel();
        cartAndPaymentPanel.add(paymentPanel, BorderLayout.SOUTH);
        
        add(cartAndPaymentPanel, BorderLayout.SOUTH);
       

    }

    private JPanel createTextPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 100, 0)); // Dark Green ����

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

        // MenuIndex ��ü ����
        MenuIndex menuIndex = new MenuIndex();

        // ���� ī�װ����� ��������
        List<BeverageCategory> beverageCategories = menuIndex.getCategories();
        List<String> categories = beverageCategories.stream()
                                                   .map(BeverageCategory::getCategoryName)
                                                   .collect(Collectors.toList());

        // �� ī�װ��� ���� �ǰ� �г��� �����Ͽ� JTabbedPane�� �߰�
        for (String category : categories) {
            JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

            // ī�װ��� ���� ���� �޴����� ��������
            List<Menu> menus = menuIndex.getMenusByCategory(category);

            // �� ���� �޴��� ���� ��ư�� �����Ͽ� �гο� �߰�
            for (Menu menu : menus) {
                MenuButton menuButton = new MenuButton(menu);
                categoryPanel.add(menuButton);
                menuButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // �޴� ��ư�� Ŭ���Ǿ��� ���� ����
                        showMenuPopup(menu);
                    }
                });
            }

            JScrollPane scrollPane = new JScrollPane(categoryPanel);
            tabbedPane.addTab(category, scrollPane);
        }

        // Filter �� �г� ����
        JPanel filterTabPanel = new JPanel(new BorderLayout());
        filterTabPanel.add(filterpanel, BorderLayout.CENTER);

        // ���� �ǿ� "Filter" �� �߰�
        tabbedPane.addTab("Filter", filterTabPanel);

        return tabbedPane;
    }


    private void showMenuPopup(Menu menu) {
        MenuPopup menuPopup = new MenuPopup(menu, this);
        menuPopup.setVisible(true);
    }

    private JPanel createCartPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // ��ٱ��� �����۵��� ǥ��
        cartItemsPanel.removeAll();  // ���� ������ ����

        for (CartItem item : cartItems) {
            // �� ��ٱ��� �׸��� ǥ���� �г� ����
            JPanel cartItemPanel = new JPanel(new BorderLayout());

            // ��ٱ��� �׸� ������ ǥ���� ���̺� ����
            JLabel cartLabel = new JLabel(item.toString());
            cartItemPanel.add(cartLabel, BorderLayout.WEST);

            // ���� ���� ��ư�� ���� �г� ����
            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

            // ���� ���� ��ư
            JButton increaseButton = new JButton("+");
            increaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // ���� ���� �� ��ٱ��� �г� ������Ʈ
                    item.increaseQuantity();
                    updateCartPanel();
                    
                    // �߰�: �� ���� �� �� ���� ������Ʈ
                    totalCartPrice += item.getPrice();
                    totalCartItems++;
                    updateTotalLabels();
                }
            });

            // ���� ���� ��ư
            JButton decreaseButton = new JButton("-");
            decreaseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // ���� ����, ������ 0�� �Ǹ� �׸� �����ϰ� ��ٱ��� �г� ������Ʈ
                    item.decreaseQuantity();
                    if (item.getQuantity() == 0) {
                        cartItems.remove(item);
                    }
                    updateCartPanel();
                    
                    // �߰�: �� ���� �� �� ���� ������Ʈ
                    totalCartPrice -= item.getPrice();
                    totalCartItems--;
                    updateTotalLabels();
                }
            });

            // ���� ���� ��ư�� ���� �гο� �߰�
            quantityPanel.add(decreaseButton);
            quantityPanel.add(increaseButton);

            // ���� �г��� ��ٱ��� �׸� �гο� �߰�
            cartItemPanel.add(quantityPanel, BorderLayout.EAST);

            // ��ٱ��� �׸� �г��� cartItemsPanel�� �߰�
            cartItemsPanel.add(cartItemPanel);
        }

        // JScrollPane�� �̿��Ͽ� cartItemsPanel�� ���ΰ�, panel�� �߰�
        JScrollPane scrollPane = new JScrollPane(cartItemsPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        // �� ������ �� ������ ǥ���� ���� �����ϰ� �߰�
        JPanel totalPanel = new JPanel();
        totalPanel.add(totalItemsLabel);
        totalPanel.add(Box.createHorizontalStrut(10)); // ���� �߰�
        totalPanel.add(totalPriceLabel);

        // totalPanel�� ���� �߰��ϰ� �� ������ cartItemsPanel �߰�
        panel.add(totalPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void updateTotalLabels() {
        totalItemsLabel.setText("�� ���� ��: " + totalCartItems);
        totalPriceLabel.setText("�� ����: $" + totalCartPrice);
    }

    public void addToCart(CartItem cartItem) {
        cartItems.add(cartItem);

        // ��ٱ��Ͽ� �������� �߰��� ������ �� ���ݰ� �� ���� ������Ʈ
        totalCartPrice += cartItem.getTotal();
        totalCartItems += cartItem.getQuantity();

        updateCartPanel();
    }
   

    public void updateCartPanel() {
        // ��ٱ��� �������� �߰��Ǿ��� �� ȭ���� ������Ʈ
        cartPanel.removeAll();

        // cart ��ü���� ��ٱ��� ������ ����Ʈ�� �����ͼ� ������Ʈ
        List<CartItem> updatedCartItems = cart.getItems();
        for (CartItem item : updatedCartItems) {
            JLabel cartLabel = new JLabel(item.toString());
            cartItemsPanel.add(cartLabel);
        }

        // totalPanel�� ���� �߰��ϰ� �� ������ cartItemsPanel �߰�
        cartPanel.add(createCartPanel());

        // �� ���� �� �� ���� ������Ʈ
        totalItemsLabel.setText("�� ���� ��: " + totalCartItems);
        totalPriceLabel.setText("�� ����: $" + totalCartPrice);

        revalidate();
        repaint();
    }

    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton payButton = new JButton("�����ϱ�");
        JButton cancelButton = new JButton("����ϱ�");

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ���� ��ư�� ������ ���� ����
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "�����Ͻðڽ��ϱ�?",
                        "�ֹ� Ȯ��",
                        JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.YES_OPTION) {
                    // ����ڰ� "��"�� ������ ���
                    // ���� �Ϸ� ���̾�α� ǥ��
                    int paymentResult = JOptionPane.showConfirmDialog(
                            null,
                            "������ �Ϸ�Ǿ����ϴ�. �������� ����Ͻðڽ��ϱ�?",
                            "�˸�",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (paymentResult == JOptionPane.YES_OPTION) {
                        // ����ڰ� "��"�� ������ ���
                        // ������ ���
                    	receipt.printReceipt(cartItems, totalCartPrice, totalCartItems);
                    	// ��ٱ��� ������ ����
                        cartItems.clear();

                         // �� ���ݰ� �� ���� �ʱ�ȭ
                         totalCartPrice = 0.0;
                         totalCartItems = 0;

                         // ��ٱ��� �г� ������Ʈ
                         updateCartPanel();
                    }

                } else {
                    // ����ڰ� "�ƴϿ�"�� ������ ���
                    JOptionPane.getRootFrame().dispose();
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // "����ϱ�" ��ư�� ������ ���� ����
                int result = JOptionPane.showConfirmDialog(null, "�ֹ��� ��ҵǾ����ϴ�.", "�˸�", JOptionPane.DEFAULT_OPTION);

                // ��ٱ��� ������ ����
                cartItems.clear();

                // �� ���ݰ� �� ���� �ʱ�ȭ
                totalCartPrice = 0.0;
                totalCartItems = 0;

                // ��ٱ��� �г� ������Ʈ
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

            JPanel mainPanel = new JPanel(); // mainPanel ����
            frame.getContentPane().add(new MenuSelectionScreen("����", mainPanel));

            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

