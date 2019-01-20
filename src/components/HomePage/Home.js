import React, { Component } from 'react';
import style from './styles/style.css';
import Load from './LoadComponent/load';
import Navigators from './navigator/navigator';
export default class Home extends Component {
    render(){
        return (
            <div className={style.contaienr}>
                <Navigators/>
                <Load/>
            </div>
        )
    } 
}