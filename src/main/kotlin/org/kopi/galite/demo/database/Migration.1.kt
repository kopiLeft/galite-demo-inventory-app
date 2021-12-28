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

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.kopi.galite.visual.db.Dummy
import org.kopi.galite.visual.db.FAVORITENId
import org.kopi.galite.visual.db.Favorites
import org.kopi.galite.visual.db.GroupParties
import org.kopi.galite.visual.db.GroupRights
import org.kopi.galite.visual.db.Groups
import org.kopi.galite.visual.db.Modules
import org.kopi.galite.visual.db.References
import org.kopi.galite.visual.db.Symbols
import org.kopi.galite.visual.db.UserRights
import org.kopi.galite.visual.db.Users

fun migration1() {
  connect()

  transaction {
    SchemaUtils.create(Modules, UserRights, GroupRights, GroupParties, Symbols,
                       Favorites, Users, Groups, References, Dummy)
    SchemaUtils.createSequence(FAVORITENId)
  }
}
