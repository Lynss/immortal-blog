import {connect} from 'react-redux'
import Welcome from "./Welcome"

const mapStateToProps = state => ({
	isLogin:state.login.isLogin,
})

const WelcomeContainer = connect(mapStateToProps)(Welcome)

export default WelcomeContainer