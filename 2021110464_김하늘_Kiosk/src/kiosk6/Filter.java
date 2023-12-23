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
    private JButton randomButton;  // Random ��ư �߰�
    private MenuIndex menuIndex;

    public Filter() {
        setLayout(new BorderLayout());

        calorieField = new JTextField(5);
        sweetnessField = new JTextField(5);
        caffeineField = new JTextField(5);
        menuTextArea = new JTextArea(20, 40);
        filterButton = new JButton("Filter");
        randomButton = new JButton("Random");  // Random ��ư �ʱ�ȭ

        JScrollPane scrollPane = new JScrollPane(menuTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        menuIndex = new MenuIndex();

        JPanel controlPanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());  // ���ο� ��ư �г� �߰�

        controlPanel.add(new JLabel("Calories:"));
        controlPanel.add(calorieField);
        controlPanel.add(new JLabel("Sweetness:"));
        controlPanel.add(sweetnessField);
        controlPanel.add(new JLabel("Caffeine:"));
        controlPanel.add(caffeineField);
        controlPanel.add(filterButton);

        // ���� ��ư�� ActionListener �߰�
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterAndDisplayMenus();
            }
        });

        // Random ��ư�� ActionListener �߰�
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRandomMenu();
            }
        });

        buttonPanel.add(randomButton);  // Random ��ư �гο� �߰�

        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);  // Random ��ư �г� �߰�
    }

    private void filterAndDisplayMenus() {
        int calorieLimit = getFieldValue(calorieField);
        int sweetnessLimit = getFieldValue(sweetnessField);
        int caffeineLimit = getFieldValue(caffeineField);

        // ����ڰ� �Է��� ���� ���� �޴� ���͸�
        List<String> filteredMenus = menuIndex.filterMenus(calorieLimit, sweetnessLimit, caffeineLimit);

        // JTextArea�� ���͸��� �޴� ��� ǥ��
        menuTextArea.setText("");
        for (String menu : filteredMenus) {
            menuTextArea.append(menu + "\n");
        }
    }

    private void showRandomMenu() {
        int calorieLimit = getFieldValue(calorieField);
        int sweetnessLimit = getFieldValue(sweetnessField);
        int caffeineLimit = getFieldValue(caffeineField);

        // ����ڰ� �Է��� ���� ���� �޴� ���͸�
        List<String> filteredMenus = menuIndex.filterMenus(calorieLimit, sweetnessLimit, caffeineLimit);

        // �����ϰ� �޴� ����
        if (!filteredMenus.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(filteredMenus.size());
            String randomMenu = filteredMenus.get(randomIndex);

            // JTextArea�� �����ϰ� ���õ� �޴� ǥ��
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
