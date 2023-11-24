package de.greluc.sc.loc.installer.data;

import org.jetbrains.annotations.Contract;

public enum Channel {
  LIVE("LIVE"),
  PTU("PTU"),
  EPTU("EPTU"),
  TECH("TECH-PREVIEW");

  private final String type;

  /**
   * Standard constructor used to map a {@link String} to a type.
   *
   * @param type {@link String} containing the type of the channel.
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
