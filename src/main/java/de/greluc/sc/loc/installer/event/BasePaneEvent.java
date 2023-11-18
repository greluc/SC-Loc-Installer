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

import de.greluc.sc.loc.installer.gui.controller.MainViewController;
import javafx.scene.layout.GridPane;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEvent;

/**
 * Event used to transmit the base pane to the {@link de.greluc.sc.loc.installer.gui.ViewHandler}.
 * <p>
 * Published by the {@link MainViewController}.
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @version 1.0.0
 * @since x.x.x
 */
@EqualsAndHashCode(callSuper = false)
public class BasePaneEvent extends ApplicationEvent {

  @Getter
  private final GridPane basePane;

  /**
   * Used to set the necessary data of this event.
   *
   * @param source {@link Object} that published this event.
   * @param basePane The base pane of the JavaFX application.
   */
  @Generated
  public BasePaneEvent(@NotNull @NonNull Object source, @NotNull @NonNull GridPane basePane) {
    super(source);
    this.basePane = basePane;
  }
}
