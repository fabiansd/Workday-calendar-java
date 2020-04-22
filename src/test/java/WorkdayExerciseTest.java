import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class WorkdayExerciseTest {

    private WorkdayExercise workdayExercise = new WorkdayExercise();

    private GregorianCalendar startDate =  new GregorianCalendar(2004, Calendar.JANUARY, 1, 8, 0);
    private GregorianCalendar endDate =  new GregorianCalendar(2004, Calendar.JANUARY, 1, 16, 0);

    @Test
    public void testDate1(){
        Date incrementDate = new Date(2004, Calendar.MAY, 24, 18, 5);
        float inc = -5.5f;

        workdayExercise.setWorkdayStartAndStop(startDate, endDate);
        Date resultDate = workdayExercise.getWorkdayIncrement(incrementDate, inc);

        System.out.println("Time " + resultDate);
    }

    @Test
    public void testDate2(){
        Date incrementDate = new Date(2004, Calendar.MAY, 24, 19, 3);
        float inc = 44.723656f;

        workdayExercise.setWorkdayStartAndStop(startDate, endDate);
        Date resultDate = workdayExercise.getWorkdayIncrement(incrementDate, inc);

        System.out.println("Time " + resultDate);
    }

    @Test
    public void testDate3(){
        Date incrementDate = new Date(2004, Calendar.MAY, 24, 18, 3);
        float inc = -6.7470217f;

        workdayExercise.setWorkdayStartAndStop(startDate, endDate);
        Date resultDate = workdayExercise.getWorkdayIncrement(incrementDate, inc);

        System.out.println("Time " + resultDate);
    }

    @Test
    public void testDate4(){
        Date incrementDate = new Date(2004, Calendar.MAY, 24, 8, 3);
        float inc = 12.782709f;

        workdayExercise.setWorkdayStartAndStop(startDate, endDate);
        Date resultDate = workdayExercise.getWorkdayIncrement(incrementDate, inc);

        System.out.println("Time " + resultDate);
    }

    @Test
    public void testDate5(){
        Date incrementDate = new Date(2004, Calendar.MAY, 24, 7, 3);
        float inc = 8.276628f;

        workdayExercise.setWorkdayStartAndStop(startDate, endDate);
        Date resultDate = workdayExercise.getWorkdayIncrement(incrementDate, inc);

        System.out.println("Time " + resultDate);
    }
}