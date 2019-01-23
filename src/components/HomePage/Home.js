import React, { Component } from 'react';
import style from './styles/style.css';
import Load from './LoadComponent/load';
import Navigators from './navigator/navigator';
import NavigatorTips from './NavigationTips/navigationTips';
export default class Home extends Component {
    render(){
        return (
            <div className={style.contaienr}>
                <Navigators/>
                <NavigatorTips/>
                <Load/>
            </div>
        )
    } 
}