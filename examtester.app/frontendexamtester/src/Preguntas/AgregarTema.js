import React, { useState } from 'react'

export default function AgregarTema() {
  const [nombreTema, setNombreTema] = useState('')
  const [mensaje, setMensaje] = useState(null)
  const [tipo, setTipo] = useState('')

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (!nombreTema.trim()) {
      setTipo('danger')
      setMensaje('El nombre del tema no puede estar vacío')
      return
    }
    try {
      const res = await fetch('http://192.168.0.13:8081/api/examen/insertartema', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nombreTema: nombreTema.trim() })
      })
      const data = await res.json()
      setTipo(data.codigo === 0 ? 'success' : 'danger')
      setMensaje(data.mensaje)
      if (data.codigo === 0) setNombreTema('')
    } catch (error) {
      setTipo('danger')
      setMensaje('Error de conexión con el servidor')
    }
  }

  return (
    <div className="container mt-4">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-header">
              <h4>Agregar Tema</h4>
            </div>
            <div className="card-body">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label htmlFor="nombreTema" className="form-label">Nombre del Tema</label>
                  <input
                    type="text"
                    className="form-control"
                    id="nombreTema"
                    placeholder="Ej: INFORMATICA"
                    value={nombreTema}
                    onChange={(e) => setNombreTema(e.target.value)}
                    autoFocus
                  />
                </div>
                <button type="submit" className="btn btn-primary">Guardar</button>
              </form>
              {mensaje && (
                <div className={`alert alert-${tipo} mt-3 mb-0`} role="alert">
                  {mensaje}
                </div>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
