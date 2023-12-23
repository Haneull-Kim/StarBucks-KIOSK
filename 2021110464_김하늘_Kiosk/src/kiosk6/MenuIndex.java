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
        // categories 리스트를 초기화
        categories = new ArrayList<>();

        // 각 카테고리 객체를 초기화하고 categories 리스트에 추가
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

        // 메뉴를 각 카테고리에 추가
        addMenuToCategory(coldbrewCategory, "돌체 콜드 브루", 5.0,"/Users/kimhaneul/Downloads/startbucks_img/coldbrew_1.jpeg",  265, 29, 155);
        addMenuToCategory(coldbrewCategory, "바닐라 크림 콜드 브루", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/coldbrew_2.jpeg", 125, 11, 155);
        addMenuToCategory(coldbrewCategory, "콜드 브루", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/coldbrew_3.jpeg", 5, 0, 155);

        addMenuToCategory(brewedcoffeeCategory, "아메리카노", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/brewedcoffee_1.jpeg", 10, 0, 150);

        addMenuToCategory(espressoCategory, "에스프레소", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/espresso_1.jpeg", 250, 100, 30);
        addMenuToCategory(espressoCategory, "카페 라떼", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/espresso_2.jpeg", 5, 0, 75);
        addMenuToCategory(espressoCategory, "에스프레소 콘파냐", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/espresso_3.jpeg", 30, 1, 75);
        addMenuToCategory(espressoCategory, "카라멜 마끼아또", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/espresso_4.jpeg", 200, 22, 75);
        addMenuToCategory(espressoCategory, "카푸치노", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/espresso_5.jpeg", 110, 8, 75);

        addMenuToCategory(frappuccinoCategory, "자바칩 프라푸치노", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/frappuccino_1.jpeg", 340, 42, 100);
        addMenuToCategory(frappuccinoCategory, "카라멜 프라푸치노", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/frappuccino_2.jpeg", 300, 39, 85);
        addMenuToCategory(frappuccinoCategory, "초콜릿 크림 칩 프라푸치노", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/frappuccino_3.jpeg", 300, 40, 10);

        addMenuToCategory(blendedCategory, "망고 패션 티 블렌디드", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/blended_1.jpeg", 150, 29, 0);
        addMenuToCategory(blendedCategory, "망고 바나나 블렌디드", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/blended_2.jpeg", 290, 45, 0);
        addMenuToCategory(blendedCategory, "피치 요거트 블렌디드", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/blended_3.jpeg", 240, 35, 0);

        addMenuToCategory(refresherCategory, "딸기 아사이 레모네이드", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/refresher_1.jpeg", 265, 62, 73);
        addMenuToCategory(refresherCategory, "망고 용과 레모네이드", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/refresher_2.jpeg", 95, 23, 25);
        addMenuToCategory(refresherCategory, "핑크 드링크 위드 딸기 아사이", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/refresher_3.jpeg", 100, 18, 30);
        addMenuToCategory(refresherCategory, "퍼플 드링크 위드 망고 용과", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/refresher_4.jpeg", 90, 16, 25);

        addMenuToCategory(physioCategory, "레드 애플 피지오", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/physio_1.jpeg", 110, 26, 0);
        addMenuToCategory(physioCategory, "유자 패션 피지오", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/physio_2.jpeg", 145, 36, 0);
        addMenuToCategory(physioCategory, "쿨 라임 피지오", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/physio_3.jpeg", 105, 25, 110);
        addMenuToCategory(physioCategory, "피치 딸기 피지오", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/physio_4.jpeg", 145, 26, 37);

        addMenuToCategory(teaCategory, "핑크 캐모마일 릴렉서", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/tea_1.jpeg", 130, 24, 1);
        addMenuToCategory(teaCategory, "클래식 밀크 티", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/tea_2.jpeg", 299, 31, 190);
        addMenuToCategory(teaCategory, "민트 블렌드 티", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/tea_3.jpeg", 0, 0, 0);
        addMenuToCategory(teaCategory, "얼그레이 티", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/tea_4.jpeg", 0, 0, 50);
        addMenuToCategory(teaCategory, "제주 유기 녹차", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/tea_5.jpeg", 0, 0, 16);

        addMenuToCategory(etcCategory, "시그니처 초콜릿", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/etc_1.jpeg", 325, 32, 15);
        addMenuToCategory(etcCategory, "우유", 5.0, "/Users/kimhaneul/Downloads/startbucks_img/etc_2.jpeg", 240, 18, 0);
    
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

        // categoryName에 해당하는 카테고리를 찾음
        BeverageCategory category = getCategoryByName(categoryName);

        // 해당 카테고리의 음료 메뉴들을 가져옴
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
        return null; // 해당하는 카테고리가 없을 경우 null 반환
    }
    
    public List<String> filterMenus(int calorieLimit, int sweetnessLimit, int caffeineLimit) {
        List<String> filteredMenus = new ArrayList<>();

        for (BeverageCategory category : categories) {
            List<Menu> menus = category.getMenus();
            for (Menu menu : menus) {
                // 각 메뉴의 칼로리, 당도, 카페인 값 비교하여 필터링
                if (menu.getCalories() <= calorieLimit &&
                    menu.getSugar() <= sweetnessLimit &&
                    menu.getCaffeine() <= caffeineLimit) {
                    filteredMenus.add(menu.getName() + " - 칼로리: " + menu.getCalories() +
                            ", 당도: " + menu.getSugar() + ", 카페인: " + menu.getCaffeine());
                }
            }
        }

        return filteredMenus;
    }
    
    
}
