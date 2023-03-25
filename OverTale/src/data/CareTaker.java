package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CareTaker {
	private static final int MAX_STATES = 3;
	private final Memento[] mementos = new Memento[MAX_STATES];
	
	private int currentIndex = -1;
	
	private static final String FILENAME = "save.dat";
	
	public CareTaker() {
		//load();
	}

	public void addMemento(Memento memento) {
		
		currentIndex++;
		if (currentIndex >= MAX_STATES) {
			currentIndex = 0;
		}
		mementos[currentIndex] = memento;
		//save();
	}

	public Memento getMemento(int index) {
		return mementos[index];
	}
	
	public int GetNumberOfMementos() {
		int num = 0;
		for (int i=0; i<=this.mementos.length-1; i++) {
            if (mementos[i] != null) {
                num++;
            }
		}
		return num;
	}
}
