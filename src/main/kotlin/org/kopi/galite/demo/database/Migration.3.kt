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

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.CurrentTimestamp
import org.jetbrains.exposed.sql.nextIntVal
import org.jetbrains.exposed.sql.transactions.transaction
import org.kopi.galite.visual.db.Users
import org.kopi.galite.visual.type.Week

fun migration3() {
  connect()

  transaction {
    Users.insert {
      it[uc] = 0
      it[ts] = 0
      it[shortName] = DEMO_USER
      it[name] = "administrator"
      it[character] = DEMO_USER
      it[active] = true
      it[createdOn] = CurrentTimestamp()
      it[createdBy] = 1
      it[changedOn] = CurrentTimestamp()
      it[changedBy] = 1
    }
    addClients()
    addTaxRules()
    addProducts()
    addSales()
    addFourns()
    addStocks()
    addCmds()
    addBillPrdts()
    addBills()
    addTasks()
  }
}
fun addClients() {
  addClient(1, "Oussama", "Mellouli", "Marsa, tunis", 38, "example@mail", "Tunisia", "Tunis", 2001)
  addClient(2, "Mohamed", "Salah", "10,Rue Lac", 56, "example@mail", "Tunisia", "Megrine", 2001)
  addClient(3, "Khaled", "Guesmi", "14,Rue Mongi Slim", 35, "example@mail", "Tunisia", "Tunis", 6000)
  addClient(4, "Ahmed", "Bouaroua", "10,Rue du Lac", 22, "example@mail", "Tunisia", "Mourouj", 5003)
}

fun addClient(id: Int,
              firstName: String,
              lastName: String,
              address: String,
              age: Int,
              email: String,
              country: String,
              city: String,
              zipcode: Int,
              active: Boolean = true) {
  Client.insert {
    it[idClt] = id
    it[firstNameClt] = firstName
    it[lastNameClt] = lastName
    it[addressClt] = address
    it[ageClt] = age
    it[mail] = email
    it[countryClt] = country
    it[cityClt] = city
    it[zipCodeClt] = zipcode
    it[activeClt] = active
  }
}

fun addProducts() {
  addProduct(0, "description Product 0", 1, "tax 1", "Men", "Supplier 0", BigDecimal("263"))
  addProduct(1, "description Product 1", 2, "tax 2", "Men","Supplier 0", BigDecimal("314"))
  addProduct(2, "description Product 2", 3, "tax 2", "Women","Supplier 0", BigDecimal("180"))
  addProduct(3, "description Product 3", 1, "tax 3", "Children","Supplier 0", BigDecimal("65"))
}

fun addSales() {
  addSale(1, 0, 1, 1)
  addSale(1, 1, 1, 2)
  addSale(1, 2, 2, 6)
  addSale(1, 3, 3, 3)
}

fun addProduct(id: Int, description: String, category: Int, taxName: String, department: String, supplier: String, price: BigDecimal) {
  Product.insert {
    it[idPdt] = id
    it[Product.description] = description
    it[Product.department] = department
    it[Product.supplier] = supplier
    it[Product.category] = category
    it[Product.taxName] = taxName
    it[Product.price] = price
  }
}

fun addSale(client: Int, product: Int, qty: Int, i: Int) {
  Purchase.insert {
    it[id] = i
    it[idClt] = client
    it[idPdt] = product
    it[quantity] = qty
  }
}

fun addFourns() {
  addFourn(0, "Radhia Jouini", 21203506, "address provider 1", "Provider 0 description", 2000)
  addFourn(1, "Sarra Boubaker", 99806234, "address provider 2", "Provider 1 description", 3005)
  addFourn(2, "Hamida Zaoueche", 55896321, "address provider 3", "Provider 2 description", 6008)
  addFourn(3, "Seif Markzi", 23254789, "address provider 4", "Provider 3 description", 2006)
}

fun addFourn(id: Int, name: String, tel: Int, address: String, description: String, postalCode: Int) {
  Provider.insert {
    it[idProvider] = id
    it[nameProvider] = name
    it[Provider.tel] = tel
    it[Provider.address] = address
    it[Provider.description] = description
    it[Provider.zipCode] = postalCode
  }
}

