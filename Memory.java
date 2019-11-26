package gerenciadordememoria;

public class Memory {
    
    private static  Memory instance;
    private int memSize;
    
    private Memory(){
        
    }
    
    public static Memory getInstance(){
        if(instance == null){
            instance = new Memory();
        }
        return instance;
    }
    
    public void setSize(int memSize){
        this.memSize = memSize;
    }
}
