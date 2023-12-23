package kiosk6;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Cart {
    private ArrayList<CartItem> items;  // ��ٱ��� ������ ���
    private int totalItemCount; 		// �� ���� ����
    private double totalPrice; 			// �� ����

    public Cart() {
        items = new ArrayList<>();
        totalItemCount = 0;
        totalPrice = 0.0;
    }

    public void addItem(CartItem item) {
        items.add(item);
        totalItemCount += item.getQuantity(); 	// ���� ����
        totalPrice += item.getTotal(); 			// ���� ����
    }

    public List<CartItem> getItems() {
        return items;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
