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

import static de.greluc.sc.loc.installer.i18n.I18NConstants.ALERT_ERROR_CONTENT_GENERAL;
import static de.greluc.sc.loc.installer.i18n.I18NConstants.ALERT_ERROR_HEADER;
import static de.greluc.sc.loc.installer.i18n.I18NConstants.VIEW_MAIN_TITLE;

import com.pixelduke.transit.FlatAlert;
import com.pixelduke.transit.Style;
import com.pixelduke.transit.TransitTheme;
import de.greluc.sc.loc.installer.i18n.I18N;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import lombok.Generated;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Creates custom alert windows. Uses {@link FlatAlert} from the JMetro library.
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @since 1.0.0
 * @version 1.0.0
 */
@Component
@Slf4j
public class AlertHandler {

  private final I18N i18N;

  /**
   * Used for dependency injection.
   *
   * @param i18N Class that handles the I18N in this project.
   */
  @Contract(pure = true)
  @Autowired
  @Generated
  public AlertHandler(@NotNull @NonNull I18N i18N) {
    this.i18N = i18N;
  }

  /**
   * Shows a general error that doesn't specify a specific error in its message.
   */
  @Generated
  public void showGeneralError() {
    showAlert(AlertType.ERROR, VIEW_MAIN_TITLE, ALERT_ERROR_HEADER, ALERT_ERROR_CONTENT_GENERAL);
  }

  /**
   * Shows an alert. Uses the {@link FlatAlert} class of the JMetro library.
   *
   * @param alertType {@link AlertType} that should be used fot the alert.
   * @param titleKey I18N key for the title of the alert window.
   * @param headerKey I18N key for the short text with main information.
   * @param contentKey I18N key for the description of the alert.
   */
  @Generated
  public void showAlert(@NotNull @NonNull Alert.AlertType alertType,
      @NotNull @NonNull String titleKey, @NotNull @NonNull String headerKey,
      @NotNull @NonNull String contentKey) {
    var alert = new FlatAlert(alertType);
    i18N.initBinding(alert.titleProperty(), titleKey);
    i18N.initBinding(alert.headerTextProperty(), headerKey);
    i18N.initBinding(alert.contentTextProperty(), contentKey);
    new TransitTheme(alert.getDialogPane().getScene(), Style.DARK);
    alert.setResizable(true);
    alert.setHeight(500);
    alert.setWidth(500);
    alert.showAndWait();
  }
}
