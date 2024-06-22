import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import axios from 'axios';
import { login } from '../redux/actions/authActions';
import { useNavigate } from 'react-router-dom';


const Login = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  //tmb se puede usar useRef
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState({ email: '', password: '' });

  const handleLogin = async (event) => {
    event.preventDefault();
    
    if (!validateEmail(email)) {
      setError({ ...error, email: 'Incorrect email' });
      return;
    }
    setError({ ...error, email: '', password: '' });

    const user = {
      email,
      password
    };

    try {
      const response = await axios.post('https://homebank1ngg.onrender.com/api/auth/login', user);
      let token = response.data;
      const responseCurrent = await axios.get('https://homebank1ngg.onrender.com/api/auth/current', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      let client = responseCurrent.data;
      client.token = token;
      dispatch(login(client));
      navigate('/accounts'); // navigate en lugar de history.push
    } catch (error) {
      console.log('Error al autenticar:', error);
      setError({ ...error, password: 'Incorrect password' });
    }
  };

  const validateEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  return (
    <div className='w-full h-[84vh] bg-slate-200 flex justify-center items-center' style={{ backgroundImage: `url(https://static.vecteezy.com/system/resources/previews/002/644/868/original/concept-for-mobile-banking-and-online-payment-using-laptop-and-mobile-smart-phone-for-online-banking-and-accounting-flat-illustration-design-for-banner-landing-page-flyer-vector.jpg)`, backgroundSize: 'cover',backgroundPosition: 'center'}}>
      <div className='bg-white p-8 rounded-lg shadow-lg flex flex-col'>
        <img src="https://cdn-icons-png.flaticon.com/512/6341/6341964.png" className='size-20 self-center' alt="bank logo" />
        <h2 className='text-2xl font-bold mb-4'>Login</h2>
        <form onSubmit={handleLogin}>
          <label className='block mb-4 font-semibold'>
            Email:
            <input
              type="text"
              className='border border-gray-300 rounded-md px-3 py-2 w-full'
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            {error.email && <p className="text-red-500">{error.email}</p>}
          </label>
          <label className='block mb-4 font-semibold'>
            Password:
            <input
              type="password"
              className='border border-gray-300 rounded-md px-3 py-2 w-full'
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {error.password && <p className="text-red-500">{error.password}</p>}
          </label>
          <button
            type="submit"
            className='bg-blue-500 hover:bg-blue-600 text-white rounded-md px-4 py-2'
          >
            Login
          </button>
          <p className="text-center mt-4">or <a href="/register" className="text-blue-500 hover:underline">Register</a></p>
        </form>
      </div>
    </div>
  );
};

export default Login;