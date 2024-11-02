package delta.games.lotro.values;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import delta.games.lotro.values.codec.ValueReader;
import delta.games.lotro.values.codec.ValueWriter;

/**
 * Test values IO
 * @author DAM
 */
class TestValueIO
{
  @Test
  void testSpecials()
  {
    testReadWrite("null");
  }

  @Test
  void testStrings()
  {
    testReadWrite("''");
    testReadWrite("'toto'");
    testReadWrite("'to\\'to'");
    testReadWrite("'\\'toto'");
    testReadWrite("'toto\\''");
  }

  @Test
  void testNumbers()
  {
    testReadWrite("1");
    testReadWrite("1L");
    testReadWrite("1.2f");
    testReadWrite("-1.2f");
  }

  @Test
  void testArrays()
  {
    testReadWrite("[]");
    testReadWrite("[1]");
    testReadWrite("[1,2]");
    testReadWrite("[e1]");
    testReadWrite("[e1,e2]");
    testReadWrite("[1,{'toto':1}]");
    testReadWrite("[null]");
  }

  @Test
  void testStructs()
  {
    testReadWrite("{}");
    testReadWrite("{'to\\'to':1}");
    testReadWrite("{'titi':[1,2],'to\\'to':1}"); // Keys shall be ordered
  }

  @Test
  void testBitSets()
  {
    testReadWrite("()");
    testReadWrite("(5)");
    testReadWrite("(1,2,3)");
  }

  String testReadWrite(String input)
  {
    Object ret=ValueReader.read(input);
    String output=ValueWriter.write(ret);
    assertEquals(input,output);
    return output;
  }
}
