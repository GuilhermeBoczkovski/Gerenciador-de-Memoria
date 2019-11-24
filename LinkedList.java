package gerenciadordememoria;

public class LinkedList {
    
    private Cell first;
    private Cell last;
    private int size;
    
    public LinkedList(int memSize, int pageSize) {
    
    for(int i=0; i<memSize/pageSize; i++){
        if (this.size == 0) {
            Cell newCell = new Cell(i);
            this.first = newCell;
            this.last = this.first;
        } else {
            Cell newCell = new Cell(i);
            this.last.setNext(newCell);
            this.last = newCell;
        }
        this.size++;
    }
    
  }
    
    public int takePage() {
        Cell store = this.first;
        this.first = this.first.getNext();
        size--;
        return store.getAddress();
    }
    
    public int getSize(){
        return this.size;
    }
    
}