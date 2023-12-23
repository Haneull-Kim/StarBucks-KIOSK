package kiosk6;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPopup extends JDialog {
	
	 private JLabel quantityLabel;
	 private JLabel caloriesLabel;
	 private JLabel sugarLabel;
	 private JLabel caffeineLabel;
	 private int quantity;
	 private Menu menu;
	 private MenuSelectionScreen menuSelectionScreen;

    public MenuPopup(Menu menu, MenuSelectionScreen menuSelectionScreen) {
        this.menu = menu;
        this.menuSelectionScreen = menuSelectionScreen;
        
        setTitle("�޴� �� ����");
        setLayout(new BorderLayout());
        setSize(400, 250);
        setResizable(false);

        // �޴� �̹��� ǥ�ø� ���� JLabel �߰�
        JLabel imageLabel = new JLabel();
        ImageIcon originalIcon = new ImageIcon(menu.getImagePath());
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(130, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(scaledIcon);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(6, 1)); // 6���� ������ ������

        infoPanel.add(createLabelWithBorder("�޴�: " + menu.getName()));
        quantity = 1;
        quantityLabel = createLabelWithBorder("����: " + quantity);
        infoPanel.add(quantityLabel);
        infoPanel.add(createLabelWithBorder("����: " + menu.getPrice() + "��"));
        
        // �߰��� �κ� ����
        caloriesLabel = createLabelWithBorder("Į�θ�: " + menu.getCalories() + " kcal");
        sugarLabel = createLabelWithBorder("�絵: " + menu.getSugar());
        caffeineLabel = createLabelWithBorder("ī����: " + menu.getCaffeine() + " mg");

        infoPanel.add(caloriesLabel);
        infoPanel.add(sugarLabel);
        infoPanel.add(caffeineLabel);

        JButton addToCartButton = new JButton("���");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ��ư�� Ŭ���Ǿ��� ���� ����
                // ������ ������ ������� CartItem ����
                CartItem cartItem = new CartItem(menu.getName(), quantity, menu.getPrice());

                // ��ٱ��Ͽ� ������ �߰�
                menuSelectionScreen.addToCart(cartItem);

                // �� ���� �� �� ���� ������Ʈ
                menuSelectionScreen.updateCartPanel();

                // �˾� â �ݱ�
                dispose();
            }
        });


        JButton cancelButton = new JButton("���");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // �˾� â �ݱ�
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addToCartButton);
        buttonPanel.add(cancelButton);

        // �̹����� ������ �����ϴ� �г� �߰�
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(imageLabel, BorderLayout.WEST);
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // ����, ��, ������, �Ʒ� ���� �߰�

        // ��ư �� �̹����� �����ϴ� �г� �߰�
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(contentPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setLocationRelativeTo(null);
    }
    
    // �޴��� ������ ��ȯ�ϴ� �޼���
    public int getQuantity() {
        return quantity;
    }

    private JLabel createLabelWithBorder(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(new EmptyBorder(0, 10, 0, 0)); // ����, ��, ������, �Ʒ� ���� �߰�
        return label;
    }

}
