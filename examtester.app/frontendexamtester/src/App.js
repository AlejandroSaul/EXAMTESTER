import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import AgregarTema from "./Preguntas/AgregarTema";
import InsercionMasiva from "./Preguntas/InsercionMasiva";
import Login from "./Login";
import Navegacion from "./Preguntas/Navegacion";
import ProtectedRoute from "./ProtectedRoute";
import Register from "./Register";
import Test from "./Preguntas/Test";

function App() {
  return (
    <BrowserRouter>
      <div className="container text-center">
        <h3>Exam Tester</h3>
        <Navegacion />
        <Routes>
          <Route path="/" element={<Navigate to="/ir-a-test" replace />} />
          <Route path="/login" element={<Login />} />
          <Route path="/registro" element={<Register />} />
          <Route
            path="/agregar-tema"
            element={
              <ProtectedRoute>
                <AgregarTema />
              </ProtectedRoute>
            }
          />
          <Route
            path="/insercion-masiva"
            element={
              <ProtectedRoute>
                <InsercionMasiva />
              </ProtectedRoute>
            }
          />
          <Route
            path="/ir-a-test"
            element={
              <ProtectedRoute>
                <Test />
              </ProtectedRoute>
            }
          />
        </Routes>
      </div>
    </BrowserRouter>
  );
}
export default App;
