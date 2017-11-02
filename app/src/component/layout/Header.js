import React, {Component} from "react"
import { Layout } from 'antd';
const { Header } = Layout;
import "Header.scss"

export default class HeaderLayout extends Component {
    render(){
        return (
            <div>
                <Layout>
                    <Header className={"header"}>Header</Header>
                </Layout>
            </div>
        )
    }
}