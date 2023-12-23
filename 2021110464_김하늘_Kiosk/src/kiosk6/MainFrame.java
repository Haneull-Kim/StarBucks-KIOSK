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
    private JFrame frame;               // 최상위 컨테이너
    private JPanel mainPanel;           // 메인패널
    private CardLayout cardLayout;       
    private String previousPanel;		// 이전에 표시한 패널의 식별자를 저장하는 변수

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("StarBucks Kiosk");    // 최상위 컨테이너
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();           // 메인 백그라운드 패널을 올릴 패널
        cardLayout = new CardLayout();      // 여러 개의 컴포넌트를 겹쳐놓고, 그 중 하나만 보이도록 하는 레이아웃 매니저
        mainPanel.setLayout(cardLayout);    // CardLayout을 사용하여 다른 패널을 mainPanel에 표시하거나 숨길 수 있음

        JPanel mainBackgroundPanel = new JPanel();              // 메인백그라운드패널(스타벅스 및 매장, 포장 버튼 포함)
        mainBackgroundPanel.setLayout(new GridLayout(2, 1));    // 레이아웃(2, 1)
        
        // 이미지 아이콘을 로드
        ImageIcon icon = new ImageIcon("/Users/kimhaneul/Downloads/startbucks_img/symbol.jpg"); // 동국대 이미지

        // 이미지를 표시할 라벨 생성
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.WHITE);
        //imageLabel.setBackground(new Color(0, 100, 0)); // 다크 그린 색

        // 텍스트를 표시할 라벨 생성
        JLabel textLabel = new JLabel("STARBUCKS");
        textLabel.setFont(new Font("Arial", Font.BOLD, 30));
        textLabel.setForeground(Color.WHITE);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);
        textLabel.setBackground(new Color(0, 100, 0));

        // 라벨을 겹쳐 표시하기 위해 레이어드패널 사용
        JPanel layeredPanel = new JPanel();
        layeredPanel.setLayout(new BorderLayout());
        layeredPanel.add(imageLabel, BorderLayout.NORTH);
        layeredPanel.add(textLabel, BorderLayout.CENTER);

        // 메인 백그라운드 패널에 레이어드패널 추가
        mainBackgroundPanel.add(layeredPanel);
       
        JPanel mainButtonPanel = new JPanel();                 // 메인 버튼 패널 (매장, 포장)
        mainButtonPanel.setLayout(new GridLayout(1, 2));       // 레이아웃(1, 2)

        JButton dineInButton = new JButton("매장");             // 매장 버튼
        dineInButton.setFont(new Font("Arial", Font.PLAIN, 24));
        dineInButton.setBackground(Color.WHITE);
        dineInButton.addActionListener(e -> {
            // "매장" 버튼이 눌렸을 때의 동작
            cardLayout.show(mainPanel, "MENU");
        });

        JButton takeOutButton = new JButton("포장");            // 포장 버튼
        takeOutButton.setFont(new Font("Arial", Font.PLAIN, 24));
        takeOutButton.setBackground(Color.WHITE);
        takeOutButton.addActionListener(e -> {
            // "포장" 버튼이 눌렸을 때의 동작
            cardLayout.show(mainPanel, "MENU");
        });
 
        mainButtonPanel.add(dineInButton);        // 버튼 패널에 매장 버튼 추가 (1, 1)
        mainButtonPanel.add(takeOutButton);       // 버튼 패널에 포장 버튼 추가 (1, 2)
        mainBackgroundPanel.add(mainButtonPanel); // 메인 백그라운드 패널에 버튼 패널 추가  (2, 1)

        mainPanel.add(mainBackgroundPanel, "MAIN"); // 메인백그라운드패널을 메인 패널에 추가, 식별자 : MAIN

        
        // "MENU" 패널을 메인 패널에 추가
        MenuSelectionScreen menuSelectionScreen = new MenuSelectionScreen("매장", mainPanel);
        mainPanel.add(menuSelectionScreen, "MENU");
        previousPanel = "MENU";

        frame.getContentPane().add(mainPanel);    // 최상위 컨테이너에 메인 패널 추가
        frame.setSize(700, 600);                    
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
