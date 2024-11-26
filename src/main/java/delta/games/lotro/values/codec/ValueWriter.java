package delta.games.lotro.values.codec;

import java.util.BitSet;
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
  public static String write(Object value)
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
    else if (value instanceof Integer)
    {
      sb.append(value);
    }
    else if (value instanceof Long)
    {
      sb.append(value).append('L');
    }
    else if (value instanceof Float)
    {
      sb.append(value).append('f');
    }
    else if (value instanceof String)
    {
      writeEscapedString(sb,(String)value);
    }
    else if (value instanceof Boolean)
    {
      writeBoolean(sb,(Boolean)value);
    }
    else if (value instanceof BitSet)
    {
      writeBitSet(sb,(BitSet)value);
    }
    else if (value instanceof EnumValue)
    {
      EnumValue enumValue=(EnumValue)value;
      sb.append('e');
      int enumId=enumValue.getEnumId();
      sb.append(enumId);
      sb.append(':');
      int intValue=enumValue.getValue();
      sb.append(intValue);
    }
    else if (value instanceof ArrayValue)
    {
      writeArrayValue(sb,(ArrayValue)value);
    }
    else if (value instanceof StructValue)
    {
      writeStructValue(sb,(StructValue)value);
    }
    else
    {
      String msg="Unmanaged value type: "+value.getClass()+": "+value;
      throw new IllegalStateException(msg);
    }
  }

  private static void writeEscapedString(StringBuilder sb, String stringValue)
  {
    sb.append('\'');
    if (stringValue.indexOf('\'')!=-1)
    {
      stringValue=stringValue.replace("'","\\'");
    }
    sb.append(stringValue).append('\'');
  }

  private static void writeBoolean(StringBuilder sb, Boolean booleanValue)
  {
    sb.append(booleanValue==Boolean.TRUE);
  }

  private static void writeBitSet(StringBuilder sb, BitSet bitSet)
  {
    sb.append('(');
    int size=bitSet.size();
    boolean isFirst=true;
    for(int i=0;i<size;i++)
    {
      if (bitSet.get(i))
      {
        if (!isFirst)
        {
          sb.append(',');
        }
        isFirst=false;
        sb.append(i);
      }
    }
    sb.append(')');
  }

  private static void writeArrayValue(StringBuilder sb, ArrayValue arrayValue)
  {
    sb.append('[');
    int size=arrayValue.getSize();
    for(int i=0;i<size;i++)
    {
      if (i>0)
      {
        sb.append(',');
      }
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
      writeEscapedString(sb,key);
      sb.append(':');
      writeValue(sb,value);
      index++;
    }
    sb.append('}');
  }
}
