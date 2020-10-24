package demo2

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

data class Cart(
        val cart_id : Int,
        val item_id : Int,
        val item_price :Float,
        val item_amount : Int,
        val item_status : String,
        val customer_id : Int
)

class getCartDet{

    var conn : Connection? = ConnectDB().con

    var amount : Int = 0

    fun itemAmount(item_id: Int,customer_id: Int): Int {
        try {
            var sql: String = "SELECT distinct(ITEM_AMOUNT) FROM CART WHERE CUSTOMER_ID = ? AND ITEM_ID = ?;"
            var stmt: PreparedStatement = conn!!.prepareStatement(sql)
            stmt.setInt(1, customer_id)
            stmt.setInt(2, item_id)
            var rs: ResultSet = stmt!!.executeQuery()
            while (rs!!.next()) {
                amount = rs.getInt("ITEM_AMOUNT")
            }
        }catch (ex : SQLException)
        {ex.printStackTrace()}
        return amount
    }

    fun updateItemAmount(customer_id : Int,item_id : Int, amount : Int): Boolean {

     try{
                var sqlup: String = "UPDATE CART SET ITEM_AMOUNT = ? WHERE CUSTOMER_ID = ? AND ITEM_ID = ?;"
                var stmtup: PreparedStatement = conn!!.prepareStatement(sqlup)
                stmtup.setInt(1, (amount))
                stmtup.setInt(2, customer_id)
                stmtup.setInt(3, item_id)
                stmtup.executeUpdate()
                return true
        }catch (ex : SQLException)
        {ex.printStackTrace()}
        return false
    }

    fun addToCart(customer_id: Int,item_id: Int) {

        var item = getItemDet().getItemById(item_id)
            try {
                var sql: String = "INSERT INTO CART(ITEM_ID,ITEM_PRICE,ITEM_AMOUNT,ITEM_STATUS,CUSTOMER_ID)" +
                        "VALUES(?,?,?,?,?);"
                var stmt: PreparedStatement = conn!!.prepareStatement(sql)
                stmt.setInt(1, item?.item_id!!)
                stmt.setFloat(2, item?.item_price!!)
                stmt.setInt(3, 1)
                stmt.setString(4, "In Cart")
                stmt.setInt(5, customer_id)
                stmt!!.executeUpdate()
            } catch (ex: SQLException) {
                ex.printStackTrace()
            }

    }

    fun getAllCart(customer_id: Int): ArrayList<Cart> {

        val cartList = ArrayList<Cart>()
        var cart: Cart? = null
        try{
            var sql : String = "SELECT CART_ID, ITEM_ID, ITEM_PRICE, ITEM_AMOUNT, ITEM_STATUS,CUSTOMER_ID FROM CART" +
                    " WHERE CUSTOMER_ID=?;"
            var stmt: PreparedStatement = conn!!.prepareStatement(sql)
            stmt.setInt(1,customer_id)
            var resultset: ResultSet = stmt!!.executeQuery()
            while(resultset!!.next()) {
                cart = Cart(
                        resultset.getInt("CART_ID")
                        ,resultset.getInt("ITEM_ID")
                        ,resultset.getFloat("ITEM_PRICE")
                        ,resultset.getInt("ITEM_AMOUNT")
                        ,resultset.getString("ITEM_STATUS")
                        ,resultset.getInt("CUSTOMER_ID")
                )
                cartList.add(cart)
        }
        }catch (ex : SQLException)
        {
            ex.printStackTrace()
        }
        return cartList
    }



    fun removeFromCart(customer_id: Int, item_id: Int)
    {
        try{
        var sqlup: String = "DELETE FROM CART WHERE CUSTOMER_ID = ? AND ITEM_ID = ?;"
        var stmtup: PreparedStatement = conn!!.prepareStatement(sqlup)
        stmtup.setInt(1, customer_id)
        stmtup.setInt(2, item_id)
        stmtup.executeUpdate()
        }catch (ex : SQLException)
        {
            ex.printStackTrace()
        }
    }
    fun removeAllFromCart(customer_id: Int): Boolean {   println("Entered Remove all cart")
        try {
            println("Enetered remove all items")
            var sqlup: String = "DELETE FROM CART WHERE CUSTOMER_ID = ? ;"
            var stmtup: PreparedStatement = conn!!.prepareStatement(sqlup)
            stmtup.setInt(1, customer_id)
            stmtup.executeUpdate()
        }catch (ex : SQLException)
    {
        return false
        ex.printStackTrace()
    }
return true
}


    fun isCartEmpty(customer_id: Int): Boolean {
        try{

            var sql : String = "SELECT CART_ID " +
                    " FROM CART WHERE CUSTOMER_ID = ?;"
            var stmt: PreparedStatement = conn!!.prepareStatement(sql)
            stmt.setInt(1,customer_id)
            var resultset: ResultSet = stmt!!.executeQuery()
            if(!resultset.isBeforeFirst){
                return true
            }
        }catch (ex : SQLException)
        {
            ex.printStackTrace()
        }
        return false
    }


}