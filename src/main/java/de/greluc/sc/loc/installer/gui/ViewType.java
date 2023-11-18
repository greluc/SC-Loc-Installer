////////////////////////////////////////////////////////////////////////////////
// SC-Loc-Installer                                                            /
// Copyright (C) 2023-2023 SC-Loc-Installer Team                               /
//                                                                             /
// This program is free software: you can redistribute it and/or modify        /
// it under the terms of the GNU General Public License as published by        /
// the Free Software Foundation, either version 3 of the License, or           /
// (at your option) any later version.                                         /
//                                                                             /
// This program is distributed in the hope that it will be useful,             /
// but WITHOUT ANY WARRANTY; without even the implied warranty of              /
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the               /
// GNU General Public License for more details.                                /
//                                                                             /
// You should have received a copy of the GNU General Public License           /
// along with this program.  If not, see <http://www.gnu.org/licenses/>.       /
////////////////////////////////////////////////////////////////////////////////

package de.greluc.sc.loc.installer.gui;

import org.jetbrains.annotations.Contract;

/**
 * Represents all types of views that are available in PiFly.
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @since 1.0.0
 * @version 1.0.0
 */
public enum ViewType {
  SETTINGS("Settings"),
  CONTENT("Content");

  private final String type;

  /**
   * Standard constructor used to map a {@link String} to a type.
   *
   * @param type {@link String} containing the type of the view.
   */
  @Contract(pure = true)
  ViewType(String type) {
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
