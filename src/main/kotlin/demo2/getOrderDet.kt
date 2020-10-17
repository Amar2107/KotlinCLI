package demo2

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

data class Orders(
        val item_id : Int,
        val item_price : Float,
        val item_amount : Int,
        val item_status : String,
        val discount : Float,
        val customer_id : Int
)

var conn : Connection? = ConnectDB().con

class getOrderDet{

fun buyItem(customer_id: Int,cart: Cart)
{
        try {
            var sql: String = "INSERT INTO ORDERS(ITEM_ID,ITEM_PRICE,ITEM_AMOUNT,ITEM_STATUS,DISCOUNT,CUSTOMER_ID) " +
                    "VALUES(?,?,?,?,?,?);"
            var stmt: PreparedStatement = conn!!.prepareStatement(sql)
            stmt.setInt(1, cart.item_id)
            stmt.setFloat(2, cart.item_price)
            stmt.setInt(3, cart.item_amount)
            stmt.setString(4, "Order placed")
            stmt.setInt(5, 0)
            stmt.setInt(6, customer_id)
            stmt!!.executeUpdate()

            var sqlup: String = "DELETE FROM CART WHERE CUSTOMER_ID = ? AND ITEM_ID = ?;"
            var stmtup: PreparedStatement = conn!!.prepareStatement(sqlup)
            stmtup.setInt(2, customer_id)
            stmtup.setInt(3, cart.item_id)
            stmtup.executeUpdate()

        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
}


fun getTotalAmt(customer_id: Int)
{
    val orderList = ArrayList<Orders>()
    var order: Orders? = null
    try{
        var sql : String = "SELECT ITEM_ID,ITEM_PRICE,ITEM_AMOUNT,ITEM_STATUS,DISCOUNT,CUSTOMER_ID FROM ORDERS" +
                " WHERE CUSTOMER_ID=?;"
        var stmt: PreparedStatement = conn!!.prepareStatement(sql)
        stmt.setInt(1,customer_id)
        var resultset: ResultSet = stmt!!.executeQuery()
        while(resultset!!.next()) {
            order = Orders(
                    resultset.getInt("ITEM_ID")
                    ,resultset.getFloat("ITEM_PRICE")
                    ,resultset.getInt("ITEM_AMOUNT")
                    ,resultset.getString("ITEM_STATUS")
                    ,resultset.getFloat("DISCOUNT")
                     ,resultset.getInt("CUSTOMER_ID")
            )
            orderList.add(order)
        }
    }catch (ex : SQLException)
    {
        ex.printStackTrace()
    }
}
}