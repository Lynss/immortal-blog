import React, {Component} from "react"
import PropTypes from 'prop-types'
import {Form, Icon, Input, Button, Checkbox, Modal, Radio} from 'antd';
import {handleChange} from "../../utils/index"

const Item = Form.Item

/**
 * todo:调样式，脑壳疼。。。
 */
class RegisterForm extends Component {
	constructor(props) {
		super(props)
        this.state = {
            username: "",
            password: "",
            repeatPassword: "",
            sex: "male",
            agree: true,
        }
		this.handleChange = handleChange.bind(this)
	}

	render() {
		const {visible,toggleRegisterModal} = this.props
		return (
			<Modal visible={visible}>
				<Form>
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
						<Input prefix={<Icon type="lock"/>} name="repeat your password"
						       type="password" placeholder="repeatPassword" value={this.state.repeatPassword}
						       onChange={this.handleChange}/>
					</Item>
					<Item>
						<Radio.Group onChange={this.handleChange} name="sex" value={this.state.sex}>
							<Radio  value="male">male</Radio>
							<Radio  value="female">female</Radio>
						</Radio.Group>
						<Checkbox onChange={this.handleChange} value={this.state.agree}>已阅读相关<a>条款</a></Checkbox>
						<Button type="primary" htmlType="submit">注冊</Button>
						<Button type="default" onClick={toggleRegisterModal}>关闭</Button>
					</Item>
				</Form>
			</Modal>
		)
	}
}

RegisterForm.propTypes = {
	visible: PropTypes.bool.isRequired,
	toggleRegisterModal: PropTypes.func.isRequired,
}

export default RegisterForm