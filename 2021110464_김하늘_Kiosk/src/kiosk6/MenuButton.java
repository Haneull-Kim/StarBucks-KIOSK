package kiosk6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButton extends JButton {

    private Menu menu;

    public MenuButton(Menu menu) {
        this.menu = menu;
        setupButton();
    }

    private void setupButton() {
        setLayout(new BorderLayout());

        // 이미지를 표시하는 JLabel
        JLabel imageLabel = new JLabel();
        ImageIcon originalIcon = new ImageIcon(menu.getImagePath());
        Image originalImage = originalIcon.getImage();

        // 이미지 크기 조절
        Image scaledImage = originalImage.getScaledInstance(180, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        imageLabel.setIcon(scaledIcon);

        // 메뉴명을 표시하는 JLabel
        JLabel nameLabel = new JLabel("<html><b>" + menu.getName() + "</b></html>");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        // 가격을 표시하는 JLabel
        JLabel priceLabel = new JLabel(menu.getPrice() + "원");
        priceLabel.setHorizontalAlignment(JLabel.CENTER);

        add(imageLabel, BorderLayout.NORTH);
        add(nameLabel, BorderLayout.CENTER);
        add(priceLabel, BorderLayout.SOUTH);

        // 버튼 크기 설정
        setPreferredSize(new Dimension(180, 250));
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼이 클릭되었을 때의 동작
                System.out.println("Selected Menu: " + menu.getName());
                System.out.println("Price: " + menu.getPrice());
                System.out.println("Calories: " + menu.getCalories());
                System.out.println("Sugar: " + menu.getSugar());
                System.out.println("Caffeine: " + menu.getCaffeine());
            }
        });

        setPreferredSize(new Dimension(180, 250));
    }
}
