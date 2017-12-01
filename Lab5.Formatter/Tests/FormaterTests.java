import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FormaterTests {

    @Test
    public void nullString(){
        Assertions.assertEquals(Formatter.build(null), null);
    }

    @Test
    public void numberOfArguments(){
        Assertions.assertEquals(Formatter.build("{0}  Zdarova {1}", 34), null);
        Assertions.assertEquals(Formatter.build("Pls {0} me", "yo", 46, "rrew"), "Pls yo me");
        Assertions.assertEquals(Formatter.build("oh my {0} that was {1}", 15, 463), "oh my 15 that was 463");
    }

    @Test
    public void positionsOfArguments() {
        Assertions.assertEquals(Formatter.build("oh my {0} that was {2}", 15, 463, 2), "oh my 15 that was 2");
        Assertions.assertEquals(Formatter.build("oh my {2} that was {1}", 15, 463, 2), "oh my 2 that was 463");
    }

    @Test
    public void correct(){
        Assertions.assertEquals(Formatter.build("Oh, hi {0}. How are you, {1}?","Mark","dude"),"Oh, hi Mark. How are you, dude?" );
    }

}
