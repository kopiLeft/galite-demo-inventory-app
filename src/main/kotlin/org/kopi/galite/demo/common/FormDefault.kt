/*
 * Copyright (c) 2013-2021 kopiLeft Services SARL, Tunis TN
 * Copyright (c) 1990-2021 kopiRight Managed Solutions GmbH, Wien AT
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
package org.kopi.galite.demo.common

import org.kopi.galite.visual.dsl.common.Actor
import org.kopi.galite.visual.dsl.common.Command
import org.kopi.galite.visual.dsl.common.Icon
import org.kopi.galite.visual.dsl.common.Menu
import org.kopi.galite.visual.dsl.form.Block
import org.kopi.galite.visual.dsl.form.Form
import org.kopi.galite.visual.dsl.form.Key
import org.kopi.galite.visual.form.Commands

interface IFormDefault {
  // -------------------------------------------------------------------
  // MENUS
  // -------------------------------------------------------------------
  val file: Menu
  val edit: Menu
  val action: Menu

  // -------------------------------------------------------------------
  // ACTORS
  // -------------------------------------------------------------------
  val quit: Actor
  val _break: Actor
  val autofill: Actor
  val editItem: Actor
  val editItemS: Actor
  val searchOperator: Actor
  val insertLine: Actor
  val deleteLine: Actor
  val menuQuery: Actor
  val serialQuery: Actor
  val insertMode: Actor
  val save: Actor
  val delete: Actor
  val createReport: Actor
  val createDynamicReport: Actor
  val help: Actor
  val showHideFilter: Actor
  val report: Actor

  // -------------------------------------------------------------------
  // FORM-LEVEL COMMANDS
  // -------------------------------------------------------------------
  val resetForm: Command
  val quitForm: Command
  val helpForm: Command

  // -------------------------------------------------------------------
  // BLOCK-LEVEL COMMANDS
  // -------------------------------------------------------------------
  val Block.breakCmd: Command
  val Block.recursiveQueryCmd: Command
  val Block.menuQueryCmd: Command
  val Block.queryMoveCmd: Command
  val Block.serialQueryCmd: Command
  val Block.insertModeCmd: Command
  val Block.saveCmd: Command
  val Block.deleteCmd: Command
  val Block.insertLineCmd: Command
  val Block.showHideFilterCmd: Command
}

open class FormDefaultImpl : IFormDefault, Form() {
  override val title: String = ""

  // --------------------MENUS-----------------
  final override val file = menu("File")
  final override val edit = menu("Edit")
  final override val action = menu("Action")

  // --------------------ACTORS----------------
  override val quit = actor(
    ident = "Quit",
    menu = file,
    label = "Quit",
    help = "Quit",
  ) {
    key = Key.ESCAPE
    icon = Icon.QUIT
  }

  override val _break = actor(
    ident = "Break",
    menu = file,
    label = "Break",
    help = "Break",
  ) {
    key = Key.F3
    icon = Icon.BREAK
  }

  override val autofill = actor(
    ident = "Autofill",
    menu = edit,
    label = "Autofill",
    help = "Autofill",
  ) {
    key = Key.F2
  }

  override val editItem = actor(
    ident = "EditItem",
    menu = edit,
    label = "EditItem",
    help = "EditItem",
  ) {
    key = Key.SHIFT_F2
  }

  override val editItemS = actor(
    ident = "EditItem_S",
    menu = edit,
    label = "EditItem_S",
    help = "EditItem_S",
  ) {
    key = Key.F2
  }

  override val searchOperator = actor(
    ident = "SearchOperator",
    menu = edit,
    label = "SearchOperator",
    help = "SearchOperator",
  ) {
    key = Key.F5
    icon = Icon.SEARCH_OP
  }

  override val insertLine = actor(
    ident = "InsertLine",
    menu = edit,
    label = "InsertLine",
    help = "InsertLine",
  ) {
    key = Key.F4
    icon = Icon.INSERT_LINE
  }

  override val deleteLine = actor(
    ident = "DeleteLine",
    menu = edit,
    label = "DeleteLine",
    help = "DeleteLine",
  ) {
    key = Key.F5
    icon = Icon.DELETE_LINE
  }

  override val menuQuery = actor(
    ident = "MenuQuery",
    menu = action,
    label = "MenuQuery",
    help = "MenuQuery",
  ) {
    key = Key.F8
    icon = Icon.MENU_QUERY
  }

  override val serialQuery = actor(
    ident = "SerialQuery",
    menu = action,
    label = "SerialQuery",
    help = "SerialQuery",
  ) {
    key = Key.F6
    icon = Icon.SERIAL_QUERY
  }

  override val insertMode = actor(
    ident = "InsertMode",
    menu = action,
    label = "InsertMode",
    help = "InsertMode",
  ) {
    key = Key.F4
    icon = Icon.INSERT
  }

  override val save = actor(
    ident = "Save",
    menu = action,
    label = "Save",
    help = "Save",
  ) {
    key = Key.F7
    icon = Icon.SAVE
  }

  override val delete = actor(
    ident = "Delete",
    menu = action,
    label = "Delete",
    help = "Delete",
  ) {
    key = Key.F5
    icon = Icon.DELETE
  }

  override val createReport = actor(
    ident = "CreateReport",
    menu = action,
    label = "CreateReport",
    help = "CreateReport",
  ) {
    key = Key.F8
    icon = Icon.PREVIEW
  }

  override val createDynamicReport = actor(
    ident = "CreateDynamicReport",
    menu = action,
    label = "CreateDynamicReport",
    help = "CreateDynamicReport",
  ) {
    key = Key.F11
    icon = Icon.PREVIEW
  }

  override val help = actor(
    ident = "Help",
    menu = action,
    label = "Help",
    help = "Help",
  ) {
    key = Key.F1
    icon = Icon.HELP
  }

  override val showHideFilter = actor(
    ident = "ShowHideFilter",
    menu = action,
    label = "ShowHideFilter",
    help = "ShowHideFilter",
  ) {
    key = Key.SHIFT_F12
    icon = Icon.SEARCH_OP
  }

  override val report = actor(
    ident = "report",
    menu = action,
    label = "CreateReport",
    help = "Create report",
  ) {
    key = Key.F8
    icon = Icon.REPORT
  }

  // -------------------------------------------------------------------
  // FORM-LEVEL COMMANDS
  // -------------------------------------------------------------------
  override val resetForm = command(item = _break) {
    resetForm()
  }

  override val quitForm = command(item = quit) {
    quitForm()
  }

  override val helpForm = command(item = help) {
    showHelp()
  }

  // -------------------------------------------------------------------
  // BLOCK-LEVEL COMMANDS
  // -------------------------------------------------------------------
  override val Block.breakCmd: Command
    get() = command(item = _break) {
      resetBlock()
    }

  override val Block.recursiveQueryCmd: Command
    get() = command(item = menuQuery) {
      Commands.recursiveQuery(block)
    }

  override val Block.menuQueryCmd: Command
    get() = command(item = menuQuery) {
      Commands.menuQuery(block)
    }

  override val Block.queryMoveCmd: Command
    get() = command(item = menuQuery) {
      Commands.queryMove(block)
    }

  override val Block.serialQueryCmd: Command
    get() = command(item = serialQuery) {
      Commands.serialQuery(block)
    }

  override val Block.insertModeCmd: Command
    get() = command(item = insertMode) {
      insertMode()
    }

  override val Block.saveCmd: Command
    get() = command(item = save) {
      saveBlock()
    }
  override val Block.deleteCmd: Command
    get() = command(item = delete) {
      deleteBlock()
    }

  override val Block.insertLineCmd: Command
    get() = command(item = insertLine) {
      insertLine()
    }

  override val Block.showHideFilterCmd: Command
    get() = command(item = showHideFilter) {
      showHideFilter()
    }
}
