import React, { useEffect, useState } from "react"

export default function Pregunta() {

  const [pregunta, setPregunta] = useState(null)
  const [seleccionadas, setSeleccionadas] = useState([])
  const [resultado, setResultado] = useState(null)

  const [temas, setTemas] = useState([])
  const [temaSeleccionado, setTemaSeleccionado] = useState("")

  const [subtemas, setSubtemas] = useState([])
  const [subtemaSeleccionado, setSubtemaSeleccionado] = useState("")

  const [topicos, setTopicos] = useState([])
  const [topicoSeleccionado, setTopicoSeleccionado] = useState("")

  // =========================
  // Cargar Temas
  // =========================
  useEffect(() => {
    fetch("http://localhost:8081/api/examen/temas")
      .then(res => res.json())
      .then(data => {
        const temasArray = Object.entries(data).map(([id, nombre]) => ({
          idTema: Number(id),
          nombre
        }))
        setTemas(temasArray)
      })
      .catch(err => console.error("Error al cargar temas:", err))
  }, [])

  // =========================
  // Cargar Subtemas
  // =========================
  useEffect(() => {
    if (!temaSeleccionado) {
      setSubtemas([])
      setSubtemaSeleccionado("")
      return
    }

    fetch(`http://localhost:8081/api/examen/subtemas/${temaSeleccionado}`)
      .then(res => res.json())
      .then(data => {
        const subtemasArray = Array.isArray(data)
          ? data
          : Object.entries(data).map(([id, nombre]) => ({
              idSubtema: Number(id),
              nombre
            }))

        setSubtemas(subtemasArray)
        setSubtemaSeleccionado("")
      })
      .catch(err => {
        console.error("Error al cargar subtemas:", err)
        setSubtemas([])
      })
  }, [temaSeleccionado])

  // =========================
  // Cargar Tópicos
  // =========================
  useEffect(() => {
    if (!subtemaSeleccionado) {
      setTopicos([])
      setTopicoSeleccionado("")
      return
    }

    fetch(`http://localhost:8081/api/examen/topico/${subtemaSeleccionado}`)
      .then(res => res.json())
      .then(data => {
        const topicosArray = Array.isArray(data)
          ? data
          : Object.entries(data).map(([id, nombre]) => ({
              idTopico: Number(id),
              nombre
            }))

        setTopicos(topicosArray)
        setTopicoSeleccionado("")
      })
      .catch(err => {
        console.error("Error al cargar topicos:", err)
        setTopicos([])
      })
  }, [subtemaSeleccionado])

  // =========================
  // Cargar Pregunta
  // =========================
  useEffect(() => {
    if (!topicoSeleccionado) {
      setPregunta(null)
      return
    }

    fetch(`http://localhost:8081/api/examen/pregunta/subtemaTopico/${topicoSeleccionado}`)
      .then(res => {
        if (!res.ok) throw new Error("Error HTTP")
        return res.json()
      })
      .then(data => {
        setPregunta(data)
        setSeleccionadas([])
        setResultado(null)
      })
      .catch(err => {
        console.error("Error al cargar pregunta:", err)
        setPregunta(null)
      })
  }, [topicoSeleccionado])

  // =========================
  // Nueva pregunta
  // =========================
  const siguientePregunta = () => {
    if (!topicoSeleccionado) return

    fetch(`http://localhost:8081/api/examen/pregunta/subtemaTopico/${topicoSeleccionado}`)
      .then(res => res.json())
      .then(data => {
        setPregunta(data)
        setSeleccionadas([])
        setResultado(null)
      })
  }

  // =========================
  // Toggle respuesta
  // =========================
  const toggleRespuesta = (letra) => {
    setSeleccionadas(prev =>
      prev.includes(letra)
        ? prev.filter(r => r !== letra)
        : [...prev, letra]
    )
  }

  // =========================
  // Calificar
  // =========================
  const calificar = () => {

    if (seleccionadas.length === 0) {
      alert("Selecciona al menos una respuesta")
      return
    }

    const correctas = pregunta.respuestaCorrecta
      .split(",")
      .map(r => r.trim())

    const seleccionadasOrdenadas = [...seleccionadas].sort()
    const correctasOrdenadas = [...correctas].sort()

    const esCorrecto =
      JSON.stringify(seleccionadasOrdenadas) ===
      JSON.stringify(correctasOrdenadas)

    setResultado(esCorrecto ? "correcto" : "incorrecto")
  }

  // =========================
  // Respuestas
  // =========================
  const respuestas = pregunta
    ? [
        { key: "A", value: pregunta.respuestaA },
        { key: "B", value: pregunta.respuestaB },
        { key: "C", value: pregunta.respuestaC },
        { key: "D", value: pregunta.respuestaD },
        { key: "E", value: pregunta.respuestaE },
        { key: "F", value: pregunta.respuestaF },
        { key: "G", value: pregunta.respuestaG },
        { key: "H", value: pregunta.respuestaH },
        { key: "I", value: pregunta.respuestaI }
      ].filter(r => r.value && r.value !== "-")
    : []

  // =========================
  // RENDER
  // =========================
  return (
    <div>

      {/* TEMA */}
      <div className="mb-4">
        <label className="form-label fw-bold">Selecciona un tema</label>
        <select
          className="form-select"
          value={temaSeleccionado}
          onChange={(e) => {
            setTemaSeleccionado(e.target.value)
            setSubtemas([])
            setSubtemaSeleccionado("")
            setTopicos([])
            setTopicoSeleccionado("")
            setPregunta(null)
          }}
        >
          <option value="">-- Selecciona un tema --</option>
          {temas.map(t => (
            <option key={t.idTema} value={t.idTema}>
              {t.nombre}
            </option>
          ))}
        </select>
      </div>

      {/* SUBTEMA */}
      {subtemas.length > 0 && (
        <div className="mb-4">
          <label className="form-label fw-bold">Selecciona un subtema</label>
          <select
            className="form-select"
            value={subtemaSeleccionado}
            onChange={(e) => {
              setSubtemaSeleccionado(e.target.value)
              setTopicos([])
              setTopicoSeleccionado("")
              setPregunta(null)
            }}
          >
            <option value="">-- Selecciona un subtema --</option>
            {subtemas.map(s => (
              <option key={s.idSubtema} value={s.idSubtema}>
                {s.nombre}
              </option>
            ))}
          </select>
        </div>
      )}

      {/* TOPICO */}
      {topicos.length > 0 && (
        <div className="mb-4">
          <label className="form-label fw-bold">Selecciona un tópico</label>
          <select
            className="form-select"
            value={topicoSeleccionado}
            onChange={(e) => setTopicoSeleccionado(e.target.value)}
          >
            <option value="">-- Selecciona un tópico --</option>
            {topicos.map(t => (
              <option key={t.idTopico} value={t.idTopico}>
                {t.nombre}
              </option>
            ))}
          </select>
        </div>
      )}

      {/* Cargando */}
      {!pregunta && topicoSeleccionado && (
        <p>Cargando pregunta...</p>
      )}

      {/* PREGUNTA */}
      {pregunta && (
        <>
          <div className="mb-3">
            <label className="form-label fw-bold">Pregunta</label>
            <input
              type="text"
              className="form-control mb-3"
              value={pregunta.pregunta}
              readOnly
            />

            <label className="form-label fw-bold">Respuestas</label>
            {respuestas.map((r, index) => (
              <div
                key={r.key}
                className={`form-check d-flex align-items-center p-2 mb-1 rounded ${
                  index % 2 === 0 ? "bg-white" : "bg-light"
                }`}
              >
                <input
                  className="form-check-input me-2"
                  type="checkbox"
                  checked={seleccionadas.includes(r.key)}
                  onChange={() => toggleRespuesta(r.key)}
                />
                <label className="form-check-label">
                  {r.value}
                </label>
              </div>
            ))}
          </div>

          {/* Resultado */}
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

          <button
            type="button"
            className="btn btn-primary me-2"
            onClick={siguientePregunta}
          >
            Nueva pregunta
          </button>

          <button
            type="button"
            className="btn btn-success"
            onClick={calificar}
          >
            Calificar
          </button>
        </>
      )}

    </div>
  )
}
