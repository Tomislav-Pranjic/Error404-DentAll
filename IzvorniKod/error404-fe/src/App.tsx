import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './components/Login';
import Home from './components/Home';
import Accommodation from './components/Accommodation';
import Transportation from './components/Transportation';
import Users from './components/Users';
import './App.css';

function App() {

  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route path='/' element={<Login />} />
          <Route path='/home' element={<Home />} />
          <Route path= '/accommodation' element={<Accommodation />} />
          <Route path= '/transportation' element={<Transportation />} />
          <Route path= '/users' element={<Users />} />
        </Routes>
      </div>
    </BrowserRouter>
  )
}

export default App
