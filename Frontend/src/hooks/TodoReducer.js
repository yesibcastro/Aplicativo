export const todoReducer = (state=[], action) => {

    switch (action.type) {
        case 'add':
            console.log(action.payload)
        return [...state, action.payload];  
        default:
            return state;
    }


}