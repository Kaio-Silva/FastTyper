package Utils;

import Views.Campaing.Tournament;
import Views.Ranking.Match;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.Scanner;

public class HandleType {
    static Random random = new Random();
    public static String wrongsTyped = "";
    
    public static double[] mainType(int opponentPoints, String textTotype) {
        return mainType(opponentPoints, textTotype, -1, -1, -1);
    }
    
    public static double[] mainType(int opponentPoints, String textTotype, int minTimeOpponent, int maxTimeOpponent, int level) {
        double startTime = minTimeOpponent == -1 ? getCurrentTime() : 0;
        
        Scanner scanner = new Scanner(System.in);

        String textTyped = "";
        double accuracyRate;
        double endTime;
        
        if(minTimeOpponent == -1){
            accuracyRate = calculateAccuracyRate(scanner, textTotype, textTyped, -1);
            endTime = getCurrentTime();
        } else {
           accuracyRate = calculateAccuracyRate(scanner, textTotype, textTyped, level);
           endTime = random.nextInt(minTimeOpponent, maxTimeOpponent);
        }
         
        double timeTaken = calculateDuration(startTime, endTime);  
        double points = calculatePoints(accuracyRate, (double)opponentPoints, timeTaken);
        
    
        double[] resp = { points, timeTaken };
        return resp;
    }

    private static double calculateAccuracyRate(Scanner scanner, String textTotype, String textTyped, int level) {
        
        int totalLetters = 0;

        char[] lettersOfText = textTotype.toCharArray();
        char[] lettersOfTyped = new char[lettersOfText.length];

        if(level == -1){
            System.out.printf("\nEscreva o texto abaixo:\n\"%s\":\n", textTotype);
            textTyped = scanner.nextLine();
        }
        
        if(textTyped.length() > lettersOfText.length){
            totalLetters += textTyped.length();
            lettersOfTyped = textTyped.toCharArray();
        } else{
            totalLetters += lettersOfText.length;
            for(int i = 0; i < textTyped.length(); i++)
                lettersOfTyped[i] = textTyped.charAt(i);
        }

        char[] letters = new char[totalLetters];
        char[] lettersToCompare = new char[totalLetters];

        for(int i = 0; i < lettersOfTyped.length; i++){
            lettersToCompare[i] = lettersOfTyped[i];
            letters[i] = lettersOfText[i];
        }
        
        double correctGuesses = level != -1 ? correctingTexts(totalLetters, lettersOfText, level)
                                            : correctingTexts(totalLetters, letters, lettersToCompare); 
        
        double accuracyPercentage = (correctGuesses / totalLetters) * 100;
        wrongsTyped += "\u001B[0m";
        Tournament.wrongsTypedOpponent += "\u001B[0m";

        return accuracyPercentage;
    }
    
    public static double correctingTexts(int totalLetters, char[] lettersToCompare, int levelOpponent){
        return correctingTexts(totalLetters, null, lettersToCompare, levelOpponent);
    }
    
    
    public static double correctingTexts(int totalLetters, char[] letters, char[] lettersToCompare){
        return correctingTexts(totalLetters, letters, lettersToCompare, -1);
    }
    
    public static double correctingTexts(int totalLetters, char[] letters, char[] lettersToCompare, int levelOpponent){
        double correctGuesses = 0;

        for(int x = 0; x < totalLetters; x++) {
            if(levelOpponent == -1){
                if(lettersToCompare[x] == letters[x]){
                    correctGuesses++;
                    wrongsTyped += "\u001B[32m" + lettersToCompare[x];
                } else if(x > totalLetters){
                    wrongsTyped += "\u001B[31m" + lettersToCompare[x];
                } else {
                    wrongsTyped += "\u001B[31m" + letters[x];
                } 
            } else {
                double resp = (random.nextInt(0, 100) / totalLetters ) * 100;

                if(resp >= x){
                    correctGuesses++;
                    Tournament.wrongsTypedOpponent += "\u001B[32m" + lettersToCompare[x];
                } else {
                    Tournament.wrongsTypedOpponent += "\u001B[31m" + lettersToCompare[x];
                }
            }
       }

       return correctGuesses;
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
        BigDecimal bd = new BigDecimal(points).setScale(3, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
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