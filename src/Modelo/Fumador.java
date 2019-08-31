package Modelo;

import Controlador.CuartoDeFumadores;
import java.util.Random;

public class Fumador extends Thread
{
    private final CuartoDeFumadores cuarto;
    private final Mesa mesa;
    private String ingrediente;
    private int numIngrediente;
    private final Tabaquero tabaquero;
    private Random r;
    
    public Fumador(Mesa mesa, int x, String nombre, Tabaquero tabaquero, CuartoDeFumadores cuarto)
    {
        super(nombre);
        this.mesa = mesa;
        this.tabaquero = tabaquero;
        this.cuarto = cuarto;
        numIngrediente = x;
        r = new Random();
    }

    @Override
    public void run()
    {
        while(true)
        {
            ingrediente = mesa.getIngrediente(numIngrediente);  // Obtiene su ingrediente unico de la mesa
            
            // Si encuentra los ingredientes que le faltan y la mesa no esta vacia
            if (!mesa.existeIngrediente(ingrediente) && !mesa.estaVacia())
            {
                // Escribe en "consola" el ingrediente que tiene
                cuarto.escribe("\n"+getName()+" tiene " + ingrediente + "\n");
                try
                {
                    fumar();
                    tabaquero.despertar();
                }
                catch (InterruptedException e) {}
            }
        }
    }
    
    public void fumar() throws InterruptedException
    {
        cuarto.escribe(getName()+" fumando...\n");
        cuarto.cambiaEstadoComponente(getName(), 1);
        sleep((1000+r.nextInt(2000))); // Duerme de 1000 a 3000 milisegundos
        cuarto.cambiaEstadoComponente(getName(), 0);
    }
}