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

import javafx.application.Application;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class starts the {@code ClientApplication}. It is necessary to run this JavaFX application
 * as a FatJAR/UberJAR with Java 17+.
 *
 * @author Lucas Greuloch (greluc, lucas.greuloch@pm.me)
 * @since 1.0.0
 * @version 1.0.0
 */
@SpringBootApplication
public class Installer {

  /**
   * Constructs a new {@code Installer} instance.
   */
  @Contract(pure = true)
  @Generated
  public Installer() {
    super();
  }

  /**
   * Entry point to start the Installer application.
   *
   * @param args Command line arguments.
   */
  @Generated
  public static void main(String[] args) {
    Application.launch(ClientApplication.class, args);
  }
}