export default function handelChange(event) {
    const changeState = {}
    changeState[event.target.dataset.type] = event.target.value
    this.setState(changeState)
}
