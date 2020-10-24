package demo2

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

data class Orders(
        val item_id : Int,
        val item_description : String,
        val item_price : Float,
        val discount : Float,
        val customer_id : Int
)

class getOrderDet{

var conn : Connection? = ConnectDB().con

fun buyItem(customer_id: Int,item_desc : String,item_price : Float ,discount: Int): Boolean {
    println("Entered Order Buy Item")
        try {
            var sql: String = "INSERT INTO ORDERS(ITEM_DESCRIPTION,ITEM_PRICE,DISCOUNT,CUSTOMER_ID) " +
                    "VALUES(?,?,?,?);"
            var stmt: PreparedStatement = conn!!.prepareStatement(sql)
            stmt.setString(1,item_desc)
            stmt.setFloat(2,item_price)
            stmt.setInt(3, discount)
            stmt.setInt(4, customer_id)
            stmt!!.executeUpdate()
        } catch (ex: SQLException) {
            return false
            ex.printStackTrace() }
    return true
}


fun getOrderByCust(customer_id: Int): ArrayList<Orders> {
    val orderList = ArrayList<Orders>()
    var order: Orders? = null
    try{
        var sql : String = "SELECT ORDER_ID,ITEM_DESCRIPTION,ITEM_PRICE,DISCOUNT,CUSTOMER_ID FROM ORDERS" +
                " WHERE CUSTOMER_ID=?;"
        var stmt: PreparedStatement = conn!!.prepareStatement(sql)
        stmt.setInt(1,customer_id)
        var resultset: ResultSet = stmt!!.executeQuery()
        while(resultset!!.next()) {
            order = Orders(
                    resultset.getInt("ORDER_ID")
                    ,resultset.getString("ITEM_DESCRIPTION")
                    ,resultset.getFloat("ITEM_PRICE")
                    ,resultset.getFloat("DISCOUNT")
                     ,resultset.getInt("CUSTOMER_ID")
            )
            orderList.add(order)
        }
    }catch (ex : SQLException) {  ex.printStackTrace() }
    return orderList
}

    fun checkOrderPresent(customer_id: Int): Boolean {
        var sql : String = "SELECT ORDER_ID,ITEM_DESCRIPTION,ITEM_PRICE,DISCOUNT,CUSTOMER_ID FROM ORDERS" +
                " WHERE CUSTOMER_ID=?;"
        var stmt: PreparedStatement = conn!!.prepareStatement(sql)
        stmt.setInt(1,customer_id)
        var resultset: ResultSet = stmt!!.executeQuery()
        if(!resultset.isBeforeFirst){
            return true
        }
        return false
    }






}