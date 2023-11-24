package de.greluc.sc.loc.installer.data;

import org.jetbrains.annotations.Contract;

/**
 * An {@link Enum} representing the different channels of Star Citizen.
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Channel {
  LIVE("LIVE"),
  PTU("PTU"),
  EPTU("EPTU"),
  TECH("TECH-PREVIEW");
  private final String type;

  /**
   * A standard constructor used to map a {@link String} to a type.
   *
   * @param type {@link String} containing the channel type.
   */
  @Contract(pure = true)
  Channel(String type) {
    this.type = type;
  }

  /**
   * Returns the type as a {@link String}.
   *
   * @return Type represented as {@link String}.
   */
  @Contract(pure = true)
  @Override
  public String toString() {
    return this.type;
  }
}
