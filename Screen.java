package gerenciadordememoria;

import java.util.Scanner;

public class Screen {
    
    private Scanner input;
    private static  Screen instance;
    
    private Screen(){
        this.input = new Scanner(System.in);
    }
    
    public static Screen getInstance(){
        if(instance == null){
            instance = new Screen();
        }
        return instance;
    }
    
    public void showStart(){
        System.out.println("Bem vindo!");
        this.showMemSizeQuestion();
    }

    private void showMemSizeQuestion() {
        System.out.println("Qual o tamando desejado da memória?");
        System.out.println("(prefira valores da PG: 2, 4, 8, 16, 32, 64...)");
        int memSize = input.nextInt();
        GeneralController.getInstance().memorySizeAnswer(memSize);
    }

    public void showPageSizeQuestion() {
        System.out.println("Qual o tamando dsejado das páginas?");
        System.out.println("(prefira valores da PG: 2, 4, 8, 16, 32, 64...)");
        System.out.println("(não escolha um valor superior ao tamanho da memória)");
        int pageSize = input.nextInt();
        GeneralController.getInstance().pageSizeAnswer(pageSize);
    }

    public void showMemPageError(int unusedAreas) {
        System.out.println("O tamanho da memória não é divisível pelo número de páginas");
        System.out.println(unusedAreas + " áreas de memória serão desperdiçadas");
        System.out.println("Deseja continuar mesmo assim? [S/N]");
        String answer = input.next();
        if(answer.equalsIgnoreCase("S")){
            GeneralController.getInstance().ccontinue();
        } else if (answer.equalsIgnoreCase("N")) {
            this.showMemSizeQuestion();
        } else {
            System.out.println("Valor inválido");
            this.showMemPageError(unusedAreas);
        }
    }
    
    public void showProcessMaxSize(int memorySize){
        System.out.println("Qual o máximo de espaços que um processo pode ocupar?");
        System.out.println("Espaços disponíneis: " + memorySize);
        int processMaxSize = input.nextInt();
        GeneralController.getInstance().processMaxSizeAnswer(processMaxSize);
    }
    
    public void showOkBegin() {
        System.out.println("Tudo pronto! ;)");
        this.showOptMenu();
    }

    public void showOptMenu() {
        System.out.println("O que gostaria de fazer agora?");
        System.out.println("[1] Registrar processo");
        System.out.println("[2] Começar novamente");
        System.out.println("[3] Visualizar memória livre e processos");
        System.out.println("[4] Sair");
        int opt = input.nextInt();
        switch(opt){
            case 1: GeneralController.getInstance().goToRegistrateProcess();
                    break;
            case 2: GeneralController.getInstance().start();
                    break;
            case 3: GeneralController.getInstance().printAll();
                    this.showOptMenu();
                    break;
            case 4:
                    break;
            default:
        }
    }

    public void showRegistrateProcess(int freeMemory, int processMaxSize) {
        System.out.println("Qual o tamanho do processo que deseja registrar?");
        System.out.println("Lembrando que temos " + freeMemory + " espaços livres livres");
        System.out.println("e o tamanho máximo de um processo é " + processMaxSize);
        int processSize = input.nextInt();
        GeneralController.getInstance().registrateProcess(processSize);
    }

    public void showProcessSizeError() {
        System.out.println("O tamanho do processo solicitado é muito grande, tente novamente");
        GeneralController.getInstance().goToRegistrateProcess();
    }
    
    
}
