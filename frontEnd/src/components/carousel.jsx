"use client";
import React from "react";
import { Carousel } from "flowbite-react";
import Access from "../assets/images/access.jpg";
import BusinessFinancial from "../assets/images/businessFinancial.jpg";
import HouseLoan from "../assets/images/houseLoan.jpg";
import CyberSale from "../assets/images/cyberSale.jpg";

function Carousel() {
  return (
    <div className="flex h-56 sm:h-64 xl:h-80 2xl:h-96 justify-center mt-5">
      <Carousel className="w-5/6">
        <img src={Access} alt="..." />
        <img src={BusinessFinancial} alt="..." />
        <img src={CyberSale} alt="..." />
        <img src={HouseLoan} alt="..." />
      </Carousel>
    </div>
  );
}

export default Carousel;