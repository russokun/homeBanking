import React from 'react';
import { useState } from 'react';
import axios from 'axios';
import { useSelector } from 'react-redux';

const MakeTransaction = () => {
  const [formData, setFormData] = useState({
    amount: '',
    description: '',
    sourceAccountNumber: '',
    destinationAccountNumber: '',
  });
  const token = useSelector(store => store.authReducer.token);

  const handleChange = (event) => {
    setFormData({
      ...formData,
      [event.target.name]: event.target.value,
    });
  };
  
  const handleSubmit = (event) => {
    event.preventDefault();

    axios.post('http://localhost:8080/api/transactions', formData, {
      headers: {
        Authorization: 'Bearer ' + token
      }
    })
    .then(response => {
      console.log(response.data);
      alert('Transaction released successfully');
      // Aquí puedes manejar la respuesta de la API
    })
    .catch(error => {
      alert('Error at realease the transaction:', error);
      // Aquí puedes manejar los errores
    });
  };

  return (
    <div className='w-full h-[84vh] bg-slate-200 sm:block flex flex-col items-center'>
      <h1 className='font-extrabold text-3xl pt-5 justify-start ml-3'>Make Transactions</h1>
      
      <div className='flex mt-16 w-full justify-center gap-4 flex-wrap items-center'>
        <form onSubmit={handleSubmit} className="bg-white p-6 rounded-lg border-2 border-gray-300 shadow-lg just">
          <div className='flex flex-col items-center'>
            <input
              type="number"
              name="amount"
              value={formData.amount}
              onChange={handleChange}
              placeholder="Amount"
              className="text-xl font-bold mb-4"
            />
            <input
              type="text"
              name="description"
              value={formData.description}
              onChange={handleChange}
              placeholder="Description"
              className="text-xl font-bold mb-4"
            />
            <input
              type="text"
              name="sourceAccountNumber"
              value={formData.sourceAccountNumber}
              onChange={handleChange}
              placeholder="Source Account Number"
              className="text-xl font-bold mb-4"
            />
            <input
              type="text"
              name="destinationAccountNumber"
              value={formData.destinationAccountNumber}
              onChange={handleChange}
              placeholder="Destination Account Number"
              className="text-xl font-bold mb-4"
            />
            <button type="submit" className="text-xl font-bold mb-4 bg-green-500 rounded-xl w-40">Sumbit</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default MakeTransaction;