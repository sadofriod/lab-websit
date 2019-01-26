import React, { Component } from 'react';
import styles from './styles/styles.css';
import store from '../../../stateManage/store';
export default class NavigationTips extends Component {
    constructor(props) {
        super(props);
        this.description = React.createRef();
        this.state = {
            navigatiorDescription: '',
            animationPlay: false,
            animationDone: true,
            previous: ''

        }
    }
    componentDidMount() {
        const unsubscribe = store.subscribe(() => {
            let state = store.getState();
            let play = state.changeTipsContent.entry ? state.changeTipsContent.entry : '';
            console.log(state)
            this.setState({
                navigatiorDescription: state.changeTipsContent.content,
                animationPlay: play,
                animationDone: false
            })
        });
    }
    removeAnimation = () => {
        if (this.state.animationPlay) {
            return;
        }
        let current = this.description.current;
        this.setState({
            animationDone: true
        })
    }
    render() {
        return (
            <div className={styles.container}>
                <p className={styles.descriptionContent} style={!this.state.animationDone ? { display: 'none' } : {}}>
                    {'The webkit description'}
                </p>
                <p ref={this.description}  style={this.state.animationDone ? { display: 'none' } : {}} onAnimationEnd={this.removeAnimation} className={
                    [
                        styles.content,
                        this.state.animationPlay ? styles.contentAnimation : '',
                        !this.state.animationPlay ? styles.contentSwitch : ''
                    ].join(' ')
                }>{this.state.navigatiorDescription}</p>
            </div>
        )
    }
}