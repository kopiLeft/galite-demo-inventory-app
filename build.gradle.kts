/*
 * Copyright (c) 2013-2022 kopiLeft Services SARL, Tunis TN
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

plugins {
  id("org.jetbrains.kotlin.jvm") version "1.5.30"
  id("io.spring.dependency-management") version "1.0.10.RELEASE"
  id("com.vaadin") version "21.0.9"
  id("org.gretty") version "3.0.6"
}

vaadin {
  pnpmEnable = true
}

gretty {
  contextPath = "/"
  servletContainer = "jetty9.4"
}


val vaadinVersion = "21.0.9"
val karibuTestingVersion = "1.2.5"
val h2Version = "1.4.199"
val postgresNGVersion = "0.8.9"
val exposedVersion = "0.36.2"

dependencies {
  implementation(kotlin("stdlib"))
  implementation(kotlin("reflect"))
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  testImplementation(kotlin("test-junit"))

  implementation("org.kopi", "galite-core", "1.0.0-beta.4")
  implementation("org.jetbrains.exposed", "exposed-jdbc", exposedVersion)

  implementation("com.vaadin", "vaadin-core") {
    listOf("com.vaadin.webjar", "org.webjars.bowergithub.insites",
           "org.webjars.bowergithub.polymer", "org.webjars.bowergithub.polymerelements",
           "org.webjars.bowergithub.vaadin", "org.webjars.bowergithub.webcomponents")
      .forEach { group -> exclude(group = group) }
  }

  implementation("com.h2database", "h2", h2Version)
  implementation("com.impossibl.pgjdbc-ng", "pgjdbc-ng", postgresNGVersion)
}

repositories {
  jcenter()
  maven {
    url = uri("https://maven.vaadin.com/vaadin-addons")
  }
}

tasks {
  compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }
  compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }
}

dependencyManagement {
  imports {
    mavenBom("com.vaadin:vaadin-bom:${vaadinVersion}")
  }
}
