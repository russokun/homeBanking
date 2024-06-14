import React, { useState } from 'react';
import axios from 'axios';


const Register = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');

  const handleRegister = async (event) => {
    event.preventDefault();
    const user = {
      firstName: firstName,
      lastName: lastName,
      email: email,
      password: password,
    }
    //realizar registro del usuario con axios
    try{
      const response = await axios.post('http://localhost:8080/api/auth/signup', user);
      if(response.status === 201){
        alert('Usuario registrado exitosamente');
      }
    }
    catch(error){
      console.log('Error al registrar:', error);
      alert('Error al registrar el usuario');
    }
  }
  return (
    <div className='w-full h-[80vh] bg-slate-200 flex justify-center items-center'>
      <div className='bg-white p-8 rounded-lg shadow-lg'>
        <h2 className='text-2xl font-bold mb-4'>Register</h2>
        <form>
          <label className='block mb-4'>
            First Name:
            <input
              type="text"
              className='border border-gray-300 rounded-md px-3 py-2 w-full'
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
            />
          </label>
          <label className='block mb-4'>
            Last Name:
            <input
              type="text"
              className='border border-gray-300 rounded-md px-3 py-2 w-full'
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
            />
          </label>
          <label className='block mb-4'>
            Email:
            <input
              type="text"
              className='border border-gray-300 rounded-md px-3 py-2 w-full'
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </label>
          <label className='block mb-4'>
            Password:
            <input
              type="password"
              className='border border-gray-300 rounded-md px-3 py-2 w-full'
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </label>
          <button
            type="submit"
            className='bg-blue-500 hover:bg-blue-600 text-white rounded-md px-4 py-2' onClick={handleRegister}
          >
            Register
          </button>
        </form>
      </div>
    </div>
  );
}

export default Register;