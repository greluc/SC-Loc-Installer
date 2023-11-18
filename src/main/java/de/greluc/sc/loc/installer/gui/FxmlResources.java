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

import lombok.Generated;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Contains the paths to the {@code FXML} files ands builds {@link Resource}s for them.
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @since 1.0.0
 * @version 1.0.0
 */
@Getter
@Component
public class FxmlResources {

  private static final String CLASSPATH = "classpath:";
  private static final String CLASSPATH_FXML = CLASSPATH + "/fxml/";
  private static final String CLASSPATH_FXML_MAIN = CLASSPATH_FXML + "MainView.fxml";
  private static final String CLASSPATH_FXML_CONTENT = CLASSPATH_FXML + "ContentView.fxml";
  private static final String CLASSPATH_FXML_SETTINGS = CLASSPATH_FXML + "SettingsView.fxml";

  private final Resource mainView;
  private final Resource contentView;
  private final Resource settingsView;

  /**
   * Used for dependency injection. Injects the {@link Resource}s for the {@code FXML} files.
   *
   * @param mainView      {@link Resource} for {@code MainView.fxml}.
   * @param contentView   {@link Resource} for {@code ContentView.fxml}.
   * @param settingsView  {@link Resource} for {@code SettingsView.fxml}.
   */
  @Contract(pure = true)
  @Autowired
  @Generated
  public FxmlResources(
      @Value(CLASSPATH_FXML_MAIN) Resource mainView,
      @Value(CLASSPATH_FXML_CONTENT) Resource contentView,
      @Value(CLASSPATH_FXML_SETTINGS) Resource settingsView) {
    this.mainView = mainView;
    this.contentView = contentView;
    this.settingsView = settingsView;
  }
}
