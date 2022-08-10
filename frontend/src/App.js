import logo from './logo.svg';
import './App.css';
import MainPage from './components/MainPage'
import { BrowserRouter, Routes, Route } from 'react-router-dom'

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
      <BrowserRouter>
        <Routes>
          <Route exact path='/:page' element={<MainPage/>} />
          <Route exact path='/' element={<MainPage/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
