package scripts.advancedcutter.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.tribot.api.General;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import org.tribot.util.Util;

import com.allatori.annotations.DoNotRename;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import scripts.advancedcutter.Main;
import scripts.advancedcutter.api.gui.AbstractGUIController;
import scripts.advancedcutter.api.utilities.Vars;
import scripts.advancedcutter.enums.treetypes.TreeTypes;

@DoNotRename
public class MainController extends AbstractGUIController {

	@FXML
	@DoNotRename
	private Button startBtn, setTreeSpotWalkBtn, setFirstBankTileBtn, setSecondBankTileBtn, setFirstTreeAreaBtn,
			setSecondTreeAreaBtn, saveBtn, loadBtn;
	@FXML
	@DoNotRename
	private ComboBox<String> methodCombo, locationCombo, treeCombo, presetCombo;

	@FXML
	@DoNotRename
	private ListView<String> optionListView;

	@FXML
	@DoNotRename
	private AnchorPane customChopPane, mainPane;

	@FXML
	@DoNotRename
	private TextField treeSpotWalkTile, treeAreaFirstTile, treeAreaSecondTile, bankAreafirstTile, bankAreaSecondTile,
			presetName;

	@FXML
	@DoNotRename
	private CheckBox powerchop, progressiveLeveling;

	@FXML
	@DoNotRename
	private ImageView image;

	private ObservableList<String> methodList, treeList, locationList;

	private RSTile firstBankTile, secondBankTile, firstTreeTile, secondTreeTile;

	private static File path;
	private static Properties prop = new Properties();

	@DoNotRename
	public void buttonAction(ActionEvent event) {
		Node node = (Node) event.getSource();
		if (node.getId().equals(this.startBtn.getId())) {
			this.startBtnEvent();
		}
		if (node.getId().equals(this.saveBtn.getId())) {
			this.saveBtnEvent();
		}
		if (node.getId().equals(this.loadBtn.getId())) {
			this.loadBtnEvent();
		}
		if (node.getId().equals(this.setTreeSpotWalkBtn.getId())) {
			General.println("The tree spot walking tile is set to " + Player.getPosition());
			Vars.chopAreaWalkTile = Player.getPosition();
			this.treeSpotWalkTile.setText(String.valueOf(Player.getPosition()));
		}
		if (node.getId().equals(this.setFirstBankTileBtn.getId())) {
			General.println("First bank tile set to: " + Player.getPosition());
			this.firstBankTile = Player.getPosition();
			this.bankAreafirstTile.setText(String.valueOf(Player.getPosition()));
		}
		if (node.getId().equals(this.setSecondBankTileBtn.getId())) {
			General.println("Second bank tile set to: " + Player.getPosition());
			this.secondBankTile = Player.getPosition();
			this.bankAreaSecondTile.setText(String.valueOf(Player.getPosition()));
		}
		if (node.getId().equals(this.setFirstTreeAreaBtn.getId())) {
			General.println("First tree area tile set to: " + Player.getPosition());
			this.firstTreeTile = Player.getPosition();
			this.treeAreaFirstTile.setText(String.valueOf(Player.getPosition()));
		}
		if (node.getId().equals(this.setSecondTreeAreaBtn.getId())) {
			General.println("Second tree area tile set to: " + Player.getPosition());
			this.secondTreeTile = Player.getPosition();
			this.treeAreaSecondTile.setText(String.valueOf(Player.getPosition()));
		}
	}

