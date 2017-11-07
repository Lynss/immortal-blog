package com.ly.immortalblog.domain

import java.io.Serializable

/**
 *@author ly
 */
class ImmortalResult<T>(override val code: Int, override val message: String,val data:T?) :BaseReturn,Serializable{
    constructor(baseReturn: BaseReturn, data: T?) :
            this(baseReturn.code, baseReturn.message, data)

    constructor(baseReturn: BaseReturn) :
            this(baseReturn.code, baseReturn.message, null)

    constructor(code: Int,message: String) :
            this(code, message, null)

    override fun toString(): String {
        return "[code:${this.code},message:${this.message},data:${this.data.toString()}]"
    }
}