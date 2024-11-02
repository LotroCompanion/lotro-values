package delta.games.lotro.values;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import delta.games.lotro.values.codec.ValueReader;

/**
 * Test value reader.
 * @author DAM
 */
class TestValueReader
{
  @Test
  void testSimpleReading()
  {
    // null value
    assertEquals((Object)null,read(null));
    // empty value
    assertEquals(null,read(""));
    // null
    assertEquals(null,read("null"));
    // bad null
    checkFails("n");
    // meaningless
    checkFails("t");
  }

  @Test
  void testNumberReading()
  {
    // int
    assertEquals(Integer.valueOf(123),read("123"));
    // negative int
    assertEquals(Integer.valueOf(-123),read("-123"));
    // long
    assertEquals(Long.valueOf(123L),read("123L"));
    // negative long
    assertEquals(Long.valueOf(-123L),read("-123L"));
    // float
    assertEquals(Float.valueOf(1.234f),read("1.234f"));
    // negative float
    assertEquals(Float.valueOf(-1.234f),read("-1.234f"));
  }

  @Test
  void testReadEnum()
  {
    // OK
    Object value=read("e1");
    assertTrue(value instanceof EnumValue);
    EnumValue enumValue=(EnumValue)value;
    assertEquals(Integer.valueOf(1),enumValue.getValue());
    // Bad
    checkFails("ea");
    checkFails("e");
    checkFails("e");
  }

  @Test
  void testStringReading()
  {
    // Empty string
    assertEquals("",read("\"\""));
    // String
    assertEquals("toto",read("\"toto\""));
    // String with double-quote
    assertEquals("to\"to",read("\"to\\\"to\""));
  }

  @Test
  void testArrayReading()
  {
    // Empty array
    {
      Object readValue=read("[]");
      assertTrue(readValue instanceof ArrayValue);
      ArrayValue readArray=(ArrayValue)readValue;
      assertEquals(0,readArray.getSize());
    }
    // Array with 1 int value
    {
      Object readValue=read("[1]");
      assertTrue(readValue instanceof ArrayValue);
      ArrayValue readArray=(ArrayValue)readValue;
      assertEquals(1,readArray.getSize());
      assertEquals(Integer.valueOf(1),readArray.getValueAt(0));
    }
    // Array with 2 int value
    {
      Object readValue=read("[1,2]");
      assertTrue(readValue instanceof ArrayValue);
      ArrayValue readArray=(ArrayValue)readValue;
      assertEquals(2,readArray.getSize());
      assertEquals(Integer.valueOf(1),readArray.getValueAt(0));
      assertEquals(Integer.valueOf(2),readArray.getValueAt(1));
    }
  }

  @Test
  void testStructReading()
  {
    // Empty map
    {
      Object readValue=read("{}");
      assertTrue(readValue instanceof StructValue);
      StructValue readStruct=(StructValue)readValue;
      assertEquals(0,readStruct.getKeys().size());
    }
    // Struct with 1 value
    {
      Object readValue=read("{\"toto\":1}");
      assertTrue(readValue instanceof StructValue);
      StructValue readStruct=(StructValue)readValue;
      assertEquals(1,readStruct.getKeys().size());
      assertEquals("toto",readStruct.getKeys().get(0));
      assertEquals(Integer.valueOf(1),readStruct.getValue("toto"));
    }
    // Array with 2 values
    {
      Object readValue=read("{\"toto\":1,\"titi\":\"tata\"}");
      assertTrue(readValue instanceof StructValue);
      StructValue readStruct=(StructValue)readValue;
      assertEquals(2,readStruct.getKeys().size());
      assertEquals(Integer.valueOf(1),readStruct.getValue("toto"));
      assertEquals("tata",readStruct.getValue("titi"));
    }
  }

  private boolean checkFails(String input)
  {
    try
    {
      read(input);
    }
    catch(Exception e)
    {
      return true;
    }
    return false;
  }

  private Object read(String input)
  {
    return ValueReader.read(input);
  }
}
