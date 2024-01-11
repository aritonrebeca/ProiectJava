import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class FormularSwing extends JFrame {
    private JTextField marcaTextField;
    private JTextField serieTextField;
    private JTextField culoareTextField;
    private JRadioButton anMaiMicRadioButton;
    private JRadioButton anMaiMareRadioButton;
    private JTextField modelMotorTextField;

    public FormularSwing() {
        setTitle("Formular cu Swing");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crearea elementelor UI
        marcaTextField = new JTextField(20);
        serieTextField = new JTextField(20);
        culoareTextField = new JTextField(20);
        anMaiMicRadioButton = new JRadioButton("An mai mic de 2000");
        anMaiMareRadioButton = new JRadioButton("An mai mare de 2000");
        ButtonGroup anGroup = new ButtonGroup();
        anGroup.add(anMaiMicRadioButton);
        anGroup.add(anMaiMareRadioButton);
        modelMotorTextField = new JTextField(20);

        JButton saveButton = new JButton("Salvează");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvareInJSON();
            }
        });

        JButton cancelButton = new JButton("Anulează");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Adăugarea elementelor în panou cu FlowLayout
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Marca mașinii:"));
        panel.add(marcaTextField);
        panel.add(new JLabel("Seria mașinii:"));
        panel.add(serieTextField);
        panel.add(new JLabel("Culoarea mașinii:"));
        panel.add(culoareTextField);
        panel.add(new JLabel("Anul mașinii:"));
        panel.add(anMaiMicRadioButton);
        panel.add(anMaiMareRadioButton);
        panel.add(new JLabel("Model motor:"));
        panel.add(modelMotorTextField);
        panel.add(saveButton);
        panel.add(cancelButton);

        // Adăugarea panoului în fereastra principală
        add(panel);

        // Setarea aspectului radio button-urilor
        UIManager.put("RadioButton.focus", UIManager.get("CheckBox.focus"));

        setVisible(true);
    }

    private void salvareInJSON() {
        JSONObject jsonObiect = new JSONObject();
        jsonObiect.put("Marca", marcaTextField.getText());
        jsonObiect.put("Serie", serieTextField.getText());
        jsonObiect.put("Culoare", culoareTextField.getText());
        jsonObiect.put("An", anMaiMicRadioButton.isSelected() ? "Mai mic de 2000" : "Mai mare de 2000");
        jsonObiect.put("ModelMotor", modelMotorTextField.getText());

        try (FileWriter fileWriter = new FileWriter("date.json", true)) {
            fileWriter.write(jsonObiect.toJSONString() + "\n");
            JOptionPane.showMessageDialog(this, "Date salvate cu succes în fișierul JSON!", "Salvare reușită", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la salvarea datelor în fișierul JSON!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularSwing();
            }
        });
    }
}
