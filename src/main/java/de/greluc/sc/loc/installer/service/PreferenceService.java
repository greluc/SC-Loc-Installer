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

package de.greluc.sc.loc.installer.service;

import de.greluc.sc.loc.installer.data.PreferenceData;
import de.greluc.sc.loc.installer.gui.AlertHandler;
import de.greluc.sc.loc.installer.i18n.I18N;
import jakarta.annotation.PostConstruct;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.scene.control.Alert.AlertType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the persistence of the preferences.
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @since 1.0.0
 * @version 1.0.0
 */
@Service
@Slf4j
public class PreferenceService {
  private final I18N i18N;
  private final Preferences preferences;
  private final PreferenceData preferenceData;
  private final AlertHandler alertHandler;
  private static final String LANGUAGE = "language";

  @Autowired
  public PreferenceService(I18N i18N, PreferenceData preferenceData, AlertHandler alertHandler) {
    this.i18N = i18N;
    this.alertHandler = alertHandler;
    this.preferenceData = preferenceData;
    preferences = Preferences.userRoot().node(this.getClass().getName());
  }

  @PostConstruct
  private void init() {
    loadPreferences();
  }

  public void persistPreferences() {
    preferences.put(LANGUAGE, preferenceData.getLanguage());
    try {
      preferences.flush();
    } catch (BackingStoreException exception) {
      log.error("Couldn't persist the preferences!", exception);
      alertHandler.showAlert(AlertType.ERROR, "ERROR", "Error while persisting the preferences!", "Couldn't save the preferences to the persistent storage. Maybe you have no access rights or the storage is full."); // TODO I18N
    }
  }

  public void loadPreferences() {
    try {
      preferences.sync();
    } catch (BackingStoreException e) {
      log.warn("Couldn't load the preferences from the persistent store!");
    }
    preferenceData.setLanguage(preferences.get(LANGUAGE, i18N.getDefaultLocale().toLanguageTag()));
  }
}
