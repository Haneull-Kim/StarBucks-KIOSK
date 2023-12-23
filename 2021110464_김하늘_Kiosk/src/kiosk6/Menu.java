package kiosk6;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Menu {
    private String name;
    private double price;
    private String imagePath;
    private int calories;
    private int sugar;
    private int caffeine;
    private int quantity;

    public Menu(String name, double price, String imagePath, int calories, int sugar, int caffeine) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.calories = calories;
        this.sugar = sugar;
        this.caffeine = caffeine;
    }
    
    // 이미지를 로드하여 크기 조절한 ImageIcon을 반환하는 메서드
    public ImageIcon loadImageIcon(int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getSugar() {
		return sugar;
	}

	public void setSugar(int sugar) {
		this.sugar = sugar;
	}

	public int getCaffeine() {
		return caffeine;
	}

	public void setCaffeine(int caffeine) {
		this.caffeine = caffeine;
	}
	

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
