package kiosk6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class Filter extends JPanel {

    private JTextField calorieField;
    private JTextField sweetnessField;
    private JTextField caffeineField;
    private JTextArea menuTextArea;
    private JButton filterButton;
    private JButton randomButton;  // Random 버튼 추가
    private MenuIndex menuIndex;

    public Filter() {
        setLayout(new BorderLayout());

        calorieField = new JTextField(5);
        sweetnessField = new JTextField(5);
        caffeineField = new JTextField(5);
        menuTextArea = new JTextArea(20, 40);
        filterButton = new JButton("Filter");
        randomButton = new JButton("Random");  // Random 버튼 초기화

        JScrollPane scrollPane = new JScrollPane(menuTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        menuIndex = new MenuIndex();

        JPanel controlPanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());  // 새로운 버튼 패널 추가

        controlPanel.add(new JLabel("Calories:"));
        controlPanel.add(calorieField);
        controlPanel.add(new JLabel("Sweetness:"));
        controlPanel.add(sweetnessField);
        controlPanel.add(new JLabel("Caffeine:"));
        controlPanel.add(caffeineField);
        controlPanel.add(filterButton);

        // 필터 버튼에 ActionListener 추가
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterAndDisplayMenus();
            }
        });

        // Random 버튼에 ActionListener 추가
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRandomMenu();
            }
        });

        buttonPanel.add(randomButton);  // Random 버튼 패널에 추가

        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);  // Random 버튼 패널 추가
    }

    private void filterAndDisplayMenus() {
        int calorieLimit = getFieldValue(calorieField);
        int sweetnessLimit = getFieldValue(sweetnessField);
        int caffeineLimit = getFieldValue(caffeineField);

        // 사용자가 입력한 값에 따라 메뉴 필터링
        List<String> filteredMenus = menuIndex.filterMenus(calorieLimit, sweetnessLimit, caffeineLimit);

        // JTextArea에 필터링된 메뉴 목록 표시
        menuTextArea.setText("");
        for (String menu : filteredMenus) {
            menuTextArea.append(menu + "\n");
        }
    }

    private void showRandomMenu() {
        int calorieLimit = getFieldValue(calorieField);
        int sweetnessLimit = getFieldValue(sweetnessField);
        int caffeineLimit = getFieldValue(caffeineField);

        // 사용자가 입력한 값에 따라 메뉴 필터링
        List<String> filteredMenus = menuIndex.filterMenus(calorieLimit, sweetnessLimit, caffeineLimit);

        // 랜덤하게 메뉴 선택
        if (!filteredMenus.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(filteredMenus.size());
            String randomMenu = filteredMenus.get(randomIndex);

            // JTextArea에 랜덤하게 선택된 메뉴 표시
            menuTextArea.setText(randomMenu);
        } else {
            menuTextArea.setText("No matching menu found.");
        }
    }

    private int getFieldValue(JTextField textField) {
        try {
            return Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu Filter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        Filter filter = new Filter();
        frame.add(filter);

        frame.setVisible(true);
    }
}
