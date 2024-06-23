import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { NavLink } from 'react-router-dom';
import { logout } from '../redux/actions/authActions';
import { Button, Drawer } from "flowbite-react";

const Header = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const handleLogout = () => {
    dispatch(logout());
    navigate('/');
  }
  const { loggedIn } = useSelector(state => state.authReducer);

  const [isOpen, setIsOpen] = useState(false);

  const handleClose = () => setIsOpen(false);

  return (
    <header className='w-full h-[10vh] flex flex-col bg-[#0F2A71]'>
      <div className='flex justify-between mb-2'>
        <img src="/src/assets/images/logo.png" className='w-16 sm:w-24   rounded-lg' alt="Logo" />
        <h1 className='pl-2 text-xl sm:text-2xl md:text-3xl lg:text-4xl font-extrabold pt-5 justify-start  text-white'>MINDHUB BROTHERS BANK</h1>
        
        <Button className='h-14 bg-blue-600 py-2' onClick={() => setIsOpen(true)}>Menu</Button>
      </div>
      <Drawer open={isOpen} onClose={handleClose} position="top">
        <Drawer.Header title="Menu" />
          <Drawer.Items className='flex flex-wrap px-2 gap-2'>
            {!loggedIn &&
            <NavLink to="/login" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Login</NavLink>}
            {!loggedIn &&
            <NavLink to="/" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Home</NavLink>}
            {!loggedIn &&
            <NavLink to="/register" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Register</NavLink>}
            {loggedIn &&<NavLink to="/accounts" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Accounts</NavLink>}
            {loggedIn &&<NavLink to="/cards" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Cards</NavLink>}
            {loggedIn &&<NavLink to="/loans" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Loans</NavLink>}
            {loggedIn &&<NavLink to="/transactions" className={({isActive})=>(isActive?'bg-blue-800 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg':'bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-700 transition duration-300 ease-in-out shadow-lg')}>Transactions</NavLink>}
            {loggedIn && <Button className='h-10 bg-red-400' onClick={handleLogout}>Logout</Button>} 
          </Drawer.Items>
      </Drawer>
    </header>
  );
};

export default Header;