package scripts.pestcontrol.gui;

import org.tribot.api.General;
import org.tribot.api2007.types.RSTile;

import scripts.pestcontrol.Main;
import scripts.pestcontrol.taskframework.TaskSet;
import scripts.pestcontrol.tasks.AttackPortals;
import scripts.pestcontrol.tasks.DefendKnights;
import scripts.pestcontrol.tasks.GameChat;
import scripts.pestcontrol.tasks.IdleBoat;
import scripts.pestcontrol.tasks.GrabKnightTile;
import scripts.pestcontrol.tasks.RideBoat;
import scripts.pestcontrol.utilities.Vars;

@SuppressWarnings("serial")
public class Gui extends javax.swing.JFrame {

	Main script;
	TaskSet taskset;

	public Gui(Main script, TaskSet taskset) {
		this.taskset = taskset;
		this.script = script;
		initComponents();
	}

	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		modeCombo = new javax.swing.JComboBox<>();
		noviceRadio = new javax.swing.JRadioButton();
		intermediateRadio = new javax.swing.JRadioButton();
		veteranRadio = new javax.swing.JRadioButton();
		startScriptBtn = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));

		jLabel1.setFont(new java.awt.Font("Segoe Print", 1, 36)); // NOI18N
		jLabel1.setText("C#2Bot Pest Control");

		jLabel2.setText("Mode:");

		modeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Attack Portals", "Defend Knight" }));
		modeCombo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				modeComboActionPerformed(evt);
			}
		});

		buttonGroup1.add(noviceRadio);
		noviceRadio.setText("Novice Boat");
		noviceRadio.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				noviceRadioActionPerformed(evt);
			}
		});

		buttonGroup1.add(intermediateRadio);
		intermediateRadio.setText("Intermediate Boat");
		intermediateRadio.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				intermediateRadioActionPerformed(evt);
			}
		});

		buttonGroup1.add(veteranRadio);
		veteranRadio.setText("Veteran Boat");
		veteranRadio.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				veteranRadioActionPerformed(evt);
			}
		});

		startScriptBtn.setText("Start Script");
		startScriptBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startScriptBtnActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
								.addGap(18, 18, 18).addGroup(layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel2)
										.addGroup(layout.createSequentialGroup()
												.addComponent(modeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 110,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(noviceRadio).addGap(9, 9, 9)
												.addComponent(intermediateRadio).addGap(8, 8, 8)
												.addComponent(veteranRadio))))
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(startScriptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 406,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel1))))
						.addGap(39, 39, 39).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup().addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel2)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(modeCombo, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(noviceRadio).addComponent(intermediateRadio).addComponent(veteranRadio))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(startScriptBtn).addGap(0, 15, Short.MAX_VALUE)).addComponent(jPanel1,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addContainerGap()));

		pack();
	}// </editor-fold>

	private void modeComboActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void startScriptBtnActionPerformed(java.awt.event.ActionEvent evt) {
		synchronized (script) {
			script.notify();
		}
		if (modeCombo.getSelectedIndex() == 0) {
			script.taskSet.addTasks(new GameChat(), new RideBoat(), new GrabKnightTile(), new IdleBoat(),
					new AttackPortals());
		}
		if (modeCombo.getSelectedIndex() == 1) {
			script.taskSet.addTasks(new GameChat(), new RideBoat(), new GrabKnightTile(), new IdleBoat(),
					new DefendKnights());
		}
		if (noviceRadio.isSelected()) {
			General.println("We are using novice boat.");
			Vars.novice = true;
			Vars.gang_Plank_Tile = new RSTile(2658, 2639, 0);
		}
		if (intermediateRadio.isSelected()) {
			General.println("We are using intermediate boat.");
			Vars.intermediate = true;
			Vars.gang_Plank_Tile = new RSTile(2643, 2644, 0);
		}
		if (this.veteranRadio.isSelected()) {
			General.println("We are using intermediate boat.");
			Vars.veteran = true;
			Vars.gang_Plank_Tile = new RSTile(2737, 2653, 0);
		}
		this.dispose();
		this.setVisible(false);
		Vars.start = true;
	}

	private void noviceRadioActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void intermediateRadioActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void veteranRadioActionPerformed(java.awt.event.ActionEvent evt) {

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	// Variables declaration - do not modify
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JRadioButton intermediateRadio;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JComboBox<String> modeCombo;
	private javax.swing.JRadioButton noviceRadio;
	private javax.swing.JButton startScriptBtn;
	private javax.swing.JRadioButton veteranRadio;
	// End of variables declaration
}
