package Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.Scanner;

public class HandleType {
    
     public static double[] mainType(int opponentPoints, String textTotype) {
         return mainType(opponentPoints, textTotype, -1, -1);
     }
    
    public static double[] mainType(int opponentPoints, String textTotype, int minTimeOpponent, int maxTimeOpponent) {
        double startTime = minTimeOpponent == -1 ? getCurrentTime() : 0;
        
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String textTyped = "";
        double accuracyRate;
        double endTime;
        
        if(minTimeOpponent == -1){
            accuracyRate = calculateAccuracyRate(scanner, textTotype, textTyped);
            endTime = getCurrentTime();
        } else {
           accuracyRate = 100;
           endTime = random.nextInt(minTimeOpponent, maxTimeOpponent);
        }
         
        double timeTaken = calculateDuration(startTime, endTime);  
        double points = calculatePoints(accuracyRate, (double)opponentPoints, timeTaken);
        
    
        double[] resp = { points, timeTaken };
        return resp;
    }

    private static double calculateAccuracyRate(Scanner scanner, String textTotype, String textTyped) {
        
        int totalLetters = 0;
        double correctGuesses = 0;
   

        System.out.printf("\nEscreva o texto abaixo:\n\"%s\": ", textTotype);
        textTyped = scanner.nextLine();

        char[] lettersOfTyped = textTyped.toCharArray();
        char[] lettersOfText = new char[lettersOfTyped.length];

        lettersOfText = textTotype.toCharArray();

        if(lettersOfTyped.length > lettersOfText.length ){
            totalLetters += lettersOfTyped.length;

            for(int x = 0; x < lettersOfText.length; x++) {
                if(lettersOfTyped[x] == lettersOfText[x]) {
                    correctGuesses++;
                }
           }
        } else {
            totalLetters += lettersOfText.length;

            for(int y = 0; y < lettersOfTyped.length; y++) {
                if(lettersOfTyped[y] == lettersOfText[y]) {
                    correctGuesses++;
                }
            }
        }

        double accuracyPercentage = (correctGuesses / totalLetters) * 100;
        return accuracyPercentage;
    }

    private static double getCurrentTime() {
        long currentTime = System.currentTimeMillis();
        double time = (double) currentTime / 1000;

        return time;
    }

    public static double calculatePoints(double points, double timeTaken, int addTime) {    
        timeTaken += addTime;
        if(addTime != 0){
            return calculatePoints(1.0, points, timeTaken);
        } else {
            return 0.0;
        }
    }  
    
    public static double calculatePoints(double accuracyRate, double opponentPoints, double timeTaken) {
        double points = accuracyRate * opponentPoints / timeTaken;        
        return points;
    }  
    
    public static double[][] reCalculatePoints(double[] player, double[] opponent, String powerPlayer, String powerOpponent){
        double[][] resp = new double[2][2];
        
        int segPower = Integer.parseInt(powerPlayer); 
        int segPowerOpponent =  Integer.parseInt(powerOpponent); 
        
        double nerf = HandleType.calculatePoints(player[0], player[1], segPower);
        double nerfOpponent = HandleType.calculatePoints(opponent[0], opponent[1], segPowerOpponent);

        if(powerPlayer.contains("-")){
            player[0] = player[0] + nerf;  
            player[1] = player[1] - segPower;
        } else {
            double newOpponentPoints = opponent[0] - nerf;
            opponent[0] = newOpponentPoints <= 0 ? 0 : newOpponentPoints;
            opponent[1] += segPower;
        }
        

        if(!powerOpponent.isEmpty()) {
            double newPlayerPoints = player[0] - nerfOpponent;    
            player[0] = newPlayerPoints <= 0 ? 0 : newPlayerPoints;
            resp[0][1] = player[1] + segPowerOpponent;
        }

        BigDecimal bd = new BigDecimal(player[0]).setScale(3, RoundingMode.HALF_EVEN);
        BigDecimal bdOpponent = new BigDecimal(opponent[0]).setScale(3, RoundingMode.HALF_EVEN);

        resp[0][0] = bd.doubleValue();
        resp[1][0] = bdOpponent.doubleValue();
        resp[1][1] = opponent[1];
        
        return resp;
    }
    
    private static double calculateDuration(double start, double end) {
        double timeTaken = end - start;
        BigDecimal bd = new BigDecimal(timeTaken).setScale(1, RoundingMode.HALF_EVEN);
        
        return bd.doubleValue();
    }
}