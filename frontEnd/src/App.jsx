import { useState } from 'react'
import './App.css'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import React from 'react'; // Import React
import Header from './components/header';
import Footer from './components/footer';
import Accounts from './pages/accounts';
import Cards from './pages/cards';
import Loans from './pages/loans';
import MakeTransaction from './pages/makeTransaction';
import RequestLoan from './pages/requestLoan';
import RequestCard from './pages/requestCard';
import Login from './pages/login';
import Register from './pages/register';
import Landing from './layouts/Landing';

function App() {

  return (
    <>
      
        <Router>
          <div>
            <Header />
            <Routes>
            <Route path='/home' element={<Landing />} />
              <Route path='/register' element={<Register />} />
              <Route path='/login' element={<Login />} />
              <Route path='/' element={<Accounts />} />
              <Route path='/cards' element={<Cards />} />
              <Route path='/loans' element={<Loans />} />
              <Route path='/transactions' element={<MakeTransaction />} />
              <Route path='/requestLoan' element={<RequestLoan />} />
              <Route path='/requestCard' element={<RequestCard />}/>
            </Routes>
            <Footer />
          </div>
        </Router>
      
    </>
  );
}

export default App