	@DoNotRename
	private void loadBtnEvent() {
		path = new File(Util.getWorkingDirectory().getAbsolutePath(),
				this.presetCombo.getValue() + "_C#2Bot_Advanced_Cutter.ini");
		try {
			if (path.exists()) {

				prop.load(new FileInputStream(path));
				String powerchop = prop.getProperty("powerchop");
				String chopMethod = prop.getProperty("chopmethod");
				Vars.treeType = prop.getProperty("treeType");

				if (chopMethod.equals("Custom chop")) {
					this.methodCombo.getSelectionModel().select(0);
				} else {
					this.methodCombo.getSelectionModel().select(1);
				}

				if (Boolean.valueOf(powerchop) == true) {
					this.powerchop.setSelected(true);
				}

				this.firstBankTile = new RSTile(Integer.parseInt(prop.getProperty("bankfirsttilex")),
						Integer.parseInt(prop.getProperty("bankfirsttiley")), 0);
				this.secondBankTile = new RSTile(Integer.parseInt(prop.getProperty("banksecondtilex")),
						Integer.parseInt(prop.getProperty("banksecondtiley")), 0);

				this.firstTreeTile = new RSTile(Integer.parseInt(prop.getProperty("treefirsttilex")),
						Integer.parseInt(prop.getProperty("treefirsttiley")), 0);
				this.secondTreeTile = new RSTile(Integer.parseInt(prop.getProperty("treesecondtilex")),
						Integer.parseInt(prop.getProperty("treesecondtiley")), 0);

				Vars.chopAreaWalkTile = new RSTile(Integer.parseInt(prop.getProperty("treewalktilex")),
						Integer.parseInt(prop.getProperty("treewalktiley")), 0);

				if (this.powerchop.isSelected()) {
					Vars.powerChop = true;
				}
				for (TreeTypes tree : TreeTypes.values()) {
					if (tree.getName().equals(Vars.treeType)) {
						this.treeCombo.getSelectionModel().select(tree.getIndex());
					}
				}
				this.bankAreafirstTile.setText(String.valueOf(this.firstBankTile));
				this.bankAreaSecondTile.setText(String.valueOf(this.secondBankTile));
				this.treeAreaFirstTile.setText(String.valueOf(this.firstTreeTile));
				this.treeAreaSecondTile.setText(String.valueOf(this.secondTreeTile));
				this.treeSpotWalkTile.setText(String.valueOf(Vars.chopAreaWalkTile));
			}

		} catch (Exception e2) {
			System.out.print("Unable to load settings");
		}
	}

	@DoNotRename
	private void saveBtnEvent() {
		path = new File(Util.getWorkingDirectory().getAbsolutePath(),
				this.presetName.getText() + "_C#2Bot_Advanced_Cutter.ini");
		try {
			prop.clear();
			prop.put("chopmethod", String.valueOf(this.methodCombo.getValue()));
			prop.put("treeType", String.valueOf(this.treeCombo.getValue()));
			prop.put("bankfirsttilex", String.valueOf(this.firstBankTile.getX()));
			prop.put("bankfirsttiley", String.valueOf(this.firstBankTile.getY()));
			prop.put("banksecondtilex", String.valueOf(this.secondBankTile.getX()));
			prop.put("banksecondtiley", String.valueOf(this.secondBankTile.getY()));
			prop.put("treefirsttilex", String.valueOf(this.firstTreeTile.getX()));
			prop.put("treefirsttiley", String.valueOf(this.firstTreeTile.getY()));
			prop.put("treesecondtilex", String.valueOf(this.secondTreeTile.getX()));
			prop.put("treesecondtiley", String.valueOf(this.secondTreeTile.getY()));
			prop.put("treewalktilex", String.valueOf(Vars.chopAreaWalkTile.getX()));
			prop.put("treewalktiley", String.valueOf(Vars.chopAreaWalkTile.getY()));
			prop.put("powerchop", String.valueOf(this.powerchop.isSelected()));
			prop.store(new FileOutputStream(path), "GUI Settings");
			System.out.print(this.presetName.getText() + " preset saved successfully.");
		} catch (Exception e1) {
			System.out.print("Save failed, please fill all the required field");
		}
	}

	@DoNotRename
	private void handleCustomArea() {
		if (this.firstBankTile != null && this.secondBankTile != null) {
			Vars.bankArea = new RSArea(this.firstBankTile, this.secondBankTile);
		}
		if (this.firstTreeTile != null && this.secondTreeTile != null) {
			Vars.chopArea = new RSArea(this.firstTreeTile, this.secondTreeTile);
		}
	}

	private void startBtnEvent() {
		Vars.start = true;
		Vars.powerChop = this.powerchop.isSelected();
		if (this.progressiveLeveling.isSelected()) {
			Vars.progressiveLevel = true;
		} else {
			if (this.methodCombo.getSelectionModel().getSelectedIndex() == 0) {
				this.handleCustomArea();
			} else {

			}
		}
		Main.gui.close();
	}

