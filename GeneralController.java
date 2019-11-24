package gerenciadordememoria;

public class GeneralController {
    
    private static  GeneralController instance;
    private int memSize;
    private int pageSize;
    
    private GeneralController(){
        
    }
    
    public static GeneralController getInstance(){
        if(instance == null){
            instance = new GeneralController();
        }
        return instance;
    }
    
    public void start() {
        Screen.getInstance().showStart();
    }

    public void memorySizeAnswer(int memSize) {
        this.memSize = memSize;
        Screen.getInstance().showPageSizeQuestion();
    }

    public void pageSizeAnswer(int pageSize) {
        this.pageSize = pageSize;
        if(this.memSize%this.pageSize==0){
            GerenciadorDeMemoria.getInstance().registrateMemory(this.memSize, this.pageSize);
            Screen.getInstance().showProcessMaxSize(this.memSize/this.pageSize * this.pageSize);
        }else {
            Screen.getInstance().showMemPageError(this.memSize%this.pageSize);
        }
    }

    public void ccontinue() {
        GerenciadorDeMemoria.getInstance().registrateMemory(this.memSize, this.pageSize);
        Screen.getInstance().showProcessMaxSize((this.memSize/this.pageSize) * this.memSize);
    }

    public void processMaxSizeAnswer(int processMaxSize) {
        GerenciadorDeMemoria.getInstance().setProcessMaxSize(processMaxSize);
        Screen.getInstance().showOkBegin();
    }

    public void goToRegistrateProcess() {
        int freeMemory = GerenciadorDeMemoria.getInstance().getFreeMemory();
        int processMaxSize = GerenciadorDeMemoria.getInstance().getProcessMaxSize();
        Screen.getInstance().showRegistrateProcess(freeMemory, processMaxSize);
    }

    void registrateProcess(int processSize) {
        boolean ok = GerenciadorDeMemoria.getInstance().addNewProcess(processSize);
        if(!ok){
            Screen.getInstance().showProcessSizeError();
        } else {
            Screen.getInstance().showOkBegin();
        }
    }
}
