const FORM_COMPONENT_TYPE = {
	checkbox: "checkbox",
	text: "text",
	password: "password",
	textarea: "textarea",
	select: "select"
}


export default function handleChange(event) {
    //不同组件调用时将this绑定到component上
    const component = this
	const changeState = {}
	const stateType = event.target.name
	switch (event.target.type) {
        case FORM_COMPONENT_TYPE.text:case FORM_COMPONENT_TYPE.textarea:
            case FORM_COMPONENT_TYPE.password:case FORM_COMPONENT_TYPE.select:{
	        changeState[stateType] = event.target.value
	        component.setState(changeState)
            break
        }
        case FORM_COMPONENT_TYPE.checkbox:{
	        changeState[stateType] = event.target.checked
	        component.setState(changeState)
            break
        }
        default:{
            console.log("unknown type")
            break
        }
	}
}
