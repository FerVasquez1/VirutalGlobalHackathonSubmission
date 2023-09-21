package UberProject;
import java.util.ArrayList;

public class Queue {
    
    private ArrayList<Corner> queue;

    public Queue() {
        queue = new ArrayList<>();
    }

    public void enqueue(Corner C){
        
        int index = 0;
        for(index = 0; index < queue.size() && C.getDistance() > queue.get(index).getDistance(); index++);
            
        queue.add(index, C);
    }

    public Corner dequeue(){
        return queue.remove(0);
    }

    public int size(){
        return queue.size();
    }

    public int findIndex(int n){

        for(int i = 0; i<queue.size(); i++){

            if(queue.get(i).getPos()==n)
            return i;

        }
        
        return -1;
    }

    public Corner remove(int n){
        return queue.remove(n);
    }

    public String toString(){
        return queue+"";
    }

}
