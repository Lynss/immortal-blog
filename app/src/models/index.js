//登陆时的token对象
export class AccessToken {
    constructor(props){
        this.username = props.username
        this.password = props.password
        this.rememberMe = props.rememberMe
    }
}
