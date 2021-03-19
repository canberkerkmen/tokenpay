import { FETCH_PRODUCTS } from './actionTypes';

import userService from '../auth/user.service';

const compare = {
  lowestprice: (a, b) => {
    if (a.price < b.price) return -1;
    if (a.price > b.price) return 1;
    return 0;
  },
  highestprice: (a, b) => {
    if (a.price > b.price) return -1;
    if (a.price < b.price) return 1;
    return 0;
  }
};

export const fetchProducts = (callback) => dispatch => {
  return userService.getProducts()
    .then(res => {

      if (!!callback) {
        callback();
      }

      return dispatch({
        type: FETCH_PRODUCTS,
        payload: res.data
      });
    })
    .catch(err => {
      console.log('Could not fetch products. Try again later.');
    });
};