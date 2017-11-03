const types = {
    LOGIN: "immortal-blog/login/login"
}

export const loginAction = (accessToken) => ({
    type: types.LOGIN
})

export default function reducer(state = {status: ""}, action = {}){
    switch (action.type) {
        case types.LOGIN:
            return {
                status: "success"
            }
        default:
            return {
                status: "false"
            }
    }
}