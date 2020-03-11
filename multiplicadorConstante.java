import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class multiplicadorConstante
{
    private static InputStreamReader isr = new InputStreamReader(System.in);
    private static BufferedReader br = new BufferedReader(isr);
    private static List<Float> almacen_r = new ArrayList<Float>();
    private static List<Integer> almacen_y_1 = new ArrayList<Integer>();
    private static List<Integer> almacen_semillas = new ArrayList<Integer>();
    private static List<Integer> almacen_producto = new ArrayList<Integer>();
    public static void main(String args[]) throws IOException
    {
        boolean switch_menu = true;

        while (switch_menu)
        {
            try
            {
                int semilla = 0;
                int constante = 0;
                int cantidad_r = 0;

                do
                {
                    System.out.println("Ingresa el numero de 4 dijitos para la semilla x0:");
                    semilla = Integer.parseInt(br.readLine());
                    System.out.println("Ingresa el numero de 4 dijitos para la constante:");
                    constante = Integer.parseInt(br.readLine());
                    System.out.println("Ingresa la cantidad de ri:");
                    cantidad_r = Integer.parseInt(br.readLine());

                    if (!is_greater_to_three(semilla) || !is_greater_to_three(constante)) 
                    {
                        JOptionPane.showMessageDialog(null,"error de cantidad");
                    }
                }while(!is_greater_to_three(semilla) || !is_greater_to_three(constante));

                obtener_r(semilla,constante,cantidad_r);
                obtener_resultados(cantidad_r, constante);
                switch_menu = false;
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"error de tipo");
            }
        }
    }

    private static boolean is_greater_to_three(int semilla)
    {
        String semilla_text = Integer.toString(semilla);
        return (semilla_text.length() > 3)? true : false;
    }

    private static void obtener_resultados(int cantidad_r,int constante)
    {
        for (int x = 0; x < cantidad_r; x++) 
        {
            System.out.println
            (
                "y"+x+" = "+
                almacen_y_1.get(x)+" a = "+
                constante+" = "+
                almacen_producto.get(x)+" x"+(x+1)+" = "+
                almacen_semillas.get(x)+" r"+(x+1)+" = "+
                almacen_r.get(x)
            );
        }
    }

    private static int obtener_r(int semilla, int constante, int cantidad_r)
    {
        if (cantidad_r == 0)
        {
            return 0;
        }
        else
        {
            almacen_y_1.add(semilla);
            almacen_producto.add(producto(semilla,constante));
            almacen_semillas.add(new_zen(producto(semilla,constante)));
            almacen_r.add((float)(new_zen(producto(semilla,constante)))/10000);
            return obtener_r(new_zen(producto(semilla,constante)),constante,cantidad_r-1);
        }
    }

    private static int producto(int semilla, int constante)
    {
        return semilla*constante;
    }

    private static int new_zen(int producto)
    {
        String producto_text_value = Integer.toString(producto);
        String text_new_zen;
        if (producto_text_value.length()%2 != 0)
        {
            producto_text_value = "0"+producto_text_value;
            int valor_limite_inicial_final = (producto_text_value.length()-4) / 2;
            text_new_zen = producto_text_value.substring(valor_limite_inicial_final, valor_limite_inicial_final+4);
        }
        else
        {
            int valor_limite_inicial_final = (producto_text_value.length()-4) / 2;
            text_new_zen = producto_text_value.substring(valor_limite_inicial_final, valor_limite_inicial_final+4);
        }
        int val_new_zen = Integer.parseInt(text_new_zen);
        return val_new_zen;
    }
}