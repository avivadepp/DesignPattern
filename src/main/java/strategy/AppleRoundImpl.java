package strategy;

public class AppleRoundImpl implements AppleStrategy{
    @Override
    public boolean predicate(Apple apple) {
        return Shape.ROUND.equals(apple.getShape());
    }
}
