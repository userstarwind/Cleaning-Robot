package StartFrameTool;

import TrashClass.TrashHashMap;
import TrashClass.TrashMap;
import TrashClass.TrashRecorder;

import javax.swing.*;
import java.util.*;


public class CleaningRobot {
    private JTextArea outputArea;
    private TrashRecorder trashRecorder=new TrashRecorder();
    private double totalDistance;
    private double x;
    private double y;
    private TrashHashMap map;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    public String[] getPosition(){
        return new String[]{String.valueOf(this.x), String.valueOf(this.y)};
    }
    public void setPosition(Double x,double y){
        setX(x);
        setY(y);
    }

    public CleaningRobot(TrashMap trashMap, JTextArea outputArea){
        this.outputArea=outputArea;
        totalDistance=0;
        Random rand=new Random();
        this.x=rand.nextDouble()*trashMap.getLength();
        this.y=rand.nextDouble()*trashMap.getWidth();
        trashRecorder.clear();
        trashRecorder.write(new String[]{String.valueOf(trashMap.getLength()), String.valueOf(trashMap.getWidth())});
        trashRecorder.write(getPosition());
        map=new TrashHashMap(trashMap,this.x,this.y);
    }



    public void collect(){
        outputArea.append(String.format("current position:[%.2f,%.2f]\n",this.x,this.y));
        Map.Entry<TrashClass.Trash, Double> minEntry = map.findNearestTrash();
        outputArea.append(String.format("the nearest trash position:[%.2f,%.2f]\n",minEntry.getKey().getX(),minEntry.getKey().getY()));
        totalDistance+= minEntry.getValue();
        outputArea.append("Moving...\n");
        setPosition(minEntry.getKey().getX(),minEntry.getKey().getY());
        trashRecorder.write(minEntry.getKey().getPosition());
        map.remove(minEntry.getKey());
        outputArea.append("Managed to collect!\n");
        outputArea.append(map.toString());
        map.updateTrashHashMap(this.x,this.y);
    }
    public void clean(){
        while(!map.isEmpty())
            collect();
        outputArea.append(String.format("the total distance is %.2f",totalDistance));
    }


}
