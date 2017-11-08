import fetch from 'isomorphic-fetch'
import {BASE_API_URL, HttpStatus, LOGIN_ERROR, SUCCESS_CODE} from "../../constants/index"
import {ImmortalError} from "../../models/index"
import {browserHistory} from 'react-router'

const types = {
    BEGIN_LOGIN: "immortal-blog/login/beginLogin",
    LOGIN_SUCCESS: "immortal-blog/login/loginSuccess",
    LOGIN_FAIL: "immortal-blog/login/loginFail",
    TOGGLE_REGISTER_MODAL: "immortal-blog/login/toggleRegisterModal",
}

export const loginAction = (accessToken) => async (dispatch) => {
    dispatch({type: types.BEGIN_LOGIN})
    const url = BASE_API_URL + "/user"
    const request = new Request(
        url, {
            method: "POST",
            headers: new Headers({
                'Content-Type': 'application/json',
                "Authorization": sessionStorage.getItem('Authorization') || "lueluelue"
            }),
            body: JSON.stringify(accessToken),
        }
    )
    try {
        const response = await fetch(request)
        debugger
        if (!response.ok) {
            throw new ImmortalError(`http request fail,http status:${response.status}`, types.LOGIN_FAIL)
        }
        const replyData = await response.json()
        if (replyData.code !== SUCCESS_CODE) {
            if (response.status === HttpStatus.NO_PERMISSION) {
                browserHistory.push("/login")
            }
            throw new ImmortalError(`login fail,for ${replyData.message}`, types.LOGIN_FAIL)
        }
        const token = response.headers.get("Authorization")
        token && sessionStorage.setItem('Authorization', token)
        dispatch({type: types.LOGIN_SUCCESS, payload: replyData.data})
    } catch (e) {
        e = e instanceof ImmortalError ? e : new ImmortalError(e.message, LOGIN_ERROR)
        dispatch(e.transformAction())
    }
}


export const toggleRegisterModalAction = () => ({
    type: types.TOGGLE_REGISTER_MODAL
})

const initialState = {
    status: "fail",
    visible: false,
    loading: false,
    isLogin: false,
    loginMessage: ""
}

export default function reducer(state = initialState, action = {}) {
    switch (action.type) {
        case types.BEGIN_LOGIN:
            return {
                ...state,
                loading: true,
                loginMessage: action.payload
            }
        case types.LOGIN_FAIL:
            return {
                ...state,
                loading: false,
                loginMessage: action.payload
            }
        case types.LOGIN_SUCCESS:
            return {
                ...state,
                isLogin: true,
                loading: false,
                loginMessage: action.payload
            }
        case LOGIN_ERROR:
            return {
                ...state,
                loading: false,
                loginMessage: action.payload
            }
        case types.TOGGLE_REGISTER_MODAL:
            return {
                ...state,
                visible: !state.visible
            }
        default:
            return {
                ...state,
                status: "false"
            }
    }
}