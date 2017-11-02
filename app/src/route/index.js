import React from 'react'
import {BrowserRouter, Route} from "react-router-dom"
import LoginContainer from "../container/LoginContainer"

const route = history => {
    return (
        <div>
            <main>
                <BrowserRouter browserHistory={history}>
                    <Route path={"/login"} extra component={LoginContainer}/>
                </BrowserRouter>
            </main>
        </div>
    )
}

export default route