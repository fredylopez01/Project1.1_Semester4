package co.edu.uptc.project11.utils;

import co.edu.uptc.SimpleUptcList.services.SimpleUptcList;

public class MyArrayUtils {
    
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

    public static SimpleUptcList<String> removeElementsRepeat(SimpleUptcList<String> list){
        SimpleUptcList<String> newList = new SimpleUptcList<>();
        boolean isAdd = false;
        newList.add(list.get(0));
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < newList.size(); j++) {
                if(list.get(i).equalsIgnoreCase(newList.get(j))){
                    isAdd = true;
                }
            }
            if(!isAdd){
                newList.add(list.get(i));
            }
            isAdd = false;
        }
        return newList;
    }
}
