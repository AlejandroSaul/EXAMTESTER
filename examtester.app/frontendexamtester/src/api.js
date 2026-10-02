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
  // Con FormData el navegador debe fijar Content-Type: multipart/form-data;
  // boundary=... Si lo forzamos a application/json se pierde el boundary y
  // Spring no puede enlazar el MultipartFile.
  const esFormData =
    typeof FormData !== "undefined" && options.body instanceof FormData;
  if (options.body && !esFormData && !headers["Content-Type"]) {
    headers["Content-Type"] = "application/json";
  }
  return fetch(`${baseURL}${url}`, { ...options, headers });
}
