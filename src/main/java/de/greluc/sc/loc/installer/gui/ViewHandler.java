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

import com.pixelduke.transit.Style;
import com.pixelduke.transit.TransitStyleClass;
import com.pixelduke.transit.TransitTheme;
import de.greluc.sc.loc.installer.event.BasePaneEvent;
import de.greluc.sc.loc.installer.event.ViewChangeEvent;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Generated;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Loads the {@code FXMl} files and switches the shown view upon request.
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @since 1.0.0
 * @version 1.0.0
 */
@Slf4j
@Component
public class ViewHandler {

  private final FxmlResources fxmlResources;
  private final ApplicationContext applicationContext;
  private final AlertHandler alertHandler;
  private GridPane basePane;

  /**
   * Used for dependency injection.
   *
   * @param fxmlResources Holds the resources for the {@code FXML} files.
   * @param applicationContext Spring Boot {@link ApplicationContext}.
   * @param alertHandler Used to show {@link javafx.scene.control.Alert}s.
   */
  @Contract(pure = true)
  @Autowired
  @Generated
  public ViewHandler(@NotNull @NonNull FxmlResources fxmlResources,
      @NotNull @NonNull ApplicationContext applicationContext,
      @NotNull @NonNull AlertHandler alertHandler) {
    this.fxmlResources = fxmlResources;
    this.applicationContext = applicationContext;
    this.alertHandler = alertHandler;
  }

  /**
   * Adds the {@code SelectionView} to the base Pane of the MainView and shows the {@code
   * WelcomeView} in it.
   *
   * @param event Contains the base pane of the {@code MainView}. Instance of a {@link VBox}.
   */
  @Generated
  @EventListener(BasePaneEvent.class)
  public void onBasePaneEvent(@NotNull @NonNull BasePaneEvent event) {
    if (basePane == null) {
      basePane = event.getBasePane();
      getPaneFromFxml(ViewType.CONTENT)
          .ifPresent(pane -> {
            GridPane.setConstraints(pane,0, 1);
            basePane.getChildren().add(pane);
            basePane.getStyleClass().add(TransitStyleClass.BACKGROUND);
          });
    } else {
      log.warn("basePane is already set");
    }
  }

  /**
   * Changes the shown view. Publishes a {@link ActiveViewEvent} when a view was changed.
   *
   * @param event Specifies which {@code view} should be loaded/opened.
   */
  @EventListener(ViewChangeEvent.class)
  public void onViewChangeEvent(@NotNull @NonNull ViewChangeEvent event) {
    if (event.getViewType() == ViewType.SETTINGS) {
      getPaneFromFxml(ViewType.SETTINGS).ifPresentOrElse(this::openSettingsView, () -> {
        log.error("SettingsView could not be opened!");
        alertHandler.showGeneralError();
      });
    }
  }

  /**
   * Opens a new window with {@code SettingsView.fxml} as base {@link Pane}.
   *
   * @param pane Will be set as base {@link Pane} in the new window.
   */
  @Generated
  private void openSettingsView(@NotNull @NonNull Pane pane) {
    var stage = new Stage();
    var scene = new Scene(pane);
    new TransitTheme(scene, Style.DARK);
    pane.getStyleClass().add(TransitStyleClass.BACKGROUND);
    stage.setScene(scene);
    stage.setMaximized(false);
    stage.setResizable(true);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.show();
  }

  /**
   * Loads a {@code Pane} from a {@code FXML} document, which is specified by the given {@code
   * ViewType}.
   *
   * @param type Specifies which {@code FXML} should be loaded.
   *
   * @return {@link Pane} specified in the {@code FXML} wrapped in an {@link Optional}.
   */
  @NotNull
  public Optional<GridPane> getPaneFromFxml(@NotNull @NonNull ViewType type) {
    switch (type) {
      case SETTINGS -> {
        log.debug("Trying to load SettingsView");
        return getPaneFromFxml(fxmlResources.getSettingsView());
      }
      default -> {
        log.debug("Trying to load MainView");
        return getPaneFromFxml(fxmlResources.getContentView());
      }
    }
  }

  /**
   * Loads a {@link Pane} from a {@code FXML} document, which is specified by the given {@link
   * Resource} and wraps it in an {@link Optional}.
   *
   * @param resource Specifies which {@code FXML} should be loaded.
   *
   * @return {@code Pane} specified in the {@code FXML}.
   */
  @NotNull
  @Generated
  private Optional<GridPane> getPaneFromFxml(@NotNull @NonNull Resource resource) {
    try {
      var fxmlLoader = new FXMLLoader(resource.getURL());
      fxmlLoader.setControllerFactory(applicationContext::getBean);
      return Optional.of(fxmlLoader.load());
    } catch (IOException exception) {
      log.error("Exception while loading a FXML file. IOException!");
      log.debug("Tried to load following file: {}", resource.getFilename(), exception);
      if (log.isTraceEnabled()) {
        exception.printStackTrace();
      }
    } catch (Exception exception) {
      log.error("Exception while loading a FXML file. General exception!");
      log.debug("Tried to load following file: {}", resource.getFilename(), exception);
      if (log.isTraceEnabled()) {
        exception.printStackTrace();
      }
    }
    return Optional.empty();
  }
}
