// Header.jsx
import React from 'react';
import { NavLink } from 'react-router-dom';
import {logout} from '../redux/actions/authActions';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

const Header = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const handleLogout = () => {
    dispatch(logout());
    navigate('/');
  }
  const {loggedIn} = useSelector(state => state.authReducer); 
  return (
    <header className='w-full h-[13vh] flex flex-col bg-[#0F2A71]'>
      <div className='flex justify-between mb-2'>
        <img src="/src/assets/img/logo.png" className='rounded-lg' alt="Logo" />
        <h1 className='font-extrabold text-3xl pt-5 justify-start  text-white'>MINDHUB BROTHERS BANK</h1>
        {loggedIn && <button className='bg-white rounded-xl' onClick={handleLogout}>
          <svg xmlns="http://www.w3.org/2000/svg" width="3em" height="3em" viewBox="0 0 21 21">
            <path fill="none" stroke="#3b82f6" strokeLinecap="round" strokeLinejoin="round" d="m14.595 13.5l2.905-3l-2.905-3m2.905 3h-9m6-7l-8 .002c-1.104.001-2 .896-2 2v9.995a2 2 0 0 0 2 2h8.095"/>
          </svg>
        </button>}
      </div>
      <nav className='flex justify-between mb-1'>
      {!loggedIn &&
        <NavLink to="/login" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Login</NavLink>}
        {!loggedIn &&
        <NavLink to="/" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Home</NavLink>}
        {!loggedIn &&
        <NavLink to="/register" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Register</NavLink>}
      {loggedIn &&
        <NavLink to="/accounts" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Accounts</NavLink>}
        {loggedIn &&<NavLink to="/cards" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Cards</NavLink>}
        {loggedIn &&<NavLink to="/loans" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Loans</NavLink>}
          {loggedIn &&<NavLink to="/transactions" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Transactions</NavLink>}
      </nav>
    </header>
  );
};

export default Header;
