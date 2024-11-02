package delta.games.lotro.values;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A struct value.
 * @author DAM
 */
public class StructValue
{
  private Map<String,Object> _values;

  /**
   * Constructor.
   */
  public StructValue()
  {
    _values=new HashMap<String,Object>();
  }

  /**
   * Get the known keys.
   * @return a list of keys.
   */
  public List<String> getKeys()
  {
    List<String> keys=new ArrayList<String>();
    keys.addAll(_values.keySet());
    Collections.sort(keys);
    return keys;
  }

  /**
   * Get the value for the given property name.
   * @param name Name to use.
   * @return A value or <code>null</code> if not found.
   */
  public Object getValue(String name)
  {
    return _values.get(name);
  }

  /**
   * Indicates if this struct has the given property.
   * @param name Property name to use.
   * @return <code>true</code> if it has this property (that may be <code>null</code>), <code>false</code> otherwise.
   */
  public boolean hasValue(String name)
  {
    return _values.containsKey(name);
  }

  /**
   * Set a value.
   * @param key Key.
   * @param value Value.
   */
  public void setValue(String key, Object value)
  {
    _values.put(key,value);
  }
}
