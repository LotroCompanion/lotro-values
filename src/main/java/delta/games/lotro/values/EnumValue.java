package delta.games.lotro.values;

/**
 * An enum value.
 * @author DAM
 */
public class EnumValue
{
  private Integer _value;

  /**
   * Constructor.
   */
  public EnumValue()
  {
    _value=null;
  }

  /**
   * Get the managed value (enum entry code).
   * @return A value or <code>null</code>.
   */
  public Integer getValue()
  {
    return _value;
  }

  /**
   * Set the enum value (code).
   * @param value A value or <code>null</code>).
   */
  public void setValue(Integer value)
  {
    _value=value;
  }
}
