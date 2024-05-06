package Utils;

import java.io.IOException;
import java.util.Scanner;


public class Terminal {
    static Scanner input = new Scanner(System.in);
    
    public static void cleanTerminal(int tempoEspera) {
        try {
            String operatingSystem = System.getProperty("os.name");
            Thread.sleep(tempoEspera);
            if (operatingSystem.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }
    
    public static void proceedTo() {
        try {
            
            do {
                 String resp = input.nextLine();
                
                if (resp.isEmpty() || resp == null) {
                    cleanTerminal(0);
                    break;
                }

                System.out.println("Tecla inválida...");
            } while (true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void continueTo(String text){
        HandleText.align(text);
        proceedTo();
    }

}
