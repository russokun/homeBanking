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
import Landing from './layouts/landing';
import { useSelector } from 'react-redux';
const authRoutes = [
  {
    path: '/accounts',
    element: <Accounts />,
    key: 'accounts',
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
const NoAuthRoutes = [
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

  const loggedIn = useSelector(state => state.authReducer.loggedIn);

  return (
    <Router>
      <Header />
      <Routes>
        {loggedIn ? (
          authRoutes.map((route) => (
            <Route
              key={route.key}
              path={route.path}
              element={route.element}
            />
          ))
        ) : (
          NoAuthRoutes.map((route) => (
            <Route
              key={route.key}
              path={route.path}
              element={route.element}
            />
          ))
        )}
        {publicRotues.map((route) => (
          <Route
            key={route.key}
            path={route.path}
            element={route.element}
          />
        ))}
      </Routes>
      <Footer />
    </Router>
  );
}

export default App
