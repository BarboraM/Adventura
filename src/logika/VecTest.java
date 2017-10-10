package logika;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class VecTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class VecTest
{
    /**
     * Default constructor for test class VecTest
     */
    public VecTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testFunkceVeci()
    {
        Vec vec1 = new Vec("1", true, true);
        Vec vec2 = new Vec("2", false, false);
        
        assertEquals(true, vec1.jePrenositelna());
        assertEquals(true, vec1.jeProzkoumatelna());
        assertEquals(false, vec2.jePrenositelna());
        assertEquals(false, vec2.jeProzkoumatelna());
    }
}
