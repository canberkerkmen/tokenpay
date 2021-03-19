import axios from "axios";
import authService from "./auth/auth.service";
import userService from "./auth/user.service";
import router from "./router/router";
import { loginAPI } from "./util";

// Add a request interceptor
axios.interceptors.request.use(
   config => {
       const token = localStorage.getItem("JWT");
       if (token) {
           config.headers['Authorization'] = 'Bearer ' + token;
       }
       // config.headers['Content-Type'] = 'application/json';
       return config;
   },
   error => {
       Promise.reject(error)
   });

//Add a response interceptor

axios.interceptors.response.use((response) => {
   return response
}, function (error) {
   const originalRequest = error.config;

   if (error.response.status === 401 && !originalRequest._retry) {

       originalRequest._retry = true;
       return  authService.login()
       .then(res => {
               if (res.status === 201) {
                   axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem("JWT");;
                   return axios(originalRequest);
               }
           })
   }
   return Promise.reject(error);
});