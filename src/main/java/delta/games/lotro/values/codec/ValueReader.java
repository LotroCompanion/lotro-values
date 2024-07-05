package delta.games.lotro.values.codec;

import delta.games.lotro.values.ArrayValue;

/**
 * Reads values from strings.
 * @author DAM
 */
public class ValueReader
{
  /**
   * Read a value from a string.
   * @param value Input string.
   * @return the loaded value.
   */
  public static Object read(String value)
  {
    if (value==null)
    {
      return null;
    }
    if (value.length()==0)
    {
      return null;
    }
    char firstChar=value.charAt(0);
    if (firstChar=='[')
    {
      return readArrayValue(value);
    }
    return null;
  }

  private static ArrayValue readArrayValue(String value)
  {
    return null;
  }
}
