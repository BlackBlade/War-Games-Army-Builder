package controllers;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import models.IModel;
import models.Model;
import views.CreateArmyCodexGUI.ICreateArmyCodexObserver;
import views.ICreateArmyCodexGUI;

/** 
 * Class that models the controller for the view CreateArmyCodexGUI.
 * @author Luca Longobardi, Orzes Matteo
 *
 */
public class CreateArmyCodexController implements ICreateArmyCodexObserver {

	private IModel model;
	private ICreateArmyCodexGUI armyCodex;
	private static final String ERROR_SAVE_DATA = "Error while saving data";
	private static final String ERROR_LOAD_DATA = "Error while loading data";
	private static final String DEFAULT_CODEX_EXTENSION = "co";

	/**
	 * Constructor for controller that takes in input a model to operate with.
	 * @param newModel 
	 */
	public CreateArmyCodexController(final IModel newModel) {
		this.model = newModel;
	}

	@Override
	public IModel getModel() {
		return this.model;
	}

	@Override
	public void setView(final ICreateArmyCodexGUI v) {
		this.armyCodex = v;
		this.armyCodex.attachObserver(this);
	}

	@Override
	public void loadCodexCmd(final String path) {
		if (path.endsWith(DEFAULT_CODEX_EXTENSION)) {
		    this.doLoadData(path);
		} else {
            this.armyCodex.showErrorDialog(ERROR_LOAD_DATA);
		}
	}

	@Override
	public void saveCodexCmd(final String path) {
		final IModel saveModel = new Model();
		saveModel.setCodexList(this.model.getCodexlist());
		this.doSaveData(path, saveModel);
	}

	/**
	 * Utility method for serializing the model to the file system Source:
	 * A.Santi Acme Exams project
	 * @param path
	 **/
	private void doSaveData(final String path, final IModel saveModel) {
		try {
			final ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(path));
			/* Writing the model to disk */
			out.writeObject(saveModel);
			out.close();
		} catch (IOException ex) {
			this.armyCodex.showErrorDialog(ERROR_SAVE_DATA);
		}
	}

	/**
	 * Utility method for loading the model from the file system Source: A.
	 * Santi Acme Exam project
	 **/
	private void doLoadData(final String path) {
		try {
			final ObjectInputStream in = new ObjectInputStream(
					new FileInputStream(path));
			this.model = (IModel) in.readObject();
			/* Closing the stream */
			in.close();
		} catch (EOFException ex) {
			/* First launch, empty data */
		} catch (Exception ex) {
			this.armyCodex.showErrorDialog(ERROR_LOAD_DATA);
		}
	}

}
