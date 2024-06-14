import React from 'react';

const MakeTransaction = () => {
  return (
  <div className="bg-slate-200 flex flex-col items-center min-h-max md:h-[80vh]">

    <h1 className="font-extrabold text-3xl pt-5 mb-5">Make a Transaction</h1>
  
    <div className="flex flex-col md:flex-row w-full h-full md:h-auto bg-slate-200 justify-center items-center md:items-start p-5">
      <div className="flex flex-col w-full md:w-1/2">
        <form className="bg-white p-8 rounded-lg shadow-md w-full border-2 border-gray-300  lg:h-[450px] xl:h-[500px]">
          <div className="mb-4 flex gap-3 flex-wrap">
            <label className="block text-gray-700 text-sm font-bold mb-2">Type of Destiny:</label>
            <div className="flex items-center mb-2">
              <input id="option1" type="radio" name="options" value="option1" className="mr-2"/>
              <label htmlFor="option1" className="text-gray-700">Own</label>
            </div>
            <div className="flex items-center mb-2">
              <input id="option2" type="radio" name="options" value="option2" className="mr-2"/>
              <label htmlFor="option2" className="text-gray-700">Another's</label>
            </div>
          </div>
  
          <label htmlFor="select" className="block text-gray-700 text-sm font-bold mb-2">Source Account:</label>
          <select id="select" className="block appearance-none w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4">
            <option>VIN-001</option>
            <option>VIN-002</option>
          </select>
  
          <label htmlFor="amount" className="block text-gray-700 text-sm font-bold mb-2">Amount ($):</label>
          <input type="number" id="amount" name="amount" placeholder="Enter amount" className="block w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4"/>
  
          <label htmlFor="description" className="block text-gray-700 text-sm font-bold mb-2">Description:</label>
          <textarea id="description" name="description" placeholder="Enter description" className="block w-full bg-white border max-h-20 border-gray-300 hover:border-gray-500 px-4 py-2 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4"></textarea>
  
          <button type="submit" className="bg-green-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full">
            Submit
          </button>
        </form>
      </div>
  
      <div className="w-full md:w-1/2 flex justify-start items-center md:ml-0 ">
        <img src="/src/assets/img/makeTransaction.png" alt="Making a transaction." className="w-full md:w-auto sm:h-[450px] mt-[20px] md:mt-0 xl:h-[500px] rounded-lg" />
      </div>
    </div>
  
  </div>
  );
};

export default MakeTransaction;