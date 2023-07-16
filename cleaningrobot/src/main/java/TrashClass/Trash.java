package TrashClass;

public class Trash {
    private double x;
    private double y;

    public String[] getPosition(){
        return new String[]{String.valueOf(this.x), String.valueOf(this.y)};
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Trash(double x, double y){
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        res.append(String.format("[%.2f,%.2f]",getX(),getY()));
        return res.toString();
    }
}
