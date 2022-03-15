import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useForm } from '../../hooks/useForm';
import Swal from 'sweetalert2';


export const AgregarCategoria = () => {

  let url = 'http://localhost:8080/api/categorias';
  let method = '';
  let location = useLocation();
 
  const [values, handleInputChange]= useForm({
    nombre: location.state !== null ? location.state.cats.nombre : '',
    descripcion:location.state !== null ? location.state.cats.descripcion : ''
  });

  const navigate = useNavigate();

  const {nombre, descripcion} = values;

  const handleSubmit = (e) => {
    e.preventDefault();

    if(location.state !== null){
        url = url + '/' + location.state.cats.id
        method = 'PUT'
    }else{
      method = 'POST'
    }

    fetch(url,{
      method,
      body: JSON.stringify(values),
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then((response) => response.json())
      //Then with the data from the response in JSON...
      .then((data) => {
        Swal.fire( {icon: 'success',title: `${location.state !== null ? 'Actualización exitosa': 'Registro exitoso' }`,text:`${data.mensaje}`});
        navigate('/categorias')
      })
      //Then with the error genereted...
      .catch((error) => {
        console.error('Error:', error);
      });
  }

  const handleVolver= () => {
    navigate(-1);
}


  return (
    <div className="card border-info mb-3 animate__animated animate__fadeIn">
      <div className="card-header"><strong>Agregar Categoria</strong></div>
      <div className="card-body">
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label
              className="form-label">
                Nombre
            </label>
            <input 
              type="text" 
              className="form-control" 
              name="nombre"
              value={nombre}
              onChange={handleInputChange} 
              aria-describedby="nombreHelp"/>
            <div 
              className="form-text">
                  Nombre de la nueva sección de categoria que se debe agregar al tablero de sueños
            </div>
          </div>
          <div className="mb-3">
            <label 
              className="form-label">
                Descripción
            </label>
            <input 
              type="text" 
              className="form-control" 
              name="descripcion"
              value={descripcion}
              onChange={handleInputChange}/>
            <div 
              className="form-text">
                  Descripción de lo que debe incluir la categoria en el tablero de sueños
            </div>
          </div>
          <button 
            type="submit" 
            className="btn btn-primary">{`${location.state !== null ? 'Actualizar' : 'Agregar'}`}</button>
          <button 
            type="button" 
            className="btn btn-danger m-2"
            onClick={handleVolver}>Volver</button>
        </form>
      </div>
    </div>
  )
}
