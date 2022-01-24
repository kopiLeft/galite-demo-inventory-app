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
package org.kopi.galite.demo.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.kopi.galite.visual.db.Slf4jSqlInfoLogger

const val DEMO_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
const val DEMO_DRIVER = "org.h2.Driver"
const val DEMO_USER = "admin"
const val DEMO_PASSWORD = "admin"

val dbConfig = DatabaseConfig {
  sqlLogger = Slf4jSqlInfoLogger
}

fun connect() {
  Database.connect(DEMO_URL, DEMO_DRIVER, DEMO_USER, DEMO_PASSWORD, databaseConfig = dbConfig)
}
