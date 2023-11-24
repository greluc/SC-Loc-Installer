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

import de.greluc.sc.loc.installer.data.Channel;
import de.greluc.sc.loc.installer.gui.AlertHandler;
import de.greluc.sc.loc.installer.gui.ViewType;
import de.greluc.sc.loc.installer.i18n.I18N;
import de.greluc.sc.loc.installer.i18n.I18NConstants;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.util.StringConverter;
import lombok.Generated;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

import static de.greluc.sc.loc.installer.i18n.I18NConstants.*;

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
  private final AlertHandler alertHandler;
  
  @FXML
  private Button buttonSelectPath;
  @FXML
  private Label labelPath;
  @FXML
  private Label labelChannel;
  @FXML
  private Label labelLanguage;
  @FXML
  private Label labelStatus;
  @FXML
  private Label labelStatusBox;
  @FXML
  private ComboBox<Channel> comboBoxChannel;
  @FXML
  private ComboBox<String> comboBoxLanguage;
  @FXML
  private TextField textFieldPath;

  /**
   * Used for dependency injection.
   *
   * @param i18N         Class that handles the I18N in this project.
   * @param alertHandler
   */
  @Contract(pure = true)
  @Autowired
  public ContentViewController(@NotNull @NonNull I18N i18N, @NotNull @NonNull AlertHandler alertHandler) {
    this.i18N = i18N;
    this.alertHandler = alertHandler;
  }

  /**
   * Initializes the view.
   */
  @FXML
  @Generated
  void initialize() {
    comboBoxChannel.setItems(
      FXCollections.observableArrayList(Channel.LIVE, Channel.PTU, Channel.EPTU, Channel.TECH));
    comboBoxChannel.getSelectionModel().select(Channel.LIVE);
    comboBoxChannel.setConverter(new StringConverter<>() {
      @Override
      @NotNull
      @Generated
      public String toString(Channel channel) {
        if (channel == null) {
          return "";
        }
        return switch (channel) {
          case LIVE -> i18N.get(I18NConstants.VIEW_CONTENT_CHANNEL_LIVE);
          case PTU -> i18N.get(I18NConstants.VIEW_CONTENT_CHANNEL_PTU);
          case EPTU -> i18N.get(I18NConstants.VIEW_CONTENT_CHANNEL_EPTU);
          case TECH -> i18N.get(I18NConstants.VIEW_CONTENT_CHANNEL_TECH);
          default -> i18N.get(I18NConstants.VIEW_CONTENT_CHANNEL_LIVE);
        };
      }

      @Override
      @Generated
      public Channel fromString(String string) {
        return null;
      }
    });
    
    initTextBindings();
  }

  /**
   * Initializes the text bindings for I18N.
   */
  @Generated
  private void initTextBindings() {
    i18N.initBinding(buttonSelectPath.textProperty(), I18NConstants.VIEW_CONTENT_BUTTON_PATH);
    i18N.initBinding(labelPath.textProperty(), I18NConstants.VIEW_CONTENT_LABEL_PATH);
    i18N.initBinding(labelChannel.textProperty(), I18NConstants.VIEW_CONTENT_LABEL_CHANNEL);
    i18N.initBinding(labelLanguage.textProperty(), I18NConstants.VIEW_SETTINGS_LABEL_LANGUAGE);
    i18N.initBinding(labelStatus.textProperty(), I18NConstants.VIEW_CONTENT_LABEL_STATUS);
    i18N.initBinding(labelStatusBox.textProperty(), I18NConstants.VIEW_CONTENT_LABEL_STATUS_BOX_NOK);
    i18N.initBinding(textFieldPath.promptTextProperty(), I18NConstants.VIEW_CONTENT_TEXTFIELD_PATH_HINT);
  }
  
  @FXML
  void setButtonSelectPath() {
    Optional<Path> pathContainer = showDirectoryChooser();
    if (pathContainer.isEmpty()) {
      return;
    }
    // TODO checks
    if (!new File(pathContainer.get() + "\\LIVE\\StarCitizen_Launcher.exe").exists()) {
      alertHandler.showAlert(Alert.AlertType.ERROR, VIEW_CONTENT_ALERT_PATH_TITLE, VIEW_CONTENT_ALERT_PATH_HEADER, VIEW_CONTENT_ALERT_PATH_CONTENT);
      return;
    }
    textFieldPath.setText(pathContainer.get().toString());
  }


  /**
   * Opens a dialog in which the user can choose the path to the Star Citizen folder.
   *
   * @return Optional containing a {@code File} object representing the Star Citizen directory. Empty when
   * no directory has been chosen.
   */
  @Contract(value = " -> new", pure = true)
  Optional<Path> showDirectoryChooser() {
    final DirectoryChooser chooser = new DirectoryChooser();
    chooser.setTitle(i18N.get(VIEW_CONTENT_DIRECTORY_TITLE));
    File outputFile = chooser.showDialog(null);
    Path path = null;
    if (outputFile != null) {
      path = outputFile.toPath();
    }
    return Optional.ofNullable(path);
  }
}
