import React, {Component} from "react"
import PropTypes from 'prop-types'
import {Form, Icon, Input, Button, Checkbox} from 'antd';
import {AccessToken} from "../../models/index";
import {handelChange} from "../../utils/index"
import "./login.scss"

const Item = Form.Item
export default class Login extends Component {
    constructor(props) {
        super(props)
        this.state = {
            username: "",
            password: "",
            rememberMe: false
        }
        this.handelSubmit = this.handelSubmit.bind(this)
        this.handelChange = handelChange.bind(this)
    }

    handelSubmit() {
        const {handelLogin} = this.props
        handelLogin(new AccessToken({
            username: this.state.username,
            password: this.state.password,
            rememberMe: this.state.rememberMe
        }))
    }

    render() {
        return (
            <Form className="login-form">
                <Item>
                    <Input prefix={<Icon type="user"/>} data-type="username"
                           type="text" placeholder="username" value={this.state.username}
                           onChange={this.handelChange}/>
                </Item>
                <Item>
                    <Input prefix={<Icon type="lock"/>} data-type="password"
                           type="password" placeholder="password" value={this.state.password}
                           onChange={this.handelChange}/>
                </Item>
                <Item>
                    <Checkbox>remember me</Checkbox>
                    <a className="login-form-forgot" href="javascript:void(0);">Forgot password</a>
                </Item>
                <Item>
                    <Button className="login-button" type="primary" onClick={this.handelSubmit}>登陆</Button>
                </Item>
            </Form>
        )
    }
}

Login.propTypes = {
    status: PropTypes.string.isRequired,
    handelLogin: PropTypes.func.isRequired
}
