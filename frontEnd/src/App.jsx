import './App.css'
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom'
import React from 'react'; // Import React
import Header from './components/header';
import Footer from './components/footer';
import Accounts from './pages/accounts';
import Cards from './pages/cards';
import Loans from './pages/loans';
import MakeTransaction from './pages/makeTransaction';
import RequestLoan from './pages/requestLoan';
import RequestCard from './pages/requestCard';
import Login from './pages/login';
import Register from './pages/register';
import Landing from './layouts/Landing';
import AccountSelected from './components/accountSelected';
import AuthRoute from './components/HOCs/authRoute';
import NoAuthRoute from './components/HOCs/noAuthRoute';
import RouteHoc from './components/HOCs/routeHoc';

const authRoutes = [
  {
    path: '/accounts',
    element: <Accounts />,
    key: 'accounts',
  },
  {
    path: '/accounts/:accountId',
    element: <AccountSelected />,
    key: 'accountSelected',
  },
  {
    path: '/cards',
    element: <Cards />,
    key: 'cards',
  },
  {
    path: '/loans',
    element: <Loans />,
    key: 'loans',
  },
  {
    path: '/transactions',
    element: <MakeTransaction />,
    key: 'transactions',
  },
  {
    path: '/requestLoan',
    element: <RequestLoan />,
    key: 'requestLoan',
  },
  {
    path: '/requestCard',
    element: <RequestCard />,
    key: 'requestCard',
  },
]
const noAuthRoutes = [
  {
    path: '/login',
    element: <Login />,
    key: 'login',
  },
  {
    path: '/register',
    element: <Register />,
    key: 'register',
  }
]
const publicRotues = [
  {
    path: '/',
    element: <Landing />,
    key: 'home',
  }
]

function App() {
    
  return (
    <Router>
      <Header />
        <Routes>
          {authRoutes.map(AuthRoute)}

          {noAuthRoutes.map(NoAuthRoute)}
          
          {publicRotues.map(route => RouteHoc(route.element, route.path, route.key))}
          <Route path='*' element={<Navigate to='/' />} />
        </Routes>
      <Footer />
    </Router>
  );
}

export default App
