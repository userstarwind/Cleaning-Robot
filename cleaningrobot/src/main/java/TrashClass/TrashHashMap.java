package TrashClass;

import java.util.*;

public class TrashHashMap {
    HashMap<Trash,Double> hashMap;

    public TrashHashMap(TrashMap trashMap, double x, double y){

        loadTrashHashMap(trashMap,x,y);
    }


    public double calcDistance(double x,double y){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }
    public void loadTrashHashMap(TrashMap trashMap, double x, double y){
        hashMap=new HashMap<TrashClass.Trash,Double>();
        for(int i=0;i< trashMap.getTrash_amount();i++) {
            hashMap.put(trashMap.getTrashLocation()[i],calcDistance(trashMap.getTrashLocation()[i].getX()-x,trashMap.getTrashLocation()[i].getY()-y));
        }
    }
    public void updateTrashHashMap(double x,double y){
        Iterator<TrashClass.Trash> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()) {
            TrashClass.Trash key = iterator.next();
            hashMap.put(key,calcDistance(key.getX()-x,key.getY()-y));
        }
    }

    public Map.Entry findNearestTrash(){
        return Collections.min(hashMap.entrySet(), Comparator.comparingDouble(Map.Entry::getValue));
    }

    public void remove(TrashClass.Trash trash){
        hashMap.remove(trash);
    }
    @Override
    public String toString(){
        if(hashMap.isEmpty())
            return "All trash is cleaned";
        else {
            StringBuilder res = new StringBuilder();
            res.append("remaining trash position:");
            for (TrashClass.Trash key : hashMap.keySet()) {
                res.append(String.format("[%.2f,%.2f]", key.getX(), key.getY()));
            }
            res.append("\n");
            return res.toString();
        }
    }
    public boolean isEmpty(){
        return hashMap.isEmpty();
    }
}
