import React, { Component } from 'react';
import  styles  from './index.css';
import { withRouter } from 'react-router'
class DrawerNavigators extends Component {
    constructor(props) {
        super(props);
        this.state = {
            navigtors: [{
                name: 'Home',
                type: 'home',
                x: '',
                y: '',
                size: '',
                description: 'Home page entry'
            }, {
                name: 'About',
                type: 'about',
                x: '',
                y: '',
                size: '',
                description: 'About page entry'
            }, {
                name: 'Blog',
                type: 'blog',
                x: '',
                y: '',
                size: '',
                description: 'Blog page entry'
            }, {
                name: 'Factory',
                type: 'factory',
                x: '',
                y: '',
                size: '',
                description: 'Factory page entry'
            }, {
                name: 'Tools',
                type: 'tools',
                x: '',
                y: '',
                size: '',
                description: 'Tools page entry'
            }, {
                name: 'Repositories',
                type: 'repositories',
                x: '',
                y: '',
                size: '',
                description: 'Repositories page entry'
            }, {
                name: 'Private Forum',
                type: 'forum',
                x: '',
                y: '',
                size: '',
                description: 'Private forum page entry'
            }, {
                name: 'Public Forum',
                type: 'forum',
                x: '',
                y: '',
                size: '',
                description: 'Public forum page entry'
            }],
        }
    }
    renderNavigators=()=> ( this.state.navigtors.map((item,index) => (
            <div key={index} className={styles.navigatorItem} 
                onClick = {()=>
                    this.props.history.push('/'+item.type)
                }
            >
                <div className={styles.itemIcon}></div>
                <div className={styles.itmeWord}>{item.name}</div>
            </div>
        ))
    )
    render() {
        return (
            <div className={styles.container}>
                {this.renderNavigators()}
                <div className={styles.backButton}></div>
            </div>
        )
    }
}
export default withRouter(DrawerNavigators);