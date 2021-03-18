export const formatPrice = (x, currency) => {
  switch (currency) {
    case 'BRL':
      return x.toFixed(2).replace('.', ',');
    default:
      return x.toFixed(2);
  }
};

//export const productsAPI = 'https://react-shopping-cart-67954.firebaseio.com/products.json';
//export const productsAPI = "http://localhost:8001/api/products";

export const productsAPI = "http://localhost:4567/products/getAll";

export const loginAPI = "http://localhost:4567/users/"

export const signinAPI = loginAPI + "signin"

export const signupAPI = loginAPI + "signup"

export const orderAPI = "http://localhost:4567/orders/"
