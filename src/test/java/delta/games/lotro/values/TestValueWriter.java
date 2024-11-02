package delta.games.lotro.values;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import delta.games.lotro.values.codec.ValueWriter;

/**
 * Test value writer.
 * @author DAM
 */
public class TestValueWriter
{
  @Test
  void testUnmanagedValues()
  {
    boolean gotException=false;
    try
    {
      ValueWriter.write(new ArrayList<String>());
    }
    catch(Exception e)
    {
      gotException=true;
    }
    assertTrue(gotException);
  }
}
