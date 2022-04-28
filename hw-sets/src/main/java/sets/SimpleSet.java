package sets;

import java.util.Arrays;
import java.util.List;

/**
 * Represents an immutable set of points on the real line that is easy to
 * describe, either because it is a finite set, e.g., {p1, p2, ..., pN}, or
 * because it excludes only a finite set, e.g., R \ {p1, p2, ..., pN}. As with
 * FiniteSet, each point is represented by a Java float with a non-infinite,
 * non-NaN value.
 */
public class SimpleSet {
  //Points are to be stored in a FiniteSet object that could represent
  //the set, or be used to easily find the complement of the set. A boolean
  //represents whether or not the set is a complement of a finite set.
  // TODO: fill in and document the representation
  //       Make sure to include the representation invariant (RI)
  //       and the abstraction function (AF).
  //RI: points != null && points.getPoints(0)...points.getPoints(n - 1) != (-infty, +infty, or NaN)
  //n = points.size()
  //AF(this):
  //if(complement), then set is R \ {points.getPoints}
  //if(!complement), then set is {points.getPoints}
  private FiniteSet points;
  private boolean complement;
  /**
   * Creates a simple set containing only the given points.
   * @param vals Array containing the points to make into a SimpleSet
   * @spec.requires points != null and has no NaNs, no infinities, and no dups
   * @spec.effects this = {vals[0], vals[1], ..., vals[vals.length-1]}
   */
  public SimpleSet(float[] vals) {
    // TODO: implement this
    this.points = FiniteSet.of(vals);
    this.complement = false;
  }

  /**
   * Private constructor that directly fills in the fields above.
   * @param complement Whether this = points or this = R \ points
   * @param points List of points that are in the set or not in the set
   * @spec.requires points != null
   * @spec.effects this = R \ points if complement else points
   */
  private SimpleSet(boolean complement, FiniteSet points) {
    // TODO: implement this
    if(complement){
      this.complement = true;
    }else{
      this.complement = false;
    }
    this.points = points;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SimpleSet))
      return false;

    SimpleSet other = (SimpleSet) o;
    if(this.complement == other.complement) {
      //If two sets contain the same complement value,
      //the points stored in them could determine their equality
      return this.points.equals(other.points);
    }else{
      //A complement set can never be equal with
      //a non-complement set
      return false;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * Returns the number of points in this set.
   * @return N      if this = {p1, p2, ..., pN} and
   *         infty  if this = R \ {p1, p2, ..., pN}
   */
  public float size() {
    // TODO: implement this
    if(complement){
      return Float.POSITIVE_INFINITY;
    }else{
      return this.points.size();
    }
  }

  /**
   * Returns a string describing the points included in this set.
   * @return the string "R" if this contains every point,
   *     a string of the form "R \ {p1, p2, .., pN}" if this contains all
   *        but {@literal N > 0} points, or
   *     a string of the form "{p1, p2, .., pN}" if this contains
   *        {@literal N >= 0} points,
   *     where p1, p2, ... pN are replaced by the individual numbers.
   */
  public String toString() {
    // TODO: implement this with a loop. document its invariant
    //       a StringBuilder may be useful for creating the string
    //Case for empty set or complement of empty set
    if(this.points.size() == 0){
      if(this.complement){
        return "R";
      }else{
        return "{}";
      }
    }
    StringBuilder b = new StringBuilder();
    int i = 0;
    while(i < this.points.size() - 1){
      b.append(this.points.getPoints().get(i));
      b.append(", ");
      i++;
    }
    b.append(this.points.getPoints().get(this.points.size() - 1));
    if(this.complement) {
      return "R \\ {" + b.toString() + "}";
    }else{
      return "{" + b.toString() + "}";
    }
  }

  /**
   * Returns a set representing the points R \ this.
   * @return R \ this
   */
  public SimpleSet complement() {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct (hint: cases)
    //Creates SimpleSet object with the same set of points, but reverses
    //complement. This will affect how other methods handle the new SimpleSet.
    if(this.complement){
      return new SimpleSet(false, this.points);
    }else{
      return new SimpleSet(true, this.points);
    }
  }

  /**
   * Returns the union of this and other.
   * @param other Set to union with this one.
   * @spec.requires other != null
   * @return this union other
   */
  public SimpleSet union(SimpleSet other) {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct (hint: cases)
    if(!this.complement && !other.complement) {
      //When both sets are not complements, return a non-complement set
      //containing the union of points from both sets.
      return new SimpleSet(false, this.points.union(other.points));
    }else if(this.complement && other.complement){
      //When both sets are complements, return a complement set
      //EXCLUDING the intersection of points EXCLUDED from both sets.
      return new SimpleSet(true, this.points.intersection(other.points));
    }else if(!this.complement && other.complement){
      //When this is not a complement set but other is, return a complement set
      //EXCLUDING the points EXCLUDED from other and also NOT in this.
      return new SimpleSet(true, other.points.difference(this.points));
    }else {
      //When other is not a complement set but this is, return a complement set
      //EXCLUDING the points EXCLUDED from this and also NOT in other.
      return new SimpleSet(true, this.points.difference(other.points));
    }
  }

  /**
   * Returns the intersection of this and other.
   * @param other Set to intersect with this one.
   * @spec.requires other != null
   * @return this intersect other
   */
  public SimpleSet intersection(SimpleSet other) {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct
    // NOTE: There is more than one correct way to implement this.
    if(!this.complement && !other.complement) {
      //When both sets are not complements, return a non-complement set
      //containing the intersection of points from both sets.
      return new SimpleSet(false, this.points.intersection(other.points));
    }else if(this.complement && other.complement){
      //When both sets are complements, return a complement set
      //EXCLUDING the union of points EXCLUDED from both sets.
      return new SimpleSet(true, this.points.union(other.points));
    }else if(!this.complement && other.complement){
      //When this is not a complement set but other is, return a non-complement set
      //containing only the points in this that are also NOT EXCLUDED from other.
      return new SimpleSet(false, this.points.difference(other.points));
    }else {
      //When other is not a complement set but this is, return a non-complement set
      //containing only the points in other that are also NOT EXCLUDED from this.
      return new SimpleSet(false, other.points.difference(this.points));
    }
  }

  /**
   * Returns the difference of this and other.
   * @param other Set to difference from this one.
   * @spec.requires other != null
   * @return this minus other
   */
  public SimpleSet difference(SimpleSet other) {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct
    // NOTE: There is more than one correct way to implement this.
    if(!this.complement && !other.complement) {
      //When both sets are not complements, return a non-complement set
      //containing the set of points in this but not other.
      return new SimpleSet(false, this.points.difference(other.points));
    }else if(this.complement && other.complement){
      //When both sets are complements, return a non-complement set
      //containing the set of points EXCLUDED from other but not from this.
      return new SimpleSet(false, other.points.difference(this.points));
    }else if(!this.complement && other.complement){
      //When this is not a complement but other is, return a non-complement set
      //containing the intersection of the set of points in this and EXCLUDED from other.
      return new SimpleSet(false, this.points.intersection(other.points));
    }else {
      //When other is not a complement but this is, return a complement set
      //EXCLUDING the union of the points EXCLUDED from this and contained in other.
      return new SimpleSet(true, this.points.union(other.points));
    }
  }

}
