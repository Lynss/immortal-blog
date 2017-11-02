import {connect} from 'react-redux'
import Login from '../component/login'

const mapStateToProps = state => ({
    status: state.login.status
})

const mockAction = accessToken => ({
    type: "login"
})


const mapStateToDispatch = dispatch => ({
    handelLogin: accessToken => dispatch(mockAction(accessToken))
})

const LoginContainer = connect(mapStateToProps, mapStateToDispatch)(Login)

export default LoginContainer