package delta.games.lotro.values.utils;

import java.util.Arrays;

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
   * Skip some bytes.
   * @param nbBytes Number of bytes to skip.
   */
  public void skip(int nbBytes)
  {
    _index+=nbBytes;
    if (_index>_input.length())
    {
      throw new IllegalStateException("Reached end of stream!");
    }
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
   * Read an expected string.
   * @param s Expected string.
   */
  public void readExpectedString(String s)
  {
    for(char c : s.toCharArray())
    {
      readExpectedChar(c);
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
   * Indicates if we're at the end of the string.
   * @return <code>true</code> if we are, <code>false</code> otherwise.
   */
  public boolean isAtEnd()
  {
    return _index==_input.length();
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

  /**
   * Peek the next bytes and check it is the given string.
   * @param s String to use.
   * @return <code>true</code> if validated, <code>false</code> otherwise.
   */
  public boolean peekString(String s)
  {
    if (isAtEnd())
    {
      return false;
    }
    int length=s.length();
    if (_index+length>_input.length())
    {
      return false;
    }
    int offset=0;
    for(char c : s.toCharArray())
    {
      char readChar=_input.charAt(_index+offset);
      if (readChar!=c)
      {
        return false;
      }
      offset++;
    }
    return true;
  }

  /**
   * Read all next characters that belong to the given allowed characters.
   * @param allowedChars Allowed chars.
   * @return A string. May be empty but never <code>null</code>.
   */
  public String readAllowedChars(char[] allowedChars)
  {
    StringBuilder sb=new StringBuilder();
    while(true)
    {
      if (isAtEnd())
      {
        break;
      }
      char c=peek();
      int index=Arrays.binarySearch(allowedChars,c);
      if (index>=0)
      {
        c=readChar();
        sb.append(c);
      }
      else
      {
        break;
      }
    }
    return sb.toString();
  }
}
