import login from "./modules/loginDuck"
import {combineReducers} from "redux"
import {createLogger} from "redux-logger";
import {createStore, applyMiddleware} from 'redux'
import {routerReducer} from 'react-router-redux'

const reducer = combineReducers({
    login:login,
    routing: routerReducer
})

const loggerMiddleware = createLogger()

const configureStore = initialState => createStore(reducer,initialState,applyMiddleware(
    loggerMiddleware
))

export default configureStore;




