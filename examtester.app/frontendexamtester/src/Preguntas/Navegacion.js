import React from 'react'

export default function Navegacion() {
  return (
    <div>
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary ">
            <div className="container-fluid">
                <a className="navbar-brand" href="#">Ir a Test</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                    <li className="nav-item">
                    <a className="nav-link active" aria-current="page" href="#">Agregar Pregunta</a>
                    </li>

                    <li className="nav-item dropdown">
                    <a className="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Agregar Contenedor Maestro
                    </a>
                    <ul className="dropdown-menu">
                        <li><a className="dropdown-item" href="#">Agregar Tema</a></li>
                        <li><a className="dropdown-item" href="#">Agregar Subtema</a></li>
                        <li><a className="dropdown-item" href="#">Agregar Topico</a></li>
                    </ul>
                    </li>

                </ul>
                <form className="d-flex" role="search">
                    <input className="form-control me-2" type="search" placeholder="Buscar Pregunta" aria-label="Search"/>
                    <button className="btn btn-outline-success" type="submit">Buscar</button>
                </form>
                </div>
            </div>
        </nav>        
    </div>
  )
}
