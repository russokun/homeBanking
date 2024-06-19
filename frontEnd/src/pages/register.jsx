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
    };

    // Realizar registro del usuario con axios
    try {
      const response = await axios.post('http://localhost:8080/api/auth/signup', user);
      if (response.status === 201) {
        alert('user registered successfully');
      }
    } catch (error) {
      console.log('Error al registrar:', error);
      alert('Error at register user', error);
    }
  };

  function verifyForm(){
    let error ={}
    if(!email.includes('@')){
      error.mail = 'Invalid email format'
    }
    if(password.length < 5){
      error.password = 'Password must be at least 5 characters long'
    }
    if(password.length > 10){
      error.password = 'Password must be at most 10 characters long'
    }
    return error;
  }
  const errors = verifyForm();

  return (
    <div className='w-full h-[84vh]  flex justify-center items-center' style={{ backgroundImage: `url(https://www.shutterstock.com/image-vector/bank-account-registration-3d-isometric-600nw-2043550520.jpg)`, backgroundSize: 'cover',backgroundPosition: 'center'}}>
      <div className='bg-white p-8 rounded-lg shadow-lg flex flex-col'>
      <img src="https://cdn-icons-png.flaticon.com/512/6341/6341964.png" className='size-20 self-center' alt="bank logo" />
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
            {errors.mail && <p className="text-red-500">{errors.mail}</p>}
          </label>
          <label className='block mb-4'>
            Password:
            <input
              type="password"
              className='border border-gray-300 rounded-md px-3 py-2 w-full'
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {errors.password && <p className="text-red-500">{errors.password}</p>}
          </label>
          <button
            type="submit"
            className='bg-blue-500 hover:bg-blue-600 text-white rounded-md px-4 py-2'
            onClick={handleRegister}
          >
            Register
          </button>
          <p className="text-center mt-4">or <a href="/login" className="text-blue-500 hover:underline">Login</a></p>
        </form>
      </div>
    </div>
  );
};

export default Register;