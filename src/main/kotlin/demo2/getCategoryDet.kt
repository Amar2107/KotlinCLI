package demo2

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import javax.swing.tree.RowMapper


data class Category(val category_id : Int,
                    val category_name : String,
                    val category_details : String
)

class getCategoryDet{

fun getAllCategory(): ArrayList<Category> {
    val catList = ArrayList<Category>()
    var cat: Category? = null
    var conn : Connection? = ConnectDB().con
    try{

        var sql : String = "SELECT CATEGORY_ID, CATEGORY_NAME,CATEGORY_DETAILS FROM CATEGORY ;"
        var stmt: PreparedStatement = conn!!.prepareStatement(sql)
        var resultset: ResultSet = stmt!!.executeQuery()
        while(resultset!!.next()) {
            cat = Category(
                resultset.getInt("CATEGORY_ID")
                ,resultset.getString("CATEGORY_NAME")
                ,resultset.getString("CATEGORY_DETAILS"))
            catList.add(cat)
        }
   }catch (ex : SQLException)
    {
        ex.printStackTrace()
    }

    return catList
}
}