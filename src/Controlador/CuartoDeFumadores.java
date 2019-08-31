package Controlador;

import Modelo.*;
import Vista.VentanaPrincipal;
import javax.swing.JTextArea;

public class CuartoDeFumadores
{
    private final VentanaPrincipal app;
    private final JTextArea consola;
    private final Mesa mesa;
    private final Tabaquero tabaquero;
    private final Fumador fumadores[];
    
    public CuartoDeFumadores(VentanaPrincipal app)
    {
        this.app = app;
        consola = app.getConsola();
        mesa = new Mesa(this);
        tabaquero = new Tabaquero("Tabaquero", mesa, this);
        fumadores = new Fumador[3];
        iniciar();
    }
    
    private void iniciar()
    {
        try {
            Thread.sleep(5000);
        }
        catch(InterruptedException ex) {
            System.err.println("Error en CuartoDeFumadores:\n"+ex.getMessage());
        }
        
        int i;
        
        // Creamos y lanzamos Fumadores
        for (i = 0; i < 3; i++)
        	fumadores[i] = new Fumador(mesa, i, "Fumador "+(i+1), tabaquero, this);
        
        consola.setText("");
        tabaquero.start();
        
        for(i = 0; i < 3; i++)
        	fumadores[i].start();
    }
    
    public void escribe(String line) { consola.append(line); }
    
    public void cambiaEstadoComponente(String componente, int status)
    {
        app.cambiarImagenComponente(componente, status);
    }
}