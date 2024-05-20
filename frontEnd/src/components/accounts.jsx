import React, { useEffect, useState } from 'react';
import Carousel from './carousel';
import axios from 'axios';

const Accounts = () => {
  const [data, setData] = useState([]);
  useEffect(() => {
    axios.get('http://localhost:8080/api/clients/')
      .then(response => {
        setData(response.data);
        console.log(response.data);
      })
      .catch(error => console.log(error));
  }, []);

  const melba = data[1];
  console.log(melba);

  const [selectedAccount, setSelectedAccount] = useState(null);

  return (
    <div className='w-full h-[80vh] bg-slate-200'>
      <h1 className='font-extrabold text-3xl pt-5 justify-start ml-3'>
        Welcome, {melba ? melba.firstName : 'loading'}!
      </h1>
      <div className='flex mt-7 w-full justify-center gap-14'>
  {melba && melba.accounts && melba.accounts.map(account => (
    <button key={account.id} className="bg-white p-6 rounded-lg border-2 border-gray-300 shadow-lg" onClick={() => setSelectedAccount(account)}>
      <h2 className="text-xl font-bold mb-4">Account Number: {account.number}</h2>
      <h2 className="text-xl font-bold mb-4">Amount: {account.balance}</h2>
      <h2 className="text-xl font-bold">Date Created: {account.creationDate}</h2>
    </button>
  ))}
  {selectedAccount && <h2>Selected Account Number: {selectedAccount.number}</h2>}
</div>
      <div className='flex justify-center'>
        <a href="#" className="bg-blue-500 text-white font-bold py-2 px-4 rounded-full border-2 border-white shadow-lg hover:bg-blue-700 transition duration-300 ease-in-out mt-5">Request Account</a>
      </div>
      <Carousel>
      </Carousel>
    </div>
  );
};
export default Accounts;