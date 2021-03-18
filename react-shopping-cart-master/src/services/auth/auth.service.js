import axios from "axios";

import {  signinAPI, signupAPI } from '../util';


class AuthService {
  login() {
    return axios
      .get(signinAPI, { params: { username: "admin", password : "admin123" } })
      .then((response) => {
        if (response.data) {
          localStorage.setItem("JWT", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("JWT");
  }

  register(username, email, password) {
    return axios.post(signupAPI, {
      username,
      email,
      password,
    });
  }
}

export default new AuthService();
