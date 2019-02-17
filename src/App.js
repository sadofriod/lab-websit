import React, { Component } from 'react';
import Entry from './components/EntryPage/Entry';
import Home from './components/HomePage/Home';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import './App.css';
class App extends Component {
  render() {
    return (
      <Router>
        <div>
          <Route exact path="/" component={Entry}></Route>
          <Route path="/Home" component={Home}></Route>
        </div>
      </Router>
    );
  }
}

export default App;
