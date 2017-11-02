import {combineReducers} from 'redux'
import {loginReducer} from "./loginReducer"
import {routerReducer} from 'react-router-redux'

const totalReducer = combineReducers({
    login:loginReducer,
    routing:routerReducer
})

export default totalReducer