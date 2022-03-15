import React from 'react';
import { Link,NavLink } from 'react-router-dom';
export const Navbar = () => {
  return (
    <>
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <div className="container-fluid">
        <Link 
                className="navbar-brand" 
                to="/"
            >
                <em >YPBC</em>
            </Link>
           <div className="collapse navbar-collapse">
              <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                <li className="nav-item">
                  <NavLink 
                    className={ ({isActive}) => 'nav-item nav-link' + (isActive ? ' active' : '')} 
                    to="/usuarios">Usuarios</NavLink>
                </li>
                <li className="nav-item">
                  <NavLink 
                    className={ ({isActive}) => 'nav-item nav-link' + (isActive ? ' active' : '')} 
                    to="/metas">Metas</NavLink>
                </li>
                <li className="nav-item">
                  <NavLink 
                    className={ ({isActive}) => 'nav-item nav-link' + (isActive ? ' active' : '')} 
                    to="/categorias">Categorias</NavLink>
                </li>
                <li className="nav-item">
                  <NavLink 
                    className={ ({isActive}) => 'nav-item nav-link' + (isActive ? ' active' : '')} 
                    to="/estados">Estados</NavLink>
                </li>
              </ul>
            </div>
            <div className="navbar-collapse collapse w-100 order-3 dual-collapse2 d-flex justify-content-end">
                <ul className="navbar-nav ml-auto">
                    <span 
                        className="nav-item nav-link text-info"
                    >
                        Yesika
                    </span>
                    <button 
                        className="nav-item nav-link btn" 
                    >
                        Logout
                    </button>
                </ul>
            </div>
        </div>
    </nav>
    <div className="container mt-3"></div>
    </>
  )
}

