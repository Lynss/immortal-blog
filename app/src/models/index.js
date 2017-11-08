//登陆时的token对象
export class AccessToken {
    constructor(props) {
        this.username = props.username
        this.password = props.password
        this.rememberMe = props.rememberMe
    }
}


//创建自定义异常对象
export class ImmortalError extends Error {
    constructor(message, type, id) {
        super(message, id)
        this.type = type
    }

    transformAction = () => ({
            type: this.type,
            payload: this.message
        })

}