package kiosk6;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Cart {
    private ArrayList<CartItem> items;  // 장바구니 아이템 목록
    private int totalItemCount; 		// 총 음료 수량
    private double totalPrice; 			// 총 가격

    public Cart() {
        items = new ArrayList<>();
        totalItemCount = 0;
        totalPrice = 0.0;
    }

    public void addItem(CartItem item) {
        items.add(item);
        totalItemCount += item.getQuantity(); 	// 수량 누적
        totalPrice += item.getTotal(); 			// 가격 누적
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
