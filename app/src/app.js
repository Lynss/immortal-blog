import React, {Component} from "react"
import {createStore, applyMiddleware} from 'redux'
import {createLogger} from 'redux-logger'
import reducer from "./reducer"
import {syncHistoryWithStore} from 'react-router-redux'
import {Provider} from "react-redux"
import route from "./route"
import {createBrowserHistory} from "history"


const loggerMiddleware = createLogger()
const store = createStore(reducer, applyMiddleware(
    loggerMiddleware
))
const history = syncHistoryWithStore(createBrowserHistory(), store)

const App = props => (
    <Provider store={store}>
        {route(history)}
    </Provider>
)

export default App