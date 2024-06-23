import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useSelector } from 'react-redux';

const RequestLoan = () => {
  const [loanId, setLoanType] = useState('');
  const [destinationAccountNumber, setDestinationAccount] = useState('');
  const [loanAmount, setLoanAmount] = useState('');
  const [installments, setInstallments] = useState('');
  const [accounts, setAccounts] = useState([]);
  const [payments, setPayments] = useState([]);
  const [loans, setLoans] = useState([]);

  const token = useSelector(store => store.authReducer.token);

  useEffect(() => {
    const fetchAccounts = async () => {
      try {
        const response = await axios.get('https://homebank1ngg.onrender.com/api/clients/current/accounts', {
            headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        console.log(response.data);
        setAccounts(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    const fetchLoans = async () => {
      try {
        const response = await axios.get('https://homebank1ngg.onrender.com/api/loans', {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        setLoans(response.data);
        
      } catch (error) {
        console.error(error);
      }
    };

    fetchAccounts();
    fetchLoans();
  }, [token]);

  useEffect(() => {
    const selectedLoan = loans.find(loan => loan.id == loanId);
    if (selectedLoan) {
      console.log(selectedLoan);
      setPayments(selectedLoan.payments);
    }
  }, [loanId, loans]);

  const handleSubmit = async (event) => {
    event.preventDefault();

    // Validación básica
    if (!loanId || !loanAmount || !installments || !destinationAccountNumber) {
      alert('Please fill in all fields');
      return;
    }

    const data = {
      loanId: loanId,
      amount: loanAmount,
      installments: installments,
      destinationAccountNumber: destinationAccountNumber,
    };

    try {
      const response = await axios.post('https://homebank1ngg.onrender.com/api/loans', data, {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      });
      alert('Loan requested successfully')
      console.log(response.data);
    } catch (error) {
      alert('Error at request the loan:', error);
      console.error(error);
    }
  };

  function verifyForm(){
    let error = ""
    if (loanId == '' || loanAmount == '' || installments == '' || destinationAccountNumber == ''){
      error = "Please valid fields to continue."
    }
    if (loanAmount <= 0){
      error.ammount = "The loan amount must be greater than 0."
    }
    return error
  }
  const error = verifyForm()
  console.log(destinationAccountNumber)
  console.log(loanAmount, loanId, installments)
  return (
    <div className="bg-slate-200 flex flex-col items-center h-full md:h-[84vh] mx-auto md:px-6 lg:px-8">
      <h1 className="font-extrabold text-3xl pt-5 mb-5">Apply for a Loan</h1>
      <div className="flex flex-col md:flex-row w-full h-full md:h-auto bg-slate-200 justify-center items-center md:items-start p-5 mt-10">
        <div className="flex flex-col w-full md:w-1/2 md:flex md:items-center">
          <form onSubmit={handleSubmit} className="bg-white p-8 rounded-lg shadow-md w-full border-2 border-gray-300">
            <select id="loanId" value={loanId} onChange={(e) => setLoanType(e.target.value)} className="block appearance-none w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4">
            <option value="">SELECT AN OPTION</option>
              {loans.map(loan => (
                <option key={loan.id} value={loan.id}>{loan.name} ({loan.id})</option>
              ))}
            </select>
            {verifyForm() && <p className="text-red-500 text-xs italic">{verifyForm()}</p>}
            <select id="destinationAccountNumber" value={destinationAccountNumber} onChange={(e) => setDestinationAccount(e.target.value)} className="block appearance-none w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4">
            <option value="">SELECT AN OPTION</option>
              {accounts.map(account => (
                <option key={account.id} value={account.number}>{account.number}</option>
              ))}
            </select>
            {verifyForm() && <p className="text-red-500 text-xs italic">{verifyForm()}</p>}
            <input type="number" id="loanAmount" value={loanAmount} onChange={(e) => setLoanAmount(e.target.value)} className="block appearance-none w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4" placeholder="Loan Amount"/>
            {error.ammount && <p className="text-red-500 text-xs italic">{error.ammount}</p>}
            <select id="installments" value={installments} onChange={(e) => setInstallments(e.target.value)} className="block appearance-none w-full bg-white border border-gray-300 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline mb-4">
            <option value="">SELECT AN OPTION</option>
              {payments.map(paymentOption => (
                <option key={paymentOption} value={paymentOption}>{paymentOption}</option>
              ))}
            </select>
            {verifyForm() && <p className="text-red-500 text-xs italic">{verifyForm()}</p>}
            <button type="submit" className="bg-green-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full">
              Submit
            </button>
          </form>
        </div>
        
          <img src="/src/assets/images/loan.png" alt="Making a transaction." className="sm:size-auto md:size-80 rounded-lg" />
        
      </div>
    </div>
  );
};

export default RequestLoan;