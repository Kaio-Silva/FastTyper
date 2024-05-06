package Views;

import Utils.*;
import java.util.Scanner;

public class Menu {

    static Scanner input = new Scanner(System.in);

    public static int mainMenu(int i) {
        int option;
        String warning = HandleText.colorText("Ajuste o tamanho do terminal para jogar: A logo tem que ser totalmente visível.\n","yellow");
        String[] options = {"1 - Modo Ranking", "2 - Modo Campanha", HandleText.colorText("3 - Sair", "red")};
        
        do { 
            if(i == -1){
                System.out.println(AsciiArts.LOGO + "\n");
                HandleText.align(warning);
                HandleText.align("Press Enter");
                Terminal.proceedTo();
                i = 0;
            }
                
            
            System.out.println(AsciiArts.LOGO + "\n");
            HandleText.align(options, "center", true);
            option = input.nextInt();


            if (option == 1 || option == 2 || option == 3) {
                Terminal.cleanTerminal(0);
                break;
            } else {
                HandleText.align("\nOps, você inseriu uma opção invalida!!!", "left", "red");
                Terminal.cleanTerminal(2000);
            }
             
            warning = "";
            Terminal.cleanTerminal(0);
        } while (true);

        return option;
    }

}
