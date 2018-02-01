package su.vistar.vetclinic.utilities;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

/**
 * Created by Владислав on 17.02.2017.
 */
public class Clipboard {

    public void copyToClipboard(String str){
        StringSelection stringSelection = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

}
