package kiosk6;

public class CartItem {
    private String itemName;
    private int quantity;
    private double price;

    // 생성자
    public CartItem(String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    // 아이템 이름 반환
    public String getItemName() {
        return itemName;
    }

    // 수량 반환
    public int getQuantity() {
        return quantity;
    }

    public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
    public double getPrice() {
        return price;
    }

    // 아이템 정보를 문자열로 반환
    @Override
    public String toString() {
        return itemName + " (수량: " + quantity + ", 가격: " + price + "원)";
    }
    
    public double getTotal() {
        return quantity * price;
    }
    
    // 수량 증가 메소드
    public void increaseQuantity() {
        quantity++;
    }
    
    // 수량 감소 메소드
    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

}
