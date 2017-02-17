package com.opensource.flow.analysis;

import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aaron on 17/02/2017.
 */
public class WorkflowFrame extends JFrame {
    public static void main(String[] args) {
        WorkflowFrame workflowFrame = new WorkflowFrame();
    }

    public WorkflowFrame() throws HeadlessException {
        init();
    }

    public void init() {
        setTitle("流程实例测试 ");
        setSize(400, 500);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWorkspace();
        setVisible(true);
    }

    private void addWorkspace() {
        setResizable(false);
        JPanel panel = new JPanel();
        setContentPane(panel);
        GridLayout gridLayout = new GridLayout(4, 2);
        setLayout(gridLayout);
        setFont(new Font("Helvetica", Font.PLAIN, 14));


        JLabel transtionLabel = new JLabel("transtion");
        add(transtionLabel);
        JTextArea jTextArea = new JTextArea();
        jTextArea.setToolTipText("输入 transtions: eg: 1-2-4-5,5-6-8-9");
        add(jTextArea);
        transtionLabel = new JLabel("instance");
        jTextArea = new JTextArea();
        jTextArea.setToolTipText("输入 instance: eg: 1-2-4-5");
        add(transtionLabel);
        add(jTextArea);
        JButton btn = new JButton("计算");
        btn.setSize(50, 100);
        add(btn);
    }
}
