package sets;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

/**
 * FiniteSetTest is a glassbox test of the FiniteSet class.
 */
public class FiniteSetTest {

  /** Test creating sets. */
  @Test
  public void testCreation() {
    assertEquals(Arrays.asList(),
        FiniteSet.of(new float[0]).getPoints());         // empty
    assertEquals(Arrays.asList(1f),
        FiniteSet.of(new float[] {1}).getPoints());      // one item
    assertEquals(Arrays.asList(1f, 2f),
        FiniteSet.of(new float[] {1, 2}).getPoints());   // two items
    assertEquals(Arrays.asList(1f, 2f),
        FiniteSet.of(new float[] {2, 1}).getPoints());   // two out-of-order
    assertEquals(Arrays.asList(-2f, 2f),
        FiniteSet.of(new float[] {2, -2}).getPoints());  // negative
  }

  // Some example sets used by the tests below.
  private static FiniteSet S0 = FiniteSet.of(new float[0]);
  private static FiniteSet S1 = FiniteSet.of(new float[] {1});
  private static FiniteSet S12 = FiniteSet.of(new float[] {1, 2});

  /** Test set equality. */
  @Test
  public void testEquals() {
    assertTrue(S0.equals(S0));
    assertFalse(S0.equals(S1));
    assertFalse(S0.equals(S12));

    assertFalse(S1.equals(S0));
    assertTrue(S1.equals(S1));
    assertFalse(S1.equals(S12));

    assertFalse(S12.equals(S0));
    assertFalse(S12.equals(S1));
    assertTrue(S12.equals(S12));
  }

  /** Test set size. */
  @Test
  public void testSize() {
    assertEquals(S0.size(), 0);
    assertEquals(S1.size(), 1);
    assertEquals(S12.size(), 2);
  }

  private static FiniteSet T1 = FiniteSet.of(new float[] {2, 3, 4});
  private static FiniteSet T2 = FiniteSet.of(new float[] {3, 4, 5});
  private static FiniteSet T3 = FiniteSet.of(new float[] {2, 3, 4, 5});
  private static FiniteSet T4 = FiniteSet.of(new float[] {3, 4});
  private static FiniteSet T5 = FiniteSet.of(new float[] {5, 6});
  private static FiniteSet T6 = FiniteSet.of(new float[] {3, 4, 5, 6});
  private static FiniteSet T7 = FiniteSet.of(new float[] {2});
  // TODO: Feel free to initialize (private static) FiniteSet objects here
  //       if you plan to use them for the tests below.

  /** Tests forming the union of two finite sets. */
  @Test
  public void testUnion() {
    assertTrue(S0.union(S0).equals(S0));
    assertTrue(S1.union(S1).equals(S1));
    assertTrue(S0.union(S1).equals(S1));
    assertTrue(S1.union(S12).equals(S12));
    assertTrue(S1.union(T7).equals(S12));
    assertFalse(S0.union(S1).equals(S12));

    assertTrue(T1.union(T2).equals(T3));
    assertTrue(T4.union(T5).equals(T6));
    assertFalse(T1.union(T2).equals(T4));
  }

  /** Tests forming the intersection of two finite sets. */
  @Test
  public void testIntersection() {
    assertTrue(S0.intersection(S0).equals(S0));
    assertTrue(S1.intersection(S1).equals(S1));
    assertFalse(S0.intersection(S1).equals(S12));

    assertTrue(T1.intersection(T2).equals(T4));
    assertTrue(T4.intersection(T5).equals(S0));
    assertTrue(T3.intersection(T2).equals(T2));
    assertFalse(T1.intersection(T2).equals(T3));
    assertFalse(T2.intersection(T3).equals(T4));
  }

  /** Tests forming the difference of two finite sets. */
  @Test
  public void testDifference() {
    assertTrue(S1.difference(S1).equals(S0));
    assertTrue(S0.difference(S0).equals(S0));

    assertTrue(T6.difference(T5).equals(T4));
    assertTrue(T1.difference(T2).equals(T7));
    assertTrue(T4.difference(T5).equals(T4));
    assertFalse(T5.difference(T4).equals(T4));
    assertFalse(T4.difference(T5).equals(T6));
  }

}
