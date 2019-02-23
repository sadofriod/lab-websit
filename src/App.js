import React, { Component } from 'react';
import Entry from './components/EntryPage/Entry';
import Home from './components/HomePage/Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import styles from './App.css';
class App extends Component {
  render() {
    return (
      <Router>
        <div>
          <Route exact path="/" component={Entry}></Route>
            <Route path="/home" component={Home}></Route>
          </div>
      </Router>
    );
  }
}

export default App;
