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

import static de.greluc.sc.loc.installer.i18n.I18NConstants.I18N_BUNDLE_NAME;

import de.greluc.sc.loc.installer.data.PreferencesData;
import de.greluc.sc.loc.installer.event.LocaleChangeEvent;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * I18N utility class.
 *
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @see <a href="https://www.sothawo.com/2016/09/how-to-implement-a-javafx-ui-where-the-language-can-be-changed-dynamically/">https://www.sothawo.com/2016/09/how-to-implement-a-javafx-ui-where-the-language-can-be-changed-dynamically/</a>
 * @since 1.0.0
 * @version 1.0.0
 */
@Component
@Slf4j
public final class I18N {

  /**
   * The current selected Locale.
   */
  @Getter
  private final ObjectProperty<Locale> localeProperty;
  private final ApplicationContext applicationContext;
  private final PreferencesData preferencesData;
  private ResourceBundle bundle;

  /**
   * Initializes the I18N and uses {@code getDefaultLocale()} to set the current Locale.
   *
   * @param applicationContext {@code Spring Boot ApplicationContext}.
   * @param preferencesData Singleton containing all preferences of the application.
   */
  @Contract(value = "_, _ -> fail", pure = true)
  public I18N(@NotNull @NonNull ApplicationContext applicationContext,
      PreferencesData preferencesData) {
    this.applicationContext = applicationContext;
    this.preferencesData = preferencesData;
    localeProperty = new SimpleObjectProperty<>(Locale.getDefault());
    bundle = ResourceBundle.getBundle(I18N_BUNDLE_NAME, localeProperty.get());
    localeProperty.addListener((observable, oldValue, newValue) -> Locale.setDefault(newValue));
  }

  /**
   * Get the supported Locales.
   *
   * @return List of Locale objects.
   */
  @Contract(" -> new")
  @NotNull
  public ObservableList<Locale> getSupportedLocales() {
    return FXCollections
        .observableArrayList(Locale.ENGLISH, Locale.US, Locale.GERMAN, Locale.GERMANY);
  }

  /**
   * Get the default locale. This is the systems default if contained in the supported locales,
   * english otherwise.
   *
   * @return Returns current default locale or english if the default locale of the system is not
   *     supported.
   */
  @NotNull
  public Locale getDefaultLocale() {
    Locale sysDefault = Locale.getDefault();
    return getSupportedLocales().contains(sysDefault) ? sysDefault : Locale.ENGLISH;
  }

  /**
   * Returns the current Locale.
   *
   * @return Current active Locale.
   */
  @NotNull
  public Locale getLocale() {
    return localeProperty.get();
  }

  /**
   * Sets a new Locale. Displayed language will change immediately. If the given locale is not
   *  supported, it will default to english.
   */
  public void setLocale() {
    Locale locale = Locale.forLanguageTag(preferencesData.getLanguage());
    if (!getSupportedLocales().contains(locale)) {
      locale = Locale.ENGLISH;
    }
    ResourceBundle.clearCache();
    bundle = ResourceBundle.getBundle(I18N_BUNDLE_NAME, locale);
    localeProperty.set(locale);
    applicationContext.publishEvent(new LocaleChangeEvent(this, locale));
  }

  /**
   * Creates the necessary I18N binding for the given {@link StringProperty}.
   *
   * @param stringProperty {@link StringProperty} which should be bounded with the localized
   *     text.
   * @param key I18N key. Must not be NULL.
   * @param args Optional arguments for the message.
   */
  @Contract(pure = true)
  public void initBinding(@NotNull @NonNull StringProperty stringProperty,
      @NotNull @NonNull String key,
      final Object... args) {
    stringProperty.bind(Bindings.createStringBinding(() -> get(key, args), localeProperty));
  }

  /**
   * Gets the string with the given key from the resource bundle for the current locale and uses it
   * as first argument to MessageFormat::format(), passing in the optional args and returning the
   * result.
   *
   * @param key I18N key. Mustn't be NULL.
   * @param args Optional arguments for the message.
   *
   * @return localized formatted string
   */
  @NotNull
  public String get(@NotNull @NonNull final String key, final Object... args) {
    return MessageFormat.format(bundle.getString(key), args);
  }
}