//全局的rest请求前缀
const BASE_API_URL = "http://localhost:80/api/auth"
//全局的请求成功code
const SUCCESS_CODE = 200
//全局的异常type
const LOGIN_ERROR = "immortal-blog/unexpectedError"
//http status
const HttpStatus ={
    NO_PERMISSION:403,
    ACCESS_DENY:401,
    SUCCESS:200
}
export {BASE_API_URL,SUCCESS_CODE,LOGIN_ERROR,HttpStatus}