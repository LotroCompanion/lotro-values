package delta.games.lotro.values;

import java.util.ArrayList;
import java.util.List;

/**
 * An array value.
 * @author DAM
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
   * Add a value.
   * @param value Value to add (may be <code>null</code>).
   */
  public void addValue(Object value)
  {
    _values.add(value);
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
