import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class WorkdayExercise {

    private GregorianCalendar startWorkday;
    private GregorianCalendar endWorkday;
    private float workdayMsDuration;

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
        this.workdayMsDuration = (endWorkday.getTimeInMillis() - startWorkday.getTimeInMillis());
    }

    public Date getWorkdayIncrement(Date startDate, float incrementWorkdays){

        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.setTime(startDate);
        int dayOfWeek = startDateCalendar.get(Calendar.DAY_OF_WEEK);

        int workdayIterations = Math.abs((int)incrementWorkdays);
        float incrementWorkMs = (incrementWorkdays % 1);

        int dateDaysChange = 0;

        int incOrDec;

        if (incrementWorkdays >= 0){
            incOrDec = 1;
        } else {
            incOrDec = -1;
        }

        while(workdayIterations != 0) {

            if (isWorkDay(dayOfWeek)) {
                workdayIterations -= 1;
            }

            if ((dayOfWeek += 1) == 8){
                dayOfWeek = 1;
            } else if ((dayOfWeek -= 1) == 0) {
                dayOfWeek = 7;
            } else {
                dayOfWeek += incOrDec;
            }

            dateDaysChange += incOrDec;

            if (workdayIterations == 0) {
                break;
            }
        }

        startDateCalendar.add(Calendar.DATE, dateDaysChange);

        return new Date();
    }
}
