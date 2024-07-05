package delta.games.lotro.values.codec;

import java.util.List;

import delta.games.lotro.values.ArrayValue;
import delta.games.lotro.values.EnumValue;
import delta.games.lotro.values.StructValue;

/**
 * Writes values as strings.
 * @author DAM
 */
public class ValueWriter
{
  /**
   * Encode the given value as a string.
   * @param value Value to encode.
   * @return the encoded string.
   */
  public static String getValue(Object value)
  {
    StringBuilder sb=new StringBuilder();
    writeValue(sb,value);
    return sb.toString();
  }

  private static void writeValue(StringBuilder sb, Object value)
  {
    if (value==null)
    {
      sb.append("null");
    }
    // Long, Integer, Float
    if (value instanceof Number)
    {
      sb.append(value);
    }
    else if (value instanceof EnumValue)
    {
      EnumValue enumValue=(EnumValue)value;
      Integer intValue=enumValue.getValue();
      writeValue(sb,intValue);
    }
    else if (value instanceof ArrayValue)
    {
      writeArrayValue(sb,(ArrayValue)value);
    }
    else if (value instanceof StructValue)
    {
      writeStructValue(sb,(StructValue)value);
    }
  }

  private static void writeArrayValue(StringBuilder sb, ArrayValue arrayValue)
  {
    sb.append('[');
    int size=arrayValue.getSize();
    sb.append(size);
    for(int i=0;i<size;i++)
    {
      sb.append(';');
      writeValue(sb,arrayValue.getValueAt(i));
    }
    sb.append(']');
  }

  private static void writeStructValue(StringBuilder sb, StructValue structValue)
  {
    sb.append('{');
    List<String> keys=structValue.getKeys();
    int index=0;
    for(String key : keys)
    {
      Object value=structValue.getValue(key);
      if (index>0)
      {
        sb.append(',');
      }
      sb.append('"').append(key).append('"').append('=');
      writeValue(sb,value);
      index++;
    }
    sb.append('}');
  }
}
