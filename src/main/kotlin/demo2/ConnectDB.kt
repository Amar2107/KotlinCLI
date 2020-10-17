package demo2

import java.sql.*
import java.util.*


class ConnectDB {


    internal var username : String = "root"
    internal var password : String = "neonowl"

     val con: Connection?
        get() {
        var conn : Connection? = null
        var stmt: Statement? = null
        var resultset: ResultSet? = null
        val connectionProps = Properties()
        connectionProps.put("user", username)
        connectionProps.put("password", password)
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(
                "jdbc:" + "mysql" + "://" +
                        "localhost" +
                        ":" + "3306" + "/" +
                        "db1",
                connectionProps
            )


        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }
            return conn;
    }


}