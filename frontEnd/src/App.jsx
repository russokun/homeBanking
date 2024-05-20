import { useState } from 'react'
import './App.css'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import React from 'react'; // Import React
import Header from './components/header';
import AccountLayout from './layouts/accountLayout'; // Import AccountLayout component
import AccountSelectedLayout from './layouts/accountSelectedLayout'; // Import AccountSelectedLayout component
import CardsLayout from './layouts/cardsLayout'; // Import CardsLayout component
import MakeTransactionLayout from './layouts/makeTransactionLayout'; // Import MakeTransactionLayout component
import RequestCardLayout from './layouts/requestCardLayout'; // Import RequestCardLayout component
import RequestLoanLayout from './layouts/requestLoanLayout';
import LoansLayout from './layouts/loansLayout';
import Footer from './components/footer';


function App() {
 

  return (
    <>
      
        <Router>
          <div>
            <Header />
            <Routes>
              <Route path='/' element={<AccountLayout />} />
              <Route path='/cards' element={<CardsLayout />} />
              <Route path='/loans' element={<LoansLayout />} />
              <Route path='/transactions' element={<MakeTransactionLayout />} />
              <Route path='/requestLoan' element={<RequestLoanLayout />} />
              <Route path='/requestCard' element={<RequestCardLayout />}/>
            </Routes>
            <Footer />
          </div>
        </Router>
      
    </>
  );
}

export default App
