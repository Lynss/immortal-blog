const types = {
	LOGIN: "immortal-blog/login/login",
	TOGGLE_REGISTER_MODAL:"immortal-blog/login/toggleRegisterModal",
}

export const loginAction = (accessToken) => ({
	type: types.LOGIN
})


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
		case types.LOGIN:
			return {
				...state,
				status: state.status === "success" ? "false" : "success",
				loading:!state.loading
			}
		case types.TOGGLE_REGISTER_MODAL:
			return {
				...state,
				visible:!state.visible
			}
		default:
			return {
				...state,
				status: "false"
			}
	}
}