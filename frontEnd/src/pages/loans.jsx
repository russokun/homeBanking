import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';
const Loans = () => {
  const [data, setData] = useState([]);
  const token = useSelector(store => store.authReducer.token);

  // // Autenticación
  // useEffect(() => {
  //   axios.post('http://localhost:8080/api/auth/login', {
  //     email: "melmorel@hotmail.com",
  //     password: "melmorel123"
  //   })
  //   .then(response => {
  //     // Guarda el token en el estado de la aplicación
  //     setToken(response.data);
  //   })
  //   .catch(error => {
  //     console.error('Error al autenticar:', error);
  //   });
  // }, []);

  // Obtención de los datos
  useEffect(() => {
    if (token) {
      axios.get('https://homebank1ngg.onrender.com/api/auth/current', {
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
  }, [token]);  // Dependencia del token

  const melba = data;
  console.log(melba.loans);

  return (
    <div className='w-full h-[84vh] bg-slate-200 sm:block flex flex-col items-center'>
      <h1 className='font-extrabold text-3xl pt-5 justify-start ml-3'>Your Loans</h1>
      
      <div className='flex mt-16 w-full justify-center gap-4 flex-wrap items-center'>
        {melba && melba.loans && melba.loans.map((loan, index) => (
          <button key={index} className="bg-white p-6 rounded-lg border-2 border-gray-300 shadow-lg">
            <h2 className="text-xl font-bold mb-4">Loan Name: {loan.name}</h2>
            <h2 className="text-xl font-bold mb-4">Max Amount: {loan.maxAmmount}</h2>
            <h2 className="text-xl font-bold">Application Date: 30 / 04 / 24 </h2>
          </button>
        ))}
      </div>
      <div className='flex justify-center'>
        <Link to="/requestLoan" className="bg-blue-500 text-white font-bold py-2 px-4 rounded-full border-2 border-white shadow-lg hover:bg-blue-700 transition duration-300 ease-in-out mt-5">Request Loan</Link>
      </div>
    </div>
  );
};

export default Loans;