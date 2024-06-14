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
      const response = await axios.post('http://localhost:8080/api/auth/login', user);
      let token = response.data;
      const responseCurrent = await axios.get('http://localhost:8080/api/auth/current', {
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
    <div className='w-full h-[80vh] bg-slate-200 flex justify-center items-center'>
      <div className='bg-white p-8 rounded-lg shadow-lg'>
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
        </form>
      </div>
    </div>
  );
};

export default Login;