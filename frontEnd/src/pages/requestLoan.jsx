import React from 'react';

const RequestLoan = () => {
  return (
    <div className="bg-slate-200 flex flex-col items-center h-full md:h-[84vh] mx-auto md:px-6 lg:px-8">

    <h1 className="font-extrabold text-3xl pt-5 mb-5">Apply for a Loan</h1>
  
    <div className="flex flex-col md:flex-row w-full h-full md:h-auto bg-slate-200 justify-center items-center md:items-start p-5">
      <div className="flex flex-col w-full md:w-1/2 md:flex md:items-center">
        <form className="bg-white p-8 rounded-lg shadow-md w-full border-2 border-gray-300  lg:h-[450px] xl:h-[500px]">
          
          <label htmlFor="select" className="block text-gray-700 text-sm font-bold mb-2">Select Loan:</label>
          <select id="select" className="block appearance-none w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4">
            <option>Personal Loan</option>
            <option>Car Loan</option>
            <option>Home Loan</option>
          </select>
  
        <label htmlFor="select" className="block text-gray-700 text-sm font-bold mb-2">Source Account:</label>
        <select id="select" className="block appearance-none w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4">
          <option>VIN-001</option>
          <option>VIN-002</option>
        </select>

          
  
          <label htmlFor="amount" className="block text-gray-700 text-sm font-bold mb-2">Amount ($):</label>
          <input type="number" id="amount" name="amount" placeholder="Enter amount" className="block w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4"/>
  
          <label htmlFor="select" className="block text-gray-700 text-sm font-bold mb-2">Payment:</label>
          <select id="select" className="block appearance-none w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4">
            <option>6</option>
            <option>12</option>
            <option>24</option>
            <option>36</option>
          </select>
  
          <button type="submit" className="bg-green-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full">
            Submit
          </button>
        </form>
      </div>
  
      <div className="w-full md:w-1/2 flex justify-center md:justify-start items-center md:ml-0">
        <img src="/src/assets/img/loan.png" alt="Making a transaction." className="w-full md:w-auto h-auto sm:h-[450px] mt-[20px] md:mt-0 xl:h-[500px] rounded-lg" />
      </div>
    </div>
  
  </div>
  );
};

export default RequestLoan;