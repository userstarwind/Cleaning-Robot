package TrashClass;

import java.util.Random;

public class TrashMap {
    private double width;
    private double length;
    private int trash_amount;
    private TrashClass.Trash[] trashLocation;

    public TrashMap(double length,double width,int trash_amount) {
        this.trash_amount=trash_amount;
        this.length=length;
        this.width=width;
    }

    public void init(){
        trashLocation=new TrashClass.Trash[trash_amount];
        Random random=new Random();
        for(int i=0;i<trash_amount;i++)
        {
            double rand_x=length * random.nextDouble();
            double rand_y=width * random.nextDouble();
            trashLocation[i]=new TrashClass.Trash(rand_x,rand_y);
        }
    }
    public TrashMap(double length,double width){
        this(length,width,5);
    }

    public TrashClass.Trash[] getTrashLocation() {
        return trashLocation;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public int getTrash_amount() {
        return trash_amount;
    }

    @Override
    public String toString()
    {

        StringBuilder res=new StringBuilder();
        res.append(String.format("TrashMap:size=%d\n",trash_amount));
        res.append("Distribution of trash:");
        for(int i=0;i<trash_amount;i++)
        {
            res.append(trashLocation[i].toString());
            if(i!=trash_amount-1)
                res.append(",");
        }
        res.append("\n");
        return res.toString();
    }
}
