// P A C K A G E ///////////////////////////////////////////////////////////////
package com.popapp.swing;

// I M P O R T /////////////////////////////////////////////////////////////////
import java.util.Timer;

import javax.swing.JTextField;

// C L A S S ///////////////////////////////////////////////////////////////////
/**
 * Este bean es un campo de texto que mantiene sincronizado su texto con el
 * valor seleccionado de otro bean que pueda tener varios valores (listas,
 * combos, tablas y arboles de forma que al cambiar la seleccion en el bean que
 * contiene el origen de los datos cambie el valor del texto y que proporcione
 * autocompletado con las opciones disponibles dentro del bean de datos al que
 * esta asignado
 * PENDIENTE: reservar el control del teclado para teclas como el tabulador,
 * retorno y escape (ver Keymaps y FocusManager)
 * NOTA: por el momento el autocompletado solo se llevara a cabo al pulsar la
 * tecla tabulador
 * NOTA: hacer una clase abstracta, y de esta derivar las que ejecuten el
 * autocompletado sobre cada tipo de bean: listas (y combos), arboles y tablas
 */
public abstract class AutoCompleteTextField extends JTextField
{
    protected Timer autoCompleteTimer = null;
    protected int popupDelay = 0; // Retardo del autocompletado
    protected int popupKeys = 0; // Teclas que invocan al autocompletado
    protected int completeKeys = 0; // Teclas que completan la selección con los resultados del autocompletado
    protected int cancelKeys = 0; // Teclas que cancelan el completado

    public AutoCompleteTextField()
    {
        super();
        autoCompleteTimer = new Timer ();
        popupDelay = 1;
    }

    public void completeWithMatch ()
    {
    }

    public int getAutoCompleteDelay ()
    {
        return popupDelay;
    }

    public abstract String getMatchingElement();

    public void setPopupDelay (int newPopupDelay)
    {
        // Si el intervalo es negativo, desactivar el autocompletado
        popupDelay = newPopupDelay;
    }
}
// E O F ///////////////////////////////////////////////////////////////////////
