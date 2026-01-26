import React from 'react'
import { useEffect, useState } from "react"

export default function Pregunta() {
  const [id, setId] = useState(1)
  const [pregunta, setPregunta] = useState(null)

  useEffect(() => {
    fetch(`http://localhost:8081/api/examen/pregunta/${id}`)
      .then(res => res.json())
      .then(data => setPregunta(data))
      .catch(err => console.error(err))
  }, [id])

  if (!pregunta) {
    return <p>Cargando pregunta...</p>
  }

  return (
    <div>
      <div className="mb-3">
        {/* Pregunta */}
        <label className="form-label fw-bold">Pregunta</label>
        <input
          type="text"
          className="form-control mb-3"
          value={pregunta.pregunta}
          readOnly
        />

        {/* Respuesta A */}
        <label className="form-label">Respuesta A</label>
        <input
          type="text"
          className="form-control mb-2"
          value={pregunta.respuestaA}
          readOnly
        />

        {/* Respuesta B */}
        <label className="form-label">Respuesta B</label>
        <input
          type="text"
          className="form-control mb-2"
          value={pregunta.respuestaB}
          readOnly
        />

        {/* Respuesta C */}
        <label className="form-label">Respuesta C</label>
        <input
          type="text"
          className="form-control mb-2"
          value={pregunta.respuestaC}
          readOnly
        />

        {/* Respuesta D */}
        <label className="form-label">Respuesta D</label>
        <input
          type="text"
          className="form-control mb-2"
          value={pregunta.respuestaD}
          readOnly
        />

        {/* Respuesta E */}
        <label className="form-label">Respuesta E</label>
        <input
          type="text"
          className="form-control mb-2"
          value={pregunta.respuestaE}
          readOnly
        />

        {/* Respuesta F */}
        <label className="form-label">Respuesta F</label>
        <input
          type="text"
          className="form-control mb-2"
          value={pregunta.respuestaF}
          readOnly
        />

        {/* Respuesta G */}
        <label className="form-label">Respuesta G</label>
        <input
          type="text"
          className="form-control mb-2"
          value={pregunta.respuestaG}
          readOnly
        />

        {/* Respuesta H */}
        <label className="form-label">Respuesta H</label>
        <input
          type="text"
          className="form-control mb-2"
          value={pregunta.respuestaH}
          readOnly
        />

        {/* Respuesta I */}
        <label className="form-label">Respuesta I</label>
        <input
          type="text"
          className="form-control mb-2"
          value={pregunta.respuestaI}
          readOnly
        />

        {/* Respuesta Elegida */}
        <label className="form-label">Respuesta Elegida</label>
        <input
          type="text"
          className="form-control mb-2"
          value={pregunta.respuestaElegida}
        />

      </div>
      <div>
        <button type="button" class="btn btn-primary me-2" onClick={() => setId(prev => prev + 1)}>Nueva pregunta</button>
        <button type="button" class="btn btn-success">Calificar</button>
      </div>
    </div>

  )
}
