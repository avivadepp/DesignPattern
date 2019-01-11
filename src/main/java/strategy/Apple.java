package strategy;

//enum的作用域是package内
enum Colour { RED,GREEN }
enum Shape {ROUND,OVAL}
public class Apple {
    private String name;
    private Colour color;
    private String birthPlace;
    private double weight;
    private Shape shape;

    public Apple(String name,Colour color,String birthPlace,double weight,Shape shape){
        this.name=name;
        this.color=color;
        this.birthPlace=birthPlace;
        this.weight=weight;
        this.shape=shape;
    }

    public String getName(){return name;}
    public Colour getColor(){return color;}
    public Shape getShape(){return shape;}

}
