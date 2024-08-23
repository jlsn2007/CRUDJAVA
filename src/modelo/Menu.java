package modelo;

import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.frmMenu;

public class Menu {
    
    String UUID_Menu;
    String nombre;
    double precio;
    String ingredientes;

    public String getUUID_Menu() {
        return UUID_Menu;
    }

    public void setUUID_Menu(String UUID_Menu) {
        this.UUID_Menu = UUID_Menu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }
    
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addMenu = conexion.prepareStatement("INSERT INTO tbMenu(UUID_menu, nombre, precio, ingredientes) VALUES (?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addMenu.setString(1, UUID.randomUUID().toString());
            addMenu.setString(2, getNombre());
            addMenu.setDouble(3, getPrecio());
            addMenu.setString(4, getIngredientes());
 
            //Ejecutar la consulta
            addMenu.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloTacos = new DefaultTableModel();
        modeloTacos.setColumnIdentifiers(new Object[]{"UUID_menu", "nombre", "precio", "ingredientes"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbMenu");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloTacos.addRow(new Object[]{rs.getString("UUID_menu"), 
                    rs.getString("nombre"), 
                    rs.getDouble("precio"), 
                    rs.getString("ingredientes")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloTacos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    public void cargarDatosTabla(frmMenu vista) {
        
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbMenu.getSelectedRow();
        
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUID_menu = vista.jtbMenu.getValueAt(filaSeleccionada, 0).toString();
            String nombre = vista.jtbMenu.getValueAt(filaSeleccionada, 1).toString();
            String precio = vista.jtbMenu.getValueAt(filaSeleccionada, 2).toString();
            String ingredientes = vista.jtbMenu.getValueAt(filaSeleccionada, 3).toString();
            // Establece los valores en los campos de texto
            vista.txtNombre.setText(nombre);
            vista.txtPrecio.setText(precio);
            vista.txtIngrediente.setText(ingredientes);
        }
    }
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbMenu where UUID_menu = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbMenu set nombre= ?, precio = ?, ingredientes = ? where UUID_menu = ?");
                updateUser.setString(1, getNombre());
                updateUser.setDouble(2, getPrecio());
                updateUser.setString(3, getIngredientes());
                updateUser.setString(4, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no funciona actualizar");
        }
    }
    
}
