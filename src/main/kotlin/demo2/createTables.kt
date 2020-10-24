package demo2

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class createTables {
    var conn : Connection? = ConnectDB().con
    fun createCustomerTable(): Boolean {
        try {
            var stmt: PreparedStatement
            var sqlcst: String = "CREATE TABLE IF NOT EXISTS " +
                    "CUSTOMER(CUSTOMER_ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "CUSTOMER_NAME VARCHAR(300) NOT NULL," +
                    "USER_NAME VARCHAR(300) NOT NULL, " +
                    "PASSWORD VARCHAR(300) NOT NULL," +
                    " ADMIN_PRIV INT DEFAULT 0);"
            stmt = conn!!.prepareStatement(sqlcst)

            var cst1 = stmt!!.executeUpdate()
            var sqlcat: String = "CREATE TABLE IF NOT EXISTS " +
                    "CATEGORY(CATEGORY_ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "CATEGORY_NAME VARCHAR(300) NOT NULL," +
                    "CATEGORY_DETAILS VARCHAR(300) NOT NULL);"
            stmt = conn!!.prepareStatement(sqlcat)

            var cat1 = stmt!!.executeUpdate()
            var sqlitem: String = "CREATE TABLE IF NOT EXISTS " +
                    "ITEM(ITEM_ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "ITEM_NAME VARCHAR(300) NOT NULL," +
                    "ITEM_PRICE FLOAT NOT NULL, " +
                    "ITEM_DESCRIPTION VARCHAR(300) NOT NULL," +
                    "CATEGORY_ID INT NOT NULL);"
            stmt = conn!!.prepareStatement(sqlitem)
            var itm1 = stmt!!.executeUpdate()

            var sqlcart: String = "CREATE TABLE IF NOT EXISTS " +
                    "CART(CART_ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "ITEM_ID INT NOT NULL," +
                    "ITEM_PRICE FLOAT NOT NULL," +
                    "ITEM_AMOUNT INT NOT NULL," +
                    "ITEM_STATUS VARCHAR(300), "+
                    "CUSTOMER_ID INT NOT NULL);"
            stmt = conn!!.prepareStatement(sqlcart)
            var cart1 = stmt!!.executeUpdate()

            var sqlorders: String = "CREATE TABLE IF NOT EXISTS " +
                    "ORDERS (ORDER_ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "ITEM_DESCRIPTION VARCHAR(500) NOT NULL," +
                    "ITEM_PRICE FLOAT NOT NULL," +
                    "DISCOUNT INT ,"+
                    "CUSTOMER_ID INT NOT NULL);"
            stmt = conn!!.prepareStatement(sqlorders)
            var order1 = stmt!!.executeUpdate()

            if(cst1 == 1 && cat1 == 1 && itm1 == 1 && cart1 == 1 && order1 == 1)
               return true

        }catch (ex: SQLException)
        {ex.printStackTrace()}
        return false
    }
}