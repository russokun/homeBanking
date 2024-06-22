import React, { useState } from 'react';
import axios from 'axios';
import { useSelector } from 'react-redux';

const RequestCard = () => {
  const [cardType, setCardType] = useState('');
  const [cardColor, setCardColor] = useState('');

  const token = useSelector(store => store.authReducer.token);

  const handleSubmit = async (event) => {
    event.preventDefault();

    const data = {
      type: cardType,
      color: cardColor,
    };

    try {
      const response = await axios.post('https://homebank1ngg.onrender.com/api/clients/current/cards', data, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      alert('Card requested successfully');
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="bg-gray-100 flex flex-col items-center justify-center h-[84vh]">
      <div className="text-center mb-6">
        <h1 className="font-extrabold text-3xl pt-5 justify-start ml-3">Apply for a Card</h1>
      </div>
      <div className="flex flex-col lg:flex-row w-11/12 lg:w-3/4 h-3/4 bg-white p-8 rounded-lg shadow-md border-2 border-gray-300">
        <form onSubmit={handleSubmit} className="w-full lg:w-1/2 p-6 bg-white shadow-lg rounded-lg flex flex-col justify-center border mb-4 lg:mb-0">
          <label htmlFor="source-account" className="block text-gray-700 text-sm font-bold mb-2">Select Card Type:</label>
          <select id="source-account" value={cardType} onChange={(e) => setCardType(e.target.value)} className="block appearance-none w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4">
            <option value="">SELECT AN OPTION</option>
            <option value="DEBIT">DEBIT</option>
            <option value="CREDIT">CREDIT</option>
          </select>

          <label htmlFor="card-color" className="block text-gray-700 text-sm font-bold mb-2">Select Card Color:</label>
          <select id="card-color" value={cardColor} onChange={(e) => setCardColor(e.target.value)} className="block appearance-none w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4">
          <option value="">SELECT AN OPTION</option>
            <option value="SILVER">SILVER</option>
            <option value="GOLD">GOLD</option>
            <option value="TITANIUM">TITANIUM</option>
          </select>

          <button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
            Submit
          </button>
        </form>
        <div className="w-full lg:w-1/2 flex items-center justify-center">
          <img src="/src/assets/img/applyCard.png" alt="Apply Card" className="max-w-full max-h-full rounded-lg"/>
        </div>
      </div>
    </div>
  );
};

export default RequestCard;