fun addTaxRules() {
  addTaxRule(0, "tax 1", 19)
  addTaxRule(1, "tax 2", 22)
  addTaxRule(2, "tax 3", 13)
  addTaxRule(3, "tax 4", 9)
  addTaxRule(4, "tax 5", 20, "<strong>ABC</strong>")
}

fun addTaxRule(id: Int, taxName: String, rate: Int, information: String? = null) {
  TaxRule.insert {
    it[idTaxe] = id
    it[TaxRule.taxName] = taxName
    it[TaxRule.rate] = rate
    it[informations] = information
  }
}

fun addBills() {
  addBill(0, "Bill address 0", LocalDate.parse("2018-09-13"), BigDecimal("3129.7"), 0)
  addBill(1, "Bill address 1", LocalDate.parse("2020-02-16"), BigDecimal("1149.24"), 1)
  addBill(2, "Bill address 2", LocalDate.parse("2019-05-13"), BigDecimal("219.6"), 2)
  addBill(3, "Bill address 3", LocalDate.parse("2019-01-12"), BigDecimal("146.9"), 3)
}

fun addBill(num: Int, address: String, date: LocalDate, amount: BigDecimal, ref: Int) {
  Bill.insert {
    it[numBill] = num
    it[addressBill] = address
    it[dateBill] = date
    it[amountWithTaxes] = amount
    it[refCmd] = ref
  }
}

fun addTasks() {
  val currentWeek = Week.now()

  addTask(currentWeek.getDate(1), LocalTime.of(8, 0, 0), LocalTime.of(10, 30, 0), "Conception", "desc 2")
  addTask(currentWeek.getDate(1), LocalTime.of(14, 0, 0), LocalTime.of(16, 0, 0), "Codage", "desc 2")
  addTask(currentWeek.getDate(4), LocalTime.of(11, 0, 0), LocalTime.of(12, 30, 0), "Validation", "desc 2")
}

fun addTask(date: LocalDate, from: LocalTime, to: LocalTime, description1: String, description2: String) {
  Task.insert {
    it[id] = TASKId.nextIntVal()
    it[Task.date] = date
    it[Task.from] = LocalDateTime.of(date, from).atZone(ZoneId.systemDefault()).toInstant()
    it[Task.to] = LocalDateTime.of(date, to).atZone(ZoneId.systemDefault()).toInstant()
    it[Task.description1] = description1
    it[Task.description2] = description2
  }
}

fun addStocks() {
  addStock(0, 0, 50)
  addStock(1, 1, 100)
  addStock(2, 2, 50)
  addStock(3, 3, 20)
}

fun addStock(id: Int, idStck: Int, minAlerte: Int) {
  Stock.insert {
    it[idStckPdt] = id
    it[idStckProv] = idStck
    it[minAlert] = minAlerte
  }
}

fun addCmds() {
  addCmd(0, 1, LocalDate.parse("2020-01-03"), "check", "in preparation")
  addCmd(1, 1, LocalDate.parse("2020-01-01"), "check", "available")
  addCmd(2, 2, LocalDate.parse("2021-05-03"), "bank card", "delivered")
  addCmd(3, 3, LocalDate.parse("2021-05-13"), "cash", "canceled")
}

fun addCmd(num: Int, client: Int, date: LocalDate, payment: String, status: String) {
  Command.insert {
    it[numCmd] = num
    it[idClt] = client
    it[dateCmd] = date
    it[paymentMethod] = payment
    it[statusCmd] = status
  }
}

fun addBillPrdts() {
  addBillPrdt(0, 10, BigDecimal("2630"), BigDecimal("3129.7"))
  addBillPrdt(1, 3, BigDecimal("942"), BigDecimal("1149.24"))
  addBillPrdt(2, 1, BigDecimal("180"), BigDecimal("219.6"))
  addBillPrdt(3, 2, BigDecimal("130"), BigDecimal("146.9"))
}

fun addBillPrdt(id: Int, quantity: Int, amount: BigDecimal, amountWithTaxes: BigDecimal) {
  BillProduct.insert {
    it[idBPdt] = id
    it[BillProduct.quantity] = quantity
    it[BillProduct.amount] = amount
    it[BillProduct.amountWithTaxes] = amountWithTaxes
  }
}
