import React, { Component } from 'react';
import styles from './styles/styles.css';
import carousel0 from '../../../assets/carousel0.jpg';
import carousel1 from '../../../assets/carousel1.jpg';
import carousel2 from '../../../assets/carousel2.jpg';
export default class Carousel extends Component {
    constructor(props) {
        super(props);
        this.carouselGroup = React.createRef();
        this.carouselOptions = React.createRef();
        this.state = {
            carouselItems: [{
                url: carousel0,
                posterWords: ''
            }, {
                url: carousel1,
                posterWords: ''
            }, {
                url: carousel2,
                posterWords: ''
            }],
            carouselItemsDoms: [],
            current: 1,
            timer: undefined,
            mouseControl: false,
            carouselOptions: [],
            carouselOptionsStatus: []
        }
    }
    componentWillMount() {
        this.renderCarouselItems();
        this.renderCarouselOption();
    }
    componentDidMount() {
        let cg = this.carouselGroup.current;
        this.carouselAction(1);
        this.carouselOptionAction(0);
        cg.addEventListener('transitionend', this.animationEnd);
        cg.addEventListener('mouseenter', () => this.setState({
            mouseControl: true
        }))
        cg.addEventListener('mouseleave', () => {
            this.setState({
                mouseControl: false
            });
            this.carouselAction(this.state.current);
        })
    }
    carouselItemStruct = (data, index) => (
        <div key={index} className={styles.carouselItems}>
            <div className={styles.poster}>
                <div className={styles.posterImageBox}>
                    <img src={data.url} />
                </div>
                <p className={styles.posterWords}>
                    {data.posterWords}
                </p>
            </div>
            <div className={styles.learnMore}>
                <button>Learn More</button>
            </div>
        </div>
    );
    carouselOptionStruct = (index) => (
        <div key={index} className={styles.carouselOptions}></div>
    )
    carouselAction = (index) => {
        let carouselGroup = this.carouselGroup.current,
            width = carouselGroup.clientWidth,
            timer = setTimeout(() => {
                if (this.state.mouseControl) {
                    clearTimeout(timer);
                    return
                }
                if (this.state.current === this.state.carouselItems.length) {
                    index = 0;
                    this.setState({
                        current: 0
                    })
                }
                console.log(index * width)
                this.carouselOptionAction(index);
                carouselGroup.style.transform = 'translateX(-' + index * width + 'px)'
                carouselGroup.style.transition = 'transform 1s';
                clearTimeout(timer);
            }, 3000);
    }
    carouselOptionAction = (index) => {
        let items = [...this.carouselOptions.current.children];
        console.log(index)
        items.forEach(item => {
            item.style.transform = 'scale(1)';
        });
        items[index].style.transform = 'scale(1.5)';
    }
    animationEnd = () => {
        this.carouselAction(this.state.current + 1);
        this.setState({
            current: this.state.current + 1
        })
    }
    renderCarouselOption = () => {
        for (let i = 0; i < this.state.carouselItems.length; i++) {
            this.state.carouselOptions.push(this.carouselOptionStruct(i));
        }
    }
    renderCarouselItems = async () => {
        let carouselItemsVDom = [];
        let data = this.state.carouselItems;
        await data.map((item, index) => {
            carouselItemsVDom.push(this.carouselItemStruct(item, index));
        });
        this.setState({
            carouselItemsDoms: carouselItemsVDom
        })
    }
    render() {
        return (
            <div className={styles.container}>
                <div onAnimationEnd={this.animationEnd} className={styles.carouselGroup} ref={this.carouselGroup}>
                    {this.state.carouselItemsDoms}
                </div>
                <div ref={this.carouselOptions} className={styles.carouselOptionsBox}>
                    {this.state.carouselOptions}
                </div>
            </div>
        )
    }
}