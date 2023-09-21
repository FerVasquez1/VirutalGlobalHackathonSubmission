package UberProject;

import java.util.ArrayList;

public class City {

    private ArrayList<Road>[] corners;
    private Corner[] cornerList;
    
    public City(int overall) {                      //
                                                    //
        corners = new ArrayList[overall];           //
        cornerList = new Corner[overall];           // Constructor for City class which creats an array type ArrayList type Road,
                                                    // as well as an array type Corner 
        for(int i = 0; i<overall; i++)              //
            corners[i] = new ArrayList<>();         //
    }

    public void add(int toCorner, int length, int fromCorner) {
                                                                            //
          corners[fromCorner].add(new Road(toCorner, length));              // Method add builds a type Road and type Corner 
          cornerList[fromCorner] = new Corner(length, fromCorner);          // 
          cornerList[fromCorner].setPos(fromCorner);                        //  
                                                                            // 
    }

    public ArrayList<Road> findNeighbors(int cornerIndex) {         // Method findNeighbors returns an ArrayList type Road of all
                                                                    // connected Roads of int cornerIndex
        ArrayList<Road> connectedRoads = corners[cornerIndex];

       for(int i = 0; i<corners.length; i++){

            if(i != cornerIndex){          
                                                                    //Implementation allows for duo-directional Roads
                ArrayList<Road> neighborRoads = corners[i];

                for(Road road: neighborRoads)
                    if(road.getDestination() == cornerIndex)
                        connectedRoads.add(new Road(i,road.getRoadLength()));
            }

        }

        return connectedRoads;
    }

    public Road findRoadBetweenCorners(int fromCorner, int toCorner){       // 
                                                                            //
        for (int i = 0; i < corners[fromCorner].size(); i++)                //
            if (corners[fromCorner].get(i).getDestination() == toCorner)    // Method findRoadBetweenCorners return a road between road f and road t 
                return corners[fromCorner].get(i);                          //
                                                                            //
        return null;                                                        //
    }

    public Corner[] ShortestPathFromSource(int source, int maxRadius) {         // Method ShortestPathFromSource is an implementation of Dijkstra's 
                                                                                // Algorithm which given a node (Corners), finds the shortest path to 
        Corner[] nodes = new Corner[this.corners.length];                       // all other nodes in a graph
        Queue queue = new Queue();
        
        for (int i = 0; i < this.corners.length; i++){                  

            nodes[i] = new Corner(Integer.MAX_VALUE, -1);
            nodes[i].setPos(i);
            queue.enqueue(nodes[i]);

        }

        nodes[source].setDistance(0);

        int initialQueuePosition = queue.findIndex(nodes[source].getPos());
        queue.enqueue(queue.remove(initialQueuePosition));

        while (queue.size()!=0){                                                    // While is in charge of finding the shortest distane between all Corners

            Corner currentCorner = queue.dequeue(); 
            
            for (Road neighborRoad: findNeighbors(currentCorner.getPos())) {

                int potentialDistance = nodes[currentCorner.getPos()].getDistance() + neighborRoad.getRoadLength();

                if (potentialDistance > maxRadius) return nodes;                                          // Value k is the a radius which if the distance (potentialDistance) exceeds
                                                                                    // the function returns because the a Road outside of radius   
                if (potentialDistance < nodes[neighborRoad.getDestination()].getDistance()) {              // reduces efficieny

                    nodes[neighborRoad.getDestination()].setDistance(potentialDistance);
                    nodes[neighborRoad.getDestination()].setPI(currentCorner.getPos());
                    
                    if(queue.size() != 0){
                        int updatedQueuePosition = queue.findIndex(nodes[neighborRoad.getDestination()].getPos());
                        queue.enqueue(queue.remove(updatedQueuePosition));
                    }
                }
            }
        }
        return nodes;
    }

    public Road findOptimalPickupLocation() {                                          // Method findOptimalPickupLocation is a unique algorithm which calculates the most
                                                                                       // optimal Road to place a PickUp spot
        Road[] CandidateLocations = new Road[cornerList.length];
        Integer[] distanceToCandidates = new Integer[cornerList.length];

        for (int i = 0; i < cornerList.length; i++){                    

                Corner currentCorner = cornerList[i];

                if (currentCorner == null)
                    continue;
                
                Corner[] shortestPaths = ShortestPathFromSource(currentCorner.getPos(), 100);    // To find the most optimal Road for PickUp spot, we run
                Corner farthestCorner = findCornerWithMaximumDistance(shortestPaths);                      // ShortestPathFromSource and feed it into Method findCornerWithMaximumDistance
                                                                      // 
                CandidateLocations[i] = findRoadBetweenCorners(farthestCorner.getPI(), farthestCorner.getPos());            
                distanceToCandidates[i] = farthestCorner.getDistance();                         // Adds the last crossed Road to an array
            
        }

        return CandidateLocations[findIndexOfMinimumDistance(distanceToCandidates)];                              // findIndexOfMinimumDistance is called to index the most optimal Road
    }

    public Corner findCornerWithMaximumDistance(Corner[] nodes) {      
                                                                       // 
        int maxDistance = -1;                                          // 
        Corner cornerWithMaxDistance = nodes[0];                       //
                                                                       //
        for (Corner c : nodes){                                        //
                                                                       // Method findCornerWithMaximumDistance uses simple two step
            if (c.getDistance() > maxDistance){                        // proccessing in order to compare the distances of each corner 
                                                                       // to return the largest
                maxDistance = c.getDistance();                         //
                cornerWithMaxDistance = c;                             //
            }                                                          //
                                                                       //
        }                                                              //
                                                                       //
        return cornerWithMaxDistance;                                  //
    }
    
    public int findIndexOfMinimumDistance(Integer[] Roads){
        
        int minDistance = Roads[0];
        int indexOfMinimum = -1;
        
        for (int i = 1; i < Roads.length; i++) {

            if (Roads[i] == null)
                continue;

            int currentDistance = Roads[i];

            if (currentDistance < minDistance){

                minDistance = currentDistance;
                indexOfMinimum = i;

            }
        }
        return indexOfMinimum;
    }
}
