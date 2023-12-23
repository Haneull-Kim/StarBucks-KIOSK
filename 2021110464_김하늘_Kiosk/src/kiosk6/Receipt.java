package kiosk6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Receipt {
	
    public static void printReceipt(List<CartItem> cartItems, double totalCartPrice, int totalCartItems) {
        // 영수증을 표시할 새로운 JDialog 생성
        JDialog receiptDialog = new JDialog();
        receiptDialog.setTitle("영수증");

        // 영수증 내용을 보여줄 JTextArea 생성
        JTextArea receiptTextArea = new JTextArea();
        receiptTextArea.setEditable(false);

        // 영수증에 추가할 내용 수집
        StringBuilder receiptContent = new StringBuilder();
        receiptContent.append("영수증\n");
        receiptContent.append("-----------------------------------\n");

        // 장바구니에 추가된 아이템 정보 출력
        for (CartItem item : cartItems) {
            receiptContent.append(item.toString()).append("\n");
        }

        // 음료 총 개수 및 총 가격 정보 출력
        receiptContent.append("\n총 음료 수: ").append(totalCartItems).append("\n");
        receiptContent.append("총 가격: $").append(totalCartPrice).append("\n");

        // JTextArea에 영수증 내용 설정
        receiptTextArea.setText(receiptContent.toString());

        // JScrollPane을 이용하여 JTextArea를 감싸고, JDialog에 추가
        JScrollPane scrollPane = new JScrollPane(receiptTextArea);
        receiptDialog.add(scrollPane);

        // JDialog 크기 및 위치 설정
        receiptDialog.setSize(400, 300);
        receiptDialog.setLocationRelativeTo(null);

        // 현재 창을 닫을 때만 JDialog 닫도록 설정
        receiptDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // JDialog를 보이게 함
        receiptDialog.setVisible(true);
        
        // CSV 파일로 저장
        Receipt receipt = new Receipt();
        receipt.saveToCSV(cartItems);
        
    }

    private void saveToCSV(List<CartItem> cartItems) {
        String csvFilePath = "/Users/kimhaneul/Downloads/test.csv"; // 저장할 CSV 파일 경로 및 이름
        
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFilePath, true), "euc-kr"))) {
        	writer.flush();
            writer.write("주문일시, 메뉴, 수량, 총 금액");
            writer.newLine();

            // 주문일시 형식 지정
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // 데이터 추가
            for (CartItem item : cartItems) {
                String orderDateTime = LocalDateTime.now().format(formatter);
                String menuName = item.getItemName();
                int menuQuantity = item.getQuantity();
                double totalMenuPrice = item.getTotal();

                // CSV 파일에 한 줄 추가
                String line = String.format("%s,%s,%d,%.2f", orderDateTime, menuName, menuQuantity, totalMenuPrice);
                writer.write(line);
                writer.newLine();
            }

            System.out.println("주문 내역이 CSV 파일로 저장되었습니다.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
