package com.company;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Inpriron on 2/19/2017.
 */
public class GameWindows extends Frame{
    public GameWindows()
    {
        setVisible(true);
        setSize(400,600);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.out.println("WindowsClosing");
                System.exit(0);
            }
        });
    }
}
