package study.javase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class Main extends JFrame {

    private final JTextArea textArea;
    private File currentFile = null; // 当前打开或保存的文件

    public Main() {
        setTitle("仿记事本");
        setSize(600, 500);
        setLocationRelativeTo(null); // 居中
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        add(new JScrollPane(textArea));

        createMenu();
    }

    // 创建菜单栏
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("文件");

        JMenuItem newItem = new JMenuItem("新建");
        JMenuItem openItem = new JMenuItem("打开");
        JMenuItem saveItem = new JMenuItem("保存");
        JMenuItem saveAsItem = new JMenuItem("另存为");

        newItem.addActionListener(e -> newFile());
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        saveAsItem.addActionListener(e -> saveFileAs());

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    // 新建
    private void newFile() {
        textArea.setText("");
        currentFile = null;
        setTitle("仿记事本 - 新建");
    }

    // 打开文件（使用 Files.readAllBytes 兼容 Java 7+）
    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            setTitle("仿记事本 - " + currentFile.getName());

            try {
                byte[] data = Files.readAllBytes(currentFile.toPath());
                textArea.setText(new String(data, StandardCharsets.UTF_8));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "文件读取失败！");
            }
        }
    }

    // 保存（已有文件才保存，否则执行另存为）
    private void saveFile() {
        if (currentFile == null) {
            saveFileAs();
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(currentFile)) {
            fos.write(textArea.getText().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "保存失败！");
        }
    }

    // 另存为
    private void saveFileAs() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();

            try (FileOutputStream fos = new FileOutputStream(currentFile)) {
                fos.write(textArea.getText().getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "保存失败！");
            }

            setTitle("仿记事本 - " + currentFile.getName());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
