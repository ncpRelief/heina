import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {

  state = {};

  componentDidMount() {
    setInterval(this.fetchUsers, 1000);
}


fetchUsers = () => {
  fetch('/listUsers')
  .then( res => res.text())
  .then( res => { this.setState( {message: res})
  });
};
 
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">{this.state.message}</h1>
        </header>
      </div>
    );
  }
}

export default App;