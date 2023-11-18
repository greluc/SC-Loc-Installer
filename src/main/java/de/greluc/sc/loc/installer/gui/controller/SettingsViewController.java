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

import de.greluc.sc.loc.installer.data.PreferencesData;
import de.greluc.sc.loc.installer.i18n.I18N;
import de.greluc.sc.loc.installer.i18n.I18NConstants;
import de.greluc.sc.loc.installer.service.PreferencesService;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.Generated;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Corresponding controller for the SettingsView (SettingsView.fxml).
 * <p>
 * Gives the user the opportunity to change the displayed local and to change the log settings.
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @since 1.0.0
 * @version 1.0.0
 */
@Component
@Slf4j
public class SettingsViewController {

  private final I18N i18N;
  private final PreferencesService preferencesService;
  private final PreferencesData preferencesData;
  @FXML
  private Menu menuFile;
  @FXML
  private MenuItem menuFileSave;
  @FXML
  private MenuItem menuFileClose;
  @FXML
  private Button buttonSave;
  @FXML
  private Label labelChooseLocale;
  @FXML
  private Label labelChooseSimbriefUsername;
  @FXML
  private Label labelChooseSimbriefId;
  @FXML
  private TextField textFieldSimbriefUsername;
  @FXML
  private TextField textFieldSimbriefId;
  @FXML
  private ComboBox<Locale> comboBoxLocale;


  /**
   * Used for dependency injection.
   *
   * @param i18N Class that handles the I18N in this project.
   * @param preferencesService Service that persists and loads the preferences.
   * @param preferencesData Singleton containing all preferences of the application.
   */
  @Contract(pure = true)
  @Autowired
  @Generated
  public SettingsViewController(@NotNull @NonNull I18N i18N, PreferencesService preferencesService, PreferencesData preferencesData) {
    this.i18N = i18N;
    this.preferencesService = preferencesService;
    this.preferencesData = preferencesData;
  }

  /**
   * Initializes the view. E.g. inits the text bindings for I18N and configures the {@link
   * ComboBox}es.
   */
  @FXML
  @Generated
  void initialize() {
    comboBoxLocale.setItems(i18N.getSupportedLocales());
    comboBoxLocale.setConverter(createStringConverter());
    comboBoxLocale.getSelectionModel().select(i18N.getLocale());
    initTextBindings();
  }

  /**
   * Initializes the text bindings for I18N.
   */
  @Generated
  private void initTextBindings() {
    i18N.initBinding(menuFile.textProperty(), I18NConstants.VIEW_SETTINGS_MENU_FILE);
    i18N.initBinding(menuFileSave.textProperty(), I18NConstants.VIEW_SETTINGS_MENU_FILE_SAVE);
    i18N.initBinding(menuFileClose.textProperty(), I18NConstants.VIEW_SETTINGS_MENU_FILE_CLOSE);
    i18N.initBinding(buttonSave.textProperty(), I18NConstants.VIEW_SETTINGS_BUTTON_SAVE);
    i18N.initBinding(labelChooseLocale.textProperty(), I18NConstants.VIEW_SETTINGS_LABEL_LANGUAGE);
  }

  /**
   * Saves the settings. Currently, they are not persisted.
   */
  @FXML
  @Generated
  void saveSettings() {
    preferencesData.setLanguage(comboBoxLocale.getSelectionModel().getSelectedItem().toLanguageTag());
    preferencesService.persistPreferences();
    i18N.setLocale();
    closeWindow();
  }

  /**
   * Closes the dedicated settings window.
   */
  @FXML
  @Generated
  private void closeWindow() {
    var stage = (Stage) comboBoxLocale.getScene().getWindow();
    stage.close();
  }

  /**
   * Creates a {@code StringConverter} that converts locales by using {@link
   * #getLocaleName(Locale)}.
   *
   * @return {@code StringConverter} that converts locales to the format name_in_english -
   *     name_in_selected_language.
   */
  @Contract(value = " -> new", pure = true)
  @Generated
  private @NotNull StringConverter<Locale> createStringConverter() {
    return new StringConverter<>() {
      @Override
      @Generated
      public @NotNull String toString(@NotNull @NonNull Locale locale) {
        return getLocaleName(locale);
      }

      @Contract(value = "_ -> null", pure = true)
      @Override
      @Generated
      public Locale fromString(String string) {
        return null;
      }
    };
  }

  /**
   * Converts a {@link Locale} to a {@link String} that shows the locales name in english and the
   * locales' language.
   *
   * @param locale {@link Locale} that should be converted to a {@link String}. Must not be
   *     NULL.
   *
   * @return String showing the {@link Locale}s name in english and in the locales' language.
   */
  @Generated
  private @NotNull String getLocaleName(@NotNull @NonNull Locale locale) {
    String languageEnglish = locale.getDisplayLanguage(Locale.ENGLISH);
    String languageCurrent = locale.getDisplayLanguage(locale);
    if (!locale.getDisplayCountry().isBlank()) {
      languageEnglish = String
          .format("%s/%s", languageEnglish, locale.getDisplayCountry(Locale.ENGLISH));
      languageCurrent = String.format("%s/%s", languageCurrent, locale.getDisplayCountry(locale));
    }
    return String.format("%s - %s", languageEnglish, languageCurrent);
  }
}