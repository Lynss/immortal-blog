import React, {Component} from "react"
import PropTypes from 'prop-types'

export default class Login extends Component {
	constructor(props) {
		super(props)
		this.state = {
			email: "",
			password: ""
		}
		this.handelSubmit = this.handelSubmit.bind(this)
		this.handelPasswordChange = this.handelPasswordChange.bind(this)
		this.handelEmailChange = this.handelEmailChange.bind(this)
	}

	handelSubmit() {
		const {handelLogin} = this.props
		handelLogin({
			email: this.state.email,
			password: this.state.password
		})
	}

	handelEmailChange(event) {
		this.setState({
			email: event.target.value
		})
	}

	handelPasswordChange(event) {
		this.setState({
			password: event.target.value
		})
	}

	render() {
		const {status} = this.props
		return (
			<div>
				<input type="text" value={this.state.email} onChange={this.handelEmailChange}/>
				<input type="password" value={this.state.password} onChange={this.handelPasswordChange}/>
				<p>{status}</p>
				<button onClick={this.handelSubmit}>登陆</button>
			</div>
		)
	}
}

Login.propTypes = {
	status: PropTypes.string.isRequired,
	handelLogin: PropTypes.func.isRequired
}
