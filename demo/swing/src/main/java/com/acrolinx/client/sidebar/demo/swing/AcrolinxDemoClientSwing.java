/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.client.sidebar.demo.swing;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.*;

import com.acrolinx.sidebar.pojo.settings.AcrolinxSidebarInitParameter;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponent;
import com.acrolinx.sidebar.pojo.settings.SoftwareComponentCategory;
import com.acrolinx.sidebar.swing.AcrolinxSidebarSwing;
import com.acrolinx.sidebar.utils.LoggingUtils;

import ch.qos.logback.core.joran.spi.JoranException;

class AcrolinxDemoClientSwing
{
    public static void main(String[] args)
    {
        try {
            LoggingUtils.setupLogging("AcrolinxDemoClientSwing");
        } catch (IOException | JoranException | URISyntaxException e) {
            e.printStackTrace();
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame("Acrolinx Demo Client Swing");
            ArrayList<SoftwareComponent> softwareComponents = new ArrayList<>();
            softwareComponents.add(new SoftwareComponent("com.acrolinx.client.sidebar.demo.swing",
                    "Acrolinx Demo Client Swing", "1.0", SoftwareComponentCategory.MAIN));
            AcrolinxSidebarInitParameter initParameter = new AcrolinxSidebarInitParameter.AcrolinxSidebarInitParameterBuilder(
                    "SW50ZWdyYXRpb25EZXZlbG9wbWVudERlbW9Pbmx5", softwareComponents).withShowServerSelector(
                            true).build();

            JTextArea textArea = new JTextArea();
            textArea.setPreferredSize(new Dimension(550, 600));

            AcrolinxSwingIntegration integration = new AcrolinxSwingIntegration(initParameter, textArea);
            AcrolinxSidebarSwing acrolinxSidebarSwing = new AcrolinxSidebarSwing(integration, null);

            JFrame.setDefaultLookAndFeelDecorated(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setResizable(false);

            Container contentPanel = frame.getContentPane();
            JPanel innerPanel = new JPanel();
            innerPanel.setLayout(new FlowLayout());
            innerPanel.setPreferredSize(new Dimension(870, 600));
            innerPanel.add(textArea);
            innerPanel.add(acrolinxSidebarSwing);

            innerPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            contentPanel.add(innerPanel);

            frame.pack();
            frame.setVisible(true);
        });
    }
}
