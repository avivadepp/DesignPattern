package StreamALambda;

enum Colour { YELLOW,GREEN }
enum Shape {ROUND,OVAL}
public class Orange {
    private String name;
    private Colour color;
    private String birthPlace;
    private int weight;
    private Shape shape;

    public Orange(String name,Colour color,String birthPlace,int weight,Shape shape){
        this.name=name;
        this.color=color;
        this.birthPlace=birthPlace;
        this.weight=weight;
        this.shape=shape;
    }

    public String getName(){return name;}
    public Colour getColor(){return color;}
    public Shape getShape(){return shape;}
    public int getWeight() {
        return weight;
    }
}