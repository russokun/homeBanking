import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import axios from 'axios';
import { login } from '../redux/actions/authActions';

const Login = () => {
  const dispatch = useDispatch();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const handleLogin = async (event) => {
    event.preventDefault();
    const user = {
      email,
      password
    }
    //realizar login del usuario con axios
    try{
      const response = await axios.post('http://localhost:8080/api/auth/login', user);
      let token = response.data;
      const responseCurrent = await axios.get('http://localhost:8080/api/auth/current', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      let client = responseCurrent.data;
      client.token = token;
      dispatch(login(client));
    }
    catch(error){
      console.log('Error al autenticar:', error);
    }
    
  };
  
  return (
    <div className='w-full h-[80vh] bg-slate-200 flex justify-center items-center'>
      <div className='bg-white p-8 rounded-lg shadow-lg'>
        <h2 className='text-2xl font-bold mb-4'>Login</h2>
        <form onSubmit={handleLogin}>
          <label className='block mb-4'>
            Username:
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
            className='bg-blue-500 hover:bg-blue-600 text-white rounded-md px-4 py-2'
          >
            Login
          </button>
        </form>
      </div>
    </div>
  );
}

export default Login;