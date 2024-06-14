// Header.jsx
import React from 'react';
import { Link } from 'react-router-dom';
import {logout} from '../redux/actions/authActions';
import { useDispatch, useSelector } from 'react-redux';
const Header = () => {
  const dispatch = useDispatch();
  const handleLogout = () => {
    dispatch(logout());
  }

  return (
    <header className='w-full h-[14vh] flex flex-col bg-[#0F2A71]'>
      <div className='flex justify-between mb-2'>
        <img src="/src/assets/img/logo.png" alt="Logo" />
        <button className='bg-white rounded-lg' onClick={handleLogout}>
          <svg xmlns="http://www.w3.org/2000/svg" width="3em" height="3em" viewBox="0 0 21 21">
            <path fill="none" stroke="#3b82f6" strokeLinecap="round" strokeLinejoin="round" d="m14.595 13.5l2.905-3l-2.905-3m2.905 3h-9m6-7l-8 .002c-1.104.001-2 .896-2 2v9.995a2 2 0 0 0 2 2h8.095"/>
          </svg>
        </button>
      </div>
      <nav className='flex justify-between mb-1'>
        <Link to="/" className='bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg'>Accounts</Link>
        <Link to="/cards" className='bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg'>Cards</Link>
        <Link to="/loans" className='bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg'>Loans</Link>
        <Link to="/transactions" className='bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg'>Transactions</Link>
      </nav>
    </header>
  );
};

export default Header;
