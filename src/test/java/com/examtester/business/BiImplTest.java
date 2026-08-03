package com.examtester.business;

import org.apache.poi.ss.usermodel.Row;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BiImplTest {


    @Test
    public void testProcesamientoExcel_FilaVacia() {
        // 1. Preparar una fila de Excel simulada (Mock)
        Row mockRow = mock(Row.class);
        
        // 2. Simular el rango de celdas para que el bucle interno se ejecute
        when(mockRow.getFirstCellNum()).thenReturn((short) 0);
        when(mockRow.getLastCellNum()).thenReturn((short) 7);
        
        // 3. Comportamiento simulado: Todas las celdas del rango (0-6) son nulas
        for (int c = 0; c < 7; c++) {
            when(mockRow.getCell(c)).thenReturn(null);
        }


        // 4. Ejecutar la validación estática del helper
        boolean esVacia = BiProcesamientoExcel.isRowEmpty(mockRow);


        // 5. Verificar el resultado (Assert)
        assertTrue(esVacia, "La fila debería ser detectada como vacía para no insertarse en MySQL");
    }
}
