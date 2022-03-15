const baseUrl = 'http://localhost:8080/api';

const agregarCategoria = (endpoint, data, method= 'GET') => {

    const url = baseUrl + endpoint;
    if(method === 'GET'){
        return fetch(url);
    }else{
        return fetch(url, {
            method,
            headers: {
                'Content-Type': 'application/json'
            }, 
            body: JSON.stringify(data)
        });
    }
}

export {
    agregarCategoria
}