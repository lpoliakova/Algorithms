package dataStructures;

public class Edge {
    private Integer head;
    private Integer bottom;
    private Integer length;

    public Edge(Integer head, Integer bottom, Integer length) {
        this.head = head;
        this.bottom = bottom;
        this.length = length;
    }

    public Integer getHead() {
        return head;
    }

    public Integer getBottom() {
        return bottom;
    }

    public Integer getLength() {
        return length;
    }
}
