package com.opensource.flow.analysis;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Aaron on 17/02/2017.
 */
public class WorkflowFrame extends JFrame {
    WorkflowInstanceUtils model = new WorkflowInstanceUtils();

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
        JPanel panel = new JPanel();
        setContentPane(panel);
        GridLayout gridLayout = new GridLayout(4, 2);
        setLayout(gridLayout);
        setFont(new Font("Helvetica", Font.PLAIN, 14));


        JLabel transtionLabel = new JLabel("transtion");
        add(transtionLabel);
        JTextArea transtions = new JTextArea();
        transtions.setAutoscrolls(true);
        transtions.setText("0-1,1-a,a-2,2-3,3-b,b-7,7-8,a-4,4-5,5-6,6-c,c-4,c-b");
        add(transtions);
        transtionLabel = new JLabel("instance");
        JTextArea instance = new JTextArea();
        instance.setAutoscrolls(true);
        instance.setText("0,1,2,4,5,3,6,4");
        add(transtionLabel);
        add(instance);
        JButton btn = new JButton("计算");
        btn.setSize(50, 100);
        add(btn);
        JTextArea result = new JTextArea();
        result.setAutoscrolls(true);
        add(result);
        btn.addActionListener(e -> {

            String transtion = transtions.getText();
            String inst = instance.getText();
            String[] ts = transtion.split(",");
            model.clear();
            for (int i = 0; i < ts.length; i++) {
                String[] sep = ts[i].split("-");
                String from = sep[0];
                String to = sep[1];
                model.getWorkflow().addTranstion(from, to);
            }

            String[] in = inst.split(",");
            for (int i = 0; i < in.length; i++) {
                model.getInstance().add(in[i]);
            }
            result.setText(model.caculate(model.getWorkflow(),model.getInstance()));

        });
    }
}
