package co.edu.uptc.project11.utils;

public class CompareArray {
    
    public static boolean isEqualsSchedule(String[] scheduleOne, String[] scheduleTwo){
        boolean isEquals = false;
        int times = 0;
        for (int i = 0; i < scheduleOne.length; i++) {
            for (int j = 0; j < scheduleTwo.length; j++) {
                if(scheduleOne[i].equals(scheduleTwo[j])){
                    times++;
                }
            }
        }
        if (times==scheduleOne.length) {
            isEquals = true;
        }
        return isEquals;
    }
}
