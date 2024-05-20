import React from 'react';
import Carousel from './carousel';

const AccountSelected = () => {
  return (
    <div className='flex w-full h-[80vh] flex-col bg-slate-200'>
      <h1 className='font-extrabold text-3xl mt-5 justify-start ml-24'>Your Selected Account</h1>
      <div className='flex font-bold text-2xl mt-5 justify-start ml-3'>
      <button className="bg-white p-6 rounded-lg border-2 border-gray-300 shadow-lg w-[30%] mt-10">
        <h2 className="text-xl font-bold mb-4">Accout Number:</h2>
        <h2 className="text-xl font-bold mb-4">Amount:</h2>
        <h2 className="text-xl font-bold">Date Created:</h2>
      </button>
      <div className='ml-10 w-full'>
        <h2 className='mt-12'>Transactions Resume</h2>
        <figure className='bg-black w-2/3 h-20 mt-5'>a</figure>
      </div>
      </div>
      <Carousel>
      </Carousel>
    </div>
  );
};

export default AccountSelected;