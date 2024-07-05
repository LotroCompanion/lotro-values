package delta.games.lotro.values;

import java.util.ArrayList;
import java.util.List;

/**
 * An array value.
 * @author DAL
 */
public class ArrayValue
{
  private List<Object> _values;

  /**
   * Constructor.
   */
  public ArrayValue()
  {
    _values=new ArrayList<Object>();
  }

  /**
   * Get the array size.
   * @return A size.
   */
  public int getSize()
  {
    return _values.size();
  }

  /**
   * Get the value at the given index.
   * @param index Index to get (starting at 0).
   * @return A value (may be <code>null</code>).
   * @throws IndexOutOfBoundsException if index is out of bounds.
   */
  public Object getValueAt(int index)
  {
    return _values.get(index);
  }
}
