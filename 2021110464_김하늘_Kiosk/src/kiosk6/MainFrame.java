package kiosk6;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
    private JFrame frame;               // �ֻ��� �����̳�
    private JPanel mainPanel;           // �����г�
    private CardLayout cardLayout;       
    private String previousPanel;		// ������ ǥ���� �г��� �ĺ��ڸ� �����ϴ� ����

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("StarBucks Kiosk");    // �ֻ��� �����̳�
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();           // ���� ��׶��� �г��� �ø� �г�
        cardLayout = new CardLayout();      // ���� ���� ������Ʈ�� ���ĳ���, �� �� �ϳ��� ���̵��� �ϴ� ���̾ƿ� �Ŵ���
        mainPanel.setLayout(cardLayout);    // CardLayout�� ����Ͽ� �ٸ� �г��� mainPanel�� ǥ���ϰų� ���� �� ����

        JPanel mainBackgroundPanel = new JPanel();              // ���ι�׶����г�(��Ÿ���� �� ����, ���� ��ư ����)
        mainBackgroundPanel.setLayout(new GridLayout(2, 1));    // ���̾ƿ�(2, 1)
        
        // �̹��� �������� �ε�
        ImageIcon icon = new ImageIcon("/Users/kimhaneul/Downloads/startbucks_img/symbol.jpg"); // ������ �̹���

        // �̹����� ǥ���� �� ����
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.WHITE);
        //imageLabel.setBackground(new Color(0, 100, 0)); // ��ũ �׸� ��

        // �ؽ�Ʈ�� ǥ���� �� ����
        JLabel textLabel = new JLabel("STARBUCKS");
        textLabel.setFont(new Font("Arial", Font.BOLD, 30));
        textLabel.setForeground(Color.WHITE);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);
        textLabel.setBackground(new Color(0, 100, 0));

        // ���� ���� ǥ���ϱ� ���� ���̾���г� ���
        JPanel layeredPanel = new JPanel();
        layeredPanel.setLayout(new BorderLayout());
        layeredPanel.add(imageLabel, BorderLayout.NORTH);
        layeredPanel.add(textLabel, BorderLayout.CENTER);

        // ���� ��׶��� �гο� ���̾���г� �߰�
        mainBackgroundPanel.add(layeredPanel);
       
        JPanel mainButtonPanel = new JPanel();                 // ���� ��ư �г� (����, ����)
        mainButtonPanel.setLayout(new GridLayout(1, 2));       // ���̾ƿ�(1, 2)

        JButton dineInButton = new JButton("����");             // ���� ��ư
        dineInButton.setFont(new Font("Arial", Font.PLAIN, 24));
        dineInButton.setBackground(Color.WHITE);
        dineInButton.addActionListener(e -> {
            // "����" ��ư�� ������ ���� ����
            cardLayout.show(mainPanel, "MENU");
        });

        JButton takeOutButton = new JButton("����");            // ���� ��ư
        takeOutButton.setFont(new Font("Arial", Font.PLAIN, 24));
        takeOutButton.setBackground(Color.WHITE);
        takeOutButton.addActionListener(e -> {
            // "����" ��ư�� ������ ���� ����
            cardLayout.show(mainPanel, "MENU");
        });
 
        mainButtonPanel.add(dineInButton);        // ��ư �гο� ���� ��ư �߰� (1, 1)
        mainButtonPanel.add(takeOutButton);       // ��ư �гο� ���� ��ư �߰� (1, 2)
        mainBackgroundPanel.add(mainButtonPanel); // ���� ��׶��� �гο� ��ư �г� �߰�  (2, 1)

        mainPanel.add(mainBackgroundPanel, "MAIN"); // ���ι�׶����г��� ���� �гο� �߰�, �ĺ��� : MAIN

        
        // "MENU" �г��� ���� �гο� �߰�
        MenuSelectionScreen menuSelectionScreen = new MenuSelectionScreen("����", mainPanel);
        mainPanel.add(menuSelectionScreen, "MENU");
        previousPanel = "MENU";

        frame.getContentPane().add(mainPanel);    // �ֻ��� �����̳ʿ� ���� �г� �߰�
        frame.setSize(700, 600);                    
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
