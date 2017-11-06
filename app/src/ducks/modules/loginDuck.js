import fetch from 'isomorphic-fetch'
import {BASE_API_URL, LOGIN_ERROR, SUCCESS_CODE} from "../../constants/index"
import {goBack} from 'react-router-redux'
import {ImmortalError} from "../../models/index"

const types = {
    BEGIN_LOGIN: "immortal-blog/login/beginLogin",
    LOGIN_SUCCESS: "immortal-blog/login/loginSuccess",
    LOGIN_FAIL: "immortal-blog/login/loginFail",
    TOGGLE_REGISTER_MODAL: "immortal-blog/login/toggleRegisterModal",
}

export const loginAction = (accessToken) => async (dispatch) => {
    dispatch(types.BEGIN_LOGIN)
    const url = BASE_API_URL + "/user"
    const request = new Request(
        url, {
            method: "GET",
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
            body: JSON.stringify(accessToken),
        }
    )
    try {
        const response = await fetch(request)
        if (!response.ok) {
            throw new ImmortalError(`http request fail,http status:${response.status}`, types.LOGIN_FAIL)
        }
        const replyData = await response.json()
        if (replyData.code !== SUCCESS_CODE) {
            throw new ImmortalError(`login fail,for ${replyData.message}`, types.LOGIN_FAIL)
        }
        dispatch(goBack())
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
    loading: false
}

export default function reducer(state = initialState, action = {}) {
    switch (action.type) {
        case types.BEGIN_LOGIN:
            return {
                ...state,
                status: state.status === "success" ? "false" : "success",
                loading: !state.loading
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