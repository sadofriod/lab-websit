import React, { Component } from 'react';
import styles from './styles/styles.css';
import { getTipsContent } from '../NavigationTips/actions/tipsCotent'
import store from '../../../stateManage/store';
import forum from '../../../assets/forum.png';
import tools from '../../../assets/tools.png';
import blog from '../../../assets/blog.png';
import about from '../../../assets/about.png';
import factory from '../../../assets/factory.png';
import repositories from '../../../assets/repositories.png';
import home from '../../../assets/home.png';
export default class Navigtor extends Component {
    constructor(props) {
        super(props);
        this.navigtorsArea = React.createRef();
        this.state = {
            navigtors: [{
                name: 'Home',
                type:'home',
                x: '',
                y: '',
                size: '',
                description: 'Home page entry'
            }, {
                name: 'About',
                type:'about',
                x: '',
                y: '',
                size: '',
                description: 'About page entry'
            }, {
                name: 'Blog',
                type:'blog',
                x: '',
                y: '',
                size: '',
                description: 'Blog page entry'
            }, {
                name: 'Factory',
                type:'factory',
                x: '',
                y: '',
                size: '',
                description: 'Factory page entry'
            }, {
                name: 'Tools',
                type:'tools',
                x: '',
                y: '',
                size: '',
                description: 'Tools page entry'
            }, {
                name: 'Repositories',
                type:'repositories',
                x: '',
                y: '',
                size: '',
                description: 'Repositories page entry'
            }, {
                name: 'Private Forum',
                type:'forum',
                x: '',
                y: '',
                size: '',
                description: 'Private forum page entry'
            }, {
                name: 'Public Forum',
                type:'forum',
                x: '',
                y: '',
                size: '',
                description: 'Public forum page entry'
            }],
            position: undefined,
            images: {
                forum: forum,
                tools: tools,
                blog: blog,
                about: about,
                factory: factory,
                repositories: repositories,
                home: home
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
                        
                    
                        store.dispatch(getTipsContent(
                            {
                                routeName: item.name.toLowerCase(),
                                entry: true,
                                url: this.state.images[item.type]
                            }
                        ));
                    }}
                    onMouseLeave={() => {
                        
                        store.dispatch(getTipsContent(
                            {
                                routeName: item.name.toLowerCase(),
                                entry: false,
                                url: this.state.images[item.type]
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