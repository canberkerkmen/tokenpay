import axios from 'axios';
import authHeader from './auth-header';

import { loginAPI,orderAPI,productsAPI } from '../util';

class UserService {

  getUser() {
    return axios.get(loginAPI + 'me', { headers: authHeader() });
  }

  getProducts() {
    return axios.get(productsAPI);
  }

  addOrder(productCode, quantity){
    return axios.post(orderAPI + "add", { productCode, quantity }, { headers: authHeader() });  
  }

  editOrder(productCode, quantity){
    return axios.post(orderAPI + "edit", { productCode, quantity }, { headers: authHeader() });  
  }
}

export default new UserService();
