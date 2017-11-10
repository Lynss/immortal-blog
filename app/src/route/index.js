import React from 'react'
import {BrowserRouter, Route,Switch} from "react-router-dom"
import {LoginContainer, WelcomeContainer} from "../containers/index"
import {authWrapper} from "../utils";

const WrapWelcome = authWrapper(WelcomeContainer,LoginContainer,"guest")

const route = history => {
	return (
		<div>
			<main>
				<BrowserRouter browserHistory={history}>
					<Switch>
						<Route path={"/login"} extra component={LoginContainer}/>
						<Route path={"/"} extra component={WrapWelcome}/>
					</Switch>
				</BrowserRouter>
			</main>
		</div>
	)
}

export default route