package demo2

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException


data class  Customer(val customer_id: Int,
                     val customer_name : String,
                     val username: String,
                     val password : String,
                     val cart_id : Int,
                     val order_id : Int,
                     val admin_priv : Int
)


class getCustomerDet{

fun getCustomer(username : String): Customer? {
    var cust: Customer? = null
    var conn : Connection? = ConnectDB().con
    try{

        var sql : String = "SELECT USER_NAME, CART_ID, ORDER_ID,CUSTOMER_ID," +
                "PASSWORD, CUSTOMER_NAME,ADMIN_PRIV FROM CUSTOMER WHERE USER_NAME = ?;"
        var stmt: PreparedStatement = conn!!.prepareStatement(sql)
        stmt.setString(1,username)
        var resultset: ResultSet = stmt!!.executeQuery()
        while(resultset!!.next()) {
            cust = Customer(
                resultset.getInt("CUSTOMER_ID")
                ,resultset.getString("CUSTOMER_NAME")
                ,resultset.getString("USER_NAME")
                ,resultset.getString("PASSWORD")
                ,resultset.getInt("CART_ID")
                ,resultset.getInt("ORDER_ID")
                ,resultset.getInt("ADMIN_PRIV"))
        }

    }catch (ex : SQLException)
    {
        ex.printStackTrace()
    }
    return cust
}
}