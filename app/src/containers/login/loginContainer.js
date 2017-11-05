import {connect} from 'react-redux'
import Login from './Login'
import {loginAction,toggleRegisterModalAction} from "../../ducks/modules/loginDuck"

const mapStateToProps = state => ({
	status: state.login.status,
	loading: state.login.loading,
	visible: state.login.visible
})

const mapStateToDispatch = dispatch => ({
	handleLogin: accessToken => dispatch(loginAction(accessToken)),
	toggleRegisterModal:()=>dispatch(toggleRegisterModalAction())
})

const LoginContainer = connect(mapStateToProps, mapStateToDispatch)(Login)

export default LoginContainer