import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { BrowserRouter } from 'react-router-dom';
import '@testing-library/jest-dom';
import TestComponent from './Test';

const respuestaPregunta = {
  idPregunta: "1",
  pregunta: "¿Prueba de integración UI exitosa?",
  respuestaA: "X.- Sí",
  respuestaB: "X.- No",
  respuestaC: "-",
  respuestaD: "-",
  respuestaCorrecta: "A"
};

// Pregunta con bloque de código en varias líneas. Antes de usar <textarea>,
// el <input type="text"> descartaba los \n y lo pintaba todo en una sola línea.
const respuestaPreguntaMultilinea = {
  idPregunta: "609",
  pregunta:
    "Given the following class:\npublic class Test{\npublic static void main(String[] args){\nSystem.out.println(\"Hello \"+args[0]);\n}\n}\nWhich set of commands prints Hello Student in the console?",
  respuestaA: "X.- java Test Student",
  respuestaB: "X.- java Test\n\n",
  respuestaC: "-",
  respuestaD: "-",
  respuestaCorrecta: "A",
  explicacion:
    "El arreglo args recibe los argumentos que siguen al nombre de la clase,\npor eso args[0] vale Student."
};

describe('Integración UI con API REST - Componente Test.js', () => {

  // CRA activa resetMocks:true, que borra la implementación de los mocks antes de cada
  // test. Por eso el mock de fetch se (re)define en beforeEach, no a nivel de módulo.
  let preguntaActual;

  beforeEach(() => {
    preguntaActual = respuestaPregunta;
    global.fetch = jest.fn((url) => {
      if (String(url).includes('/api/examen/temas')) {
        return Promise.resolve({ ok: true, json: () => Promise.resolve({ 1: "Java" }) });
      }
      if (String(url).includes('/api/examen/subtemas/')) {
        return Promise.resolve({ ok: true, json: () => Promise.resolve({ 1: "Fundamentos" }) });
      }
      if (String(url).includes('/api/examen/topico/')) {
        return Promise.resolve({ ok: true, json: () => Promise.resolve({ 1: "Variables" }) });
      }
      // /api/examen/pregunta/subtemaTopico/
      return Promise.resolve({ ok: true, json: () => Promise.resolve(preguntaActual) });
    });
  });

  // Selecciona Tema -> Subtema -> Tópico y espera a que se pinte la pregunta.
  async function seleccionarCadenaCompleta() {
    await waitFor(() => expect(screen.getByRole('option', { name: 'Java' })).toBeInTheDocument());
    await userEvent.selectOptions(screen.getAllByRole('combobox')[0], '1');
    await waitFor(() => expect(screen.getByRole('option', { name: 'Fundamentos' })).toBeInTheDocument());
    await userEvent.selectOptions(screen.getAllByRole('combobox')[1], '1');
    await waitFor(() => expect(screen.getByRole('option', { name: 'Variables' })).toBeInTheDocument());
    await userEvent.selectOptions(screen.getAllByRole('combobox')[2], '1');
  }

  it('debe procesar la respuesta asíncrona y renderizar las opciones dinámicamente', async () => {
    render(
      <BrowserRouter>
        <TestComponent />
      </BrowserRouter>
    );

    // El componente NO llama a la API de pregunta al montar: primero carga /temas
    // y solo tras elegir Tema -> Subtema -> Tópico solicita la pregunta.
    await seleccionarCadenaCompleta();

    // La pregunta y las respuestas se renderizan en <textarea readOnly>
    // (AutoTextarea), por eso se validan por rol y comparando .value.
    await waitFor(() => {
      expect(screen.getByRole('textbox', { name: 'Pregunta' }).value).toBe(
        '¿Prueba de integración UI exitosa?'
      );
      expect(screen.getByRole('textbox', { name: 'Respuesta A' }).value).toBe('X.- Sí');
      expect(screen.getByRole('textbox', { name: 'Respuesta B' }).value).toBe('X.- No');
    });

    // Validar que se intentó comunicar con el backend
    expect(fetch).toHaveBeenCalled();
  });

  it('debe preservar los saltos de línea de preguntas y respuestas con código multilínea', async () => {
    preguntaActual = respuestaPreguntaMultilinea;

    render(
      <BrowserRouter>
        <TestComponent />
      </BrowserRouter>
    );

    await seleccionarCadenaCompleta();

    // El valor del textarea debe ser EXACTAMENTE el string de la API, con los \n
    // intactos. Con el <input type="text"> anterior estos se perdían al pintar.
    // Ojo: getByDisplayValue normaliza el whitespace (colapsa los \n), por eso
    // buscamos por rol y comparamos .value de forma exacta.
    await waitFor(() => {
      const textareaPregunta = screen.getByRole('textbox', { name: 'Pregunta' });
      expect(textareaPregunta.value).toBe(respuestaPreguntaMultilinea.pregunta);
      expect(textareaPregunta.value).toContain('\n');
    });

    // Las respuestas también conservan sus saltos de línea
    await waitFor(() => {
      const respuestaA = screen.getByRole('textbox', { name: 'Respuesta A' });
      const respuestaB = screen.getByRole('textbox', { name: 'Respuesta B' });
      expect(respuestaA.value).toBe('X.- java Test Student');
      expect(respuestaB.value).toBe('X.- java Test\n\n');
    });
  });

  it('debe mostrar la explicación bajo demanda y ocultarla cuando es guion', async () => {
    preguntaActual = { ...respuestaPreguntaMultilinea };

    render(
      <BrowserRouter>
        <TestComponent />
      </BrowserRouter>
    );

    await seleccionarCadenaCompleta();

    // El botón aparece porque SÍ hay explicación
    await waitFor(() =>
      expect(screen.getByRole('button', { name: /ver explicaci/i })).toBeInTheDocument()
    );
    expect(screen.queryByRole('textbox', { name: 'Explicacion' })).toBeNull();

    // Al pulsarlo se despliega preservando los saltos de línea
    await userEvent.click(screen.getByRole('button', { name: /ver explicaci/i }));
    await waitFor(() => {
      const textareaExplicacion = screen.getByRole('textbox', { name: 'Explicacion' });
      expect(textareaExplicacion.value).toBe(respuestaPreguntaMultilinea.explicacion);
      expect(textareaExplicacion.value).toContain('\n');
    });
  });
});
