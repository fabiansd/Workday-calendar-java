import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class WorkdayExercise {

    private GregorianCalendar startWorkday;
    private GregorianCalendar endWorkday;
    private float workdayMinDuration;

    WorkdayExercise() {}

    private Boolean isWorkDay(int dayOfWeek) {

        if (dayOfWeek == 1 || dayOfWeek == 7){
            return false;
        } else {
            return true;
        }
    }

    public void setWorkdayStartAndStop(GregorianCalendar startWorkday, GregorianCalendar endWorkday){
        this.startWorkday = startWorkday;
        this.endWorkday = endWorkday;
        this.workdayMinDuration = (int)((endWorkday.getTimeInMillis() - startWorkday.getTimeInMillis())/60000);
    }

    public Date getWorkdayIncrement(Date startDate, float incrementWorkdays){

        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.setTime(startDate);
        int dayOfWeek = startDateCalendar.get(Calendar.DAY_OF_WEEK);

        int workdayIterations = Math.abs((int)incrementWorkdays);
        float incrementWorkMin = (incrementWorkdays % 1);

        int dateDaysChange = 0;

        int incOrDec;

        if (incrementWorkdays >= 0){
            incOrDec = 1;
        } else {
            incOrDec = -1;
        }

        while(workdayIterations >= 0) {

            if (isWorkDay(dayOfWeek)) {
                workdayIterations -= 1;
            }

            if (dayOfWeek == 7 && incOrDec > 0){
                dayOfWeek = 1;
            } else if (dayOfWeek == 1 && incOrDec < 0){
                dayOfWeek = 7;
            } else {
                dayOfWeek += incOrDec;
            }

            dateDaysChange += incOrDec;

            if (workdayIterations == 0) {
                break;
            }
        }

        if (incOrDec > 0 ){
            startDateCalendar.set(Calendar.HOUR, startWorkday.get(Calendar.HOUR));
            startDateCalendar.set(Calendar.MINUTE, startWorkday.get(Calendar.MINUTE));

        } else {
            startDateCalendar.set(Calendar.HOUR, endWorkday.get(Calendar.HOUR));
            startDateCalendar.set(Calendar.MINUTE, endWorkday.get(Calendar.MINUTE));
        }

        int minutes = (int)(workdayMinDuration * incrementWorkMin);

        startDateCalendar.add(Calendar.MINUTE, minutes);
        startDateCalendar.add(Calendar.DATE, dateDaysChange);

        return startDateCalendar.getTime();
    }
}
