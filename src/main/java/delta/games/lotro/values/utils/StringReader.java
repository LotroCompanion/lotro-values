package delta.games.lotro.values.utils;

/**
 * String reading facilities.
 * @author DAM
 */
public class StringReader
{
  private String _input;
  private int _index;

  /**
   * Constructor.
   * @param input Input string.
   */
  public StringReader(String input)
  {
    _input=input;
    _index=0;
  }

  /**
   * Read an expected character.
   * @param c Expected character.
   */
  public void readExpectedChar(char c)
  {
    char readChar=readChar();
    if (readChar!=c)
    {
      throw new IllegalStateException("Read '"+readChar+"' instead of expected '"+c+"'!");
    }
  }

  /**
   * Read next character.
   * @return A character.
   * @throws IllegalStateException If at end of string.
   */
  public char readChar()
  {
    if (_index<_input.length())
    {
      char c=_input.charAt(_index);
      _index++;
      return c;
    }
    throw new IllegalStateException("Attempt to read char at end of string!");
  }

  /**
   * Peek next character.
   * @return A character.
   * @throws IllegalStateException If at end of string.
   */
  public char peek()
  {
    if (_index<_input.length())
    {
      char c=_input.charAt(_index);
      return c;
    }
    throw new IllegalStateException("Attempt to peek char at end of string!");
  }
}
