package frame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by priprabh on 11/2/2017.
 */
public class SimpleCalculatorApplet extends JApplet {
    /**
     * Called by the browser or applet viewer to inform this applet that it has
     * been loaded into the system. It is always called before the first time
     * that the start method is called.
     *
     * A subclass of Applet should override this method if it has initialization
     * to perform.
     *
     * For example, an applet with threads would use the init method to create the threads and the
     * destroy method to kill them.
     *
     */

    /**
     * Initialise the SimpleCalculatorPanel class and applet for loading
     */
    public void init() {
        Container ContentPane = getContentPane();
        ContentPane.add(new SimpleCalculatorPanel());
    }
}
