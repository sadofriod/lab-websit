import React, { Component } from 'react';
import styles from './styles/styles.css';
import { getTipsContent } from '../NavigationTips/actions/tipsCotent'
import store from '../../../stateManage/store';
export default class Navigtor extends Component {
    constructor(props) {
        super(props);
        this.navigtorsArea = React.createRef();
        this.state = {
            navigtors: [{
                name: 'Home',
                x: '',
                y: '',
                size: '',
                description: 'Home page entry'
            }, {
                name: 'About',
                x: '',
                y: '',
                size: '',
                description: 'About page entry'
            }, {
                name: 'Blog',
                x: '',
                y: '',
                size: '',
                description: 'Blog page entry'
            }, {
                name: 'Factory',
                x: '',
                y: '',
                size: '',
                description: 'Factory page entry'
            }, {
                name: 'Tools',
                x: '',
                y: '',
                size: '',
                description: 'Tools page entry'
            }, {
                name: 'Repositories',
                x: '',
                y: '',
                size: '',
                description: 'Repositories page entry'
            }, {
                name: 'Private Forum',
                x: '',
                y: '',
                size: '',
                description: 'Private forum page entry'
            }, {
                name: 'Public Forum',
                x: '',
                y: '',
                size: '',
                description: 'Public forum page entry'
            }],
            position: undefined,
            images: {
                forum: require('../../../assets/forum.png'),
                tools:require('../../../assets/tools.png'),
                blog:require('../../../assets/blog.png'),
                about:require('../../../assets/about.png'),
                factory:require('../../../assets/factory.png'),
                repositories:require('../../../assets/repositories.png'),
                home:require('../../../assets/home.png')
            }
        }
    }
    async componentDidMount() {
        let result = await this.navigtorsPosition();
        await this.setState({
            position: result
        });
    }
    navigtorsPosition = async () => {
        let ns = this.state.navigtors;
        let target = this.navigtorsArea.current;
        let centerPoint = { x: 0, y: 0 }, col = 0, row = 0;
        let num = parseInt(target.offsetWidth / ns.length);
        await ns.map((item, index) => {
            let random = (Math.random() + 1) * 15;
            item.size = random;
            if (index === 0) {
                item.size = 44;
                item.x = parseInt(target.offsetWidth / 2);
                item.y = parseInt(target.offsetHeight / 2) - 30;
                centerPoint.x = item.x;
                centerPoint.y = item.y - 30;
                col = parseInt(target.offsetHeight / 2) - 30
                row = parseInt(target.offsetWidth / 2)
            } else {
                item.x = num * index
                let y = Math.sqrt((col * col * row * row - col * col * Math.pow((item.x - centerPoint.x), 2)) / (row * row)) + centerPoint.y
                if (index % 2 != 1) {
                    item.y = 2 * col - y
                } else {
                    item.y = y;
                }
            }
        });
        return ns;
    }
    renderNavigators = (position) => {
        let p = position;
        return p.map((item, index) => {
            return (
                <div key={index} className={styles.navigatorItem} style={{
                    top: item.y + 'px',
                    left: item.x * 0.75 + 'px',
                    fontSize: item.size + 'px',
                }}
                    data-title={item.description}
                    onMouseEnter={() => {
                        if(item.name.indexOf(' ') !== -1){
                            item.name = 'forum';
                        }
                        console.log(this.state.images[item.name.toLowerCase()])
                        store.dispatch(getTipsContent(
                            {
                                routeName: item.name.toLowerCase(),
                                entry: true,
                                url: this.state.images[item.name.toLowerCase()]
                            }
                        ));
                    }}
                    onMouseLeave={() => {
                        if(item.name.indexOf(' ') !== -1){
                            item.name = 'forum';
                        }
                        store.dispatch(getTipsContent(
                            {
                                routeName: item.name.toLowerCase(),
                                entry: false,
                                url: this.state.images[item.name.toLowerCase()]
                            }
                        ));
                    }}
                >
                    {item.name}
                </div>
            )
        });
    }
    render() {
        return (
            <div className={styles.container} ref={this.navigtorsArea}>
                <div className={styles.description}></div>
                <div className={styles.navigatorGroup}>
                    {this.state.position === undefined ? '' : this.renderNavigators(this.state.position)}
                </div>
            </div>
        )
    }
} 