package study.javase;

import study.javase.service.MenuService;

public class Main {
    public static void main(String[] args) {
        MenuService menuService = new MenuService();

        System.out.printf("菜单");
        for (int i=0;i<menuService.getMenuSize();i++){
            System.out.printf("\n%d. 价格: %.2f",i+1,menuService.getItemPrice(i+1L));
        }
        System.out.println();
        System.out.println("总价：");
        //这里我们模拟点1,2,3,4号餐品
        System.out.println(menuService.calculateTotal(new long[]{1,2,3,4}));

    }
}
