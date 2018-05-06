package uiTools;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<Observer> _allObserver;
    
    protected Observable() {
	_allObserver = new ArrayList<>();
    }
    
    public void registerNewObserver(Observer newObserver) {
	_allObserver.add(newObserver);
    }
    
    protected void notifyObserver() {
	for (Observer observer : _allObserver) {
	    observer.update();
	}
    }
}
