import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { Navbar } from '../ui/Navbar'
import { DashboardRoutes } from '../routers/DashboardRoutes'

export const Approuter = () => {
  return (
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<Navbar/>}/>
            <Route path="/*" element={<DashboardRoutes/>}/>
        </Routes>
        
    </BrowserRouter>
  )
}
