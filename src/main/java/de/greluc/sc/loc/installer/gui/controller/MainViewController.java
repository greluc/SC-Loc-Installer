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

import static de.greluc.sc.loc.installer.i18n.I18NConstants.*;

import de.greluc.sc.loc.installer.data.PreferenceData;
import de.greluc.sc.loc.installer.event.BasePaneEvent;
import de.greluc.sc.loc.installer.event.ViewChangeEvent;
import de.greluc.sc.loc.installer.gui.ViewHandler;
import de.greluc.sc.loc.installer.gui.ViewType;
import de.greluc.sc.loc.installer.i18n.I18N;
import de.greluc.sc.loc.installer.service.PreferenceService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import lombok.Generated;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * A corresponding controller for the MainView (MainView.fxml).
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
@Slf4j
public class MainViewController {

  private final I18N i18N;
  private final ApplicationContext applicationContext;
  private final PreferenceData preferenceData;
  private final PreferenceService preferenceService;
  @FXML
  private GridPane basePane;
  @FXML
  private Menu menuFile;
  @FXML
  private MenuItem menuFileSettings;
  @FXML
  private MenuItem menuFileQuit;
  @FXML
  private Menu menuHelp;
  @FXML
  private MenuItem menuHelpAbout;

  /**
   * Used for dependency injection.
   *
   * @param i18N This class handles the I18N in this project.
   * @param applicationContext Spring Boot {@link ApplicationContext}.
   * @param preferenceData Singleton containing all preferences of the application.
   * @param preferenceService This Service persists and loads the preferences.
   */
  @Contract(pure = true)
  @Autowired
  @Generated
  public MainViewController(@NotNull @NonNull I18N i18N, ApplicationContext applicationContext,
      @NotNull @NonNull PreferenceData preferenceData,
      @NotNull @NonNull PreferenceService preferenceService) {
    this.applicationContext = applicationContext;
    this.i18N = i18N;
    this.preferenceData = preferenceData;
    this.preferenceService = preferenceService;
  }

  /**
   * Initializes the view.
   * For example, inits the text bindings for I18N and calls {@link ViewHandler} to set the child pane.
   */
  @FXML
  @Generated
  void initialize() {
    applicationContext.publishEvent(new BasePaneEvent(this, basePane));
    initTextBindings();
    i18N.setLocale();
  }

  /**
   * Initializes the text bindings for I18N.
   */
  @Generated
  private void initTextBindings() {
    i18N.initBinding(menuFile.textProperty(), VIEW_MAIN_MENU_FILE);
    i18N.initBinding(menuFileSettings.textProperty(), VIEW_MAIN_MENU_FILE_SETTINGS);
    i18N.initBinding(menuFileQuit.textProperty(), VIEW_MAIN_MENU_FILE_QUIT);
    i18N.initBinding(menuHelp.textProperty(), VIEW_MAIN_MENU_HELP);
    i18N.initBinding(menuHelpAbout.textProperty(), VIEW_MAIN_MENU_HELP_ABOUT);
  }

  /**
   * Used to open a new window for the settings view, when the user pressed the corresponding
   * {@link MenuItem}.
   */
  @FXML
  @Generated
  private void openSettingsView() {
    applicationContext.publishEvent(new ViewChangeEvent(this, ViewType.SETTINGS));
  }

  /**
   * Shuts the application down.
   */
  @FXML
  @Generated
  private void closeApplication() {
    Platform.exit();
  }
}