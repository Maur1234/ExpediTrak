package ie.expenditrak.expenditrak.model

data class Expsense(var id:String? = null,
                    var merchant:String? = null,
                    var date:String? = null,
                    var price:Double? = null){

    override fun toString(): String {
        return super.toString()
    }

}
