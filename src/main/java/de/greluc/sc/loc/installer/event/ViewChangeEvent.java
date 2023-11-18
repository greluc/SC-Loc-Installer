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

package de.greluc.sc.loc.installer.event;

import de.greluc.sc.loc.installer.gui.ViewType;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEvent;

/**
 * Event used switch the current view.
 * <p>
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @version 1.0.0
 * @since x.x.x
 */
@Getter
@EqualsAndHashCode(callSuper = false)
public class ViewChangeEvent extends ApplicationEvent {

  private final ViewType viewType;

  /**
   * Used to set the necessary data of this event.
   *
   * @param source {@link Object} that published this event.
   * @param viewType The new view that should be shown.
   */
  @Generated
  public ViewChangeEvent(@NotNull @NonNull Object source, @NotNull @NonNull ViewType viewType) {
    super(source);
    this.viewType = viewType;
  }
}
