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

package de.greluc.sc.loc.installer.i18n;

import de.greluc.sc.loc.installer.CommonConstants;
import lombok.Generated;
import org.jetbrains.annotations.Contract;

/**
 * Holds the keys used in the messages bundle.
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @since 1.0.0
 * @version 1.0.0
 */
public class I18NConstants {

  // I18N
  public static final String ALERT_ERROR_HEADER = "alert.error.header";
  public static final String ALERT_ERROR_CONTENT_GENERAL = "alert.error.content.general";
  public static final String NOTIFICATION_DECLINATION_ERROR_TITLE = "view.declination.error.title";
  public static final String NOTIFICATION_DECLINATION_ERROR_CONTENT = "view.declination.error.content";
  public static final String VIEW_SETTINGS = "view.settings";
  public static final String VIEW_MAIN_TITLE = "view.main.title";
  public static final String VIEW_MAIN_MENU_FILE = "view.main.menu.file";
  public static final String VIEW_MAIN_MENU_FILE_SETTINGS = "view.main.menu.file.settings";
  public static final String VIEW_MAIN_MENU_FILE_QUIT = "view.main.menu.file.quit";
  public static final String VIEW_MAIN_MENU_HELP = "view.main.menu.help";
  public static final String VIEW_MAIN_MENU_HELP_ABOUT = "view.main.menu.help.about";
  public static final String VIEW_SETTINGS_MENU_FILE = "view.settings.menu.file";
  public static final String VIEW_SETTINGS_MENU_FILE_SAVE = "view.settings.menu.file.save";
  public static final String VIEW_SETTINGS_MENU_FILE_CLOSE = "view.settings.menu.file.close";
  public static final String VIEW_SETTINGS_BUTTON_SAVE = "view.settings.button.save";
  public static final String VIEW_SETTINGS_LABEL_LANGUAGE = "view.settings.label.language";
  public static final String VIEW_SETTINGS_LABEL_LOG_LEVEL = "view.settings.label.log.level";
  public static final String VIEW_SETTINGS_LABEL_LOG_FILE = "view.settings.label.log.file";
  public static final String I18N_BUNDLE_NAME = "messages";

  /**
   * Used to exclude the unused constructor from code coverage evaluation.
   */
  @Contract(value = " -> fail", pure = true)
  @Generated
  private I18NConstants() {
    throw new IllegalStateException(CommonConstants.UTILITY_CLASS);
  }
}
