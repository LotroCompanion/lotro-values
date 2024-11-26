package delta.games.lotro.values;

/**
 * An enum value.
 * @author DAM
 */
public class EnumValue
{
  private int _enumId;
  private int _value;

  /**
   * Constructor.
   */
  public EnumValue()
  {
    _value=0;
  }

  /**
   * Get the enum identifier.
   * @return the enum identifier.
   */
  public int getEnumId()
  {
    return _enumId;
  }

  /**
   * Set the enum identifier.
   * @param enumId Identifier to set.
   */
  public void setEnumId(int enumId)
  {
    _enumId=enumId;
  }

  /**
   * Get the managed value (enum entry code).
   * @return A value.
   */
  public int getValue()
  {
    return _value;
  }

  /**
   * Set the enum value (code).
   * @param value A value or <code>null</code>).
   */
  public void setValue(int value)
  {
    _value=value;
  }
}
