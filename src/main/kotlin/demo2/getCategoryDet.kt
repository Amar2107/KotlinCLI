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

    var conn : Connection? = ConnectDB().con



fun insertCategory(category_name: String, category_details: String){
    try {
        var sql: String = "INSERT INTO CATEGORY(CATEGORY_NAME, CATEGORY_DETAILS) " +
                "VALUES(?,?);"
        var stmt: PreparedStatement = conn!!.prepareStatement(sql)
        stmt.setString(1, category_name)
        stmt.setString(2, category_details)
        stmt!!.executeUpdate()
    } catch (ex: SQLException) {
        ex.printStackTrace()
    }
}

fun getAllCategory(): ArrayList<Category> {
    val catList = ArrayList<Category>()
    var cat: Category? = null

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

    fun getCategoryByName(category_name : String): Category? {
        var cat: Category? = null
        try{
            var sql : String = "SELECT CATEGORY_ID, CATEGORY_NAME,CATEGORY_DETAILS FROM CATEGORY WHERE CATEGORY_NAME =?;"
            var stmt: PreparedStatement = conn!!.prepareStatement(sql)
            stmt.setString(1, category_name)
            var resultset: ResultSet = stmt!!.executeQuery()
            while(resultset!!.next()) {
                cat = Category(
                        resultset.getInt("CATEGORY_ID")
                        ,resultset.getString("CATEGORY_NAME")
                        ,resultset.getString("CATEGORY_DETAILS"))
            }
        }catch (ex : SQLException)
        {
            ex.printStackTrace()
        }

        return cat
    }

}