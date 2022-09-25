import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SayTheNumberTest {

    @Test
    public void convertToWordTest(){
        SayTheNumber test = new SayTheNumber();

        assertEquals("ninety nine", SayTheNumber.convertToWords(99));
        assertEquals("eight", SayTheNumber.convertToWords(8));
        assertEquals("nine million nine hundred ninety nine thousand nine hundred ninety nine",
                SayTheNumber.convertToWords(9999999));
    }
}