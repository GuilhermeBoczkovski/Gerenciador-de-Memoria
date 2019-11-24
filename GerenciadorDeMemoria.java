package gerenciadordememoria;

import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class

public class GerenciadorDeMemoria {
    
    private static  GerenciadorDeMemoria instance;
    private int processMaxSize;
    LinkedList freeMemory;
    ArrayList<Integer> processCodes;
    int pageSize;
    //tabela de paginas, array com endereços de memória
    private HashMap<Integer, int[]> pageTableByProcess = new HashMap<Integer, int[]>();
    //hashmap <numeroDoProcesso, tabelaDePaginas>
    
    private GerenciadorDeMemoria(){
        processCodes = new ArrayList();
    }
    
    public static GerenciadorDeMemoria getInstance(){
        if(instance == null){
            instance = new GerenciadorDeMemoria();
        }
        return instance;
    }
    
    public void registrateMemory(int memSize, int pageSize){
        freeMemory = new LinkedList(memSize, pageSize);
        this.pageSize = pageSize;
    }
    
    public boolean addNewProcess(int processSize){
        if(processSize > this.processMaxSize){
            return false;
        }else{
            //cria um codigo para o processo e registra no arraylist de codigos
            int processCode = processCodes.size();
            processCodes.add(processCode);
            int pageNum = (int)Math.ceil((double)processSize/(double)this.pageSize);
            int[] pageTable = new int[pageNum];
            int pt = 0;
            for(int i = processSize; i>0; i-=this.pageSize){
                int address = this.freeMemory.takePage();
                pageTable[pt] = address;
                pt++;
            }
            pageTableByProcess.put(processCode, pageTable);
            return true;
        }
    }

    public void setProcessMaxSize(int processMaxSize) {
        this.processMaxSize = processMaxSize;
    }
    
    public int getProcessMaxSize() {
       return this.processMaxSize;
    }
    
    public int getFreeMemory(){
        return this.freeMemory.getSize() * this.pageSize;
    }
    
    public int getFreeMemoryInPages(){
        return this.freeMemory.getSize();
    }
}
