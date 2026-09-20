const baseURL = process.env.REACT_APP_API_URL;

export function getToken() {
  return localStorage.getItem("token");
}

export function setAuth(token, nombre) {
  localStorage.setItem("token", token);
  localStorage.setItem("nombre", nombre);
}

export function getNombre() {
  return localStorage.getItem("nombre");
}

export function clearAuth() {
  localStorage.removeItem("token");
  localStorage.removeItem("nombre");
}

export function isAuthenticated() {
  return !!getToken();
}

export function authFetch(url, options = {}) {
  const token = getToken();
  const headers = { ...(options.headers || {}) };
  if (token) {
    headers["Authorization"] = `Bearer ${token}`;
  }
  if (options.body && !headers["Content-Type"]) {
    headers["Content-Type"] = "application/json";
  }
  return fetch(`${baseURL}${url}`, { ...options, headers });
}
