package controllers;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import models.IModel;
import models.Model;
import views.ArmyBuildGUI.IArmyBuildObserver;
import views.IArmyBuildGUI;

/** 
 * Class that models the controller for the main view ArmyBuildGUI.
 * @author Luca Longobardi, Orzes Matteo
 *
 */
public class ArmyBuildController implements IArmyBuildObserver {
	
	private IModel model;
	private IArmyBuildGUI armyBuild;
	
	private static final String ERROR_SAVE_DATA = "Error while saving data";
	private static final String ERROR_LOAD_DATA = "Error while loading data";
	private static final String DEFAULT_SAVE_PATH = "data.ar";
	private static final String DEFAULT_CODEX_EXTENSION = "co";
	private static final String DEFAULT_LIST_EXTENSION = "ar";
	
	/**
	 * Constructor for controller that takes in input a model to operate with.
	 * @param newModel 
	 */
	public ArmyBuildController(final IModel newModel) {
		this.model = newModel;
	}
	@Override
	public IModel getModel() {
		return this.model;
	}
	
	@Override
	public void setView(final IArmyBuildGUI v) {
		this.armyBuild = v;
		this.armyBuild.attachObserver(this);
	}

	@Override
	public void loadCodexCmd(final String path) {
		if (path.endsWith(DEFAULT_CODEX_EXTENSION)) {
		    this.doLoadData(path);
		} else {
            this.armyBuild.showErrorDialog(ERROR_LOAD_DATA);
		}
	}

	@Override
	public void saveCodexCmd(final String path) {
		final IModel saveModel = new Model();
		saveModel.setCodexList(this.model.getCodexlist());
		this.doSaveData(path, saveModel); // adding default extension for codex list
	}

	@Override
	public void saveDataCmd() {
		this.doSaveData(DEFAULT_SAVE_PATH, model);
		
	}

	@Override
	public void loadDataCmd() {
		this.doLoadData(DEFAULT_SAVE_PATH);
		
	}

	@Override
	public void saveListCmd(final String path) {
		final IModel saveModel = new Model();
		saveModel.setCodexList(this.model.getCodexlist());
		saveModel.setArmylist(this.model.getArmyList());
		this.doSaveData(path, saveModel); // adding default extension for army list
		
	}
	
	@Override
	public void saveListAsTxtCmd(final String path) {
		this.doSaveAsTxt(path, this.model.toText());
	}
	
	

	@Override
	public void loadListCmd(final String path) {
		if (path.endsWith(DEFAULT_LIST_EXTENSION)) {
		    this.doLoadData(path);
		} else {
			this.armyBuild.showErrorDialog(ERROR_LOAD_DATA);
		}
	}
	
	/**
	 * Method used to save the current Army list as Txt.
	 * @param path 
	 * @param totext 
	 */
	private void doSaveAsTxt(final String path, final String totext) {
		try {
				final File totextFile = new File(path);
				final BufferedWriter out = new BufferedWriter(new FileWriter(totextFile));
		        /* Writing the model to disk */ 
		        out.write(totext);
		        out.close();
		    } catch (IOException ex) {
		      this.armyBuild.showErrorDialog(ERROR_SAVE_DATA);
		    }
	}

	  /** 
	   * Utility method for serializing the model to the file system
	   * Source: A.Santi Acme Exams project
	   * @param path
	   **/
	  private void doSaveData(final String path, final IModel saveModel) {
	    try {
	      final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
	      /* Writing the model to disk */ 
	      out.writeObject(saveModel);
	      out.close();
	    } catch (IOException ex) {
	      this.armyBuild.showErrorDialog(ERROR_SAVE_DATA);
	    }
	  }
	  
	  /** 
	   * Utility method for loading the model from the file system 
	   * Source: A. Santi Acme Exam project
	   * 
	   **/
	  private void doLoadData(final String path) {
	    try {
	      final ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
	      this.model = (IModel) in.readObject();
	      /* Closing the stream */ 
	      in.close();
	    } catch (EOFException ex) {
	      /* First launch, empty data */
	    } catch (Exception ex) {
	      this.armyBuild.showErrorDialog(ERROR_LOAD_DATA);
	    }
	  }

	@Override
	public void moveUp(final int index) {
		this.model.switchTwoElements(index, index - 1);
	}
	@Override
	public void moveDown(final int index) {
		this.model.switchTwoElements(index, index + 1);
	}
}
