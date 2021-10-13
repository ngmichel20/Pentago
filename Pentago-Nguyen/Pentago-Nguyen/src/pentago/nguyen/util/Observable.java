package pentago.nguyen.util;

/**
 * This class is the Observable.
 * 
 * @author Michel
 */
public interface Observable {

    /**
     * This method allows to add an observer.
     * 
     * @param obs is the observer.
     */
    void addObserver(Observer obs);

    /**
     * This method allows to remove an observer.
     * 
     * @param obs is the observer.
     */
    void removeObserver(Observer obs);
}
