package kiosk6;

import java.util.ArrayList;
import java.util.List;

public class BeverageCategory {
    private String categoryName;
    private List<Menu> menus;
    

    public BeverageCategory(String categoryName) {
        this.categoryName = categoryName;
        this.menus = new ArrayList<>();
    }

    public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public void addMenu(Menu menu) {
        menus.add(menu);
    }

    public List<Menu> getMenus() {
        return menus;
    }
}
