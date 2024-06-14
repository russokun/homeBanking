import React from 'react';
import { Button, Card } from 'flowbite-react';
import { HiOutlineHand, HiOutlineShieldCheck, HiOutlineCurrencyDollar, HiOutlineCreditCard, HiOutlineSwitchHorizontal} from 'react-icons/hi';
import { HiOutlineBanknotes } from "react-icons/hi2";
import { HiOutlineWallet } from "react-icons/hi2";


export default function Landing() {
  return (
    <div className="bg-[#0F4C81] min-h-screen">
      <main className="container mx-auto px-4 py-16">
        <section className="text-center text-white mb-16">
          <h1 className="text-5xl font-bold mb-4">Welcome to MindHubBrothers Bank!</h1>
          <p className="text-xl">Your reliable partner for digital banking solutions.</p>
        </section>
        <section className="mb-16">
          <h2 className="text-3xl text-white font-bold mb-4">Why Choose Us?</h2>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
            <Card className="bg-white">
              <div className="card-body">
                <HiOutlineHand className="h-8 w-8 text-[#0F4C81]" />
                <h3 className="text-lg font-semibold">Personalized Service</h3>
                <p>Our dedicated team provides personalized attention to each customer.</p>
              </div>
            </Card>
            <Card className="bg-white">
              <div className="card-body">
                <HiOutlineShieldCheck className="h-8 w-8 text-[#0F4C81]" />
                <h3 className="text-lg font-semibold">Secure Banking</h3>
                <p>Your financial information is protected with our advanced security measures.</p>
              </div>
            </Card>
            <Card className="bg-white">
              <div className="card-body">
                <HiOutlineWallet className="h-8 w-8 text-[#0F4C81]" />
                <h3 className="text-lg font-semibold">Convenience</h3>
                <p>Manage your finances anytime, anywhere with our user-friendly digital platform.</p>
              </div>
            </Card>
            <Card className="bg-white">
              <div className="card-body">
                <HiOutlineBanknotes className="h-8 w-8 text-[#0F4C81]" />
                <h3 className="text-lg font-semibold">Financial Stability</h3>
                <p>Enjoy peace of mind with our strong financial standing and reliable services.</p>
              </div>
            </Card>
          </div>
        </section>
        <section className="mb-16">
          <h2 className="text-3xl text-white font-bold mb-4">Explore Our Services</h2>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            <Card className="bg-white">
              <div className="card-body">
                <HiOutlineCurrencyDollar className="h-8 w-8 text-[#0F4C81]" />
                <h3 className="text-lg font-semibold">Accounts</h3>
                <p>Open and manage multiple accounts with our user-friendly platform.</p>
              </div>
            </Card>
            <Card className="bg-white">
              <div className="card-body">
                <HiOutlineCreditCard className="h-8 w-8 text-[#0F4C81]" />
                <h3 className="text-lg font-semibold">Cards</h3>
                <p>Choose from a variety of credit and debit cards to fit your needs.</p>
              </div>
            </Card>
            <Card className="bg-white">
              <div className="card-body">
                <HiOutlineCreditCard className="h-8 w-8 text-[#0F4C81]" />
                <h3 className="text-lg font-semibold">Loans</h3>
                <p>Get competitive rates on personal and business loans to achieve your financial goals.</p>
              </div>
            </Card>
            <Card className="bg-white">
              <div className="card-body">
                <HiOutlineSwitchHorizontal className="h-8 w-8 text-[#0F4C81]" />
                <h3 className="text-lg font-semibold">Transfers</h3>
                <p>Send and receive money instantly with low fees and secure transactions.</p>
              </div>
            </Card>
            <Card className="bg-white">
              <div className="card-body">
                <HiOutlineWallet className="h-8 w-8 text-[#0F4C81]" />
                <h3 className="text-lg font-semibold">Digital Wallets</h3>
                <p>Securely store and manage your digital payments with our mobile app.</p>
              </div>
            </Card>
            <Card className="bg-white">
              <div className="card-body">
                <HiOutlineBanknotes className="h-8 w-8 text-[#0F4C81]" />
                <h3 className="text-lg font-semibold">Savings Accounts</h3>
                <p>Earn higher interest rates on your savings with our competitive accounts.</p>
              </div>
            </Card>
          </div>
        </section>
      </main>
    </div>
  );
}
