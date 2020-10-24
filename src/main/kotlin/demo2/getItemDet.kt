package demo2

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

data class Item(
        val item_id : Int,
        val item_name : String,
        val item_price : Float,
        val item_description : String,
        val category_id : Int
)


class getItemDet {

    var conn : Connection? = ConnectDB().con

    fun insertItem(Item_Name: String, Item_Price: Float, Item_Description: String, Category_Id: Int) {
        try {
            var sql: String = "INSERT INTO ITEM(ITEM_NAME, ITEM_PRICE, ITEM_DESCRIPTION,CATEGORY_ID) " +
                    "VALUES(?,?,?,?);"
            var stmt: PreparedStatement = conn!!.prepareStatement(sql)

            stmt.setString(1, Item_Name)
            stmt.setFloat(2, Item_Price)
            stmt.setString(3, Item_Description)
            stmt.setInt(4, Category_Id)
            stmt!!.executeUpdate()
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }


    fun getAllItem(category_id: Int): ArrayList<Item> {
        val itmList = ArrayList<Item>()
        var itm: Item? = null
        try {
            var sql: String = "SELECT ITEM_ID, ITEM_NAME,ITEM_PRICE," +
                    "ITEM_DESCRIPTION,CATEGORY_ID FROM ITEM WHERE CATEGORY_ID = ? ;"
            var stmt: PreparedStatement = conn!!.prepareStatement(sql)
            stmt.setInt(1, category_id)
            var resultset: ResultSet = stmt!!.executeQuery()
            while (resultset!!.next()) {
                itm = Item(
                        resultset.getInt("ITEM_ID"),
                        resultset.getString("ITEM_NAME"),
                        resultset.getFloat("ITEM_PRICE"),
                        resultset.getString("ITEM_DESCRIPTION"),
                        resultset.getInt("CATEGORY_ID")
                )
                itmList.add(itm)
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
        return itmList
    }

    fun getItemById(item_id: Int): Item? {
        var itm: Item? = null
            try {
                var sql: String = "SELECT ITEM_ID, ITEM_NAME,ITEM_PRICE," +
                        "ITEM_DESCRIPTION,CATEGORY_ID FROM ITEM WHERE ITEM_ID = ? ;"
                var stmt: PreparedStatement = conn!!.prepareStatement(sql)
                stmt.setInt(1, item_id)
                var resultset: ResultSet = stmt!!.executeQuery()
                while (resultset!!.next()) {
                    itm = Item(
                            resultset.getInt("ITEM_ID"),
                            resultset.getString("ITEM_NAME"),
                            resultset.getFloat("ITEM_PRICE"),
                            resultset.getString("ITEM_DESCRIPTION"),
                            resultset.getInt("CATEGORY_ID")
                    )

                }
            } catch (ex: SQLException) {
                ex.printStackTrace()
            }
    return itm
    }
    fun getItemByName(item_name: String): Item? {
        var itm: Item? = null
        try {
            var sql: String = "SELECT ITEM_ID, ITEM_NAME,ITEM_PRICE," +
                    "ITEM_DESCRIPTION,CATEGORY_ID FROM ITEM WHERE ITEM_NAME = ? ;"
            var stmt: PreparedStatement = conn!!.prepareStatement(sql)
            stmt.setString(1, item_name)
            var resultset: ResultSet = stmt!!.executeQuery()
            while (resultset!!.next()) {
                itm = Item(
                        resultset.getInt("ITEM_ID"),
                        resultset.getString("ITEM_NAME"),
                        resultset.getFloat("ITEM_PRICE"),
                        resultset.getString("ITEM_DESCRIPTION"),
                        resultset.getInt("CATEGORY_ID")
                )

            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
        return itm
    }

    fun checkItemPresent(item_id : Int): Boolean? {
        try{

            var sql : String = "SELECT ITEM_ID" +
                    " FROM ITEM WHERE ITEM_ID = ?;"
            var stmt: PreparedStatement = conn!!.prepareStatement(sql)
            stmt.setInt(1,item_id)
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