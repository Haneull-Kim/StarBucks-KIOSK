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
        // �������� ǥ���� ���ο� JDialog ����
        JDialog receiptDialog = new JDialog();
        receiptDialog.setTitle("������");

        // ������ ������ ������ JTextArea ����
        JTextArea receiptTextArea = new JTextArea();
        receiptTextArea.setEditable(false);

        // �������� �߰��� ���� ����
        StringBuilder receiptContent = new StringBuilder();
        receiptContent.append("������\n");
        receiptContent.append("-----------------------------------\n");

        // ��ٱ��Ͽ� �߰��� ������ ���� ���
        for (CartItem item : cartItems) {
            receiptContent.append(item.toString()).append("\n");
        }

        // ���� �� ���� �� �� ���� ���� ���
        receiptContent.append("\n�� ���� ��: ").append(totalCartItems).append("\n");
        receiptContent.append("�� ����: $").append(totalCartPrice).append("\n");

        // JTextArea�� ������ ���� ����
        receiptTextArea.setText(receiptContent.toString());

        // JScrollPane�� �̿��Ͽ� JTextArea�� ���ΰ�, JDialog�� �߰�
        JScrollPane scrollPane = new JScrollPane(receiptTextArea);
        receiptDialog.add(scrollPane);

        // JDialog ũ�� �� ��ġ ����
        receiptDialog.setSize(400, 300);
        receiptDialog.setLocationRelativeTo(null);

        // ���� â�� ���� ���� JDialog �ݵ��� ����
        receiptDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // JDialog�� ���̰� ��
        receiptDialog.setVisible(true);
        
        // CSV ���Ϸ� ����
        Receipt receipt = new Receipt();
        receipt.saveToCSV(cartItems);
        
    }

    private void saveToCSV(List<CartItem> cartItems) {
        String csvFilePath = "/Users/kimhaneul/Downloads/test.csv"; // ������ CSV ���� ��� �� �̸�
        
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFilePath, true), "euc-kr"))) {
        	writer.flush();
            writer.write("�ֹ��Ͻ�, �޴�, ����, �� �ݾ�");
            writer.newLine();

            // �ֹ��Ͻ� ���� ����
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // ������ �߰�
            for (CartItem item : cartItems) {
                String orderDateTime = LocalDateTime.now().format(formatter);
                String menuName = item.getItemName();
                int menuQuantity = item.getQuantity();
                double totalMenuPrice = item.getTotal();

                // CSV ���Ͽ� �� �� �߰�
                String line = String.format("%s,%s,%d,%.2f", orderDateTime, menuName, menuQuantity, totalMenuPrice);
                writer.write(line);
                writer.newLine();
            }

            System.out.println("�ֹ� ������ CSV ���Ϸ� ����Ǿ����ϴ�.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
