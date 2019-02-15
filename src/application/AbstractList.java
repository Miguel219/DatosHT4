package application;
import java.util.List;
/**
 * Silvio Orozco 18282
 * Jose Castaneda 18161
 */
public abstract class AbstractList<E> implements List<E>
{
   public AbstractList()
   // post: does nothing
   {
   }

   public boolean isEmpty()
   // post: returns true iff list has no elements
   {
      return size() == 0;
   }
  
   public abstract void addLast(E value);
   
   public boolean contain(E value)
  // pre: value is not null
  // post: returns true iff list contains an object equal to value
  {
	return -1 != indexOf(value);
  }
}