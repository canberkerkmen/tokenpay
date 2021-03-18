export default function authHeader() {
    const user = JSON.parse(localStorage.getItem('JWT'));
    if (user) {
      return { Authorization: 'Bearer ' + user };
    } else {
      return {};
    }
  }
  