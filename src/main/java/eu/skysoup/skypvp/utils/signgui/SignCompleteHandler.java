package eu.skysoup.skypvp.utils.signgui;

/**
 * JavaDoc this file!
 * Created: 13.01.2023
 *
 * @author thvf
 */
@FunctionalInterface
public interface SignCompleteHandler {
    void onSignClose(SignCompletedEvent event);
}
