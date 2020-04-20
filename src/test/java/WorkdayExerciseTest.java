import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorkdayExerciseTest {

    private WorkdayExercise workdayExercise = new WorkdayExercise();

    private GregorianCalendar startDate =  new GregorianCalendar(2004, Calendar.JANUARY, 1, 8, 0);
    private GregorianCalendar endDate =  new GregorianCalendar(2004, Calendar.JANUARY, 1, 16, 0);

    private Date incrementDate1 = new Date(2004, Calendar.MAY, 24, 18, 5);
    private float increment1 = -5.5f;

    private float incrementPos = 8.27f;

    @Test
    public void testDateReturnNegative(){
        workdayExercise.setWorkdayStartAndStop(startDate, endDate);
        Date resultDate = workdayExercise.getWorkdayIncrement(incrementDate1, increment1);
    }

    @Test
    public void testDateReturnPosInc(){
        workdayExercise.setWorkdayStartAndStop(startDate, endDate);
        Date resultDate = workdayExercise.getWorkdayIncrement(incrementDate1, incrementPos);
    }
}