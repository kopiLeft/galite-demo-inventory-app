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

import kotlin.reflect.KClass

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.kopi.galite.demo.bill.BillForm
import org.kopi.galite.demo.billproduct.BillProductForm
import org.kopi.galite.demo.client.ClientForm
import org.kopi.galite.demo.command.CommandForm
import org.kopi.galite.demo.product.ProductForm
import org.kopi.galite.demo.provider.ProviderForm
import org.kopi.galite.demo.stock.StockForm
import org.kopi.galite.demo.tasks.TasksForm
import org.kopi.galite.demo.taxRule.TaxRuleForm
import org.kopi.galite.visual.db.Modules
import org.kopi.galite.visual.db.UserRights
import org.kopi.galite.visual.db.Users

fun migration4() {
  connect()

  transaction {
    insertIntoModule("1000", "org/kopi/galite/demo/Menu", 0)
    insertIntoModule("1001", "org/kopi/galite/demo/Menu", 1, "1000", ClientForm::class)
    insertIntoModule("2000", "org/kopi/galite/demo/Menu", 100)
    insertIntoModule("2001", "org/kopi/galite/demo/Menu", 101, "2000", CommandForm::class)
    insertIntoModule("3000", "org/kopi/galite/demo/Menu", 200)
    insertIntoModule("3001", "org/kopi/galite/demo/Menu", 201, "3000", ProductForm::class)
    insertIntoModule("4000", "org/kopi/galite/demo/Menu", 300)
    insertIntoModule("4001", "org/kopi/galite/demo/Menu", 301, "4000", BillForm::class)
    insertIntoModule("4010", "org/kopi/galite/demo/Menu", 401, "4000", BillProductForm::class)
    insertIntoModule("5000", "org/kopi/galite/demo/Menu", 500)
    insertIntoModule("5001", "org/kopi/galite/demo/Menu", 501, "5000", StockForm::class)
    insertIntoModule("6000", "org/kopi/galite/demo/Menu", 600)
    insertIntoModule("6001", "org/kopi/galite/demo/Menu", 601, "6000", TaxRuleForm::class)
    insertIntoModule("7000", "org/kopi/galite/demo/Menu", 700)
    insertIntoModule("7001", "org/kopi/galite/demo/Menu", 701, "7000", ProviderForm::class)
    insertIntoModule("8000", "org/kopi/galite/demo/Menu", 800)
    insertIntoModule("8001", "org/kopi/galite/demo/Menu", 801, "8000", TasksForm::class)
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
