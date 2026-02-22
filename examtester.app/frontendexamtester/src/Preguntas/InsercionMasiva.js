import React, { useState } from "react";

export default function ImportarMasivo() {
  const [archivo, setArchivo] = useState(null);

  const subir = () => {
    const formData = new FormData();
    formData.append("file", archivo);

    fetch("http://192.168.0.71:8081/api/examen/importar-excel", {
      method: "POST",
      body: formData,
    })
      .then(res => res.json())
      .then(data => alert("Resultado: " + data.mensaje)) // Asumiendo que GenericResponse tiene 'mensaje'
      .catch(err => console.error(err));
  };

  return (
    <div className="p-4 border rounded bg-light">
      <h4>Subir Preguntas vía Excel</h4>
      <input type="file" className="form-control mb-3" onChange={(e) => setArchivo(e.target.files[0])} />
      <button className="btn btn-success" onClick={subir}>Cargar a Base de Datos</button>
    </div>
  );
}