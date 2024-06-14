import React from 'react';

const RequestCard = () => {
  return (
    <body className="bg-gray-100 flex flex-col items-center justify-center h-screen">
    <div className="text-center mb-6">
      <h1 className="font-extrabold text-3xl pt-5 justify-start ml-3">Apply for a Card</h1>
    </div>
    <div className="flex flex-col lg:flex-row w-11/12 lg:w-3/4 h-3/4 bg-white p-8 rounded-lg shadow-md border-2 border-gray-300">
      <form className="w-full lg:w-1/2 p-6 bg-white shadow-lg rounded-lg flex flex-col justify-center border mb-4 lg:mb-0">
        <label htmlFor="source-account" className="block text-gray-700 text-sm font-bold mb-2">Select Card Type:</label>
        <select id="source-account" className="block appearance-none w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4">
          <option>DEBIT</option>
          <option>CREDIT</option>
        </select>

        <label htmlFor="card-membership" className="block text-gray-700 text-sm font-bold mb-2">Select Card Membership(Color):</label>
        <select id="card-membership" className="block appearance-none w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4">
          <option>SILVER</option>
          <option>GOLD</option>
          <option>TITANIUM</option>
        </select>

        <div className="flex justify-evenly mt-4">
          <button type="submit" className="bg-green-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full">
            Submit
          </button>
          <button type="button" className="bg-red-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full">
            Cancel
          </button>
        </div>
      </form>

      <div className="w-full lg:w-1/2 flex items-center justify-center">
        <img src="/src/assets/img/applyCard.png" alt="Apply Card" className="max-w-full max-h-full rounded-lg"/>
      </div>
    </div>
  </body>
  );
};

export default RequestCard;