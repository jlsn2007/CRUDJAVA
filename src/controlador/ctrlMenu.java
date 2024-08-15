package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Menu;
import vista.frmMenu;

public class ctrlMenu implements MouseListener, KeyListener{
    
    private Menu ModeloMenu;
    private frmMenu vistaMenu;
    
    public ctrlMenu(Menu menu, frmMenu frmmenu){
    
       this.ModeloMenu = menu;
       this.vistaMenu = frmmenu;
       
       
       
       //Siempre poner todos los botones que vamos a ocupar
       frmmenu.btnGuardar.addMouseListener(this);
       frmmenu.jtbMenu.addMouseListener(this);
       
       ModeloMenu.Mostrar(vistaMenu.jtbMenu);
           
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource()== vistaMenu.btnGuardar){
        
            ModeloMenu.setNombre(vistaMenu.txtNombre.getText());
            ModeloMenu.setPrecio(Double.parseDouble(vistaMenu.txtPrecio.getText()));
            ModeloMenu.setIngredientes(vistaMenu.txtIngrediente.getText());
            
            ModeloMenu.Guardar();
            ModeloMenu.Mostrar(vistaMenu.jtbMenu);
        }
        
        if(e.getSource()== vistaMenu.btnEliminar){
            ModeloMenu.Eliminar(vistaMenu.jtbMenu);
            ModeloMenu.Mostrar(vistaMenu.jtbMenu);
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
