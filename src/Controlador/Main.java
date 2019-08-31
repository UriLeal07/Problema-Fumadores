package Controlador;

import javax.swing.JOptionPane;
import Vista.VentanaPrincipal;

class Main
{
    public static void main(String args[])
    {
        try
        {
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels = javax.swing.UIManager.getInstalledLookAndFeels();
            int idx;
            
            for(idx = 0; idx < installedLookAndFeels.length; idx++)
                if("Nimbus".equals(installedLookAndFeels[idx].getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;
                }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar Look and Feel", "Error de Look and Feel", JOptionPane.ERROR_MESSAGE);
        }
        
        VentanaPrincipal app = new VentanaPrincipal();
        app.setVisible(true);
        app.setDefaultCloseOperation(VentanaPrincipal.EXIT_ON_CLOSE);
        new CuartoDeFumadores(app);
    }
}