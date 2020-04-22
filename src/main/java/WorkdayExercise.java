import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/* TODO */
//Upgrade JAVA version
public class WorkdayExercise {

    private GregorianCalendar startWorkday;
    private GregorianCalendar endWorkday;
    private float workdayMsDuration;

    WorkdayExercise() {}

    /* TODO */
    // Icororate checks for stored holidays
    // TIPS: Use time/date.com
    private Boolean isWorkDay(int dayOfWeek) {

        if (dayOfWeek == 1 || dayOfWeek == 7){
            return false;
        } else {
            return true;
        }
    }

    private Boolean clockTimeIsAfter(Calendar date1, Calendar date2){
        if (date1.get(Calendar.HOUR_OF_DAY) > date2.get(Calendar.HOUR_OF_DAY)){
            return true;
        } else if (date1.get(Calendar.HOUR_OF_DAY) == date2.get(Calendar.HOUR_OF_DAY) && date1.get(Calendar.MINUTE) > date1.get(Calendar.MINUTE)){
            return true;
        } else {
            return false;
        }
    }

    private float clockTimeMsDiff(Calendar date1, Calendar date2){
        Calendar tempDate =  (Calendar)date1.clone();
        tempDate.set(Calendar.HOUR_OF_DAY, date2.get(Calendar.HOUR_OF_DAY));
        tempDate.set(Calendar.MINUTE, date2.get(Calendar.MINUTE));

        return date1.getTimeInMillis() - tempDate.getTimeInMillis();
    }

    public void setWorkdayStartAndStop(GregorianCalendar startWorkday, GregorianCalendar endWorkday){
        this.startWorkday = startWorkday;
        this.endWorkday = endWorkday;
        this.workdayMsDuration = endWorkday.getTimeInMillis() - startWorkday.getTimeInMillis();
    }

    /* TODO */
    // Function to handle / store holidays

    public Date getWorkdayIncrement(Date startDate, float incrementWorkdays){

        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.setTime(startDate);

        int dayOfWeek = startDateCalendar.get(Calendar.DAY_OF_WEEK);
        int workdayIterations = Math.abs((int)incrementWorkdays);
        int dateDaysChange = 0;
        int incOrDec;
        float incrementWorkMs = (incrementWorkdays % 1) * workdayMsDuration;

        if (incrementWorkdays > 0){
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
        /* TODO */
        // Compress logic
        if (incOrDec == 1){

            // Startdate clock is before the start of the workday
            if (clockTimeIsAfter(startWorkday,startDateCalendar)){
                startDateCalendar.set(Calendar.HOUR_OF_DAY, startWorkday.get(Calendar.HOUR_OF_DAY));
                startDateCalendar.set(Calendar.MINUTE, startWorkday.get(Calendar.MINUTE));
                startDateCalendar.add(Calendar.MILLISECOND, (int)(incrementWorkMs));
            // Startdate clock is after the end of workday
            } else if (clockTimeIsAfter(startDateCalendar, endWorkday)){
                startDateCalendar.set(Calendar.HOUR_OF_DAY, startWorkday.get(Calendar.HOUR_OF_DAY));
                startDateCalendar.set(Calendar.MINUTE, startWorkday.get(Calendar.MINUTE));
                startDateCalendar.add(Calendar.DATE, 1);
                startDateCalendar.add(Calendar.MILLISECOND, (int)(incrementWorkMs));
            // Startday clock is in the workday
            } else {
                startDateCalendar.set(Calendar.HOUR_OF_DAY, startDateCalendar.get(Calendar.HOUR_OF_DAY));
                startDateCalendar.set(Calendar.MINUTE, startDateCalendar.get(Calendar.MINUTE));

                // The increment will NOT stretch to the next workday
                if (incrementWorkMs <= clockTimeMsDiff(endWorkday, startDateCalendar)){
                    startDateCalendar.add(Calendar.MILLISECOND, (int)(incrementWorkMs));
                // The increment will stretch to the next workday
                } else {
                    startDateCalendar.add(Calendar.DATE, 1);
                    startDateCalendar.add(Calendar.MILLISECOND, (int)(incrementWorkMs - clockTimeMsDiff(endWorkday, startDateCalendar)));
                }
            }

        } else
            // Startdate clock is after the end of workday
            if (clockTimeIsAfter(startDateCalendar, endWorkday)){
                startDateCalendar.set(Calendar.HOUR_OF_DAY, endWorkday.get(Calendar.HOUR_OF_DAY));
                startDateCalendar.set(Calendar.MINUTE, endWorkday.get(Calendar.MINUTE));
                startDateCalendar.add(Calendar.MILLISECOND, (int)(incrementWorkMs));
            // Startdate clock is before the datrt of workday
            } else if (clockTimeIsAfter(startWorkday, startDateCalendar)){
                startDateCalendar.set(Calendar.HOUR_OF_DAY, endWorkday.get(Calendar.HOUR_OF_DAY));
                startDateCalendar.set(Calendar.MINUTE, endWorkday.get(Calendar.MINUTE));
                startDateCalendar.add(Calendar.DATE, -1);
                startDateCalendar.add(Calendar.MILLISECOND, (int)(incrementWorkMs));
                // Startday clock is in the workday
            } else {
                startDateCalendar.set(Calendar.HOUR_OF_DAY, startDateCalendar.get(Calendar.HOUR_OF_DAY));
                startDateCalendar.set(Calendar.MINUTE, startDateCalendar.get(Calendar.MINUTE));

                // The increment will NOT stretch to the previous workday
                if (incrementWorkMs <= clockTimeMsDiff(startWorkday, startDateCalendar)){
                    startDateCalendar.add(Calendar.MILLISECOND, (int)(incrementWorkMs));
                    // The increment will stretch to the previous workday
                } else {
                    startDateCalendar.add(Calendar.DATE, -1);
                    startDateCalendar.add(Calendar.MILLISECOND, (int)(incrementWorkMs - clockTimeMsDiff(endWorkday, startDateCalendar)));
                }
            }

        startDateCalendar.add(Calendar.DATE, dateDaysChange);
        return startDateCalendar.getTime();
    }
}
