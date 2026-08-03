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

describe('Integración UI con API REST - Componente Test.js', () => {

  // CRA activa resetMocks:true, que borra la implementación de los mocks antes de cada
  // test. Por eso el mock de fetch se (re)define en beforeEach, no a nivel de módulo.
  beforeEach(() => {
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
      return Promise.resolve({ ok: true, json: () => Promise.resolve(respuestaPregunta) });
    });
  });

  it('debe procesar la respuesta asíncrona y renderizar las opciones dinámicamente', async () => {
    render(
      <BrowserRouter>
        <TestComponent />
      </BrowserRouter>
    );

    // El componente NO llama a la API de pregunta al montar: primero carga /temas
    // y solo tras elegir Tema -> Subtema -> Tópico solicita la pregunta.
    // El <select> de tema se renderiza siempre; hay que esperar a que llegue
    // la opción "Java" desde /temas antes de seleccionarla.
    await waitFor(() => expect(screen.getByRole('option', { name: 'Java' })).toBeInTheDocument());

    // Seleccionar el tema (dispara fetch de subtemas)
    await userEvent.selectOptions(screen.getAllByRole('combobox')[0], '1');

    // Esperar la opción de subtema que llega desde /subtemas/1
    await waitFor(() => expect(screen.getByRole('option', { name: 'Fundamentos' })).toBeInTheDocument());
    await userEvent.selectOptions(screen.getAllByRole('combobox')[1], '1');

    // Esperar la opción de tópico que llega desde /topico/1
    await waitFor(() => expect(screen.getByRole('option', { name: 'Variables' })).toBeInTheDocument());
    await userEvent.selectOptions(screen.getAllByRole('combobox')[2], '1');

    // Esperar a que el DOM virtual se actualice con la respuesta de la API simulada.
    // La pregunta se renderiza en un <input readOnly> (se valida con getByDisplayValue)
    // y las opciones de respuesta como <label> (getByText).
    await waitFor(() => {
      expect(screen.getByDisplayValue('¿Prueba de integración UI exitosa?')).toBeInTheDocument();
      expect(screen.getByText(/X.- Sí/i)).toBeInTheDocument();
      expect(screen.getByText(/X.- No/i)).toBeInTheDocument();
    });

    // Validar que se intentó comunicar con el backend
    expect(fetch).toHaveBeenCalled();
  });
});
