package UberProject;

class Corner {
    private int distanceFromSource;
    private int previousCorner;
    private int position;

    public Corner(int distanceFromSource, int previousCorner) {
        this.distanceFromSource = distanceFromSource;
        this.previousCorner = previousCorner;
    }

    public int getDistance() {
        return distanceFromSource;
    }

    public int getPI() {
        return previousCorner;
    }

    public void setDistance(int distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    public void setPI(int p) {
        previousCorner = p;
    }

    public void setPos(int p) {
        position = p;
    }

    public int getPos() {
        return position;
    }
    
    public String toString() {
        return distanceFromSource+"";
    }
}
