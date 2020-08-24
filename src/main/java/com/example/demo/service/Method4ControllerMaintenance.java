package com.example.demo.service;

import com.example.demo.entity.Maintenance;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class Method4ControllerMaintenance {

    //dve metode, druga je bitna(zamisljeno da vraca String "home" ili "maintenance" kako bi slao na neku od te dve stranice ako je vreme za maintenance
    // prva se poziva u drugoj, ne koristim ih, koristim donje dve modifikovane da vracaju boolean radi provere da li je vreme za maintenance ili ne
    public String switchDays(String hourRangeForDay, int currentHour, String answer){
        String stringStartingHour = hourRangeForDay.substring(0,2);
        int startingHour = Integer.parseInt(stringStartingHour);
        String stringEndHour = hourRangeForDay.substring(6,8);
        int endHour = Integer.parseInt(stringEndHour);
        //System.out.println("St " + stringStartingHour + " end   " + stringEndHour + "   current  " + currentHour);

        if(currentHour >= startingHour && currentHour <= endHour){
            answer = "maintenanceInfoPage";
        }else{
            answer = "home";
        }

        return answer;
    }

    public String maintenanceOrNot(List<Maintenance> listOfDaysForMaintenance){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        Date date=calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = dateFormat.format(date);

        int currentHour = Integer.parseInt(formattedDate.substring(0,2));

        String answer = "";

        switch (day) {
            case Calendar.MONDAY:
                String mondayHour = listOfDaysForMaintenance.get(0).getHours();
                answer = switchDays(mondayHour, currentHour, answer);
                break;
            case Calendar.TUESDAY:
                String tuesdayHour = listOfDaysForMaintenance.get(1).getHours();
                answer = switchDays(tuesdayHour, currentHour, answer);
                break;
            case Calendar.WEDNESDAY:
                String wednesdayHour = listOfDaysForMaintenance.get(2).getHours();
                answer = switchDays(wednesdayHour, currentHour, answer);
                break;
            case Calendar.THURSDAY:
                String thursdayHour = listOfDaysForMaintenance.get(3).getHours();
                answer = switchDays(thursdayHour, currentHour, answer);
                break;
            case Calendar.FRIDAY:
                String fridayHour = listOfDaysForMaintenance.get(4).getHours();
                answer = switchDays(fridayHour, currentHour, answer);
                break;
            default:
                answer = "home";
                System.out.println("Usao u default, sajt radi jer je vrv vikend i answer je " + answer);
        }
        return answer;
    }








    //ova metoda se koristi u drugoj, skuplja podatke iz baze i gleda da li je vreme za maintenance, a posle toga se poziva u drugoj metodi "maintenanceOrNotBoolean"
    public boolean switchDaysBoolean(String hourRangeForDay, int currentHour, boolean answerBoolean){
        String stringStartingHour = hourRangeForDay.substring(0,2);
        int startingHour = Integer.parseInt(stringStartingHour);
        String stringEndHour = hourRangeForDay.substring(6,8);
        int endHour = Integer.parseInt(stringEndHour);
        //System.out.println("St " + stringStartingHour + " end   " + stringEndHour + "   current  " + currentHour);

        if(currentHour >= startingHour && currentHour <= endHour){
            answerBoolean = true;
        }else{
            answerBoolean = false;
        }

        return answerBoolean;
    }
    //glavna metoda koja proverava celu pricu da li je vreme za maintenance ili ne, poziva se u klasi "MaintenanceAspect" gde ce presecati
    //metodu kontrolera "showHome()" i ako je vreme za maintenance baca na stranicu "maintenanceInfoPage" ako nije nastavlja se sa telom metode
    //kontrolera a tamo je po default-u fiksirano da baca na "home"
    public boolean maintenanceOrNotBoolean(List<Maintenance> listOfDaysForMaintenance){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        Date date=calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = dateFormat.format(date);

        int currentHour = Integer.parseInt(formattedDate.substring(0,2));

        boolean answer = false;

        switch (day) {
            case Calendar.MONDAY:
                String mondayHour = listOfDaysForMaintenance.get(0).getHours();
                answer = switchDaysBoolean(mondayHour, currentHour, answer);
                break;
            case Calendar.TUESDAY:
                String tuesdayHour = listOfDaysForMaintenance.get(1).getHours();
                answer = switchDaysBoolean(tuesdayHour, currentHour, answer);
                break;
            case Calendar.WEDNESDAY:
                String wednesdayHour = listOfDaysForMaintenance.get(2).getHours();
                answer = switchDaysBoolean(wednesdayHour, currentHour, answer);
                break;
            case Calendar.THURSDAY:
                String thursdayHour = listOfDaysForMaintenance.get(3).getHours();
                answer = switchDaysBoolean(thursdayHour, currentHour, answer);
                break;
            case Calendar.FRIDAY:
                String fridayHour = listOfDaysForMaintenance.get(4).getHours();
                answer = switchDaysBoolean(fridayHour, currentHour, answer);
                break;
            default:
                answer = false;
                System.out.println("Usao u default, sajt radi jer je vrv vikend i answer je " + answer);
        }
        return answer;
    }

}
