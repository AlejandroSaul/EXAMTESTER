import React from 'react'
import { useEffect, useState } from "react"

export default function Pregunta() {
  const ids = [137,1]
  const [id, setId] = useState(ids[0])
  const [pregunta, setPregunta] = useState(null)
  const [seleccionadas, setSeleccionadas] = useState([])
  const [resultado, setResultado] = useState(null) // "correcto" | "incorrecto"

useEffect(() => {
  fetch(`http://localhost:8081/api/examen/pregunta/${id}`)
    .then(res => {
      if (!res.ok) {
        throw new Error("Error HTTP")
      }
      return res.json()
    })
    .then(data => {
      setPregunta(data)
      setSeleccionadas([]) // reset
      setResultado(null)
    })
    .catch(err => {
      console.error("Error al cargar pregunta:", err)
      setPregunta(null)
    })
}, [id])

if (!pregunta) {
  return <p>Cargando pregunta...</p>
}

  const respuestas = [
    { key: "A", value: pregunta.respuestaA },
    { key: "B", value: pregunta.respuestaB },
    { key: "C", value: pregunta.respuestaC },
    { key: "D", value: pregunta.respuestaD },
    { key: "E", value: pregunta.respuestaE },
    { key: "F", value: pregunta.respuestaF },
    { key: "G", value: pregunta.respuestaG },
    { key: "H", value: pregunta.respuestaH },
    { key: "I", value: pregunta.respuestaI }
  ];

  const calificar = () => {

    if (seleccionadas.length === 0) {
      alert("Selecciona al menos una respuesta")
      return
    }
  // Convierte "A,C" → ["A","C"]
  const correctas = pregunta.respuestaCorrecta
    .split(",")
    .map(r => r.trim())

  // Ordenamos para comparar sin importar orden
  const seleccionadasOrdenadas = [...seleccionadas].sort()
  const correctasOrdenadas = [...correctas].sort()

  const esCorrecto =
    JSON.stringify(seleccionadasOrdenadas) ===
    JSON.stringify(correctasOrdenadas)

  setResultado(esCorrecto ? "correcto" : "incorrecto")
}
  
  const toggleRespuesta = (letra) => {
  setSeleccionadas(prev =>
    prev.includes(letra)
      ? prev.filter(r => r !== letra)
      : [...prev, letra]
  )
}

const siguientePregunta = () => {
  let nuevoId
  do {
    nuevoId = ids[Math.floor(Math.random() * ids.length)]
  } while (nuevoId === id)

  setId(nuevoId)
}

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

        <div className="mb-3">
          <label className="form-label fw-bold">Respuestas</label>
          {respuestas
          .filter(r => r.value && r.value !== "-")
          .map((r, index) => (
            <div
              key={r.key}
              className={`form-check d-flex align-items-center p-2 mb-1 rounded ${
                index % 2 === 0 ? "bg-white" : "bg-light"
              }`}
            >
              <input
                className="form-check-input me-2"
                type="checkbox"
                id={`respuesta-${r.key}`}
                checked={seleccionadas.includes(r.key)}
                onChange={() => toggleRespuesta(r.key)}
              />

              <label
                className="form-check-label mb-0"
                htmlFor={`respuesta-${r.key}`}
              >
                {r.value}
              </label>
            </div>
          ))}
        </div>

        {/* Calificar */}
      <label className="form-label fw-bold">La respuesta es...</label>
      <input
        type="text"
        className={`form-control mb-3 ${
          resultado === "correcto"
            ? "is-valid"
            : resultado === "incorrecto"
            ? "is-invalid"
            : ""
        }`}
        value={
          resultado === "correcto"
            ? "Correcto ✅"
            : resultado === "incorrecto"
            ? "Incorrecto ❌"
            : ""
        }
        readOnly
      />
      </div>
      <div>
        <button type="button" className="btn btn-primary me-2" onClick={siguientePregunta}>Nueva pregunta</button>
        
        <button
          type="button"
          className="btn btn-success"
          onClick={calificar}>
          Calificar
        </button>

      </div>
    </div>

  )
}
