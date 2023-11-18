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

package de.greluc.sc.loc.installer;

import com.pixelduke.transit.Style;
import com.pixelduke.transit.TransitTheme;
import de.greluc.sc.loc.installer.gui.AlertHandler;
import de.greluc.sc.loc.installer.gui.FxmlResources;
import de.greluc.sc.loc.installer.i18n.I18N;
import de.greluc.sc.loc.installer.i18n.I18NConstants;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Generated;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * Starts the JavaFX application.
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @since 1.0.0
 * @version 1.0.0
 */
@Slf4j
public class ClientApplication extends Application {

  private ConfigurableApplicationContext applicationContext;

  /**
   * Constructs a new {@code ClientApplication} instance.
   */
  @Generated
  public ClientApplication() {
    super();
  }

  @Override
  @Generated
  public void init() {
    applicationContext = new SpringApplicationBuilder(Installer.class).run();
  }

  @Override
  @Generated
  public void start(@NotNull @NonNull Stage stage) {
    printProperties();
    var fxmlResources = (FxmlResources) applicationContext.getBean("fxmlResources");
    var i18N = (I18N) applicationContext.getBean("i18N");
    getPaneFromFxml(fxmlResources.getMainView()).ifPresentOrElse(pane -> {
      var scene = new Scene(pane, 1000, 700);
      new TransitTheme(scene, Style.DARK).getOverridingStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/font.css")).toExternalForm());
      stage.setScene(scene);
      stage.setMinWidth(1000);
      stage.setMinHeight(700);
      i18N.initBinding(stage.titleProperty(), I18NConstants.VIEW_MAIN_TITLE);
      stage.show();
    }, () -> {
      log.error("MainView could not be opened!");
      var alertHandler = applicationContext.getBean(AlertHandler.class);
      alertHandler.showGeneralError();
      Platform.exit();
    });
  }

  /**
   * This method is called when the application should stop, and provides a convenient place to
   * prepare for application exit and destroy resources.
   *
   * <p>
   * The implementation of this method provided by the Application class does nothing.
   * </p>
   *
   * <p>
   * NOTE: This method is called on the JavaFX Application Thread.
   */
  @Override
  @Generated
  public void stop() {
    log.info("The application \"Installer\" is exiting.");
    applicationContext.close();
  }

  /**
   * Loads a {@link Pane} from a {@code FXML} document, which is specified by the given {@link
   * Resource}.
   *
   * @param resource Specifies which {@code FXML} should be loaded.
   *
   * @return {@link Pane} specified in the {@code FXML} wrapped in an {@link Optional}. Empty if an
   *     exception occurred while loading the {@code FXML}.
   */
  @NotNull
  private Optional<Pane> getPaneFromFxml(@NotNull @NonNull Resource resource) {
    try {
      var fxmlLoader = new FXMLLoader(resource.getURL());
      fxmlLoader.setControllerFactory(applicationContext::getBean);
      return Optional.of(fxmlLoader.load());
    } catch (IOException exception) {
      log.error(
          "Exception while loading main window of the application \"Installer\". IOException!");
      log.debug("Tried to load following file: {}", resource.getFilename(), exception);
    } catch (Exception exception) {
      log.error(
          "Exception while loading main window of the application \"Installer\". General exception!");
      log.debug("Tried to load following file: {}", resource.getFilename(), exception);
    }
    return Optional.empty();
  }

  /**
   * Prints some basic environment and application properties.
   */
  @Generated
  private void printProperties() {
    // print java, javafx and os to log
    log.info("Running with Java: " + System.getProperty("java.version") + " - " + System
        .getProperty("java.vendor") + " - " + System.getProperty("java.vm.version"));
    log.info("Running with JavaFX: " + System.getProperty("javafx.version") + " - " + System
        .getProperty("javafx.runtime.version"));
    log.info(
        "Running on OS: " + System.getProperty("os.name") + " - " + System.getProperty("os.arch")
            + " - " + System.getProperty("os.version"));

    // print all system properties to log when log level is trace
    log.trace(CommonConstants.DELIMITER);
    log.trace("System properties start");
    log.trace(CommonConstants.DELIMITER);
    if (log.isTraceEnabled()) {
      var properties = System.getProperties();
      properties.forEach((key, value) -> log.trace(key + ": " + value));
    }
    log.trace(CommonConstants.DELIMITER);
    log.trace("System properties end");
    log.trace(CommonConstants.DELIMITER);
  }
}
