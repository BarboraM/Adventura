package logika;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class InventarTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class InventarTest
{
        private Hra hra1;
    /**
     * Default constructor for test class InventarTest
     */
    public InventarTest()
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
        hra1 = new Hra();
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
    public void testFunkceInventare()
    {
        
        Inventar inventar = new Inventar(hra1.getHerniPlan());
        Vec vec1 = new Vec("1", true, false);
        Vec vec2 = new Vec("2", false, false);
        Vec vec3 = new Vec("3", true, false);
        Vec vec4 = new Vec("4", true, false);
        Vec vec5 = new Vec("5", true, false);
        Vec vec6 = new Vec("6", true, false);
        Vec vec7 = new Vec("7", true, false);
        Vec vec8 = new Vec("8", true, false);
        Vec vec9 = new Vec("9", true, false);
        Vec vec10 = new Vec("10", true, false);
        Vec vec11 = new Vec("11", true, false);
        Vec vec12 = new Vec("12", true, false);
        
        assertEquals(true, inventar.vlozVec(vec1));
        assertEquals(false, inventar.vlozVec(vec2));
        assertEquals(true, inventar.vlozVec(vec3));
        assertEquals(true, inventar.vlozVec(vec4));
        assertEquals(true, inventar.vlozVec(vec5));
        assertEquals(true, inventar.vlozVec(vec6));
        assertEquals(true, inventar.vlozVec(vec7));
        assertEquals(true, inventar.vlozVec(vec8));
        assertEquals(true, inventar.vlozVec(vec9));
        assertEquals(true, inventar.vlozVec(vec10));
        assertEquals(true, inventar.vlozVec(vec11));
        assertEquals(false, inventar.vlozVec(vec12));
        
        assertEquals(false, inventar.jeMistoVInventari());
        assertEquals(null, inventar.odeberVec("2"));
        assertEquals(vec1, inventar.odeberVec("1"));
        assertEquals(true, inventar.jeMistoVInventari());
        assertEquals(true, inventar.jeVInventari("3"));
         assertEquals(false, inventar.jeVInventari("1"));
    }
    
    
}
