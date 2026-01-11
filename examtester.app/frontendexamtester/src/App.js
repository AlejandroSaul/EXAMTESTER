import { BrowserRouter, Routes, Route } from "react-router-dom";

import InsertarPregunta from "./Preguntas/InsertarPregunta";
import Navegacion from "./Preguntas/Navegacion";
import Test from "./Preguntas/Test";

function App() {
  return (
    <BrowserRouter>
      <div className="container text-center">
        <h3>Exam Tester</h3>
        <Navegacion />
        <Routes>
          <Route path="/agregar-pregunta"  element={<InsertarPregunta />} />
          <Route path="/ir-a-test"  element={<Test />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}
export default App;
