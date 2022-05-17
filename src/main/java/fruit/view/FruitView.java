package fruit.view;

import fruit.pojo.Fruit;

import java.util.List;

public interface FruitView {
    public List<Fruit> showFruitList();
    public void addFruit();
    public void showFruitInfo();
    public void delFruit();
}
