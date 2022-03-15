import React from 'react'
import { Route, Routes } from 'react-router-dom'
import { AgregarCategoria } from '../components/categorias/AgregarCategoria'
import { CategoriasScreen } from '../components/categorias/CategoriasScreen'

import { Navbar } from '../ui/Navbar'

export const DashboardRoutes = () => {
  return (
    <>
        <Navbar/>
        <div className="container">
            <Routes>
                <Route path="categorias" element={<CategoriasScreen />} />
                <Route path="/agregarCategoria" element={<AgregarCategoria />} />
                <Route path="/actualizarCategoria/:id" element={<AgregarCategoria />} />
            </Routes>
        </div>
    </>
  )
}
