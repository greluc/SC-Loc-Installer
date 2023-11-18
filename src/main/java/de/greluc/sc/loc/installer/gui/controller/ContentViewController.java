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

package de.greluc.sc.loc.installer.gui.controller;

import de.greluc.sc.loc.installer.i18n.I18N;
import de.greluc.sc.loc.installer.i18n.I18NConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.Generated;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Corresponding controller for the WelcomeView (ContentView.fxml).
 * <p>
 * Shows a greeting.
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @since 1.0.0
 * @version 1.0.0
 */
@Component
@Slf4j
public class ContentViewController {

  private final I18N i18N;
  @FXML
  private Label labelWelcome;

  /**
   * Used for dependency injection.
   *
   * @param i18N Class that handles the I18N in this project.
   */
  @Contract(pure = true)
  @Autowired
  public ContentViewController(@NotNull @NonNull I18N i18N) {
    this.i18N = i18N;
  }

  /**
   * Initializes the view.
   */
  @FXML
  @Generated
  void initialize() {
    initTextBindings();
  }

  /**
   * Initializes the text bindings for I18N.
   */
  @Generated
  private void initTextBindings() {
    //i18N.initBinding(labelWelcome.textProperty(), I18NConstants.VIEW_WELCOME_LABEL);
  }
}
