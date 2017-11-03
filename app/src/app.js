import React from "react"
import {syncHistoryWithStore} from 'react-router-redux'
import {Provider} from "react-redux"
import {createBrowserHistory} from "history"
import "./app.scss"
import route from "./route"
import configureStore from "./ducks/configureStore"

const store = configureStore()
const history = syncHistoryWithStore(createBrowserHistory(), store)

const App = props => (
    <Provider store={store}>
        {route(history)}
    </Provider>
)

export default App