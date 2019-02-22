import React, { Component } from 'react';
import Entry from './components/EntryPage/Entry';
import Home from './components/HomePage/Home';
import DrawerNavigator from './components/DrawerNavigator/index';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import styles from './App.css';
class App extends Component {
  render() {
    return (
      <Router>
        <div>
          <Route exact path="/" component={Entry}></Route>
          <div className={styles.actionPageArea}>
            <DrawerNavigator/>
            <Route path="/home" component={Home}></Route>
          </div>
        </div>
      </Router>
    );
  }
}

export default App;
