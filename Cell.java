package gerenciadordememoria;

public class Cell {

    private Cell next;

    private int address;
    private int occupiedBytes;

    public Cell(Cell next, int address, int occupiedBytes) {
        this.next = next;
        this.address = address;
        this.occupiedBytes = occupiedBytes;
    }

    public Cell(int address) {
        this.address = address;
    }

    public void setNext(Cell next) {
        this.next = next;
    }

    public Cell getNext() {
        return this.next;
    }

    public int getAddress() {
        return this.address;
    }
  
    public int getOccupiedBytes(){
        return this.occupiedBytes;
    }

    void setOccupiedBytes(int occupiedBytes) {
        this.occupiedBytes = occupiedBytes;
    }
}