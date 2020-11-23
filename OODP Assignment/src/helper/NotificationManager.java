package helper;


/**
 * Manages notification sending via different modes e.g. email, SMS, WhatsApp
 *
 */
public interface NotificationManager {

	/**
	 * sends Notification containing message to the specified contactInfo
	 * 
	 * @param contactInfo 	contact Information, e.g. email or handphone number as a string
	 * @param messageEntry	(a portion of) the message contents to be sent
	 * @return				String which differs depending on whether notification is sent successfully
	 */
	public abstract String sendNotification(String contactInfo, String messageEntry);

}
