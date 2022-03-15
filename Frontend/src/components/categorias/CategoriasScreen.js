import React, { useEffect, useRef, useState } from 'react';
import Card from "react-bootstrap/Card";
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
export const CategoriasScreen = () => {

  const isMounted = useRef(true);

  const [state, setState] = useState({
      data:[]
  });
 
  useEffect(()=> {
    return () => {
      isMounted.current = false;
    }
  }, []);

  const navigate = useNavigate();
  
  useEffect(() => {
    fetch("http://localhost:8080/api/categorias")
      .then((response) => response.json())
      .then((categorias) => {
        if(isMounted.current){  
          setState({
            data: categorias
          });
        }})
      },[isMounted]);

  const {data} = state;
  const handlerAddCategory = () => {

    navigate('/agregarCategoria')
  }

  const handleUpdateCategory = (cats) => {
    navigate(`/actualizarCategoria/${cats.id}`, {state: { cats}})
  }

 const handleDeleteCategory = (id) => {
   Swal.fire({
    title: '¿Estás seguro?',
    text: "¡No podrás revertir esto!",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Si, eliminar!',
    cancelButtonText: 'Cancelar'
  }).then((result) => {
    if (result.isConfirmed) {
      fetch(`http://localhost:8080/api/categorias/${id}`, 
    { method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
    } 
    })
  .then(() => {
    Swal.fire({
      title:'Eliminado!',
      text:'La categoria ha sido eliminada.',
      icon:'success',
    });
    window.setTimeout(() => {
      window.location.reload();
    }, 2000)
   
  });
      
}
  })

 }
  return (
    <div>
    <Card className="card" >
      <div className="card-header">
        <strong>Listado de Categorias</strong>
      </div>
      <div className="card-body mt-1">
      <button 
        type="button" 
        className="btn btn-primary"
        onClick={handlerAddCategory}
        >Agregar Categoria</button>
      <table className="table mt-2">
        <thead className="table-dark">
          <tr>
            <th>#</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody className="animate__animated animate__fadeIn animate__slow">
          {
            data.map(cats => (
              <tr key={cats.id}>
                <td>{cats.id}</td>
                <td>{cats.nombre}</td>
                <td>{cats.descripcion}</td>
                <td>
                  <button 
                        type="button" 
                        className="btn btn-info"
                        onClick={() => handleUpdateCategory(cats)}>
                        Actualizar
                  </button>
                </td>
                <td>
                  <button 
                    type="button" 
                    className="btn btn-danger"
                    onClick={() => handleDeleteCategory(cats.id)}>Eliminar</button></td>
              </tr>
                  )
                )
              }
        </tbody>
      </table>
      </div>
    </Card>
    </div>
  )
}
