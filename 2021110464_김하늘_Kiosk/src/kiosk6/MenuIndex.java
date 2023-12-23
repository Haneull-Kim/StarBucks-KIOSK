package kiosk6;

import java.util.ArrayList;
import java.util.List;

public class MenuIndex {
    private List<BeverageCategory> categories;

    private BeverageCategory coldbrewCategory;
    private BeverageCategory brewedcoffeeCategory;
    private BeverageCategory espressoCategory;
    private BeverageCategory frappuccinoCategory;
    private BeverageCategory blendedCategory;
    private BeverageCategory refresherCategory;
    private BeverageCategory physioCategory;
    private BeverageCategory teaCategory;
    private BeverageCategory etcCategory;

    public MenuIndex() {
        // categories ����Ʈ�� �ʱ�ȭ
        categories = new ArrayList<>();

        // �� ī�װ� ��ü�� �ʱ�ȭ�ϰ� categories ����Ʈ�� �߰�
        coldbrewCategory = new BeverageCategory("Cold Brew");
        categories.add(coldbrewCategory);
        
        brewedcoffeeCategory = new BeverageCategory("Brewed Coffee");
        categories.add(brewedcoffeeCategory);
        
        espressoCategory = new BeverageCategory("Espresso");
        categories.add(espressoCategory);
        
        frappuccinoCategory = new BeverageCategory("Frappuccino");
        categories.add(frappuccinoCategory);
        
        blendedCategory = new BeverageCategory("Blended");
        categories.add(blendedCategory);
        
        refresherCategory = new BeverageCategory("Refresher");
        categories.add(refresherCategory);
        
        physioCategory = new BeverageCategory("Physio");
        categories.add(physioCategory);
        
        teaCategory = new BeverageCategory("Tea");
        categories.add(teaCategory);
        
        etcCategory = new BeverageCategory("Etc");
        categories.add(etcCategory);

        // �޴��� �� ī�װ��� �߰�
        addMenuToCategory(coldbrewCategory, "��ü �ݵ� ���", 5.0,"/Users/kimhaneul/Downloads/startbucks_img/coldbrew_1.jpeg",  265, 29, 155);
        addMenuToCategory(coldbrewCategory, "�ٴҶ� ũ�� �ݵ� ���", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/coldbrew_2.jpeg", 125, 11, 155);
        addMenuToCategory(coldbrewCategory, "�ݵ� ���", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/coldbrew_3.jpeg", 5, 0, 155);

        addMenuToCategory(brewedcoffeeCategory, "�Ƹ޸�ī��", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/brewedcoffee_1.jpeg", 10, 0, 150);

        addMenuToCategory(espressoCategory, "����������", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/espresso_1.jpeg", 250, 100, 30);
        addMenuToCategory(espressoCategory, "ī�� ��", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/espresso_2.jpeg", 5, 0, 75);
        addMenuToCategory(espressoCategory, "���������� ���ĳ�", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/espresso_3.jpeg", 30, 1, 75);
        addMenuToCategory(espressoCategory, "ī��� �����ƶ�", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/espresso_4.jpeg", 200, 22, 75);
        addMenuToCategory(espressoCategory, "īǪġ��", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/espresso_5.jpeg", 110, 8, 75);

        addMenuToCategory(frappuccinoCategory, "�ڹ�Ĩ ����Ǫġ��", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/frappuccino_1.jpeg", 340, 42, 100);
        addMenuToCategory(frappuccinoCategory, "ī��� ����Ǫġ��", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/frappuccino_2.jpeg", 300, 39, 85);
        addMenuToCategory(frappuccinoCategory, "���ݸ� ũ�� Ĩ ����Ǫġ��", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/frappuccino_3.jpeg", 300, 40, 10);

        addMenuToCategory(blendedCategory, "���� �м� Ƽ �����", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/blended_1.jpeg", 150, 29, 0);
        addMenuToCategory(blendedCategory, "���� �ٳ��� �����", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/blended_2.jpeg", 290, 45, 0);
        addMenuToCategory(blendedCategory, "��ġ ���Ʈ �����", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/blended_3.jpeg", 240, 35, 0);

        addMenuToCategory(refresherCategory, "���� �ƻ��� ������̵�", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/refresher_1.jpeg", 265, 62, 73);
        addMenuToCategory(refresherCategory, "���� ��� ������̵�", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/refresher_2.jpeg", 95, 23, 25);
        addMenuToCategory(refresherCategory, "��ũ �帵ũ ���� ���� �ƻ���", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/refresher_3.jpeg", 100, 18, 30);
        addMenuToCategory(refresherCategory, "���� �帵ũ ���� ���� ���", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/refresher_4.jpeg", 90, 16, 25);

        addMenuToCategory(physioCategory, "���� ���� ������", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/physio_1.jpeg", 110, 26, 0);
        addMenuToCategory(physioCategory, "���� �м� ������", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/physio_2.jpeg", 145, 36, 0);
        addMenuToCategory(physioCategory, "�� ���� ������", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/physio_3.jpeg", 105, 25, 110);
        addMenuToCategory(physioCategory, "��ġ ���� ������", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/physio_4.jpeg", 145, 26, 37);

        addMenuToCategory(teaCategory, "��ũ ĳ���� ������", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/tea_1.jpeg", 130, 24, 1);
        addMenuToCategory(teaCategory, "Ŭ���� ��ũ Ƽ", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/tea_2.jpeg", 299, 31, 190);
        addMenuToCategory(teaCategory, "��Ʈ ���� Ƽ", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/tea_3.jpeg", 0, 0, 0);
        addMenuToCategory(teaCategory, "��׷��� Ƽ", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/tea_4.jpeg", 0, 0, 50);
        addMenuToCategory(teaCategory, "���� ���� ����", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/tea_5.jpeg", 0, 0, 16);

        addMenuToCategory(etcCategory, "�ñ״�ó ���ݸ�", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/etc_1.jpeg", 325, 32, 15);
        addMenuToCategory(etcCategory, "����", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/etc_2.jpeg", 240, 18, 0);
    
    }

    private static void addMenuToCategory(BeverageCategory category, String name, double price, String imagePath, int calories, int sugar, int caffeine) {
        Menu menu = new Menu(name, price, imagePath, calories, sugar, caffeine);
        category.addMenu(menu);
    }


    public List<BeverageCategory> getCategories() {
        return categories;
    }
    
    public List<Menu> getMenusByCategory(String categoryName) {
        MenuIndex menuIndex = new MenuIndex();
        BeverageCategory category = menuIndex.getCategoryByName(categoryName);
        return (category != null) ? category.getMenus() : new ArrayList<>();
    }
   

    public List<String> getMenuItemsByCategory(String categoryName) {
        List<String> menuItems = new ArrayList<>();

        // categoryName�� �ش��ϴ� ī�װ��� ã��
        BeverageCategory category = getCategoryByName(categoryName);

        // �ش� ī�װ��� ���� �޴����� ������
        if (category != null) {
            List<Menu> menus = category.getMenus();
            for (Menu menu : menus) {
                menuItems.add(menu.getName());
            }
        }

        return menuItems;
    }

    public BeverageCategory getCategoryByName(String categoryName) {
        for (BeverageCategory category : categories) {
            if (category.getCategoryName().equals(categoryName)) {
                return category;
            }
        }
        return null; // �ش��ϴ� ī�װ��� ���� ��� null ��ȯ
    }
    
    public List<String> filterMenus(int calorieLimit, int sweetnessLimit, int caffeineLimit) {
        List<String> filteredMenus = new ArrayList<>();

        for (BeverageCategory category : categories) {
            List<Menu> menus = category.getMenus();
            for (Menu menu : menus) {
                // �� �޴��� Į�θ�, �絵, ī���� �� ���Ͽ� ���͸�
                if (menu.getCalories() <= calorieLimit &&
                    menu.getSugar() <= sweetnessLimit &&
                    menu.getCaffeine() <= caffeineLimit) {
                    filteredMenus.add(menu.getName() + " - Į�θ�: " + menu.getCalories() +
                            ", �絵: " + menu.getSugar() + ", ī����: " + menu.getCaffeine());
                }
            }
        }

        return filteredMenus;
    }
    
    
}
