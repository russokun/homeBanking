"use client";
import React from "react";
import { Carousel } from "flowbite-react";

export function Component() {
  return (
    <div className="flex h-56 sm:h-64 xl:h-80 2xl:h-96 justify-center mt-5">
      <Carousel className="w-5/6">
        <img src="/src/assets/images/access.jpg" alt="..." />
        <img src="/src/assets/images/businessFinancial.jpg" alt="..." />
        <img src="/src/assets/images/houseLoan.jpg" alt="..." />
        <img src="/src/assets/images/cyberSale.jpg" alt="..." />
      </Carousel>
    </div>
  );
}

export default Component;