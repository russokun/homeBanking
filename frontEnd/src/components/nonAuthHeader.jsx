import React from 'react';

const NonAuthHeader = () => {
  return (
    <header className="container mx-auto px-4 py-4 flex justify-between items-center">
        <img src="/placeholder.svg" alt="Bank Logo" className="h-12" />
        <nav className="flex space-x-4">
          <Button outline={true} className="text-white border-white">
            Login
          </Button>
          <Button className="bg-white text-[#0F4C81]">Sign Up</Button>
        </nav>
      </header>
  );
};

export default NonAuthHeader;