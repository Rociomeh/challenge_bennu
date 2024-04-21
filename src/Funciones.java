import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Funciones {
    public static void menu(){
        System.out.println("Opciones");
        System.out.println("---------------------");
        System.out.println("0 - Menu");
        System.out.println("1 - Genera nuevo archivo");
        System.out.println("2 - Lee archivo generado");
        System.out.println("3 - Ordena archivo");
        System.out.println("4 - Lee archivo ordenado");
        System.out.println("5 - Buscar numero en archivo");
        System.out.println("6 - Salir");
        System.out.println("Seleccione una opción :");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        System.out.println("opcion ingresada " +opcion);

        if(opcion == 0){ //funcion que se llama a si misma. Recursividad
            menu();
        } else if (opcion == 1) {
            generarArchivo();
            menu();
        }else if (opcion == 2){
            leerArchivo("archivo.txt");
            menu();
        } else if (opcion == 3) {
            ordenarArchivo();
            menu();
        } else if (opcion == 4) {
            leerArchivo("archivoOrdenado.txt");
            menu();
        } else if (opcion == 5) {
            System.out.println("Ingresa el numero que deseas buscar");
            int num = scanner.nextInt();
            buscar(num);
            menu();
        } else if (opcion == 6) {
            return;
        }
    }
    public static void generarArchivo(){

        Random random = new Random();
        int[] numeros = random.ints(10, 0, 10).toArray();//genera los numeros aleatoriamente y los guarda en una array

        FileWriter archivo; //genero una copia de la clase FileWriter en el archivo para poder ocupar sus metodos (instanciacion)

        try {
            archivo = new FileWriter("archivo.txt");

            for(int num : numeros ){
                archivo.write(num+"\n");
            }

            archivo.close(); //finaliza la ejecucion
            System.out.println("archivo generado con exito");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void leerArchivo(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        Scanner scanner;

        try{
            scanner = new Scanner(archivo); //guarda la ruta del archivo.txt en archivo

            while (scanner.hasNextLine()){
                String linea = scanner.nextLine();
                System.out.println(linea);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void ordenarArchivo(){

        List<Integer> numeros = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader("archivo.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;

            while ((linea = bufferedReader.readLine())!= null){
                numeros.add(Integer.parseInt(linea));
            }

            Collections.sort(numeros);

            FileWriter archivo = new FileWriter("archivoOrdenado.txt");

            for (int num : numeros){
                archivo.write(num + "\n");
            }

            archivo.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void buscar(int numeroBuscado){
        List<Integer> numeros = new ArrayList<>();
        boolean existe = false;

        try {
            FileReader fileReader = new FileReader("archivo.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;

            while ((linea = bufferedReader.readLine())!=null){
                numeros.add(Integer.parseInt(linea));
            }

            for (int num : numeros){
                if (num == numeroBuscado){
                    existe = true;
                    break;
                }
            }
            if (existe){
                System.out.println("numero "+numeroBuscado+" encontrado con exito");

            }else{
                System.out.println("numero "+numeroBuscado+" no encontrado");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
