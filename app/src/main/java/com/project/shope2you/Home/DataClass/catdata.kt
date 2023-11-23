package com.project.shope2you.Home.DataClass

data class catdata(
    val productCatagory:String?="",
    val productCoverImage:String?="",
    val productId:String?="",
    val productDId:String?=""


)
data class productmodel(
    val productName:String?="",
    val productDescription:String?="",
    val productCoverImage:String?="",
    val productId:String?="",
    val productDId:String?="",
    val productMrp:String?="",
    val productSp:String?="",
    val quantity:String?="",
    val quantityType:String?="",
    var s:String?="go to cart",
    var t:String?="add"


)
data class subcatdata(
    val productCatagory:String?="",
    val subCatagoryCoverImage:String?="",
    val subCatagoryName:String?="",
    var productId:String?=""


)
