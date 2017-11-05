import {connect} from 'react-redux'
import RegisterForm from "./Register";
import {toggleRegisterModalAction} from "../../ducks/modules/loginDuck";

const mapStateToProps = state => ({
	visible:state.login.visible
})

const mapStateToDispatch = dispatch => ({
	toggleRegisterModal:()=>dispatch(toggleRegisterModalAction())
})

const RegisterContainer = connect(mapStateToProps, mapStateToDispatch)(RegisterForm)

export default RegisterContainer