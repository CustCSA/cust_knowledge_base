package study.javase.service;

import study.javase.mapper.impl.MenuMapperImpl;

public class MenuService {
    MenuMapperImpl menuMapper = new MenuMapperImpl();

    public double calculateTotal(long[] itemIds) {
        double total = 0.0;
        for (long id : itemIds) {
            total += menuMapper.getItemById(id).getPrice();
        }
        return total;
    }
    public double getItemPrice(long itemId) {
        return menuMapper.getItemById(itemId).getPrice();
    }

    public int getMenuSize() {
        return menuMapper.getMenuSize();
    }
}
