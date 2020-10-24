package demo2

class AuthenticateCust{
    var username : String
    var password : String
    var cust : Customer? = null
    constructor(username: String, password: String)
    {
        this.username = username
        this.password = password
        if(customerPresent())
            this.cust = getCustomerByUser()
    }

     fun customerPresent(): Boolean {
         return !getCustomerDet().getCustomerId(username)!!
    }

    fun Authenticate(): Boolean {
        return password.equals(cust?.password)
    }

    fun checkAdminPriv(): Boolean {
        return Authenticate() && (cust?.admin_priv == 1)
    }

    fun getCustomerByUser(): Customer? {
        return getCustomerDet().getCustomer(username)
    }

}