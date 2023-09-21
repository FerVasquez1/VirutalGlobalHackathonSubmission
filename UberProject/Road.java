package UberProject;

class Road {
    private int Destination;
    private int roadLength;

    public Road (int Destination, int roadLength) {
        this.Destination = Destination;
        this.roadLength = roadLength;
    }

    public int getDestination(){
        return Destination;
    }

    public int getRoadLength(){
        return roadLength;
    }

    public void setTo(int Destination){
        this.Destination = Destination;
    }

    public void setLength(int roadLength){
        this.roadLength = roadLength;
    }

    public String toString() {
        return "Destination: " + Destination + ", Length: " + roadLength;
    }
}
