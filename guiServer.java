import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class guiServer extends JFrame {
    private JLabel ipLabel;
    private JLabel portLabel;

    public guiServer() {
        super("Server GUI");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // สร้าง JPanel สำหรับแสดงข้อมูล IP และ Port
        JPanel infoPanel = new JPanel(new GridLayout(1, 2));
        ipLabel = new JLabel("IP: ");
        portLabel = new JLabel("Port: ");
        infoPanel.add(ipLabel);
        infoPanel.add(portLabel);

        // กำหนดให้ JLabel อยู่ตรงกลางของ JPanel
        ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
        portLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // สร้าง JPanel สำหรับปุ่ม Start Server และ Close Server
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton startButton = new JButton("Start Server");
        JButton closeButton = new JButton("Close Server");
        buttonPanel.add(startButton);
        buttonPanel.add(closeButton);

        // นำ JPanel ทั้งสองมาต่อกัน
        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // กำหนด ActionListener สำหรับปุ่ม Start Server
        startButton.addActionListener(e -> startServer());

        // กำหนด ActionListener สำหรับปุ่ม Close Server
        closeButton.addActionListener(e -> closeServer());

        // แสดง GUI ตรงกลางของหน้าจอ
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // เริ่มการทำงานของเซิร์ฟเวอร์
    private void startServer() {
        // ทำสิ่งที่ต้องการเมื่อคลิกปุ่ม "Start Server"
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String ip = inetAddress.getHostAddress();
            ipLabel.setText("IP: " + ip);
            portLabel.setText("Port: 1234"); // กำหนดพอร์ตเฉยๆ ให้เป็น 1234 (สามารถแก้ไขได้ตามต้องการ)
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    // ปิดการทำงานของเซิร์ฟเวอร์
    private void closeServer() {
        // ทำสิ่งที่ต้องการเมื่อคลิกปุ่ม "Close Server"
        dispose(); // ปิดหน้าต่าง GUI
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new guiServer());
    }
}
