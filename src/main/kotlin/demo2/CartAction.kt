package demo2

class CartAction
{
    var username: String
    var cust : Customer? = null
    var item_id : Int = 0

    constructor(username : String)
    {
        this.username = username
        cust = getCustomerDet().getCustomer(username)
    }

    fun printCartItems()
    {
        if(!isCartEmpty()) {
            var cart = getCartDet().getAllCart(cust?.customer_id!!)
            for (i in 0 until cart.size)
                println(cart.get(i))
        }
        else
            println("cart is empty")
    }

    fun getUserByCart(customer_id :Int) {
        if(!checkCartForCust(customer_id)) {
            var cartList=  getCartDet().getAllCart(customer_id)
            for(i in 0 until cartList.size)
                print(cartList)
        }
        else
            print("cart is empty")
    }
    fun getCartItems(): ArrayList<Cart>? {
        if(!isCartEmpty())
        {
            return getCartDet().getAllCart(cust?.customer_id!!)
        }
        else
            return null
    }



    fun isCartEmpty(): Boolean {
        return getCartDet().isCartEmpty(cust!!.customer_id)
    }

    fun checkCartForCust(customer_id: Int) : Boolean{
        return getCartDet().isCartEmpty(customer_id )
    }

    fun checkItemPresent(item_id: Int): Boolean {
        return getItemDet().checkItemPresent(item_id)!!
    }

    fun addToCart(item_id: Int)
    {
        if(!checkItemPresent(item_id)) {
            var amount = getCartDet().itemAmount(item_id, cust!!.customer_id)
            if (amount != 0)
                getCartDet().updateItemAmount(cust!!.customer_id, item_id, (amount + 1))
            else
                getCartDet().addToCart(cust!!.customer_id, item_id)
        }
        else
            println("item_id not present")
    }

    fun removeItem(item_id: Int)
    {
        if(!checkItemPresent(item_id)) {
            var amount = getCartDet().itemAmount(item_id, cust!!.customer_id)
            if (amount != 1)
                getCartDet().updateItemAmount(cust!!.customer_id, item_id, (amount - 1))
            else if (amount == 1)
                getCartDet().removeFromCart(cust!!.customer_id, item_id)
            else
                println("item $item_id not present in cart")
        }
        else
            println("item_id not present")
    }

    fun removeAllCart(): Boolean {
        if(!isCartEmpty())
        {
            println("Remove all Cart")
            getCartDet().removeAllFromCart(cust?.customer_id!!)
            return true
        }
        else
            return false
    }


}


