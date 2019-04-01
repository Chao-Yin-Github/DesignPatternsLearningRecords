package DrcoratorPattern;

public class Whip extends CondimentDecorator{
    Beverage beverage;
    public Whip(Beverage beverage){
        this.beverage = beverage;
    }
    public double cost() {
        return 0.10+beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription();
    }
}
