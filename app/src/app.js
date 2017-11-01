import React,{Component} from 'react'
import { Layout } from 'antd';
const { Header } = Layout;

class App extends Component {
    render(){
        return (
            <div>
                <Layout>
                    <Header>Header</Header>
                </Layout>
            </div>
        )
    }
}

export default App