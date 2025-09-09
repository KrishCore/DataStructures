import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.time.*;
import java.util.TimeZone;

public class AccessedOn
{
    public static void main(String[] args)
    {
        try {
            File file = new File("AccessedOn.txt");
            if (!file.exists())
                file.createNewFile();
            FileWriter fileWriter = new FileWriter(file,true);
            PrintWriter printWriter = new PrintWriter(fileWriter);


            int year = LocalDate.now().getYear();
            Month month = LocalDate.now().getMonth();
            DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
            int day = LocalDate.now().getDayOfMonth();

            int hour = LocalTime.now().getHour();
            int min = LocalTime.now().getMinute();
            int sec = LocalTime.now().getSecond();

            TimeZone zone = TimeZone.getDefault();
            String zoneString = zone.getDisplayName();
//            System.out.println(zoneString);
            String zu = "";
            for (int i = 0; i < zoneString.length(); i++) {
                if (Character.isUpperCase(zoneString.charAt(i)))
                    zu += zoneString.charAt(i);
            }
            String dow = dayOfWeek.toString().charAt(0)+dayOfWeek.toString().substring(1,3).toLowerCase();
            String mon = month.toString().charAt(0)+month.toString().substring(1,3).toLowerCase();
            String print = String.format("%s %s %d %02d:%02d:%02d %s %d", dow, mon, day, hour, min, sec, zu , year);
            printWriter.println(print);
            System.out.printf("%s %s %d %02d:%02d:%02d %s %d%n", dow, mon, day, hour, min, sec, zu , year);

            //dayOfWeek+" "+month+" "+day1+" "+year+" "+hour+":"+min+":"+sec+"  ");//+ zone);
//            printWriter.println(date + " ___ " + time);

            printWriter.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
