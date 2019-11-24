package gerenciadordememoria;

public class Cell {

  private Cell next;

  private int address;

  public Cell(Cell next, int address) {
    this.next = next;
    this.address = address;
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
}