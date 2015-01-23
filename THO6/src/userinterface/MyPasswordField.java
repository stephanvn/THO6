package userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPasswordField;
import javax.swing.text.Document;

public class MyPasswordField extends JPasswordField {
	
	private static final long serialVersionUID = 1L;
	private String placeholder;
	
	public MyPasswordField() {
		
	}

	public MyPasswordField(final Document pDoc,final String pText,final int pColumns) {
        super(pDoc, pText, pColumns);
    }
	
	public MyPasswordField(final int pColumns) {
	    super(pColumns);
    }

    public MyPasswordField(final String pText) {
        super(pText);
    }

    public MyPasswordField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder.length() == 0 || getPassword().length > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.black);
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics().getMaxAscent() + getInsets().top);
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

}
