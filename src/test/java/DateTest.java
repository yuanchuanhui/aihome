import org.junit.Test;

import java.util.Calendar;

public class DateTest {

    @Test
    public void testDayOfYear(){
        System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
    }

}

