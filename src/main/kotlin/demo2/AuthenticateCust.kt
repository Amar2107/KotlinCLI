package demo2

class AuthenticateCust{
    var username : String
    var password : String
    private val cust : Customer
    constructor(username: String, password: String)
    {
        this.username = username
        this.password = password
         cust = getCustomerDet().getCustomer(username)!!
    }

    fun Authenticate(): Boolean {
        return password.equals(cust?.password)
    }

    fun checkAdminPriv(): Boolean {
        return Authenticate() && (cust?.admin_priv == 1)
    }


}