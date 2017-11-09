import React, {Component} from "react"
import PropTypes from 'prop-types'
import {Form, Icon, Input, Button, Checkbox, Modal} from 'antd';
import {AccessToken} from "../../models/index";
import {handleChange} from "../../utils/index"
import "./login.scss"
import RegisterContainer from "../register/RegisterContainer";

const Item = Form.Item

class LoginForm extends Component {
	constructor(props) {
		super(props)
		this.state = {
			username: "",
			password: "",
			rememberMe: false
		}
		this.handleSubmit = this.handleSubmit.bind(this)
		this.handleChange = handleChange.bind(this)
	}

	handleSubmit = () => {
		const {handleLogin} = this.props
		handleLogin(new AccessToken({
			username: this.state.username,
			password: this.state.password,
			rememberMe: this.state.rememberMe
		}))
	}

	render() {
		const {loading, toggleRegisterModal} = this.props
		return (
			<div className="login-main">
				<Form className="login-form">
					<Item>
						<Input prefix={<Icon type="user"/>} name="username"
						       type="text" placeholder="username" value={this.state.username}
						       onChange={this.handleChange}/>
					</Item>
					<Item>
						<Input prefix={<Icon type="lock"/>} name="password"
						       type="password" placeholder="password" value={this.state.password}
						       onChange={this.handleChange}/>
					</Item>
					<Item>
						<Checkbox className="login-form rememberMe" name="rememberMe"
						          value={this.state.rememberMe} onChange={this.handleChange}>
							Remember me</Checkbox>
						<a className="login-form forgot" href="javascript:void(0);">Forgot password</a>
						<Button className="login-form" type="primary" onClick={this.handleSubmit}
						        loading={loading}>登陆</Button>
						Or <a className="login-form register" onClick={toggleRegisterModal}>register now!</a>
					</Item>
				</Form>
				<RegisterContainer/>
			</div>
		)
	}
}

LoginForm.propTypes = {
	status: PropTypes.string.isRequired,
	loading: PropTypes.bool.isRequired,
	toggleRegisterModal: PropTypes.func.isRequired,
	handleLogin: PropTypes.func.isRequired
}

export default LoginForm