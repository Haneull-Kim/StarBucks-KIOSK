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
        
        setTitle("메뉴 상세 정보");
        setLayout(new BorderLayout());
        setSize(400, 250);
        setResizable(false);

        // 메뉴 이미지 표시를 위한 JLabel 추가
        JLabel imageLabel = new JLabel();
        ImageIcon originalIcon = new ImageIcon(menu.getImagePath());
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(130, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(scaledIcon);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(6, 1)); // 6개의 행으로 나누기

        infoPanel.add(createLabelWithBorder("메뉴: " + menu.getName()));
        quantity = 1;
        quantityLabel = createLabelWithBorder("수량: " + quantity);
        infoPanel.add(quantityLabel);
        infoPanel.add(createLabelWithBorder("가격: " + menu.getPrice() + "원"));
        
        // 추가된 부분 시작
        caloriesLabel = createLabelWithBorder("칼로리: " + menu.getCalories() + " kcal");
        sugarLabel = createLabelWithBorder("당도: " + menu.getSugar());
        caffeineLabel = createLabelWithBorder("카페인: " + menu.getCaffeine() + " mg");

        infoPanel.add(caloriesLabel);
        infoPanel.add(sugarLabel);
        infoPanel.add(caffeineLabel);

        JButton addToCartButton = new JButton("담기");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼이 클릭되었을 때의 동작
                // 수량과 가격을 기반으로 CartItem 생성
                CartItem cartItem = new CartItem(menu.getName(), quantity, menu.getPrice());

                // 장바구니에 아이템 추가
                menuSelectionScreen.addToCart(cartItem);

                // 총 가격 및 총 수량 업데이트
                menuSelectionScreen.updateCartPanel();

                // 팝업 창 닫기
                dispose();
            }
        });


        JButton cancelButton = new JButton("취소");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 팝업 창 닫기
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addToCartButton);
        buttonPanel.add(cancelButton);

        // 이미지와 정보를 포함하는 패널 추가
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(imageLabel, BorderLayout.WEST);
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 왼쪽, 위, 오른쪽, 아래 여백 추가

        // 버튼 및 이미지를 포함하는 패널 추가
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(contentPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setLocationRelativeTo(null);
    }
    
    // 메뉴의 수량을 반환하는 메서드
    public int getQuantity() {
        return quantity;
    }

    private JLabel createLabelWithBorder(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(new EmptyBorder(0, 10, 0, 0)); // 왼쪽, 위, 오른쪽, 아래 여백 추가
        return label;
    }

}
