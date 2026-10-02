import React, { useState } from "react";
import { authFetch } from "../api";

export default function ImportarMasivo() {
  const [archivo, setArchivo] = useState(null);
  const [mensaje, setMensaje] = useState(null);
  const [tipo, setTipo] = useState("");
  const [cargando, setCargando] = useState(false);

  const subir = async () => {
    if (!archivo) {
      setTipo("danger");
      setMensaje("Selecciona un archivo Excel");
      return;
    }

    setCargando(true);
    setMensaje(null);

    // FormData: authFetch NO debe fijar Content-Type, el navegador añade el boundary
    const formData = new FormData();
    formData.append("file", archivo);

    try {
      const res = await authFetch("/api/examen/importar-excel", {
        method: "POST",
        body: formData,
      });

      if (res.status === 401) {
        setTipo("danger");
        setMensaje("Sesión no válida o expirada. Vuelve a iniciar sesión.");
        return;
      }
      if (!res.ok) {
        setTipo("danger");
        setMensaje(`Error HTTP ${res.status} al subir el archivo`);
        return;
      }

      const data = await res.json();
      setTipo("success");
      setMensaje(data.mensaje);
    } catch (err) {
      console.error("Error al cargar el archivo:", err);
      setTipo("danger");
      setMensaje("Error de conexión con el servidor");
    } finally {
      setCargando(false);
    }
  };

  return (
    <div className="p-4 border rounded bg-light">
      <h4>Subir Preguntas vía Excel</h4>
      <input
        type="file"
        className="form-control mb-3"
        onChange={(e) => setArchivo(e.target.files[0])}
      />
      <button
        className="btn btn-success"
        onClick={subir}
        disabled={cargando}
      >
        {cargando ? "Cargando..." : "Cargar a Base de Datos"}
      </button>
      {mensaje && (
        <div className={`alert alert-${tipo} mt-3 mb-0`} role="alert">
          {mensaje}
        </div>
      )}
    </div>
  );
}