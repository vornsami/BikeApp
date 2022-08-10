import './App.css';
import MainPage from './components/MainPage'
import FailPage from './components/FailPage'
import { BrowserRouter, Routes, Route } from 'react-router-dom'

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route exact path='/:page' element={<MainPage/>} />
          <Route exact path='/' element={<MainPage/>} />
          <Route exact path='*' element={<FailPage/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
