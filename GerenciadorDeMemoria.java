package gerenciadordememoria;

import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class

public class GerenciadorDeMemoria {
    
    private static  GerenciadorDeMemoria instance;
    private int processMaxSize;
    private LinkedList memory;
    private LinkedList freeMemory;
    private ArrayList<Integer> processCodes;
    private int pageSize;
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
        memory = new LinkedList(memSize, pageSize);
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
                Cell cell = memory.getFirst();
                while(cell.getAddress() != address){
                    cell = cell.getNext();
                }
                if(i >= this.pageSize){
                    cell.setOccupiedBytes(pageSize);
                } else {
                    cell.setOccupiedBytes(i);
                }
                //registrat na cell de endereço address se i.pageSize registra o tamanho = pageSize, senao tamanho=i
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
    
    public void printAll(){
        System.out.println("");
        System.out.println("=============================================================");
        System.out.println("Iniciando impressão das tabelas de páginas por processo");
        System.out.println("");
        for (Integer processCode: pageTableByProcess.keySet()){
            String key = processCode.toString();
            String value = "";
            for(int pagina: pageTableByProcess.get(processCode)){
                value += pageTableByProcess.get(processCode)[pagina] + ", ";
            }
            System.out.println("----------------------------------------------------");
            System.out.println("");
            System.out.println("Número do Processo: " + key);
            System.out.println("Páginas ocupadas pelo processo: " + value);  
            System.out.println("");
        }
        System.out.println("");
        System.out.println("=============================================================");
        System.out.println("Iniciando impressão da memória");
        System.out.println("");
        System.out.println("");
        
        Cell n = memory.getFirst();        
        while (n != null) { 
            System.out.println("Página:               " + n.getAddress());
            System.out.println("Endereço físico;      " + n.getAddress()*this.pageSize);
            System.out.println("Espaços ocupados:     " + n.getOccupiedBytes());
            System.out.println("--------------------------------------------------------");
            System.out.println("");
            n = n.getNext();
        }
    }
}
