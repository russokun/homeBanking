import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';
const Cards = () => {
  const [data, setData] = useState(null);
  const token = useSelector(store => store.authReducer.token);
  const [error, setError] = useState(null);

  // // Autenticación
  // useEffect(() => {
  //   axios.post('http://localhost:8080/api/auth/login', {
  //     email: "melmorel@hotmail.com",
  //     password: "melmorel123"
  //   })
  //   .then(response => {
  //     setToken(response.data);
  //   })
  //   .catch(error => {
  //     console.error('Error al autenticar:', error);
  //     setError('Error al autenticar');
  //   });
  // }, []);

  // Obtención de los datos
  useEffect(() => {
    if (token) {
      axios.get('http://localhost:8080/api/auth/current', {
        headers: {
          Authorization: 'Bearer ' + token
        }
      })
      .then(response => {
        setData(response.data);
      })
      .catch(error => {
        console.error('Error al obtener los clientes:', error);
        setError('Error al obtener los clientes');
      });
    }
  }, [token]);

  if (error) {
    return <div>Error: {error}</div>;
  }

  if (!data) {
    return <div>Loading...</div>;
  }

  const melbaCreditCards = data.cards?.filter(card => card.type === 'CREDIT') || [];
  const melbaDebitCards = data.cards?.filter(card => card.type === 'DEBIT') || [];
  const creditCards = melbaCreditCards.map((card, index) => (
    <div key={index} className="card relative h-[260px] w-[400px] flex flex-col justify-end px-6 py-10 text-white rounded-3xl gap-8 bg-gradient-to-r from-blue-600 to-slate-800 shadow-lg mb-5">
      <div className='mt-5'>
      <p className="text-lg font-medium">CVV: {card.cvv}</p> 
      <p className="text-lg font-medium">Type: {card.type}</p>
      </div>
      <p className="text-2xl font-medium">{card.number}</p>
      
      <div className="flex justify-between gap-10">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 58 36" height="36" width="58">
          <circle fillOpacity="0.62" fill="#F9CCD1" r="18" cy="18" cx="18"></circle>
          <circle fill="#424242" r="18" cy="18" cx="40" opacity="0.36"></circle>
        </svg>
        <p className="text-lg font-medium">{card.cardHolder}</p>
        <div className="flex-1 flex flex-col justify-end">
          <p className="self-end">Valid Date</p>
          <p className="self-end">{card.dueDate}</p>
          
        </div>
      </div>
    </div>
  ));

  const debitCards = melbaDebitCards.map((card, index) => (
    <div key={index} className="card relative h-[260px] w-[400px] flex flex-col justify-end px-6 py-10 text-white rounded-3xl gap-8 bg-gradient-to-r from-blue-600 to-slate-800 shadow-lg">
      <div className='mt-5'>
      <p className="text-lg font-medium">CVV: {card.cvv}</p> 
      <p className="text-lg font-medium">Type: {card.type}</p>
      </div>
      <p className="text-2xl font-medium">{card.number}</p>
      <div className="flex justify-between gap-10">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 58 36" height="36" width="58">
          <circle fillOpacity="0.62" fill="#F9CCD1" r="18" cy="18" cx="18"></circle>
          <circle fill="#424242" r="18" cy="18" cx="40" opacity="0.36"></circle>
        </svg>
        <p className="text-lg font-medium">{card.cardHolder}</p>
        <div className="flex-1 flex flex-col justify-end">
          <p className="self-end">Valid Date</p>
          <p className="self-end">{card.dueDate}</p>
        </div>
      </div>
    </div>
  ));
  

  return (
    <div className='flex w-full min-h-screen bg-slate-200 flex-col'>
      <h1 className='font-extrabold text-3xl pt-5 justify-start ml-3'>Your Cards</h1>
      <div className='flex justify-center'>
        <Link to="/requestCard" className="bg-blue-500 text-white font-bold py-2 px-4 rounded-full border-2 border-white shadow-lg hover:bg-blue-700 transition duration-300 ease-in-out mt-5">Request Card</Link>
      </div>
      <h2 className='text-xl font-bold mb-4 mt-10'>Credit</h2>
      <div className='flex flex-row flex-wrap gap-5 justify-evenly'>
        {creditCards}
      </div>
      <h2 className='text-xl font-bold mb-4 mt-3'>Debit</h2>
      <div className='flex flex-row flex-wrap gap-5 justify-evenly mb-4'>
        {debitCards}
      </div>
    </div>
  );
};

export default Cards;