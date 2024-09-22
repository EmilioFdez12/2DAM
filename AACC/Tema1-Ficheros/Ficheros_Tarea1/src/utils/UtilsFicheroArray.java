package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import models.Empleado;

public class UtilsFicheroArray {
	
	
	
	//Metodos
	
	 public static List<Empleado> empleadosToArray() {
	        List<Empleado> empleados = new ArrayList<>(); 
	        try (BufferedReader lector = new BufferedReader(new FileReader("..\\arch\\empleados.txt"))) {
	            lector.readLine(); 
	            String linea;
	            while ((linea = lector.readLine()) != null) {	       
	                String[] campos = linea.split("\",\"");                
	                String empresa = campos[0].replace("\"", "");
	                int edad = Integer.parseInt(campos[1].replace("\"", ""));
	                int numEmpleados = Integer.parseInt(campos[2].replace("\"", ""));
	                empleados.add(new Empleado(empresa, edad, numEmpleados));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return empleados;
	    }
	
	 public static void toArchivoTexto(List<Empleado> empleados) {
		    try (BufferedWriter escritor = new BufferedWriter(new FileWriter("..\\arch\\empleados.txt"))) {
		        // Escribir cabecera en el archivo
		        escritor.write("EMPRESA,EDAD,NUM_EMPLEADOS");
		        
		        // Escribimos cada empleado en el archivo
		        for (Empleado emp : empleados) {
		            escritor.write("\"" + emp.getEmpresa() + "\",\"" + emp.getEdad() + "\",\"" + emp.getNum_empleados() + "\"");
		            escritor.newLine();
		        }
		       
		       
		    } catch (IOException e) {
		        
		    }
		}
	 
	 public static void toArchivoBinarioInverso(List<Empleado> empleados) {
		    try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream("..\\arch\\empleados.txt"))) {
		        // Escribimos los empleados en orden inverso
		        for (int i = empleados.size() - 1; i >= 0; i--) {
		            escritor.writeObject(empleados.get(i));
		        }
		    } catch (IOException e) {
		    	
		    }
	 }
		    
	 // Metodo que le el archivo y lo escribe por pantalla
	public static void leerEmpleados(String archivo) {
		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))){
			String linea = reader.readLine();	
			while (linea != null) {
				System.out.println(linea);
			}
		} catch (Exception e) {
			
		}
		
	}
	
	public static void leerEmpleadosB(String archivo) {
		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(archivo))){
			String linea = (String) reader.readObject();
			while (linea != null) {
				System.out.println(linea);
			}
		} catch (Exception e) {
			
		}
		
	}
	
		
		    
		


	
}
