package Utils;

import java.util.Scanner;
import Views.Campaing.Campaing;


public class HandleInputs {
    static Scanner input = new Scanner(System.in);
    
    
    public static String VerifyUserExist(){
        String resp;
        int i = 0;
        do{ 
            if(i > 0)
                HandleText.align("\nEssa pessoa já passou pelo inferno!!!\nEscolha outro nome para o seu personagem: ");
                
            resp = getTextInput();
            i++;
        } while(Database.Db.getPlayerPos(resp) != 0);
        
        return resp;
    }
    
    
    public static String getTextInput(){
        return getTextInput("", "", "", false);
    }
    
    
    public static String getTextInput(String errorMessage, boolean choosePower){
        String resp = "";
        String warn = "";
        
        do{ 
            System.out.printf(warn);
            resp = getTextInput("", "", "", choosePower);
            warn = errorMessage;
            choosePower = false;
        } while(!resp.equalsIgnoreCase("sim") && !resp.equalsIgnoreCase("nao"));
        
        return resp;
    }
    
    
    public static String getTextInput(String errorMessage){
        return getTextInput("", errorMessage, "", false);
    }
    
    
    public static String getTextInput(String errorMessage, String textCompare){
        return getTextInput("", errorMessage, textCompare, false);
    }
    
    
    public static String getTextInput(String message, String errorMessage, String textCompare, boolean choosePower){
        String text;

        do{
            System.out.printf(message);
            
            if(Campaing.tournamentStarted){
                if(choosePower) { 
                    input.nextLine();
                } 
            }
            text = input.nextLine();

            if(text.isEmpty() || !text.contains(textCompare)){
                message = errorMessage.isEmpty() ? "Ops, Você inseriu um texto invalido!!!\nInsira novamente: " : errorMessage;
                choosePower = false;
            } 

        } while(text.isEmpty() || !text.contains(textCompare));
 
        return text;
    }
    
     
    public static int getIntInput(String message, int limit){
        return getIntInput(message, "", limit);
    }
    
    
    public static int getIntInput(int limit){
        return getIntInput("", "", limit);
    }
    
    
    public static int getIntInput(String message, String errorMessage, int limit){
        int num;
        
        do{
            System.out.printf(message);
            num = input.nextInt();
            
            if(num <= 0 || num > limit) 
                message = errorMessage.isEmpty() ? "Ops, Você inseriu um numero invalido!!!\nInsira novamente: " : errorMessage;
        } while(num <= 0 || num > limit);
        
        return num;
    }
}
