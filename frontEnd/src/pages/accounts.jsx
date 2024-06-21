import React, { useEffect, useState } from 'react';
import Carousel from '../components/carousel';
import axios from 'axios';
import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';

const Accounts = () => {
  
  const [data, setData] = useState([]);
  const token = useSelector(store => store.authReducer.token);

  useEffect(() => {
    if (token) {
      axios.get('http://localhost:8080/api/auth/current', {
        headers: {
          Authorization: 'Bearer ' + token
        }
      })
      .then(response => {
        setData(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error al obtener los clientes:', error);
      });
    }
  }, [token]);

  const melba = data;
  console.log(melba);

  const requestNewAccount = async () => {
    try {
      // Asume que tienes una URL de API y un token válido
      const response = await axios.post('http://localhost:8080/api/clients/current/accounts', {}, {
        headers: {
          'Authorization': `Bearer ${token}`,
        },
      });
      alert('Account created successfully', response.data);
      // Aquí podrías actualizar el estado para incluir la nueva cuenta en la UI
    } catch (error) {
      alert('Error creating account', error);
    }
  };

  return (
    <div className='w-full min-h-screen bg-slate-200'>
      <h1 className='font-extrabold text-3xl pt-5 justify-start ml-3'>
        Welcome, {melba ? melba.firstName : 'loading'}!
      </h1>
      <div>
      {melba && melba.accounts && melba.accounts.map(account => (
        <Link key={account.id} to={`/accounts/${account.id}`}>
          <div className="p-4 m-2 border rounded shadow">
            <h2 className="text-xl font-bold">Account Number: {account.number}</h2>
            <p>Balance: {account.balance}</p>
          </div>
        </Link>
      ))}
    </div>
    <div className='flex justify-center'>
        <button onClick={requestNewAccount} className="bg-blue-500 text-white font-bold py-2 px-4 rounded-full border-2 border-white shadow-lg hover:bg-blue-700 transition duration-300 ease-in-out mt-5">Request Account</button>
      </div>
      <Carousel>
      </Carousel>
    </div>
  );
};

export default Accounts;