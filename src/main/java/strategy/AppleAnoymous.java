package strategy;

import java.util.ArrayList;
import java.util.List;

public class AppleAnoymous {
    private static List<Apple> filterApplesByStrategy(List<Apple> inventory, AppleStrategy strategy) {
        List<Apple> result = new ArrayList<>(inventory.size());
        for (Apple apple : inventory) {
            if (strategy.predicate(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        Apple a1 = new Apple("a1",Colour.GREEN, "SH", 150.34, Shape.OVAL);
        Apple a2 = new Apple("a2",Colour.RED, "BJ", 110.34, Shape.ROUND);
        Apple a3=new Apple("a3",Colour.RED, "GZ", 210.34, Shape.ROUND);
        apples.add(a1);
        apples.add(a2);
        apples.add(a3);
        List<Apple> greenApples = filterApplesByStrategy(apples, new AppleStrategy() {
            @Override
            public boolean predicate(Apple apple) {
                return Colour.GREEN.equals(apple.getColor());
            }
        });
        List<Apple> roundApples = filterApplesByStrategy(apples, new AppleStrategy() {
            @Override
            public boolean predicate(Apple apple) {
                return Shape.ROUND.equals(apple.getShape());
            }
        });
        System.out.println(greenApples.get(0).getName());
        System.out.println(roundApples.get(0).getName());
    }

}
