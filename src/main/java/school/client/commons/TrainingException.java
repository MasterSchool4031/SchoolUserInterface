package school.client.commons;

/**
 * Exception soulevée pour signaler une action impossible dans la manipulation
 * des formations
 * 
 * @author Yannick Boogaerts pour STE-Formations<br>
 */
public class TrainingException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param arg0
	 *            message d'erreur
	 */
	public TrainingException(String arg0) {
		super(arg0);
	}

	/**
	 * Renvoit la cause de l'exception.
	 * 
	 * @param cause
	 *            la cause de l'exception.
	 */
	public TrainingException(Throwable cause) {
		super(cause);
	}

	/**
	 * Renvoit la cause initiale du soulèvement de l'Exception.
	 * 
	 * @return la cause initiale du soulèvement de l'Exception
	 */
	public Throwable getinitCause() {
		Throwable cause = this;
		while (cause.getCause() != null) {
			cause = cause.getCause();
		}
		return cause;
	}
}
