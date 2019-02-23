import React, { Component } from 'react';
import Carousel from './carousel/Carousel';
import DrawerNavigator from '../DrawerNavigator/index';
export default class Home extends Component {
    render() {
        return (
            <div style={{ display: 'flex' }}>
                <DrawerNavigator />
                <Carousel />
            </div>
        )
    }
}