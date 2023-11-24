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

plugins {
  id("java")
  id("application")
  id("idea")
  id("jacoco")
  id("checkstyle")
  id("maven-publish")
  id("org.openjfx.javafxplugin").version("0.1.0") // https://plugins.gradle.org/plugin/org.openjfx.javafxplugin
  id("io.spring.dependency-management").version("1.1.4") // https://plugins.gradle.org/plugin/io.spring.dependency-management
  id("org.springframework.boot").version("3.2.0") // https://plugins.gradle.org/plugin/org.springframework.boot
  id("org.cyclonedx.bom") version "1.8.1" // https://github.com/CycloneDX/cyclonedx-gradle-plugin
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")
  testImplementation("org.springframework.boot:spring-boot-starter-test")

  // https://search.maven.org/artifact/org.controlsfx/controlsfx
  implementation("org.controlsfx:controlsfx:11.2.0")

  // https://github.com/dukke/Transit
  implementation("com.pixelduke:transit:1.0.0")

  // https://github.com/JetBrains/java-annotations
  // https://mvnrepository.com/artifact/org.jetbrains/annotations
  implementation("org.jetbrains:annotations:24.1.0")
}

base {
  group = "de.greluc.sc.loc"
  version = "0.0.1"
  description = ""
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

java {
  sourceCompatibility = JavaVersion.VERSION_21
}

javafx {
  application {
    mainClass.set("de.greluc.sc.loc.installer.Installer")
  }

  springBoot {
    mainClass.set("de.greluc.sc.loc.installer.Installer")
  }
  modules("javafx.controls", "javafx.fxml")
  version = "21"
}

idea {
  module {
    inheritOutputDirs = true
  }
}

checkstyle {
  toolVersion = "10.12.5" // https://github.com/checkstyle/checkstyle
}

tasks.test {
  useJUnitPlatform()
  finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
  dependsOn(tasks.test) // tests are required to run before generating the report
  reports {
    xml.required.set(true)
    csv.required.set(true)
    html.required.set(true)
  }
}