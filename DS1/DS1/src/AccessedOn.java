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
            File file = new File("src\\AccessedOn.txt");
            if (!file.exists())
                file.createNewFile();
            FileWriter fileWriter = new FileWriter(file,true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            int year = LocalDate.now().getYear();
            Month month = LocalDate.now().getMonth();
            DayOfWeek day = LocalDate.now().getDayOfWeek();
//            int day = LocalDate.now().getDayOfMonth();
            int day1 = LocalDate.now().getDayOfMonth();
            int hour = LocalTime.now().getHour();
            int min = LocalTime.now().getMinute();
            int sec = LocalTime.now().getSecond();
            TimeZone zone = TimeZone.getDefault();
            printWriter.println(day + " " + month + " " + day1 + " " + year + " " + hour + ":" + min + ":" + sec + "  " + zone);


            fileWriter.close();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
