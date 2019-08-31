package Modelo;

import Controlador.CuartoDeFumadores;

public class Tabaquero extends Thread
{
    private final CuartoDeFumadores cuarto;
    private final Mesa mesa;
    
    public Tabaquero(String name, Mesa mesa, CuartoDeFumadores cuarto)
    {
        super(name);
        this.mesa = mesa;
        this.cuarto = cuarto;
    }

    @Override
    public void run()
    {
        while(true)
        {
            try {
                sleep(1000);
            }
            catch (InterruptedException e) {}
            
            mesa.ponerIngredientes();   // Pone los ingredientes en la mesa
            cuarto.escribe("\nSoy el Tabaquero y tengo"+mesa.getIngredientesEnMesa()+" en la mesa.\n");
            dormir();
        }
    }

    public synchronized void despertar()
    {
        try{
            notify();
        }
        catch(Exception e){}
    }

    public synchronized void dormir()
    {
        try{
            this.wait();
        }
        catch (Exception e) {}
    }
}