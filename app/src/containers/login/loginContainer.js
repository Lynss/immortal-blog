import {connect} from 'react-redux'
import Login from './Login'
import {loginAction} from "../../ducks/modules/loginDuck"

const mapStateToProps = state => ({
    status: state.login.status
})

const mapStateToDispatch = dispatch => ({
    handelLogin: accessToken => dispatch(loginAction(accessToken))
})

const LoginContainer = connect(mapStateToProps, mapStateToDispatch)(Login)

export default LoginContainer