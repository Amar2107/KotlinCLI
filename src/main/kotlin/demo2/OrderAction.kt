package demo2

import org.omg.CORBA.Object

class OrderAction{

    var amount : Float = 0.0F
    var username : String=""
    var itemDesc : String = ""
    var discount : Int= 0
    constructor(username :String)
    {
        this.username = username
    }
    fun buyItems()
    {
        println("Enetered Buy Item")
        var cart : ArrayList<Cart>? = null
        cart = CartAction(username).getCartItems()
        println("Cart :$cart")
        for(i in 0 until cart!!.size) {
            var item_name = getItemDet().getItemById(cart[i].item_id)?.item_name

            itemDesc = itemDesc+" "+ item_name+" " +
                    " "+ cart[i].item_price.toString()+" "+cart[i].item_amount.toString()
            amount += cart[i].item_price
        }
        if(amount > 1000){
            discount = 500
            amount -= discount }
        var cust : Customer? = getCustomerDet().getCustomer(username)
        val insFlag: Boolean = getOrderDet().buyItem(cust?.customer_id!!,itemDesc,amount,discount)
        if(insFlag) {
            val delFlag = CartAction(username).removeAllCart()
            if(!delFlag)
                println("didn't delete from cart")
        }
        else
            println("problem in inserting data into orders")
    }

    fun checkOrderPresent(customer_id : Int): Boolean {
        return getOrderDet().checkOrderPresent(customer_id)
    }
    fun getCustomersOrder()
    {
        var cust : Customer? = getCustomerDet().getCustomer(username)
        if(!checkOrderPresent(cust?.customer_id!!)) {
            var orderList = getOrderDet().getOrderByCust(cust?.customer_id!!)
            for(i in 0 until orderList.size) {
                println(orderList[i]?.customer_id)
                print(orderList[i]?.item_description + " ")
                println(orderList[i]?.item_price)
                println(orderList[i]?.discount)
            }
        }
        else
            println("no orders placed by user")
    }

    fun getOrderByCust(customer_id: Int)
    {
        if(!checkOrderPresent(customer_id))
        {
            var orderList = getOrderDet().getOrderByCust(customer_id)
            for(i in 0 until orderList.size) {
                println(orderList[i]?.customer_id)
                print(orderList[i]?.item_description + " ")
                println(orderList[i]?.item_price)
                println(orderList[i]?.discount)
            }
        }
        else
            println("no orders placed by user")

    }

}