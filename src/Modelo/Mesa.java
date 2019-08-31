package Modelo;

import Controlador.CuartoDeFumadores;
import java.util.ArrayList;
import java.util.Random;

public class Mesa
{
    private final ArrayList<String> ingredientesTotales;
    private ArrayList<String> ingredientesEnMesa;
    private final CuartoDeFumadores cuarto;

    public Mesa(CuartoDeFumadores cuarto)
    {
        ingredientesTotales  = new ArrayList<> ();
        ingredientesEnMesa = new ArrayList<> ();
        ingredientesTotales.add("Tabaco");
        ingredientesTotales.add("Papel Arroz");
        ingredientesTotales.add("Cerillos");
        this.cuarto = cuarto;
    }
    
    public void ponerIngredientes()
    {
        Random r = new Random();
        int ingrediente;
        ingredientesEnMesa.clear();
        ArrayList<String> ingredientesDisponibles = (ArrayList<String>) ingredientesTotales.clone();
        
        for(int i = 0; i < 2; i++)
        {
            ingrediente = r.nextInt(ingredientesDisponibles.size());
            cuarto.cambiaEstadoComponente("Tabaquero", ingrediente);
            ingredientesEnMesa.add((String)ingredientesDisponibles.get(ingrediente));
            cuarto.escribe("++Ingrediente en mesa colocado: "+ingredientesDisponibles.get(ingrediente));
            ingredientesDisponibles.remove(ingrediente);
        }
    }
    
    public boolean estaVacia() { return (ingredientesEnMesa.isEmpty()); }
    
    
    public synchronized String getIngredientesEnMesa()
    {
        notifyAll();
        return ingredientesEnMesa.toString();
    }
    
    public synchronized String getIngrediente(int x)
    {
        return (String) ingredientesTotales.get(x);
    }
    
    public boolean existeIngrediente(String ingrediente) { return(ingredientesEnMesa.contains(ingrediente)); }
}