	@DoNotRename
	private void shouldDisableMain(boolean disable) {
		for (Node node : this.mainPane.getChildren()) {
			if (node instanceof Button || node instanceof ComboBox || node instanceof TextField
					|| node instanceof ListView || node instanceof CheckBox) {
				if (node instanceof Button) {
					Button btn = (Button) node;
					if (!btn.getId().equals(this.startBtn.getId()))
						node.setDisable(disable);
				} else {
					if (node instanceof CheckBox) {
						CheckBox cb = (CheckBox) node;
						if (!cb.getId().equals(this.progressiveLeveling.getId()))
							node.setDisable(disable);
					} else {
						node.setDisable(disable);
					}
				}
			}
		}
	}

	@DoNotRename
	private void shouldDisableCustom(boolean disable) {
		for (Node node : this.customChopPane.getChildren()) {
			if (node instanceof Button || node instanceof ComboBox || node instanceof ListView) {
				if (node instanceof Button) {
					node.setDisable(disable);
				} else {
					node.setDisable(disable);
				}
			}
		}
	}

	@DoNotRename
	public void progressiveLevelingSelected(ActionEvent event) {
		if (this.progressiveLeveling.isSelected()) {
			this.shouldDisableMain(true);
			this.shouldDisableCustom(true);
		} else {
			this.shouldDisableMain(false);
			this.shouldDisableCustom(false);
		}
	}

	@DoNotRename
	public void powerChopSelected(ActionEvent event) {
		for (Node node : this.customChopPane.getChildren()) {
			if (node instanceof Button) {
				if (methodCombo.getSelectionModel().getSelectedIndex() == 0) {
					if (this.powerchop.isSelected()) {
						Button btn = (Button) node;
						if (btn.getId().equals(this.setFirstBankTileBtn.getId())
								|| btn.getId().equals(this.setSecondBankTileBtn.getId())) {
							node.setDisable(true);
						}
					} else {
						node.setDisable(false);
					}
				} else {
					node.setDisable(true);
				}
			}
		}
	}

	@DoNotRename
	public void treeComboChanged(ActionEvent event) {
		this.optionListView.getItems().clear();
		if (this.locationCombo.getSelectionModel().getSelectedItem() == null) {
			this.optionListView.getItems().add("Please select");
			this.optionListView.getItems().add("a location");
		}
	}

	@DoNotRename
	public void methodComboChanged(ActionEvent event) {
		this.optionListView.getItems().clear();
		this.powerchop.setSelected(false);
		if (this.presetCombo.getSelectionModel().getSelectedItem() != null) {
			this.presetName.setText(this.presetCombo.getValue());
			this.loadBtnEvent();
		}
		if (this.treeCombo.getSelectionModel().getSelectedItem() == null) {
			this.optionListView.getItems().add("Please select");
			this.optionListView.getItems().add("a tree");
		}
		for (Node node : this.customChopPane.getChildren()) {
			if (node instanceof Button) {
				if (methodCombo.getSelectionModel().getSelectedIndex() == 0) {
					this.locationCombo.setDisable(true);
					node.setDisable(false);
				} else {
					this.locationCombo.setDisable(false);
					node.setDisable(true);
				}
			}
		}
	}

	@DoNotRename
	private void grabSettingFiles() {
		path = new File(Util.getWorkingDirectory().getAbsolutePath());
		File[] files = path.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.getName().contains("C#2Bot_Advanced_Cutter")) {
					List<String> list = new ArrayList<String>();
					list.add(file.getName().replace("_C#2Bot_Advanced_Cutter.ini", ""));
					ObservableList<String> obList = FXCollections.observableList(list);
					this.presetCombo.getItems().clear();
					this.presetCombo.setItems(obList);
				}
			}
		}
	}

	@Override
	@DoNotRename
	public void initialize(URL location, ResourceBundle resources) {

		this.methodList = FXCollections.observableArrayList("Custom chop", "Standard chop");
		this.treeList = FXCollections.observableArrayList("Tree", "Oak", "Willow", "Yew");
		this.locationList = FXCollections.observableArrayList("Varrock", "Falador", "Catherby", "Draynor", "Lumbridge");
		this.optionListView.getItems().add("Please choose");
		this.optionListView.getItems().add("options from");
		this.optionListView.getItems().add("the comboxes.");
		this.image.setImage(new Image("http://i.imgur.com/umqvzoH.jpg"));
		if (this.methodList.size() > 0) {
			this.methodCombo.setItems(this.methodList);
		}
		if (this.treeList.size() > 0) {
			this.treeCombo.setItems(this.treeList);
		}
		if (this.locationList.size() > 0) {
			this.locationCombo.setItems(this.locationList);
		}
		this.grabSettingFiles();
	}

}
