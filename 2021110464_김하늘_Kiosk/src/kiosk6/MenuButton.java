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

        // �̹����� ǥ���ϴ� JLabel
        JLabel imageLabel = new JLabel();
        ImageIcon originalIcon = new ImageIcon(menu.getImagePath());
        Image originalImage = originalIcon.getImage();

        // �̹��� ũ�� ����
        Image scaledImage = originalImage.getScaledInstance(180, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        imageLabel.setIcon(scaledIcon);

        // �޴����� ǥ���ϴ� JLabel
        JLabel nameLabel = new JLabel("<html><b>" + menu.getName() + "</b></html>");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        // ������ ǥ���ϴ� JLabel
        JLabel priceLabel = new JLabel(menu.getPrice() + "��");
        priceLabel.setHorizontalAlignment(JLabel.CENTER);

        add(imageLabel, BorderLayout.NORTH);
        add(nameLabel, BorderLayout.CENTER);
        add(priceLabel, BorderLayout.SOUTH);

        // ��ư ũ�� ����
        setPreferredSize(new Dimension(180, 250));
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ��ư�� Ŭ���Ǿ��� ���� ����
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
