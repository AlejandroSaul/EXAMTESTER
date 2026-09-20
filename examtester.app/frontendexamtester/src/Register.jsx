import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { authFetch } from "./api";

export default function Register() {
  const navigate = useNavigate();
  const [nombre, setNombre] = useState("");
  const [correoElectronico, setCorreoElectronico] = useState("");
  const [password, setPassword] = useState("");
  const [mensaje, setMensaje] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMensaje("");
    try {
      const res = await authFetch("/api/auth/registro", {
        method: "POST",
        body: JSON.stringify({ nombre, correoElectronico, password }),
      });
      const data = await res.json();
      if (data.codigo === 0) {
        navigate("/login");
      } else {
        setMensaje(data.mensaje || "Error al registrar");
      }
    } catch (err) {
      setMensaje("Error de conexión");
    }
  };

  return (
    <div className="row justify-content-center mt-4">
      <div className="col-md-5">
        <div className="card">
          <div className="card-body">
            <h5 className="card-title text-center">Registrar Cuenta</h5>
            <form onSubmit={handleSubmit}>
              <div className="mb-3">
                <label className="form-label">Nombre</label>
                <input
                  type="text"
                  className="form-control"
                  value={nombre}
                  onChange={(e) => setNombre(e.target.value)}
                  required
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Correo electrónico</label>
                <input
                  type="email"
                  className="form-control"
                  value={correoElectronico}
                  onChange={(e) => setCorreoElectronico(e.target.value)}
                  required
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Contraseña</label>
                <input
                  type="password"
                  className="form-control"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>
              {mensaje && <div className="alert alert-danger py-2">{mensaje}</div>}
              <button type="submit" className="btn btn-success w-100">
                Registrarse
              </button>
            </form>
            <div className="text-center mt-3">
              <Link to="/login">¿Ya tienes cuenta? Inicia sesión</Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
