import React, {Component} from "react"
import {connect} from 'react-redux'

const  authenticate=(auths,needAuths)=>(!needAuths||!needAuths.length) || !needAuths.some(needAuth=> !auths||auths.indexOf(needAuth)===-1)

export default function authWrapper(ComposedComponent, NoAuthComponent,...needAuth) {
	class WrapComponent extends Component {
		render() {
			const {auth} = this.props
			if (!authenticate(auth,needAuth)){
				return <NoAuthComponent />;
			} else {
				return <ComposedComponent />;
			}
		}
	}

	const mapStateToProps = state => ({
		auth: state.login.auth,
	})

	return connect(mapStateToProps)(WrapComponent)
}
