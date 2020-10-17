/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package demo2

import java.sql.*
import java.util.Properties

class App {
    val greeting: String
        get() {
            return "Hello world."
        }
}


fun main(args: Array<String>) {
    println(App().greeting)
    var username : String =args[0]
    var password : String =args[1]

    if (AuthenticateCust(username,password).Authenticate())
        println("Welcome")
    else
        println("Authentication error")

    if(AuthenticateCust(username,password).checkAdminPriv())
        println("is Admin")

    val cust = getCustomerDet().getCustomer(username)
    val cat = getCategoryDet().getAllCategory()
    println(cat)
    val itm = getItemDet().getAllItem(cat[0].category_id)
    println()
    println(itm[0])

    getCartDet().addToCart(cust?.customer_id!!,itm[0])
    val item = getCartDet().getAllCart(cust?.customer_id!!)
    println(item)

    getOrderDet().buyItem(1,item[0])


}

