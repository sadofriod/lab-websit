import React, { Component } from 'react';
import style from './styles.css/style.css';
import store from '../../../stateManage/store';
export default class Load extends Component {
    constructor(props){
        super(props);
        this.state = {
            url:''
        }
    }
    componentDidMount() {
        const unsubscribe = store.subscribe(() => {
            let state = store.getState();
            
            this.setState({
                url:state.changeTipsContent.url
            })
        })
    }
    render() {
        return (
            <div className={style.container}>
                <div className={style.mid}></div>
                <div className={style.loadingBG}>
                    <p className={style.loadingWords}>
                        <img src={this.state.url} />
                    </p>
                </div>
            </div>
        )
    }
}