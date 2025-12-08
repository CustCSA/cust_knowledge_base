package study.javase.mapper.impl;

import study.javase.mapper.MenuMapper;
import study.javase.model.Item;

import java.util.HashMap;
import java.util.Map;

public class MenuMapperImpl implements MenuMapper {

    //模拟数据库
    static Map<Long, Item> menuMap = new HashMap<>();

    static {
        menuMap.put(1L, new Item("Burger", 5.99));
        menuMap.put(2L, new Item("Fries", 2.99));
        menuMap.put(3L, new Item("Soda", 1.49));
        menuMap.put(4L, new Item("Chicken", 1.49));
    }

    @Override
    public Item getItemById(long id) {
        Item item = menuMap.get(id);
        return item;
    }
    public int getMenuSize() {
        return menuMap.size();
    }
}
