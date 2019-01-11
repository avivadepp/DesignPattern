package strategy;

public class AppleGreenImpl implements AppleStrategy {
    @Override
    public boolean predicate(Apple apple) {
        return Colour.GREEN.equals(apple.getColor());
    }
}