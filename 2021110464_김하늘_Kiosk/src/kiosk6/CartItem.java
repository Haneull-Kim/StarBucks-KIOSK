package kiosk6;

public class CartItem {
    private String itemName;
    private int quantity;
    private double price;

    // ������
    public CartItem(String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    // ������ �̸� ��ȯ
    public String getItemName() {
        return itemName;
    }

    // ���� ��ȯ
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

    // ������ ������ ���ڿ��� ��ȯ
    @Override
    public String toString() {
        return itemName + " (����: " + quantity + ", ����: " + price + "��)";
    }
    
    public double getTotal() {
        return quantity * price;
    }
    
    // ���� ���� �޼ҵ�
    public void increaseQuantity() {
        quantity++;
    }
    
    // ���� ���� �޼ҵ�
    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

}
