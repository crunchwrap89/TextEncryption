package marcusn;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class EncryptionUi extends JFrame{

	JPanel panel; 
    JButton kryptera;
    JButton avkryptera;
    String filnamnNy;
	String filnamnSpara;
	JButton nyFil = new JButton("Ny fil");
	JButton öppna = new JButton("Öppna");
	JButton spara = new JButton("Spara");
	JTextArea area = new JTextArea();
	JScrollPane sp = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
												JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    
public EncryptionUi run(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(900, 900);
        setVisible(true);
     
        panel = new JPanel();
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        sp.setPreferredSize(new Dimension(300,300));
        kryptera = new JButton("Kryptera");
        avkryptera = new JButton("Avkryptera");
        panel.add(nyFil);panel.add(öppna); panel.add(spara);
        panel.add(kryptera);panel.add(avkryptera);
        add(sp, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);
        
		nyFil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
						area.setText(" ");
		
			};
		});
		öppna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {	
						läsInFil(filnamnNy);
				
			};
		});
		spara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				sparaFil(area.getText());
			
			};
		});
		kryptera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String textkrypt = area.getText();  
				String krypteradText = krypteraAvkryptera(textkrypt); 
				area.setText(krypteradText);
			
			};
		});
		avkryptera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String textkrypt = area.getText();  
				String krypteradText = krypteraAvkryptera(textkrypt); 
				area.setText(krypteradText);
			
			};
		});

	return this;
        
}

private void läsInFil(String filnamn) {
	try {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fc.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			filnamn = selectedFile.getAbsolutePath();
			filnamnNy = selectedFile.getAbsolutePath();
		}
		
		
		FileReader fr = new FileReader(filnamn);
		area.read(fr, null);
	}
	catch(IOException e) {}
}

private void sparaFil(String filnamn) {
	try {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Namnge din fil och vart du vill spara den.");   
		 
		int userSelection = fileChooser.showSaveDialog(this);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    filnamn = fileToSave.getAbsolutePath();
		    
		    
		}
		FileWriter w = new FileWriter(filnamn);
		area.write(w);
		
		
	}
	catch (IOException e) {}
}

String krypteraAvkryptera(String inString) 
{ 
	String xorn = JOptionPane.showInputDialog(null, "Vilken krypteringsnyckel vill du använda?");
	char xorNyckel = xorn.charAt(0); 
	String utString = ""; 
	int len = inString.length(); 

	for (int i = 0; i < len; i++) 
	{ 
		utString = utString + 
		Character.toString((char) (inString.charAt(i) ^ xorNyckel)); 
	} 

	return utString; 
} 

}