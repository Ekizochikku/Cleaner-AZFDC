import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class WorldPanel extends JPanel {
	public WorldPanel() {
		setLayout(null);
		
		JLabel lblTest = new JLabel("Test");
		lblTest.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblTest.setBounds(471, 260, 90, 93);
		add(lblTest);
	}

}
