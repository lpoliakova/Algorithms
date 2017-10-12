package dataStructures;

public class DijkstraVertice implements Comparable<DijkstraVertice> {
    private Integer number;
    private Integer distanceToVertice;

    public DijkstraVertice(Integer number, Integer distance) {
        this.number = number;
        this.distanceToVertice = distance;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getDistance() {
        return distanceToVertice;
    }

    public void setDistance(Integer newDistance) {
        this.distanceToVertice = newDistance;
    }

    public void setMinDistance(Integer newDistance) {
        if (newDistance < this.distanceToVertice) {
            this.distanceToVertice = newDistance;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DijkstraVertice that = (DijkstraVertice) o;

        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public int compareTo(DijkstraVertice otherVertice) {
        return this.distanceToVertice - otherVertice.distanceToVertice;
    }
}
