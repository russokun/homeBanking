import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { useSelector } from "react-redux";

const AccountSelected = () => {
  const { accountId } = useParams();
  const [account, setAccount] = useState(null);
  const [transactions, setTransactions] = useState([]);

  const token = useSelector((store) => store.authReducer.token);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/clients/accounts/${accountId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        console.log("Response:", response.data);
        setAccount(response.data);

        // Formatear las fechas de las transacciones
        const formattedTransactions = response.data.transactions.map(
          (transaction) => {
            const date = new Date(transaction.date);
            return {
              ...transaction,
              date: date.toLocaleDateString("es-ES", {
                day: "2-digit",
                month: "2-digit",
                year: "2-digit",
              }),
            };
          }
        );

        setTransactions(formattedTransactions);
      })
      .catch((error) => {
        console.error("Error fetching account data:", error);
      });
  }, [token]);

  return (
    <div className="flex flex-col w-full h-[84vh] bg-slate-200 p-4 sm:p-0">
      <h1 className="font-extrabold text-3xl mt-5 self-center ml-24">
        Your Selected Account
      </h1>
      {account && (
        <div className="flex font-bold text-2xl mt-5 ml-3 justify-center">
          <button className="bg-white p-6 rounded-lg border-2 border-gray-300 shadow-lg w-full sm:w-[30%] mt-10">
            <h2 className="text-xl font-bold mb-4">
              Account Number: {account.number}
            </h2>
            <h2 className="text-xl font-bold mb-4">
              Amount: {account.balance}
            </h2>
            <h2 className="text-xl font-bold">
              Date Created: {account.creationDate}
            </h2>
          </button>
        </div>
      )}
      <div className="w-full mt-10 flex flex-col">
        <h2 className='mt-12 font-bold text-2xl self-center'>Transactions Resume:</h2>
        <table className='table-auto w-full'>
          <thead>
            <tr>
              <th className='px-4 py-2'>Type</th>
              <th className='px-4 py-2'>Amount</th>
              <th className='px-4 py-2'>Date</th>
              <th className='px-4 py-2'>Description</th>
            </tr>
          </thead>
          <tbody>
            {transactions && transactions.map(transaction => (
              <tr key={transaction.id} className={`border px-4 py-2 text-center text-black ${transaction.type == 'CREDIT' ? 'bg-green-200' : 'bg-red-200'} `}>
                <td>{transaction.type}</td>
                <td>{transaction.amount}</td>
                <td>{transaction.date}</td>
                <td>{transaction.description}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default AccountSelected;
