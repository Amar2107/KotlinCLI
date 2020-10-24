package demo2

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException


data class  Customer(val customer_id: Int,
                     val customer_name : String,
                     val username: String,
                     val password : String,
                     val admin_priv : Int
)


class getCustomerDet{

    var conn : Connection? = ConnectDB().con
fun getCustomer(username : String): Customer? {
    var cust: Customer? = null
    try{

        var sql : String = "SELECT USER_NAME,CUSTOMER_ID," +
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
                ,resultset.getInt("ADMIN_PRIV"))
        }

    }catch (ex : SQLException)
    {
        ex.printStackTrace()
    }
    return cust
 }

    fun getCustomerId(username : String): Boolean? {
        try{

            var sql : String = "SELECT CUSTOMER_ID" +
                    " FROM CUSTOMER WHERE USER_NAME = ?;"
            var stmt: PreparedStatement = conn!!.prepareStatement(sql)
            stmt.setString(1,username)
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


    fun registerCustomer(custname: String, username: String, password: String): Boolean {
        try {
            var sql: String = "INSERT INTO CUSTOMER(CUSTOMER_NAME,USER_NAME,PASSWORD)" +
                    "VALUES(?,?,?);"
            var stmt: PreparedStatement = conn!!.prepareStatement(sql)
            stmt.setString(1, custname)
            stmt.setString(2, username)
            stmt.setString(3, password)
            stmt!!.executeUpdate()
        } catch (ex: SQLException) {
            ex.printStackTrace()
            return false
        }
        return true
    }

}