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


class getItemDet{



    fun getAllItem(category_id: Int): ArrayList<Item> {
        val itmList = ArrayList<Item>()
        var itm: Item? = null
        var conn : Connection? = ConnectDB().con
        try{

            var sql : String = "SELECT ITEM_ID, ITEM_NAME,ITEM_PRICE," +
                    "ITEM_DESCRIPTION,CATEGORY_ID FROM ITEM WHERE CATEGORY_ID = ? ;"
            var stmt: PreparedStatement = conn!!.prepareStatement(sql)
            stmt.setInt(1,category_id)
            var resultset: ResultSet = stmt!!.executeQuery()
            while(resultset!!.next()) {
                itm= Item(
                        resultset.getInt("ITEM_ID")
                        ,resultset.getString("ITEM_NAME")
                        ,resultset.getFloat("ITEM_PRICE")
                        ,resultset.getString("ITEM_DESCRIPTION")
                        ,resultset.getInt("CATEGORY_ID")
                )
                itmList.add(itm)
            }
        }catch (ex : SQLException)
        {
            ex.printStackTrace()
        }

        return itmList
    }

}