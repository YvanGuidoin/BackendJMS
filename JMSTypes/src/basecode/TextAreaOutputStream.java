package basecode;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Classe gérant le stream de sortie standard
 *
 * @author Yvan
 */
public class TextAreaOutputStream extends OutputStream {

    /**
     * TextArea cible
     */
    private final JTextArea textArea;
    /**
     * Constructeur de chaîne
     */
    private final StringBuilder sb = new StringBuilder();

    /**
     * Initialise le stream sur la sortie textArea
     *
     * @param textArea affichage de sortie
     */
    public TextAreaOutputStream(final JTextArea textArea) {
        this.textArea = textArea;
        sb.append("> ");
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

    @Override
    public void write(int b) throws IOException {
        if (b == '\r') {
            return;
        }
        if (b == '\n') {
            final String text = sb.toString() + "\n";
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    textArea.append(text);
                }
            });
            sb.setLength(0);
            sb.append("> ");
            return;
        }
        sb.append((char) b);
    }
}
