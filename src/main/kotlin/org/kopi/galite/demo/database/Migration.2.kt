/*
 * Copyright (c) 2013-2021 kopiLeft Services SARL, Tunis TN
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

import kotlin.reflect.KClass

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.kopi.galite.visual.db.Modules
import org.kopi.galite.visual.db.UserRights
import org.kopi.galite.visual.db.Users

fun migration2() {
  connect()

  transaction {
    insertIntoModule("1000", "org/kopi/galite/test/Menu", 0)
  }
}

/**
 * Inserts data into [Module] table
 */
fun insertIntoModule(shortname: String,
                     source: String,
                     priorityNumber: Int,
                     parentName: String = "-1",
                     className: KClass<*>? = null,
                     symbolNumber: Int? = null) {
  Modules.insert {
    it[uc] = 0
    it[ts] = 0
    it[shortName] = shortname
    it[parent] = if (parentName != "-1") Modules.select { shortName eq parentName }.single()[id] else -1
    it[sourceName] = source
    it[priority] = priorityNumber
    it[objectName] = if (className != null) className.qualifiedName!! else null
    it[symbol] = symbolNumber
  }
  insertIntoUserRights(DEMO_USER, shortname, true)
}

/**
 * Inserts data into [UserRights] table
 */
fun insertIntoUserRights(userName: String,
                         moduleName: String,
                         accessUser: Boolean) {
  UserRights.insert {
    it[ts] = 0
    it[module] = Modules.slice(Modules.id).select { Modules.shortName eq moduleName }.single()[Modules.id]
    it[user] = Users.slice(Users.id).select { Users.shortName eq userName }.single()[Users.id]
    it[access] = accessUser
  }
}
