import React from 'react';
import Header from '../components/header'
import Accounts from '../components/accounts'
import Footer from '../components/footer'
import SelectedAccountContext from '../SelectedAccountContext';
const AccountLayout = () => {
  const { setSelectedAccount } = useContext(SelectedAccountContext);

  return (
    <>
      
      <Accounts setSelectedAccount={setSelectedAccount}/>
      
      
    </>
  );
};

export default AccountLayout;