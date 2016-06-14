package androidcourse.venturus.org.br.androidcourse_firstexample_likecounter;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by matuki on 12/28/15.
 */
public class ToastIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ToastIntentService(String name) {
        super(name);
    }

    public ToastIntentService() {
        super("");
    }

    private NotificationManager mNotificationManager;

    private int notificationId = 5556;

    @Override
    protected void onHandleIntent(Intent workIntent) {
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        showNotification("Estou aqui");

        sleep();

        showNotification("Ainda estou aqui");

        sleep();

        showNotification("Tchau!");

        sleep();
    }

    private void showNotification(String message) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this).setContentTitle(message).setSmallIcon(R.mipmap.ic_launcher);
        mNotificationManager.notify(notificationId, builder.build());
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
