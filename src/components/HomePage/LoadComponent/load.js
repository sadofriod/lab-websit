import React,{ Component } from 'react';
import style from './styles.css/style.css';
export default class Load extends Component {
    render() {
        return (
            <div className={style.container}>
                <div className={style.mid}></div>
                <div className={style.loadingBG}>
                    <p className={style.loadingWords}>...</p>
                </div>
            </div>
        )
    }
}