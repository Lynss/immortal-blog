const FORM_COMPONENT_TYPE = {
    checkbox: "checkbox",
    text: "text",
    radio: "radio",
    password: "password",
    textarea: "textarea",
    select: "select"
}


export default function handleChange(event) {
    //不同组件调用时将this绑定到component上
    const component = this
    const stateType = event.target.name
    switch (event.target.type) {
        case FORM_COMPONENT_TYPE.text:
        case FORM_COMPONENT_TYPE.textarea:
        case FORM_COMPONENT_TYPE.password:
        case FORM_COMPONENT_TYPE.select:
        case FORM_COMPONENT_TYPE.radio: {
            component.setState({
                [stateType]:event.target.value
            })
            break
        }
        case FORM_COMPONENT_TYPE.checkbox: {
            component.setState({
                [stateType]:event.target.checked
            })
            break
        }
        default: {
            console.log("unknown type")
            break
        }
    }
}
