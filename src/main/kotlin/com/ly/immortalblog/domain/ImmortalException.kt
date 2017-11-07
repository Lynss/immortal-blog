package com.ly.immortalblog.domain

class ImmortalException(val code: Int, override val message: String):Exception(){
    constructor(baseReturn: BaseReturn) : this(baseReturn.code, baseReturn.message)
}
