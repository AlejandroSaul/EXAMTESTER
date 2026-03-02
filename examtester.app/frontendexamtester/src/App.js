import { BrowserRouter, Routes, Route } from "react-router-dom";

import InsercionMasiva from "./Preguntas/InsercionMasiva";
import Navegacion from "./Preguntas/Navegacion";
import Test from "./Preguntas/Test";

function App() {
  return (
    <BrowserRouter>
      <div className="container text-center">
        <h3>Exam Tester</h3>
        <Navegacion />
        <Routes>
           <Route path="/insercion-masiva"  element={<InsercionMasiva />} />
          <Route path="/ir-a-test"  element={<Test />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}
export default App;
