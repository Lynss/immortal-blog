//登陆时的token对象
export class AccessToken {
    constructor(props) {
        this.username = props.username
        this.password = props.password
        this.rememberMe = props.rememberMe
    }
}

//创建action对象
export class Action {
    constructor(props) {
        this.type = props.type
        this.payload = props.payload
    }
}

//创建自定义异常对象
export class ImmortalError extends Error {
    constructor(message, type, id) {
        super(message, id)
        this.type = type
    }

    transformAction = () => {
        return new Action({
            type: this.type,
            payload: this.message
        })
    }
}